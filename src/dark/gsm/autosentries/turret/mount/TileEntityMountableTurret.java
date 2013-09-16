package dark.gsm.autosentries.turret.mount;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import universalelectricity.core.vector.Vector3;
import dark.core.network.PacketHandler;
import dark.core.prefab.IMultiBlock;
import dark.gsm.autosentries.Sentries;
import dark.gsm.autosentries.turret.TileEntityTurretBase;

public abstract class TileEntityMountableTurret extends TileEntityTurretBase implements IMultiBlock
{

    /** Current player on the sentry */
    protected EntityPlayer mountedPlayer = null;
    /** Fake entity this sentry uses for mounting the player in position */
    private EntityFakeMountable entityFake = null;

    @Override
    public void onUpdate()
    {

    }

    @Override
    public void updateRotation()
    {
        if (this.mountedPlayer != null)
        {
            if (this.mountedPlayer.rotationPitch > 30)
            {
                this.mountedPlayer.rotationPitch = 30;
            }
            if (this.mountedPlayer.rotationPitch < -45)
            {
                this.mountedPlayer.rotationPitch = -45;
            }

            this.currentRotationPitch = this.wantedRotationPitch = this.mountedPlayer.rotationPitch * rotationTranslation;
            this.currentRotationYaw = this.wantedRotationYaw = this.mountedPlayer.rotationYaw * rotationTranslation;
            if (this.worldObj.isRemote)
            {
                PacketHandler.instance().sendPacketToClients(PacketHandler.instance().getPacket(Sentries.CHANNEL, this, turretPacket.ROTATION.ordinal(), this.wantedRotationPitch, this.wantedRotationYaw, this.speedUpRotation), this.worldObj, new Vector3(this), 50);
            }
        }
        else if (this.entityFake != null)
        {
            this.entityFake.setDead();
            this.entityFake = null;
        }
    }

    @Override
    public boolean onActivated(EntityPlayer entityPlayer)
    {
        if (this.mountedPlayer != null && entityPlayer == this.mountedPlayer)
        {
            this.mountedPlayer = null;
            entityPlayer.mountEntity(this.entityFake);

            if (this.entityFake != null)
            {
                this.entityFake.setDead();
                this.entityFake = null;
            }
        }
        else
        {
            this.mount(entityPlayer);
        }

        return true;
    }

    @Override
    public void mount(EntityPlayer entityPlayer)
    {
        // Creates a fake entity to be mounted on
        if (this.mountedPlayer == null)
        {
            if (!this.worldObj.isRemote)
            {
                this.entityFake = new EntityFakeMountable(this.worldObj, new Vector3(this.xCoord + 0.5, this.yCoord + 1.2, this.zCoord + 0.5), this, false);
                this.worldObj.spawnEntityInWorld(entityFake);
                entityPlayer.mountEntity(this.entityFake);
            }

            this.mountedPlayer = entityPlayer;
            entityPlayer.rotationYaw = 0;
            entityPlayer.rotationPitch = 0;
        }
    }

    @Override
    public boolean canApplyPotion(PotionEffect par1PotionEffect)
    {
        return false;
    }

}
