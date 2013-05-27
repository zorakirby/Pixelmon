package pixelmon.battles.controller;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.battles.participants.ParticipantType;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonItemsHeld;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.stats.EVsStore;
import pixelmon.entities.pixelmon.stats.StatsType;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;
import pixelmon.items.heldItems.EVAdjusting;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import pixelmon.storage.PlayerStorage;

/**
 * Experience: Methods for awarding EXP to a team's pokemon. Class made final
 * since it has no instance variables/methods.
 */
public final class Experience {

	/**
	 * Award EXP to the pokemon of battle participants after an opposing pokemon
	 * faints.
	 * 
	 * @param participants
	 *            list of team owners to award EXP to
	 * @param losingTeamOwner
	 *            owner of faintedPokemon, will be a player, an NPC, or wild (no
	 *            owner)
	 * @param faintedPokemon
	 *            defeated pokemon to base EXP reward on
	 */
	public static void awardExp(ArrayList<BattleParticipant> participants, BattleParticipant losingTeamOwner, EntityPixelmon faintedPokemon) {
		if (!PixelmonConfig.allowPVPExperience) {
			boolean allPlayers = true;
			for (BattleParticipant p : participants) {
				if (p.getType() != ParticipantType.Player) {
					allPlayers = false;
				}
			}
			if (allPlayers) {
				// Award no EXP to anyone if all participants are players with
				// PVP EXP gain configured off.
				return;
			}
		}
		try {
			for (BattleParticipant teamOwner : participants) {
				if (teamOwner.team != losingTeamOwner.team && teamOwner instanceof PlayerParticipant) {
					// Begin EXP-awarding logic for a team owner if it is a
					// player and if
					// faintedPokemon is an enemy of the player.
					final PlayerParticipant player = (PlayerParticipant) teamOwner;
					final EntityPixelmon activePokemon = player.currentPokemon();
					final ArrayList<Integer> enemyIdList = player.attackersList;

					PlayerStorage storage = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) activePokemon.getOwner());

					ArrayList<Integer> doneUsers = new ArrayList<Integer>();
					if (!enemyIdList.contains(activePokemon.getPokemonId())) {
						enemyIdList.add(activePokemon.getPokemonId());
					}
					for (int i = 0; i < enemyIdList.size(); i++) {
						if (activePokemon.getOwner() != null) {
							if (storage.isFainted(enemyIdList.get(i))) {
								enemyIdList.remove(i);
								i--;
							}
						}
					}
					int expShareCount = 0;
					for (NBTTagCompound nbt : storage.partyPokemon) {
						// Iterating through the player's party/belt.
						if (nbt != null) {
							if (nbt.hasKey("HeldItem")) {
								int itemId = nbt.getInteger("HeldItem");
								if (itemId != -1) {
									if (PixelmonItemsHeld.getHeldItem(itemId) != null
											&& PixelmonItemsHeld.getHeldItem(itemId).getHeldItemType() == EnumHeldItems.expShare
											&& !nbt.getBoolean("IsFainted") && nbt.getShort("Health") > 0) {
										expShareCount++;
									}
								}
							}
						}
					}

					for (NBTTagCompound nbt : storage.partyPokemon) {
						// Iterating through the player's party/belt.
						if (nbt != null) {
							if (nbt.hasKey("HeldItem")) {
								int itemId = nbt.getInteger("HeldItem");
								if (itemId != -1) {
									if (PixelmonItemsHeld.getHeldItem(itemId).getHeldItemType() == EnumHeldItems.expShare && !nbt.getBoolean("IsFainted")
											&& nbt.getShort("Health") > 0) {
										calcExp(storage, activePokemon, faintedPokemon, nbt.getInteger("pixelmonID"), 0.5 / (double) expShareCount);
									}
								}
							}
						}
					}

					for (int userIndex : enemyIdList) {
						if (!doneUsers.contains(userIndex)) {
							calcExp(storage, activePokemon, faintedPokemon, userIndex, expShareCount > 0 ? 0.5 : 1);
							doneUsers.add(userIndex);
						}
					}
				}
			}
		} catch (PlayerNotLoadedException e) {
		}
	}

	/**
	 * Calculate EXP and EV gains of a pokemon from base stats of the pokemon
	 * and its fainted opponent. Note: isSharedExp was unused.
	 * 
	 * @param storage
	 *            player pokemon belt storage
	 * @param battlingPokemon
	 *            the sent-out, battling pokemon of the player
	 * @param faintedPokemon
	 *            defeated pokemon to base EXP reward on
	 * @param expReceiverIndex
	 *            integer index of the EXP-receiving pokemon; will be the
	 *            battling pokemon's in one case
	 * @param scaleFactor
	 *            total EXP multiplier
	 */
	private static void calcExp(PlayerStorage storage, EntityPixelmon battlingPokemon, EntityPixelmon faintedPokemon, int expReceiverIndex, double scaleFactor) {
		NBTTagCompound expReceiver = null;
		expReceiver = storage.getNBT(expReceiverIndex);
		if (expReceiver == null) {
			return;
		}
		// Rename this variable. Don't know what this is.
		double a = 1.5;

		// Traded.
		double t = 1;

		// Base EXP of fainted pokemon.
		double baseExp = faintedPokemon.baseStats.baseExp;

		// 150% multiplier if holding a Lucky Egg.
		// Note: Multiplier has always been applied to party pokemon as long as
		// the battling pokemon is holding it.
		double eggMultiplier = ItemHeld.isItemOfType(battlingPokemon.getHeldItem(), EnumHeldItems.luckyEgg) ? 1.5 : 1;

		// Level of fainted pokemon.
		double faintedLevel = faintedPokemon.getLvl().getLevel();

		// Level of EXP-receiving pokemon.
		double Lp = expReceiver.getInteger("Level");

		// Don't know what this is.
		double s = 1;

		// Don't know what this is.
		double power = 1;

		double exp = ((a * baseExp * faintedLevel) / (5 * s) * (Math.pow(2 * faintedLevel + 10, 2.5) / Math.pow(faintedLevel + Lp + 10, 2.5)) + 1) * t
				* eggMultiplier * power;
		exp = exp * scaleFactor;
		if (expReceiverIndex == battlingPokemon.getPokemonId()) {
			// Begin EXP award to the currently battling pokemon.
			battlingPokemon.getLvl().awardEXP((int) exp);
			if (battlingPokemon.getLvl().canLevelUp()) {
				EVsStore evStore = faintedPokemon.baseStats.evGain.cloneEVs();
				if (ItemHeld.isItemOfType(battlingPokemon.getHeldItem(), EnumHeldItems.evAdjusting)) {
					EVAdjusting item = (EVAdjusting) battlingPokemon.getHeldItem().getItem();
					if (item.type.statAffected == StatsType.None) {
						evStore.doubleValues();
					} else {
						evStore.addEVs(4, item.type.statAffected);
					}
				}
			}
			battlingPokemon.stats.EVs.gainEV(evStore);
			storage.updateNBT(battlingPokemon);
		} else {
			// Begin EXP award to a stored party pokemon.
			EntityPixelmon partyPokemon;
			boolean wasAlreadyOut = false;
			if (storage.EntityAlreadyExists(expReceiverIndex, battlingPokemon.getOwner().worldObj)) {
				partyPokemon = storage.getAlreadyExists(expReceiverIndex, battlingPokemon.getOwner().worldObj);
				wasAlreadyOut = true;
			} else {
				// Sending out stored pokemon so player may see it for potential
				// levelling up.
				partyPokemon = storage.sendOut(expReceiverIndex, battlingPokemon.getOwner().worldObj);
			}
			partyPokemon.getLvl().awardEXP((int) exp);
			EVsStore evStore = faintedPokemon.baseStats.evGain.cloneEVs();
			if (ItemHeld.isItemOfType(partyPokemon.getHeldItem(), EnumHeldItems.evAdjusting)) {
				EVAdjusting item = (EVAdjusting) partyPokemon.getHeldItem().getItem();
				if (item.type.statAffected == StatsType.None) {
					evStore.doubleValues();
				} else {
					evStore.addEVs(4, item.type.statAffected);
				}
			}
			partyPokemon.stats.EVs.gainEV(evStore);
			if (!wasAlreadyOut) {
				storage.retrieve(partyPokemon);
			}
		}
	}
}
