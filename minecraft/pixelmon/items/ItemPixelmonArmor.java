package pixelmon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.IArmorTextureProvider;
import pixelmon.config.PixelmonItems;
import pixelmon.migration.Pixelmon;

public class ItemPixelmonArmor extends ItemArmor implements IArmorTextureProvider{

    public ItemPixelmonArmor(int i, int index, EnumArmorMaterial enumArmorMaterial, int k, int l, String textureName, String itemName)
    {
        super(i, enumArmorMaterial, k, l);
        this.setMaxDamage(enumArmorMaterial.getDurability(l));
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.textureName = textureName;
        setUnlocalizedName(itemName);
    }
    
    String textureName;
    
    public void makeNewArmor(ItemStack stack, EntityPlayer player){
    	if(player.getCurrentItemOrArmor(1) != null){
			ItemStack boots1 = player.getCurrentItemOrArmor(1);
			ItemStack itemstack = new ItemStack(PixelmonItems.oldRunning, 1, 0);
			if(boots1.getItem() == PixelmonItems.newRunning && PixelmonItems.newRunning.getItemDamageFromStack(stack) == PixelmonItems.newRunning.getMaxDamage()){
				player.inventory.addItemStackToInventory(itemstack);
			}
    	}
    }

	

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(textureName);
	}
	
	public String getArmorTextureFile(ItemStack itemstack) {
		if(itemstack.getItem() == PixelmonItems.leggingsAluminium)
		return "/pixelmon/armor/aluminum_2.png";
		//else 

			if(itemstack.getItem() == PixelmonItems.newRunning){
		
			return"/pixelmon/armor/running_1.png";
			}
				if(itemstack.getItem() == PixelmonItems.oldRunning){
				
					return"/pixelmon/armor/oldrunning_1.png";
				}else{
					return "/pixelmon/armor/aluminum_1.png";
		}
			
		}
	}


