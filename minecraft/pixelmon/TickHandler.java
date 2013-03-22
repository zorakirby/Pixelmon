package pixelmon;

import java.util.ArrayList;
import java.util.EnumSet;

import pixelmon.client.PixelmonServerStore;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.sounds.Sounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPool;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TickHandler implements ITickHandler {
	int ticksSinceSentLogin = 0;
	boolean checkedForUsername = false;
	boolean musicCleared = false;

	@Override
	public void tickStart(EnumSet<TickType> types, Object... tickData) {
		for (TickType type : types) {
			if (!checkedForUsername && type == TickType.RENDER && !Minecraft.getMinecraft().session.username.equals("ASH")
					&& java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0) {
				Minecraft.getMinecraft().session.username = "ASH";
			}
			checkedForUsername = true;
			if (type == TickType.RENDER) {
				if (ServerStorageDisplay.count() == 0) {
					ticksSinceSentLogin++;
					if (ticksSinceSentLogin >= 50) {
						ticksSinceSentLogin = 0;
						Packet250CustomPayload packet = PacketCreator.createPacket(EnumPackets.RequestUpdatedPokemonList, 0);
						PacketDispatcher.sendPacketToServer(packet);
					}
				}
			}
			if (!musicCleared) {
				ArrayList l = ObfuscationReflectionHelper.getPrivateValue(SoundPool.class, Minecraft.getMinecraft().sndManager.soundPoolMusic, 2);
				if (l.size() != 0) {
					l.clear();
					Sounds.installMusic();
					musicCleared = true;
				}
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> types, Object... tickData) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER, TickType.WORLD);
	}

	@Override
	public String getLabel() {
		return "Pixelmon Ticker";
	}

}
