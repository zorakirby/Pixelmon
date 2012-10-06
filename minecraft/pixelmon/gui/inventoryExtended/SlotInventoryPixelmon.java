package pixelmon.gui.inventoryExtended;

import pixelmon.comm.PixelmonDataPacket;
import pixelmon.gui.pc.SlotPC;

public class SlotInventoryPixelmon extends SlotPC{

	public SlotInventoryPixelmon(int x, int y, PixelmonDataPacket pokemon) {
		super(x, y, pokemon);
		swidth = 18;
		//change this when resized
	}

}
