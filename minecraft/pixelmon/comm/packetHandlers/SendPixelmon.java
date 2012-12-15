package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import pixelmon.battles.BattleRegistry;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.storage.PixelmonStorage;
import cpw.mods.fml.common.network.Player;

public class SendPixelmon extends PacketHandlerBase {
	public static HashMap<EntityPlayer, EntityPokeBall> playerPokeballs;

	public SendPixelmon() {
		playerPokeballs = new HashMap<EntityPlayer, EntityPokeBall>();
		packetsHandled.add(EnumPackets.SendPokemon);
	}

	@Override
	public void handlePacket(int index, Player pl, DataInputStream dataStream) throws IOException {
		EntityPlayerMP player = (EntityPlayerMP) pl;
		int pokemonId = dataStream.readInt();
		NBTTagCompound nbt = PixelmonStorage.PokeballManager.getPlayerStorage(player).getNBT(pokemonId);
		if (nbt == null)
			return;
		if (!PixelmonStorage.PokeballManager.getPlayerStorage(player).EntityAlreadyExists(pokemonId, player.worldObj) && !PixelmonStorage.PokeballManager.getPlayerStorage(player).isFainted(pokemonId)) {

			if (playerPokeballs.get(player) != null && !playerPokeballs.get(player).isDead)
				return;

			EntityPixelmon pokemon = PixelmonStorage.PokeballManager.getPlayerStorage(player).sendOut(pokemonId, player.worldObj);
			EntityPokeBall pokeball = new EntityPokeBall(player.worldObj, player, pokemon, pokemon.caughtBall);
			playerPokeballs.put(player, pokeball);

			boolean flag = nbt.getString("NickName") == null || nbt.getString("Nickname").isEmpty();
			ChatHandler.sendChat(player, "You sent out " + (flag ? nbt.getString("Name") : nbt.getString("Nickname")) + "!");

			player.worldObj.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / ((new Random()).nextFloat() * 0.4F + 0.8F));
			player.worldObj.spawnEntityInWorld(pokeball);
		} else if (PixelmonStorage.PokeballManager.getPlayerStorage(player).isFainted(pokemonId)) {
			boolean flag = nbt.getString("NickName") == null || nbt.getString("Nickname").isEmpty();
			ChatHandler.sendChat(player, (flag ? nbt.getString("Name") : nbt.getString("Nickname")) + " is unable to battle!");
		} else if (PixelmonStorage.PokeballManager.getPlayerStorage(player).EntityAlreadyExists(pokemonId, player.worldObj)) {
			EntityPixelmon pixelmon = PixelmonStorage.PokeballManager.getPlayerStorage(player).getAlreadyExists(pokemonId, player.worldObj);
			if (pixelmon == null) {
				return;
			}
			if (BattleRegistry.getBattle(player) != null
					&& (BattleRegistry.getBattle(player).participant1.currentPokemon().getPokemonId() == pixelmon.getPokemonId() || BattleRegistry.getBattle(player).participant2.currentPokemon()
							.getPokemonId() == pixelmon.getPokemonId())) {
				if (pixelmon.battleController == null) {
					BattleRegistry.deRegisterBattle(BattleRegistry.getBattle(player));
				} else {
					ChatHandler.sendChat(player, pixelmon.getName() + " is in a battle!");
					return;
				}
			}

			if (pixelmon.getOwner() == null)
				pixelmon.unloadEntity();
			else if (pixelmon.getOwner() == player) {
				PixelmonStorage.PokeballManager.getPlayerStorage(player).retrieve(pixelmon);
				pixelmon.catchInPokeball();
				boolean flag = nbt.getString("NickName") == null || nbt.getString("Nickname").isEmpty();
				ChatHandler.sendChat(player, "You retrieved " + (flag ? nbt.getString("Name") : nbt.getString("Nickname")) + "!");
			}
		}
	}
}
