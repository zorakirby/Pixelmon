package pixelmon.client.camera;

public abstract class CameraMovement {
	EntityCamera camera;
	void setCamera(EntityCamera cam){
		if(camera != null)
			camera.setMovement(null);
		camera = cam;
	}
	public abstract void updateCameraPosition();
	public abstract void cameraTargetSet();
}
