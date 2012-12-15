package pixelmon.client.keybindings;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.enums.EnumMovement;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class RidingBindings extends KeyHandler {

	public RidingBindings() {
		super(new KeyBinding[] { new KeyBinding("Accelerate", Keyboard.KEY_W), new KeyBinding("Decelerate", Keyboard.KEY_S), new KeyBinding("Left", Keyboard.KEY_A),
				new KeyBinding("Right", Keyboard.KEY_D), new KeyBinding("Jump/Fly", Keyboard.KEY_SPACE), }, new boolean[] { true, true, true, true, true });
	}

	@Override
	public String getLabel() {
		return "Riding Keys";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		if (Minecraft.getMinecraft().thePlayer.ridingEntity == null || tickEnd) {
			return;
		}
		if (kb.keyCode == Keyboard.KEY_W)
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.Movement, EnumMovement.Accelerate.index));
		if (kb.keyCode == Keyboard.KEY_S)
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.Movement, EnumMovement.Decelerate.index));
		if (kb.keyCode == Keyboard.KEY_A)
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.Movement, EnumMovement.Left.index));
		if (kb.keyCode == Keyboard.KEY_D)
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.Movement, EnumMovement.Right.index));
		if (kb.keyCode == Keyboard.KEY_SPACE)
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.Movement, EnumMovement.Jump.index));
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.WORLD);
	}

}
