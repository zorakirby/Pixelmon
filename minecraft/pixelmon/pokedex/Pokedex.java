package pixelmon.pokedex;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import pixelmon.comm.PixelmonPokedexPacket;
import pixelmon.database.DatabaseHelper;
import pixelmon.storage.PixelmonStorage;

public class Pokedex {

	public static final HashMap<Integer, PokedexEntry> fullPokedex = new HashMap<Integer, PokedexEntry>();
	public static final HashMap<String, Integer> nameToID = new HashMap<String, Integer>();
	public static final int pokedexSize = 649;

	static {
		try {
			ResultSet r = DatabaseHelper.getResultSet("select * from PIXELMON");
			while (r.next()) {
				String n = r.getString("PIXELMONFULLNAME");
				int i = r.getInt("NATIONALPOKEDEXNUMBER");
				String d = r.getString("POKEDEXDESCRIPTION");
				float w = r.getFloat("POKEDEXWEIGHT");
				float h = r.getFloat("POKEDEXHEIGHT");
				fullPokedex.put(i, new PokedexEntry(i, n, d, w, h));
				nameToID.put(n, i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public enum DexRegisterStatus {
		unknown, seen, caught;

		public static DexRegisterStatus get(int i) {
			return values()[i];
		}
	}

	// Dummy method to trigger the static block
	public static void init() {
	}

	public static int nameToID(String name) {
		if (!nameToID.containsKey(name))
			return 0;
		return nameToID.get(name);
	}

	public static boolean isEntryEmpty(int i) {
		if (!fullPokedex.containsKey(i))
			return true;
		if (fullPokedex.get(i).name == "???")
			return true;
		return false;
	}

	public Entity owner;
	private final HashMap<Integer, DexRegisterStatus> seenMap;

	public Pokedex() {
		this(null);
	}

	public Pokedex(Entity e) {
		owner = e;
		seenMap = new HashMap<Integer, DexRegisterStatus>();
	}

	public NBTTagCompound readFromNBT(NBTTagCompound nbt) {
		seenMap.clear();
		NBTTagList nbtl = nbt.getTagList("Pokedex");
		for (int i = 0; i < nbtl.tagCount(); i++) {
			try {
				String[] s = ((NBTTagString) nbtl.tagAt(i)).data.split(":");
				seenMap.put(Integer.parseInt(s[0]), DexRegisterStatus.get(Integer.parseInt(s[1])));
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return nbt;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagList nbtl = new NBTTagList("Pokedex");
		for (Entry<Integer, DexRegisterStatus> e : seenMap.entrySet())
			nbtl.appendTag(new NBTTagString("", e.getKey() + ":" + e.getValue().ordinal()));
		nbt.setTag("Pokedex", nbtl);
		return nbt;
	}

	public void sendToPlayer(EntityPlayerMP e) {
		PixelmonPokedexPacket p = new PixelmonPokedexPacket(this);
		e.playerNetServerHandler.sendPacketToPlayer(p.getPacket());
	}

	public void set(int id, DexRegisterStatus drs) {
		if (seenMap.containsKey(id))
			if (seenMap.get(id).ordinal() > drs.ordinal())
				return;
		if (id > 0 && id <= Pokedex.pokedexSize)
			seenMap.put(id, drs);
	}

	public DexRegisterStatus get(int id) {
		DexRegisterStatus d = seenMap.get(id);
		if (d == null)
			d = DexRegisterStatus.unknown;
		return d;
	}

	public boolean isUnknown(int id) {
		return get(id) == DexRegisterStatus.unknown;
	}

	public boolean hasSeen(int id) {
		DexRegisterStatus d = get(id);
		return d == DexRegisterStatus.seen || hasCaught(id);
	}

	public boolean hasCaught(int id) {
		return get(id) == DexRegisterStatus.caught;
	}

	public PokedexEntry getEntry(int id) {
		PokedexEntry e = fullPokedex.get(id);
		if (e == null)
			e = new PokedexEntryEmpty(id);
		return e;
	}
}
