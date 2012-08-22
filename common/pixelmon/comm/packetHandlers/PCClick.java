package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

import cpw.mods.fml.common.network.Player;

import pixelmon.Pixelmon;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.enums.EnumGui;
import pixelmon.storage.ComputerBox;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerComputerStorage;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NetworkManager;

public class PCClick extends PacketHandlerBase {

	public HashMap<EntityPlayer, NBTTagCompound> mousePokemon;

	public PCClick() {
		packetsHandled.add(EnumPackets.PCClick);
		mousePokemon = new HashMap<EntityPlayer, NBTTagCompound>();
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream data) throws IOException {
		EntityPlayer player = (EntityPlayer)pl;
		int box = data.readInt();
		if(box == -2){
			mousePokemon.put(player, null);
			return;
		}
		else if(box == -1){
			int useless = data.readInt();
			int pos = data.readInt();
			int id = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).getIDFromPosition(pos);
			NBTTagCompound n = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).getNBT(id);
			NBTTagCompound n1 = mousePokemon.get(player);
			mousePokemon.put(player, n);
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).changePokemon(pos, n1);
			return;
		}
		//not used
		else if(box == -3){
			int box1 = data.readInt();
			int boxPos = data.readInt();
			NBTTagCompound n = mousePokemon.get(player);
			mousePokemon.put(player, null);
			PixelmonStorage.ComputerManager.getPlayerStorage((EntityPlayerMP)player).changePokemon(box1, boxPos, n);
			return;
		}
		else if(box >= 0 && box <= 8){
			int useless = data.readInt();
			int boxPos = data.readInt();
			NBTTagCompound n1 = mousePokemon.get(player);
			if (PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).EntityAlreadyExists(n1.getInteger("pixelmonID"), player.worldObj))
			{
				IHaveHelper pixelmon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).getAlreadyExists(n1.getInteger("pixelmonID"), player.worldObj);
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).retrieve(pixelmon);
				pixelmon.catchInPokeball();
			}	
			NBTTagCompound n = PixelmonStorage.ComputerManager.getPlayerStorage(player).getBox(box).getNBTByPosition(boxPos);
			mousePokemon.put(player, n);
			PixelmonStorage.ComputerManager.getPlayerStorage(player).changePokemon(box, boxPos, n1);
			return;
		}
		else if(box == -4){
			int box1 = data.readInt();
			int boxPos = data.readInt();
			NBTTagCompound n = mousePokemon.get(player);
			if (PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).EntityAlreadyExists(n.getInteger("pixelmonID"), player.worldObj))
			{
				IHaveHelper pixelmon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).getAlreadyExists(n.getInteger("pixelmonID"), player.worldObj);
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player).retrieve(pixelmon);
				pixelmon.catchInPokeball();
			}	
			mousePokemon.put(player, null);
			PixelmonStorage.ComputerManager.getPlayerStorage(player).changePokemon(box1, boxPos, n);
			return;
		}
		else if(box == -5){
//			PlayerComputerStorage s = PixelmonStorage.ComputerManager.getPlayerStorage(player);
//			for(ComputerBox b : s.getBoxList()){
//				for(NBTTagCompound n: b.getStoredPokemon()){
//					if (n != null) {
//						PixelmonDataPacket p = new PixelmonDataPacket(n, EnumPackets.AddToTempStore);
//						((EntityPlayerMP)player).serverForThisPlayer.sendPacketToPlayer(p.getPacket());
//					}
//				}
//			}
//			player.openGui(Pixelmon.instance, EnumGui.PC.getIndex(), player.worldObj, 0,0,0);
		}	
		
	}
}
