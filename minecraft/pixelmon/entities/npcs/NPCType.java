package pixelmon.entities.npcs;

public enum NPCType {
	Trainer("trainers", 0), 
	Doctor("npcs", 1),
	Trader("npcs", 2),
	ShopKeeper("npcs", 3);

	public String textureDirectory;
	public int index;

	private NPCType(String textureDirectory, int index) {
		this.textureDirectory = textureDirectory;
	}

	public static boolean has(String name) {
		for (NPCType n : values()) {
			if (name.equalsIgnoreCase(n.toString()))
				return true;
		}
		return false;
	}

	public static NPCType get(String name) {
		for (NPCType n : values()) {
			if (name.equalsIgnoreCase(n.toString()))
				return n;
		}
		return null;
	}

	public static NPCType getType(int index) {
		for (NPCType n : values()) {
			if (index == n.index)
				return n;
		}
		return null;
	}
}
