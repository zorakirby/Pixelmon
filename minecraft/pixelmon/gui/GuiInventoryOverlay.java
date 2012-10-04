package pixelmon.gui;

import java.awt.Rectangle;

import net.minecraft.client.Minecraft;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiInventory;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.ScaledResolution;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiInventoryOverlay extends GuiScreen{
	
	Rectangle button;
	boolean clicked = false;
	boolean open = false;
	
	@ForgeSubscribe
	public void onRenderWorldLast(RenderWorldLastEvent event){
		if(Minecraft.getMinecraft().currentScreen instanceof GuiInventory){
			GuiInventory gui = (GuiInventory) Minecraft.getMinecraft().currentScreen;
			FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
			ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
			int var6 = var5.getScaledWidth();
			int var7 = var5.getScaledHeight();
			int var8 = var5.getScaleFactor();
			button = new Rectangle(var6 / 2 + 100, var7 / 2 - 10, 75, 20);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
			int var4;
			var4 = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/gui/pixelmonOverlaySimple.png");
			Minecraft.getMinecraft().renderEngine.bindTexture(var4);
			Minecraft.getMinecraft().entityRenderer.setupOverlayRendering();
			RenderHelper.enableGUIStandardItemLighting();
			if(Mouse.isButtonDown(0)){
				int mx = Mouse.getEventX() / var8;
				int my = Mouse.getEventY() / var8;
				if(button.contains(mx, my) && !clicked){
					System.out.println("Will open another gui for the pixelmon bag!");
				}
				clicked = true;
			}
			else{
				clicked = false;
			}
			fontRenderer.setUnicodeFlag(true);
			int i = 0;
			//can be replaced with some texture later
			drawRect(var6 / 2 + 100, var7 / 2 - 10, var6 / 2 + 175, var7 / 2 + 10, 0xff000000);
			drawRect(var6 / 2 + 101, var7 / 2 - 9, var6 / 2 + 174, var7 / 2 + 9, 0xffffffff);
			fontRenderer.drawString("Pixelmon Bag", var6 / 2 + 115, var7 / 2 - 5, 0x000000);
			fontRenderer.setUnicodeFlag(false);
			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
		else{
			button = null;
			open = false;
		}
		
	}
	

}
