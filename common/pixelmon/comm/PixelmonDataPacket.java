package pixelmon.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.NetworkMod;

import pixelmon.database.DatabaseStats;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.stats.BaseStats;
import pixelmon.enums.EnumType;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.DataWatcher;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet;
import net.minecraftforge.common.MinecraftForge;

public class PixelmonDataPacket extends PixelmonPacket {
	public int pokemonID;
	public int nationalPokedexNumber;
	public String name;
	public String nickname;
	public int lvl;
	public int hp;
	public int health;
	public boolean isMale;
	public boolean isFainted;
	public int order;
	public int numMoves;
	public EnumType type1;
	public EnumType type2;
	public int HP;
	public int Speed;
	public int Attack;
	public int Defence;
	public int SpecialAttack;
	public int SpecialDefence;
	public int nextLvlXP;
	public int boxNumber = 0;
	public boolean isShiny;
	public boolean hasOwner;
	public boolean doesLevel;
	public int heldItemId = -1;

	public PixelmonMovesetDataPacket[] moveset = new PixelmonMovesetDataPacket[4];

	public PixelmonDataPacket() {

	}

	public PixelmonDataPacket(NBTTagCompound p, EnumPackets packetType) {
		this.packetType = packetType;
		pokemonID = p.getInteger("pixelmonID");
		BaseStats b = DatabaseStats.GetBaseStats(p.getString("Name"));
		nationalPokedexNumber = b.nationalPokedexNumber;
		name = p.getString("Name");
		nickname = p.getString("Nickname");
		lvl = p.getInteger("Level");
		nextLvlXP = p.getInteger("EXPToNextLevel");
		hp = p.getInteger("StatsHP");
		health = p.getShort("Health");
		isMale = p.getBoolean("IsMale");
		isFainted = p.getBoolean("IsFainted");
		isShiny = p.getBoolean("IsShiny");
		order = p.getInteger("PixelmonOrder");
		numMoves = p.getInteger("PixelmonNumberMoves");
		for (int i = 0; i < numMoves; i++) {
			moveset[i] = PixelmonMovesetDataPacket.createPacket(p, i);
		}
		type1 = b.Type1;
		type2 = b.Type2;
		HP = p.getInteger("StatsHP");
		Speed = p.getInteger("StatsSpeed");
		Attack = p.getInteger("StatsAttack");
		Defence = p.getInteger("StatsDefence");
		SpecialAttack = p.getInteger("StatsSpecialAttack");
		SpecialDefence = p.getInteger("StatsSpecialDefence");
		if (p.hasKey("Held Item")) {
			heldItemId = p.getInteger("HeldItemId");
		}
		if (p.hasKey("BoxNumber"))
			boxNumber = p.getInteger("BoxNumber");
		hasOwner = true;
		doesLevel = p.getBoolean("DoesLevel");
	}

	public PixelmonDataPacket(EntityPixelmon p, EnumPackets packetType) {
		this.packetType = packetType;
		pokemonID = p.getPokemonId();
		nationalPokedexNumber = p.baseStats.nationalPokedexNumber;
		name = p.getName();
		nickname = p.getNickname();
		lvl = p.getLvl().getLevel();
		nextLvlXP = p.getLvl().getExpToNextLevel();
		hp = p.stats.HP;
		health = p.getHealth();
		isMale = p.isMale;
		isFainted = p.isFainted;
		isShiny = p.getIsShiny();
		order = 0;
		if (p.moveset.size() == 0)
			p.loadMoveset();
		numMoves = p.moveset.size();
		for (int i = 0; i < numMoves; i++) {
			moveset[i] = PixelmonMovesetDataPacket.createPacket(p.moveset, i);
		}
		type1 = p.type.get(0);
		if (p.type.size() == 1)
			type2 = EnumType.Mystery;
		else
			type2 = p.type.get(1);
		HP = p.stats.HP;
		Speed = p.stats.Speed;
		Attack = p.stats.Attack;
		Defence = p.stats.Defence;
		SpecialAttack = p.stats.SpecialAttack;
		SpecialDefence = p.stats.SpecialDefence;
		if (p.heldItem!=null)
			heldItemId = p.heldItem.itemID;
		hasOwner = p.getOwner() != null || p.getTrainer() != null;
		doesLevel = p.doesLevel;
	}

	@Override
	public int getID() {
		return packetType.getIndex();
	}

	@Override
	public void writePacketData(DataOutputStream data) throws IOException {
		data.writeInt(pokemonID);
		data.writeShort(nationalPokedexNumber);
		Packet.writeString(name, data);
		Packet.writeString(nickname, data);
		data.writeShort(lvl);
		data.writeShort(nextLvlXP);
		data.writeShort(hp);
		data.writeShort(health);
		data.writeBoolean(isMale);
		data.writeBoolean(isFainted);
		data.writeBoolean(hasOwner);
		data.writeShort(order);
		data.writeShort(numMoves);
		for (int i = 0; i < numMoves; i++) {
			moveset[i].writeData(data);
		}
		data.writeShort(type1.getIndex());
		data.writeShort(type2.getIndex());
		data.writeShort(HP);
		data.writeShort(Speed);
		data.writeShort(Attack);
		data.writeShort(Defence);
		data.writeShort(SpecialAttack);
		data.writeShort(SpecialDefence);
		data.writeShort(boxNumber);
		data.writeBoolean(isShiny);
		data.writeBoolean(doesLevel);
		data.writeInt(heldItemId);
	}

	@Override
	public void readPacketData(DataInputStream data) throws IOException {
		pokemonID = data.readInt();
		nationalPokedexNumber = data.readShort();
		name = Packet.readString(data, 64);
		nickname = Packet.readString(data, 64);
		lvl = data.readShort();
		nextLvlXP = data.readShort();
		hp = data.readShort();
		health = data.readShort();
		isMale = data.readBoolean();
		isFainted = data.readBoolean();
		hasOwner = data.readBoolean();
		order = data.readShort();
		numMoves = data.readShort();
		for (int i = 0; i < numMoves; i++) {
			moveset[i] = new PixelmonMovesetDataPacket();
			moveset[i].readData(data);
		}
		type1 = EnumType.parseType(data.readShort());
		type2 = EnumType.parseType(data.readShort());
		HP = data.readShort();
		Speed = data.readShort();
		Attack = data.readShort();
		Defence = data.readShort();
		SpecialAttack = data.readShort();
		SpecialDefence = data.readShort();
		boxNumber = data.readShort();
		isShiny = data.readBoolean();
		doesLevel = data.readBoolean();
		heldItemId = data.readInt();
	}
}
