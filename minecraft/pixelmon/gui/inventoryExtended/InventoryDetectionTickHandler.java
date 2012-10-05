package pixelmon.gui.inventoryExtended;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiInventory;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ScaledResolution;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class InventoryDetectionTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		GuiScreen gui = Minecraft.getMinecraft().currentScreen;
		if (gui != null) {
			if (gui instanceof GuiInventory && !(gui instanceof GuiInventoryPixelmonExtended)) {
				GuiInventoryPixelmonExtended newGui = new GuiInventoryPixelmonExtended(Minecraft.getMinecraft().thePlayer);
				ScaledResolution var2 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
                int var3 = var2.getScaledWidth();
                int var4 = var2.getScaledHeight();
                ((GuiScreen)newGui).setWorldAndResolution(Minecraft.getMinecraft(), var3, var4);
				Minecraft.getMinecraft().currentScreen = newGui;
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> types, Object... tickData) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER, TickType.WORLD);
	}

	@Override
	public String getLabel() {
		return "Inventory Open Checker";
	}

}
