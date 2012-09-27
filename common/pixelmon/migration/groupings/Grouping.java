package pixelmon.migration.groupings;

import java.util.ArrayList;

import net.minecraft.src.World;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class Grouping {
	public ArrayList<EntityPixelmon> pixelmon;
	public EntityPixelmon leader;
	public World worldObj;
	public boolean emptyGroup;
	public boolean oneType;

	public Grouping(String creatureName, World w) {
		pixelmon = new ArrayList<EntityPixelmon>();
		oneType = false;
		worldObj = w;
		leader = new EntityPixelmon(worldObj);
		leader.init(creatureName);
		emptyGroup = false;
	}
	
	public Grouping setOneType(boolean b){
		oneType = b;
		return this;
	}
	
	public void addPixelmon(String name){
		if(oneType){
			if(!name.equals(leader.getName())){
				throw new RuntimeException(name + " was tried to be put in a one type group of " + leader.getName());
			}
			else{
				EntityPixelmon p = new EntityPixelmon(worldObj);
				p.init(name);
				pixelmon.add(p);
			}
		}
		else{
			EntityPixelmon p = new EntityPixelmon(worldObj);
			p.init(name);
			pixelmon.add(p);
		}
	}
	
	public void pixelmonKilled(EntityPixelmon p){
		if(p == leader){
			leader = null;
			if(pixelmon.isEmpty()){
				emptyGroup = true;
			}
			else{
				EntityPixelmon newLeader = null;
				int level = 0;
				for(EntityPixelmon p1: pixelmon){
					int l = p1.getLvl().getLevel();
					if(l > level){
						newLeader = p1;
						level = l;
					}
				}
				pixelmon.remove(newLeader);
				leader = newLeader;
			}
		}
		else{
			pixelmon.remove(p);
		}
	}

}
