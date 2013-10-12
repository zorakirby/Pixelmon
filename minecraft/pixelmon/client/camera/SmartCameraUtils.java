package pixelmon.client.camera;

public class SmartCameraUtils {
	public static boolean canCameraSeeTargetFrom(EntityCamera camera, CameraTarget t, double x, double y, double z){
		return camera.worldObj.clip(camera.worldObj.getWorldVec3Pool().getVecFromPool(x, y, z), camera.worldObj.getWorldVec3Pool().getVecFromPool(t.getX()+t.getXSeeOffset(), t.getY()+t.getYSeeOffset(), t.getZ()+t.getZSeeOffset())) == null;
	}
	public static boolean canCameraSeeTargetFrom(EntityCamera camera, double x, double y, double z){
		return canCameraSeeTargetFrom(camera, camera.getTarget(), x, y, z);
	}
	public static boolean canCameraSeeTarget(EntityCamera camera, CameraTarget t){
		return camera.worldObj.clip(camera.worldObj.getWorldVec3Pool().getVecFromPool(camera.posX, camera.posY, camera.posZ), camera.worldObj.getWorldVec3Pool().getVecFromPool(t.getX()+t.getXSeeOffset(), t.getY()+t.getYSeeOffset(), t.getZ()+t.getZSeeOffset())) == null;
	}
	public static boolean canCameraSeeTarget(EntityCamera camera){
		return canCameraSeeTarget(camera, camera.getTarget());
	}
}
