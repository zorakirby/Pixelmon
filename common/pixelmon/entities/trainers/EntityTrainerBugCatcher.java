package pixelmon.entities.trainers;

import net.minecraft.src.World;
import pixelmon.entities.EntityTrainer;

public class EntityTrainerBugCatcher extends EntityTrainer {

	public EntityTrainerBugCatcher(World par1World) {
		super(par1World);
		texture = "/pixelmon/texture/trainers/bugcatcher.png";
		init();
	}

	public void init(){
		name = "BugCatcher";
		super.init();
	}
}
