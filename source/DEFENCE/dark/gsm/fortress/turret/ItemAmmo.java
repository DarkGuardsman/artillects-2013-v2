package dark.gsm.fortress.turret;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import universalelectricity.core.vector.Vector3;

import com.builtbroken.common.Pair;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dark.core.prefab.damage.TileDamageSource;
import dark.core.prefab.helpers.AutoCraftingManager;
import dark.gsm.core.common.GSMCore;
import dark.gsm.core.common.item.ItemBasic;
import dark.gsm.fortress.api.IAmmo;
import dark.gsm.fortress.api.ProjectileTypes;

public class ItemAmmo extends ItemBasic implements IAmmo
{
    enum types
    {
        SHELL("bulletShell", ProjectileTypes.NEUTRIAL, true),
        BULLET("bullet", ProjectileTypes.CONVENTIONAL, true),
        BULLETRAIL("bulletRailgun", ProjectileTypes.RAILGUN, true),
        BULLETANTI("bulletAntimatter", ProjectileTypes.RAILGUN, true),
        BULLETINF("bulletInfinite", ProjectileTypes.CONVENTIONAL, false);

        public String iconName;
        public ProjectileTypes type;
        public boolean consume;

        private types(String iconName, ProjectileTypes type, boolean consume)
        {
            this.iconName = iconName;
            this.type = type;
            this.consume = consume;
        }
    }

    public static final Icon[] ICONS = new Icon[types.values().length];

    public ItemAmmo(int id, Configuration config)
    {
        super(id, "ammunition", config);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return "item." + GSMCore.PREFIX + types.values()[itemStack.getItemDamage()].iconName;
    }

    @Override
    public Icon getIconFromDamage(int i)
    {
        return ICONS[i];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        for (int i = 0; i < types.values().length; i++)
        {
            ICONS[i] = iconRegister.registerIcon(GSMCore.PREFIX + types.values()[i].iconName);
        }
    }

    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < types.values().length; i++)
        {
            par3List.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public ProjectileTypes getType(int meta)
    {
        if (meta < types.values().length)
        {
            return types.values()[meta].type;
        }
        return null;
    }

    @Override
    public Pair<DamageSource, Integer> getDamage(Entity entity, Object shooter, int meta)
    {
        if (meta == types.BULLET.ordinal() || meta == types.BULLETINF.ordinal())
        {
            return new Pair<DamageSource, Integer>(TileDamageSource.doBulletDamage(shooter), 8);
        }
        return null;
    }

    @Override
    public boolean applyDirectDamage(int meta)
    {
        if (meta == types.BULLET.ordinal() || meta == types.BULLETINF.ordinal())
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean fireAmmoLiving(Entity target, int meta)
    {
        return false;
    }

    @Override
    public boolean fireAmmoLoc(World world, Vector3 target, int meta)
    {
        return false;
    }

    @Override
    public ItemStack consumeItem(ItemStack itemStack)
    {
        if (itemStack != null && itemStack.getItemDamage() != types.BULLETINF.ordinal())
        {
            return AutoCraftingManager.decrStackSize(itemStack, 1);
        }
        return itemStack;
    }

    @Override
    public boolean canDrop(int meta)
    {
        if (meta == types.BULLETINF.ordinal())
        {
            return false;
        }
        return true;
    }

    @Override
    public ItemStack onDroppedIntoWorld(ItemStack stack)
    {
        return stack;
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, World world)
    {
        if (itemStack != null && itemStack.getItemDamage() == types.BULLETINF.ordinal())
        {
            return 40;
        }
        return super.getEntityLifespan(itemStack, world);
    }
}