package pixelmon.comm;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.enums.EnumPokeballs;
import pixelmon.enums.EnumPokemon;

public class StarterListPacket extends PixelmonPacket {

	public StarterListPacket() {
	}

	public EnumPokemon[] starterList;
	public int[] starterListIndex;

	public StarterListPacket(EnumPokemon... starters) {
		packetType = EnumPackets.StarterList;
		starterList = starters;
		starterListIndex = new int[starterList.length];
		for (int i =0; i < starterList.length; i++)
			starterListIndex[i] = Entity3HasStats.getBaseStats(starterList[i].name).nationalPokedexNumber;
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
		for (int i=0; i < starterList.length; i++){
			int index = data.readInt();
			starterListIndex[i] = index;
			starterList[i] = EnumPokemon.get(Entity3HasStats.getBaseStats(index).pixelmonName);
		}
	}

	@Override
	public int getID() {
		return packetType.getIndex();
	}

}
