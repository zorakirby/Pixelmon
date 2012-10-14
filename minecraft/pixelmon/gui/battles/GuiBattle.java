package pixelmon.gui.battles;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import pixelmon.comm.PixelmonMovesetDataPacket;
import pixelmon.entities.EntityCamera;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.gui.ContainerEmpty;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderManager;
import net.minecraft.src.Slot;
import net.minecraft.src.Tessellator;

public class GuiBattle extends GuiContainer {

	public enum BattleMode {
		MainMenu, ChoosePokemon, UseBag, ChooseAttack;
	}

	private int battleControllerIndex;
	public static BattleMode mode;
	private int guiWidth = 300;
	private int guiHeight = 60;

	boolean cameraEnabled = false;

	public GuiBattle(int battleControllerIndex) {
		super(new ContainerEmpty());
		this.battleControllerIndex = battleControllerIndex;
		mode = BattleMode.MainMenu;
		ClientBattleManager.addMessage("Battle Started");
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float mfloat, int mouseX, int mouseY) {
		if (cameraEnabled && ClientBattleManager.camera != null)
			if (Minecraft.getMinecraft().renderViewEntity != ClientBattleManager.camera)
				Minecraft.getMinecraft().renderViewEntity = ClientBattleManager.camera;

		int left = (width - xSize) / 2;
		int top = (height - ySize) / 2;
		RenderHelper.disableStandardItemLighting();
		if (ClientBattleManager.hasMoreMessages())
			drawMessageScreen();
		else if (mode == BattleMode.MainMenu)
			drawMainMenu(mouseX, mouseY);
		else if (mode == BattleMode.ChooseAttack)
			drawChooseAttack(mouseX, mouseY);

	}

	int flashCount = 0;

	private void drawMessageScreen() {
		int guiIndex = -1;
		guiIndex = mc.renderEngine.getTexture("/pixelmon/gui/battleGui3.png");

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawImageQuad(guiIndex, width / 2 - guiWidth / 2, height - guiHeight, guiWidth, guiHeight, 0, 0, 1, 146f / 480f);
		drawCenteredString(fontRenderer, ClientBattleManager.getNextMessage(), width / 2, height - 35, 0xFFFFFF);
		flashCount++;
		if (flashCount > 30) {
			drawImageQuad(guiIndex, width / 2 + 130, height - 15, 10, 6, 611f / 640f, 149f / 480f, 628f / 640f, 159f / 480f);
			if (flashCount > 60)
				flashCount = 0;
		}
	}

