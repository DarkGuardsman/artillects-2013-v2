package dark.gsm.fortress.platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.CustomDamageSource;
import dark.core.prefab.damage.IDamageableTile;
import dark.core.prefab.terminal.TileEntityTerminal;
import dark.gsm.fortress.Sentries;
import dark.gsm.fortress.api.AmmoPair;
import dark.gsm.fortress.api.IAmmo;
import dark.gsm.fortress.api.IAmmunition;
import dark.gsm.fortress.api.ISentryUpgrade;
import dark.gsm.fortress.api.ProjectileTypes;
import dark.gsm.fortress.turret.TileEntityTurretBase;

public class TileEntityTurretPlatform extends TileEntityTerminal implements IAmmunition, IInventory
{

    /** The turret linked to this platform. */
    private TileEntityTurretBase turret = null;
    /** Deploy direction of the sentry */
    public ForgeDirection deployDirection = ForgeDirection.UP;
    /** CURRENT MAP OF UPGRADES STATS */
    public HashMap<String, Float> upgrades = new HashMap<String, Float>();
    /** The start index of the upgrade slots for the turret. */
    public static final int UPGRADE_START_INDEX = 12;

    /** The first 12 slots are for ammunition. The last 4 slots are for upgrades. */
    public ItemStack[] containingItems = new ItemStack[UPGRADE_START_INDEX + 4];

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (!this.isDisabled())
        {
            TileEntityTurretBase turret = this.getTurret(false);

            if (this.isRunning() && turret != null)
            {
                this.setEnergyStored(this.getEnergyStored() - turret.getRunningRequest());
            }
        }

    }

    @Override
    public float receiveElectricity(ForgeDirection from, ElectricityPack receive, boolean doReceive)
    {
        /** Creates an explosion if the voltage is too high. */
        if (UniversalElectricity.isVoltageSensitive && receive != null)
        {
            if (receive.voltage > this.getVoltage())
            {
                TileEntityTurretBase turret = this.getTurret(false);
                if (turret != null && turret instanceof IDamageableTile)
                {
                    ((IDamageableTile) this.turret).onDamageTaken(CustomDamageSource.electrocution, Integer.MAX_VALUE);
                }
                return 0;
            }
        }
        return super.receiveElectricity(from, receive, doReceive);
    }

    @Override
    public float getRequest(ForgeDirection side)
    {
        if (this.getTurret(false) != null)
        {
            return this.getTurret(false).getRunningRequest();
        }

        return 0;
    }

    /** Gets the turret instance linked to this platform */
    public TileEntityTurretBase getTurret(boolean getNew)
    {
        Vector3 vec = new Vector3(this);
        if (getNew || this.turret == null || this.turret.isInvalid() || !(new Vector3(this.turret).equals(vec.clone().modifyPositionFromSide(this.deployDirection))))
        {
            TileEntity tileEntity = vec.clone().modifyPositionFromSide(this.deployDirection).getTileEntity(this.worldObj);
            if (tileEntity instanceof TileEntityTurretBase)
            {
                this.turret = (TileEntityTurretBase) tileEntity;
            }
            else
            {
                this.turret = null;
            }
        }
        return this.turret;
    }

    /** if a sentry is spawned above the stand it is removed
     * 
     * @return */
    public boolean destroyTurret()
    {
        TileEntity ent = this.worldObj.getBlockTileEntity(this.xCoord + deployDirection.offsetX, this.yCoord + deployDirection.offsetY, this.zCoord + deployDirection.offsetZ);

        if (ent instanceof TileEntityTurretBase)
        {
            this.turret = null;
            return ((TileEntityTurretBase) ent).destroy(false);
        }

        return false;
    }

    public boolean destroy(boolean doExplosion)
    {
        if (doExplosion)
        {
            this.worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, 2f, true);
        }

        if (!this.worldObj.isRemote)
        {
            this.getBlockType().dropBlockAsItem(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata(), 0);
        }

        return this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, 0);
    }

    @Override
    public String getInvName()
    {
        return this.getBlockType().getLocalizedName();
    }

    public boolean isRunning()
    {
        return !this.isDisabled() && (this.getTurret(false) != null && this.getTurret(false).getRunningRequest() <= this.getEnergyStored() || this.runPowerLess());
    }

    @Override
    public AmmoPair<IAmmo, ItemStack> hasAmmunition(ProjectileTypes ammunitionStack)
    {
        for (int i = 0; i < TileEntityTurretPlatform.UPGRADE_START_INDEX; i++)
        {
            ItemStack itemStack = this.containingItems[i];

            if (itemStack != null)
            {
                Item item = Item.itemsList[itemStack.itemID];
                if (item instanceof IAmmo && ((IAmmo) item).getType(itemStack.getItemDamage()).ordinal() == ammunitionStack.ordinal())
                {
                    return new AmmoPair<IAmmo, ItemStack>(((IAmmo) item), itemStack);
                }
            }
        }
        return null;
    }

    @Override
    public boolean useAmmunition(AmmoPair<IAmmo, ItemStack> ammo)
    {
        if (ammo != null && ammo.getStack() != null)
        {
            ItemStack ammoStack = ammo.getStack().copy();
            for (int i = 0; i < TileEntityTurretPlatform.UPGRADE_START_INDEX; i++)
            {
                ItemStack itemStack = this.containingItems[i];

                if (itemStack != null)
                {
                    if (itemStack.isItemEqual(ammoStack))
                    {
                        this.containingItems[i] = ammo.getAmmo().consumeItem(itemStack);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** updates the hash map with the current upgrades to be applied to the sentry */
    public void processUpgrades()
    {
        List<ItemStack> used = new ArrayList<ItemStack>();
        this.upgrades.clear();
        for (int slot = UPGRADE_START_INDEX; slot < UPGRADE_START_INDEX + 4; slot++)
        {
            ItemStack itemStack = this.getStackInSlot(slot);

            if (itemStack != null)
            {
                if (itemStack.getItem() instanceof ISentryUpgrade)
                {
                    List<String> names = ((ISentryUpgrade) itemStack.getItem()).getTypes(itemStack);
                    for (String name : names)
                    {
                        float increase = 0;

                        for (int s = 0; s < itemStack.stackSize; s++)
                        {
                            float up = ((ISentryUpgrade) itemStack.getItem()).getEffectiveness(itemStack);
                            int count = 0;

                            for (ItemStack stack : used)
                            {
                                if (stack.isItemEqual(itemStack))
                                {
                                    count++;
                                }
                            }
                            for (int i = 0; i < count; i++)
                            {
                                up *= .1;
                            }
                            used.add(new ItemStack(itemStack.itemID, 0, itemStack.getItemDamage()));
                            increase += up;
                        }
                        if (this.upgrades.containsKey(name))
                        {
                            increase += this.upgrades.get(name);
                        }
                        this.upgrades.put(name, increase);

                    }
                }
            }
        }
    }

    /** Gets the change for the upgrade type 100% = 1.0 */
    public float getUpgradePercent(String name)
    {
        if (this.upgrades.containsKey(name))
        {
            return this.upgrades.get(name);
        }
        return 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        // Inventory
        NBTTagList var2 = nbt.getTagList("Items");
        this.containingItems = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.containingItems.length)
            {
                this.containingItems[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        // Inventory
        NBTTagList itemTag = new NBTTagList();
        for (int slots = 0; slots < this.containingItems.length; ++slots)
        {
            if (this.containingItems[slots] != null)
            {
                NBTTagCompound itemNbtData = new NBTTagCompound();
                itemNbtData.setByte("Slot", (byte) slots);
                this.containingItems[slots].writeToNBT(itemNbtData);
                itemTag.appendTag(itemNbtData);
            }
        }

        nbt.setTag("Items", itemTag);
    }

    @Override
    public int getSizeInventory()
    {
        return this.containingItems.length;
    }

    /** Returns the stack in slot i */
    @Override
    public ItemStack getStackInSlot(int par1)
    {
        return this.containingItems[par1];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.containingItems[par1] != null)
        {
            ItemStack var2 = this.containingItems[par1];
            this.containingItems[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.containingItems[par1] != null)
        {
            ItemStack var3;

            if (this.containingItems[par1].stackSize <= par2)
            {
                var3 = this.containingItems[par1];
                this.containingItems[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.containingItems[par1].splitStack(par2);

                if (this.containingItems[par1].stackSize == 0)
                {
                    this.containingItems[par1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.containingItems[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return true;
    }

    @Override
    public void openChest()
    {
    }

    @Override
    public void closeChest()
    {
    }

    @Override
    public boolean canConnect(ForgeDirection direction)
    {
        return true;
    }

    @Override
    public boolean isInvNameLocalized()
    {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int slotID, ItemStack itemStack)
    {
        if (slotID < UPGRADE_START_INDEX && itemStack.getItem() instanceof IAmmunition)
        {
            return true;
        }

        return false;
    }

    @Override
    public void onInventoryChanged()
    {
        super.onInventoryChanged();
        this.processUpgrades();
    }

    @Override
    public String getChannel()
    {
        return Sentries.CHANNEL;
    }

}