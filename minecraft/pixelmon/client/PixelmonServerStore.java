package pixelmon.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import pixelmon.comm.PixelmonDataPacket;
import pixelmon.comm.StarterListPacket;
import pixelmon.enums.EnumPokemon;
import pixelmon.storage.ComputerBox;
import pixelmon.storage.PlayerComputerStorage;

public class PixelmonServerStore {
	private static PixelmonDataPacket[][] store = new PixelmonDataPacket[PlayerComputerStorage.boxCount][ComputerBox.boxLimit];
	private static PixelmonDataPacket mousePokemon;
	public static StarterListPacket starterListPacket;

	public static void addToList(DataInputStream dataStream) {
		PixelmonDataPacket p = new PixelmonDataPacket();
		try {
			p.readPacketData(dataStream);
			store[p.boxNumber][p.order] = p;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void clearList() {
		for (int i = 0; i < PlayerComputerStorage.boxCount; i++)
			for (int j = 0; j < ComputerBox.boxLimit; j++)
				store[i][j] = null;
	}

	public static PixelmonDataPacket getFromBox(int i, int j) {
		return store[i][j];
	}

	public static PixelmonDataPacket getPixelmonDataFromID(int id) {
		for (int i = 0; i < PlayerComputerStorage.boxCount; i++)
			for (int j = 0; j < ComputerBox.boxLimit; j++)
				if (store[i][j] != null)
					if (store[i][j].pokemonID == id)
						return store[i][j];
		return null;
	}

	public static void removeFromList(int box, int pos) {
		store[box][pos] = null;
	}

	public static void setMousePokemon(DataInputStream dataStream) {
		PixelmonDataPacket p = new PixelmonDataPacket();
		try {
			p.readPacketData(dataStream);
			mousePokemon = p;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void clearMousePokemon() {
		mousePokemon = null;
	}

	public static PixelmonDataPacket getMousePokemon() {
		return mousePokemon;
	}
}
