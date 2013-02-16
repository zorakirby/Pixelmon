package pixelmon.client.models.animations;

import java.util.ArrayList;

import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.entities.pixelmon.EntityPixelmon;

import net.minecraft.client.model.ModelRenderer;

public class ModuleHead extends Module {

	ArrayList<Module> modules = new ArrayList<Module>();

	ModelRenderer head;

	float headStartAngleX, headStartAngleY;

	public ModuleHead(PixelmonModelRenderer head) {
		this.head = head;
		headStartAngleX = head.rotateAngleX;
		headStartAngleY = head.rotateAngleY;
	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2, float rotateAnglePitch, float rotateAngleYaw) {
		head.rotateAngleX = rotateAngleYaw * toRadians + headStartAngleX;
		head.rotateAngleY = rotateAnglePitch * toRadians + headStartAngleY;
		for (Module m : modules)
			m.walk(entity, f, f1, f2, rotateAnglePitch, rotateAngleYaw);
	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4) {
		walk(entity, f, f1, f2, f3, f4);
	}

}
