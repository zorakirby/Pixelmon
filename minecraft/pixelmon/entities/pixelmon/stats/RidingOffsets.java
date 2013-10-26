package pixelmon.entities.pixelmon.stats;

import pixelmon.tools.Vector3f;

public class RidingOffsets {
	public Vector3f standing;
	public Vector3f moving;

	public RidingOffsets(){
		standing = new Vector3f();
		moving = new Vector3f();
	}
	
	public void setStandingOffsets(double x, double y, double z) {
		standing.x = (float)x;
		standing.y = (float)y;
		standing.z = (float)z;
	}

	public void setMovingOffsets(double x, double y, double z) {
		moving.x = (float)x;
		moving.y = (float)y;
		moving.z = (float)z;
	}
}
