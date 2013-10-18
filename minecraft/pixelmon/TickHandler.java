package pixelmon;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPool;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonItems;
import pixelmon.items.ItemPixelmonBoots;
import pixelmon.sounds.Sounds;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TickHandler implements ITickHandler {
	int ticksSinceSentLogin = 0;
	boolean checkedForUsername = false;
	boolean musicCleared = false;
	boolean foundSounds = false;
	boolean createdWatcher = false;
	boolean screenOpen = false;
	boolean initialised = false;

	private void onPlayerTick(EntityPlayer player) {
		ItemStack boots = player.getCurrentItemOrArmor(1);
		if (player.getHeldItem() != null) {
			if (player.getHeldItem().getItem().itemID == PixelmonItems.oldRunningShoes.itemID) {
				if (boots == null) {
					ItemPixelmonBoots.itemHeld = true;
				}
			} else if (player.getHeldItem().getItem().itemID == PixelmonItems.newRunningShoes.itemID) {
				ItemPixelmonBoots.itemHeld = true;
			} else {
				ItemPixelmonBoots.itemHeld = false;
			}
		} else {
			ItemPixelmonBoots.itemHeld = false;
		}

		if (!(player instanceof EntityPlayerMP)) {
			if (Minecraft.getMinecraft().currentScreen != null && (!screenOpen || !initialised)) {
				PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.GuiOpen));
				screenOpen = true;
				initialised = true;
			} else if (screenOpen && Minecraft.getMinecraft().currentScreen == null || !initialised) {
				PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.GuiClose));
				screenOpen = false;
				initialised = true;
			}
		}

	}

	@Override
	public void tickStart(EnumSet<TickType> types, Object... tickData) {

		if (types.equals(EnumSet.of(TickType.PLAYER))) {
			onPlayerTick((EntityPlayer) tickData[0]);
		}

		for (TickType type : types) {
			//
			// if (Minecraft.getMinecraft().thePlayer !=null &&
			// !checkedForUsername && type == TickType.RENDER &&
			// !Minecraft.getMinecraft().thePlayer.username.equals("ASH")
			// &&
			// java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp")
			// > 0) {
			// try {
			// Field f =
			// Minecraft.getMinecraft().thePlayer.getClass().getDeclaredField("username");
			// f.setAccessible(true);
			// f.set(Minecraft.getMinecraft().thePlayer, "ASH");
			// } catch (Exception e) {
			// }
			// //Minecraft.getMinecraft().thePlayer.username = "ASH";
			// }
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
				if (true && !musicCleared) {
					Map l = ObfuscationReflectionHelper.getPrivateValue(SoundPool.class, Minecraft.getMinecraft().sndManager.soundPoolMusic, 1);
					if (l.size() != 0) {
						if (PixelmonConfig.removeVanillaMusic)
							l.clear();
						foundSounds = Sounds.installMusic();
						musicCleared = true;
					}
				}
				if (musicCleared && !foundSounds) {
					foundSounds = true;
					Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(
							"Couldn't find music at " + Minecraft.getMinecraft().mcDataDir + "/resources/music/pixelmon");
				}
			}
		}

	}

	@Override
	public void tickEnd(EnumSet<TickType> types, Object... tickData) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER, TickType.WORLD, TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return "Pixelmon Ticker";
	}

}
