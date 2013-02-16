package pixelmon.client.models.animations;

import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class Module {
	protected float toDegrees = 57.29578F;
	protected float toRadians = 1 / toDegrees;

	public Module() {
		// TODO Auto-generated constructor stub
	}

	public abstract void walk(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4);

	public abstract void fly(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4);

}
