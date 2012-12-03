package pixelmon.models.fossils;

import pixelmon.blocks.TileEntityFossilMachine;
import net.minecraft.src.ModelBase;

public abstract class ModelFossil extends ModelBase {
	public abstract void renderModel(TileEntityFossilMachine entity, float f5);
}
