package pixelmon.AI;

import java.util.Random;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import pixelmon.entities.pixelmon.Entity7HasAI;
import net.minecraft.entity.Entity;

public class AIFlying extends EntityAIBase {
	private final int PIXELMON_OWNER_FOLLOW_Y_OFFSET = 3;
	private final long OWNER_FIND_INTERVAL = 5000L;
	private final long SITTINGSPOT_REACHTIME = 3000L;
	private final double OWNER_DISTANCE_TO_TAKEOFF = 100D;
	private final double OWNER_DISTANCE_TO_TELEPORT = 400D;
	private boolean flag_height = false;
	private final Entity7HasAI pixelmon;
	private ChunkCoordinates currentFlightTarget;
	private Random rand;
	private long nextOwnerCheckTime;
	private long sittingSpotAbortTime;

	public AIFlying(Entity7HasAI entity) {
		pixelmon = entity;
		rand = entity.getRNG();
		nextOwnerCheckTime = System.currentTimeMillis();
		sittingSpotAbortTime = -1L;
	}

	@Override
	public boolean shouldExecute() {
		return true;
	}

	@Override
	public boolean continueExecuting() {
		return true;
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
	}

	@Override
	public void resetTask() {
		super.resetTask();
	}

	@Override
	public void updateTask() {
		// map reload or owner disconnected - search owner entity every couple
		// seconds
		if (pixelmon.getOwner() == null) {
			lookForOwnerEntity();
		}

		if (pixelmon.getIsIdle()) {
			checkTakeOffConditions();
		} else {
			updateFlightTarget();

		}

		super.updateTask();
	}

	private void updateFlightTarget() {
		if (pixelmon.getOwner() != null) {
			if (pixelmon.getDistanceSqToEntity(pixelmon.getOwner()) > OWNER_DISTANCE_TO_TAKEOFF
					|| (sittingSpotAbortTime > 0 && System.currentTimeMillis() > sittingSpotAbortTime)) {
				pixelmon.setIdleSpot(null);
			}
		}

		if (pixelmon.getIdleSpot() == null) {
			sittingSpotAbortTime = -1L;

			// target invalid or no free block
			if (currentFlightTarget != null
					&& (!pixelmon.worldObj.isAirBlock(currentFlightTarget.posX, currentFlightTarget.posY, currentFlightTarget.posZ) || currentFlightTarget.posY < 1)) {
				currentFlightTarget = null;
			}

			// finding a new target, randomly
			if (currentFlightTarget == null || rand.nextInt(30) == 0
					|| currentFlightTarget.getDistanceSquared((int) pixelmon.posX, (int) pixelmon.posY, (int) pixelmon.posZ) < 4.0F) {
				currentFlightTarget = getRandomFlightCoordinates();
			}
		} else {
			currentFlightTarget = pixelmon.getIdleSpot();

			if (sittingSpotAbortTime < 0) {
				sittingSpotAbortTime = System.currentTimeMillis() + SITTINGSPOT_REACHTIME;
			}

			if (currentFlightTarget.getDistanceSquared((int) pixelmon.posX, (int) pixelmon.posY, (int) pixelmon.posZ) < 2F) {
				land();
			}
		}
	}

	private ChunkCoordinates getRandomFlightCoordinates() {
		if (pixelmon.getOwner() != null) {
			if (!pixelmon.getOwner().isEntityAlive()) {
				nextOwnerCheckTime = System.currentTimeMillis() + OWNER_FIND_INTERVAL;
			} else {
				pixelmon.updateOwnerCoords();
			}
		} else {
			if (this.currentFlightTarget != null
					&& (!pixelmon.worldObj.isAirBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ) || this.currentFlightTarget.posY < 1)) {
				this.currentFlightTarget = null;
			}

			if (this.currentFlightTarget == null || this.rand.nextInt(30) == 0
					|| this.currentFlightTarget.getDistanceSquared((int) pixelmon.posX, (int) pixelmon.posY, (int) pixelmon.posZ) < 4.0F) {
				this.currentFlightTarget = new ChunkCoordinates((int) pixelmon.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int) pixelmon.posY
						+ this.rand.nextInt(6) - 2, (int) pixelmon.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
			}
			double d0;
			double d1;
			double d2;
			d0 = (double) this.currentFlightTarget.posX + 0.5D - pixelmon.posX;
			d1 = (double) this.currentFlightTarget.posY + 0.1D - pixelmon.posY;
			d2 = (double) this.currentFlightTarget.posZ + 0.5D - pixelmon.posZ;
			pixelmon.motionX += (Math.signum(d0) * 1D - pixelmon.motionX) * 0.10000000149011612D;
			pixelmon.motionY += (Math.signum(d1) * 0.699999988079071D - pixelmon.motionY) * 0.10000000149011612D;
			pixelmon.motionZ += (Math.signum(d2) * 1D - pixelmon.motionZ) * 0.10000000149011612D;
			float f = (float) (Math.atan2(pixelmon.motionZ, pixelmon.motionX) * 180.0D / Math.PI) - 90.0F;
			float f1 = MathHelper.wrapAngleTo180_float(f - pixelmon.rotationYaw);
			pixelmon.setMoveForward(0.5F);
			pixelmon.rotationYaw += f1;
			if (this.rand.nextInt(100) == 0
					&& pixelmon.worldObj.isBlockNormalCube(MathHelper.floor_double(pixelmon.posX), (int) pixelmon.posY + 1,
							MathHelper.floor_double(pixelmon.posZ))) {
				pixelmon.setIsIdle(true);
			}
		}
		return currentFlightTarget;
	}

	private void lookForOwnerEntity() {
		if (!pixelmon.getOwnerName().equals("") && System.currentTimeMillis() > nextOwnerCheckTime) {
			nextOwnerCheckTime = System.currentTimeMillis() + OWNER_FIND_INTERVAL;
		}
	}

	private void checkTakeOffConditions() {

		if (pixelmon.getOwner() != null && pixelmon.getOwner().isEntityAlive()
				&& pixelmon.getDistanceSqToEntity(pixelmon.getOwner()) > OWNER_DISTANCE_TO_TAKEOFF) {
			takeOff();
		}

		// block it was hanging from is no more
		if (!pixelmon.worldObj.isBlockNormalCube(MathHelper.floor_double(pixelmon.posX), (int) pixelmon.posY + 1, MathHelper.floor_double(pixelmon.posZ))) {
			takeOff();
		}

		// player scare
		EntityPlayer nearest = pixelmon.worldObj.getClosestPlayerToEntity(pixelmon, 4.0D);
		if (nearest != null && nearest != pixelmon.getOwner()) {
			takeOff();
		}
	}

	private void land() {
		sittingSpotAbortTime = -1L;
		pixelmon.setPosition(currentFlightTarget.posX + 0.5D, currentFlightTarget.posY + 0.5D, currentFlightTarget.posZ + 0.5D);
		pixelmon.setIsIdle(true);
	}

	private void takeOff() {
		pixelmon.setIsIdle(false);
		pixelmon.setPosition(pixelmon.posX, pixelmon.posY - 1D, pixelmon.posZ);
		pixelmon.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1015, (int) pixelmon.posX, (int) pixelmon.posY, (int) pixelmon.posZ, 0);
	}
}
