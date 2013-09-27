package dark.gsm.common;

import dark.core.faction.FactionInstance;

public interface IFactionObject
{
    /** Gets the faction this is linked too. Will return Neutral rather than null */
    public FactionInstance getFaction();

    public boolean setFaction(FactionInstance faction, boolean override);
}
