package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.mod_Pixelmon;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.enums.EnumPokeballs;

public class SendPixelmon extends PacketHandlerBase {
	static EntityPokeBall currentPokeball = null;

	public SendPixelmon(){
		packetsHandled.add(EnumPackets.SendPokemon);
	}
	
	@Override
	public void handlePacket(int index, NetworkManager network, DataInputStream dataStream) throws IOException {
		EntityPlayer player = ((NetServerHandler) network.getNetHandler()).getPlayerEntity();
		int pokemonId = dataStream.readInt();
		NBTTagCompound nbt = mod_Pixelmon.pokeballManager.getPlayerStorage(player).getNBT(pokemonId);
		if (!mod_Pixelmon.pokeballManager.getPlayerStorage(player).EntityAlreadyExists(pokemonId, player.worldObj) && (currentPokeball == null || currentPokeball.isDead)
				&& !mod_Pixelmon.pokeballManager.getPlayerStorage(player).isFainted(pokemonId)) {
			player.worldObj.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / ((new Random()).nextFloat() * 0.4F + 0.8F));
			currentPokeball = new EntityPokeBall(player.worldObj, player, mod_Pixelmon.pokeballManager.getPlayerStorage(player).sendOut(pokemonId, player.worldObj).getHelper(),EnumPokeballs.PokeBall);
			boolean flag = nbt.getString("NickName") == null || nbt.getString("Nickname").isEmpty();
			ChatHandler.sendChat(player, "You sent out " + (flag ? nbt.getString("Name") : nbt.getString("Nickname")) + "!");
			player.worldObj.spawnEntityInWorld(currentPokeball);
		} else if (mod_Pixelmon.pokeballManager.getPlayerStorage(player).isFainted(pokemonId)) {
			boolean flag = nbt.getString("NickName") == null || nbt.getString("Nickname").isEmpty();
			ChatHandler.sendChat(player, (flag ? nbt.getString("Name") : nbt.getString("Nickname")) + " is unable to battle!");
		} else if (mod_Pixelmon.pokeballManager.getPlayerStorage(player).EntityAlreadyExists(pokemonId, player.worldObj)) {
			IHaveHelper pixelmon = mod_Pixelmon.pokeballManager.getPlayerStorage(player).getAlreadyExists(pokemonId, player.worldObj);
			if (pixelmon == null) {
				return;
			}
			if (mod_Pixelmon.battleRegistry.getBattle(player) != null
					&& (mod_Pixelmon.battleRegistry.getBattle(player).participant1.currentPokemon().getPokemonId() == pixelmon.getPokemonId() || mod_Pixelmon.battleRegistry.getBattle(player).participant2
							.currentPokemon().getPokemonId() == pixelmon.getPokemonId())) {
				ChatHandler.sendChat(player, pixelmon.getHelper().getName() + " is in a battle!");
				return;
			}

			if (pixelmon.getHelper().getOwner() == null)
				pixelmon.unloadEntity();
			else if (pixelmon.getHelper().getOwner() == player) {
				mod_Pixelmon.pokeballManager.getPlayerStorage(player).retrieve(pixelmon);
				pixelmon.catchInPokeball();
				boolean flag = nbt.getString("NickName") == null || nbt.getString("Nickname").isEmpty();
				ChatHandler.sendChat(player, "You retrieved " + (flag ? nbt.getString("Name") : nbt.getString("Nickname")) + "!");
			}
		}
	}
}
