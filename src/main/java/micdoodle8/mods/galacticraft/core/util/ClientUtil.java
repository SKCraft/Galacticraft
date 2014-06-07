package micdoodle8.mods.galacticraft.core.util;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.dimension.SpaceRace;
import micdoodle8.mods.galacticraft.core.dimension.SpaceRaceManager;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.wrappers.FlagData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientUtil 
{
	public static FlagData updateFlagData(String playerName, boolean sendPacket)
	{
		SpaceRace race = SpaceRaceManager.getSpaceRaceFromPlayer(playerName); 
		
		if (race != null)
		{
			return race.getFlagData();
		}
		else if (!ClientProxyCore.flagRequestsSent.contains(playerName) && sendPacket)
		{
			GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_REQUEST_FLAG_DATA, new Object[] { playerName }));
			ClientProxyCore.flagRequestsSent.add(playerName);
		}
		
		return null;
	}
	
	public static Vector3 updateTeamColor(String playerName, boolean sendPacket)
	{
		SpaceRace race = SpaceRaceManager.getSpaceRaceFromPlayer(playerName); 
		
		if (race != null)
		{
			return race.getTeamColor();
		}
		else if (!ClientProxyCore.flagRequestsSent.contains(playerName) && sendPacket)
		{
			GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_REQUEST_FLAG_DATA, new Object[] { playerName }));
			ClientProxyCore.flagRequestsSent.add(playerName);
		}
		
		return null;
	}
}