package pixelmon.spawning.spawners;

import pixelmon.database.SpawnLocation;
import net.minecraft.world.World;

public class SpawnRequestEvent {
	public final World world;
	public final int x, y, z;
	public final SpawnLocation spawn;
	public boolean approved;
	
	public SpawnRequestEvent(World world, int x, int y, int z, SpawnLocation spawn){
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.spawn = spawn;
		this.approved = true;
	}

}
