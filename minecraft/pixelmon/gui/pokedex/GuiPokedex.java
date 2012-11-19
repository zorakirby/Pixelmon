package pixelmon.gui.pokedex;

import org.lwjgl.opengl.GL11;

import pixelmon.config.PixelmonConfig;
import pixelmon.gui.ContainerEmpty;
import pixelmon.pokedex.Pokedex;
import pixelmon.pokedex.PokedexEntry;
import pixelmon.render.GraphicsHelper;
import pixelmon.storage.PixelmonStorage;
import net.minecraft.src.*;

public class GuiPokedex extends GuiContainer
{
	
	public int top, left;
	public int currentEntry;
	public Pokedex pokedex;
	public GuiPokedexSlot scrollPane;
	
	private GuiPokedex(String username) 
	{
		super(new ContainerEmpty());
		currentEntry = 1;
		pokedex = PixelmonStorage.PokeballManager.getPlayerStorage(PixelmonStorage.PokeballManager.getPlayerFromName(username)).pokedex;
		xSize = 256;
		ySize = 226;
	}

	public GuiPokedex(String username, int id)
	{
		this(username);
		currentEntry = id;
	}
	
	/*
	public GuiPokedex(String lookup)
	{
		this();
		currentEntry = Pokedex.nameToID(lookup);
		if(currentEntry == 0)
			currentEntry = 1;
	}
	*/
	
	public void initGui()
	{
		super.initGui();
		left = (width - xSize) / 2;
		top = (height - ySize) / 2;
		controlList.clear();
		scrollPane = new GuiPokedexSlot(this);
		scrollPane.elementClicked(0, false);
	}
	
	int mouseX, mouseY;
	float mfloat;
	
	protected void drawGuiContainerBackgroundLayer(float par3, int par2, int par1)
	{
		pokedex = PixelmonStorage.PokeballManager.getPlayerStorage(PixelmonStorage.PokeballManager.getPlayerFromName(pokedex.owner.username)).pokedex;
		drawDefaultBackground();
		left = (width - xSize) / 2;
		top = (height - ySize) / 2;
		mouseX = par2;
		mouseY = par1;
		mfloat = par3;
		PokedexEntry selectedEntry = pokedex.getEntry(currentEntry);
		RenderHelper.disableStandardItemLighting();
		int i = mc.renderEngine.getTexture("/pixelmon/gui/pokedex.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(i);
		drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
		fontRenderer.drawString("Pokedex", left + 6, top + 5, 0xFFFFFF);
		String s = selectedEntry.getPokedexDisplayNumber() + " " + (pokedex.hasSeen(currentEntry)?selectedEntry.name:"???");
		drawCenteredString(fontRenderer, s, left + 174, top + 38 - 3, 0x575757);
		s = "Description";
		drawCenteredString(fontRenderer, s, left + 141, top + 125, 0x575757);
		boolean b = pokedex.hasCaught(currentEntry);
		//b = true;
		s = "";
		if(b)
		{
			s = selectedEntry.description;
			fontRenderer.drawSplitString(s, left + 104, top + 141 - 3, 97, 0x575757);
		}
		s = "Height: ";
		if(b)
			s += PixelmonConfig.isInMetric?selectedEntry.heightM:selectedEntry.heightC;
		else
			s += "??? " + (PixelmonConfig.isInMetric?"m":"ft");
		fontRenderer.drawString(s, left + 164, top + 69 - 10, 0x575757);
		s = "Weight: ";
		if(b)
			s += PixelmonConfig.isInMetric?selectedEntry.weightM:selectedEntry.weightC;
		else
			s += "??? " + (PixelmonConfig.isInMetric?"kg":"lbs");
		b = pokedex.hasSeen(currentEntry);
		//b = !pokedex.isUnknown(currentEntry);
		if(b)
			GraphicsHelper.drawModelToScreen(40, 40, 40, left + 131, top + 107, selectedEntry.getRenderTarget(mc.theWorld), this, true);
		fontRenderer.drawString(s, left + 164, top + 69 + 0, 0x575757);
		scrollPane.drawScreen(mouseX, mouseY, mfloat);
	}
	
	public void drawCenteredString(FontRenderer f, String s, int x, int y, int c)
	{
		f.drawString(s, x - f.getStringWidth(s) / 2, y, c);
	}
	
	public boolean doesGuiPauseGame()
	{
		return true;
	}
	
}