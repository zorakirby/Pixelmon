package pixelmon.AI;

import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.battles.participants.WildPixelmonParticipant;
import pixelmon.entities.pixelmon.Entity7HasAI;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import pixelmon.storage.PixelmonStorage;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIBase;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;

public class AIStartBattle extends EntityAIBase {
	private Entity7HasAI theEntity;

	public AIStartBattle(Entity7HasAI entity7HasAI) {
		this.theEntity = entity7HasAI;
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
		if (((EntityLiving) theEntity).getAttackTarget().getDistanceSqToEntity((Entity) this.theEntity) < 40) {
			if (theEntity.getAttackTarget() instanceof EntityPlayer) {
				EntityPlayerMP player = (EntityPlayerMP) theEntity.getAttackTarget();
				EntityPixelmon firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage(player).getFirstAblePokemon(player.worldObj);
				theEntity.StartBattle(new WildPixelmonParticipant((EntityPixelmon)theEntity), new PlayerParticipant(player, firstPokemon));
				return true;
			}
			EntityPixelmon target = (EntityPixelmon)theEntity.getAttackTarget();
			theEntity.StartBattle(new WildPixelmonParticipant((EntityPixelmon)theEntity), new WildPixelmonParticipant(target));
			return true;
		}
		return false;
	}
}
