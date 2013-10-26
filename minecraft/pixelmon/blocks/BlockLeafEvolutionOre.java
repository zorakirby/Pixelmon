package pixelmon.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import pixelmon.config.PixelmonBlocks;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.config.PixelmonItems;
import pixelmon.enums.EnumEvolutionStone;

public class BlockLeafEvolutionOre extends Block {

	private EnumEvolutionStone type = null;
	private String[][] Textures = {{"pixelmon:leafstone", "pixelmon:leafstone_birch", "pixelmon:leafstone_jungle", "pixelmon:leafstone_spruce"}, {"pixelmon:leafstone", "pixelmon:leafstone_birch", "pixelmon:leafstone_jungle", "pixelmon:leafstone_spruce"}};
	
	private Icon[][] icon = new Icon[2][];
	private int iconType;
	
	public BlockLeafEvolutionOre(int id, EnumEvolutionStone type, float hardness, String itemName) {
		super(id, Material.rock);
		this.type = type;
		setHardness(hardness);
		setStepSound(Block.soundGrassFootstep);
		setCreativeTab(PixelmonCreativeTabs.natural);
		setUnlocalizedName(itemName);
	}

    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        double d0 = 0.5D;
        double d1 = 1.0D;
        return ColorizerFoliage.getFoliageColor(d0, d1);
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1)
    {
        return (par1 & 3) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((par1 & 3) == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
    }
    
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

        if ((l & 3) == 1)
        {
            return ColorizerFoliage.getFoliageColorPine();
        }
        else if ((l & 3) == 2)
        {
            return ColorizerFoliage.getFoliageColorBirch();
        }
        else
        {
            int i1 = 0;
            int j1 = 0;
            int k1 = 0;

            for (int l1 = -1; l1 <= 1; ++l1)
            {
                for (int i2 = -1; i2 <= 1; ++i2)
                {
                    int j2 = par1IBlockAccess.getBiomeGenForCoords(par2 + i2, par4 + l1).getBiomeFoliageColor();
                    i1 += (j2 & 16711680) >> 16;
                    j1 += (j2 & 65280) >> 8;
                    k1 += j2 & 255;
                }
            }

            return (i1 / 9 & 255) << 16 | (j1 / 9 & 255) << 8 | k1 / 9 & 255;
        }
    }
    
    @Override
    public void registerIcons(IconRegister icon) {
    	blockIcon = icon.registerIcon("pixelmon:" + type.toString().toLowerCase());
    }
	
    /* @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        return (par2 & 3) == 1 ? this.icon[this.iconType][1] : ((par2 & 3) == 3 ? this.icon[this.iconType][3] : ((par2 & 3) == 2 ? this.icon[this.iconType][2] : this.icon[this.iconType][0]));
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        for (int i = 0; i < Textures.length; i++)
        {
            this.icon[i] = new Icon[Textures[i].length];

            for (int j = 0; j < Textures[i].length; j++)
            {
                this.icon[i][j] = par1IconRegister.registerIcon(Textures[i][j]);
            }
        }
    }*/

	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 0;
	}

	public int idDropped(int i, Random rand, int j) {
		return PixelmonItems.leafStoneShard.itemID;
	}
}
