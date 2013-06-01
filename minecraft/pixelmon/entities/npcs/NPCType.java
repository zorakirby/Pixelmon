package pixelmon.entities.npcs;

public enum NPCType {
	Trainer("trainers"),
	Doctor("doctors");
	public String textureDirectory;
	private NPCType(String textureDirectory){
		this.textureDirectory = textureDirectory;
	}
	public static boolean has(String name) {
		for (NPCType n: values()){
			if (name.equalsIgnoreCase(n.toString()))
				return true;
		}
		return false;
	}
	public static NPCType get(String name) {
		for (NPCType n: values()){
			if (name.equalsIgnoreCase(n.toString()))
				return n;
		}
		return null;
	}
}
