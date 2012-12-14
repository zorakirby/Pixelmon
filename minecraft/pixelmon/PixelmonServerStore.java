package pixelmon;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import pixelmon.comm.PixelmonDataPacket;

public class PixelmonServerStore {
	public static ArrayList<PixelmonDataPacket> store = new ArrayList<PixelmonDataPacket>();

	public static void addToList(DataInputStream dataStream) {
		PixelmonDataPacket packet = new PixelmonDataPacket();
		try {
			packet.readPacketData(dataStream);
			store.add(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static PixelmonDataPacket getPixelmonData(int index) {
		for (PixelmonDataPacket p : store) {
			if (p.order == index)
				return p;
		}
		return null;
	}

	public static void clearList() {
		store.clear();
	}

	public static PixelmonDataPacket getFromBox(int i, int j) {
		for (PixelmonDataPacket p: store){
			if (p.boxNumber == i && p.order == j) return p;
		}
		return null;
	}

	public static void addToList(PixelmonDataPacket p) {
		store.add(p);
	}

	public static PixelmonDataPacket getPixelmonDataFromID(int id) {
		for (PixelmonDataPacket p : store) {
			if (p.pokemonID == id)
				return p;
		}
		return null;
	}
}
