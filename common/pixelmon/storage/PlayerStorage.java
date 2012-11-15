package pixelmon.storage;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.EntityRegistry;

import pixelmon.Pixelmon;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.trainers.EntityTrainer;
import pixelmon.enums.EnumPokeballs;
import pixelmon.storage.PokeballManager.PokeballManagerMode;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;

import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet;
import net.minecraft.src.World;
import net.minecraftforge.common.MinecraftForge;

public class PlayerStorage {
	public NBTTagCompound[] partyPokemon = new NBTTagCompound[6];
	private static int pokeDollars = 0;
	private static final int carryLimit = 6;
	public EntityPlayerMP player;
	public EntityTrainer trainer;
	public String userName;
	public String saveFile;
	public PokeballManagerMode mode;
	public boolean guiOpened = false;

	public PlayerStorage(EntityPlayerMP player) {
		this.mode = PokeballManagerMode.Player;
		this.player = player;
		this.userName = player.username;
		this.saveFile = "saves/" + player.worldObj.getSaveHandler().getSaveDirectoryName() + "/pokemon/" + player.username + ".pk";
	}

	public PlayerStorage(EntityTrainer trainer) {
		this.mode = PokeballManagerMode.Trainer;
		this.trainer = trainer;
	}

	public static int getCurrency() {
		return pokeDollars;
	}

	public static void setCurrency(int par1) {
		pokeDollars = par1;
		if (pokeDollars >= 999999) {
			pokeDollars = 999999;
		}
		if (pokeDollars <= 0) {
			pokeDollars = 0;
		}
	}

