package pixelmon.battles.attacks.specialAttacks.basic;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.database.DatabaseStats;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class CrushGrip extends SpecialAttackBase{
	public CrushGrip() {
		super(ApplyStage.During, false);}



	@Override
	public boolean ApplyEffect(EntityPixelmon user, EntityPixelmon target, Attack a, double crit, ArrayList<String> attackList, ArrayList<String> targetAttackList) throws Exception {
		// TODO Auto-generated method stub
		float variable =  120 * (target.getHealth() / target.getMaxHealth());
		a.movePower = (int) (120 * (target.getHealth()/target.getMaxHealth()));
		return false;}
}