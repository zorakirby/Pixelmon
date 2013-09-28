package pixelmon.items;

import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeInstance;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import pixelmon.config.PixelmonItems;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPixelmonBoots extends ItemArmor {

	public static int speed = 0;
	public static boolean itemHeld = false;

	public ItemPixelmonBoots(int i, int index, EnumArmorMaterial enumArmorMaterial, int k, int l, String textureName, String itemName) {
		super(i, enumArmorMaterial, k, l);
		this.setMaxDamage(400);
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.textureName = textureName;
		setUnlocalizedName(itemName);
	}

	private static UUID runningShoesUUID = UUID.fromString("B7060ADF-8FAF-4C0F-B816-87CB5721979F");
	private static AttributeModifier oldRunningShoesModifier = new AttributeModifier(runningShoesUUID, SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(),
			0.5, 1);
	private static AttributeModifier newRunningShoesModifier = new AttributeModifier(runningShoesUUID, SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(),
			0.75, 1);

	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap o = HashMultimap.create();
		if (itemHeld == false) {
			if (this.itemID == PixelmonItems.oldRunningShoes.itemID) {
				o.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), oldRunningShoesModifier);
			} else if (this.itemID == PixelmonItems.newRunningShoes.itemID) {
				o.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), newRunningShoesModifier);
			}
		} else {
			HashMultimap.create();
		}
		return o;
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
		ItemStack boots = player.getCurrentItemOrArmor(1);
		AttributeInstance attributeinstance = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
		world = player.worldObj;
		if (player.getHeldItem() != null) {
			if (player.getHeldItem().getItem().itemID == PixelmonItems.oldRunningShoes.itemID && boots.getItem() != PixelmonItems.oldRunningShoes) {
				ItemPixelmonBoots.itemHeld = true;
			} else if (player.getHeldItem().getItem().itemID == PixelmonItems.newRunningShoes.itemID && boots.getItem() != PixelmonItems.newRunningShoes) {
				ItemPixelmonBoots.itemHeld = true;
			} else {
				ItemPixelmonBoots.itemHeld = false;
			}
		} else {
			ItemPixelmonBoots.itemHeld = false;
		}
		
		
		if(boots.getItem().itemID == PixelmonItems.newRunningShoes.itemID)
		if (ItemPixelmonBoots.bootLastX == 0 || ItemPixelmonBoots.bootLastZ == 0) {
			ItemPixelmonBoots.bootLastX = (int) player.getPlayerCoordinates().posX;
			ItemPixelmonBoots.bootLastZ = (int) player.getPlayerCoordinates().posZ;
		} else {
			int changeX = (int) (Math.abs(ItemPixelmonBoots.bootLastX - player.getPlayerCoordinates().posX));
			int changeZ = (int) (Math.abs(ItemPixelmonBoots.bootLastZ - player.getPlayerCoordinates().posZ));

			if (changeX > 2 || changeZ > 2) {
				boots.damageItem(2, player);
				ItemPixelmonBoots.bootLastX = (int) player.getPlayerCoordinates().posX;
				ItemPixelmonBoots.bootLastZ = (int) player.getPlayerCoordinates().posZ;
				if (boots.getItemDamage() == PixelmonItems.newRunningShoes.getMaxDamage()) {
					removeItem(player, boots);
					ItemStack oldShoes = new ItemStack(PixelmonItems.oldRunningShoes, 1, 0);
					player.inventory.addItemStackToInventory(oldShoes);
				}
			}
		}
	}

	public void removeItem(EntityPlayer ep, ItemStack removeitem) {
		IInventory inv = ep.inventory;
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i) != null) {
				ItemStack j = inv.getStackInSlot(i);
				if (j.getItem() != null && j.getItem() == removeitem.getItem()) {
					inv.setInventorySlotContents(i, null);
				}
			}
		}

	}

	public static int bootLastX = 0;
	public static int bootLastZ = 0;

	String textureName;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(textureName);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		if (stack.getItem() == PixelmonItems.newRunningShoes) {
			return "pixelmon:armor/running_1.png";
		} else {
			return "pixelmon:armor/oldrunning_1.png";
		}

	}
}
