package pixelmon.client.camera;

import net.minecraft.entity.Entity;

public class CameraTargetEntity implements CameraTarget{

	Entity entity;
	
	private double lastX = 0, lastY = 0, lastZ = 0;
	
	public CameraTargetEntity(Entity e){
		entity = e;
	}
	
	@Override
	public double getX() {
		return entity.posX;
	}

	@Override
	public double getY() {
		return entity.posY;
	}

	@Override
	public double getZ() {
		return entity.posZ;
	}

	@Override
	public boolean isValidTarget() {
		return entity != null && !entity.isDead;
	}

	@Override
	public double getXSeeOffset() {
		return 0;
	}

	@Override
	public double getYSeeOffset() {
		return entity.getEyeHeight();
	}

	@Override
	public double getZSeeOffset() {
		return 0;
	}

	@Override
	public double minimumCameraDistance() {
		return 0;
	}
	
	@Override
	public boolean hasChanged(){
		boolean r = false;
		if(entity.posX != lastX || entity.posY != lastY || entity.posZ != lastZ)
			r = true;
		lastX = entity.posX;
		lastY = entity.posY;
		lastZ = entity.posZ;
		return r;
	}

	@Override
	public boolean setTargetData(Object o) {
		if(o instanceof Entity){
			entity = (Entity)o;
			return true;
		}
		return false;
	}

	@Override
	public Object getTargetData() {
		return entity;
	}
}
