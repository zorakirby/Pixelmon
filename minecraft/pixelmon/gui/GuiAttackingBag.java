package pixelmon.gui;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

public class GuiAttackingBag extends GuiScreen{
	
	private GuiScreen parent; 
	
	public GuiAttackingBag(GuiScreen parent){
		this.parent = parent;
	}
	
	public void initGui(){
		super.initGui();
		controlList.clear();
		controlList.add(new GuiButton(0, width / 2 - 25, height / 2 - 100, 50, 20, "Back"));
	}
	
	public void actionPerformed(GuiButton b){
		if(b.id == 0){
			mc.displayGuiScreen(parent);
		}
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
	}

}
