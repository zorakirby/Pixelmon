package pixelmon.blocks.apricornTrees;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import pixelmon.enums.EnumApricornTrees;

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
