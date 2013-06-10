package pixelmon.client.models.animations;

import net.minecraft.client.model.ModelRenderer;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class Module {
	protected float toDegrees = 57.29578F;
	protected float toRadians = 1 / toDegrees;

	public Module() {
		// TODO Auto-generated constructor stub
	}

	public abstract void walk(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4);

	public abstract void fly(EntityPixelmon entity, float f, float f1, float f2, float f3, float f4);
	
	public IModulizable getModulizableFrom(Object obj){
		if(obj instanceof ModelRenderer){
			return new ModulizableRenderWrapper((ModelRenderer) obj);
		}
		else if(obj instanceof IModulizable){
			return (IModulizable) obj;
		}
		else{
			throw new IllegalArgumentException("The first variable passed-in to the Module contstructor must either be a ModelRenderer, or an instance of IModulizable. The class of the passed-in arm variable was " + obj.getClass().getSimpleName());
		}
	}

}
