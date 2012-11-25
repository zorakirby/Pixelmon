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
import pixelmon.gui.battles.ClientBattleManager;
import net.minecraft.client.Minecraft;
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

	public EntityCamera(World par1World, EntityPlayer player, BattleController battleController) {
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
		if (battleController != null) {
			if (battleController.battleEnded == false) {
				Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
			} else if (battleController.battleEnded) {
				Minecraft.getMinecraft().renderViewEntity = Minecraft.getMinecraft().thePlayer;
			}
		}
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	public void onUpdate() {
		super.onUpdate();
	}

	public void updatePosition() {
		setAttackTarget(battleController.getOpponent(player).getEntity());
		if (battleController == null)
			setPositionAndUpdate(player.posX + 2, player.posY + 2.5, player.posZ + 2);
		if (battleController != null) {
			EntityLiving part1 = battleController.participant1.currentPokemon();
			EntityLiving part2 = battleController.participant2.currentPokemon();

			if (battleController.turnCount % 2 == 0) {
				setPositionAndUpdate(part1.posX, part1.posY, part1.posZ);
				rotationYaw = part1.rotationYawHead;
			} else {
				setPositionAndUpdate(part2.posX, part2.posY, part2.posZ);
				rotationYaw = part2.rotationYawHead;
			}
		}
	}
}
