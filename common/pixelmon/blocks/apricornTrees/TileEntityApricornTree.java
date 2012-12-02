package pixelmon.blocks.apricornTrees;

import pixelmon.enums.EnumApricornTrees;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet132TileEntityData;
import net.minecraft.src.TileEntity;

public class TileEntityApricornTree extends TileEntity {

	public EnumApricornTrees tree;

	public TileEntityApricornTree() {
	};

	public TileEntityApricornTree(EnumApricornTrees tree) {
		this.tree = tree;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setShort("ApricornTreeID", (short) tree.id);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		tree = EnumApricornTrees.getFromID(nbt.getShort("ApricornTreeID"));
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound var1 = new NBTTagCompound();
		this.writeToNBT(var1);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
	}

}
