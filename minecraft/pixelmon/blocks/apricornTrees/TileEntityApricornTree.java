package pixelmon.blocks.apricornTrees;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import pixelmon.enums.EnumApricornTrees;

public class TileEntityApricornTree extends TileEntity {
	
	public EnumApricornTrees tree;
	
	public long timeLastWatered;

	public TileEntityApricornTree() {
	};

	public TileEntityApricornTree(EnumApricornTrees tree) {
		this.tree = tree;
		this.timeLastWatered = (long) 0;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setLong("TimeLastWatered", timeLastWatered);
		nbt.setShort("ApricornTreeID", (short) tree.id);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		tree = EnumApricornTrees.getFromID(nbt.getShort("ApricornTreeID"));
		timeLastWatered = nbt.getLong("TimeLastWatered");		
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
	}
	
	public boolean wasWateredToday(){
		WorldServer worldserver = MinecraftServer.getServer().worldServers[0];
		long tickTimeSinceMidnight = worldserver.getWorldTime();
		long tickTimeSinceLastWatering = (worldserver.getTotalWorldTime() - timeLastWatered);
				
		if (tickTimeSinceLastWatering > tickTimeSinceMidnight){
			return false;
		}
		return true;
	}
	
	public void updateWatering(){
		WorldServer worldserver = MinecraftServer.getServer().worldServers[0];
		timeLastWatered = worldserver.getTotalWorldTime();
		
		Packet132TileEntityData packet = (Packet132TileEntityData) this.getDescriptionPacket();
		NBTTagCompound nbt = packet.customParam1;
		
		nbt.setLong("TimeLastWatered", timeLastWatered);
		this.writeToNBT(nbt);
	}
	
}
