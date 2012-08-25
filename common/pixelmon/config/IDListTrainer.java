package pixelmon.config;


import net.minecraftforge.common.Configuration;

public class IDListTrainer {

	public static int i = 700;
	public static int trainerYoungsterId;
	public static int trainerYoungster2Id;
	public static int trainerBugCatcherId;
	
	public static void load(Configuration configuration) {
		trainerYoungsterId = Integer.parseInt(configuration.getOrCreateIntProperty("Youngster", "trainers", i++).value);
		trainerYoungster2Id = Integer.parseInt(configuration.getOrCreateIntProperty("Youngster2", "trainers", i++).value);
		trainerBugCatcherId = Integer.parseInt(configuration.getOrCreateIntProperty("BugCatcher", "trainers", i++).value);
	}
}
