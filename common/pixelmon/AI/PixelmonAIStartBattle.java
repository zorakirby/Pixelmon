package pixelmon.AI;

import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.battles.participants.WildPixelmonParticipant;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import pixelmon.storage.PixelmonStorage;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIBase;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;

public class PixelmonAIStartBattle extends EntityAIBase {
	private EntityPixelmon theEntity;

	public PixelmonAIStartBattle(EntityPixelmon theEntity) {
		this.theEntity = theEntity;
		setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		if (theEntity.battleController != null)
			return false;
		if (theEntity.getOwner() != null)
			return false;
		if (theEntity.getAttackTarget() == null)
			return false;
		if (theEntity.getAttackTarget() instanceof EntityPlayer) {
			EntityPlayerMP player = (EntityPlayerMP) theEntity.getAttackTarget();
			EntityPixelmon firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage(player).getFirstAblePokemon(player.worldObj);
			theEntity.StartBattle(new WildPixelmonParticipant(theEntity), new PlayerParticipant(player, firstPokemon));
			return true;
		}
		if (((EntityLiving) theEntity).getAttackTarget().getDistanceSqToEntity((Entity) this.theEntity) < 2) {
			EntityPixelmon target = (EntityPixelmon)theEntity.getAttackTarget();
			theEntity.StartBattle(new WildPixelmonParticipant(theEntity), new WildPixelmonParticipant(target));
			return true;
		}
		return false;
	}
}
