package pixelmon.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.FontRenderer;

public class FontScaler {

	public static void scaleFont(FontRenderer f, String s, int x, int y, int color, double scale){
		GL11.glScaled(scale, scale, scale);
		f.drawString(s, x, y, color);
		double fixScale = 1/scale;
		GL11.glScaled(fixScale, fixScale, fixScale);
	}
}
