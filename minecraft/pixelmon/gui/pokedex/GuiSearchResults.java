package pixelmon.gui.pokedex;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.src.Tessellator;

public class GuiSearchResults
{
	
	public GuiPokedexMore guiPokedexMore;
	public int slotHeight = 10;
	public int posX, posY, width, height;
	public int[] results;
	public int hoverElement;
	public GuiSearchResults(GuiPokedexMore gui, int x, int y, int w, int h)
	{
		guiPokedexMore = gui;
		posX = x;
		posY = y;
		width = w;
		height = h;
		updateResults();
	}
	
	public void updateResults()
	{
		Integer[] ints = guiPokedexMore.results.toArray(new Integer[] {});
		results = new int[ints.length];
		for(int i = 0; i < ints.length; i++)
			results[i] = ints[i];
	}
	
	public void elementClicked(int index)
	{
		int i = results[index];
		guiPokedexMore.parentScreen.currentEntry = i;
		guiPokedexMore.parentScreen.scrollPane.scrollTo(i);
		guiPokedexMore.returnToParent();
	}
	
	private void drawSlot(int x, int y, String text, boolean hover)
	{
		if(height < y + slotHeight)
			return;
		if(hover)
		{
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glColor4f(1, 0, 0, 1);
			Tessellator t = Tessellator.instance;
			t.startDrawingQuads();
			t.setColorRGBA(255, 0, 0, 255);
			t.addVertexWithUV(x + width, y, 0, 1, 0);
			t.addVertexWithUV(x + width, y + slotHeight, 0, 1, 1);
			t.addVertexWithUV(x, y + slotHeight, 0, 0, 1);
			t.addVertexWithUV(x, y, 0, 0, 0);
			t.draw();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_CULL_FACE);
		}
		guiPokedexMore.drawText(x, y + 1, text, 0xFFFFFF);
	}
	
	public void draw()
	{
		for(int i = 0; i < results.length; i++)
			drawSlot(posX, posY + 5 + i * slotHeight, guiPokedexMore.pokedex.getEntry(results[i]).toString(), i == hoverElement);
		//guiPokedexMore.drawText(300, 20, "" + hoverElement, 0xFFFFFF);
	}
	
	public void update(int x, int y)
	{
		updateResults();
		int i = y - posY;
		i /= slotHeight;
		if(posX < x && posY + width > x && i >= 0 && i < results.length)
		{
			hoverElement = i;
			if(Mouse.isButtonDown(0))
				elementClicked(i);
		} else
			hoverElement = -1;
	}
}