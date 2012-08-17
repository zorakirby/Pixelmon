package pixelmon.entities;

/**
 * 
 * Made so other players cant steal a players item from switching held items
 * 
 * @author Gerald
 * 
 */

import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntityOwnedItem extends EntityItem {

	private String owner;
	
	public EntityOwnedItem(World var1, double var2, double var3, double var4, ItemStack var5, String var6) {
		super(var1, var2, var3, var4, var5);
		owner = var6;
	}
	
	public void writeEntityToNBT(NBTTagCompound var1)
	{
		super.writeEntityToNBT(var1);
		var1.setString("Owner", owner);
	}
	
	public void readEntityFromNBT(NBTTagCompound var1)
	{
		super.readEntityFromNBT(var1);
		owner = var1.getString("Owner");
	}
	
	public void onCollideWithPlayer(EntityPlayer var1)
	{
		if(!owner.equals(var1.username))
		{
			return;
		}
		super.onCollideWithPlayer(var1);
	}

}
