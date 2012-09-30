package pixelmon.entities.pokeballs.captures;

import net.minecraft.src.EntityPlayer;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall.Mode;
import pixelmon.enums.EnumPokeballs;

public class CaptureFriendBall extends CaptureBase {

	public CaptureFriendBall() {
		super(EnumPokeballs.FriendBall);
	}

	@Override
	public double getBallBonus(EnumPokeballs type, EntityPlayer thrower, EntityPixelmon p2, Mode mode) {
		return type.getBallBonus();
	}
	
	@Override
	public void doAfterEffect(EnumPokeballs type, EntityPixelmon p) {
		p.friendship.friendship = 200;		
	}
	
	
}
