package pixelmon.entities.pixelmon.helpers;

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
import net.minecraft.src.EnumCreatureType;
import pixelmon.AI.AIIsInBattle;
import pixelmon.AI.AIMoveTowardsTarget;
import pixelmon.AI.AIStartBattle;
import pixelmon.AI.AISwimming;
import pixelmon.AI.AITargetNearest;
import pixelmon.AI.AITeleportAway;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.pixelmon.Entity7HasAI;
import pixelmon.entities.pixelmon.Entity7HasAI.Aggression;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class AIHelper {

	int i = 0;

	public AIHelper(String Name, Entity7HasAI entity, EntityAITasks tasks) {
		if (tasks.taskEntries.size() != 0)
			tasks.taskEntries.clear();
		initBaseAI(entity, tasks);
		if (entity.baseStats.creatureType == EnumCreatureType.creature && !entity.baseStats.canFly) {
			initGroundAI(Name, entity, tasks);
		} else if (entity.baseStats.canFly) {
			initFlyingAI(Name, entity, tasks);
		} else if (entity.baseStats.creatureType == EnumCreatureType.waterCreature) {
			initSwimmingAI(Name, entity, tasks);
		}
	}

	private void initSwimmingAI(String name, Entity7HasAI entity, EntityAITasks tasks) {
		tasks.addTask(i++, new EntityAITempt(entity, entity.getMoveSpeed(), PixelmonItems.rareCandy.shiftedIndex, false));
		tasks.addTask(i++, new AISwimming(entity));
	}

	private void initFlyingAI(String name, Entity7HasAI entity, EntityAITasks tasks) {
		tasks.addTask(i++, new EntityAITempt(entity, entity.getMoveSpeed(), PixelmonItems.rareCandy.shiftedIndex, false));
		tasks.addTask(i++, new EntityAIWatchClosest(entity, pixelmon.entities.pixelmon.EntityPixelmon.class, 8F));
		tasks.addTask(i++, new EntityAILookIdle(entity));
	}

	private void initBaseAI(Entity7HasAI entity, EntityAITasks tasks) {
		if (entity.baseStats.creatureType != EnumCreatureType.waterCreature)
			tasks.addTask(i++, new EntityAISwimming(entity));
		tasks.addTask(i++, new AIIsInBattle(entity));
		if (entity.aggression == Aggression.aggressive) {
			tasks.addTask(i++, new AIStartBattle(entity));
			tasks.addTask(i++, new AIMoveTowardsTarget(entity, entity.getMoveSpeed(), 10));
			tasks.addTask(i++, new AITargetNearest(entity, 10, true));
		} else if (entity.aggression == Aggression.timid) {
			tasks.addTask(i++, new EntityAIAvoidEntity(entity, EntityPlayer.class, 16.0F, 0.23F, 0.4F));
		}
	}

	private void initGroundAI(String Name, Entity7HasAI entity, EntityAITasks tasks) {
		if (Name.equalsIgnoreCase("Abra")) {
			tasks.addTask(i++, new AITeleportAway(entity));
		} else {
			tasks.addTask(i++, new EntityAIFollowOwner(entity, 0.3F, 10.0F, 4.0F));
			tasks.addTask(i++, new EntityAITempt(entity, entity.getMoveSpeed(), PixelmonItems.rareCandy.shiftedIndex, false));
			tasks.addTask(i++, new EntityAIWander(entity, entity.getMoveSpeed()));
			tasks.addTask(i++, new EntityAIWatchClosest(entity, pixelmon.entities.pixelmon.EntityPixelmon.class, 8F));
			tasks.addTask(i++, new EntityAILookIdle(entity));
		}
	}

	public void clearAI() {
		
		
	}
}
