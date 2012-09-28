package pixelmon.gui;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.StatCollector;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.enums.EnumType;

public class GuiScreenPokeChecker extends GuiContainer {
	protected PixelmonDataPacket targetPacket;

	public GuiScreenPokeChecker(PixelmonDataPacket pixelmonDataPacket) {
		super(new ContainerEmpty());
		targetPacket = pixelmonDataPacket;
	}

	public boolean doesGuiPauseGame() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
		controlList.clear();
		controlList.add(new GuiButton(0, width / 2 - 100, (int) (height * 0.8), StatCollector.translateToLocal("menu.returnToGame")));
		String s = "";
		s = !targetPacket.name.equals(targetPacket.nickname) ? "Give Nickname" : "Change Nickname";
		controlList.add(new GuiButton(1, width / 2 - 100, (int) (height * 0.8 - 25), s));
	}

	public void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			mc.thePlayer.closeScreen();
			break;
		case 1:
			mc.displayGuiScreen(new GuiRenamePokemon(targetPacket, this));
		}

	}

	public void drawGuiContainerBackgroundLayer(float f, int i, int i1) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, "PokeChecker", width / 2, height / 7, 0xffffff);
		drawCenteredString(fontRenderer, "Lv: " + targetPacket.lvl + " " + targetPacket.nickname + " (" + targetPacket.name + ")", width / 2, height / 7 + 15, 0xcccccc);
		this.drawHorizontalLine(width / 5, height / 7 + 20, width * 4 / 5, 0xffffff);
		// STATS
		drawCenteredString(fontRenderer, "Stats", width / 3, height / 7 + 25, 0xdddddd);
		String s = (targetPacket.type2 == EnumType.Mystery ? "Type:" : "Types:");
		drawCenteredString(fontRenderer, s, width / 3, height / 7 + 40, 0xdddddd);
		if (targetPacket.type2 == EnumType.Mystery) {
			drawCenteredString(fontRenderer, targetPacket.type1.getName(), width / 3, height / 7 + 50, targetPacket.type1.getColor());
		} else {
			int swidth = fontRenderer.splitStringWidth(targetPacket.type1.getName(), 10);
			drawCenteredString(fontRenderer, targetPacket.type1.getName(), width / 3 - (swidth / 2), height / 7 + 50, targetPacket.type1.getColor());
			drawString(fontRenderer, targetPacket.type2.getName(), width / 3 + 3, height / 7 + 50, targetPacket.type2.getColor());
		}
		drawCenteredString(fontRenderer, "Health: " + targetPacket.health + "/" + targetPacket.hp, width / 3, height / 7 + 60, 0xdddddd);
		drawCenteredString(fontRenderer, "Exp: " + targetPacket.lvl + "/" + targetPacket.nextLvlXP, width / 3, height / 7 + 70, 0xdddddd);
		drawCenteredString(fontRenderer, "Attack: " + targetPacket.Attack, width / 3, height / 7 + 80, 0xdddddd);
		drawCenteredString(fontRenderer, "Defence: " + targetPacket.Defence, width / 3, height / 7 + 90, 0xdddddd);
		drawCenteredString(fontRenderer, "Special Attack: " + targetPacket.SpecialAttack, width / 3, height / 7 + 100, 0xdddddd);
		drawCenteredString(fontRenderer, "Special Defence: " + targetPacket.SpecialDefence, width / 3, height / 7 + 110, 0xdddddd);
		drawCenteredString(fontRenderer, "Speed: " + targetPacket.Speed, width / 3, height / 7 + 120, 0xdddddd);
		// MOVES
		drawCenteredString(fontRenderer, "Moves", width * 2 / 3, height / 7 + 25, 0xdddddd);
		for (int i2 = 0; i2 < targetPacket.numMoves; i2++) {
			drawCenteredString(fontRenderer, (targetPacket.moveset[i2]).attackName, width * 2 / 3, height / 7 + 40 + (i2 * 10), targetPacket.moveset[i2].type.getColor());
		}
	}
}