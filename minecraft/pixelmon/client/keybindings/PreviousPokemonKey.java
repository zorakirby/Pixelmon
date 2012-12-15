package pixelmon.client.keybindings;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import pixelmon.client.gui.GuiPixelmonOverlay;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class PreviousPokemonKey extends KeyHandler {

	public PreviousPokemonKey() {
		super(new KeyBinding[]{new KeyBinding("Select Previous Pixelmon", 26)}, new boolean[]{false});	
	}

	@Override
	public String getLabel() {
		return "Previous Pixelmon";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb,
			boolean tickEnd, boolean isRepeat) {
		if (tickEnd) return;
		GuiPixelmonOverlay.selectPreviousPixelmon();
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.WORLD);
	}

}
