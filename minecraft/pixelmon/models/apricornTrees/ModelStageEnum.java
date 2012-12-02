package pixelmon.models.apricornTrees;

import net.minecraft.src.ModelBase;
import pixelmon.enums.EnumApricornTrees;

public class ModelStageEnum {

	public ModelApricornTreeBase model;
	public int stage;

	public ModelStageEnum(int stage, ModelApricornTreeBase model) {
		this.stage = stage;
		this.model = model;
	}
}
