package pixelmon.storage;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.comm.packetHandlers.PC.PCData;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PokeballManager.PokeballManagerMode;

public class ComputerBox {
	public boolean hasChanged = false;
	public static final int boxLimit = 30;
	public int position;
	private NBTTagCompound[] storedPokemon = new NBTTagCompound[boxLimit];
	private PlayerComputerStorage parentStorage;

	public ComputerBox(PlayerComputerStorage parentStorage, int position) {
		this.position = position;
		hasChanged = true;
		this.parentStorage = parentStorage;
	}

	public boolean hasSpace() {
		int count = 0;
		for (int i = 0; i < boxLimit; i++) {
			if (storedPokemon[i] != null)
				count++;
		}
		if (count < boxLimit)
			return true;
		return false;
	}

	public void add(EntityPixelmon p, int id) {
		NBTTagCompound n = new NBTTagCompound();
		p.setPokemonId(id);
		p.writeEntityToNBT(n);
		p.writeToNBT(n);
		n.setString("id", p.getName());
		n.setName(p.getName());
		n.setBoolean("IsInBall", true);
		n.setBoolean("IsShiny", p.getIsShiny());
		int pos = getNextSpace();
		n.setInteger("PixelmonOrder", pos);
		n.setInteger("BoxNumber", position);
		if (n.getShort("Health") > 0)
			n.setBoolean("IsFainted", false);
		storedPokemon[pos] = n;
		hasChanged = true;
	}

	public int getNextSpace() {
		for (int i = 0; i < boxLimit; i++)
			if (storedPokemon[i] == null)
				return i;
		return 0;
	}

	public NBTTagCompound get(int id) {
		for (NBTTagCompound n : storedPokemon) {
			if (n != null) {
				if (n.getInteger("pixelmonID") == id)
					return n;
			}
		}
		return null;
	}

	public NBTTagCompound[] getStoredPokemon() {
		for (int i = 0; i < storedPokemon.length; i++) {
			NBTTagCompound n = storedPokemon[i];
			if (n != null) {
				if (n.getString("Name").equals(""))
					storedPokemon[i] = null;
			}
		}
		return storedPokemon;
	}

	public NBTTagCompound getNBTByPosition(int pos) {
		return storedPokemon[pos];
	}

	public void load(NBTTagCompound boxTag) {
		for (int i = 0; i < boxLimit; i++)
			storedPokemon[i] = null;
		Iterator<NBTTagCompound> i = boxTag.getTags().iterator();

		while (i.hasNext()) {
			NBTTagCompound tag = i.next();
			tag.setName(tag.getString("Name"));
			int pos = tag.getInteger("PixelmonOrder");
			storedPokemon[pos] = tag;
		}
		hasChanged = false;
	}

	public void save(NBTTagCompound tag) {
		for (int i = 0; i < boxLimit; i++) {
			if (storedPokemon[i] != null) {
				tag.setCompoundTag("" + storedPokemon[i].getInteger("pixelmonID"), storedPokemon[i]);
			}
		}
		hasChanged = false;
	}

	public void addToFirstSpace(NBTTagCompound n) {
		for (int i = 0; i < boxLimit; i++) {
			if (storedPokemon[i] == null) {
				n.setInteger("PixelmonOrder", i);
				storedPokemon[i] = n;
				updatePCData(i);
				hasChanged = true;
				return;
			}
		}
	}

	public void changePokemon(int boxPos, NBTTagCompound n) {
		storedPokemon[boxPos] = n;
		updatePCData(boxPos);
		hasChanged = true;
	}

	private void updatePCData(int pos) {
		if (PCData.guiOpen.containsKey(parentStorage.player) && PCData.guiOpen.get(parentStorage.player)) {
			if (storedPokemon[pos] != null) {
				PixelmonDataPacket p = new PixelmonDataPacket(storedPokemon[pos], EnumPackets.AddToTempStore);
				((EntityPlayerMP) parentStorage.player).playerNetServerHandler.sendPacketToPlayer(p.getPacket());
			} else {
				((EntityPlayerMP) parentStorage.player).playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.RemoveFromTempStore,
						position, pos));
			}
		}
	}

	public boolean contains(int pokemonID) {
		for (NBTTagCompound n : storedPokemon) {
			if (n != null)
				if (n.getInteger("pixelmonID") == pokemonID)
					return true;
		}
		return false;
	}

	public EntityPixelmon getPokemonEntity(int pokemonID) {
		for (NBTTagCompound n : storedPokemon) {
			if (n != null) {
				if (n.getInteger("pixelmonID") == pokemonID) {
					n.setFloat("FallDistance", 0);
					n.setBoolean("IsInBall", false);
					EntityPixelmon e = (EntityPixelmon) PixelmonEntityList.createEntityFromNBT(n, parentStorage.player.worldObj);
					e.setOwner(parentStorage.player.username);
					e.playerOwned = true;
					e.motionX = e.motionY = e.motionZ = 0;
					e.isDead = false;
					return e;
				}
			}
		}
		return null;
	}

	public void updatePokemonEntry(EntityPixelmon p) {
		for (int i = 0; i < storedPokemon.length; i++) {
			NBTTagCompound nbt = storedPokemon[i];
			if (nbt != null) {
				if (nbt.getInteger("pixelmonID") == p.getPokemonId()) {
					NBTTagCompound n = new NBTTagCompound();
					p.writeEntityToNBT(n);
					p.writeToNBT(n);
					n.setString("id", p.getName());
					n.setName(p.getName());
					n.setBoolean("IsInBall", true);
					n.setBoolean("IsShiny", p.getIsShiny());
					int pos = getNextSpace();
					n.setInteger("PixelmonOrder", pos);
					n.setInteger("BoxNumber", position);
					if (n.getShort("Health") > 0)
						n.setBoolean("IsFainted", false);
					storedPokemon[i] = n;
					hasChanged = true;
				}
			}
		}
	}

	public NBTTagCompound getPokemonNBT(int id) {
		for (NBTTagCompound n : storedPokemon)
			if (n != null)
				if (n.getInteger("pixelmonID") == id)
					return n;

		return null;
	}

	public void updatePokemonNBT(int id, NBTTagCompound nbt) {
		for (int i = 0; i < storedPokemon.length; i++) {
			NBTTagCompound n = storedPokemon[i];
			if (n != null)
				if (n.getInteger("pixelmonID") == id){
					storedPokemon[i] = nbt;
					hasChanged = true;
				}
		}
	}
}
