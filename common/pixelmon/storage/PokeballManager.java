package pixelmon.storage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.google.common.eventbus.Subscribe;

import pixelmon.comm.ChatHandler;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumGui;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.Player;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.AnvilSaveHandler;
import net.minecraft.src.Chunk;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ISaveHandler;

import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.SaveHandler;
import net.minecraft.src.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

public class PokeballManager{
	private File workingDir;

	private ArrayList<PlayerStorage> playerPokemonList = new ArrayList<PlayerStorage>();

	public enum PokeballManagerMode {
		Player, Trainer
	}

	public PokeballManager() {
	}

	public PlayerStorage getPlayerStorage(EntityPlayerMP owner) {

		for (PlayerStorage p : playerPokemonList) {
			if (p.player.username.equals(owner.username))
				return p;
		}
		loadPlayer(owner);
		return getPlayerStorage(owner);
	}

	@SuppressWarnings("unchecked")
	public void loadPlayer(EntityPlayerMP player) {
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
				EntityPlayer player = playerPokemonList.get(i).player;
				boolean playerConnected = false;
				for (String playerName : MinecraftServer.getServer().getAllUsernames())
					if (player.username.equals(playerName)) {
						playerConnected = true;
						break;
					}

				if (playerConnected) {
					File playerSaveFile = new File(getSaveFolder(player) + player.username + ".pk");
					FileOutputStream f = new FileOutputStream(playerSaveFile);
					DataOutputStream s = new DataOutputStream(f);
					CompressedStreamTools.write(getData(player), s);
					s.close();
					f.close();
				} else {
					playerPokemonList.remove(i);
					i--;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private NBTTagCompound getData(EntityPlayer player) {
		for (PlayerStorage p : playerPokemonList)
			if (p.player == player) {
				NBTTagCompound n = new NBTTagCompound();
				p.writeToNBT(n);
				return n;
			}
		return null;
	}

	private String getSaveFolder(EntityPlayer player) {
		return "saves/" + player.worldObj.getSaveHandler().getSaveDirectoryName() + "/pokemon/";
	}

	@ForgeSubscribe
	public void onWorldLoad(WorldEvent.Load event) {
		playerPokemonList.clear();
	}

	@ForgeSubscribe
	public void onWorldSave(WorldEvent.Save event) {
		save();
	}

	public boolean hasPlayerFile(Player player) {
		File playerSaveFile = new File(getSaveFolder((EntityPlayerMP)player) + ((EntityPlayerMP)player).username + ".pk");
		return playerSaveFile.exists();
	}
}
