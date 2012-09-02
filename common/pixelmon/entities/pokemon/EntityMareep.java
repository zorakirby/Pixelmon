package pixelmon.entities.pokemon;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class EntityMareep extends EntityPixelmon {

	public EntityMareep(World world) {
		super(world);
		init();
	}

	public void init()
	{
		super.init("Mareep");
	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		return super.interact(par1EntityPlayer);
	}

	protected int getDropItemId() {
		return Block.cloth.blockID;
	}

}
