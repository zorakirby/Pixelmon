package pixelmon.client.models.fossils;

import net.minecraft.client.model.ModelBase;
import pixelmon.blocks.TileEntityFossilMachine;

public abstract class ModelFossil extends ModelBase {
	
	public static String fossilName;
	
	public void setFossilName(String par1String){
		this.fossilName = par1String;
	}
	
	public String getFossilName(){
		return this.fossilName;
	}
	
	public abstract void renderModel(float f5);
}
