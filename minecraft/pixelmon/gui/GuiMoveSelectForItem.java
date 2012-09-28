package pixelmon.gui;

import java.awt.Rectangle;
import java.util.ArrayList;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.Item;

import org.lwjgl.input.Keyboard;

import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiMoveSelectForItem extends GuiContainer {

	private GuiAttackingBag parent;
	private PixelmonDataPacket userPacket;
	private boolean choiceSelected;
	private ArrayList<GuiItemMoveSlot> moveSlots;
	private GuiItemMoveSlot selected;
	private Item item;

	public GuiMoveSelectForItem(PixelmonDataPacket userPacket, GuiAttackingBag parent, Item item) {
		super(new ContainerEmpty());
		this.parent = parent;
		this.userPacket = userPacket;
		this.item = item;
		choiceSelected = false;
		moveSlots = new ArrayList<GuiItemMoveSlot>();
		selected = null;
		loadMoves();
	}

	public void loadMoves() {
		for (int a = 0; a < userPacket.moveset.length; a++) {
			if (userPacket.moveset[a] != null) {
				GuiItemMoveSlot ms = new GuiItemMoveSlot(userPacket.moveset, a);
				moveSlots.add(ms);
			}
		}
	}

	public void initGui() {
		super.initGui();
		controlList.clear();
		controlList.add(new GuiButton(0, width / 2 - 25, height / 2 - 100, 50, 20, "Back"));
		controlList.add(new GuiButton(1, width / 2 - 35, height / 2 - 70 + moveSlots.size() * 20 + 10, 70, 20, "Restore PP"));
	}

	public void actionPerformed(GuiButton b) {
		if (b.id == 0) {
			mc.displayGuiScreen(parent);
		}
		if (b.id == 1) {
			if (selected == null) {
				mc.displayGuiScreen(parent);
				return;
			}
			int moveIndex = selected.getAttackIndex();
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.BagPacket, item.shiftedIndex, parent.getParent().battleControllerIndex, moveIndex));
			mc.thePlayer.closeScreen();
		}
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float par3, int par1, int par2) {
		drawDefaultBackground();
		for (int i = 0; i < moveSlots.size(); i++) {
			GuiItemMoveSlot bs = moveSlots.get(i);
			if (selected != bs) {
				drawRect(width / 2 - 55, height / 2 - 75 + i * 20, width / 2 + 55, height / 2 - 55 + i * 20, 0x6f000000);
				fontRenderer.drawString(bs.getDisplay(), width / 2 - 50, height / 2 - 70 + i * 20, 0xffffff);
			} else {
				drawRect(width / 2 - 55, height / 2 - 75 + i * 20, width / 2 + 55, height / 2 - 55 + i * 20, 0x6f000000);
				drawRect(width / 2 - 55, height / 2 - 75 + i * 20, width / 2 + 55, height / 2 - 55 + i * 20, 0x1fffff00);
				fontRenderer.drawString(bs.getDisplay(), width / 2 - 50, height / 2 - 70 + i * 20, 0xffff00);
			}
		}
		super.drawScreen(par1, par2, par3);
	}

	public void keyTyped(char i, int i1) {
	}

	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		for (int i = 0; i < moveSlots.size(); i++) {
			Rectangle r = new Rectangle(width / 2 - 55, height / 2 - 75 + i * 20, 110, 20);
			if (r.contains(par1, par2)) {
				GuiItemMoveSlot bs = moveSlots.get(i);
				selected = bs;
				break;
			}
		}
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

}
