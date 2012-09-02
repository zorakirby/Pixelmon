package pixelmon.entities.pixelmon;

import java.lang.reflect.InvocationTargetException;

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
	
	protected void init(String name){
		super.init(name);
		model = loadModel();
	}

	@SideOnly(Side.CLIENT)
	private ModelBase loadModel() {
		ModelBase model = null;
		try {
			Class<?> var3 = (Class<?>) Class.forName("pixelmon.models.pokemon.Model" + getName());
			if (var3 != null) {
				model = (ModelBase) var3.getConstructor(new Class[] { World.class }).newInstance(new Object[] { worldObj });
			}

		} catch (Exception e) {
			System.out.println("Can't find Model for " + getName());
		}
		if (model == null)
			System.out.println("Can't find Model for " + getName());
		return model;
	}

}
