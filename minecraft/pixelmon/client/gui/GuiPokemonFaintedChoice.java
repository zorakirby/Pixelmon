package pixelmon.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;

import org.lwjgl.input.Keyboard;

import pixelmon.battles.controller.BattleController;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.gui.ContainerEmpty;

public class GuiPokemonFaintedChoice extends GuiContainer {

	private EntityPixelmon mypixelmon;
	private BattleController bc;

	public GuiPokemonFaintedChoice(BattleController bc, EntityPixelmon entity) {
		super(new ContainerEmpty());
		mypixelmon = entity;
		this.bc = bc;
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		buttonList.add(new GuiButton(10, width / 2 - 100, height / 2 - 20,
				"Switch To Another Pokemon"));
		if (!bc.isTrainerVsTrainer())
			buttonList.add(new GuiButton(10, width / 2 - 100, height / 2 + 20,
					"Run"));
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}
	
	public void keyTyped(char i, int i1) {
	}

	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.id == 0) {

		} else {

		}
		mc.thePlayer.closeScreen();
		mc.setIngameFocus();
	}

	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
	}

	public void drawGuiContainerBackgroundLayer(float f, int i, int i1) {

		drawDefaultBackground();
		drawCenteredString(fontRenderer, mypixelmon.getNickname()
				+ "can no longer fight", width / 2, 10, 0xFFFFFF);
	}
}
