package pixelmon.battles.animations.particles;

import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

	@SideOnly(Side.CLIENT)
	public class EntityFlameParticle extends EntityFlameFX {

		public EntityFlameParticle(World par1World, double par2, double par4,
				double par6, double par8, double par10, double par12) {
			super(par1World, par2, par4, par6, par8, par10, par12);
		}
		
		public EntityFlameParticle setMaxAge(int i) {
			particleMaxAge = i;
			return this;
		}
		
		public int maxAge() {
			return particleMaxAge;
		}
		
		public int currentAge() {
			return particleAge;
		}
	}