package pixelmon;

import java.util.ArrayList;
import java.util.EnumSet;

import pixelmon.client.ServerStorageDisplay;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonItems;
import pixelmon.sounds.Sounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPool;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
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

			if(player.getCurrentItemOrArmor(1) != null){
				ItemStack boots = player.getCurrentItemOrArmor(1);
				if(boots.getItem() == PixelmonItems.newRunning){
					player.addPotionEffect((new PotionEffect(Potion.moveSpeed.getId(), 10, 1)));
					if((player.isSprinting() || player.isJumping)&& boots.getItem() == PixelmonItems.newRunning){
						ItemStack itemstack = new ItemStack(PixelmonItems.newRunning);
						boots.getItem().getItemDamageFromStack(boots);
						boots.damageItem(boots, player);
					}
				}
				if(player.getCurrentItemOrArmor(1) != null){
					ItemStack boots1 = player.getCurrentItemOrArmor(1);
					if(boots1.getItem() == pixelmon.config.PixelmonItems.oldRunning){
						player.addPotionEffect((new PotionEffect(Potion.moveSpeed.getId(), 10, 0)));
					}
				}
			}
	
	}


	@Override
	public void tickStart(EnumSet<TickType> types, Object... tickData) {
		
		if (types.equals(EnumSet.of(TickType.PLAYER)))
		  {
		    onPlayerTick((EntityPlayer)tickData[0]);
		  }
		
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
				if (!musicCleared) {
					ArrayList l = ObfuscationReflectionHelper.getPrivateValue(SoundPool.class, Minecraft.getMinecraft().sndManager.soundPoolMusic, 2);
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
							"Couldn't find music at " + Minecraft.getMinecraftDir() + "/resources/music/pixelmon");
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
