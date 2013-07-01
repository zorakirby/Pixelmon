package pixelmon.AI;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import pixelmon.entities.pixelmon.Entity7HasAI;

public class AITeleportAway extends EntityAIBase {

	Entity7HasAI pixelmon;
	private EntityPlayer closestLivingEntity;

	public AITeleportAway(Entity7HasAI entity) {
		pixelmon = entity;
	}

	@Override
	public boolean shouldExecute() {
		if (pixelmon.hasOwner())
			return false;
		this.closestLivingEntity = pixelmon.worldObj.getClosestPlayerToEntity(pixelmon, 7);

		if (this.closestLivingEntity == null) {
			return false;
		}

		return true;
	}

	@Override
	public boolean continueExecuting() {
		return false;
	}

	@Override
	public void startExecuting() {
		teleportRandomly(pixelmon, rand);
	}

	Random rand = new Random();

	public static boolean teleportRandomly(Entity7HasAI pixelmon, Random rand) {
		double d = pixelmon.posX + (rand.nextDouble() - 0.5D) * 64D;
		double d1 = pixelmon.posY + (double) (rand.nextInt(64) - 32);
		double d2 = pixelmon.posZ + (rand.nextDouble() - 0.5D) * 64D;
		return teleportTo(pixelmon, rand, d, d1, d2);
	}

	protected static boolean teleportTo(Entity7HasAI pixelmon, Random rand, double par1, double par3, double par5) {
		double d = pixelmon.posX;
		double d1 = pixelmon.posY;
		double d2 = pixelmon.posZ;
		pixelmon.posX = par1;
		pixelmon.posY = par3;
		pixelmon.posZ = par5;
		boolean flag = false;
		int i = MathHelper.floor_double(pixelmon.posX);
		int j = MathHelper.floor_double(pixelmon.posY);
		int k = MathHelper.floor_double(pixelmon.posZ);

		if (pixelmon.worldObj.blockExists(i, j, k)) {
			boolean flag1;

			for (flag1 = false; !flag1 && j > 0;) {
				int i1 = pixelmon.worldObj.getBlockId(i, j - 1, k);

				if (i1 == 0 || !Block.blocksList[i1].blockMaterial.blocksMovement()) {
					pixelmon.posY--;
					j--;
				} else {
					flag1 = true;
				}
			}

			if (flag1) {
				pixelmon.setPosition(pixelmon.posX, pixelmon.posY, pixelmon.posZ);

				if (pixelmon.worldObj.getCollidingBoundingBoxes(pixelmon, pixelmon.boundingBox).size() == 0
						&& !pixelmon.worldObj.isAnyLiquid(pixelmon.boundingBox)) {
					flag = true;
				}
			}
		}

		if (!flag) {
			pixelmon.setPosition(d, d1, d2);
			return false;
		}

		int l = 128;

		for (int j1 = 0; j1 < l; j1++) {
			double d3 = (double) j1 / ((double) l - 1.0D);
			float f = (rand.nextFloat() - 0.5F) * 0.2F;
			float f1 = (rand.nextFloat() - 0.5F) * 0.2F;
			float f2 = (rand.nextFloat() - 0.5F) * 0.2F;
			double d4 = d + (pixelmon.posX - d) * d3 + (rand.nextDouble() - 0.5D) * (double) pixelmon.width * 2D;
			double d5 = d1 + (pixelmon.posY - d1) * d3 + rand.nextDouble() * (double) pixelmon.height;
			double d6 = d2 + (pixelmon.posZ - d2) * d3 + (rand.nextDouble() - 0.5D) * (double) pixelmon.length * 2D;
			pixelmon.worldObj.spawnParticle("portal", d4, d5, d6, f, f1, f2);
		}

		pixelmon.worldObj.playSoundEffect(d, d1, d2, "mob.endermen.portal", 1.0F, 1.0F);
		pixelmon.worldObj.playSoundAtEntity(pixelmon, "mob.endermen.portal", 1.0F, 1.0F);
		return true;
	}

}
