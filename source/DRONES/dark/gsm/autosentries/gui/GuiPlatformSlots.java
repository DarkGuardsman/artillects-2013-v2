package dark.gsm.autosentries.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import universalelectricity.core.electricity.ElectricityDisplay;
import universalelectricity.core.electricity.ElectricityDisplay.ElectricUnit;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dark.gsm.autosentries.platform.TileEntityTurretPlatform;
import dark.gsm.autosentries.turret.TileEntityTurretBase;
import dark.gsm.core.common.GSMCore;

@SideOnly(Side.CLIENT)
public class GuiPlatformSlots extends GuiPlatformContainer
{
    ResourceLocation gui_pic = new ResourceLocation(GSMCore.DOMAIN, GSMCore.GUI_DIRECTORY + "gui_platform_slot.png");

    public GuiPlatformSlots(InventoryPlayer inventoryPlayer, TileEntityTurretPlatform tileEntity)
    {
        super(inventoryPlayer, tileEntity);
    }

    /** Draw the foreground layer for the GuiContainer (everything in front of the items) */
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        this.fontRenderer.drawString("Ammunition", 8, 30, 4210752);

        // Shows the status of the EMP Tower
        String color = "\u00a74";

        if (!this.tileEntity.isDisabled() && this.tileEntity.getEnergyStored() >= this.tileEntity.getRequest(ForgeDirection.UNKNOWN))
        {
            color = "\u00a7a";
        }
        TileEntityTurretBase turret = this.tileEntity.getTurret(false);
        if (turret != null && turret.getFiringRequest() > 0)
        {
            this.fontRenderer.drawString("Energy Per Shot", 85, 43, 4210752);
            this.fontRenderer.drawString(color + ElectricityDisplay.getDisplayShort(Math.min(this.tileEntity.getEnergyStored(), turret.getFiringRequest()), ElectricUnit.JOULES) + "/" + ElectricityDisplay.getDisplayShort(this.tileEntity.getTurret(false).getFiringRequest(), ElectricUnit.JOULES), 87, 53, 4210752);
        }
        this.fontRenderer.drawString("Upgrades", 87, 66, 4210752);
        super.drawGuiContainerForegroundLayer(x, y);
    }

    /** Draw the background layer for the GuiContainer (everything behind the items) */
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int x, int y)
    {
        super.drawGuiContainerBackgroundLayer(par1, x, y);
        this.mc.renderEngine.bindTexture(gui_pic);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int containerWidth = (this.width - this.xSize) / 2;
        int containerHeight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(containerWidth, containerHeight, 0, 0, this.xSize, this.ySize);
    }
}
