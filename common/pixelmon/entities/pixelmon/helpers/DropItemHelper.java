package pixelmon.entities.pixelmon.helpers;

import pixelmon.config.PixelmonItems;
import pixelmon.entities.pixelmon.Entity8HoldsItems;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class DropItemHelper {

	Entity8HoldsItems pixelmon;

	public DropItemHelper(Entity8HoldsItems entity8HoldsItems) {
		pixelmon = entity8HoldsItems;
	}

	public int getDropItemID() {
		if (pixelmon.baseStats.droppedItem == null)
			return 0;

		if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Apple"))
			return Item.appleRed.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Blaze Powder"))
			return Item.blazePowder.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Bones"))
			return Item.bone.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Diamond"))
			return Item.diamond.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Egg"))
			return Item.egg.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Ender Pearl"))
			return Item.enderPearl.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Feather"))
			return Item.feather.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Glowstone"))
			return Item.lightStoneDust.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Gold Nugget"))
			return Item.goldNugget.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Gunpowder"))
			return Item.gunpowder.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Ink sac")) {
			pixelmon.entityDropItem(new ItemStack(Item.dyePowder, 1, 0), 0.0F);
			return 0;
		} else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Iron Ingot"))
			return Item.ingotIron.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Leather"))
			return Item.leather.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Raw Beef"))
			return Item.beefRaw.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Raw Fish"))
			return Item.fishRaw.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Rotten Flesh"))
			return Item.rottenFlesh.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Seeds"))
			return Item.seeds.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Slimeball"))
			return Item.slimeBall.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("String"))
			return Item.silk.shiftedIndex;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Thunderstone Shard"))
			return PixelmonItems.thunderStoneShardID;
		else if (pixelmon.baseStats.droppedItem.equalsIgnoreCase("Wool"))
			return Block.cloth.blockID;;

		return 0;
	}
}
