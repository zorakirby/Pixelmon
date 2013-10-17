package pixelmon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStem;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.FakePlayerFactory;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class ItemWailmerPail extends Item
{

public ItemWailmerPail(int par1, String textureName, String itemName) 
{
		super(par1);		
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setMaxStackSize(1);
		this.setMaxDamage(32);

	}

private String texurePath; 



public void ItemWatersprayer(int par1) {
}

	
@SideOnly(Side.CLIENT)
public void registerIcons(IconRegister par1IconRegister)
{
 this.itemIcon = par1IconRegister.registerIcon("pixelmon:wailmerpail");
}	



public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)

{
    if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
    {
        return false;
    }
    else        {
            if (applyBonemeal(par1ItemStack, par3World, par4, par5, par6, par2EntityPlayer))
            {
                if (!par3World.isRemote)
                {
                    par3World.playAuxSFX(2005, par4, par5, par6, 0);
                }

                return true;
            }
        }
        {
            int i1 = par3World.getBlockId(par4, par5, par6);
            int j1 = par3World.getBlockMetadata(par4, par5, par6);
            

            if (i1 == Block.wood.blockID && BlockLog.limitToValidMetadata(j1) == 3)
            {
                if (par7 == 0)
                {
                    return false;
                }

                if (par7 == 1)
                {
                    return false;
                }

                if (par7 == 2)
                {
                    --par6;
                }

                if (par7 == 3)
                {
                    ++par6;
                }

                if (par7 == 4)
                {
                    --par4;
                }

                if (par7 == 5)
                {
                    ++par4;
                }

                if (par3World.isAirBlock(par4, par5, par6))
                {
                    int k1 = Block.blocksList[Block.cocoaPlant.blockID].onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, 0);
                    par3World.setBlock(par4, par5, par6, Block.cocoaPlant.blockID, k1, 2);

                    if (!par2EntityPlayer.capabilities.isCreativeMode)
                    {
                       
                    }
                }

                return true;
            }
        }

        return false;
    }
//}


public static boolean func_96604_a(ItemStack par0ItemStack, World par1World, int par2, int par3, int par4)
{
    return applyBonemeal(par0ItemStack, par1World, par2, par3, par4, FakePlayerFactory.getMinecraft(par1World));
}

public static boolean applyBonemeal(ItemStack par0ItemStack, World par1World, int par2, int par3, int par4, EntityPlayer player)
{
	
	
    int l = par1World.getBlockId(par2, par3, par4);

    BonemealEvent event = new BonemealEvent( player, par1World, l, par2, par3, par4);
    if (MinecraftForge.EVENT_BUS.post(event))
    {
        return false;
    }


	if (event.getResult() == Result.ALLOW)
    {
        if (!par1World.isRemote)
        {
        	
        }
        return true;
    }

    if (l == Block.sapling.blockID)
    {
        if (!par1World.isRemote)
        {
            if ((double)par1World.rand.nextFloat() < 0.45D)
            {
                ((BlockSapling)Block.sapling).markOrGrowMarked(par1World, par2, par3, par4, par1World.rand);
            }

            
        }

        return true;
    }
    else if (l != Block.mushroomBrown.blockID && l != Block.mushroomRed.blockID)
    {
        if (l != Block.melonStem.blockID && l != Block.pumpkinStem.blockID)
        {
            if (l > 0 && Block.blocksList[l] instanceof BlockCrops)
            {
                if (par1World.getBlockMetadata(par2, par3, par4) == 7)
                {
                    return false;
                }
                else
                {
                    if (!par1World.isRemote)
                    {
                        ((BlockCrops)Block.blocksList[l]).fertilize(par1World, par2, par3, par4);
                        
                    }

                    return true;
                }
            }
            else
            {
                int i1;
                int j1;
                int k1;

                if (l == Block.cocoaPlant.blockID)
                {
                    i1 = par1World.getBlockMetadata(par2, par3, par4);
                    j1 = BlockDirectional.getDirection(i1);
                    k1 = BlockCocoa.func_72219_c(i1);

                    if (k1 >= 2)
                    {
                        return false;
                    }
                    else
                    {
                        if (!par1World.isRemote)
                        {
                            ++k1;
                            par1World.setBlockMetadataWithNotify(par2, par3, par4, k1 << 2 | j1, 2);
                            
                        }

                        return true;
                    }
                }
                else if (l != Block.grass.blockID)
                {
                    return false;
                }
                else
                {
                    if (!par1World.isRemote)
                    {
                        
             
                        label102:

                        for (i1 = 0; i1 < 128; ++i1)
                        {
                            j1 = par2;
                            k1 = par3 + 1;
                            int l1 = par4;

                            for (int i2 = 0; i2 < i1 / 16; ++i2)
                            {
                                j1 += itemRand.nextInt(3) - 1;
                                k1 += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
                                l1 += itemRand.nextInt(3) - 1;

                                if (par1World.getBlockId(j1, k1 - 1, l1) != Block.grass.blockID || par1World.isBlockNormalCube(j1, k1, l1))
                                {
                                    continue label102;
                                }
                            }

                            if (par1World.getBlockId(j1, k1, l1) == 0)
                            {
                                if (itemRand.nextInt(10) != 0)
                                {
                                    if (Block.tallGrass.canBlockStay(par1World, j1, k1, l1))
                                    {
                                        par1World.setBlock(j1, k1, l1, Block.tallGrass.blockID, 1, 3);
                                    }
                                                              
                                    
                                }
                                else
                                {
                                    ForgeHooks.plantGrass(par1World, j1, k1, l1);
                                }
                                
                                
                            }
                        }
                    }

                    return true;
                }
            }
            
            
        }
        
        
        
        else if (par1World.getBlockMetadata(par2, par3, par4) == 7)
        {
            return false;
        }
        else
        {
            if (!par1World.isRemote)
            {
                ((BlockStem)Block.blocksList[l]).fertilizeStem(par1World, par2, par3, par4);
                
     
            }

            return true;
        }
    }

    else
    {
        if (!par1World.isRemote)
        {
            if ((double)par1World.rand.nextFloat() < 0.4D)
            {
                ((BlockMushroom)Block.blocksList[l]).fertilizeMushroom(par1World, par2, par3, par4, par1World.rand);
            }

            
            
        }  
        	}	    	
 
	return true;
	
    	}
	}


	
	
	

