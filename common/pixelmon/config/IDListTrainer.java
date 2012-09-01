package pixelmon.config;


import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraftforge.common.Configuration;

public class IDListTrainer {

	public static int trainerYoungsterId;
	public static int trainerYoungster2Id;
	public static int trainerBugCatcherId;
	
	private static int i=700;
	public static void load(Configuration configuration) {
		trainerYoungsterId = Integer.parseInt(configuration.getOrCreateIntProperty("Youngster", "trainers", i++).value);
		trainerYoungster2Id = Integer.parseInt(configuration.getOrCreateIntProperty("Youngster2", "trainers", i++).value);
		trainerBugCatcherId = Integer.parseInt(configuration.getOrCreateIntProperty("BugCatcher", "trainers", i++).value);
	}
}
