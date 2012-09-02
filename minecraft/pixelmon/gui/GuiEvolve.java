package pixelmon.gui;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.render.GraphicsHelper;
import net.minecraft.src.*;

public class GuiEvolve extends GuiScreen
{
	
	public EntityPixelmon beginPixelmon, finishPixelmon;
	private String start, complete, cancel;
	
	public GuiEvolve(EntityPixelmon start, EntityPixelmon end)
	{
		beginPixelmon = start;
		finishPixelmon = end;
		this.start = "What? Your " + beginPixelmon.getNickname()
				+ " is evolving!";
		complete = "Congratulations, your " + beginPixelmon.getNickname() + " evolved into " + finishPixelmon.getNickname() + "!";
		cancel = "Huh? " + beginPixelmon.getNickname() + " stopped evolving!";
	}
	
	public void initGui()
	{
		controlList.clear();
	}
	
	public void updateScreen()
	{
		GraphicsHelper.drawModelToScreen(1, 1, 1, mc.displayWidth / 2, mc.displayWidth / 2, beginPixelmon, this, true);
	}
}