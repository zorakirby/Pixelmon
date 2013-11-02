package pixelmon.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.config.PixelmonItems;
import pixelmon.enums.EnumEvolutionRock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEvolutionRock extends BlockContainer {

        public EnumEvolutionRock rockType;
        
        public BlockEvolutionRock(int par1, Material par2Material, EnumEvolutionRock rockType) {
                super(par1, par2Material);                
                this.rockType = rockType;
        }

        @Override
        public int idDropped(int par1, Random par2Random, int par3) {
                return -1;
        }

        @SideOnly(Side.CLIENT)
    //only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
                if (this.rockType == EnumEvolutionRock.MossyRock) {
                        return PixelmonItems.mossyRock.itemID;
                }
                else {
                        return PixelmonItems.icyRock.itemID;
                }
    }

        @Override
        public int quantityDropped(Random random) {
                return 0;
        }

        @SideOnly(Side.CLIENT)
        public void registerIcons(IconRegister par1IconRegister) {
                if (this.rockType == EnumEvolutionRock.MossyRock)
                        blockIcon = par1IconRegister.registerIcon("pixelmon:mossyrock");
                else
                        blockIcon = par1IconRegister.registerIcon("pixelmon:icyrock");
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
                return new TileEntityEvolutionRock();
        }

}