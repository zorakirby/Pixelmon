package pixelmon.gui.pokedex;

import pixelmon.pokedex.Pokedex;
import pixelmon.pokedex.PokedexEntry;
import net.minecraft.src.ModLoader;
import net.minecraft.src.Tessellator;


public class GuiPokedexSlot extends GuiPokedexSlotBase {
	GuiPokedex guiPokedex;
	public GuiPokedexSlot(GuiPokedex gui) 
	{
		super(gui.top+19, gui.left+3, gui.height);
		guiPokedex = gui;
	}

	@Override
    protected int getSize()
    {
        return Pokedex.pokedexSize;
    }


    /**
     * the element in the slot that was clicked, boolean for wether it was double clicked or not
     */
    protected void elementClicked(int par1, boolean par2)
    {
    	guiPokedex.currentEntry = par1 + 1;
    }

    /**
     * returns true if the element passed in is currently selected
     */

    protected boolean isSelected(int par1)
    {
        return par1 + 1 == guiPokedex.currentEntry;
    }

    /**
     * return the height of the content being scrolled
     */
    protected int getContentHeight()
    {
        return getSize() * slotHeight;
    	//return super.getContentHeight();
    }

    protected void drawBackground()
    {
        this.guiPokedex.drawBackground(0);
    }

	protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5) 
	{	
		PokedexEntry p = guiPokedex.pokedex.getEntry(var1 + 1);
		//this.guiPokedex.drawString(Minecraft.getMinecraft().fontRenderer, guiPokedex.pokedex.getEntry(var1).getDisplayNumber(true) + " " + (String)this.guiPokedex.pokedexList.get(var1), var2+2, var3-1, 16777215);
		guiPokedex.drawString(ModLoader.getMinecraftInstance().fontRenderer, p.getPokedexDisplayNumber() + " " + (guiPokedex.pokedex.hasSeen(var1 + 1)?p.name:"???"), var2 + 2, var3 - 1, 16777215);
	}

}
