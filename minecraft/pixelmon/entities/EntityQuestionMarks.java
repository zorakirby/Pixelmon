package pixelmon.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityQuestionMarks extends EntityLiving
{
	
	public EntityQuestionMarks(World world)
	{
		super(world);
		texture = "/pixelmon/image/questionmarks.png";
		height = 0.2F;
	}

	public int getMaxHealth() 
	{
		return 0;
	}
	
}