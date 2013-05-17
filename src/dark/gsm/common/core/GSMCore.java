package dark.gsm.common.core;

import dark.library.DarkMain;

public class GSMCore
{
	public static boolean loaded = false;

	public static void registerMod(IMod mod)
	{
		if (!loaded)
		{
			DarkMain.forceLoadBCItems(mod, mod.getChannel());
			GSMCore.loaded = true;
		}
	}
}
