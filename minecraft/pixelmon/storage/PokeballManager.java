package pixelmon.storage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import pixelmon.DownloadHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.network.Player;

public class PokeballManager {
	private File workingDir;

	private ArrayList<PlayerStorage> playerPokemonList = new ArrayList<PlayerStorage>();

	public enum PokeballManagerMode {
		Player, Trainer
	}

	public PokeballManager() {
	}

	public PlayerStorage getPlayerStorage(EntityPlayerMP owner) {

		for (PlayerStorage p : playerPokemonList) {
			if (p.player != null && owner != null && p.player.username.equals(owner.username))
				return p;
		}
		loadPlayer(owner);
		return getPlayerStorage(owner);
	}

	public EntityPlayerMP getPlayerFromName(String name) {
		for (PlayerStorage p : playerPokemonList)
			if (p.player.username.equals(name))
				return p.player;
		return null;
	}

	@SuppressWarnings("unchecked")
	public void loadPlayer(EntityPlayerMP player) {
		if (player == null)
			return;
		File saveDirPath = new File(getSaveFolder(player));
		if (!saveDirPath.exists())
			saveDirPath.mkdirs();
		File playerFile = new File(getSaveFolder(player) + player.username + ".pk");
		if (playerFile.exists()) {
			PlayerStorage p = new PlayerStorage(player);
			try {
				p.readFromNBT(CompressedStreamTools.read(new DataInputStream(new FileInputStream(playerFile))));
			} catch (FileNotFoundException e) {
				System.out.println("Couldn't read player data file for " + player.username);
			} catch (IOException e) {
				System.out.println("Couldn't read player data file for " + player.username);
			}
			playerPokemonList.add(p);
		} else {
			PlayerStorage p = new PlayerStorage(player);
			playerPokemonList.add(p);
		}
	}

	public void save() {
		try {
			for (int i = 0; i < playerPokemonList.size(); i++) {
				String userName = playerPokemonList.get(i).userName;
				File playerSaveFile = new File(playerPokemonList.get(i).saveFile);
				FileOutputStream f = new FileOutputStream(playerSaveFile);
				DataOutputStream s = new DataOutputStream(f);
				NBTTagCompound nbt = new NBTTagCompound();
				playerPokemonList.get(i).writeToNBT(nbt);
				CompressedStreamTools.write(nbt, s);
				s.close();
				f.close();
				if (playerPokemonList.get(i).player == null || playerPokemonList.get(i).player.playerNetServerHandler.connectionClosed) {
					playerPokemonList.remove(i);
					System.out.println("Saved dc'd player's data - " + userName);
					i--;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getSaveFolder(EntityPlayer player) {
		return DownloadHelper.getDir() + "/saves/" + player.worldObj.getSaveHandler().getSaveDirectoryName() + "/pokemon/";
	}

	@ForgeSubscribe
	public void onWorldLoad(WorldEvent.Load event) {
		ArrayList<EntityPlayerMP> playerList = new ArrayList<EntityPlayerMP>();
		for (int i = 0; i < playerPokemonList.size(); i++) {
			playerList.add(playerPokemonList.get(i).player);
		}
		playerPokemonList.clear();
		for (EntityPlayerMP player : playerList) {
			loadPlayer(player);
		}
	}

	@ForgeSubscribe
	public void onWorldSave(WorldEvent.Save event) {
		save();
	}

	public boolean hasPlayerFile(Player player) {
		File playerSaveFile = new File(getSaveFolder((EntityPlayerMP) player) + ((EntityPlayerMP) player).username + ".pk");
		return playerSaveFile.exists();
	}

	public void unloadDCPlayers() {
		save();
	}
}
