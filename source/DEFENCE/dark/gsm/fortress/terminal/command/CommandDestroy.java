package dark.gsm.fortress.terminal.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import dark.api.ITerminal;
import dark.api.access.AccessLevel;
import dark.api.access.ISpecialAccess;
import dark.api.access.ITerminalCommand;
import dark.gsm.fortress.platform.TileEntityTurretPlatform;

public class CommandDestroy implements ITerminalCommand
{
    @Override
    public String getCommandName()
    {
        return "destroy";
    }

    @Override
    public boolean called(EntityPlayer player, ITerminal terminal, String[] args)
    {
        if (terminal instanceof TileEntityTurretPlatform)
        {
            TileEntityTurretPlatform turret = (TileEntityTurretPlatform) terminal;

            if (args.length > 1)
            {
                turret.destroyTurret();
                terminal.addToConsole("Destroyed Turret");
                return true;
            }
            else
            {
                turret.destroy(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canSupport(ITerminal mm)
    {
        return mm instanceof TileEntityTurretPlatform;
    }

    @Override
    public Set<String> getPermissionNodes()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getNode(String[] args)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
