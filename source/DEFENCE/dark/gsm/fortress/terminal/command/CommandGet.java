package dark.gsm.fortress.terminal.command;

import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import dark.api.ITerminal;
import dark.api.access.AccessUser;
import dark.api.access.ITerminalCommand;
import dark.gsm.fortress.platform.TileEntityTurretPlatform;

public class CommandGet implements ITerminalCommand
{
    @Override
    public String getCommandName()
    {
        return "get";
    }

    @Override
    public boolean called(EntityPlayer player, ITerminal TE, String[] args)
    {
        if (args[0].equalsIgnoreCase("get") && args.length > 1 && args[1] != null && TE instanceof TileEntityTurretPlatform)
        {
            TileEntityTurretPlatform turret = (TileEntityTurretPlatform) TE;
            if (args[1].equalsIgnoreCase("owner"))
            {
                if (turret.getOwnerGroup().getMembers().size() > 0)
                {
                    for (AccessUser access : turret.getOwnerGroup().getMembers())
                    {
                        TE.addToConsole(access.getName());
                    }
                }
                else
                {
                    TE.addToConsole("No owners");
                }
                return true;
            }
            else if (args[1].equalsIgnoreCase("position"))
            {
                TE.addToConsole("position: " + turret.xCoord + "x " + turret.yCoord + "y " + turret.zCoord + "z ");
                return true;
            }
            else if (args[1].equalsIgnoreCase("kills"))
            {
                // TODO track
                TE.addToConsole("Not yet useable");
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
