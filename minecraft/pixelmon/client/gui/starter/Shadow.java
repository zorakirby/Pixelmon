package pixelmon.client.gui.starter;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import pixelmon.client.gui.GuiHelper;
import pixelmon.client.gui.GuiResources;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class Shadow {
	private static ResourceLocation shadow = new ResourceLocation("pixelmon:gui/starter/ShadowLarge.png");

	enum MoveDirection {
		Left, Right
	}

	EnumShadow shadowType;
	MoveDirection moveDirection;
	GuiChooseStarter parent;

	// position as percentage of distance travelled
	float position;
	float yPos;
	float moveSpeed;

	public Shadow(EnumShadow shadowType, GuiChooseStarter parent) {
		this.shadowType = shadowType;
		this.parent = parent;
		Random r = new Random();
		moveDirection = r.nextFloat() < 0.5f ? MoveDirection.Left : MoveDirection.Right;

		if (moveDirection == MoveDirection.Left)
			position = 1;
		else
			position = 0;
		moveSpeed = r.nextFloat() * shadowType.moveSpeedModifier * 0.01f + 0.001f;
		yPos = r.nextFloat();
	}

	public Shadow(EnumShadow shadowType, GuiChooseStarter parent, float startPos) {
		this(shadowType, parent);
		position = startPos;
	}

	public void update() {
		if (moveDirection == moveDirection.Left) {
			position = position - moveSpeed;
			if (position <= 0)
				parent.removeShadow(this);
		} else {
			position = position + moveSpeed;
			if (position >= 1)
				parent.removeShadow(this);
		}
	}

	public void draw(Minecraft mc, int screenWidth, int screenHeight) {
		mc.renderEngine.func_110577_a(shadow);
		float totWidth = screenWidth + shadowType.width * 2;
		float x = totWidth * position - shadowType.width;
		float totHeight = screenHeight - 50;
		float y = yPos * totHeight - shadowType.height + 50;
		GL11.glEnable(GL11.GL_BLEND);
		GuiHelper.drawImageQuad((int) x, (int) y, shadowType.width, shadowType.height, 0, 0, 1, 1, 0);
	}
}
