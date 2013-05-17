package pixelmon.entities.pixelmon.interactions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import pixelmon.api.interactions.IInteraction;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.items.ItemTM;
import pixelmon.storage.PixelmonStorage;

public class InteractionTM implements IInteraction {

	@Override
	public boolean interact(EntityPixelmon entityPixelmon, EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			ItemStack itemstack = ((EntityPlayer) player).getCurrentEquippedItem();
			if (itemstack.getItem() instanceof ItemTM) {
				if (player != entityPixelmon.getOwner())
					return true;
				if (DatabaseMoves.CanLearnAttack(entityPixelmon.getName(), ((ItemTM) itemstack.getItem()).attackName)) {
					Attack a = DatabaseMoves.getAttack(((ItemTM) itemstack.getItem()).attackName);
					if (a == null) {
						ChatHandler.sendChat(entityPixelmon.getOwner(), ((ItemTM) itemstack.getItem()).attackName + " is corrupted");
						return true;
					}
					a.STAB = DatabaseMoves.hasSTAB(entityPixelmon.getName(), ((ItemTM) itemstack.getItem()).attackName);
					if (entityPixelmon.moveset.size() >= 4) {
						((EntityPlayerMP) entityPixelmon.getOwner()).playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(
								EnumPackets.ChooseMoveToReplace, entityPixelmon.getPokemonId(), a.baseAttack.attackIndex, entityPixelmon.getLvl().getLevel()));
					} else {
						entityPixelmon.moveset.add(a);
						ChatHandler.sendChat(entityPixelmon.getOwner(), entityPixelmon.getName() + " just learnt " + a.baseAttack.attackName + "!");
					}
					entityPixelmon.updateNBT();
					if (!player.capabilities.isCreativeMode)
						player.inventory.consumeInventoryItem(itemstack.itemID);
				} else {
					ChatHandler.sendChat(entityPixelmon.getOwner(), entityPixelmon.getName() + " can't learn " + ((ItemTM) itemstack.getItem()).attackName
							+ "!");
				}
				return true;
			}
		}
		return false;
	}

}
