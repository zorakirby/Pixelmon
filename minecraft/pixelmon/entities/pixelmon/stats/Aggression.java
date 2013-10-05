package pixelmon.entities.pixelmon.stats;

import pixelmon.config.PixelmonConfig;
import pixelmon.database.DatabaseStats;

public class Aggression {
	public int timid;
	public int passive;
	public int aggressive;

	public Aggression(int pcTimid, int pcAgg, String pixelmonName) {
		timid = pcTimid;
		passive = 100 - pcTimid - pcAgg;
		aggressive = pcAgg;
	}
}
