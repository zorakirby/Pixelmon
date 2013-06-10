package pixelmon.client.models.animations;

import net.minecraft.client.model.ModelRenderer;
import pixelmon.client.models.animations.IModulizable.EnumGeomData;

public class ModulizableRenderWrapper implements IModulizable{

	ModelRenderer renderer;
	
	public ModulizableRenderWrapper(ModelRenderer renderer){
		this.renderer = renderer;
	}


	@Override
	public float getValue(EnumGeomData d) {
		switch(d){
		case xloc: return this.renderer.rotationPointX;
		case yloc: return this.renderer.rotationPointY;
		case zloc: return this.renderer.rotationPointZ;
		case xrot: return this.renderer.rotateAngleX;
		case yrot: return this.renderer.rotateAngleY;
		case zrot: return this.renderer.rotateAngleZ;
		default: return -1;
		}
	}

	@Override
	public void setValue(float value, EnumGeomData d) {
		switch(d){
		case xloc: this.renderer.rotationPointX = -value;
			break;
		case yloc: this.renderer.rotationPointY = -value;
			break;
		case zloc: this.renderer.rotationPointY = -value;
			break;
		case xrot: this.renderer.rotateAngleX = value;
			break;
		case yrot: this.renderer.rotateAngleY = value;
			break;
		case zrot: this.renderer.rotateAngleZ = value;
			break;
		}
	}

}
