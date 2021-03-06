package dark.gsm.artillects.gen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;

public class NBTFileSaver
{

    public static boolean saveNBTFile(String filename, File direcotry, NBTTagCompound data, boolean override)
    {
        try
        {
            File tempFile = new File(direcotry, filename + "_tmp");
            File file = new File(direcotry, filename);

            CompressedStreamTools.writeCompressed(data, new FileOutputStream(tempFile));

            if (file.exists())
            {
                if (override)
                {
                    file.delete();
                }
                else
                {
                    File fileNew = new File(direcotry, "Back_" + filename);
                    int i = 1;
                    while (fileNew.exists())
                    {
                        i++;
                        fileNew = new File(direcotry, "Back_" + i + "_" + filename);
                    }
                    file.renameTo(fileNew);
                }
            }

            tempFile.renameTo(file);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean saveNBTFile(String filename, NBTTagCompound data)
    {
        return saveNBTFile(filename, getWorldSaveFolder(MinecraftServer.getServer().getFolderName()), data, true);
    }

    public static NBTTagCompound getSaveFile(File saveDirectory, String filename)
    {
        try
        {
            File file = new File(saveDirectory, filename);
            if (file.exists())
            {
                return CompressedStreamTools.readCompressed(new FileInputStream(file));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;
    }

    public static File getSaveFolder()
    {
        File saveDirectory = getWorldSaveFolder(MinecraftServer.getServer().getFolderName());
        File file = new File(saveDirectory, "dark");

        if (!saveDirectory.exists())
        {
            saveDirectory.mkdir();
        }

        if (!file.exists())
        {
            file.mkdir();
        }

        return file;
    }

    public static File getWorldSaveFolder(String worldName)
    {
        File parent = getBaseFolder();

        if (FMLCommonHandler.instance().getSide().isClient())
        {
            parent = new File(getBaseFolder(), "saves" + File.separator);
        }

        return new File(parent, worldName + File.separator);
    }

    public static File getFolder(String file, boolean make)
    {
        File fil = new File(getBaseFolder(), file + File.separator);
        if (make && !fil.exists())
        {
            fil.mkdir();
        }
        return fil;
    }

    public static File getBaseFolder()
    {
        if (FMLCommonHandler.instance().getSide().isClient())
        {
            FMLClientHandler.instance().getClient();
            return Minecraft.getMinecraft().mcDataDir;
        }
        else
        {
            return new File(".");
        }
    }

}
