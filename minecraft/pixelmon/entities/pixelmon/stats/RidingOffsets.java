package pixelmon.entities.pixelmon.stats;

import pixelmon.tools.Vector3f;

public class RidingOffsets {
	public Vector3f standing;
	public Vector3f moving;

	public RidingOffsets(){
	}
	
	public void setStandingOffsets(double x, double y, double z) {
		standing = new Vector3f();
		standing.x = (float)x;
		standing.y = (float)y;
		standing.z = (float)z;
	}

	public void setMovingOffsets(double x, double y, double z) {
		moving = new Vector3f();
		moving.x = (float)x;
		moving.y = (float)y;
		moving.z = (float)z;
	}
}
