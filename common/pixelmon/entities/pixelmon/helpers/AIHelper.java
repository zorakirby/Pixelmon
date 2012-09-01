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
import pixelmon.AI.PixelmonAIIsInBattle;
import pixelmon.AI.PixelmonAIStartBattle;
import pixelmon.AI.PixelmonAITargetNearest;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.pixelmon.Entity7HasAI.Aggression;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class AIHelper {

	public AIHelper(String Name, EntityPixelmon pixelmon, EntityAITasks tasks) {
		tasks.addTask(0, new EntityAISwimming(pixelmon));
		tasks.addTask(1, new PixelmonAIIsInBattle(pixelmon));
		if (pixelmon.aggression== Aggression.aggressive) {
			tasks.addTask(2, new EntityAIMoveTowardsTarget(pixelmon, pixelmon.getMoveSpeed(), 15));
			tasks.addTask(3, new PixelmonAITargetNearest(pixelmon, 10, true));
			tasks.addTask(4, new PixelmonAIStartBattle(pixelmon));
		} else if (pixelmon.aggression == Aggression.timid){
			tasks.addTask(5, new EntityAIAvoidEntity(pixelmon, EntityPlayer.class, 16.0F, 0.23F, 0.4F));
		}
		tasks.addTask(6, new EntityAIFollowOwner(pixelmon, 0.3F, 10.0F, 4.0F));
		tasks.addTask(7, new EntityAITempt(pixelmon, pixelmon.getMoveSpeed(), PixelmonItems.rareCandy.shiftedIndex, false));
		tasks.addTask(8, new EntityAIWander(pixelmon, pixelmon.getMoveSpeed()));
		tasks.addTask(9, new EntityAIWatchClosest(pixelmon, pixelmon.entities.pixelmon.EntityPixelmon.class, 8F));
		tasks.addTask(10, new EntityAILookIdle(pixelmon));
	}

}
