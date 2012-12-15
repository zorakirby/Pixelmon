package pixelmon.client.gui.pc;


public class SlotPCPC extends SlotPC{
	public int boxNumber, boxPosition;
	
	public SlotPCPC(int x, int y, int boxNum, int boxPos){
		super(x, y, null);
		boxNumber = boxNum;
		boxPosition = boxPos;
	}
	
}
