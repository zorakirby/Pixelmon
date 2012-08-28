package pixelmon.entities.pixelmon;

import pixelmon.AI.PixelmonAIIsInBattle;
import pixelmon.AI.PixelmonAITargetNearest;
import pixelmon.AI.PixelmonAIStartBattle;
import pixelmon.battles.BattleController;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.EntityTrainer;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import net.minecraft.src.EntityAIAvoidEntity;
import net.minecraft.src.EntityAIFollowOwner;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAIMoveTowardsTarget;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAITasks;
import net.minecraft.src.EntityAITempt;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraftforge.common.MinecraftForge;

public abstract class EntityGroundPixelmon extends BaseEntityPixelmon {
	private World world;

	public EntityGroundPixelmon(World world) {
		super(world);
		yOffset = 0f;
		this.world = world;
	}

	public void init() {
		super.init();
		loadAI();
	}

	public void loadAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new PixelmonAIIsInBattle(helper));
		if (helper.aggression > 0) {
			tasks.addTask(2, new EntityAIMoveTowardsTarget(this, moveSpeed, 15));
			tasks.addTask(3, new PixelmonAITargetNearest(this, 10, 50 - helper.aggression, true));
			tasks.addTask(4, new PixelmonAIStartBattle(this));
		}
		tasks.addTask(5, new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.23F, 0.4F));
        tasks.addTask(6, new EntityAIFollowOwner(this, 0.3F, 10.0F, 4.0F));
		tasks.addTask(7, new EntityAITempt(this, moveSpeed, PixelmonItems.rareCandy.shiftedIndex, false));
		tasks.addTask(8, new EntityAIWander(this, moveSpeed));
		tasks.addTask(9, new EntityAIWatchClosest(this, pixelmon.entities.pixelmon.BaseEntityPixelmon.class, 8F));
		tasks.addTask(10, new EntityAILookIdle(this));
	}
}
