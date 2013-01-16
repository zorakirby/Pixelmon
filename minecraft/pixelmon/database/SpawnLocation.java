package pixelmon.database;

public enum SpawnLocation {
	Land, Water, OnWater, UnderGround, Air, AirPersistent;

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
}
