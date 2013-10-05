package pixelmon.database;

import java.util.ArrayList;

public enum SpawnConditions {
	Grass(0), Sand(0), Rock(0), Darkness(1), DayLight(1);

	public int index;

	private SpawnConditions(int index) {
		this.index = index;
	}

	public static SpawnConditions[] ParseSpawnConditions(ArrayList<String> list) {
		if (list == null || list.size() == 0)
			return new SpawnConditions[0];
		SpawnConditions[] conds = new SpawnConditions[list.size()];
		int i = 0;
		for (String s : list)
			for (SpawnConditions c : values())
				if (c.toString().equalsIgnoreCase(s))
					conds[i++] = c;
		return conds;
	}
}
