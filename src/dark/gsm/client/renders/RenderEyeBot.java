package dark.gsm.client.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dark.gsm.common.artillects.bots.EntityEyeBot;

@SideOnly(Side.CLIENT)
public class RenderEyeBot extends RenderLiving
{
	public RenderEyeBot(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2);
	}

	public void renderEyeBot(EntityEyeBot eyeBot, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRenderLiving(eyeBot, par2, par4, par6, par8, par9);
	}

	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderEyeBot((EntityEyeBot) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderEyeBot((EntityEyeBot) par1Entity, par2, par4, par6, par8, par9);
	}
}
