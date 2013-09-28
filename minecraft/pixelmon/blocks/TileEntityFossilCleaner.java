package pixelmon.blocks;

import pixelmon.config.PixelmonItemsFossils;
import pixelmon.items.ItemCoveredFossil;
import pixelmon.items.ItemFossil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;

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
				if (!itemClean && PixelmonItemsFossils.getItemFromIndex(itemInCleaner) instanceof ItemCoveredFossil) {
					itemInCleaner = ((ItemCoveredFossil) PixelmonItemsFossils.getItemFromIndex(itemInCleaner)).cleanedFossil.itemID;
					itemClean = true;
					if (worldObj instanceof WorldServer)
						((WorldServer) worldObj).getPlayerManager().markBlockForUpdate(xCoord, yCoord, zCoord);
				}
			}

		} else {
			timer = 360;
			itemClean = false;
		}
		super.updateEntity();
	}

	public ItemFossil getFinishedFossil() {
		if (itemClean) {
			int itemId = itemInCleaner;
			itemInCleaner = -1;
			timer = 360;
			if (worldObj instanceof WorldServer)
				((WorldServer) worldObj).getPlayerManager().markBlockForUpdate(xCoord, yCoord, zCoord);
			return PixelmonItemsFossils.getFossilFromIndex(itemId);
		}
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		itemInCleaner = nbt.getShort("ItemInCleaner");
		timer = nbt.getShort("CleanerTimer");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("ItemInCleaner", (short) itemInCleaner);
		nbt.setShort("CleanerTimer", (short) timer);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound var1 = new NBTTagCompound();
		this.writeToNBT(var1);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		readFromNBT(pkt.data);
	}

	public void setItemInCleaner(int itemID) {
		itemInCleaner = itemID;
		timer = 360;
		itemClean = false;
	}

}
