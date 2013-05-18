package pixelmon.battles.controller;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import pixelmon.api.events.EventType;
import pixelmon.api.events.PixelmonEventHandler;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.battles.participants.ParticipantType;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.battles.participants.WildPixelmonParticipant;
import pixelmon.battles.status.StatusBase;
import pixelmon.comm.ChatHandler;
import pixelmon.config.PixelmonConfig;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.items.PixelmonItem;
import cpw.mods.fml.common.network.Player;

public class BattleController {

	public int battleIndex;

	public ArrayList<BattleParticipant> participants = new ArrayList<BattleParticipant>();

	private int battleTicks = 0;

	public ArrayList<StatusBase> battleStatusList = new ArrayList<StatusBase>();
	public boolean battleEnded = false;
	public int turnCount = 0;
	private Attack lastMoveUsed;

	public BattleController(BattleParticipant participant1, BattleParticipant participant2) throws Exception {
		participant1.startedBattle = true;
		participant1.team = 0;
		participant2.team = 1;
		participants.add(participant1);
		participants.add(participant2);
		initBattle();
	}

	private void initBattle() throws Exception {
		for (BattleParticipant p : participants) {
			if (!p.checkPokemon()) {
				throw new Exception("Battle Could not start!");
			}
		}
		BattleRegistry.registerBattle(this);
		for (BattleParticipant p : participants) {
			p.StartBattle(this, otherParticipant(p));
			p.updateOpponent();
			if (p.canGainXP())
				p.addToAttackersList();
		}
	}

	BattleParticipant otherParticipant(BattleParticipant current) {
		for (BattleParticipant p : participants)
			if (p != current)
				return p;
		return null;
	}

	enum MoveStage {
		PickAttacks, Move
	};

	private MoveStage moveStage = MoveStage.PickAttacks;

	// private boolean pixelmon1MovesFirst = true;
	// private Attack[] attacks = new Attack[2];

	public void endBattle() {
		battleEnded = true;
		for (BattleParticipant p : participants)
			p.EndBattle();
		BattleRegistry.deRegisterBattle(this);
	}

	public void endBattleWithoutXP() {
		battleEnded = true;
		BattleRegistry.deRegisterBattle(this);
		for (BattleParticipant p : participants)
			p.EndBattle();
	}

	int turn = 0;

	public void update() {
		try {
			if (isPvP()) {
				for (BattleParticipant p : participants) {
					if (((PlayerParticipant) p).player == null)
						endBattleWithoutXP();
				}
			}
			if (isWaiting() || paused)
				return;
			int tickTop;
			if (moveStage == MoveStage.PickAttacks)
				tickTop = 30;
			else
				tickTop = 70;
			if (participants.size() < 2)
				return;
			if (battleTicks++ > tickTop) {

				if (moveStage == MoveStage.PickAttacks) { // Pick Moves
					// moveToPositions();
					PickingMoves.pickMoves(this);
					PickingMoves.checkMoveSpeed(this);
					moveStage = MoveStage.Move;
					turn = 0;
				} else if (moveStage == MoveStage.Move) { // First Move
					takeTurn(participants.get(turn));
					turn++;

					if (turn == participants.size()) {
						moveStage = MoveStage.PickAttacks;
						for (BattleParticipant p : participants) {
							p.turnTick();
						}
						for (int i = 0; i < battleStatusList.size(); i++) {
							try {
								battleStatusList.get(i).turnTick(null, null);
							} catch (Exception e) {
								System.out.println("Error on battleStatus tick for " + battleStatusList.get(i).type.toString());
								e.printStackTrace();
							}
						}
						turnCount++;
					}
					checkAndReplaceFaintedPokemon();
				}
				battleTicks = 0;
			}
		} catch (Exception e) {
			System.out.println("Caught error in battle.  Continuing...");
			e.printStackTrace();
		}
	}

	private boolean isPvP() {
		for (BattleParticipant p : participants)
			if (!(p instanceof PlayerParticipant))
				return false;
		return true;
	}

	private void checkAndReplaceFaintedPokemon() {
		for (BattleParticipant p : participants) {
			p.updatePokemon();
			if (p.getIsFaintedOrDead()) {
				if (p instanceof WildPixelmonParticipant && otherParticipant(p) instanceof PlayerParticipant)
					PixelmonEventHandler.fireEvent(EventType.BeatWildPokemon, ((PlayerParticipant) otherParticipant(p)).player);
				String name = p.currentPokemon().getNickname();
				sendToOtherParticipants(p, p.getFaintMessage());
				if (p.getType() == ParticipantType.Player)
					ChatHandler.sendChat(p.currentPokemon().getOwner(), "Your " + name + " fainted!");
				Experience.awardExp(participants, p, p.currentPokemon());

				p.currentPokemon().setEntityHealth(0);
				p.currentPokemon().setDead();
				p.currentPokemon().isFainted = true;
				p.updatePokemon();

				if (p.hasMorePokemon()) {
					p.wait = true;
					p.getNextPokemon();
				} else {
					endBattle();
				}
			}
		}
	}

