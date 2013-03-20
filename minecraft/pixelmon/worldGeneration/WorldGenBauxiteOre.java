package pixelmon.worldGeneration;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import pixelmon.config.PixelmonBlocks;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenBauxiteOre extends WorldGenerator implements IWorldGenerator {

	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		int amount = 0;
		float var6 = par2Random.nextFloat() * (float) Math.PI;
		double var7 = (double) ((float) (par3 + 8) + MathHelper.sin(var6) * (float) amount / 8.0F);
		double var9 = (double) ((float) (par3 + 8) - MathHelper.sin(var6) * (float) amount / 8.0F);
		double var11 = (double) ((float) (par5 + 8) + MathHelper.cos(var6) * (float) amount / 8.0F);
		double var13 = (double) ((float) (par5 + 8) - MathHelper.cos(var6) * (float) amount / 8.0F);
		double var15 = (double) (par4 + par2Random.nextInt(3) - 2);
		double var17 = (double) (par4 + par2Random.nextInt(3) - 2);

		for (int i = 0; i < (par4); i++) {
			amount = i / 10;
		}
		for (int var19 = 0; var19 <= amount; ++var19) {
			double var20 = var7 + (var9 - var7) * (double) var19 / (double) amount;
			double var22 = var15 + (var17 - var15) * (double) var19 / (double) amount;
			double var24 = var11 + (var13 - var11) * (double) var19 / (double) amount;
			double var26 = par2Random.nextDouble() * (double) amount / 16.0D;
			double var28 = (double) (MathHelper.sin((float) var19 * (float) Math.PI / (float) amount) + 1.0F) * var26 + 1.0D;
			double var30 = (double) (MathHelper.sin((float) var19 * (float) Math.PI / (float) amount) + 1.0F) * var26 + 1.0D;
			int var32 = MathHelper.floor_double(var20 - var28 / 2.0D);
			int var33 = MathHelper.floor_double(var22 - var30 / 2.0D);
			int var34 = MathHelper.floor_double(var24 - var28 / 2.0D);
			int var35 = MathHelper.floor_double(var20 + var28 / 2.0D);
			int var36 = MathHelper.floor_double(var22 + var30 / 2.0D);
			int var37 = MathHelper.floor_double(var24 + var28 / 2.0D);

			for (int var38 = var32; var38 <= var35; ++var38) {
				double var39 = ((double) var38 + 0.5D - var20) / (var28 / 2.0D);

				if (var39 * var39 < 1.0D) {
					for (int var41 = var33; var41 <= var36; ++var41) {
						double var42 = ((double) var41 + 0.5D - var22) / (var30 / 2.0D);

						if (var39 * var39 + var42 * var42 < 1.0D) {
							for (int var44 = var34; var44 <= var37; ++var44) {
								double var45 = ((double) var44 + 0.5D - var24) / (var28 / 2.0D);

								Block block = Block.blocksList[par1World.getBlockId(var38, var41, var44)];
								if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0D
										&& (block != null && block.isBlockReplaceable(par1World, var38, var41, var44))) {
									par1World.setBlockAndMetadataWithNotify(var38, var41, var44, PixelmonBlocks.bauxite.blockID, 0, 0);
								}
							}
						}
					}
				}
			}
		}

		return true;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (int i = 0; i < 20; i++) {
			int xPos = random.nextInt(2) + chunkX * 16;
			int zPos = random.nextInt(4) + chunkZ * 16;
			int yPos = random.nextInt(100) + 28;
			generate(world, random, xPos, yPos, zPos);
		}
	}

}
