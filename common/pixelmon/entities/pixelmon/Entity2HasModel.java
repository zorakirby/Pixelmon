package pixelmon.entities.pixelmon;

import java.lang.reflect.InvocationTargetException;

import pixelmon.ClientProxy;
import pixelmon.Pixelmon;
import pixelmon.database.DatabaseStats;
import pixelmon.enums.EnumPokemon;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityTameable;
import net.minecraft.src.ModelBase;
import net.minecraft.src.World;

public abstract class Entity2HasModel extends Entity1Base {

	public ModelBase model;

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
		oldName = evolveTo;
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

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote && !oldName.equals(getName())) {
			isInitialised = false;
			loadModel();
			oldName = getName();
		}
	}
}
