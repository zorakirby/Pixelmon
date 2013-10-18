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
	public int exp;
	public static int orbExp;
	private int zero = 0;
	public static boolean isFilled = false;
	boolean filled = this.isFilled;

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
				if (orbExp < 100) {
					orbExp += exp;
					((EntityPlayer) par3Entity).experienceLevel = zero;
				}
			}
		} else {
			return;
		}
		if (orbExp <= 0) {
			ItemRendererExperience.percent = 0;
		} else if (orbExp > 0 & orbExp <= 3) {
			ItemRendererExperience.percent = .025F;
		} else if (orbExp > 3 & orbExp <= 6) {
			ItemRendererExperience.percent = .05F;
		} else if (orbExp > 6 & orbExp <= 9) {
			ItemRendererExperience.percent = .075F;
		} else if (orbExp > 9 & orbExp <= 12) {
			ItemRendererExperience.percent = .1F;
		} else if (orbExp > 12 & orbExp <= 15) {
			ItemRendererExperience.percent = .125F;
		} else if (orbExp > 15 & orbExp <= 18) {
			ItemRendererExperience.percent = .15F;
		} else if (orbExp > 18 & orbExp <= 21) {
			ItemRendererExperience.percent = .175F;
		} else if (orbExp > 21 & orbExp <= 24) {
			ItemRendererExperience.percent = .2F;
		} else if (orbExp > 24 & orbExp <= 27) {
			ItemRendererExperience.percent = .225F;
		} else if (orbExp > 27 & orbExp <= 30) {
			ItemRendererExperience.percent = .25F;
		} else if (orbExp > 30 & orbExp <= 33) {
			ItemRendererExperience.percent = .275F;
		} else if (orbExp > 33 & orbExp <= 36) {
			ItemRendererExperience.percent = .3F;
		} else if (orbExp > 36 & orbExp <= 39) {
			ItemRendererExperience.percent = .325F;
		} else if (orbExp > 39 & orbExp <= 42) {
			ItemRendererExperience.percent = .35F;
		} else if (orbExp > 42 & orbExp <= 45) {
			ItemRendererExperience.percent = .375F;
		} else if (orbExp > 45 & orbExp <= 48) {
			ItemRendererExperience.percent = .4F;
		} else if (orbExp > 48 & orbExp <= 51) {
			ItemRendererExperience.percent = .425F;
		} else if (orbExp > 51 & orbExp <= 54) {
			ItemRendererExperience.percent = .45F;
		} else if (orbExp > 57 & orbExp <= 60) {
			ItemRendererExperience.percent = .5F;
		} else if (orbExp > 60 & orbExp <= 63) {
			ItemRendererExperience.percent = .65F;
		} else if (orbExp > 63 & orbExp <= 66) {
			ItemRendererExperience.percent = .7F;
		} else if (orbExp > 66 & orbExp <= 69) {
			ItemRendererExperience.percent = .725F;
		} else if (orbExp > 69 & orbExp <= 72) {
			ItemRendererExperience.percent = .75F;
		} else if (orbExp > 72 & orbExp <= 75) {
			ItemRendererExperience.percent = .775F;
		} else if (orbExp > 75 & orbExp <= 78) {
			ItemRendererExperience.percent = .8F;
		} else if (orbExp > 78 & orbExp <= 81) {
			ItemRendererExperience.percent = .825F;
		} else if (orbExp > 81 & orbExp <= 84) {
			ItemRendererExperience.percent = .85F;
		} else if (orbExp > 84 & orbExp <= 87) {
			ItemRendererExperience.percent = .875F;
		} else if (orbExp > 87 & orbExp <= 90) {
			ItemRendererExperience.percent = .9F;
		} else if (orbExp > 90 & orbExp <= 93) {
			ItemRendererExperience.percent = .925F;
		} else if (orbExp > 93 & orbExp <= 96) {
			ItemRendererExperience.percent = .95F;
		} else if (orbExp > 96 & orbExp <= 99) {
			this.isFilled = true;
			ItemRendererExperience.percent = .975F;
		}
	}

}
