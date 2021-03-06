package pixelmon.entities.pixelmon.helpers;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import pixelmon.RandomHelper;
import pixelmon.comm.BossDropPacket;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.pixelmon.Entity8HoldsItems;
import pixelmon.enums.EnumBossMode;

public class DropItemHelper {

	Entity8HoldsItems pixelmon;

	public DropItemHelper(Entity8HoldsItems entity8HoldsItems) {
		pixelmon = entity8HoldsItems;
	}

	public int getDropItemID() {
		if (!PixelmonConfig.pokemonDropsEnabled || pixelmon.hasOwner() || pixelmon.getTrainer() != null)
			return 0;
		if (pixelmon.getBossMode() != EnumBossMode.Normal) {
			return 0;
		}
		if (pixelmon.baseStats.droppedItem == null)
			return 0;
		String[] droppedItems = pixelmon.baseStats.droppedItem.split(";");
		String droppedItem = droppedItems[RandomHelper.getRandomNumberBetween(0, droppedItems.length - 1)];
		if (droppedItem == null)
			return 0;

		if (droppedItem.equalsIgnoreCase("Apple"))
			return Item.appleRed.itemID;
		else if (droppedItem.equalsIgnoreCase("Blaze Powder"))
			return Item.blazePowder.itemID;
		else if (droppedItem.equalsIgnoreCase("Bones"))
			return Item.bone.itemID;
		else if (droppedItem.equalsIgnoreCase("Diamond"))
			return Item.diamond.itemID;
		else if (droppedItem.equalsIgnoreCase("Egg"))
			return Item.egg.itemID;
		else if (droppedItem.equalsIgnoreCase("Ender Pearl"))
			return Item.enderPearl.itemID;
		else if (droppedItem.equalsIgnoreCase("Feather"))
			return Item.feather.itemID;
		else if (droppedItem.equalsIgnoreCase("Glowstone"))
			return Item.glowstone.itemID;
		else if (droppedItem.equalsIgnoreCase("Gold Nugget"))
			return Item.goldNugget.itemID;
		else if (droppedItem.equalsIgnoreCase("Gunpowder"))
			return Item.gunpowder.itemID;
		else if (droppedItem.equalsIgnoreCase("Ink sac"))
			return Item.dyePowder.itemID;
		else if (droppedItem.equalsIgnoreCase("Iron Ingot"))
			return Item.ingotIron.itemID;
		else if (droppedItem.equalsIgnoreCase("Leather"))
			return Item.leather.itemID;
		else if (droppedItem.equalsIgnoreCase("Raw Beef"))
			return Item.beefRaw.itemID;
		else if (droppedItem.equalsIgnoreCase("Raw Fish"))
			return Item.fishRaw.itemID;
		else if (droppedItem.equalsIgnoreCase("Rotten Flesh"))
			return Item.rottenFlesh.itemID;
		else if (droppedItem.equalsIgnoreCase("Seeds"))
			return Item.seeds.itemID;
		else if (droppedItem.equalsIgnoreCase("Slimeball"))
			return Item.slimeBall.itemID;
		else if (droppedItem.equalsIgnoreCase("String"))
			return Item.silk.itemID;
		else if (droppedItem.equalsIgnoreCase("Thunderstone Shard"))
			return PixelmonItems.thunderStoneShardID;
		else if (droppedItem.equalsIgnoreCase("Wool"))
			return Block.cloth.blockID;
		;

		return 0;
	}

	public static ArrayList<Item> bossDropItems = new ArrayList<Item>();

	public void dropBossItems(EntityPlayerMP player) {
		int numDrops = pixelmon.getBossMode().numDroppedItems + player.getRNG().nextInt(3);
		int[] drops = new int[numDrops];
		for (int i = 0; i < numDrops; i++) {
			Item item = bossDropItems.get(player.getRNG().nextInt(bossDropItems.size()));
			if (!player.inventory.addItemStackToInventory(new ItemStack(item, 1, 0)))
				player.dropItem(item.itemID, 1);
			else
				player.sendContainerToPlayer(player.inventoryContainer);
			drops[i] = item.itemID;
		}
		BossDropPacket p = new BossDropPacket(drops);
		player.playerNetServerHandler.sendPacketToPlayer(p.getPacket());
	}
}
