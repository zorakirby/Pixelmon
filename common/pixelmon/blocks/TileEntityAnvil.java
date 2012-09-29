package pixelmon.blocks;

import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsPokeballs;
import pixelmon.items.ItemPokeballDisc;
import pixelmon.items.PixelmonItem;
import net.minecraft.src.Item;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet132TileEntityData;
import net.minecraft.src.TileEntity;

public class TileEntityAnvil extends TileEntity {

	public int itemOnAnvil = -1;
	public int state = 0;

	public TileEntityAnvil() {
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("ItemOnAnvil", itemOnAnvil);
		nbt.setInteger("AnvilItemState", state);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		itemOnAnvil = nbt.getInteger("ItemOnAnvil");
		state = nbt.getInteger("AnvilItemState");
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

	int count = 0;

	public void blockHit() {
		if (itemOnAnvil != -1) {
			Item item = PixelmonItemsPokeballs.getItemFromID(itemOnAnvil);
			if (item instanceof ItemPokeballDisc || item == PixelmonItemsPokeballs.ironDisc) {
				count++;
				if (count == 4) {
					count = 0;
					state++;
					if (state == 3) {
						state = 0;
						if (item == PixelmonItemsPokeballs.ironDisc)
							itemOnAnvil = PixelmonItemsPokeballs.ironBase.shiftedIndex;
						else
							itemOnAnvil -= 20;
					}
				}
			}
		}
	}
}
