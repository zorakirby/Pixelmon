package pixelmon;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import pixelmon.storage.PlayerStorage;

public class PixelmonMethods {
	public static ArrayList<EntityPixelmon> getAllActivePokemon(EntityPlayer player){
		ArrayList<EntityPixelmon> list = new ArrayList<EntityPixelmon>();
		try {
			PlayerStorage storage = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)player);
			for (NBTTagCompound nbt: storage.partyPokemon){
				if (storage.EntityAlreadyExists(nbt.getInteger("pixelmonID"), player.worldObj))
					list.add(storage.getAlreadyExists(nbt.getInteger("pixelmonID"), player.worldObj));
			}
		} catch (PlayerNotLoadedException e) {
		}
		
		
		return list;
	}
}
