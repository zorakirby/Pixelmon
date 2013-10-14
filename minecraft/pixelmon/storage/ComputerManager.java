package pixelmon.storage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import pixelmon.Pixelmon;
import pixelmon.config.PixelmonConfig;

public class ComputerManager {
	private File workingDir;
	private ArrayList<PlayerComputerStorage> playerComputerList = new ArrayList<PlayerComputerStorage>();

	public ComputerManager() {
	}

	public PlayerComputerStorage getPlayerStorage(EntityPlayer owner) {
		for (PlayerComputerStorage p : playerComputerList) {
			if (p.player.username.equals(owner.username))
				return p;
		}
		loadPlayer(owner);
		return getPlayerStorage(owner);
	}

	@SuppressWarnings("unchecked")
	private void loadPlayer(EntityPlayer player) {
		File saveDirPath = new File(getSaveFolder(player));
		if (!saveDirPath.exists())
			saveDirPath.mkdirs();
		File playerFile = new File(getSaveFolder(player) + player.username + ".comp");
		if (playerFile.exists()) {
			PlayerComputerStorage p = new PlayerComputerStorage(player);
			try {
				p.readFromNBT(CompressedStreamTools.read(new DataInputStream(new FileInputStream(playerFile))));
			} catch (FileNotFoundException e) {
				if (PixelmonConfig.printErrors)
					System.out.println("Couldn't read player data file for " + player.username);
			} catch (IOException e) {
				if (PixelmonConfig.printErrors)
					System.out.println("Couldn't read player data file for " + player.username);
			}
			playerComputerList.add(p);
		} else {
			PlayerComputerStorage p = new PlayerComputerStorage(player);
			playerComputerList.add(p);
		}

	}

	public void saveAll() {
		for (int i = 0; i < playerComputerList.size(); i++) {
			savePlayer(playerComputerList.get(i));
		}
	}

	public void savePlayer(PlayerComputerStorage storage) {
		try {
			EntityPlayer player = storage.player;
			File playerSaveFile = new File(storage.saveFile + "temp");
			if (getPlayerStorage(player).hasChanges()) {
				FileOutputStream f = new FileOutputStream(playerSaveFile);
				DataOutputStream s = new DataOutputStream(f);
				NBTTagCompound nbt = new NBTTagCompound();
				storage.writeToNBT(nbt);
				CompressedStreamTools.write(nbt, s);
				s.close();
				f.close();
				replaceSaveFile(storage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void replaceSaveFile(PlayerComputerStorage storage) throws IOException {
		File playerSaveTempFile = new File(storage.saveFile + "temp");
		File playerSaveFile = new File(storage.saveFile);
		if (!playerSaveFile.exists()) {
			playerSaveFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(playerSaveTempFile).getChannel();
			destination = new FileOutputStream(playerSaveFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	private NBTTagCompound getData(EntityPlayer player) {
		for (PlayerComputerStorage p : playerComputerList)
			if (p.player == player) {
				NBTTagCompound n = new NBTTagCompound();
				p.writeToNBT(n);
				return n;
			}
		return null;
	}

	private String getSaveFolder(EntityPlayer player) {
		if (MinecraftServer.getServer() instanceof DedicatedServer)
			return Pixelmon.modDirectory + "/" + player.worldObj.getSaveHandler().getWorldDirectoryName() + "/pokemon/";
		else
			return Pixelmon.modDirectory + "/saves/" + player.worldObj.getSaveHandler().getWorldDirectoryName() + "/pokemon/";
	}

	@ForgeSubscribe
	public void onWorldLoad(WorldEvent.Load event) {
		playerComputerList.clear();
	}

	@ForgeSubscribe
	public void onWorldSave(WorldEvent.Save event) {
		saveAll();
	}

	public void onPlayerDC(EntityPlayer player) {
		if (player == null)
			return;
		for (int i = 0; i < playerComputerList.size(); i++) {
			if (playerComputerList.get(i).player == player) {
				savePlayer(playerComputerList.get(i));
				playerComputerList.remove(i);
				break;
			}
		}
	}

	public void playerLoggedIn(EntityPlayerMP player) {
		if (player == null)
			return;
		for (int i = 0; i < playerComputerList.size(); i++) {
			if (playerComputerList.get(i).player == player) {
				playerComputerList.remove(i);
				break;
			}
		}
	}
}
