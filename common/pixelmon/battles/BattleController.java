package pixelmon.battles;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.network.Player;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectType;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.battles.attacks.attackEffects.EffectBase.ApplyStage;
import pixelmon.battles.attacks.attackModifiers.AttackModifierBase;
import pixelmon.battles.attacks.attackModifiers.AttackModifierType;
import pixelmon.battles.attacks.attackModifiers.PriorityAttackModifier;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.participants.IBattleParticipant;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.comm.ChatHandler;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.stats.BattleStats;
import pixelmon.entities.trainers.EntityTrainer;
import pixelmon.enums.EnumGui;
import pixelmon.enums.EnumHeldItems;
import pixelmon.items.ItemHeld;
import pixelmon.items.PixelmonItem;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PokeballManager;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ItemStack;

import net.minecraft.src.NBTTagCompound;

public class BattleController {

	public int battleIndex;

	public IBattleParticipant participant1;
	public IBattleParticipant participant2;
	private int battleTicks = 0;
	private ArrayList<String> attackList1 = new ArrayList<String>();
	private ArrayList<String> attackList2 = new ArrayList<String>();

	private ArrayList<Integer> attackersList1 = new ArrayList<Integer>();
	private ArrayList<Integer> attackersList2 = new ArrayList<Integer>();

	public ArrayList<StatusEffectBase> battleStatusList = new ArrayList<StatusEffectBase>();
	private boolean battleEnded = false;

	public BattleController(IBattleParticipant participant1, IBattleParticipant participant2) throws Exception {
		this.participant1 = participant1;
		this.participant2 = participant2;
		if (!participant1.checkPokemon())
			throw new Exception("Battle Could not start!");
		if (!participant2.checkPokemon())
			throw new Exception("Battle Could not start!");
		BattleRegistry.registerBattle(this);
		participant1.setBattleController(this);
		participant2.setBattleController(this);
		participant1.currentPokemon().battleController = this;
		participant2.currentPokemon().battleController = this;
		if (participant1.canGainXP())
			attackersList1.add(participant1.currentPokemon().getPokemonId());
		if (participant2.canGainXP())
			attackersList2.add(participant2.currentPokemon().getPokemonId());
		setPositions();
	}

	private void setPositions() {
	}

	enum MoveStage {
		PickAttacks, FirstMove, SecondMove
	};

	private MoveStage moveStage = MoveStage.PickAttacks;
	private boolean pixelmon1MovesFirst = true;
	private Attack[] attacks = new Attack[2];

	public void endBattle(boolean did1Win) {
		battleEnded = true;
		participant1.EndBattle(did1Win, participant2);
		participant2.EndBattle(!did1Win, participant1);
		BattleRegistry.deRegisterBattle(this);
	}

	public void update() {
		if (isWaiting() || isCapturing)
			return;
		int tickTop;
		if (moveStage == MoveStage.PickAttacks)
			tickTop = 20;
		else
			tickTop = 50;
		if (battleTicks++ > tickTop) {
			if (moveStage == MoveStage.PickAttacks) { // Pick Moves
				if (attacks[0] != null)
					attacks[0].flinched = false;
				if (attacks[1] != null)
					attacks[1].flinched = false;
				// moveToPositions();
				pickMoves();
				checkMoveSpeed();
				moveStage = MoveStage.FirstMove;
			} else if (moveStage == MoveStage.FirstMove) { // First Move
				if (pixelmon1MovesFirst) {
					if (pixelmon1CanAttack)
						takeTurn(participant1, participant2, attacks[0]);
				} else {
					if (pixelmon2CanAttack)
						takeTurn(participant2, participant1, attacks[1]);
				}

				checkAndReplaceFaintedPokemon(participant1, participant2);
				if (!battleEnded)
					checkAndReplaceFaintedPokemon(participant2, participant1);
				moveStage = MoveStage.SecondMove;
			} else if (moveStage == MoveStage.SecondMove) { // Second Move
				if (attacks[0] == null || (attacks[0] != null && !attacks[0].flinched)) {
					if (pixelmon1MovesFirst) {
						if (pixelmon2CanAttack)
							takeTurn(participant2, participant1, attacks[1]);
					} else {
						if (pixelmon1CanAttack)
							takeTurn(participant1, participant2, attacks[0]);
					}
				}

				for (int i = 0; i < participant1.currentPokemon().status.size(); i++) {
					StatusEffectBase s = participant1.currentPokemon().status.get(i);
					s.applyRepeatedEffect(participant1.currentPokemon(), participant2.currentPokemon());
					s.turnTick(participant1.currentPokemon(), participant2.currentPokemon()); // Update
																								// Status's
				}
				for (int i = 0; i < participant2.currentPokemon().status.size(); i++) {
					StatusEffectBase s = participant2.currentPokemon().status.get(i);
					s.applyRepeatedEffect(participant2.currentPokemon(), participant1.currentPokemon());
					s.turnTick(participant2.currentPokemon(), participant1.currentPokemon());
				}
				for (int i = 0; i < battleStatusList.size(); i++) {
					battleStatusList.get(i).turnTick(participant1.currentPokemon(), participant2.currentPokemon());
				}

				checkAndReplaceFaintedPokemon(participant1, participant2);
				if (!battleEnded)
					checkAndReplaceFaintedPokemon(participant2, participant1);
				moveStage = MoveStage.PickAttacks;
			}
			battleTicks = 0;
		}
	}

