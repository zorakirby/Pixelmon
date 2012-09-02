package pixelmon.entities.pokemon;

import net.minecraft.src.*;
import pixelmon.config.PixelmonEntityList.ClassType;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class EntitySandile extends EntityPixelmon
{
	
	public EntitySandile(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Sandile");
	}
	
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
	    return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.sand.blockID;
	}
}