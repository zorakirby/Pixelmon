package pixelmon.gui;

import net.minecraft.src.GuiContainer;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.render.GraphicsHelper;

public class GuiEvolve extends GuiContainer
{
	
	public EntityPixelmon beginPixelmon, finishPixelmon;
	private String start, complete, cancel;
	
	public GuiEvolve(EntityPixelmon start, EntityPixelmon end)
	{
		super(new ContainerEmpty());
		beginPixelmon = start;
		finishPixelmon = end;
		this.start = "What? Your " + beginPixelmon.getNickname()
				+ " is evolving!";
		complete = "Congratulations, your " + beginPixelmon.getNickname() + " evolved into " + finishPixelmon.getNickname() + "!";
		cancel = "Huh? " + beginPixelmon.getNickname() + " stopped evolving!";
	}
	
	public void initGui()
	{
		super.initGui();
		controlList.clear();
	}
	
	public void updateScreen()
	{
		GraphicsHelper.drawModelToScreen(1, 1, 1, mc.displayWidth / 2, mc.displayWidth / 2, beginPixelmon, this, true);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		
	}
}