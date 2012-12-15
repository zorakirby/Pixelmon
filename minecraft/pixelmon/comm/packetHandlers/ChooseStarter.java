package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import pixelmon.StarterList;
import pixelmon.comm.EnumPackets;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPokeballs;
import pixelmon.storage.PixelmonStorage;
import cpw.mods.fml.common.network.Player;

public class ChooseStarter extends PacketHandlerBase {

	public ChooseStarter() {
		packetsHandled.add(EnumPackets.ChooseStarter);
	}

	@Override
	public void handlePacket(int index, Player play, DataInputStream dataStream) throws IOException {
		EntityPlayer player = (EntityPlayer)play;
		int pokemonIndex = dataStream.readInt();
		EntityPixelmon p = (EntityPixelmon) PixelmonEntityList.createEntityByName(StarterList.getStarterStringList()[pokemonIndex], player.worldObj);
		p.getLvl().setLevel(5);
		p.setEntityHealth(p.stats.HP);
		p.loadMoveset();
		p.caughtBall = EnumPokeballs.MasterBall;
		p.friendship.initFromCapture();
		PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).addToParty(p);
	}

}
