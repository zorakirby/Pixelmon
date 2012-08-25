package pixelmon.gui.pc;

import net.minecraft.src.NBTTagCompound;

public class SlotPCParty extends SlotPC{
	
	public int partyPosition;
	
	public SlotPCParty(int x, int y, int partyNumber){
		super(x, y, null);
		partyPosition = partyNumber;
	}

}
