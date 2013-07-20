package pixelmon.battles.participants;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.controller.BattleController;
import pixelmon.battles.status.GlobalStatusBase;
import pixelmon.battles.status.StatusBase;
import pixelmon.comm.ChatHandler;
import pixelmon.config.PixelmonConfig;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class BattleParticipant {
	public boolean startedBattle = false;
	BattleParticipant opponent;
	public BattleController bc;
	public ArrayList<Integer> attackersList = new ArrayList<Integer>();
	public ArrayList<String> attackList = new ArrayList<String>();
	public Attack attack;
	public boolean canAttack = true;
	public boolean willTryFlee = false;
	public boolean isSwitching = false;
	public ItemStack willUseItemInStack;
	public int moveOrder;
	public int priority;
	public boolean orderSet;
	public int team = 0;
	public boolean wait;
	public int escapeAttempts = 0;
	public int willUseItemInStackInfo;

	public abstract EntityPixelmon currentPokemon();

	public abstract boolean hasMorePokemon();

	public abstract boolean canGainXP();

	public void StartBattle(BattleController bc, BattleParticipant opponent) {
		escapeAttempts = 0;
		this.opponent = opponent;
		if (startedBattle)
			ChatHandler.sendBattleMessage(currentPokemon().getOwner(), "You initiated a battle with " + opponent.getName() + "!");
		else
			ChatHandler.sendBattleMessage(currentPokemon().getOwner(), opponent.getName() + " initiated a battle with you!");

		currentPokemon().battleController = bc;
		this.bc = bc;
	}

	public abstract void EndBattle();

	public abstract boolean getIsFaintedOrDead();

	public abstract String getName();

	public abstract Attack getMove();

	public abstract void switchPokemon(int newPixelmonId);

	public abstract boolean checkPokemon();

	public abstract void updatePokemon();

	public abstract EntityLivingBase getEntity();

	public abstract void updateOpponent();

	public abstract ParticipantType getType();

	public abstract void getNextPokemon();

	public void addToAttackersList() {
		attackersList.add(currentPokemon().getPokemonId());
	}

	public abstract String getFaintMessage();

	public void turnTick() {
		if (currentPokemon() == null)
			return;
		for (int i = 0; i < currentPokemon().status.size(); i++) {
			StatusBase s = currentPokemon().status.get(i);
			try {
				s.applyRepeatedEffect(currentPokemon(), opponent.currentPokemon());
				s.turnTick(currentPokemon(), opponent.currentPokemon());
			} catch (Exception e) {
				if (PixelmonConfig.printErrors) {
					System.out.println("Error calculating turnTick for " + s.type.toString());
					System.out.println(e.getStackTrace());
				}
			}
		}
		
//		for (int i = 0; i < this.bc.globalStatuses.size()-1; i++)
//		{
//			System.out.println(bc.globalStatuses.get(i).endOfTurnMessage());
//			GlobalStatusBase g = bc.globalStatuses.get(i);
//			ChatHandler.sendBattleMessage(bc.participants.get(0).currentPokemon().getOwner(), bc.participants.get(1).currentPokemon().getOwner(), g.endOfTurnMessage());
//			g.applyRepeatedEffect(bc.globalStatuses, currentPokemon(), opponent.currentPokemon());
//		}
		
	}
}
