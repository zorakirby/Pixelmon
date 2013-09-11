package pixelmon;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPool;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
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

	private void onPlayerTick(EntityPlayer player) {
		ItemStack boots = player.getCurrentItemOrArmor(1);
		if (boots == null) {
			player.capabilities.setPlayerWalkSpeed(.1F);
		} else {
			if (boots.getItem() == PixelmonItems.oldRunningShoes) {
				player.capabilities.setPlayerWalkSpeed(.17F);
			} else if (boots.getItem() == PixelmonItems.newRunningShoes) {
				player.capabilities.setPlayerWalkSpeed(.21F);
				if (ItemPixelmonBoots.bootLastX == 0 || ItemPixelmonBoots.bootLastZ == 0) {
					ItemPixelmonBoots.bootLastX = (int) player.getPlayerCoordinates().posX;
					ItemPixelmonBoots.bootLastZ = (int) player.getPlayerCoordinates().posZ;
				} else {
					int changeX = (int) (Math.abs(ItemPixelmonBoots.bootLastX - player.getPlayerCoordinates().posX));
					int changeZ = (int) (Math.abs(ItemPixelmonBoots.bootLastZ - player.getPlayerCoordinates().posZ));

					if (changeX > 2 || changeZ > 2) {
						boots.damageItem(100, player);
						ItemPixelmonBoots.bootLastX = (int) player.getPlayerCoordinates().posX;
						ItemPixelmonBoots.bootLastZ = (int) player.getPlayerCoordinates().posZ;
						if (boots.getItemDamage() == PixelmonItems.newRunningShoes.getMaxDamage()) {
							removeItem(player, boots);
							ItemStack oldShoes = new ItemStack(PixelmonItems.oldRunningShoes, 1, 0);
							player.inventory.addItemStackToInventory(oldShoes);
						}
					}
				}
			}
		}
	}

	public void removeItem(EntityPlayer ep, ItemStack removeitem) {
		IInventory inv = ep.inventory;
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i) != null) {
				ItemStack j = inv.getStackInSlot(i);
				if (j.getItem() != null && j.getItem() == removeitem.getItem()) {
					inv.setInventorySlotContents(i, null);
				}
			}
		}
	}

	@Override
	public void tickStart(EnumSet<TickType> types, Object... tickData) {

		if (types.equals(EnumSet.of(TickType.PLAYER))) {
			if (tickData[0] instanceof EntityClientPlayerMP)
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