	private void checkAndReplaceFaintedPokemon(IBattleParticipant participant, IBattleParticipant foe) {
		if (participant.getIsFaintedOrDead()) {
			String name = participant.currentPokemon().getNickname().equals("") ? participant.currentPokemon().getName() : participant.currentPokemon().getNickname();
			if (participant == participant1) {
				if (participant1.isWild)
					ChatHandler.sendChat(participant2.currentPokemon().getOwner(), "The wild " + participant1.currentPokemon().getName() + " fainted!");
				if (participant1.currentPokemon().getOwner() != null || participant2.currentPokemon().getOwner() != null) {
					awardExp(attackersList2, participant2.currentPokemon(), participant1.currentPokemon());
					ChatHandler.sendChat(participant.currentPokemon().getOwner(), "Your " + name + " fainted!");
					ChatHandler.sendChat(foe.currentPokemon().getOwner(), name + " fainted!");
				}
			} else if (participant == participant2) {
				if (participant2.isWild)
					ChatHandler.sendChat(participant1.currentPokemon().getOwner(), "The wild " + participant2.currentPokemon().getName() + " fainted!");
				if (participant1.currentPokemon().getOwner() != null || participant2.currentPokemon().getOwner() != null) {
					awardExp(attackersList1, participant1.currentPokemon(), participant2.currentPokemon());
					ChatHandler.sendChat(participant.currentPokemon().getOwner(), "Your " + name + " fainted!");
					ChatHandler.sendChat(foe.currentPokemon().getOwner(), name + " fainted!");
				}
			}
			participant.currentPokemon().setEntityHealth(0);
			participant.currentPokemon().setDead();

			if (participant.hasMorePokemon()) {
				participant.getNextPokemon();
				participant.currentPokemon().battleController = this;
				name = participant.currentPokemon().getNickname().equals("") ? participant.currentPokemon().getName() : participant.currentPokemon().getNickname();
				ChatHandler.sendChat(participant.currentPokemon().getOwner(), foe.currentPokemon().getOwner(), participant.getName() + " sent out " + name + "!");
				attackersList1.clear();
				attackersList2.clear();
				if (participant == participant1) {
					if (participant.canGainXP())
						attackersList1.add(participant.currentPokemon().getPokemonId());
					if (foe.canGainXP())
						attackersList2.add(foe.currentPokemon().getPokemonId());
					pixelmon1CanAttack = false;
				} else if (participant == participant2) {
					if (participant.canGainXP())
						attackersList2.add(participant.currentPokemon().getPokemonId());
					if (foe.canGainXP())
						attackersList1.add(foe.currentPokemon().getPokemonId());
					pixelmon2CanAttack = false;
				}
			} else {
				endBattle(foe == participant1);
			}
		}
	}

