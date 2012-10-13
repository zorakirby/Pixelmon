package pixelmon.gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import pixelmon.ClientBattleManager;
import pixelmon.entities.EntityCamera;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderManager;
import net.minecraft.src.Tessellator;

public class GuiBattle extends GuiContainer {

	public enum BattleMode {
		ChoosePokemon, UseBag, MainScreen, ChooseAttack;
	}

	private int battleControllerIndex;
	public static BattleMode mode;
	private int guiWidth = 300;
	private int guiHeight = 60;

	boolean cameraEnabled = false;

	public GuiBattle(int battleControllerIndex) {
		super(new ContainerEmpty());
		this.battleControllerIndex = battleControllerIndex;
		mode = BattleMode.MainScreen;
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float mfloat, int mouseX,
			int mouseY) {
		if (cameraEnabled && ClientBattleManager.camera != null)
			if (Minecraft.getMinecraft().renderViewEntity != ClientBattleManager.camera)
				Minecraft.getMinecraft().renderViewEntity = ClientBattleManager.camera;

		int left = (width - xSize) / 2;
		int top = (height - ySize) / 2;
		RenderHelper.disableStandardItemLighting();
		int guiIndex = -1;
		if (mode == BattleMode.MainScreen)
			guiIndex = mc.renderEngine
					.getTexture("/pixelmon/gui/battleGui1.png");

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawImageQuad(guiIndex, width / 2 - guiWidth / 2, height - guiHeight,
				guiWidth, guiHeight, 0, 0, 1, 146f / 480f);
		if (mode == BattleMode.MainScreen) {
			drawMessageBox();
			drawButton(width / 2 +31, height - guiHeight + 9,
					48, 16, "FIGHT", mouseX, mouseY, guiIndex);
			drawButton(width / 2 +31, height - guiHeight + 35,
					48, 16, "BAG", mouseX, mouseY, guiIndex);
			drawButton(width / 2 +90, height - guiHeight + 9,
					48, 16, "POKEMON", mouseX, mouseY, guiIndex);
			drawButton(width / 2 + 90, height - guiHeight + 35,
					48, 16, "RUN", mouseX, mouseY, guiIndex);
		}
	}

	private void drawButton(int x, int y, int buttonWidth, int buttonHeight,
			String string, int mouseX, int mouseY, int guiIndex) {
		if (mouseX > x && mouseX < x + buttonWidth && mouseY > y
				&& mouseY < y + buttonHeight) {
			drawImageQuad(guiIndex, x, y, buttonWidth, buttonHeight, 387f/640f, 158f/480f, 489f/640f, 196f/480f);
		}
		drawCenteredString(fontRenderer, string, x + buttonWidth/2, y + buttonHeight/2-3, 0xFFFFFF);
	}

	private void drawMessageBox() {

	}

	@Override
	public void drawBackground(int par1) {
	}

	@Override
	public void drawDefaultBackground() {
	}

	private void drawImageQuad(int textureHandle, int x, int y, float w,
			float h, float us, float vs, float ue, float ve) {
		// activate the specified texture
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureHandle);

		float var7 = 0.00390625F;
		float var8 = 0.00390625F;
		Tessellator var9 = Tessellator.instance;
		var9.startDrawingQuads();
		var9.addVertexWithUV((double) (x + 0), (double) (y + h),
				(double) this.zLevel, (double) ((float) us),
				(double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + h),
				(double) this.zLevel, (double) ((float) ue),
				(double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + 0),
				(double) this.zLevel, (double) ((float) ue),
				(double) ((float) vs));
		var9.addVertexWithUV((double) (x + 0), (double) (y + 0),
				(double) this.zLevel, (double) ((float) us),
				(double) ((float) vs));
		var9.draw();
	}
}
