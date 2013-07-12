package pixelmon.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pixelmon.entities.projectiles.EntityHook;
import pixelmon.enums.EnumRodType;

public class ItemFishingRod extends PixelmonItem {

	EnumRodType rodType;
	
	public ItemFishingRod(int par1, EnumRodType rodType, String name) {
		super(par1, rodType.textureName, name);
		this.rodType = rodType;
	}
	
	@SideOnly(Side.CLIENT)

    /**
     * Returns True is the item is renderer in full 3D when held.
     */
    public boolean isFull3D()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if this item should be rotated by 180 degrees around the Y axis when being held in an entities
     * hands.
     */
    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {	
    	
        if (par3EntityPlayer.fishEntity != null)
        {
            int i = par3EntityPlayer.fishEntity.catchFish();
            par1ItemStack.damageItem(i, par3EntityPlayer);
            par3EntityPlayer.swingItem();
        }
        else
        {
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!par2World.isRemote)
            {
				par2World.spawnEntityInWorld(new EntityHook(par2World, par3EntityPlayer, rodType));
            }
            

            par3EntityPlayer.swingItem();
        }

        return par1ItemStack;
    }


}
