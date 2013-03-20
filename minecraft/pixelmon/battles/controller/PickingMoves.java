package pixelmon.battles.controller;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.EffectBase;
import pixelmon.battles.attacks.EffectBase.ApplyStage;
import pixelmon.battles.attacks.specialAttacks.attackModifiers.Priority;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.battles.status.StatusBase;

public class PickingMoves {

	public static void pickMoves(BattleController bc) {
		for (BattleParticipant p : bc.participants) {
			if (p.attack != null)
				p.attack.flinched = false;
			p.canAttack = true;
			p.willTryFlee = false;
			p.isSwitching = false;
			BattleParticipant foe = bc.otherParticipant(p);
			for (int i = 0; i < p.currentPokemon().status.size(); i++) {
				StatusBase e = p.currentPokemon().status.get(i);
				try {
					if (!e.canAttackThisTurn(p.currentPokemon(), foe.currentPokemon())) {
						p.canAttack = false;
						p.attackList.add("None");
						break;
					}
				} catch (Exception exc) {
					System.out.println("Error calculating canAttackThisTurn for " + e.type.toString());
					System.out.println(exc.getStackTrace());
				}
			}
			if (p.canAttack && (p.attack == null || !p.attack.doesPersist(p.currentPokemon()))) {
				p.attack = p.getMove();
				if (p.attack != null)
					p.attackList.add(p.attack.baseAttack.attackName);
			}
		}
	}

	public static void checkMoveSpeed(BattleController bc) {
		ArrayList<BattleParticipant> sortedParticipants = new ArrayList<BattleParticipant>();
		int order = 0;
		for (int i = 0; i < bc.participants.size(); i++) {
			BattleParticipant p = bc.participants.get(i);
			p.orderSet = false;
			if (p.willUseItemInStack != null) {
				p.moveOrder = order++;
				p.orderSet = true;
				sortedParticipants.add(p);
				bc.participants.remove(i);
				i--;
			}
		}

		boolean hasPriorityEffect = false;
		for (StatusBase e : bc.battleStatusList) {
			if (e.hasPriorityEffect()) {
				hasPriorityEffect = true;
			}
		}

		for (BattleParticipant p : bc.participants) {
			if (p.attack != null) {
				for (EffectBase e : p.attack.baseAttack.effects) {
					if (e.applyStage == ApplyStage.Priority) {
						p.priority = ((Priority) e).value;
					}
				}
			}
		}

		Object[] array = bc.participants.toArray();
		for (int i = 1; i < array.length; i++) {
			BattleParticipant p = (BattleParticipant) array[i];
			int holePos = i;
			while (holePos > 0 && (((BattleParticipant) array[holePos - 1]).priority < p.priority || doesGoFirst(p, (BattleParticipant) array[holePos - 1]))) {
				array[holePos] = array[holePos - 1];
				holePos--;
			}
			array[holePos] = p;
		}

		for (Object p : array)
			sortedParticipants.add((BattleParticipant)p);

		bc.participants = sortedParticipants;
	}

	private static boolean doesGoFirst(BattleParticipant p, BattleParticipant foe) {
		if (p.currentPokemon().stats.Speed * p.currentPokemon().battleStats.getSpeedModifier() > foe.currentPokemon().stats.Speed * foe.currentPokemon().battleStats.getSpeedModifier())
			return true;
		else if (foe.currentPokemon().stats.Speed * foe.currentPokemon().battleStats.getSpeedModifier() > p.currentPokemon().stats.Speed * p.currentPokemon().battleStats.getSpeedModifier())
			return false;

		if (RandomHelper.getRandomNumberBetween(0, 2) >= 1)
			return false;
		else
			return true;

	}

	private static BattleParticipant otherParticipant(BattleParticipant current, ArrayList<BattleParticipant> participants) {
		for (BattleParticipant p : participants)
			if (p != current)
				return p;
		return null;
	}
}
