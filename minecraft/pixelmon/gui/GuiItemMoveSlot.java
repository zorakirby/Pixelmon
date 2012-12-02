package pixelmon.gui;

import pixelmon.comm.PixelmonMovesetDataPacket;

public class GuiItemMoveSlot {

	private PixelmonMovesetDataPacket attack;
	private String name;
	private int attackIndex;
	
	public GuiItemMoveSlot(PixelmonMovesetDataPacket[] moveSet, int a){
		attack = moveSet[a];
		name = attack.attackName;
		attackIndex = a;
	}

	public int getAttackIndex(){
		return attackIndex;
	}
	
	public String getDisplay(){
		return name + " " + attack.pp + "/" + attack.ppBase;
	}

	public String getName(){
		return name;
	}
	
	public PixelmonMovesetDataPacket getAttack(){
		return attack;
	}

}
