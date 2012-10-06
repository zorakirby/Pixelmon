package pixelmon.gui.inventoryExtended;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiInventory;

public class GuiInventoryPixelmonExtended extends GuiInventory {

	public GuiInventoryPixelmonExtended(EntityPlayer par1EntityPlayer) {
		super(par1EntityPlayer);
	}

	@Override
	public void initGui() {
		super.initGui();

		controlList.add(new GuiButton(2, this.width * 4 / 5, this.height / 2, 50, 20, "Pixelmon"));
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		System.out.println("new gui action performed");
		super.actionPerformed(par1GuiButton);
	}

}
