package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import pixelmon.comm.EnumPackets;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPokeballs;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import pixelmon.storage.PlayerStorage;
import cpw.mods.fml.common.network.Player;

public class ChooseStarter extends PacketHandlerBase {

	public ChooseStarter() {
		packetsHandled.add(EnumPackets.ChooseStarter);
	}

	@Override
	public void handlePacket(int index, Player play, DataInputStream dataStream) throws IOException {
		try {
			EntityPlayer player = (EntityPlayer) play;
			PlayerStorage s = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player);
			int pokemonIndex = dataStream.readInt();
			EntityPixelmon p = (EntityPixelmon) PixelmonEntityList.createEntityByName(Entity3HasStats.getBaseStats(pokemonIndex).pixelmonName, player.worldObj);
			p.getLvl().setLevel(5);
			p.setEntityHealth(p.stats.HP);
			p.loadMoveset();
			p.caughtBall = EnumPokeballs.PokeBall;
			p.friendship.initFromCapture();
			s.addToParty(p);
		} catch (PlayerNotLoadedException e) {
		}
	}

}
