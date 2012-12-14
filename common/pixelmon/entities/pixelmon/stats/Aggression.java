package pixelmon.entities.pixelmon.stats;

public class Aggression {
	public int timid;
	public int passive;
	public int aggressive;

	public Aggression(String aggressionString, String pixelmonName) {
		if (aggressionString == null) {
			return;
		}
		String[] splits = aggressionString.split(";");
		if (splits.length != 3) {
			System.out.println("Error in Aggression" + " For Pokemon : " + pixelmonName);
			return;
		}
		timid = Integer.parseInt(splits[0]);
		passive = Integer.parseInt(splits[1]);
		aggressive = Integer.parseInt(splits[2]);
	}

}
