package pixelmon.client.models.animations;

import java.util.ArrayList;

import net.minecraft.client.model.ModelRenderer;

public abstract class ModuleTail extends Module {

	public ArrayList<ModelRenderer> tailParts;
	public int[] lengths;

	public ModuleTail(ModelRenderer... tailArgs) {
		tailParts = new ArrayList<ModelRenderer>();
		for (ModelRenderer m : tailArgs) {
			tailParts.add(m);
		}
		lengths = new int[tailParts.size()];

	}
}