	public void sendToOtherParticipants(BattleParticipant current, String string) {
		for (BattleParticipant p : participants)
			if (p != current)
				ChatHandler.sendBattleMessage(p.currentPokemon().getOwner(), string);
	}

	public void setAttack(EntityPixelmon mypixelmon, Attack a) {
		for (BattleParticipant p : participants)
			if (p.currentPokemon() == mypixelmon) {
				p.attack = a;
				p.wait = false;
				p.attackList.add(a.baseAttack.attackName);
				return;
			}
	}

	private void takeTurn(BattleParticipant p) {
		if (p.willTryFlee && !p.currentPokemon().isLockedInBattle) {
			calculateEscape(p, p.currentPokemon(), otherParticipant(p).currentPokemon());
		} else if (p.currentPokemon().isLockedInBattle)
			ChatHandler.sendBattleMessage(p.currentPokemon().getOwner(), " cannot escape!");
		else if (p.isSwitching)
			p.isSwitching = false;
		else if (p.willUseItemInStack != null)
			useItem(p);
		else {
			for (int i = 0; i < p.currentPokemon().status.size(); i++) {
				StatusBase e = p.currentPokemon().status.get(i);
				try {
					if (!e.canAttackThisTurn(p.currentPokemon(), otherParticipant(p).currentPokemon())) {
						p.canAttack = false;
						p.attackList.add("None");
						break;
					}
				} catch (Exception exc) {
					if (PixelmonConfig.printErrors) {
						System.out.println("Error calculating canAttackThisTurn for " + e.type.toString());
						System.out.println(exc.getStackTrace());
					}
				}
			}

			if (p.bc == null || otherParticipant(p).bc == null) {
				endBattle();
				return;
			}
			if (p.canAttack)
				p.attack.use(p.currentPokemon(), otherParticipant(p).currentPokemon(), p.attackList, otherParticipant(p).attackList);
		}
	}

	private void calculateEscape(BattleParticipant p, EntityPixelmon user, EntityPixelmon target) {
		float A = ((float) user.stats.Speed) * ((float) user.battleStats.getSpeedModifier()) / 100;
		float B = ((float) target.stats.Speed) * ((float) target.battleStats.getSpeedModifier()) / 100;
		if (B > 255)
			B = 255;
		float C = p.escapeAttempts++;
		float F = A * 32 / B + 30 * C;

		if (F > 255 || new Random().nextInt(255) < F) {
			if (!user.isLockedInBattle) {
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " escaped!");
				endBattle();
			} else {
				ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "Its locked in battle!");
			}
		} else
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " couldn't escape!");
	}

	public void setFlee(EntityPixelmon mypixelmon) {
		for (BattleParticipant p : participants)
			if (mypixelmon == p.currentPokemon()) {
				p.willTryFlee = true;
				p.wait = false;
			}
	}

	public void setUseItem(Player user, ItemStack usedStack, int additionalInfo) {
		for (BattleParticipant p : participants)
			if (p.getType() == ParticipantType.Player && p.getEntity() == user) {
				p.willUseItemInStack = usedStack;
				p.willUseItemInStackInfo = additionalInfo;
				p.wait = false;
			}
	}

	public void SwitchPokemon(EntityPixelmon currentPixelmon, int newPixelmonId) {
		for (BattleParticipant p : participants)
			if (p.currentPokemon() == currentPixelmon) {
				p.switchPokemon(newPixelmonId);
				p.currentPokemon().battleController = this;
				p.attackersList.add(p.currentPokemon().getPokemonId());
				p.isSwitching = true;
				p.wait = false;
				for (BattleParticipant p2 : participants)
					if (p2.team != p.team) {
						p2.attackersList.clear();
						p2.attackersList.add(p2.currentPokemon().getPokemonId());
						p2.updateOpponent();
					}

			}
	}

	public void useItem(BattleParticipant p) {
		EntityPixelmon userPokemon = null, targetPokemon = null;
		PixelmonItem item = null;
		EntityPlayer user = null;
		ItemStack usedStack = null;
		int additionalInfo = 0;
		userPokemon = p.currentPokemon();
		targetPokemon = otherParticipant(p).currentPokemon();
		usedStack = p.willUseItemInStack;
		additionalInfo = p.willUseItemInStackInfo;
		user = ((PlayerParticipant) p).player;
		p.willUseItemInStack = null;
		p.willUseItemInStackInfo = 0;

		item = (PixelmonItem) usedStack.getItem();
		item.useFromBag(userPokemon, targetPokemon, additionalInfo);

		ItemStack[] inv = user.inventory.mainInventory;
		item.removeFromInventory(inv);
	}

	public boolean isTrainerVsTrainer() {
		return false;
	}

	boolean paused = false;

	public int money;

	public void pauseBattle() {
		paused = true;
	}

	public boolean isWaiting() {
		for (BattleParticipant p : participants)
			if (p.wait)
				return true;
		return false;
	}

	public void endPause() {
		paused = false;
	}

	public Attack getLastMoveUsed() {
		return lastMoveUsed;
	}
}
