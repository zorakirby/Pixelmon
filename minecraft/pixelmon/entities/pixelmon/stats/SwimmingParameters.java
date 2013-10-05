package pixelmon.entities.pixelmon.stats;

import pixelmon.config.PixelmonConfig;

public class SwimmingParameters {
	public int depthRangeStart;
	public int depthRangeEnd;
	public float swimSpeed;
	public float decayRate;
	public int refreshRate;

	public SwimmingParameters(int depthMin, int depthMax, double swimSpeed, double decayRate, int refreshRate) {
		depthRangeStart = depthMin;
		depthRangeEnd = depthMax;
		this.swimSpeed = (float)swimSpeed;
		this.decayRate = (float)decayRate;
		this.refreshRate = refreshRate;
	}
}
