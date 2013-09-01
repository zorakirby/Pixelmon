package pixelmon.battles.attacks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import pixelmon.RandomHelper;
import pixelmon.battles.attacks.EffectBase.ApplyStage;
import pixelmon.battles.attacks.animations.IAttackAnimation;
import pixelmon.battles.attacks.specialAttacks.RemoveEffect;
import pixelmon.battles.attacks.specialAttacks.StatsEffect;
import pixelmon.battles.attacks.specialAttacks.attackModifiers.AttackModifierBase;
import pixelmon.battles.attacks.specialAttacks.attackModifiers.CriticalHit;
import pixelmon.battles.attacks.specialAttacks.attackModifiers.Flinch;
import pixelmon.battles.attacks.specialAttacks.basic.SpecialAttackBase;
import pixelmon.battles.attacks.specialAttacks.multiTurn.MultiTurnSpecialAttackBase;
import pixelmon.battles.attacks.specialAttacks.statusAppliers.StatusApplierBase;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusType;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.EnumUpdateType;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonConfig;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;
import pixelmon.items.heldItems.ChoiceItem;
import pixelmon.storage.PixelmonStorage;

public class Attack {
	public static final float EFFECTIVE_NORMAL = 1, EFFECTIVE_SUPER = 2, EFFECTIVE_MAX = 4, EFFECTIVE_NOT = 0.5F, EFFECTIVE_BARELY = 0.25F, EFFECTIVE_NONE = 0;
	public static final int TYPE_NORMAL = 0, TYPE_FIRE = 1, TYPE_WATER = 2, TYPE_ELECTRIC = 3, TYPE_GRASS = 4, TYPE_ICE = 5, TYPE_FIGHT = 6, TYPE_POISON = 7,
			TYPE_GROUND = 8, TYPE_FLYING = 9, TYPE_PSYCHIC = 10, TYPE_BUG = 11, TYPE_ROCK = 12, TYPE_GHOST = 13, TYPE_DRAGON = 14, TYPE_DARK = 15,
			TYPE_STEEL = 16;
	public static final int ATTACK_PHYSICAL = 0, ATTACK_SPECIAL = 1, ATTACK_STATUS = 2;

	private static AttackBase[] fullAttackList = new AttackBase[600];
	private boolean disabled = false;
	public AttackBase baseAttack;
	public int pp;
	public int ppBase;
	public boolean STAB;
	public int movePower;
	public int moveAccuracy;

	public Attack(int attackIndex, String moveName, ResultSet rs) throws SQLException {
		if (fullAttackList[attackIndex] == null) {
			AttackBase a = new AttackBase(attackIndex, moveName, rs);
			fullAttackList[attackIndex] = a;
			baseAttack = a;
		} else {
			baseAttack = fullAttackList[attackIndex];
		}

		this.pp = rs.getInt("PP");
		this.ppBase = pp;
	}

	public boolean flinched = false;

