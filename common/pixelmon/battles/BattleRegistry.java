package pixelmon.battles;

import java.util.ArrayList;

import pixelmon.battles.participants.PlayerParticipant;

import net.minecraft.src.EntityPlayer;

public class BattleRegistry {
	private static ArrayList<BattleController> battleList = new ArrayList<BattleController>();
	
	public static void registerBattle(BattleController bc){
		battleList.add(bc);
	}
	
	public static int getIndex(BattleController bc){
		for (int i=0;i < battleList.size(); i++){
			if (battleList.get(i) == bc) return i;
		}
		return -1;
	}
	
	public static BattleController getBattle(int index){
		return battleList.get(index);
	}
	
	public static void deRegisterBattle(BattleController bc){
		for (int i=0; i < battleList.size(); i++)
		{
			if (battleList.get(i) == bc){
				battleList.remove(i);
				return;
			}
		}
	}
	
	public static BattleController getBattle(EntityPlayer player) {
		for (BattleController bc : battleList) {
			if (bc.participant1 instanceof PlayerParticipant) {
				if (((PlayerParticipant) bc.participant1).player == player) {
					return bc;
				}
			} else if (bc.participant2 instanceof PlayerParticipant) {
				if (((PlayerParticipant) bc.participant2).player == player) {
					return bc;
				}
			}
		}
		return null;
	}
}
