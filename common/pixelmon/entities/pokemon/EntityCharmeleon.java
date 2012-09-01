package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityCharmeleon extends EntityGroundPixelmon
{
	
	public EntityCharmeleon(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		name = "Charmeleon";
		isImmuneToFire = true;
		super.init();
		this.litUp = false;
		this.litLevel = 45;
	}
	
	public void evolve() 
	{
		EntityPixelmon entity = new EntityCharizard(worldObj);
		helper.evolve(entity.helper);
	}


}