	public void use(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList, ArrayList<String> targetAttackList) {
		movePower = baseAttack.basePower;
		moveAccuracy = baseAttack.accuracy;
		target.hurtTime = 0;
		user.hurtTime = 0;
		boolean attackHandled = false, cantMiss = false;
		user.getLookHelper().setLookPositionWithEntity(target, 0, 0);
		double accuracy = ((double) moveAccuracy) * ((double) user.battleStats.getAccuracy()) / ((double) target.battleStats.getEvasion());
		double crit = calcCriticalHit(null);
		/* Check for Protect */
		for (int i = 0; i < target.status.size(); i++) {
			StatusBase e = target.status.get(i);
			try {
				if (e.stopsIncomingAttack(user, target, this))
					return;
			} catch (Exception exc) {
				if (PixelmonConfig.printErrors) {
					System.out.println("Error calculating stopsIncomingAttack for " + e.type.toString() + " for attack " + baseAttack.attackName);
					System.out.println(exc.getStackTrace());
				}
			}

		}
		for (int i = 0; i < user.status.size(); i++) {
			StatusBase e = user.status.get(i);
			try {
				if (!e.canAttackThisTurn(user, target))
					return;
			} catch (Exception exc) {
				if (PixelmonConfig.printErrors) {
					System.out.println("Error calculating canAttackThisTurn for " + e.type.toString() + " for attack " + baseAttack.attackName);
					System.out.println(exc.getStackTrace());
				}
			}
		}

		if (baseAttack.accuracy == -1 || cantMiss(user))
			cantMiss = true;
		for (int i = 0; i < baseAttack.effects.size(); i++) {
			EffectBase e = baseAttack.effects.get(i);
			try {
				if (e.hasSpecialAccuracyEffect())
					accuracy = e.getAccuracy(user, target);
			} catch (Exception exc) {
				if (PixelmonConfig.printErrors) {
					System.out.println("Error calculating hasSpecialAccuracyEffect for " + e.getClass().toString() + " for attack " + baseAttack.attackName);
					System.out.println(exc.getStackTrace());
				}
			}
		}

		if (cantMiss || RandomHelper.getRandomNumberBetween(0, 100) <= accuracy) {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(),
					user.getNickname() + " used " + baseAttack.attackName + " on " + target.getNickname() + "!");
			for (int j = 0; j < baseAttack.effects.size(); j++) {
				EffectBase e = baseAttack.effects.get(j);
				if (e instanceof StatsEffect) {
					try {
						e.ApplyEffect(this, crit, user, target, attackList, targetAttackList);
					} catch (Exception exc) {
						if (PixelmonConfig.printErrors) {
							System.out.println("Error in applyEffect for " + e.getClass().toString() + " for attack " + baseAttack.attackName);
							System.out.println(exc.getStackTrace());
						}
					}
				} else if (e instanceof StatusApplierBase) {
					if (target.status.size() > 0) {
						for (int i = 0; i < target.status.size(); i++) {
							StatusBase et = target.status.get(i);
							try {
								if (!et.stopsStatusChange())
									e.ApplyEffect(this, crit, user, target, attackList, targetAttackList);
							} catch (Exception exc) {
								if (PixelmonConfig.printErrors) {
									System.out.println("Error in applyEffect for " + e.getClass().toString() + " for attack " + baseAttack.attackName);
									System.out.println(exc.getStackTrace());
								}
							}
						}
					} else {
						try {
							e.ApplyEffect(this, crit, user, target, attackList, targetAttackList);
						} catch (Exception exc) {
							if (PixelmonConfig.printErrors) {
								System.out.println("Error in applyEffect for " + e.getClass().toString() + " for attack " + baseAttack.attackName);
								System.out.println(exc.getStackTrace());
							}
						}
					}
				}
				// if (e.effectType == EffectType.AttackModifier) {
				// }
			}
			for (int i = 0; i < baseAttack.effects.size(); i++) {
				EffectBase e = baseAttack.effects.get(i);
				try {
					if (e.applyStage == ApplyStage.During) {
						if (e instanceof AttackModifierBase) {
							if (e instanceof CriticalHit)
								crit = calcCriticalHit(e);
							else
								attackHandled = ((AttackModifierBase) e).ApplyEffect(user, target, this);
						} else if (e instanceof SpecialAttackBase)
							attackHandled = ((SpecialAttackBase) e).ApplyEffect(user, target, this, crit, attackList, targetAttackList);

						else if (e instanceof MultiTurnSpecialAttackBase)
							attackHandled = ((MultiTurnSpecialAttackBase) e).ApplyEffect(user, target, this, crit, attackList, targetAttackList);

					}
				} catch (Exception exc) {
					System.out.println("Error in applyEffect for " + e.getClass().toString() + " for attack " + baseAttack.attackName);
					System.out.println(exc.getStackTrace());
				}
			}

			if (!attackHandled) {
				int power = doDamageCalc(user, target, crit);
				if (baseAttack.attackCategory == ATTACK_STATUS)
					power = 0;
				else {
					target.attackEntityFrom(DamageSource.causeMobDamage(user), power);
				}

				doMove(user, target);

				String s = null;
				if (baseAttack.attackCategory != ATTACK_STATUS) {
					if (crit > 1)
						ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Critical Hit!");
					float effectiveness = EnumType.getTotalEffectiveness(target.type, baseAttack.attackType);
					if (effectiveness == EFFECTIVE_NONE)
						s = "It had no effect!";
					if (effectiveness == EFFECTIVE_NOT || effectiveness == EFFECTIVE_BARELY)
						s = "It wasn't very effective...";
					if (effectiveness == EFFECTIVE_SUPER || effectiveness == EFFECTIVE_MAX)
						s = "It's super effective!";
					if (s != null)
						ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), s);
				}
			}

			for (int i = 0; i < baseAttack.effects.size(); i++) {
				EffectBase e = baseAttack.effects.get(i);
				try {
					if (e.applyStage == ApplyStage.End) {
						if (e instanceof AttackModifierBase) {
							if (e instanceof Flinch)
								flinched = ((Flinch) e).ApplyEffect(user, target, this);
						} else if (e instanceof RemoveEffect)
							e.ApplyEffect(this, crit, user, target, attackList, targetAttackList);
					}
				} catch (Exception exc) {
					if (PixelmonConfig.printErrors) {
						System.out.println("Error in applyEffect for " + e.getClass().toString() + " for attack " + baseAttack.attackName);
						System.out.println(exc.getStackTrace());
					}
				}
			}
		} else {
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " tried to use " + baseAttack.attackName
					+ ", but it missed!");
			for (int i = 0; i < baseAttack.effects.size(); i++) {
				EffectBase e = baseAttack.effects.get(i);
				try {
					e.ApplyMissEffect(user, target);
				} catch (Exception exc) {
					if (PixelmonConfig.printErrors) {
						System.out.println("Error in applyMissEffect for " + e.getClass().toString() + " for attack " + baseAttack.attackName);
						System.out.println(exc.getStackTrace());
					}
				}
			}
		}
		if (user.getOwner() != null)
			user.update(EnumUpdateType.HP, EnumUpdateType.Moveset);
		if (target.getOwner() != null)
			target.update(EnumUpdateType.HP, EnumUpdateType.Moveset);
		if (user.getTrainer() != null)
			user.getTrainer().pokemonStorage.update(user, new EnumUpdateType[] { EnumUpdateType.Moveset, EnumUpdateType.HP });
		if (target.getTrainer() != null)
			target.getTrainer().pokemonStorage.update(target, new EnumUpdateType[] { EnumUpdateType.Moveset, EnumUpdateType.HP });;
		pp--;
		ItemHeld.useBattleItems(user, target);
		return;
	}

	public void doMove(EntityLiving user, EntityLiving target) {
		for (IAttackAnimation anim : baseAttack.animations)
			anim.doMove(user, target);
	}

	public int doDamageCalc(EntityPixelmon user, EntityPixelmon target, double crit) {
		double stab = 1;
		if (STAB)
			stab = 1.5;
		double type = EnumType.getTotalEffectiveness(target.type, baseAttack.attackType);
		double critical = crit;
		double rand = ((((double) RandomHelper.getRandomNumberBetween(1, 128)) % 16) + 85) * 0.01;
		double modifier = stab * type * critical; // Add in
													// Type/Weakness/Resistance
													// in again.
		double attack = 0, defense = 0, Level = (double) (user.getLvl().getLevel());
		if (baseAttack.attackCategory == ATTACK_PHYSICAL) {
			attack = ((double) user.stats.Attack) * ((double) user.battleStats.getAttackModifier()) * 0.01;
			defense = ((double) target.stats.Defence) * ((double) target.battleStats.getDefenceModifier()) * 0.01;
			if (ItemHeld.isItemOfType(user.getHeldItem(), EnumHeldItems.choiceItem)) {
				attack = ((ChoiceItem) user.getHeldItem().getItem()).affectAttack(attack);
			}
		} else if (baseAttack.attackCategory == ATTACK_SPECIAL) {
			attack = ((double) user.stats.SpecialAttack) * ((double) user.battleStats.getSpecialAttackModifier()) * 0.01;
			defense = ((double) target.stats.SpecialDefence) * ((double) target.battleStats.getSpecialDefenceModifier()) * 0.01;
			if (ItemHeld.isItemOfType(user.getHeldItem(), EnumHeldItems.choiceItem)) {
				attack = ((ChoiceItem) user.getHeldItem().getItem()).affectSpecialAttack(attack);
			}
		}

		// double Damage = ((2 * ((float) user.getLvl().getLevel()) + 10) / 250
		// * (attack / defense)
		// * baseAttack.basePower + 2) * modifier;

		double DmgRand = ((15 * rand) + 85) * 0.01;
		double DamageBase = (((((((2 * Level) / 5) + 2) * attack * movePower) * 0.02) / defense) + 2);

		// Split up so they can be monitored while debugging.
		double Damage = DamageBase * modifier * DmgRand * 0.85;

		for (int i = 0; i < target.status.size(); i++) {
			StatusBase e = target.status.get(i);
			try {
				Damage = e.adjustDamage(this, Damage, user, target, crit);
			} catch (Exception exc) {
				if (PixelmonConfig.printErrors) {
					System.out.println("Error in adjustDamage for " + e.getClass().toString() + " for attack " + baseAttack.attackName);
					System.out.println(exc.getStackTrace());
				}
			}
		}
		return (int) (Damage);
	}

	public void setDisabled(boolean value, EntityPixelmon pixelmon) {
		disabled = value;
		if (pixelmon.battleController != null && pixelmon.getOwner() != null) {
			((EntityPlayerMP) pixelmon.getOwner()).playerNetServerHandler.sendPacketToPlayer(new PixelmonDataPacket(pixelmon, EnumPackets.UpdateStorage)
					.getPacket());
		}
	}

	public boolean getDisabled() {
		return disabled;
	}

	public static double calcCriticalHit(EffectBase e) {
		int critStage = 1;
		int percent = 6;
		if (e != null) {
			if (e instanceof CriticalHit)
				critStage += e.value;

			if (critStage == 1)
				percent = 6;
			else if (critStage == 2)
				percent = 13;
			else if (critStage == 3)
				percent = 25;
			else if (critStage == 4)
				percent = 33;
			else if (critStage == 5)
				percent = 50;
		}
		if (RandomHelper.getRandomNumberBetween(0, 100) < percent)
			return 2;

		return 1;
	}

	public boolean canHit(EntityPixelmon pixelmon1, EntityPixelmon pixelmon2) {
		if (pixelmon2 == null) {
			return false;
		}

		if (pixelmon1.isDead || pixelmon1.isFainted || pixelmon2.isDead || pixelmon2.isFainted) {
			return false;
		}

		return true;
	}

	public static boolean canMovesHit(EntityPixelmon entity, EntityPixelmon target) {
		boolean[] b = new boolean[4];
		int i1 = 0;
		b[0] = b[1] = b[2] = b[3] = true;
		for (int i = 0; i < entity.getMoveset().size(); i++) {
			Attack a = entity.getMoveset().get(i);
			if (!a.canHit(entity, target)) {
				b[i1] = false;
			}
			i1++;
		}
		if (!(b[0] && b[1] && b[2] && b[3]))
			return false;

		return true;
	}

	public static Attack getWhichMoveIsBest(List<Attack> moves, ArrayList<EnumType> types, EntityPixelmon releasedPokemon, EntityPixelmon entityPixelmon) {
		List<Attack> usableMoves = new ArrayList<Attack>();
		for (Attack a : moves) {
			if (!a.getDisabled() && a.pp > 0)
				usableMoves.add(a);
		}
		if (usableMoves.size() == 0) {
			return DatabaseMoves.getAttack("Struggle");
		} else {
			int i1 = 0;
			i1 = RandomHelper.getRandomNumberBetween(0, usableMoves.size() - 1);
			return usableMoves.get(i1);
		}
	}

	public void setSTAB(boolean STAB) {
		this.STAB = STAB;
	}

	public static int getAttackCategory(String categoryString) {
		if (categoryString.equalsIgnoreCase("Special"))
			return ATTACK_SPECIAL;
		else if (categoryString.equalsIgnoreCase("Physical"))
			return ATTACK_PHYSICAL;
		else if (categoryString.equalsIgnoreCase("Status"))
			return ATTACK_STATUS;
		else {
			if (PixelmonConfig.printErrors) {
				System.out.println("Unknown Attack Category: " + categoryString);
			}
			return -1;
		}
	}

	public boolean doesPersist(EntityPixelmon entityPixelmon) {
		if (baseAttack.attackName.equalsIgnoreCase("Fly") || baseAttack.attackName.equalsIgnoreCase("Bounce")) {
			for (int i = 0; i < entityPixelmon.status.size(); i++) {
				StatusBase s = entityPixelmon.status.get(i);
				if (s.type == StatusType.Flying)
					return true;
			}
			return false;
		}
		for (int i = 0; i < baseAttack.effects.size(); i++) {
			EffectBase e = baseAttack.effects.get(i);
			try {
				if (e.doesPersist(entityPixelmon))
					return true;
			} catch (Exception exc) {
				if (PixelmonConfig.printErrors) {
					System.out.println("Error in doesPersist for " + e.getClass().toString() + " for attack " + baseAttack.attackName);
					System.out.println(exc.getStackTrace());
				}
			}

		}
		return false;
	}

	public boolean cantMiss(EntityPixelmon user) {
		for (int i = 0; i < baseAttack.effects.size(); i++) {
			EffectBase e = baseAttack.effects.get(i);
			try {
				if (e instanceof MultiTurnSpecialAttackBase)
					if (((MultiTurnSpecialAttackBase) e).cantMiss(user))
						return true;
			} catch (Exception exc) {
				if (PixelmonConfig.printErrors) {
					System.out.println("Error in cantMiss for " + e.getClass().toString() + " for attack " + baseAttack.attackName);
					System.out.println(exc.getStackTrace());
				}
			}
		}
		return false;
	}
}
