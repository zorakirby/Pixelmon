package pixelmon.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import pixelmon.comm.ChatHandler;
import pixelmon.config.PixelmonItems;

public class BlockTradeMachine extends BlockContainer {

	public BlockTradeMachine(int id) {
		super(id, Material.rock);
		setHardness(3.5f);
		setStepSound(Block.soundStoneFootstep);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return -1;
	}

	@SideOnly(Side.CLIENT)
    //only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return PixelmonItems.tradeMachineItem.itemID;
    }

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon("quartz_block_bottom");
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
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (!par1World.isRemote) {
			TileEntityTradeMachine te = (TileEntityTradeMachine) par1World.getBlockTileEntity(par2, par3, par4);
			if (te.playerCount < 2) {
				te.registerPlayer(player);
			} else {
				ChatHandler.sendChat(player, "Trading Machine Full!");
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityTradeMachine();
	}
}
