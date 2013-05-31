package dark.gsm.common.artillects;

import java.awt.Color;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.network.PacketManager;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.client.FMLClientHandler;
import dark.library.DarkMain;
import dark.library.effects.FXBeam;

public class PacketHandler extends PacketManager
{
	public enum PacketType
	{
		UNSPECIFIED, TILEENTITY, EFFECTS, USER_LIST;

		public static PacketType get(int id)
		{
			if (id >= 0 && id < PacketType.values().length)
			{
				return PacketType.values()[id];
			}
			return UNSPECIFIED;
		}
	}

	@Override
	public void handlePacketData(INetworkManager network, int packetID, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput data)
	{
		PacketType packetType = PacketType.get(packetID);
		if (packetType == PacketType.EFFECTS)
		{
			int effectName = data.readInt();
			if (effectName == 0)
			{
				if (player.worldObj != null && player.worldObj.isRemote)
				{
					Vector3 start = new Vector3(data.readDouble(), data.readDouble(), data.readDouble());
					Vector3 end = new Vector3(data.readDouble(), data.readDouble(), data.readDouble());

					int red = data.readInt();
					int blue = data.readInt();
					int green = data.readInt();
					Color color = new Color(red, green, blue);

					int time = data.readInt();

					FMLClientHandler.instance().getClient().effectRenderer.addEffect(new FXBeam(player.worldObj, start, end, color, DarkMain.TEXTURE_DIRECTORY + "", time));
				}
				else
				{
					System.out.println("LaserHelper>>>Failed to render laser");
				}
			}
		}
	}
}
