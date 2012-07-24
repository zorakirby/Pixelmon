package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumType;

import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_Pixelmon;

public class Infatuated extends StatusEffectBase {

	PixelmonEntityHelper originalTarget; 
	public Infatuated() {
		super(StatusEffectType.Infatuated, false, true, false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		if (checkChance()) {
			for (StatusEffectBase e : target.status)
				if (e.type == StatusEffectType.Infatuated) {
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " is already in love!");
					return;
				}
			target.status.add(this);
			originalTarget = user;
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " has fallen in love!");
		} else
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " failed!");
	}

	@Override
	public boolean canAttackThisTurn(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		if (originalTarget !=target){
			user.status.remove(this);
			return true;
		}
		
		if (mod_Pixelmon.getRandomNumberBetween(0, 100) <= 50) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " is in love!");
			return false;
		} else {
			return true;
		}
	}
}
