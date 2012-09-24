package pixelmon.models.apricornTrees;

import java.util.ArrayList;

import net.minecraft.src.ModelBase;
import pixelmon.enums.EnumApricornTrees;

public class ApricornTreeModelStore {

	private static ArrayList<ModelStageEnum> treeModels = new ArrayList<ModelStageEnum>();

	public static ModelApricornTreeBase getModel(EnumApricornTrees tree, int stage) {
		for (int i = 0; i < treeModels.size(); i++) {
			if (treeModels.get(i).tree == tree && treeModels.get(i).stage == stage) {
				return treeModels.get(i).model;
			}
		}
		ModelApricornTreeBase model = null;
		try {
			Class<?> var3 = (Class<?>) Class.forName("pixelmon.models.apricornTrees." + tree.modelList[stage]);
			if (var3 != null) {
				model = (ModelApricornTreeBase) var3.getConstructor(new Class[] {}).newInstance(new Object[] {});
			}

		} catch (Exception e) {
			System.out.println("Can't find Model for " + tree.toString());
		}
		treeModels.add(new ModelStageEnum(tree, stage, model));
		return model;
	}

}
