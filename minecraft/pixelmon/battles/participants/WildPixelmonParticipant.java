package pixelmon.battles.participants;

import net.minecraft.entity.EntityLiving;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.controller.BattleController;
import pixelmon.config.PixelmonConfig;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class WildPixelmonParticipant extends BattleParticipant {

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
	public void StartBattle(BattleController bc, BattleParticipant opponent) {
		super.StartBattle(bc, opponent);
		for (int i = 0; i < 4; i++) {
			if (pixelmon.moveset.get(i) != null)
				pixelmon.moveset.get(i).setDisabled(false, pixelmon);
		}
	}
	
	@Override
	public void EndBattle() {
		pixelmon.EndBattle();
		if (currentPokemon().isFainted || currentPokemon().isDead)
			pixelmon.setDead();
		else {
			pixelmon.battleStats.clearBattleStats();
			pixelmon.status.clear();
			pixelmon.setEntityHealth(pixelmon.stats.HP);
		}
	}

	@Override
	public void getNextPokemon() {
		return;
	}

	@Override
	public boolean getIsFaintedOrDead() {
		return pixelmon.isDead || pixelmon.isFainted || pixelmon.getHealth() <= 0;
	}

	@Override
	public String getName() {
		return pixelmon.getName();
	}

	@Override
	public Attack getMove() {
		if (pixelmon.moveset.size() > 0)
			return Attack.getWhichMoveIsBest(pixelmon.moveset, opponent.currentPokemon().type, pixelmon, opponent.currentPokemon());
		bc.setFlee(pixelmon);
		return null;
	}

	@Override
	public void switchPokemon(int newPixelmonId) {

	}

	@Override
	public boolean checkPokemon() {
		if (pixelmon.moveset.size() == 0) {
			pixelmon.loadMoveset();
			if (pixelmon.moveset.size() == 0) {
				if (PixelmonConfig.printErrors)
					System.out.println("Couldn't load " + pixelmon.getName() + "'s moves");
				return false;
			}
		}
		return true;
	}

	@Override
	public void updatePokemon() {
	}

	@Override
	public EntityLiving getEntity() {
		return pixelmon;
	}

	@Override
	public void updateOpponent() {
	}

	@Override
	public String getFaintMessage() {
		return "The Wild " + currentPokemon().getName() + " fainted!";
	}

}
