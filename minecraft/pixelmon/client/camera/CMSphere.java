package pixelmon.client.camera;

public abstract class CMSphere extends CameraMovement{
	protected static final double twoPI = Math.PI*2D;
	
	double preferedMoveRadius = 3.5;
	double moveRadius = 3.5;
	double prefPosYaw = 0;
	double posYaw = 0;
	double prefPosPitch = 0;
	double posPitch = 0;
}