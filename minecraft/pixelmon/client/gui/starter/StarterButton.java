package pixelmon.client.gui.starter;

import org.lwjgl.opengl.GL11;

import pixelmon.client.PixelmonServerStore;
import pixelmon.client.gui.GuiResources;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class StarterButton extends GuiButton {

	protected static final ResourceLocation buttonTexture = new ResourceLocation("pixelmon:gui/starter/starterHolder.png");
	protected static final ResourceLocation mouseOverTexture = new ResourceLocation("pixelmon:gui/starter/moStarter.png");
	protected static final ResourceLocation questionMark = new ResourceLocation("pixelmon:gui/starter/questionmark.png");

	int starterIndex;

	public StarterButton(int par1, int par2, int par3, int starterIndex) {
		super(par1, par2, par3, 80, 33, "");
		this.starterIndex = starterIndex;
	}

	/**
	 * Draws this button to the screen.
	 */
	@Override
	public void drawButton(Minecraft mc, int par2, int par3) {
		if (this.drawButton) {
			FontRenderer fontrenderer = mc.fontRenderer;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_82253_i = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
			int k = this.getHoverState(this.field_82253_i);
			if (field_82253_i && starterIndex!=-1)
				mc.func_110434_K().func_110577_a(mouseOverTexture);
			else
				mc.func_110434_K().func_110577_a(buttonTexture);
			drawImageQuad(xPosition, yPosition, width, height, 0, 0, 1, 1);
			// this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0,
			// this.width, this.height);
			this.mouseDragged(mc, par2, par3);
			int l = 0;

			if (starterIndex == -1) {
				l = 14737632;
				mc.renderEngine.func_110577_a(questionMark);
				drawImageQuad(this.xPosition + width / 2 - 12, this.yPosition + 5, 24f, 24f, 0f, 0f, 1f, 1f);
			} else {
				if (!this.enabled) {
					l = -6250336;
				} else if (this.field_82253_i) {
					l = 0xFFFFFF;
				}
				int npn = PixelmonServerStore.starterListPacket.starterListIndex[starterIndex];
				String numString = "";
				if (npn < 10)
					numString = "00" + npn;
				else if (npn < 100)
					numString = "0" + npn;
				else
					numString = "" + npn;
				mc.renderEngine.func_110577_a(GuiResources.sprite(numString));
				drawImageQuad(this.xPosition + 3, this.yPosition + 3, 24f, 24f, 0f, 0f, 1f, 1f);
			}
			String name = "";
			GL11.glScalef(0.8f, 0.8f, 0.8f);
			if (starterIndex == -1) {
				name = "Coming Soon...";
				fontrenderer.drawString(name, (int) ((this.xPosition + this.width / 2 - 25) / 0.8f), (int) ((this.yPosition + (this.height-3) / 2) / 0.8f), l);
			} else {
				name = PixelmonServerStore.starterListPacket.starterList[starterIndex].name;
				fontrenderer.drawString(name, (int) ((this.xPosition + this.width / 2 - 15) / 0.8f), (int) ((this.yPosition + (this.height-3) / 2) / 0.8f), l);
			}
			GL11.glScalef(1 / 0.8f, 1 / 0.8f, 1 / 0.8f);
		}
	}

	@Override
	public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {
		if (starterIndex == -1)
			return false;
		return super.mousePressed(par1Minecraft, par2, par3);
	}

	private void drawImageQuad(int x, int y, float w, float h, float us, float vs, float ue, float ve) {
		// activate the specified texture
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
