package pixelmon.entities;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import pixelmon.Pixelmon;
import pixelmon.AI.AILookAtTarget;
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
		tasks.addTask(0, new AILookAtTarget(this));
	}

	@Override
	public int getMaxHealth() {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getRenderSizeModifier() {
		return 0;
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
		super.onEntityUpdate();
		if (battleController == null && !clientSided)
			setDead();
		
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	// problem was the rotation is being changed in the onUpdate method because
	// it extends entity living
	// I tried this in the onEntityUpdate but it still moved a bit so it must
	// happen here, those numbers need to be
	// replaced with whatever you want
	public void onUpdate() {
		super.onUpdate();
	}

	float newRotationPitch = 0;
	float newRotationYaw = 0;

	public void updatePosition() {
		setAttackTarget(battleController.getOpponent(player).getEntity());
		setPositionAndUpdate(player.posX + 2, player.posY + 2.5,
				player.posZ + 2);
	}
}
