package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.World;

public class EntityCharizard extends EntityGroundPixelmon
{
	
	public EntityCharizard(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Charizard";
		isImmuneToFire = true;
		super.init();
		this.litUp = false;
		this.litLevel = 50;
	}
	
	public void evolve() 
	{
		EntityPixelmon entity = new EntityCharizard(worldObj);
		helper.evolve(entity.helper);
	}

}
