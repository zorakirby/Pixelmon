package pixelmon.models.fossils;

import pixelmon.blocks.TileEntityFossilMachine;
import net.minecraft.src.ModelBase;

public abstract class ModelFossil extends ModelBase {
	
	public static String fossilName;
	
	public void setFossilName(String par1String){
		this.fossilName = par1String;
	}
	
	public String getFossilName(){
		return this.fossilName;
	}
	
	public abstract void renderModel(TileEntityFossilMachine entity, float f5);
}