	private void checkMoveSpeed() {
		int priority1 = 0, priority2 = 0;
		if (attacks[0] != null) {
			for (EffectBase e : attacks[0].effects)
				if (e.applyStage == ApplyStage.Priority)
					priority1 = ((PriorityAttackModifier) e).value;
		}
		if (attacks[1] != null) {
			for (EffectBase e : attacks[1].effects)
				if (e.applyStage == ApplyStage.Priority)
					priority2 = ((PriorityAttackModifier) e).value;
		}

		if (priority1 > priority2)
			pixelmon1MovesFirst = true;
		else if (priority2 > priority1)
			pixelmon1MovesFirst = false;
		else {
			for (StatusEffectBase e : battleStatusList) {
				if (e.applyStage == ApplyStage.Priority) {
					pixelmon1MovesFirst = e.pokemon1MovesFirst(participant1.currentPokemon(), participant2.currentPokemon());
					return;
				}
			}
			if (participant1.currentPokemon().stats.Speed * participant1.currentPokemon().battleStats.SpeedModifier > participant2.currentPokemon().stats.Speed
					* participant2.currentPokemon().battleStats.SpeedModifier)
				pixelmon1MovesFirst = true;
			else if (participant2.currentPokemon().stats.Speed * participant2.currentPokemon().battleStats.SpeedModifier > participant1.currentPokemon().stats.Speed
					* participant1.currentPokemon().battleStats.SpeedModifier)
				pixelmon1MovesFirst = false;
			else {
				if (RandomHelper.getRandomNumberBetween(0, 2) >= 1)
					pixelmon1MovesFirst = false;
				else
					pixelmon1MovesFirst = true;
			}
		}
	}

	boolean pixelmon1CanAttack = true;
	boolean pixelmon2CanAttack = true;
	boolean pixelmon1WillTryFlee = false;
	boolean pixelmon2WillTryFlee = false;
	int player1EscapeAttempts = 0;
	int player2EscapeAttempts = 0;
	boolean pixelmon1IsSwitching = false;
	boolean pixelmon2IsSwitching = false;
	ItemStack pixelmon1WillUseItemInStack = null;
	ItemStack pixelmon2WillUseItemInStack = null;
	int pixelmon1WillUseItemInStackInfo = 0;
	int pixelmon2WillUseItemInStackInfo = 0;

	private void pickMoves() {
		pixelmon1CanAttack = true;
		pixelmon2CanAttack = true;
		pixelmon1WillTryFlee = false;
		pixelmon2WillTryFlee = false;
		pixelmon1IsSwitching = false;
		pixelmon2IsSwitching = false;
		for (int i = 0; i < participant1.currentPokemon().status.size(); i++) {
			StatusEffectBase e = participant1.currentPokemon().status.get(i);
			if (!e.canAttackThisTurn(participant1.currentPokemon(), participant2.currentPokemon())) {
				pixelmon1CanAttack = false;
				attackList1.add("None");
				break;
			}
		}
		for (int i = 0; i < participant2.currentPokemon().status.size(); i++) {
			StatusEffectBase e = participant2.currentPokemon().status.get(i);
			if (!e.canAttackThisTurn(participant2.currentPokemon(), participant1.currentPokemon())) {
				pixelmon2CanAttack = false;
				attackList2.add("None");
				break;
			}
		}
		if (pixelmon1CanAttack && (attacks[0] == null || !attacks[0].doesPersist(participant1.currentPokemon()))) {
			attacks[0] = participant1.getMove(participant2);
			if (attacks[0] != null)
				attackList1.add(attacks[0].attackName);
		}
		if (pixelmon2CanAttack && (attacks[1] == null || !attacks[1].doesPersist(participant2.currentPokemon()))) {
			attacks[1] = participant2.getMove(participant1);
			if (attacks[1] != null)
				attackList2.add(attacks[1].attackName);
		}
	}

	public void setAttack(EntityPixelmon mypixelmon, Attack a) {
		if (mypixelmon == participant1.currentPokemon()) {
			attacks[0] = a;
			attackList1.add(a.attackName);
			participant1Wait = false;
		} else {
			attacks[1] = a;
			attackList2.add(a.attackName);
			participant2Wait = false;
		}
	}

