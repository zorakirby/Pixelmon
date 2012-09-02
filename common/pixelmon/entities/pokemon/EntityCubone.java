package pixelmon.entities.pokemon;

import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class EntityCubone extends EntityPixelmon {

	public EntityCubone(World par1World) {
		super(par1World);
		init();
	}

	protected int getDropItemId() {
		return Item.bone.shiftedIndex;
	}

	public void init() {
		super.init("Cubone");
	}

	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.sand.blockID;
	}
}