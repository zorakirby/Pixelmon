package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import net.minecraft.src.*;

public class EntityStaryu extends EntityWaterPixelmon
{

	public EntityStaryu(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		super.init("Staryu");
		depthRangeStart=7;
	}

	public float getMoveSpeed() 
	{
		return 2F;
	}
	
	public boolean getCanSpawnHere() {
		return  worldObj.getBlockId((int)posX,(int)Math.ceil(posY)-1,(int)posZ)!= Block.waterStill.blockID && super.getCanSpawnHere();
	}
}