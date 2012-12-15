package pixelmon.database;


public enum SpawnConditions {
	Grass(0), Sand(0), Rock(0), Darkness(1), DayLight(1);

	public int index;
	private SpawnConditions(int index) {
		this.index = index;
	}
	
	public static SpawnConditions[] ParseSpawnConditions(String conditions) {
		if (conditions == null)
			return new SpawnConditions[0];
		String[] splits = conditions.split(";");
		SpawnConditions[] conds = new SpawnConditions[splits.length];
		int i = 0;
		for (String s : splits)
			for (SpawnConditions c : values())
				if (c.toString().equalsIgnoreCase(s))
					conds[i++] = c;
		return conds;
	}
}
