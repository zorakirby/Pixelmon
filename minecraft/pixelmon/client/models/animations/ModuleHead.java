package pixelmon.client.models.animations;

import java.util.ArrayList;

import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.IModulizable.EnumGeomData;
import pixelmon.entities.pixelmon.EntityPixelmon;

import net.minecraft.client.model.ModelRenderer;

public class ModuleHead extends Module {

	ArrayList<Module> modules = new ArrayList<Module>();

	IModulizable head;

	float headStartAngleX, headStartAngleY;

	public ModuleHead(Object head) {
		this.head = getModulizableFrom(head);
		headStartAngleX = this.head.getValue(EnumGeomData.xrot);
		headStartAngleY = this.head.getValue(EnumGeomData.yrot);
	}

	@Override
	public void walk(EntityPixelmon entity, float f, float f1, float f2, float rotateAnglePitch, float rotateAngleYaw) {
		head.setValue(rotateAngleYaw * toRadians + headStartAngleX, EnumGeomData.xrot);
		head.setValue(rotateAnglePitch * toRadians + headStartAngleY, EnumGeomData.yrot);
		for (Module m : modules)
			m.walk(entity, f, f1, f2, rotateAnglePitch, rotateAngleYaw);
	}

	@Override
	public void fly(EntityPixelmon entity, float f, float f1, float f2,
			float f3, float f4) {
		walk(entity, f, f1, f2, f3, f4);
	}
	
	public ModuleHead addModule(Module m){
		modules.add(m);
		return this;
	}

}
