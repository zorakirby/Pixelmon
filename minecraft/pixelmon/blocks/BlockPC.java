package pixelmon.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import pixelmon.Pixelmon;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonItems;
import pixelmon.enums.EnumGui;
import pixelmon.storage.ComputerBox;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerComputerStorage;

public class BlockPC extends BlockContainer {

	public BlockPC(int i, int j) {
		super(i, Material.rock);
		setHardness(2.5f);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return PixelmonItems.pcItem.itemID;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon("quartzblock_bottom");
	}
	
	@SideOnly(Side.CLIENT)
    //only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return PixelmonItems.pcItem.itemID;
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

	public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int var5) {
		if (var1.getBlockId(var2, var3 + 1, var4) == blockID) {
			var1.setBlock(var2, var3 + 1, var4, 0, 0, 2);
		}
		if (var1.getBlockId(var2, var3 - 1, var4) == blockID) {
			var1.setBlock(var2, var3 - 1, var4, 0, 0, 2);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (!world.isRemote) {
			player.openGui(Pixelmon.instance, EnumGui.PC.getIndex(), world, 0, 0, 0); // GUIPC
			return true;
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityPC();
	}
}
