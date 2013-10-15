package pixelmon.storage;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.World;
import pixelmon.Pixelmon;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.EnumUpdateType;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.comm.PixelmonUpdatePacket;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.npcs.EntityTrainer;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumBossMode;
import pixelmon.enums.EnumPokeballs;
import pixelmon.pokedex.Pokedex;
import pixelmon.pokedex.Pokedex.DexRegisterStatus;
import pixelmon.storage.PokeballManager.PokeballManagerMode;

public class PlayerStorage {
	public NBTTagCompound[] partyPokemon = new NBTTagCompound[6];
	private int pokeDollars = 0;
	private static final int carryLimit = 6;
	public EntityPlayerMP player;
	public EntityTrainer trainer;
	public String userName;
	public String saveFile;
	public PokeballManagerMode mode;
	public Pokedex pokedex;
	public boolean guiOpened = false;

	public PlayerStorage(EntityPlayerMP player) {
		this.mode = PokeballManagerMode.Player;
		this.player = player;
		this.userName = player.username;
		if (MinecraftServer.getServer() instanceof DedicatedServer)
			this.saveFile = Pixelmon.modDirectory + "/" + player.worldObj.getSaveHandler().getWorldDirectoryName() + "/pokemon/" + player.username + ".pk";
		else
			this.saveFile = Pixelmon.modDirectory + "/saves/" + player.worldObj.getSaveHandler().getWorldDirectoryName() + "/pokemon/" + player.username
					+ ".pk";
		pokedex = new Pokedex(player);
	}

	public PlayerStorage(EntityTrainer trainer) {
		this.mode = PokeballManagerMode.Trainer;
		this.trainer = trainer;
	}

	public int getCurrency() {
		return pokeDollars;
	}

	public void setCurrency(int par1) {
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
		if (mode == PokeballManagerMode.Player && pokedex != null) {
			pokedex.set(Pokedex.nameToID(p.getName()), DexRegisterStatus.caught);
			pokedex.sendToPlayer((EntityPlayerMP) pokedex.owner);
		}
		if (p.getMoveset().size() == 0)
			p.loadMoveset();
		p.setBoss(EnumBossMode.Normal);
		if (!hasSpace()) {
			ChatHandler.sendChat(p.getOwner(), "Your party is full, " + p.getName() + " is sent to your computer!");
			PixelmonStorage.ComputerManager.getPlayerStorage(player).addToComputer(p);
			return;
		}
		if (p.caughtBall == null)
			p.caughtBall = EnumPokeballs.PokeBall;
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
					n.setInteger("Bukkit.MaxHealth", n.getInteger("StatsHP"));
					n.setBoolean("IsInBall", false);
					EntityPixelmon e = (EntityPixelmon) PixelmonEntityList.createEntityFromNBT(n, world);
					e.setBoss(EnumBossMode.Normal);
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
				player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.RemoveFromStorage,
						partyPokemon[pos].getInteger("pixelmonID")));
		}
		if (n != null) {
			n.setInteger("PixelmonOrder", pos);
			if (mode == PokeballManagerMode.Player)
				player.playerNetServerHandler.sendPacketToPlayer(new PixelmonDataPacket(n, EnumPackets.AddToStorage).getPacket());
		}
		partyPokemon[pos] = n;
	}

	public void addToFirstEmptySpace(NBTTagCompound n) {
		for (int i = 0; i < partyPokemon.length; i++) {
			if (partyPokemon[i] == null) {
				if (n != null) {
					n.setInteger("PixelmonOrder", i);
					if (mode == PokeballManagerMode.Player)
						player.playerNetServerHandler.sendPacketToPlayer(new PixelmonDataPacket(n, EnumPackets.AddToStorage).getPacket());
				}
				partyPokemon[i] = n;
				return;
			}
		}
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

	private void updateNBT(EntityPixelmon pixelmon) {
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
				}
			}
		}
	}

	public void update(NBTTagCompound nbt, EnumUpdateType type) {
		update(nbt, new EnumUpdateType[] { type });
	}

	public void update(NBTTagCompound nbt, EnumUpdateType[] types) {
		if (mode == PokeballManagerMode.Player)
			player.playerNetServerHandler.sendPacketToPlayer(new PixelmonUpdatePacket(nbt, types).getPacket());
	}

	public void update(EntityPixelmon pixelmon, EnumUpdateType type) {
		update(pixelmon, new EnumUpdateType[] { type });
	}

	public void update(EntityPixelmon pixelmon, EnumUpdateType[] types) {
		updateNBT(pixelmon);
		if (mode == PokeballManagerMode.Player)
			player.playerNetServerHandler.sendPacketToPlayer(new PixelmonUpdatePacket(pixelmon, types).getPacket());
	}

	public void sendUpdatedList() {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound nbt = partyPokemon[i];
			if (nbt != null) {
				if (mode == PokeballManagerMode.Player)
					player.playerNetServerHandler.sendPacketToPlayer(new PixelmonDataPacket(nbt, EnumPackets.UpdateStorage).getPacket());
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
		if (pokedex != null)
			pokedex.writeToNBT(var1);
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
		if (pokedex != null)
			pokedex.readFromNBT(var1);
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
		nbt.setFloat("HealF", (float) nbt.getInteger("StatsHP"));
		nbt.setBoolean("IsFainted", false);
		int numMoves = nbt.getInteger("PixelmonNumberMoves");
		for (int i = 0; i < numMoves; i++) {
			nbt.setInteger("PixelmonMovePP" + i, nbt.getInteger("PixelmonMovePPBase" + i));
		}
		int numStatus = nbt.getShort("StatusCount");
		for (int i = 0; i < numStatus; i++) {
			nbt.removeTag("Status" + i);
		}
		nbt.setShort("StatusCount", (short) 0);
		if (mode == PokeballManagerMode.Player)
			player.playerNetServerHandler.sendPacketToPlayer(new PixelmonDataPacket(nbt, EnumPackets.UpdateStorage).getPacket());
	}

	public void recallAllPokemon() {
		for (int i = 0; i < partyPokemon.length; i++) {
			if (partyPokemon[i] != null) {
				if (EntityAlreadyExists(partyPokemon[i].getInteger("pixelmonID"), player.worldObj)) {
					EntityPixelmon p = getAlreadyExists(partyPokemon[i].getInteger("pixelmonID"), player.worldObj);
					try {
						PixelmonStorage.PokeballManager.getPlayerStorage(player).retrieve(p);
					} catch (PlayerNotLoadedException e) {
						e.printStackTrace();
					}
					updateNBT(p);
					p.isInBall = true;
					p.unloadEntity();
				}
			}
		}
	}

	public int getPosition(int pokemonID) {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound n = partyPokemon[i];
			if (n != null) {
				if (n.getInteger("pixelmonID") == pokemonID)
					return n.getInteger("PixelmonOrder");
			}
		}
		return -1;
	}

	public int getNextPokemonId(int pokemonID) {
		for (int i = 0; i < partyPokemon.length; i++) {
			NBTTagCompound nbt = partyPokemon[i];
			if (nbt != null) {
				if (nbt.getInteger("pixelmonID") != pokemonID) {
					if (!nbt.getBoolean("IsFainted") && nbt.getShort("Health") >= 0)
						return nbt.getInteger("pixelmonID");
				}
			}
		}
		return -1;
	}
}
