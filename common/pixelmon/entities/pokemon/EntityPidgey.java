package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityFlyingPixelmon;
import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import net.minecraft.src.Item;
import net.minecraft.src.World;

public class EntityPidgey extends EntityFlyingPixelmon
{
	
	public EntityPidgey(World world)
	{
		super(world);
		init();
	}
	 protected int getDropItemId()
	    {
	        return Item.feather.shiftedIndex;
	    }
	public void init()
	{
		name = "Pidgey";
		isImmuneToFire = false;
		super.init();
	}
	
	public void evolve() 
	{		
		IHaveHelper entity = new EntityPidgeotto(worldObj);
		helper.evolve(entity.getHelper());
	}
}
