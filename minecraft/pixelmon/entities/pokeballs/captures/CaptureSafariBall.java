package pixelmon.entities.pokeballs.captures;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall.Mode;
import pixelmon.enums.EnumPokeballs;

public class CaptureSafariBall extends CaptureBase {

	public CaptureSafariBall() {
		super(EnumPokeballs.SafariBall);
	}

	@Override
	public double getBallBonus(EnumPokeballs type, EntityPlayer thrower, EntityPixelmon p2, Mode mode) {
		BiomeGenBase biome = p2.worldObj.getBiomeGenForCoords(MathHelper.floor_double(p2.posX), MathHelper.floor_double(p2.posZ));
		if(biome == BiomeGenBase.plains){
			return 1.5;
		}
		return 1;
	}
}
