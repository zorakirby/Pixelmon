package pixelmon.gui;

import org.lwjgl.opengl.GL11;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.Container;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.RenderHelper;

public class GuiAttackingNew extends GuiContainer {

	public enum BattleMode {
		ChoosePokemon, UseBag, MainScreen, ChooseAttack;
	}

	private int battleControllerIndex;
	public static BattleMode mode;

	public GuiAttackingNew(int battleControllerIndex) {
		super(new ContainerEmpty());
		this.battleControllerIndex = battleControllerIndex;
		mode = BattleMode.MainScreen;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float mfloat, int mouseX, int mouseY) {
		int left = (width - xSize) / 2;
		int top = (height - ySize) / 2;
		RenderHelper.disableStandardItemLighting();
		int guiIndex = -1;
		if (mode == BattleMode.MainScreen)
			guiIndex = mc.renderEngine.getTexture("/pixelmon/gui/battleGui1.png");

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(guiIndex);
		drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
	}

	@Override
	public void drawBackground(int par1) {
	}

	@Override
	public void drawDefaultBackground() {
	}
}
