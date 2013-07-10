package pixelmon.enums;

public enum EnumMovement {
	Jump(0), Crouch(1);
	private EnumMovement(int index) {
		this.index = index;
	}

	public int index;

	public static EnumMovement getMovement(int index) {
		for (EnumMovement e : values()) {
			if (e.index == index)
				return e;
		}
		return null;
	}
}