	public boolean hasSpace() {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound nbt = partyPokemon[i];
			if (nbt == null) {
				return true;
			}
		}
		return false;
	}

	public int getNextOpen() {
		for (int i = 0; i < partyPokemon.length; i++) {
			if (partyPokemon[i] == null) {
				return i;
			}
		}
		return 0;
	}

	public void setPokemon(NBTTagCompound[] pokemon) {
		partyPokemon = pokemon;
	}

	public void addToParty(EntityPixelmon p) {
		if (p.moveset.size() == 0)
			p.loadMoveset();
		if (!hasSpace()) {
			ChatHandler.sendChat(p.getOwner(), "Your party is full, " + p.getName() + " is sent to your computer!");
			PixelmonStorage.ComputerManager.getPlayerStorage(player).addToComputer(p);
			return;
		}
		if (p.caughtBall == null)
			p.caughtBall = EnumPokeballs.MasterBall;
		if (mode == PokeballManagerMode.Player)
			p.setOwner(player.username);
		else if (mode == PokeballManagerMode.Trainer)
			p.setTrainer(trainer);
		NBTTagCompound n = new NBTTagCompound();
		int id = 0;
		if (mode == PokeballManagerMode.Player)
			id = new Random().nextInt(32000);
		else if (mode == PokeballManagerMode.Trainer)
			id = new Random().nextInt(32000) * -1 - 1;
		boolean isUsed = false;
		do {
			isUsed = false;
			for (int i = 0; i < partyPokemon.length; i++) {
				NBTTagCompound nbt = partyPokemon[i];
				if (nbt != null) {
					if (mode == PokeballManagerMode.Player) {
						id = new Random().nextInt(32000);
					} else if (mode == PokeballManagerMode.Trainer) {
						id = new Random().nextInt(32000) * -1 - 1;
					}
				}
			}
		} while (contains(id));

		p.setPokemonId(id);
		p.writeEntityToStorageNBT(n);
		p.writeToNBT(n);
		n.setString("id", "Pixelmon");
		n.setName(p.getName());
		n.setBoolean("IsInBall", true);
		n.setBoolean("IsShiny", p.getIsShiny());
		n.setInteger("PixelmonOrder", getNextOpen());
		if (p.getHeldItem() != null) {
			n.setCompoundTag("Held Item", p.getHeldItem().writeToNBT(new NBTTagCompound()));
		}
		partyPokemon[getNextOpen()] = n;
		if (p.getHealth() > 0)
			n.setBoolean("IsFainted", false);
		if (mode == PokeballManagerMode.Player)
			((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(new PixelmonDataPacket(n, EnumPackets.AddToStorage).getPacket());
	}

	public void retrieve(EntityPixelmon currentPixelmon) {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound n = partyPokemon[i];
			if (n != null) {
				if (n.getInteger("pixelmonID") == currentPixelmon.getPokemonId()) {
					currentPixelmon.writeEntityToStorageNBT(n);
					currentPixelmon.writeToNBT(n);
					n.setName(currentPixelmon.getName());
					n.setBoolean("IsInBall", true);
					currentPixelmon.unloadEntity();
				}
			}
		}
	}

	public boolean contains(int id) {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound nbt = partyPokemon[i];
			if (nbt != null) {
				if (nbt.getInteger("pixelmonID") == id)
					return true;
			}
		}
		return false;
	}

	public EntityPixelmon sendOut(int id, World world) {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound n = partyPokemon[i];
			if (n != null) {
				if (n.getInteger("pixelmonID") == id) {
					n.setFloat("FallDistance", 0);
					n.setBoolean("IsInBall", false);
					EntityPixelmon e = (EntityPixelmon) PixelmonEntityList.createEntityFromNBT(n, world);
					if (mode == PokeballManagerMode.Player) {
						e.setOwner(player.username);
						e.playerOwned = true;
					} else
						e.setTrainer(trainer);
					e.motionX = e.motionY = e.motionZ = 0;
					e.isDead = false;
					return e;
				}
			}
		}
		return null;
	}

	public NBTTagCompound getNBT(int id) {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound nbt = partyPokemon[i];
			if (nbt != null) {
				if (nbt.getInteger("pixelmonID") == id)
					return nbt;
			}
		}
		return null;
	}

	public NBTTagCompound[] getList() {
		return partyPokemon;
	}

	public void replace(EntityPixelmon entityPixelmon, EntityPixelmon entityCapturedPixelmon) {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound nbt = partyPokemon[i];
			if (nbt != null) {
				if (nbt.getInteger("pixelmonID") == entityPixelmon.getPokemonId()) {
					entityCapturedPixelmon.setPokemonId(entityPixelmon.getPokemonId());
					entityCapturedPixelmon.writeEntityToStorageNBT(nbt);
					entityCapturedPixelmon.writeToNBT(nbt);
					nbt.setString("id", entityCapturedPixelmon.getName());
					nbt.setName(entityCapturedPixelmon.getName());
					if (mode == PokeballManagerMode.Player)
						player.playerNetServerHandler.sendPacketToPlayer(new PixelmonDataPacket(nbt, EnumPackets.UpdateStorage).getPacket());
				}
			}
		}
	}

	public void changePokemon(int pos, NBTTagCompound n) {
		if (partyPokemon[pos] != null) {
			if (mode == PokeballManagerMode.Player)
				player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.RemoveFromStorage, partyPokemon[pos].getInteger("pixelmonID")));
		}
		if (n != null) {
			n.setInteger("PixelmonOrder", pos);
			if (mode == PokeballManagerMode.Player)
				player.playerNetServerHandler.sendPacketToPlayer(new PixelmonDataPacket(n, EnumPackets.AddToStorage).getPacket());
		}
		partyPokemon[pos] = n;
	}

	public int count() {
		int count = 0;
		for (int i = 0; i < partyPokemon.length; i++)
			if (partyPokemon[i] != null)
				count++;
		return count;
	}

	public int countAblePokemon() {
		int c = 0;
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound nbt = partyPokemon[i];
			if (nbt != null) {
				if (!nbt.getBoolean("IsFainted") && nbt.getShort("Health") > 0)
					c++;
			}
		}
		return c;
	}

	public boolean isIn(EntityPixelmon entityPixelmon) {
		return contains(entityPixelmon.getPokemonId());
	}

	public boolean hasSentOut(int pixelmonID) {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound nbt = partyPokemon[i];
			if (nbt != null) {
				if (nbt.getInteger("pixelmonID") == pixelmonID)
					if (!nbt.getBoolean("IsInBall"))
						return true;
			}
		}
		return false;
	}

	public boolean isFainted(int pokemonId) {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound nbt = partyPokemon[i];
			if (nbt != null) {
				if (nbt.getInteger("pixelmonID") == pokemonId) {
					if (nbt.getBoolean("IsFainted"))
						return true;
					if (nbt.getShort("Health") <= 0)
						return true;
				}
			}
		}
		return false;
	}

	public void updateNBT(EntityPixelmon pixelmon) {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound nbt = partyPokemon[i];
			if (nbt != null) {
				if (nbt.getInteger("pixelmonID") == pixelmon.getPokemonId()) {
					pixelmon.writeEntityToStorageNBT(nbt);
					pixelmon.writeToNBT(nbt);
					nbt.setString("id", pixelmon.getName());
					nbt.setName(pixelmon.getName());
					if (pixelmon.getHealth() <= 0)
						nbt.setBoolean("IsFainted", true);
					if (mode == PokeballManagerMode.Player)
						player.playerNetServerHandler.sendPacketToPlayer(new PixelmonDataPacket(nbt, EnumPackets.UpdateStorage).getPacket());
				}
			}
		}
	}

	public int getIDFromPosition(int pos) {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound n = partyPokemon[i];
			if (n != null) {
				if (n.getInteger("PixelmonOrder") == pos)
					return n.getInteger("pixelmonID");
			}
		}
		return -1;
	}

	public boolean EntityAlreadyExists(int id, World world) {

		@SuppressWarnings("unchecked")
		List<Entity> EntityList = world.loadedEntityList;
		for (int i = 0; i < EntityList.size(); i++) {
			Entity e = EntityList.get(i);
			if (e instanceof EntityPixelmon) {
				if (((EntityPixelmon) e).getPokemonId() == id) {
					return true;
				}
			}
		}
		return false;
	}

	public EntityPixelmon getAlreadyExists(int id, World world) {
		if (id == -1) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<Entity> EntityList = world.loadedEntityList;
		for (int i = 0; i < EntityList.size(); i++) {
			Entity e = EntityList.get(i);
			if (e instanceof EntityPixelmon) {
				if (((EntityPixelmon) e).getPokemonId() == id) {
					return (EntityPixelmon) e;
				}
			}
		}
		return null;
	}

	public void writeToNBT(NBTTagCompound var1) {
		var1.setInteger("pixelDollars", pokeDollars);
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound e = partyPokemon[i];
			if (e != null) {
				if (EntityAlreadyExists(e.getInteger("pixelmonID"), player.worldObj)) {
					EntityPixelmon pixelmon = getAlreadyExists(e.getInteger("pixelmonID"), player.worldObj);
					updateNBT(pixelmon);
				}
				e.setInteger("PixelmonOrder", i);
				var1.setCompoundTag("" + e.getInteger("pixelmonID"), e);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void readFromNBT(NBTTagCompound var1) {
		pokeDollars = var1.getInteger("pixelDollars");
		Iterator iterator = var1.getTags().iterator();
		do {
			if (!iterator.hasNext())
				break;

			NBTBase nbtbase = (NBTBase) iterator.next();

			if (nbtbase instanceof NBTTagCompound) {
				NBTTagCompound pokemonData = (NBTTagCompound) nbtbase;
				pokemonData.setName(pokemonData.getString("Name"));
				partyPokemon[pokemonData.getInteger("PixelmonOrder")] = pokemonData;
				if (mode == PokeballManagerMode.Player)
					player.playerNetServerHandler.sendPacketToPlayer(new PixelmonDataPacket(pokemonData, EnumPackets.AddToStorage).getPacket());
			}
		} while (true);
	}

	public EntityPixelmon getFirstAblePokemon(World world) {
		for (int i = 0; i < carryLimit; i++) {
			int id = getIDFromPosition(i);
			if (id != -1 && !isFainted(id))
				return sendOut(id, world);
		}
		return null;
	}

	public int getFirstAblePokemonID(World worldObj) {
		for (int i = 0; i < carryLimit; i++) {
			int id = getIDFromPosition(i);
			if (id != -1 && !isFainted(id))
				return id;
		}
		return -1;
	}

	public void healAllPokemon() {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound nbt = partyPokemon[i];
			if (nbt != null) {
				heal(nbt);
			}
		}
	}

	public EntityPixelmon sendOutFromPosition(int pos, World worldObj) {
		return sendOut(getIDFromPosition(pos), worldObj);
	}

	public void heal(int index) {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound nbt = partyPokemon[i];
			if (nbt != null) {
				if (nbt.getInteger("pixelmonID") == index)
					heal(nbt);
			}
		}
	}

	private void heal(NBTTagCompound nbt) {
		nbt.setShort("Health", (short) nbt.getInteger("StatsHP"));
		nbt.setBoolean("IsFainted", false);
		int numMoves = nbt.getInteger("PixelmonNumberMoves");
		for (int i = 0; i < numMoves; i++) {
			nbt.setInteger("PixelmonMovePP" + i, nbt.getInteger("PixelmonMovePPBase" + i));
		}
		int numStatus = nbt.getShort("EffectCount");
		for (int i = 0; i < numStatus; i++) {
			nbt.removeTag("Effect" + i);
		}
		nbt.setShort("EffectCount", (short) 0);
		if (mode == PokeballManagerMode.Player)
			player.playerNetServerHandler.sendPacketToPlayer(new PixelmonDataPacket(nbt, EnumPackets.UpdateStorage).getPacket());
	}

	public void recallAllPokemon() {
		for (int i = 0; i < partyPokemon.length; i++) {
			if (partyPokemon[i] != null) {
				if (EntityAlreadyExists(partyPokemon[i].getInteger("pixelmonID"), player.worldObj)) {
					EntityPixelmon p = getAlreadyExists(partyPokemon[i].getInteger("pixelmonID"), player.worldObj);
					PixelmonStorage.PokeballManager.getPlayerStorage(player).retrieve(p);
					updateNBT(p);
					p.isInBall = true;
					p.unloadEntity();
				}
			}
		}
	}
}
