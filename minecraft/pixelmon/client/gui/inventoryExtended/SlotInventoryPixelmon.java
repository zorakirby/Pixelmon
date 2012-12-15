package pixelmon.client.gui.inventoryExtended;

import java.awt.Rectangle;

import pixelmon.client.gui.pc.SlotPC;
import pixelmon.comm.PixelmonDataPacket;

public class SlotInventoryPixelmon extends SlotPC {

	public int heldItemX, heldItemY;

	public SlotInventoryPixelmon(int x, int y, PixelmonDataPacket pokemon) {
		super(x, y, pokemon);
		swidth = 16;
		heldItemX = x + 19;
		heldItemY = y;
	}

	public Rectangle getHeldItemBounds() {
		return new Rectangle(heldItemX, heldItemY, swidth, swidth);
	}

}
