package pixelmon.keybindings;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import pixelmon.ServerStorageDisplay;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.gui.GuiPixelmonOverlay;

import net.minecraft.src.KeyBinding;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class NextPokemonKey extends KeyHandler {

	public NextPokemonKey() {
		super(new KeyBinding[]{new KeyBinding("Next Pixelmon", 27)}, new boolean[]{false});	
	}

	@Override
	public String getLabel() {
		return "Next Pixelmon";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, boolean isRepeat) {
		GuiPixelmonOverlay.selectNextPixelmon();
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.WORLD);
	}

}
