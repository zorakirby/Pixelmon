package pixelmon.entities.pokeballs;

import java.util.ArrayList;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import pixelmon.battles.BattleController;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall.Mode;
import pixelmon.entities.pokeballs.captures.CaptureBase;
import pixelmon.entities.pokeballs.captures.CaptureFriendBall;
import pixelmon.entities.pokeballs.captures.CaptureLevelBall;
import pixelmon.entities.pokeballs.captures.CaptureMoonBall;
import pixelmon.enums.EnumPokeballs;

public class PokeballTypeHelper {

	private static ArrayList<CaptureBase> captureList  = new ArrayList<CaptureBase>();
	
	static{
		captureList.add(new CaptureLevelBall());
		captureList.add(new CaptureMoonBall());
		captureList.add(new CaptureFriendBall());
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

	public static int modifyCaptureRate(int captureRate) {
		return captureRate;
	}

	
}
