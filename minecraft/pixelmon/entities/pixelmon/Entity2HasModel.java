package pixelmon.entities.pixelmon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.world.World;
import pixelmon.Pixelmon;
import pixelmon.enums.EnumPokemon;

public abstract class Entity2HasModel extends Entity1Base {

	public ModelBase model;
	public float animationNum1 = 0f;

	public Entity2HasModel(World par1World) {
		super(par1World);
	}

	protected void init(String name) {
		super.init(name);
		oldName = name;
	}

	public void evolve(String evolveTo) {
		if (!EnumPokemon.hasPokemon(evolveTo))
			return;
		setName(evolveTo);
	}

	public void loadModel() {
		if (Pixelmon.proxy.getModels().length == 0)
			return;
		int n = ((Entity3HasStats) this).baseStats.nationalPokedexNumber;
		if (Pixelmon.proxy.getModels()[n] != null) {
			model = Pixelmon.proxy.getModels()[n];
		} else {
			ModelBase m = Pixelmon.proxy.loadModel(getName());
			Pixelmon.proxy.getModels()[n] = m;
			model = m;
		}
	}

	String oldName;

	public float getGrowthScaleFactor() {
		return getGrowth().scaleValue;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote && !oldName.equals(getName())) {
			isInitialised = false;
			((Entity3HasStats) this).getBaseStats(getName());
			loadModel();
			oldName = getName();
		}
	}
}
