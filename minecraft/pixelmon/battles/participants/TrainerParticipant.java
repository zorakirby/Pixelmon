package pixelmon.battles.participants;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.controller.BattleController;
import pixelmon.comm.ChatHandler;
import pixelmon.config.PixelmonConfig;
import pixelmon.entities.npcs.EntityTrainer;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class TrainerParticipant extends BattleParticipant {

	public EntityTrainer trainer;

	public TrainerParticipant(EntityTrainer trainer, EntityPlayer opponent) {
		this.trainer = trainer;
		trainer.releasePokemon();
		trainer.startBattle(opponent);
	}

	@Override
	public ParticipantType getType() {
		return ParticipantType.Trainer;
	}

	@Override
	public EntityPixelmon currentPokemon() {
		return trainer.releasedPokemon;
	}

	@Override
	public boolean hasMorePokemon() {
		return trainer.hasAblePokemon();
	}

	@Override
	public boolean canGainXP() {
		return false;
	}

	@Override
	public void EndBattle() {
		if (hasMorePokemon()) {
			trainer.releasedPokemon.battleStats.clearBattleStats();
			trainer.releasedPokemon.EndBattle();
			trainer.healAllPokemon();
			this.trainer.setAttackTarget(null);
			trainer.releasedPokemon.status.clear();
			trainer.releasedPokemon.setDead();
			trainer.winBattle(opponent.currentPokemon().getOwner());
		} else {
			trainer.loseBattle(opponent.currentPokemon().getOwner());
			if (trainer.releasedPokemon != null)
				trainer.releasedPokemon.EndBattle();
			trainer.setDead();
		}
		trainer.releasedPokemon = null;
	}

	@Override
	public void getNextPokemon() {
		bc.SwitchPokemon(currentPokemon(), trainer.getNextPokemonID());
	}

	@Override
	public boolean getIsFaintedOrDead() {
		return trainer.releasedPokemon == null || trainer.releasedPokemon.isDead || trainer.releasedPokemon.isFainted
				|| trainer.releasedPokemon.func_110143_aJ() <= 0;
	}

	@Override
	public String getName() {
		return trainer.info.name;
	}

	@Override
	public Attack getMove() {
		return Attack.getWhichMoveIsBest(trainer.releasedPokemon.getMoveset(), opponent.currentPokemon().type, trainer.releasedPokemon, opponent.currentPokemon());
	}

	@Override
	public void switchPokemon(int newPixelmonId) {
		currentPokemon().battleStats.clearBattleStats();
		if (!currentPokemon().isFainted) {
			bc.sendToOtherParticipants(this, trainer.info.name + " withdrew " + currentPokemon().getNickname() + "!");
		}
		currentPokemon().catchInPokeball();
		trainer.pokemonStorage.getNBT(currentPokemon().getPokemonId()).setBoolean("IsFainted", true);

		trainer.releasePokemon();

		bc.sendToOtherParticipants(this, trainer.info.name + " sent out " + currentPokemon().getNickname() + "!");

		for (BattleParticipant p : bc.participants)
			p.updateOpponent();
	}

	@Override
	public boolean checkPokemon() {
		for (NBTTagCompound n : trainer.pokemonStorage.partyPokemon) {
			if (n != null && n.getInteger("PixelmonNumberMoves") == 0) {
				if (PixelmonConfig.printErrors) {
					System.out.println("Couldn't load pokemon's moves");
				}
				return false;
			}
		}
		return true;
	}

	@Override
	public void updatePokemon() {
		if (trainer != null && currentPokemon() != null)
			trainer.pokemonStorage.getNBT(currentPokemon().getPokemonId()).setBoolean("IsFainted", true);
	}

	@Override
	public EntityLiving getEntity() {
		return trainer;
	}

	@Override
	public void updateOpponent() {
	}

	@Override
	public String getFaintMessage() {
		return trainer.info.name + "'s " + currentPokemon().getNickname() + " fainted!";
	}

}
