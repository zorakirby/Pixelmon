package pixelmon.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.config.PixelmonItems;
import pixelmon.enums.EnumShrine;
import pixelmon.items.ItemShrineOrb;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockShrine extends BlockContainer {

	public EnumShrine rockType;
	public boolean uno, dos, tres = false;
	public int numStages = 1;
	private TileEntityShrine tile;

	public BlockShrine(int par1, Material par2Material, EnumShrine rockType) {
		super(par1, par2Material);
		this.rockType = rockType;
		setTickRandomly(true);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return -1;
	}

	@SideOnly(Side.CLIENT)
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return this.blockID;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		if (this.rockType == EnumShrine.Articuno)
			blockIcon = par1IconRegister.registerIcon("pixelmon:unoShrine");
		else if (this.rockType == EnumShrine.Zapdos)
			blockIcon = par1IconRegister.registerIcon("pixelmon:dosShrine");
		else if (this.rockType == EnumShrine.Moltres)
			blockIcon = par1IconRegister.registerIcon("pixelmon:tresShrine");

	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityShrine();
	}
/**
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (par5EntityPlayer.getHeldItem() != null)
			if (par5EntityPlayer.getHeldItem().getItem().itemID == PixelmonItems.unoOrb.itemID
					|| par5EntityPlayer.getHeldItem().getItem().itemID == PixelmonItems.dosOrb.itemID
					|| par5EntityPlayer.getHeldItem().getItem().itemID == PixelmonItems.tresOrb.itemID)
				if (this.rockType == EnumShrine.Articuno) {
					if (par5EntityPlayer.getHeldItem().getItem().itemID == PixelmonItems.unoOrb.itemID) {
						boolean filled = ItemShrineOrb.isFilled;
						if (ItemShrineOrb.isFilled = true) {
							uno = true;
							--par5EntityPlayer.getHeldItem().stackSize;
							ItemShrineOrb.orbExp = 0;
							par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
						}else{
							
						}
					}
				} else if (this.rockType == EnumShrine.Zapdos) {
					if (par5EntityPlayer.getHeldItem().getItem().itemID == PixelmonItems.dosOrb.itemID) {
						if (ItemShrineOrb.isFilled = true) {
							dos = true;
							ItemShrineOrb.orbExp = 0;
							--par5EntityPlayer.getHeldItem().stackSize;
							par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
						}
					}
				} else if (this.rockType == EnumShrine.Moltres) {
					if (par5EntityPlayer.getHeldItem().getItem().itemID == PixelmonItems.tresOrb.itemID) {
						if (ItemShrineOrb.isFilled = true) {
							tres = true;
							ItemShrineOrb.orbExp = 0;
							--par5EntityPlayer.getHeldItem().stackSize;
							par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
						}
					} else {
						return false;
					}
				}
		return false;
	}
**/
	@Override
	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int x, int y, int z, Random par5Random) {
		super.updateTick(world, x, y, z, par5Random);

		if (world.getBlockLightValue(x, y + 1, z) >= 9) {
			int stage = world.getBlockMetadata(x, y, z);
			float var7 = 10;

			if (par5Random.nextInt(3) == 0) {
				world.setBlockMetadataWithNotify(x, y, z, stage + 1, 2);
				((WorldServer) world).getPlayerManager().markBlockForUpdate(x, y, z);
			}
		}
	}
}
