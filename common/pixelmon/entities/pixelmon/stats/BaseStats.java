package pixelmon.entities.pixelmon.stats;

import java.util.ArrayList;

import net.minecraft.src.EnumCreatureType;
import pixelmon.database.DatabaseStats;
import pixelmon.database.ExperienceGroup;
import pixelmon.database.SpawnConditions;
import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.enums.EnumType;

public class BaseStats {
	

	private Entity3HasStats entity;
	public BaseStatsStore store;

	public BaseStats(Entity3HasStats entity) {
		this.entity = entity;
		

}
