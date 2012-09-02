package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectType;
import pixelmon.battles.attacks.attackEffects.EffectBase;
import pixelmon.battles.attacks.attackEffects.StatsEffect;
import pixelmon.battles.attacks.attackModifiers.ModifierBase;
import pixelmon.battles.attacks.attackModifiers.ModifierType;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Mist extends StatusEffectBase {

	int turnCount =0;
	public Mist() {
		super(StatusEffectType.Mist, false, true, false);
	}

	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : user.status)
				if (e.type == StatusEffectType.Paralysis) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is already surrounded by mist!");
					return;
				}

			user.status.add(this);
			turnCount=0;
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " creates a cloud of mist!");

		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public boolean stopsIncomingAttack(EntityPixelmon user, EntityPixelmon target, Attack a) {
		if (a.attackCategory ==  Attack.ATTACK_STATUS){
			for (EffectBase e:a.effects){
				if (e.effectType == EffectType.Stats){
					for (ModifierBase m: (((StatsEffect)e).modifiers)){
						if (m.type == ModifierType.User) return false;
					}
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is protected by the mist!");
					return true;
				}
			}
		}
			
		return false;
	}

	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target) {
		turnCount++;
		if (turnCount==5) {
			user.status.remove(this);
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), "The mist wore off!");
		}
	}

}
