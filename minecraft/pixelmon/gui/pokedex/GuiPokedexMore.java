package pixelmon.gui.pokedex;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import pixelmon.pokedex.Pokedex;
import net.minecraft.src.*;

public class GuiPokedexMore extends GuiScreen
{
	public GuiPokedex parentScreen;
	public int posX, posY, width, height;
	public GuiTextField textArea;
	public Pokedex pokedex;
	private String query;
	private boolean hasChanged;
	public int resultsLength;
	public ArrayList<Integer> results;
	public GuiSearchResults searchResults;
	public int mouseX, mouseY;
	
	public GuiPokedexMore(GuiPokedex p, String s)
	{
		parentScreen = p;
		query = s;
		hasChanged = true;
		pokedex = p.pokedex;
		results = new ArrayList<Integer>();
	}
	
	public void initGui()
	{
		width = 80;
		height = 10;
		posX = parentScreen.left - width;
		posY = parentScreen.top + 2;
		textArea = new GuiTextField(mc.fontRenderer, posX, posY, width, height);
		searchResults = new GuiSearchResults(this, posX, posY + height, width, /*parentScreen.ySize*/226);
		textArea.setText(query);
	}
	
	public void drawScreen(int mx, int my, float pt)
	{
		mouseX = mx;
		mouseY = my;
		//drawDefaultBackground();
		RenderHelper.disableStandardItemLighting();
		parentScreen.drawScreen(mx, my, pt);
		textArea.drawTextBox();
		searchResults.draw();
		//searchResults.draw();
		RenderHelper.enableStandardItemLighting();
	}
	
	public void updateScreen()
	{
		if(hasChanged)
		{
			textArea.setText(query);
			results.clear();
			for(int i = 1; i <= Pokedex.pokedexSize; i++)
			{
				if(pokedex.hasSeen(i) && !Pokedex.isEntryEmpty(i))
				{
					String s = pokedex.getEntry(i).name;
					if(startsWithIgnoreCase(s, query))
						results.add(i);
				}
			}
			hasChanged = false;
		}
		searchResults.update(mouseX, mouseY);
	}
	
	public boolean startsWithIgnoreCase(String target, String prefix)
	{
		for(int i = 0; i < prefix.length(); i++)
		{
			if(i >= target.length())
				return false;
			char c = prefix.charAt(i);
			char t = target.charAt(i);
			if(Character.toUpperCase(c) != Character.toUpperCase(t))
			{
				return false;
			}
		}
		return true;
	}
	
	public void keyTyped(char c, int i)
	{
		if(i == Keyboard.KEY_ESCAPE)
		{
			returnToParent();
		} else if(i == Keyboard.KEY_BACK)
		{
			if(query != null && query.length() > 0)
				query = query.substring(0, query.length() - 1);
		} else if(c == 'v' && isCtrlKeyDown())
		{
			query += getClipboardString();
		} else if(Character.isLetter(c))
			query += c;
		hasChanged = true;
	}
	
	public void returnToParent()
	{
		mc.displayGuiScreen(parentScreen);
	}
	
	public void drawText(int x, int y, String text, int c)
	{
		fontRenderer.drawString(text, x, y, c);
	}
	
	public boolean doesGuiPauseGame()
	{
		return true;
	}
}