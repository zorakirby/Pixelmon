package pixelmon.blocks;

import pixelmon.config.PixelmonItemsFossils;
import pixelmon.items.ItemFossilUncovered;
import pixelmon.items.ItemPokemonFossil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFossilCleaner extends TileEntity {

	public int itemInCleaner = -1;
	public int timer = 360;
	public boolean itemClean = false;

	public TileEntityFossilCleaner() {

	}

	public boolean isOn() {
		return itemInCleaner != -1 && timer > 0;
	}

	@Override
	public void updateEntity() {
		if (itemInCleaner != -1) {
			if (timer > 0) {
				timer--;
			} else {
				if (!itemClean && PixelmonItemsFossils.getItemFromIndex(itemInCleaner) instanceof ItemFossilUncovered) {
					itemInCleaner = ((ItemFossilUncovered) PixelmonItemsFossils.getItemFromIndex(itemInCleaner)).cleanedFossil.itemID;
					itemClean = true;
				}
			}

		} else {
			timer = 360;
			itemClean = false;
		}
		super.updateEntity();
	}

	public ItemPokemonFossil getFinishedFossil() {
		if (itemClean) {
			int itemId = itemInCleaner;
			itemInCleaner = -1;
			timer = 360;
			return PixelmonItemsFossils.getFossilFromIndex(itemId);
		}
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		nbt.setShort("DirtyFossilID", (short) itemInCleaner);
		nbt.setShort("FossilTimer", (short) timer);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		itemInCleaner = nbt.getShort("DirtyFossilID");
		timer = nbt.getShort("FossilTimer");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound var1 = new NBTTagCompound();
		this.writeToNBT(var1);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		readFromNBT(pkt.customParam1);
	}

}
