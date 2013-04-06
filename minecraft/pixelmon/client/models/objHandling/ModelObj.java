package pixelmon.client.models.objHandling;

import java.util.ArrayList;

import pixelmon.entities.pixelmon.EntityPixelmon;

public class ModelObj {
	ArrayList<Object3D> objectParts = new ArrayList<Object3D>();

	public ModelObj(Object3D... objects) {
		for (Object3D o : objects) {
			if (o != null) {
				objectParts.add(o);
				o.opengldrawtolist();
			}
		}

	}

	public void renderModel(EntityPixelmon pixelmon, float var16, float var15, float var13, float f, float var12, float var14) {
		for (Object3D o : objectParts) {
			o.opengldraw();
		}
	}
}
