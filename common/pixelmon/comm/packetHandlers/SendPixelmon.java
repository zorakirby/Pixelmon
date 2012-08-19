package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import cpw.mods.fml.common.network.Player;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.NetworkManager;
import pixelmon.battles.BattleRegistry;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.enums.EnumPokeballs;
import pixelmon.items.ItemPokeBall;
import pixelmon.storage.PixelmonStorage;

public class SendPixelmon extends PacketHandlerBase {

	public SendPixelmon(){
		packetsHandled.add(EnumPackets.SendPokemon);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		EntityPlayerMP player = (EntityPlayerMP)pl;
		int pokemonId = dataStream.readInt();
		NBTTagCompound nbt = PixelmonStorage.PokeballManager.getPlayerStorage(player).getNBT(pokemonId);
		if (!PixelmonStorage.PokeballManager.getPlayerStorage(player).EntityAlreadyExists(pokemonId, player.worldObj)
				&& !PixelmonStorage.PokeballManager.getPlayerStorage(player).isFainted(pokemonId)) {
			if(ItemPokeBall.ballTimer > 0)
			{
				return;
			}
			ItemPokeBall.ballTimer = 40;
			
			PixelmonEntityHelper helper = PixelmonStorage.PokeballManager.getPlayerStorage(player).sendOut(pokemonId, player.worldObj).getHelper();
			EntityPokeBall pokeball = new EntityPokeBall(player.worldObj, player, helper, helper.caughtBall);
			
			boolean flag = nbt.getString("NickName") == null || nbt.getString("Nickname").isEmpty();
			ChatHandler.sendChat(player, "You sent out " + (flag ? nbt.getString("Name") : nbt.getString("Nickname")) + "!");

			player.worldObj.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / ((new Random()).nextFloat() * 0.4F + 0.8F));
			player.worldObj.spawnEntityInWorld(pokeball);
		} else if (PixelmonStorage.PokeballManager.getPlayerStorage(player).isFainted(pokemonId)) {
			boolean flag = nbt.getString("NickName") == null || nbt.getString("Nickname").isEmpty();
			ChatHandler.sendChat(player, (flag ? nbt.getString("Name") : nbt.getString("Nickname")) + " is unable to battle!");
		} else if (PixelmonStorage.PokeballManager.getPlayerStorage(player).EntityAlreadyExists(pokemonId, player.worldObj)) {
			IHaveHelper pixelmon = PixelmonStorage.PokeballManager.getPlayerStorage(player).getAlreadyExists(pokemonId, player.worldObj);
			if (pixelmon == null) {
				return;
			}
			if (BattleRegistry.getBattle(player) != null
					&& (BattleRegistry.getBattle(player).participant1.currentPokemon().getPokemonId() == pixelmon.getPokemonId() || BattleRegistry.getBattle(player).participant2
							.currentPokemon().getPokemonId() == pixelmon.getPokemonId())) {
				ChatHandler.sendChat(player, pixelmon.getHelper().getName() + " is in a battle!");
				return;
			}

			if (pixelmon.getHelper().getOwner() == null)
				pixelmon.unloadEntity();
			else if (pixelmon.getHelper().getOwner() == player) {
				PixelmonStorage.PokeballManager.getPlayerStorage(player).retrieve(pixelmon);
				pixelmon.catchInPokeball();
				boolean flag = nbt.getString("NickName") == null || nbt.getString("Nickname").isEmpty();
				ChatHandler.sendChat(player, "You retrieved " + (flag ? nbt.getString("Name") : nbt.getString("Nickname")) + "!");
			}
		}
	}
}
