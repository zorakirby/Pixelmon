package pixelmon.entities.pixelmon;

import java.lang.reflect.InvocationTargetException;

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
		if (!EnumPokemon.hasPokemon(evolveTo)) return;
		setName(evolveTo);
		oldName = evolveTo;
	}
	
	public void loadModel(){
		model = Pixelmon.proxy.loadModel(getName());
	}

	String oldName;

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!oldName.equals(getName())) {
			isInitialised=false;
			loadModel();
			oldName = getName();
		}
	}

}
