package pixelmon.database;

public enum SpawnLocation {
	Land(0), Water(1), OnWater(2), UnderGround(3), Air(4), AirPersistent(5);

	public int index;

	private SpawnLocation(int index) {
		this.index = index;
	}

	public static SpawnLocation[] getSpawnLocations(String string) {
		String[] splits = string.split(";");
		SpawnLocation[] locations = new SpawnLocation[splits.length];
		int i = 0;
		for (String s : splits) {
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
