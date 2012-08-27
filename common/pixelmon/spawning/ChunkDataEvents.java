package pixelmon.spawning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pixelmon.RandomHelper;
import pixelmon.config.PixelmonConfig;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.ChunkDataEvent;

public class ChunkDataEvents {
	private static ArrayList<NumPos> numPosList = new ArrayList<NumPos>();

	private class NumPos {
		public int x;
		public int z;
		public int num;

		public NumPos(int x, int z, int num) {
			this.x = x;
			this.z = z;
			this.num = num;
		}
	}

	public static int getNumFromPos(int x, int z) {
		for (int i = 0; i < numPosList.size(); i++) {
			if (numPosList.get(i).x == x && numPosList.get(i).z == z) {
				return numPosList.get(i).num;
			}
		}
		return 0;
	}

	@ForgeSubscribe
	public void spawnOnChunkLoad(ChunkDataEvent.Load event) {
		int numPixelmon;
		int[] chunkPos = new int[] { event.getChunk().xPosition, event.getChunk().zPosition };
		if (!event.getData().hasKey("NumberOfPixelmon")) {
			numPixelmon = generateChunkPixelmonNumber();
			if (numPixelmon < 0)
				numPixelmon = 0;
		} else {
			numPixelmon = event.getData().getInteger("NumberOfPixelmon");
		}
		numPosList.add(new NumPos(event.getChunk().xPosition, event.getChunk().zPosition, numPixelmon));
	}

	@ForgeSubscribe
	public void saveChunk(ChunkDataEvent.Save event) {
		for (int i = 0; i < numPosList.size(); i++) {
			if (numPosList.get(i).x == event.getChunk().xPosition && numPosList.get(i).z == event.getChunk().zPosition) {
				event.getData().setInteger("NumberOfPixelmon", numPosList.get(i).num);
				numPosList.remove(i);
				break;
			}
		}
	}

	private Random rand = new Random();

	private int generateChunkPixelmonNumber() {
		if (rand.nextFloat() * 100 < PixelmonConfig.chanceOfNoPokemon)
			return 0;
		int r = rand.nextInt(PixelmonConfig.maxPokemonPerChunk - 1) + 1;
		return r;
	}
}
