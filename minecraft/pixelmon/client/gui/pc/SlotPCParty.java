package pixelmon.client.gui.pc;


public class SlotPCParty extends SlotPC{
	
	public int partyPosition;
	
	public SlotPCParty(int x, int y, int partyNumber){
		super(x, y, null);
		partyPosition = partyNumber;
	}

}
