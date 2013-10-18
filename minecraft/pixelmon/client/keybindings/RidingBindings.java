package pixelmon.client.keybindings;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import org.lwjgl.input.Keyboard;

import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.enums.EnumMovement;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class RidingBindings extends KeyHandler {

	public RidingBindings() {
		super(new KeyBinding[] { new KeyBinding("Descend", Keyboard.KEY_C), }, new boolean[] { true });
	}

	@Override
	public String getLabel() {
		return "Riding Keys";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		if (Minecraft.getMinecraft().currentScreen != null && Minecraft.getMinecraft().thePlayer != null
				&& Minecraft.getMinecraft().thePlayer.ridingEntity == null || tickEnd) {
			return;
		}
		if (kb.keyCode == Keyboard.KEY_C)
			MovementHandler.jump--;
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.WORLD);
	}

}
