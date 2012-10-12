package pixelmon.gui;

import net.minecraft.src.Container;
import net.minecraft.src.GuiContainer;

public class GuiAttackingNew extends GuiContainer{

	private int battleControllerIndex;
	
	public GuiAttackingNew(int battleControllerIndex) {
		super(new ContainerEmpty());
		this.battleControllerIndex = battleControllerIndex;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		// TODO Auto-generated method stub
		
	}

}
