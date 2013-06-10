package pixelmon.client.models.animations;

public interface IModulizable {

	public void setValue(float value, EnumGeomData d);
	

	/**
	 * actually getting the BASE values here.
	 */
	public float getValue(EnumGeomData d);
	
	
	public enum EnumGeomData{
		xloc, yloc, zloc, xrot, yrot, zrot;
	}

}
