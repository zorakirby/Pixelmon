package pixelmon.gui.pokechecker;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiTextField;
import net.minecraft.src.StringTranslate;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.gui.ContainerEmpty;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiRenamePokemon extends GuiContainer {

	private GuiScreen parentGuiScreen;
	private GuiTextFieldTransparent theGuiTextField;
	private PixelmonDataPacket targetPacket;

	public GuiRenamePokemon(PixelmonDataPacket targetPacket, GuiScreenPokeChecker parent) {
		super(new ContainerEmpty());
		this.targetPacket = targetPacket;
		parentGuiScreen = parent;
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
		StringTranslate stringtranslate = StringTranslate.getInstance();
		Keyboard.enableRepeatEvents(true);
		controlList.clear();
		controlList.add(new GuiRenameButtons(0, width / 2 - 98, height / 4 + 80, stringtranslate.translateKey("selectWorld.renameButton")));
		controlList.add(new GuiRenameButtons(1, width / 2 + 48, height / 4 + 80, stringtranslate.translateKey("gui.cancel")));
		controlList.add(new GuiRenameButtons(2, width / 2 - 25, height / 4 + 80, stringtranslate.translateKey("Reset")));
		theGuiTextField = new GuiTextFieldTransparent(fontRenderer, width / 2 - 68, height / 4 + 37, 140, 30);
		theGuiTextField.setFocused(true);
		theGuiTextField.setText(targetPacket.nickname);
	}

	public void updateScreen() {
		theGuiTextField.updateCursorCounter();
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton par1GuiButton) {
		if (!par1GuiButton.enabled) {
			return;
		}

		switch (par1GuiButton.id) {
		case 2:
			PacketDispatcher.sendPacketToServer(PacketCreator.createStringPacket(EnumPackets.RenamePokemon, targetPacket.pokemonID, ""));
			targetPacket.nickname = "";
			mc.displayGuiScreen(parentGuiScreen);
			break;
		case 1:
			mc.displayGuiScreen(parentGuiScreen);
		case 0:
				PacketDispatcher.sendPacketToServer(PacketCreator.createStringPacket(EnumPackets.RenamePokemon, targetPacket.pokemonID, theGuiTextField.getText()));
				targetPacket.nickname = theGuiTextField.getText();
			parentGuiScreen.initGui();
			mc.displayGuiScreen(parentGuiScreen);
		}
	}

	protected void keyTyped(char par1, int par2) {
		theGuiTextField.textboxKeyTyped(par1, par2);
		((GuiButton) controlList.get(0)).enabled = theGuiTextField.getText().trim().length() > 0;

		if (par1 == '\r') {
			actionPerformed((GuiButton) controlList.get(0));
		}
	}

	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		theGuiTextField.mouseClicked(par1, par2, par3);
	}

	public void drawGuiContainerBackgroundLayer(float par3, int par1, int par2) {
		GL11.glNormal3f(0.0F, -1.0F, 0.0F);
		int bg = mc.renderEngine.getTexture("/pixelmon/gui/rename.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(bg);
		drawTexturedModalRect((width - xSize) / 2 - 40, height / 4, 0, 0, 256, 114);
		StringTranslate stringtranslate = StringTranslate.getInstance();
		drawCenteredString(fontRenderer, stringtranslate.translateKey("Rename Pokemon"), width / 2, (height / 4 - 60) + 80, 0xffffff);
		theGuiTextField.drawTextBox();
	}
}