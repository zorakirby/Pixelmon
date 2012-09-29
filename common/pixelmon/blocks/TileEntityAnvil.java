package pixelmon.blocks;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet132TileEntityData;
import net.minecraft.src.TileEntity;

public class TileEntityAnvil extends TileEntity {

	public int itemOnAnvil = -1;

	public TileEntityAnvil() {
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("ItemOnAnvil", itemOnAnvil);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		itemOnAnvil = nbt.getInteger("ItemOnAnvil");
	}

	@Override
	public Packet getAuxillaryInfoPacket() {
		NBTTagCompound var1 = new NBTTagCompound();
		this.writeToNBT(var1);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, Packet132TileEntityData pkt) {
		readFromNBT(pkt.customParam1);
	}
}
