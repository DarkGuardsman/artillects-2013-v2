package dark.gsm.fortress.terminal.command;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import dark.api.ITerminal;
import dark.api.access.AccessUser;
import dark.api.access.ITerminalCommand;
import dark.gsm.fortress.platform.TileEntityTurretPlatform;

/** Manipulates the access level of the turret platform.
 *
 * @author Darkguardsman, Calclavia */
public class CommandAccess implements ITerminalCommand
{

    @Override
    public String getCommandName()
    {
        return "access";
    }

    @Override
    public boolean called(EntityPlayer player, ITerminal terminal, String[] args)
    {
        if (args[0].equalsIgnoreCase("access") && args.length > 1 && args[1] != null && terminal instanceof TileEntityTurretPlatform)
        {
            TileEntityTurretPlatform turret = (TileEntityTurretPlatform) terminal;
            AccessUser userAccess = terminal.getUserAccess(player.username);

            if (args[1].equalsIgnoreCase("?"))
            {
                terminal.addToConsole("Group: " + userAccess.getGroup().name());
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
        Set<String> nodes = new HashSet();
        nodes.add("?");
        nodes.add("set");
        return nodes;
    }

    @Override
    public String getNode(String[] args)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
