package pixelmon.entities.pixelmon;

import pixelmon.AI.EntityAIIsInBattle;
import pixelmon.AI.EntityAINearestPixelmonTarget;
import pixelmon.AI.EntityAIStartBattle;
import pixelmon.battles.BattleController;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.EntityTrainer;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAIMoveTowardsTarget;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAITasks;
import net.minecraft.src.EntityAITempt;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public abstract class EntityGroundPixelmon extends BaseEntityPixelmon {
	private World world;
	public EntityGroundPixelmon(World world){
		super(world);
		yOffset=0f;
		this.world = world;
	}
	
	public void init(){
		super.init();
		loadAI();
	}
	public void loadAI(){
		if (helper.aggression >0){
			tasks.addTask(0, new EntityAIMoveTowardsTarget(this, moveSpeed, 15));
			tasks.addTask(1, new EntityAINearestPixelmonTarget(this, 10, 50-helper.aggression, true));
			tasks.addTask(2, new EntityAIStartBattle(this));
		}
		tasks.addTask(3, new EntityAISwimming(this));
		tasks.addTask(4, new EntityAITempt(this, moveSpeed,
				PixelmonItems.rareCandy.shiftedIndex, false));
		tasks.addTask(5, new EntityAIWander(this, moveSpeed));
		tasks.addTask(6, new EntityAIWatchClosest(this,
				pixelmon.entities.pixelmon.BaseEntityPixelmon.class, 8F));
		tasks.addTask(7, new EntityAILookIdle(this));
		tasks.addTask(8, new EntityAIIsInBattle(helper));
	}	
}
