package pixelmon.blocks.apricornTrees;

import pixelmon.enums.EnumApricornTrees;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;

public class TileEntityApricornTree extends TileEntity {

	public EnumApricornTrees tree;
	
	public TileEntityApricornTree(){};
	
	public TileEntityApricornTree(EnumApricornTrees tree) {
		this.tree = tree;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setShort("ApricornTreeID", (short)tree.id);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		tree = EnumApricornTrees.getFromID(nbt.getShort("ApricornTreeID"));
	}

}
