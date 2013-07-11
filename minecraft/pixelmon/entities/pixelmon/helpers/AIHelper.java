package pixelmon.entities.pixelmon.helpers;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import pixelmon.AI.*;
import pixelmon.config.PixelmonItems;
import pixelmon.database.SpawnLocation;
import pixelmon.entities.pixelmon.Entity7HasAI;
import pixelmon.entities.pixelmon.Entity7HasAI.Aggression;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumType;

public class AIHelper {

	int i = 0;

	public AIHelper(String Name, Entity7HasAI entity, EntityAITasks tasks) {
		if (tasks.taskEntries.size() != 0)
			tasks.taskEntries.clear();
		initBaseAI(entity, tasks);
		if (((EntityPixelmon) entity).pokemonLocation == SpawnLocation.Land && !entity.baseStats.canFly && !(entity.baseStats.isRideable && (entity.baseStats.type1 == EnumType.Water || entity.baseStats.type2 == EnumType.Water))) {
			initGroundAI(Name, entity, tasks);
		} else if (entity.baseStats.canFly) {
			initFlyingAI(Name, entity, tasks);
		} else if (((EntityPixelmon) entity).pokemonLocation == SpawnLocation.Water) {
			initSwimmingAI(Name, entity, tasks);
		}
	}

	private void initSwimmingAI(String name, Entity7HasAI entity, EntityAITasks tasks) {
		tasks.addTask(i++, new AITempt(entity, PixelmonItems.rareCandy.itemID, false));
		tasks.addTask(i++, new AISwimming(entity));
	}

	private void initFlyingAI(String name, Entity7HasAI entity, EntityAITasks tasks) {
		tasks.addTask(i++, new EntityAISwimming(entity));
		tasks.addTask(i++, new AITempt(entity, PixelmonItems.rareCandy.itemID, false));
		tasks.addTask(i++, new EntityAIWatchClosest(entity, pixelmon.entities.pixelmon.EntityPixelmon.class, 8F));
		tasks.addTask(i++, new EntityAIWander(entity, entity.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e()));
		tasks.addTask(i++, new AIFlying(entity));
	}

	private void initBaseAI(Entity7HasAI entity, EntityAITasks tasks) {
		tasks.addTask(i++, new AIIsInBattle(entity));
		if (((EntityPixelmon) entity).pokemonLocation != SpawnLocation.Water && !(entity.baseStats.isRideable && (entity.baseStats.type1 == EnumType.Water || entity.baseStats.type2 == EnumType.Water))) {
			if (entity.aggression == Aggression.aggressive) {
				tasks.addTask(i++, new AIStartBattle(entity));
				tasks.addTask(i++, new AIMoveTowardsTarget(entity, 10));
				tasks.addTask(i++, new AITargetNearest(entity, 10, true));
			} else if (entity.aggression == Aggression.timid) {
				tasks.addTask(i++, new EntityAIAvoidEntity(entity, EntityPlayer.class, 16.0F, 0.23F, 0.4F));
			}
		}
	}

	private void initGroundAI(String Name, Entity7HasAI entity, EntityAITasks tasks) {
		if (Name.equalsIgnoreCase("Abra")) {
			tasks.addTask(i++, new AITeleportAway(entity));
		} else {
			tasks.addTask(i++, new EntityAISwimming(entity));
			tasks.addTask(i++, new EntityAIFollowOwner(entity, 0.3F, 10.0F, 4.0F));
			tasks.addTask(i++, new AITempt(entity, PixelmonItems.rareCandy.itemID, false));
			tasks.addTask(i++, new AIWander(entity));
			tasks.addTask(i++, new EntityAIWatchClosest(entity, pixelmon.entities.pixelmon.EntityPixelmon.class, 8F));
			tasks.addTask(i++, new EntityAILookIdle(entity));
		}
	}

	public void clearAI() {

	}
}
