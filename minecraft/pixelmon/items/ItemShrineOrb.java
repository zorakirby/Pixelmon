package pixelmon.items;

import pixelmon.comm.ChatHandler;
import pixelmon.enums.EnumShrine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemShrineOrb extends Item {
	private String texturePath;
	public String itemNames;
	// public boolean isFilled == false;
	public int exp;
	public int orbExp;
	private int zero = 0;

	public ItemShrineOrb(int par1, EnumShrine shrine, String textureName, String itemName) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setMaxStackSize(5);
		this.itemNames = itemName;
		this.texturePath = textureName;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("pixelmon:emptyorb");
	}

	// 22070 = 100 Levels of EXPerience
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if (par3Entity instanceof EntityPlayer) {
			exp = ((EntityPlayer) par3Entity).experienceLevel;
			if (exp != 0) {
				orbExp += exp;
				((EntityPlayer) par3Entity).experienceLevel = zero;
			}
		} else
			return;
		if (par1ItemStack.stackTagCompound == null)
			par1ItemStack.setTagCompound(new NBTTagCompound());

		}
	}
