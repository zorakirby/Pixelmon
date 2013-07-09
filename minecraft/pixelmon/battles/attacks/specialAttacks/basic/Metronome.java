package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.RandomHelper;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Metronome extends SpecialAttackBase {

	public Metronome() {
		super(ApplyStage.During, false);
	
	}
	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target,
			Attack a, double crit, ArrayList<String> attackList,
			ArrayList<String> targetAttackList) throws Exception {
		
		Attack RandomAttack;
		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " waggled its finger!");
		do 
		{
		RandomAttack = DatabaseMoves.getAttack(RandomHelper.getRandomNumberBetween(1, 559));
		}
		
		while((RandomAttack == DatabaseMoves.getAttack("After You"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Assist"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Bestow"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Chatter"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Copycat"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Counter"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Covet"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Destiny Bond"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Detect"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Endure"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Feint"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Focus Punch"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Follow Me"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Freeze Shock"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Helping Hand"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Ice Burn"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Me First"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Mimic"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Mirror Coat"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Mirror Move"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Nature Power"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Protect"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Quash"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Quick Guard"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Rage Powder"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Relic Song"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Secret Sword"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Sketch"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Sleep Talk"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Snarl"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Snatch"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Snore"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Struggle"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Switcheroo"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Techno Blast"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Thief"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Transform"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Trick"))
		   ||(RandomAttack == DatabaseMoves.getAttack("V-Create"))
		   ||(RandomAttack == DatabaseMoves.getAttack("Wide Guard")));
		RandomAttack.use(user, target, attackList, targetAttackList);
		return false;
	}

}
