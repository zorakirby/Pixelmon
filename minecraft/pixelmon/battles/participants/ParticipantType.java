package pixelmon.battles.participants;

public enum ParticipantType {
	Player(0), Trainer(1), WildPokemon(2);
	public int index;

	private ParticipantType(int index) {
		this.index = index;
	}

	public static ParticipantType get(int index) {
		for (ParticipantType p: values())
			if (p.index == index)
				return p;
		return null;
	}
}
