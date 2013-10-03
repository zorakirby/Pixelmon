package pixelmon.database;

import java.util.ArrayList;

public enum SpawnLocation {
	Land(0), Water(1), OnWater(2), UnderGround(3), Air(4), AirPersistent(5);

	public int index;

	private SpawnLocation(int index) {
		this.index = index;
	}

	public static SpawnLocation[] getSpawnLocations(ArrayList<String> list) {
		SpawnLocation[] locations = new SpawnLocation[list.size()];
		int i = 0;
		for (String s : list) {
			for (SpawnLocation sp : values()) {
				if (sp.toString().equalsIgnoreCase(s))
					locations[i++] = sp;
			}
		}

		return locations;
	}

	public static SpawnLocation getFromIndex(int integer) {
		for (SpawnLocation s : values())
			if (s.index == integer)
				return s;
		return null;
	}
}
