package pixelmon.entities.pixelmon.particleEffects;

import pixelmon.client.ClientProxy;
import pixelmon.entities.pixelmon.Entity4Textures;
import pixelmon.enums.EnumPixelmonParticles;


public class FlameParticles extends ParticleEffects {

	public float radius;
	public float yOffset;
	private byte count = 0;
	
	public FlameParticles(Entity4Textures pixelmon, float r, float y) {
		super(pixelmon);
		radius = r;
		yOffset = y;
	}

	public void onUpdate() {
		double x = pixelmon.posX;
		double z = pixelmon.posZ;
		
		float f = 180 - (pixelmon.renderYawOffset);
		x += Math.sin(Math.toRadians(f)) * radius * pixelmon.getScaleFactor();
		z += Math.cos(Math.toRadians(f)) * radius * pixelmon.getScaleFactor();
		String particle = "flame";
		if(pixelmon.isWet())
			particle = "smoke";
		byte countmax = 3;
		//if(pixelmon.motionX > 0 || pixelmon.motionZ > 0)
			//countmax = 10;
		if(count++ == countmax) {
			pixelmon.worldObj.spawnParticle(particle, x, pixelmon.posY + pixelmon.hoverTimer + yOffset * pixelmon.getScaleFactor(), z,
					Math.random() * 0.01 + pixelmon.motionX, Math.abs(Math.random() * 0.03), 
					Math.random() * 0.01 + pixelmon.motionZ);
			count = 0;
		}
	}
	
}