	private void takeTurn(IBattleParticipant user, IBattleParticipant target, Attack a) {
		boolean isP1 = user == participant1;
		boolean isP2 = user == participant2;
		if ((isP1 && pixelmon1WillTryFlee) || (isP2 && pixelmon2WillTryFlee))
			calculateEscape(user.currentPokemon(), target.currentPokemon());
		else if (pixelmon1IsSwitching && isP1) {
			pixelmon1IsSwitching = false;
		} else if (pixelmon2IsSwitching && isP2) {
			pixelmon2IsSwitching = false;
		} else if ((pixelmon1WillUseItemInStack != null && isP1) || (pixelmon2WillUseItemInStack != null && isP2)) {
			useItem(isP1);
		} else {
			ArrayList al = (isP1 ? attackList1 : attackList2);
			ArrayList al2 = (isP1 ? attackList2 : attackList1);
			a.use(user.currentPokemon(), target.currentPokemon(), al, al2);
		}
	}

	private void calculateEscape(EntityPixelmon user, EntityPixelmon target) {
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " tries to run away");
		float A = ((float) user.stats.Speed) * ((float) user.battleStats.SpeedModifier) / 100;
		float B = ((float) target.stats.Speed) * ((float) target.battleStats.SpeedModifier) / 100;
		if (B > 255)
			B = 255;
		float C = player1EscapeAttempts++;
		float F = A * 32 / B + 30 * C;
		if (F > 255 || new Random().nextInt(255) < F) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " escaped!");
			endBattle(target == participant1.currentPokemon());
		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " couldn't escape!");
	}

	private void awardExp(ArrayList<Integer> users, EntityPixelmon entityPixelmon, EntityPixelmon target) {
		ArrayList<Integer> doneUsers = new ArrayList<Integer>();
		if (!users.contains(entityPixelmon.getPokemonId()))
			users.add(entityPixelmon.getPokemonId());
		for (int i = 0; i < users.size(); i++) {
			if (entityPixelmon.getOwner() != null) {
				if (PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entityPixelmon.getOwner()).isFainted(users.get(i))) {
					users.remove(i);
					i--;
				}
			}
		}
		for (int userIndex : users) {
			if (!doneUsers.contains(userIndex)) {
				double a, t, b, e, L, Lp, s, p;
				NBTTagCompound user = null;
				if (entityPixelmon.getOwner() != null)
					user = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entityPixelmon.getOwner()).getNBT(userIndex);
				if (user != null)
					a = 1.5;
				else
					return;
				t = 1;// traded
				b = target.baseStats.BaseExp;
				e = ItemHeld.isItemOfType(entityPixelmon.getHeldItem(), EnumHeldItems.luckyEgg) ? 1.5 : 1;
				L = target.getLvl().getLevel();
				Lp = user.getInteger("Level");
				s = users.size();
				p = 1;

				double exp = ((a * b * L) / (5 * s) * (Math.pow(2 * L + 10, 2.5) / Math.pow(L + Lp + 10, 2.5)) + 1) * t * e * p;
				if (userIndex == entityPixelmon.getPokemonId()) {
					entityPixelmon.getLvl().awardEXP((int) exp);
					if (entityPixelmon.getLvl().canLevelUp())
						entityPixelmon.stats.EVs.gainEV(target.baseStats.evGain);
				} else {
					EntityPixelmon pix = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entityPixelmon.getOwner()).sendOut(userIndex, entityPixelmon.getOwner().worldObj);
					pix.getLvl().awardEXP((int) exp);
					pix.stats.EVs.gainEV(target.baseStats.evGain);
					PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entityPixelmon.getOwner()).retrieve(pix);
				}
				doneUsers.add(userIndex);
			}
		}
	}

	public void setFlee(EntityPixelmon mypixelmon) {
		if (mypixelmon == participant1.currentPokemon()) {
			pixelmon1WillTryFlee = true;
			participant1Wait = false;
		} else if (mypixelmon == participant2.currentPokemon()) {
			pixelmon2WillTryFlee = true;
			participant2Wait = false;
		}
	}

	public void setUseItem(Player user, ItemStack usedStack, int additionalInfo) {
		if (participant1 instanceof PlayerParticipant && ((PlayerParticipant) participant1).player == (EntityPlayerMP) user) {
			pixelmon1WillUseItemInStack = usedStack;
			pixelmon1WillUseItemInStackInfo = additionalInfo;
			participant1Wait = false;
		} else {
			pixelmon2WillUseItemInStack = usedStack;
			pixelmon2WillUseItemInStackInfo = additionalInfo;
			participant2Wait = false;
		}
	}

	public void SwitchPokemon(EntityPixelmon currentPixelmon, int newPixelmonId) {
		boolean wasInitiator = false;
		if (participant1.currentPokemon() == currentPixelmon) {
			if (participant1.currentPokemon().wasBattleInitiator)
				wasInitiator = true;
			participant1.switchPokemon(participant2, newPixelmonId);
			participant1.currentPokemon().battleController = this;
			participant1.currentPokemon().wasBattleInitiator = wasInitiator;
			attackersList1.add(participant1.currentPokemon().getPokemonId());
			attackersList2.clear();
			attackersList2.add(participant2.currentPokemon().getPokemonId());
			pixelmon1IsSwitching = true;
			participant1Wait = false;
		} else {
			if (participant2.currentPokemon().wasBattleInitiator)
				wasInitiator = true;
			participant2.switchPokemon(participant1, newPixelmonId);
			participant2.currentPokemon().battleController = this;
			participant2.currentPokemon().wasBattleInitiator = wasInitiator;
			attackersList2.add(participant2.currentPokemon().getPokemonId());
			attackersList1.clear();
			attackersList1.add(participant1.currentPokemon().getPokemonId());
			pixelmon2IsSwitching = true;
			participant2Wait = false;
		}
	}

	public void useItem(boolean isP1) {
		EntityPixelmon userPokemon = null, targetPokemon = null;
		PixelmonItem item = null;
		EntityPlayer user = null;
		ItemStack usedStack = null;
		int additionalInfo = 0;
		if (isP1) {
			userPokemon = participant1.currentPokemon();
			targetPokemon = participant2.currentPokemon();
			usedStack = pixelmon1WillUseItemInStack;
			additionalInfo = pixelmon1WillUseItemInStackInfo;
			user = ((PlayerParticipant) participant1).player;
			pixelmon1WillUseItemInStack = null;
			pixelmon1WillUseItemInStackInfo = 0;
		} else {
			userPokemon = participant2.currentPokemon();
			targetPokemon = participant1.currentPokemon();
			usedStack = pixelmon2WillUseItemInStack;
			additionalInfo = pixelmon2WillUseItemInStackInfo;
			user = ((PlayerParticipant) participant2).player;
			pixelmon2WillUseItemInStack = null;
			pixelmon2WillUseItemInStackInfo = 0;
		}

		item = (PixelmonItem) usedStack.getItem();
		item.useFromBag(userPokemon, targetPokemon, additionalInfo);

		/*
		 * This code has the serious issue that battle controller is run on the
		 * server and doesn't have access to Minecraft.thePlayer
		 */
		// ItemStack[] inv = user.inventory.mainInventory;
		// if (((EntityPlayer) Minecraft.getMinecraft().thePlayer).entityId ==
		// user.entityId) {
		// ItemStack[] c_inv = ((EntityPlayer)
		// Minecraft.getMinecraft().thePlayer).inventory.mainInventory;
		// item.removeFromInventory(inv, c_inv);
		// } else {
		// item.removeFromInventory(inv);
		// }
		ChatHandler.sendChat(user, item.getItemDisplayName(usedStack) + " used!");
	}

	public boolean isTrainerVsTrainer() {
		return false;
	}

	boolean participant1Wait;
	boolean participant2Wait;
	boolean isCapturing = false;
	
	public void waitForCapture(){
		isCapturing = true;
	}
	
	public void waitForMove(PlayerParticipant playerParticipant) {
		if (playerParticipant == participant1)
			participant1Wait = true;
		else if (playerParticipant == participant2)
			participant2Wait = true;
	}

	public boolean isWaiting() {
		return participant1Wait || participant2Wait;
	}
}
