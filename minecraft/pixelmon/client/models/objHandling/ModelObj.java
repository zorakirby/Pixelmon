package pixelmon.client.models.objHandling;

import java.util.ArrayList;

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

	public void renderModel() {
		for (Object3D o : objectParts) {
			o.opengldraw();
		}
	}
}
