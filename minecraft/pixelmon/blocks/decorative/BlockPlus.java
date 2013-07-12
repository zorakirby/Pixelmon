package pixelmon.blocks.decorative;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockPlus extends Block{
	protected String iconName = "quartzblock_bottom";
	protected int idDropped, amountDropped;
	protected boolean opaqueCube = true, renderNormalBlock = true;
	
	public BlockPlus(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
	public BlockPlus setRenderOptions(int renderType, boolean opaqueCube, boolean renderNormal){
		this.opaqueCube = opaqueCube;
		this.renderNormalBlock = renderNormal;
		return this;
	}
	
	public BlockPlus setIdDropped(int id){
		this.idDropped = id;
		return this;
	}
	public BlockPlus setAmountDropped(int amount){
		this.amountDropped = amount;
		return this;
	}
	public BlockPlus setIconName(String name){
		this.iconName = name;
		return this;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return this.opaqueCube;
	}

	@Override
	public boolean renderAsNormalBlock(){
		return this.renderNormalBlock;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon(iconName);
	}

}
