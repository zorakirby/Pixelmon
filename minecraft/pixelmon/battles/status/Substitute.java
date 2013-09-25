package pixelmon.battles.status;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Substitute extends StatusBase {
	public int health;
	public Substitute(int health) {
		super(StatusType.Substitute, true, false, false);
		this.health = health;
	}
/*	@Override
	public double adjustDamage(Attack a, double damage, EntityPixelmon user, EntityPixelmon target, double crit) throws Exception {
		health -= damage;
		if (health <= 0)
		{
			ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), "The substitute faded away!");
			target.status.remove(this);
		}
		return 0;
	}
*/	
	@Override
	public boolean stopsStatusChange(Attack a) throws Exception {
		if (a.baseAttack.basePower == -1)
		return false;
		return true;
	}
	@Override
	public boolean stopsIncomingAttack(EntityPixelmon user, EntityPixelmon target, Attack a) throws Exception {
		String attackName = a.baseAttack.attackName;
		if (attackName == "Attract" ||
			attackName == "Curse" ||
			attackName == "Destiny Bond" ||
			attackName == "Disable" ||
			attackName == "Encore" ||
			attackName == "Foresight" ||
			attackName == "Grudge" ||
			attackName == "Guard Swap" ||
			attackName == "Haze" ||
			attackName == "Heart Swap" ||
			attackName == "Imprison" ||
			attackName == "Miracle Eye" ||
			attackName == "Odor Sleuth" ||
			attackName == "Perish Song" ||
			attackName == "Psych Up" ||
			attackName == "Roar" ||
			attackName == "Role Play" ||
			attackName == "Skill Swap" ||
			attackName == "Spite" ||
			attackName == "Taunt" ||
			attackName == "Torment" ||
			attackName == "Whirlwind" ||
			a.baseAttack.basePower != -1)
			return false;

		ChatHandler.sendBattleMessage(user.getOwner(), target.getOwner(), user.getNickname() + " tried to use " + attackName + ", but it failed!");
		return true;
	}
	public void attackSubstitute(int damage, EntityPixelmon attacker)
	{
		health -= damage;
		
		
		if (health <= 0)
		{
			if (attacker.battleController.participants.get(0).currentPokemon() == attacker) {
				ChatHandler.sendBattleMessage(attacker.battleController.participants.get(1).currentPokemon().getOwner(),
						attacker.getOwner(), "The substitute faded away!");
				attacker.battleController.participants.get(1).currentPokemon().status.remove(this);
			}
			else {
				ChatHandler.sendBattleMessage(attacker.battleController.participants.get(0).currentPokemon().getOwner(),
						attacker.getOwner(), "The substitute faded away!");
			attacker.battleController.participants.get(0).currentPokemon().status.remove(this);
			}
		}
	}
	
}
