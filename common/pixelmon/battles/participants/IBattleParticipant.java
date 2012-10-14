package pixelmon.battles.participants;

import net.minecraft.src.EntityLiving;
import pixelmon.battles.BattleController;
import pixelmon.battles.attacks.Attack;
import pixelmon.entities.pixelmon.EntityPixelmon;

public interface IBattleParticipant {
	boolean isWild = false;
	EntityPixelmon currentPokemon();
	boolean hasMorePokemon();
	boolean canGainXP();
	void StartBattle(IBattleParticipant opponent);
	void EndBattle(boolean didWin, IBattleParticipant participant2);
	boolean getIsFaintedOrDead();
	void getNextPokemon();
	String getName();
	Attack getMove(IBattleParticipant participant2);
	void switchPokemon(IBattleParticipant participant2, int newPixelmonId);
	boolean checkPokemon();
	void setBattleController(BattleController bc);
	void updatePokemon();
	void update();
	EntityLiving getEntity();
	void updateOpponent(IBattleParticipant opponent);
}
