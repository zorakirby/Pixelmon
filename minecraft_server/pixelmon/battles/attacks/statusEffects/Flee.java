package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.battles.BattleRegistry;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;

import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_Pixelmon;

public class Flee extends StatusEffectBase {

	public Flee() {
		super(StatusEffectType.Flee, false, false, true);
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName()+ " runs away!");
		mod_Pixelmon.battleRegistry.deRegisterBattle(target.bc);
		target.EndBattle();
		user.EndBattle();
	}

	@Override
	public boolean canAttackThisTurn(PixelmonEntityHelper user, PixelmonEntityHelper target) {
		return false;
	}
}
