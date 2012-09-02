package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.*;

public class EntityCharmander extends EntityPixelmon
{
	
	public EntityCharmander(World world)
	{
		super(world);
		init();
	}

	public void init()
	{
		super.init("Charmander");
	}
	
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
	    return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.sand.blockID && this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID;
	}
}
