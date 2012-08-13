package pixelmon.battles.participants;

import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import pixelmon.battles.BattleController;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;

public class WildPixelmonParticipant implements IBattleParticipant {

	PixelmonEntityHelper pixelmon;

	public WildPixelmonParticipant(PixelmonEntityHelper pixelmon) {
		this.pixelmon = pixelmon;
	}

	@Override
	public PixelmonEntityHelper currentPokemon() {
		return pixelmon;
	}

	@Override
	public boolean hasMorePokemon() {
		return false;
	}

	@Override
	public boolean canGainXP() {
		return false;
	}

	@Override
	public void EndBattle(boolean didWin, IBattleParticipant foe) {
		pixelmon.EndBattle();
		if (!pixelmon.getIsDead() && !pixelmon.isFainted) {
			pixelmon.battleStats.clearBattleStats();
			pixelmon.setHealth(pixelmon.stats.HP);
		}
	}

	@Override
	public void getNextPokemon() {
		return;
	}

	@Override
	public boolean getIsFaintedOrDead() {
		return pixelmon.getIsDead() || pixelmon.isFainted;
	}

	boolean isWild = true;
	private BattleController bc;

	@Override
	public String getName() {
		return pixelmon.getName();
	}

	@Override
	public Attack getMove(IBattleParticipant participant2) {
		return Attack.getWhichMoveIsBest(pixelmon.moveset, participant2.currentPokemon().getType(), pixelmon, participant2.currentPokemon());
	}

	@Override
	public void switchPokemon(IBattleParticipant participant2, int newPixelmonId) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkPokemon() {
		if (pixelmon.moveset.size() == 0) {
			pixelmon.loadMoveset();
			if (pixelmon.moveset.size() == 0) {
				System.out.println("Couldn't load " + pixelmon.getName() + "'s moves");
				return false;
			}
		}
		return true;
	}

	@Override
	public void setBattleController(BattleController bc) {
		this.bc = bc;
	}

	@Override
	public void updatePokemon() {
	}
}
