package pixelmon.entities.pixelmon.stats;

public class SwimmingParameters {
	public int depthRangeStart;
	public int depthRangeEnd;
	public float swimSpeed;
	public float decayRate;
	public int refreshRate;

	public SwimmingParameters(String parameterString, String pokemonName) {
		String[] splits = parameterString.split(";");
		if (splits.length != 5)
			System.out.println("[ERROR] SwimmingParameter Error for " + pokemonName);
		try {
			depthRangeStart = Integer.parseInt(splits[0]);
			depthRangeEnd = Integer.parseInt(splits[1]);
			swimSpeed = Float.parseFloat(splits[2]);
			decayRate = Float.parseFloat(splits[3]);
			refreshRate = Integer.parseInt(splits[4]);
		} catch (Exception e) {
			System.out.println("[ERROR] SwimmingParameter Error2 for " + pokemonName);
		}
	}
}