	@Override
	public void handleKeyboardInput() {
		return;
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (ClientBattleManager.hasMoreMessages()) {
			ClientBattleManager.removeMessage();
			return;
		}
		if (mode == BattleMode.MainMenu) {
			int x = width / 2 + 31;
			int y = height - guiHeight + 9;
			int w = 48, h = 16;
			if (mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h) {
				mode = BattleMode.ChooseAttack;
			}
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	private void drawMainMenu(int mouseX, int mouseY) {
		int guiIndex = -1;
		guiIndex = mc.renderEngine.getTexture("/pixelmon/gui/battleGui1.png");

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawImageQuad(guiIndex, width / 2 - guiWidth / 2, height - guiHeight, guiWidth, guiHeight, 0, 0, 1, 146f / 480f);
		drawButton(width / 2 + 31, height - guiHeight + 9, 48, 16, "FIGHT", mouseX, mouseY, guiIndex, 0);
		drawButton(width / 2 + 31, height - guiHeight + 35, 48, 16, "BAG", mouseX, mouseY, guiIndex, 1);
		drawButton(width / 2 + 90, height - guiHeight + 9, 48, 16, "POKEMON", mouseX, mouseY, guiIndex, 2);
		drawButton(width / 2 + 90, height - guiHeight + 35, 48, 16, "RUN", mouseX, mouseY, guiIndex, 3);
		drawString(fontRenderer, "What will " + ClientBattleManager.getUserPokemon().name + " do?", width / 2 - 130, height - 35, 0xFFFFFF);
	}

	private void drawChooseAttack(int mouseX, int mouseY) {
		int guiIndex = -1;
		guiIndex = mc.renderEngine.getTexture("/pixelmon/gui/battleGui2.png");

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawImageQuad(guiIndex, width / 2 - guiWidth / 2, height - guiHeight, guiWidth, guiHeight, 0, 0, 1, 146f / 480f);
		PixelmonMovesetDataPacket[] moveset = ClientBattleManager.getUserPokemon().moveset;
		int numMoves = ClientBattleManager.getUserPokemon().numMoves;
		if (numMoves > 0)
			drawButton(width / 2 - 141, height - guiHeight + 9, 87, 20, moveset[0].attackName, mouseX, mouseY, guiIndex, 0);
		if (numMoves > 1)
			drawButton(width / 2 - 50, height - guiHeight + 9, 87, 20, moveset[1].attackName, mouseX, mouseY, guiIndex, 1);
		if (numMoves > 2)
			drawButton(width / 2 - 141, height - guiHeight + 33, 87, 20, moveset[2].attackName, mouseX, mouseY, guiIndex, 2);
		if (numMoves > 3)
			drawButton(width / 2 - 50, height - guiHeight + 33, 87, 20, moveset[3].attackName, mouseX, mouseY, guiIndex, 3);
		drawCenteredString(fontRenderer, "PP: " + moveset[mouseOverButton].pp + "/" + moveset[mouseOverButton].ppBase, width / 2 + 99, height - guiHeight + 18,
				0xFFFFFF);
		drawString(fontRenderer, "Type: ", width / 2 + 99 - fontRenderer.getStringWidth("Type: " + moveset[mouseOverButton].type.toString()) / 2, height
				- guiHeight + 33, 0xFFFFFF);
		drawString(fontRenderer, moveset[mouseOverButton].type.toString(),
				width / 2 + 99 - fontRenderer.getStringWidth("Type: " + moveset[mouseOverButton].type.toString()) / 2 + fontRenderer.getStringWidth("Type: "), height - guiHeight + 33,
				moveset[mouseOverButton].type.getColor());

	}

	private int mouseOverButton = 0;

	private void drawButton(int x, int y, int buttonWidth, int buttonHeight, String string, int mouseX, int mouseY, int guiIndex, int ind) {
		if (mode == BattleMode.MainMenu) {
			if (mouseX > x && mouseX < x + buttonWidth && mouseY > y && mouseY < y + buttonHeight) {
				drawImageQuad(guiIndex, x, y, buttonWidth, buttonHeight, 387f / 640f, 158f / 480f, 489f / 640f, 196f / 480f);
			}
			drawCenteredString(fontRenderer, string, x + buttonWidth / 2, y + buttonHeight / 2 - 3, 0xFFFFFF);
		} else if (mode == BattleMode.ChooseAttack) {
			drawImageQuad(guiIndex, x, y, buttonWidth, buttonHeight, 206f / 640f, 152f / 480f, 393f / 640f, 202f / 480f);
			if (mouseX > x && mouseX < x + buttonWidth && mouseY > y && mouseY < y + buttonHeight) {
				drawImageQuad(guiIndex, x + 2, y + 2, buttonWidth - 5, buttonHeight - 4, 23f / 640f, 155f / 480f, 200f / 640f, 195f / 480f);
				mouseOverButton = ind;
			}
			drawCenteredString(fontRenderer, string, x + buttonWidth / 2, y + buttonHeight / 2 - 3, 0xFFFFFF);
		}
	}

	@Override
	public void drawBackground(int par1) {
	}

	@Override
	public void drawDefaultBackground() {
	}

	private void drawImageQuad(int textureHandle, int x, int y, float w, float h, float us, float vs, float ue, float ve) {
		// activate the specified texture
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureHandle);

		float var7 = 0.00390625F;
		float var8 = 0.00390625F;
		Tessellator var9 = Tessellator.instance;
		var9.startDrawingQuads();
		var9.addVertexWithUV((double) (x + 0), (double) (y + h), (double) this.zLevel, (double) ((float) us), (double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + h), (double) this.zLevel, (double) ((float) ue), (double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + 0), (double) this.zLevel, (double) ((float) ue), (double) ((float) vs));
		var9.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) this.zLevel, (double) ((float) us), (double) ((float) vs));
		var9.draw();
	}
}
