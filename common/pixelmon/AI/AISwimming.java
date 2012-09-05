package pixelmon.AI;

import java.util.Random;

import pixelmon.WorldHelper;
import pixelmon.entities.pixelmon.Entity7HasAI;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.EntityAIBase;

public class AISwimming extends EntityAIBase {
	private Entity7HasAI pixelmon;
	private float swimSpeed = 1.5f;
	private float decayRate = 0.99f;
	private int refreshRate = 100;
	private int depthRangeStart = 0;
	private int depthRangeEnd = 100;

	private int count;

	float randomMotionSpeed;
	float randomMotionVecX;
	float randomMotionVecY;
	float randomMotionVecZ;
	
	Random rand;
	
	public AISwimming(Entity7HasAI entity) {
		swimSpeed = entity.baseStats.swimmingParameters.swimSpeed;
		decayRate = entity.baseStats.swimmingParameters.decayRate;
		refreshRate = entity.baseStats.swimmingParameters.refreshRate;
		depthRangeStart = entity.baseStats.swimmingParameters.depthRangeStart;
		depthRangeEnd = entity.baseStats.swimmingParameters.depthRangeEnd;
		this.pixelmon = entity;
		this.rand = new Random();
	}

	@Override
	public boolean shouldExecute() {
		if (count == 0) {
			return true;
		}
		count--;

		return false;
	}

	@Override
	public void startExecuting() {
		int wdepth = WorldHelper.getWaterDepth((int) pixelmon.posX, (int) pixelmon.posY, (int) pixelmon.posZ, pixelmon.worldObj);
		count = refreshRate - refreshRate / 2 + rand.nextInt(refreshRate / 2);
		randomMotionVecX = (-0.5f + rand.nextFloat()) * 0.2F;
		if (wdepth >= depthRangeEnd)
			randomMotionVecY = (0.02F + rand.nextFloat()) * 0.1F;
		else if (wdepth <= depthRangeStart)
			randomMotionVecY = (-0.02F + rand.nextFloat()) * 0.1F;
		else
			randomMotionVecY = rand.nextFloat() * 0.1F;
		randomMotionVecZ = (-0.5f + rand.nextFloat()) * 0.2F;
		randomMotionSpeed = swimSpeed;
	}
	
	@Override
	public boolean continueExecuting() {
		if (!pixelmon.isInWater()){
			return false;
		}
		randomMotionSpeed *= decayRate;

		pixelmon.motionX = randomMotionVecX * randomMotionSpeed;
		pixelmon.motionY = randomMotionVecY * randomMotionSpeed * 0.05;
		pixelmon.motionZ = randomMotionVecZ * randomMotionSpeed;

		pixelmon.renderYawOffset += ((-(float) Math.atan2(pixelmon.motionX, pixelmon.motionZ) * 180F) / (float) Math.PI - pixelmon.renderYawOffset) * 0.1F;
		pixelmon.rotationYaw = pixelmon.renderYawOffset;

		return randomMotionSpeed > 0.001;
	}

}
