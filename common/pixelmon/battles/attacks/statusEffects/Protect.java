package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;



public class Protect extends StatusEffectBase {

	public Protect() {
		super(StatusEffectType.Protect, false, false, true);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		float chance = 1f;
		for(int i=attackList.size()-2; i >=0; i--){
			if (attackList.get(i) == "Protect") chance *=0.5f;
			else break;
		}
		if (chance < 0.125f) chance = 0.125f;
		
		if ((new Random()).nextInt(100)<= chance*100) {
			target.status.add(this);
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is readying itself!");

		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public boolean stopsIncomingAttack(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a) {
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " protects itself!");
		return true;
	}

	@Override
	public void turnTick(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		user.status.remove(this);
	}
}
