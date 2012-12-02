package pixelmon.battles.participants;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.NBTTagCompound;
import pixelmon.battles.BattleController;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class WildPixelmonParticipant implements IBattleParticipant {

	EntityPixelmon pixelmon;

	public WildPixelmonParticipant(EntityPixelmon pixelmon) {
		this.pixelmon = pixelmon;
	}

	@Override
	public ParticipantType getType() {
		return ParticipantType.WildPokemon;
	}

	@Override
	public EntityPixelmon currentPokemon() {
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
	public void StartBattle(IBattleParticipant opponent) {
	}

	@Override
	public void EndBattle(boolean didWin, IBattleParticipant foe) {
		pixelmon.EndBattle();
		if (!didWin)
			pixelmon.setDead();
		else {
			pixelmon.battleStats.clearBattleStats();
			pixelmon.status.clear();
			pixelmon.setEntityHealth(pixelmon.stats.HP);
		}
	}

	@Override
	public void getNextPokemon(IBattleParticipant opponent) {
		return;
	}

	@Override
	public boolean getIsFaintedOrDead() {
		return pixelmon.isDead || pixelmon.isFainted || pixelmon.getHealth() <= 0;
	}

	boolean isWild = true;
	private BattleController bc;

	@Override
	public String getName() {
		return pixelmon.getName();
	}

	@Override
	public Attack getMove(IBattleParticipant participant2) {
		if (pixelmon.moveset.size() > 0)
			return Attack.getWhichMoveIsBest(pixelmon.moveset, participant2.currentPokemon().type, pixelmon, participant2.currentPokemon());
		bc.setFlee(pixelmon);
		return null;
	}

	@Override
	public void switchPokemon(IBattleParticipant participant2, int newPixelmonId) {

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

	@Override
	public void update() {
	}

	@Override
	public EntityLiving getEntity() {
		return pixelmon;
	}

	@Override
	public void updateOpponent(IBattleParticipant opponent) {
	}
}
