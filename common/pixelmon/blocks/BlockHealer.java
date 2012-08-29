package pixelmon.blocks;

import java.util.Random;

import pixelmon.Pixelmon;
import pixelmon.config.PixelmonItems;
import pixelmon.enums.EnumGui;

import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockHealer extends BlockContainer {

	public BlockHealer(int par1) {
		super(par1, Material.rock);
		setHardness(3.5f);
		setStepSound(Block.soundStoneFootstep);
		setRequiresSelfNotify();
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return PixelmonItems.healerItem.shiftedIndex;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
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

	/**
	 * Called upon block activation (left or right click on the block.). The
	 * three integers represent x,y,z of the block.
	 */
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if(!par1World.isRemote)
		{
			par5EntityPlayer.openGui(Pixelmon.instance, EnumGui.Healer.getIndex(), par1World, 0, 0, 0);
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityHealer();
	}
}
