package pixelmon.client.camera;

public interface CameraTarget {
	public double getX();
	public double getXSeeOffset();
	public double getY();
	public double getYSeeOffset();
	public double getZ();
	public double getZSeeOffset();
	public double minimumCameraDistance();
	public boolean isValidTarget();
	public boolean hasChanged();
	public boolean setTargetData(Object o);
	public Object getTargetData();
}
