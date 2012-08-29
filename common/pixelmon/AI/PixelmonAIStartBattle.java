package pixelmon.AI;

import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.battles.participants.WildPixelmonParticipant;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.storage.PixelmonStorage;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIBase;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;

public class PixelmonAIStartBattle extends EntityAIBase {
	private IHaveHelper theEntity;

	public PixelmonAIStartBattle(IHaveHelper theEntity) {
		this.theEntity = theEntity;
		setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		if (theEntity.getHelper().bc != null)
			return false;
		if (theEntity.getHelper().getOwner() != null)
			return false;
		if (((BaseEntityPixelmon) theEntity).getAttackTarget() == null)
			return false;
		if (((EntityLiving) theEntity).getAttackTarget() instanceof EntityPlayer) {
			EntityPlayerMP player = (EntityPlayerMP) ((BaseEntityPixelmon) theEntity).getAttackTarget();
			PixelmonEntityHelper firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage(player).getFirstAblePokemon(player.worldObj).getHelper();
			theEntity.getHelper().StartBattle(new WildPixelmonParticipant(theEntity.getHelper()), new PlayerParticipant(player, firstPokemon));
			return true;
		}
		if (((EntityLiving) theEntity).getAttackTarget().getDistanceSqToEntity((Entity) this.theEntity) < 2) {
			PixelmonEntityHelper target = ((IHaveHelper) ((EntityLiving) theEntity).getAttackTarget()).getHelper();
			theEntity.getHelper().StartBattle(new WildPixelmonParticipant(theEntity.getHelper()), new WildPixelmonParticipant(target));
			return true;
		}
		return false;
	}
}
