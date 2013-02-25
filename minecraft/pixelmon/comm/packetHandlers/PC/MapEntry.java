package pixelmon.comm.packetHandlers.PC;

import net.minecraft.nbt.NBTTagCompound;

public class MapEntry {

	public NBTTagCompound nbt;
	public int originalBox;
	public int originalPosition;

	public MapEntry(NBTTagCompound nbt, int origBox, int origPos) {
		this.nbt = nbt;
		originalBox = origBox;
		originalPosition = origPos;
	}

}
