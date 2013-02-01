package pixelmon.battles;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import pixelmon.battles.controller.BattleController;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.battles.participants.PlayerParticipant;

public class BattleRegistry {
	private static int battleIndex = 0;
	private static ArrayList<BattleController> battleList = new ArrayList<BattleController>();

	public static void registerBattle(BattleController bc) {
		bc.battleIndex = battleIndex++;
		battleList.add(bc);
	}

	public static int getIndex(BattleController bc) {
		for (int i = 0; i < battleList.size(); i++)
			if (battleList.get(i) == bc)
				return battleList.get(i).battleIndex;

		return -1;
	}

	public static BattleController getBattle(int index) {
		for (int i = 0; i < battleList.size(); i++)
			if (battleList.get(i).battleIndex == index)
				return battleList.get(i);

		return null;
	}

	public static BattleController getBattle(EntityPlayer player) {
		for (int i = 0; i < battleList.size(); i++) {
			for (BattleParticipant p : battleList.get(i).participants)
				if (p instanceof PlayerParticipant) {
					if (((PlayerParticipant) p).player == player) {
						return battleList.get(i);
					}
				}
		}
		return null;
	}

	public static void deRegisterBattle(BattleController bc) {
		for (int i = 0; i < battleList.size(); i++) {
			if (battleList.get(i) == bc) {
				battleList.remove(i);
				return;
			}
		}
	}

	public static void updateBattles() {
		for (int i = 0; i < battleList.size(); i++) {
			battleList.get(i).update();
		}
	}
}
