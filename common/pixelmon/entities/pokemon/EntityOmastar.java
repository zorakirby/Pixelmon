package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import net.minecraft.src.*;

public class EntityOmastar extends EntityWaterPixelmon
{
	
	public EntityOmastar(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		super.init("Omastar");
		depthRangeStart = 5;
		depthRangeEnd = 10;
	}

	public float getMoveSpeed() 
	{
		return 2F;
	}

	public boolean getCanSpawnHere() {
		
		return  this.posY < 55.0D && super.getCanSpawnHere();
	}
}