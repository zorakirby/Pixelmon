package pixelmon.entities.pokeballs;

import java.util.ArrayList;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import pixelmon.battles.BattleController;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall.Mode;
import pixelmon.entities.pokeballs.captures.*;
import pixelmon.enums.EnumPokeballs;

public class PokeballTypeHelper {

	private static ArrayList<CaptureBase> captureList  = new ArrayList<CaptureBase>();
	
	static{
		captureList.add(new CaptureLoveBall());
		captureList.add(new CaptureLevelBall());
		captureList.add(new CaptureMoonBall());
		captureList.add(new CaptureFriendBall());
		captureList.add(new CaptureSafariBall());
		captureList.add(new CaptureDiveBall());
		captureList.add(new CaptureDuskBall());
		captureList.add(new CaptureHealBall());
		captureList.add(new CaptureLuxuryBall());
		captureList.add(new CaptureNetBall());
		captureList.add(new CaptureNestBall());
		captureList.add(new CaptureHeavyBall());
	}
	
	public static double getBallBonus(EnumPokeballs type, EntityLiving thrower, EntityPixelmon p2, Mode mode) {
		double ballBonus = type.getBallBonus();
		for (CaptureBase c: captureList)
			if (c.pokeball == type) return c.getBallBonus(type, (EntityPlayer)thrower, p2, mode);
		return ballBonus;
	}
	
	public static void doAfterEffect(EnumPokeballs type, EntityPixelmon p2){
		for (CaptureBase c: captureList)
			if (c.pokeball == type) c.doAfterEffect(type, p2);
	}

	public static int modifyCaptureRate(EnumPokeballs type, String pokemonName, int captureRate) {
		for (CaptureBase c: captureList)
			if (c.pokeball == type) return c.modifyCaptureRate(pokemonName, captureRate);
		return captureRate;
	}

	
}
