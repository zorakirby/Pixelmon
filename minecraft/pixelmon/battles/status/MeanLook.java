package pixelmon.battles.status;

import pixelmon.entities.pixelmon.EntityPixelmon;

public class MeanLook extends StatusBase {
	EntityPixelmon locker;
	public MeanLook(EntityPixelmon locker) {
		super(StatusType.MeanLook, true, false, false);
		this.locker = locker;
	}

	public boolean stopsSwitching() throws Exception {
		return true;
	}
	
	@Override
	public void applyRepeatedEffect(EntityPixelmon entityPixelmon, EntityPixelmon entityPixelmon2) throws Exception {
		if (entityPixelmon2 != locker)
			entityPixelmon.status.remove(this);
	}
}
