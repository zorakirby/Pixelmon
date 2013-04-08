package pixelmon.entities.pixelmon.particleEffects;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.world.World;
import pixelmon.battles.animations.particles.EntityFlameParticle;
import pixelmon.battles.animations.particles.EntitySmokeParticle;
import pixelmon.client.ClientProxy;
import pixelmon.entities.pixelmon.Entity4Textures;
import pixelmon.enums.EnumPixelmonParticles;


public class FlameParticles extends ParticleEffects {

	public float radius;
	public float yOffset;
	private byte count = 0;
	private int size = 0;
	
	public FlameParticles(Entity4Textures pixelmon, float r, float y, int s) {
		super(pixelmon);
		radius = r;
		yOffset = y;
		size = s;
	}

	private Double random(double sc, boolean np) {
		if(!np)
			return Math.random() * sc;
		double d = Math.random();
		if(d > 0.5)
			return Math.random() * sc;
		else
			return -(Math.random() * sc);
	}
	
	private Double random(double sc) {
		return random(sc, true);
	}
	
	public void onUpdate() {
		double x = pixelmon.posX;
		double y = pixelmon.posY + pixelmon.hoverTimer + yOffset * pixelmon.getScaleFactor();
		double z = pixelmon.posZ;
		
		float f = 180 - (pixelmon.renderYawOffset);
		x += Math.sin(Math.toRadians(f)) * radius * pixelmon.getScaleFactor();
		z += Math.cos(Math.toRadians(f)) * radius * pixelmon.getScaleFactor();
		byte countmax = 3;
		if(count++ == countmax) {
			if(pixelmon.isWet()) {
				for(int i = 0; i < size * 2; i++) {
					EntitySmokeParticle esp = new EntitySmokeParticle(pixelmon.worldObj, x, y, z,
							random(0.001 * size), random(0.001 * size, false), random(0.001 * size));
					boolean movedMuch = Math.abs(pixelmon.posX - pixelmon.lastTickPosX) > 0.3 ||
							Math.abs(pixelmon.posY - pixelmon.lastTickPosY) > 0.1 ||
							Math.abs(pixelmon.posZ - pixelmon.lastTickPosZ) > 0.1;
					if(movedMuch)
						esp.setMaxAge(esp.maxAge() / (size));
					else
						esp.setMaxAge(esp.maxAge() * size / 10);
					Minecraft.getMinecraft().effectRenderer.addEffect(esp);
				}
			} else {
				for(int i = 0; i < size * 2; i++) {
					EntityFlameParticle efp = new EntityFlameParticle(pixelmon.worldObj, x, y, z,
							random(0.005 * size), random(0.015 * size, false), random(0.005 * size));
					boolean movedMuch = Math.abs(pixelmon.posX - pixelmon.lastTickPosX) > 0.1 ||
							Math.abs(pixelmon.posY - pixelmon.lastTickPosY) > 0.1 ||
							Math.abs(pixelmon.posZ - pixelmon.lastTickPosZ) > 0.1;
					if(movedMuch)
						efp.setMaxAge(efp.maxAge() / (4));
					else
						efp.setMaxAge(efp.maxAge() * size / 10);
					Minecraft.getMinecraft().effectRenderer.addEffect(efp);
				}
			}
			count = 0;
		}
	}
}