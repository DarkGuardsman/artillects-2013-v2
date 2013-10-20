package dark.gsm.fortress.terminal.command;

import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import dark.api.ITerminal;
import dark.api.access.ITerminalCommand;
import dark.gsm.fortress.platform.TileEntityTurretPlatform;
import dark.gsm.fortress.turret.sentries.TileEntityAutoTurret;

public class CommandTarget implements ITerminalCommand
{
    @Override
    public String getCommandName()
    {
        return "target";
    }

    @Override
    public boolean called(EntityPlayer player, ITerminal terminal, String[] args)
    {
        if (terminal instanceof TileEntityTurretPlatform)
        {
            TileEntityTurretPlatform turret = (TileEntityTurretPlatform) terminal;

            if (turret.getTurret(false) instanceof TileEntityAutoTurret)
            {
                TileEntityAutoTurret sentry = ((TileEntityAutoTurret) turret.getTurret(false));

                if (args.length > 1)
                {
                    String obj = args[1];
                    String bool = "";
                    boolean change = false;

                    if (args.length > 2)
                    {
                        bool = args[2];
                        change = Boolean.getBoolean(bool);
                    }

                    if (obj.equalsIgnoreCase("player"))
                    {
                        if (!bool.isEmpty())
                        {
                            sentry.targetPlayers = change;
                        }
                        else
                        {
                            sentry.targetPlayers = !sentry.targetPlayers;
                        }

                        return true;
                    }
                    else if (obj.equalsIgnoreCase("hostile"))
                    {
                        if (!bool.isEmpty())
                        {
                            sentry.targetHostile = change;
                        }
                        else
                        {
                            sentry.targetHostile = !sentry.targetHostile;
                        }

                        return true;
                    }
                    else if (obj.equalsIgnoreCase("friendly"))
                    {
                        if (!bool.isEmpty())
                        {
                            sentry.targetFriendly = change;
                        }
                        else
                        {
                            sentry.targetFriendly = !sentry.targetFriendly;
                        }

                        return true;
                    }
                    else if (obj.equalsIgnoreCase("air"))
                    {
                        if (!bool.isEmpty())
                        {
                            sentry.targetAir = change;
                        }
                        else
                        {
                            sentry.targetAir = !sentry.targetAir;
                        }

                        return true;
                    }
                }

                terminal.addToConsole("[player|hostile|friendly|air] [true|false]");
                return false;
            }
        }

        return false;
    }

    @Override
    public boolean canSupport(ITerminal mm)
    {
        if (mm instanceof TileEntityTurretPlatform)
        {
            return ((TileEntityTurretPlatform) mm).getTurret(false) instanceof TileEntityAutoTurret;
        }
        return false;
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
