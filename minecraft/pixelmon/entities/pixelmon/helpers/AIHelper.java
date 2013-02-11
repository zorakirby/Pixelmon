package pixelmon.entities.pixelmon.helpers;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import pixelmon.AI.AIIsInBattle;
import pixelmon.AI.AIMoveTowardsTarget;
import pixelmon.AI.AIStartBattle;
import pixelmon.AI.AISwimming;
import pixelmon.AI.AITargetNearest;
import pixelmon.AI.AITeleportAway;
import pixelmon.config.PixelmonItems;
import pixelmon.database.SpawnLocation;
import pixelmon.entities.pixelmon.Entity7HasAI;
import pixelmon.entities.pixelmon.Entity7HasAI.Aggression;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class AIHelper {

	int i = 0;

	public AIHelper(String Name, Entity7HasAI entity, EntityAITasks tasks) {
		if (tasks.taskEntries.size() != 0)
			tasks.taskEntries.clear();
		initBaseAI(entity, tasks);
		if (((EntityPixelmon)entity).pokemonLocation == SpawnLocation.Land && !entity.baseStats.canFly) {
			initGroundAI(Name, entity, tasks);
		} else if (entity.baseStats.canFly) {
			initFlyingAI(Name, entity, tasks);
		} else if (((EntityPixelmon)entity).pokemonLocation == SpawnLocation.Water) {
			initSwimmingAI(Name, entity, tasks);
		}
	}

	private void initSwimmingAI(String name, Entity7HasAI entity, EntityAITasks tasks) {
		tasks.addTask(i++, new EntityAITempt(entity, entity.getMoveSpeed(), PixelmonItems.rareCandy.itemID, false));
		tasks.addTask(i++, new AISwimming(entity));
	}

	private void initFlyingAI(String name, Entity7HasAI entity, EntityAITasks tasks) {
		tasks.addTask(i++, new EntityAITempt(entity, entity.getMoveSpeed(), PixelmonItems.rareCandy.itemID, false));
		tasks.addTask(i++, new EntityAIWatchClosest(entity, pixelmon.entities.pixelmon.EntityPixelmon.class, 8F));
		tasks.addTask(i++, new EntityAILookIdle(entity));
	}

	private void initBaseAI(Entity7HasAI entity, EntityAITasks tasks) {
		if (((EntityPixelmon)entity).pokemonLocation == SpawnLocation.Water)
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
			tasks.addTask(i++, new EntityAITempt(entity, entity.getMoveSpeed(), PixelmonItems.rareCandy.itemID, false));
			tasks.addTask(i++, new EntityAIWander(entity, entity.getMoveSpeed()));
			tasks.addTask(i++, new EntityAIWatchClosest(entity, pixelmon.entities.pixelmon.EntityPixelmon.class, 8F));
			tasks.addTask(i++, new EntityAILookIdle(entity));
		}
	}

	public void clearAI() {
		
		
	}
}
