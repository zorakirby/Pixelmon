package pixelmon.entities;

import pixelmon.Pixelmon;
import pixelmon.battles.BattleController;
import pixelmon.battles.BattlePerspective;
import pixelmon.battles.participants.IBattleParticipant;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.battles.participants.TrainerParticipant;
import pixelmon.battles.participants.WildPixelmonParticipant;
import pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public class EntityCamera extends EntityLiving {
	boolean clientSided = false;

	public EntityCamera(World world) {
		super(world);
		this.tasks.taskEntries.clear();
		Pixelmon.proxy.registerCameraEntity(this);
		clientSided = true;
	}

	public EntityPlayer player;
	public EntityPixelmon userPokemon;
	public EntityLiving target;
	private BattleController battleController;

	public EntityCamera(World par1World, EntityPlayer player,
			BattleController battleController) {
		super(par1World);
		this.player = player;
		this.battleController = battleController;
		setPositionAndUpdate(player.posX, player.posY, player.posZ);
		this.tasks.taskEntries.clear();
	}

	@Override
	public int getMaxHealth() {
		return 1;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected void fall(float par1) {
	}

	@Override
	public void onEntityUpdate() {
		if (battleController == null && !clientSided)
			setDead();
		super.onEntityUpdate();
		motionX = 0;
		motionY = 0;
		motionZ = 0;
	}

	public void updatePosition() {

		setPositionAndUpdate(player.posX + 1, player.posY + 0.5,
				player.posZ + 1);
		rotationPitch += 0.01f;
		rotationYaw += 0.01f;
	}
}
