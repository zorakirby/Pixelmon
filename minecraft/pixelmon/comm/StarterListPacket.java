package pixelmon.comm;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import pixelmon.config.PixelmonConfig;
import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.enums.EnumPokeballs;
import pixelmon.enums.EnumPokemon;
import pixelmon.spawning.SpawnRegistry;

public class StarterListPacket extends PixelmonPacket {

	public StarterListPacket() {
	}

	public EnumPokemon[] starterList;
	public int[] starterListIndex;

	public StarterListPacket(EnumPokemon... starters) {
		packetType = EnumPackets.StarterList;
		starterList = starters;
		starterListIndex = new int[starterList.length];

		for (int i = 0; i < starterList.length; i++)
			if (starterList[i] != null) {
				int ID = Entity3HasStats.getBaseStats(starterList[i].name).nationalPokedexNumber;
				int value = -2;
				if (ID <= 151) {
					if (PixelmonConfig.Gen1 == true)
						value = ID;
				} else if (ID > 151 && ID <= 251) {
					if (PixelmonConfig.Gen2 == true)
						value = ID;
				} else if (ID > 251 && ID <= 386) {
					if (PixelmonConfig.Gen3 == true)
						value = ID;
				} else if (ID > 386 && ID <= 493) {
					if (PixelmonConfig.Gen4 == true)
						value = ID;
				} else if (ID > 493 && ID <= 649) {
					if (PixelmonConfig.Gen5 == true)
						value = ID;
				}
				starterListIndex[i] = value;
			} else
				starterListIndex[i] = -1;
	}

	@Override
	public void writePacketData(DataOutput data) throws IOException {
		data.writeShort(starterList.length);
		for (int i : starterListIndex) {
			data.writeInt(i);
		}
	}

	@Override
	public void readPacketData(DataInput data) throws IOException {
		starterList = new EnumPokemon[data.readShort()];
		starterListIndex = new int[starterList.length];
		for (int i = 0; i < starterList.length; i++) {
			int index = data.readInt();
			starterListIndex[i] = index;
			if (index >= 0)
				starterList[i] = EnumPokemon.get(Entity3HasStats.getBaseStats(index).pixelmonName);
		}
	}

	@Override
	public int getID() {
		return packetType.getIndex();
	}

}
