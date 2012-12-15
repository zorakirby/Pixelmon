package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import pixelmon.comm.EnumPackets;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PixelmonStorage;
import cpw.mods.fml.common.network.Player;

public class PCClick extends PacketHandlerBase {

	private class MapEntry {
		public NBTTagCompound nbt;
		public int originalBox;
		public int originalPosition;

		public MapEntry(NBTTagCompound nbt, int origBox, int origPos) {
			this.nbt = nbt;
			originalBox = origBox;
			originalPosition = origPos;
		}
	}

	public HashMap<EntityPlayer, MapEntry> mousePokemon;

	public PCClick() {
		packetsHandled.add(EnumPackets.PCClick);
		mousePokemon = new HashMap<EntityPlayer, MapEntry>();
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream data) throws IOException {
		EntityPlayer player = (EntityPlayer) pl;
		int box = data.readInt();
		if (box == -2) {
			mousePokemon.put(player, null);
			return;
		} else if (box == -1) {
			int useless = data.readInt();
			int pos = data.readInt();
			int id = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).getIDFromPosition(pos);
			NBTTagCompound n = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).getNBT(id);
			NBTTagCompound n1 = null;
			if (mousePokemon.get(player) != null)
				n1 = mousePokemon.get(player).nbt;
			mousePokemon.put(player, new MapEntry(n, box, pos));
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).changePokemon(pos, n1);
			return;
		}
		// not used
		else if (box == -3) {
			int box1 = data.readInt();
			int boxPos = data.readInt();
			NBTTagCompound n = mousePokemon.get(player).nbt;
			mousePokemon.put(player, null);
			PixelmonStorage.ComputerManager.getPlayerStorage((EntityPlayerMP) player).changePokemon(box1, boxPos, n);
			return;
		} else if (box >= 0 && box <= 8) {
			int useless = data.readInt();
			int boxPos = data.readInt();
			NBTTagCompound n1 = null;
			if (mousePokemon.get(player) != null) {
				n1 = mousePokemon.get(player).nbt;
				if (n1 != null && PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).EntityAlreadyExists(n1.getInteger("pixelmonID"), player.worldObj)) {
					EntityPixelmon pixelmon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).getAlreadyExists(n1.getInteger("pixelmonID"), player.worldObj);
					PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).retrieve(pixelmon);
					pixelmon.catchInPokeball();
				}
			}
			NBTTagCompound n = PixelmonStorage.ComputerManager.getPlayerStorage(player).getBox(box).getNBTByPosition(boxPos);
			mousePokemon.put(player, new MapEntry(n, box, boxPos));
			PixelmonStorage.ComputerManager.getPlayerStorage(player).changePokemon(box, boxPos, n1);
			return;
		} else if (box == -4) {
			if (mousePokemon.get(player) == null)
				return;
			MapEntry e = mousePokemon.get(player);
			if (e != null && PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).EntityAlreadyExists(e.nbt.getInteger("pixelmonID"), player.worldObj)) {
				EntityPixelmon pixelmon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).getAlreadyExists(e.nbt.getInteger("pixelmonID"), player.worldObj);
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).retrieve(pixelmon);
				pixelmon.catchInPokeball();
			}
			mousePokemon.put(player, null);
			if (e.originalBox == -1)
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).changePokemon(e.originalPosition, e.nbt);
			else
				PixelmonStorage.ComputerManager.getPlayerStorage(player).changePokemon(e.originalBox, e.originalPosition, e.nbt);
			return;		
		}

	}
}
