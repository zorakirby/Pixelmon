package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.Player;

import pixelmon.StarterList;
import pixelmon.comm.EnumPackets;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.enums.EnumPokeballs;
import pixelmon.storage.PixelmonStorage;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.NetworkManager;

public class ChooseStarter extends PacketHandlerBase {

	public ChooseStarter() {
		packetsHandled.add(EnumPackets.ChooseStarter);
	}

	@Override
	public void handlePacket(int index, Player play, DataInputStream dataStream) throws IOException {
		EntityPlayer player = (EntityPlayer)play;
		int pokemonIndex = dataStream.readInt();
		IHaveHelper p = (IHaveHelper) PixelmonEntityList.createEntityByName(StarterList.getStarterStringList()[pokemonIndex], player.worldObj);
		p.getHelper().getLvl().setLevel(5);
		p.getHelper().loadMoveset();
		p.getHelper().caughtBall = EnumPokeballs.MasterBall;
		PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(p.getHelper());
		
	}

}
