package dark.gsm.fortress.turret.sentries;

public class TileEntityLaserTurret extends TileEntityAutoTurret
{
    public TileEntityLaserTurret()
    {
        this.targetPlayers = true;
        this.targetHostile = true;

        this.baseTargetRange = 30;
        this.maxTargetRange = 90;

        this.idleRtSpeed = 3f;
        this.targetRtSpeed = 6f;

        this.maxHeat = 1000;

        this.baseFiringDelay = 20;
        this.minFiringDelay = 10;
    }

    @Override
    public float getFiringRequest()
    {
        return 1000;
    }

    @Override
    public float getRunningRequest()
    {
        return 10;
    }

    @Override
    public double getHeatPerShot()
    {
        return 100;
    }

    @Override
    public int getMaxHealth()
    {
        return 120;
    }

    @Override
    public void playFiringSound()
    {
        // TODO Auto-generated method stub

    }

}
