package pixelmon.blocks;

import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsPokeballs;
import pixelmon.items.ItemPokeballDisc;
import pixelmon.items.PixelmonItem;
import net.minecraft.src.Item;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.INetworkManager;
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
	public Packet getDescriptionPacket() {
		NBTTagCompound var1 = new NBTTagCompound();
		this.writeToNBT(var1);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		readFromNBT(pkt.customParam1);
	}

	@Override
	public void receiveClientEvent(int par1, int par2) {
		super.receiveClientEvent(par1, par2);
	}
	
	int count = 0;

	public boolean blockHit(int f) {
		boolean returnVal = false;
		if (itemOnAnvil != -1) {
			Item item = PixelmonItemsPokeballs.getItemFromID(itemOnAnvil);
			if (item instanceof ItemPokeballDisc || item == PixelmonItemsPokeballs.ironDisc) {
				count += f;
				if (count >= 16) {
					count = 0;
					state++;
					returnVal = true;
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
		return returnVal;
	}
}
