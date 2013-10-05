package pixelmon.entities.pixelmon.interactions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import pixelmon.api.interactions.IInteraction;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.EnumUpdateType;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.packetHandlers.ReplaceMove;
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
				if (DatabaseMoves.CanLearnAttack(entityPixelmon.baseStats.id, ((ItemTM) itemstack.getItem()).attackName)) {
					Attack a = DatabaseMoves.getAttack(((ItemTM) itemstack.getItem()).attackName);
					if (a == null) {
						ChatHandler.sendChat(entityPixelmon.getOwner(), ((ItemTM) itemstack.getItem()).attackName + " is corrupted");
						return true;
					}
					if (entityPixelmon.getMoveset().size() >= 4) {
						ReplaceMove.tmID = itemstack.itemID;
						((EntityPlayerMP) entityPixelmon.getOwner()).playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(
								EnumPackets.ChooseMoveToReplace, entityPixelmon.getPokemonId(), a.baseAttack.attackIndex, entityPixelmon.getLvl().getLevel()));
					} else {
						entityPixelmon.getMoveset().add(a);
						ChatHandler.sendChat(entityPixelmon.getOwner(), entityPixelmon.getName() + " just learnt " + a.baseAttack.attackName + "!");
						if (!player.capabilities.isCreativeMode)
							player.inventory.consumeInventoryItem(itemstack.itemID);
					}
					entityPixelmon.update(EnumUpdateType.Moveset);
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
