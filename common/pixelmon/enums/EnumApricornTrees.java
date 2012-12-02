package pixelmon.enums;

public enum EnumApricornTrees {
	Black(0, EnumApricorns.Black), White(1, EnumApricorns.White), Pink(2, EnumApricorns.Pink), Green(3, EnumApricorns.Green), Blue(4, EnumApricorns.Blue), Yellow(5, EnumApricorns.Yellow), Red(6,
			EnumApricorns.Red);

	public EnumApricorns apricorn;
	public int id;

	private EnumApricornTrees(int id, EnumApricorns apricorn) {
		this.id = id;
		this.apricorn = apricorn;
	}

	public static EnumApricornTrees getFromID(int id) {
		for (EnumApricornTrees e : values())
			if (e.id == id)
				return e;

		return null;
	}
}
