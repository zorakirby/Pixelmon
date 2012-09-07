package pixelmon.database;

import java.util.List;

public enum SpawnConditions {
	Grass, Sand, Rock, Darkness, DayLight;

	public static SpawnConditions[] ParseSpawnConditions(String conditions) {
		if (conditions == null)
			return null;
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
