package dark.gsm.client.renders.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShoeBot extends ModelBase
{
	public ModelRenderer body;
	public ModelRenderer Head;

	public ModelShoeBot()
	{
		body = new ModelRenderer(this, 0, 0);
		body.addBox(-4.0F, -2.0F, -4.0F, 8, 4, 8, 0);

		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0);

	}

	@Override
	public void render(Entity par1Entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.setRotationAngles(f, f1, f2, f3, f4, f5, par1Entity);
		body.render(f5);
		Head.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
