package pixelmon.comm;

public enum EnumUpdateType {
	HP(0), Nickname(2), Name(3), Stats(4), Friendship(5), Moveset(6), Status(7), CanLevel(8), HeldItem(9);

	public int index;

	private EnumUpdateType(int index) {
		this.index = index;
	}

	public static EnumUpdateType getType(int index) {
		for (EnumUpdateType type : values()) {
			if (type.index == index)
				return type;
		}
		return null;
	}
}
