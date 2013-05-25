package dark.gsm.common.core;

import dark.library.DarkMain;

public class GSMCore
{
	public static boolean loaded = false;

	public static final String MAJOR_VERSION = "@MAJOR@";
	public static final String MINOR_VERSION = "@MINOR@";
	public static final String REVISION_VERSION = "@REVIS@";
	public static final String BUILD_VERSION = "@BUILD@";
	public static final String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION;
	
	public static void registerMod(IMod mod)
	{
		if (!loaded)
		{
			DarkMain.forceLoadBCItems(mod, mod.getChannel());
			GSMCore.loaded = true;
		}
	}
}
