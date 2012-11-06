package pixelmon.enums;

public enum EnumTrainers {
	Youngster, 
	Fisherman,
	//FemaleRocketGrunt,
	BugCatcher;

	/* <<FOR YASEEN>>
	 * I grandly discovered that for trainers that have multiple models, ie. Fishermen and Youngsters,
	 * they only require the trainer class here, and the different models it can use are in the
	 * database under the column "Models". Once you've added the second fisherman model, add it into
	 * the database.
	 * I've changed this Enum accordingly.
	 * -Hiroku
	 */
	public static boolean has(String creatureName) {
		for (EnumTrainers trainer : values())
			if (trainer.toString().equalsIgnoreCase(creatureName))
				return true;
		return false;
	}
}
