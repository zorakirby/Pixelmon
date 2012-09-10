package pixelmon.entities.pixelmon;

import pixelmon.entities.pixelmon.helpers.AIHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public abstract class Entity7HasAI extends Entity6CanBattle {
	public enum Aggression {
		timid(0), passive(1), aggressive(2);
		Aggression(int i) {
			index = i;
		}

		public int index;

		public static Aggression getAggression(int index) {
			for (Aggression a: values())
				if (a.index == index) return a;
			return Aggression.passive;
		}
	};

	public Aggression aggression;

	private AIHelper aiHelper;
	
	public Entity7HasAI(World par1World) {
		super(par1World);
	}

	@Override
	protected void init(String name) {
		super.init(name);
		int r = rand.nextInt(100) + 1;
		if (baseStats.aggression == null) {
			aggression = Aggression.passive;
			return;
		}
		if (r < baseStats.aggression.timid)
			aggression = Aggression.timid;
		else if (r < baseStats.aggression.timid + baseStats.aggression.passive)
			aggression = Aggression.passive;
		else if (r < baseStats.aggression.timid + baseStats.aggression.passive + baseStats.aggression.aggressive)
			aggression = Aggression.aggressive;
		else
			aggression = Aggression.passive;

		aiHelper = new AIHelper(name, this, tasks);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("Aggression", aggression.index);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		aggression = Aggression.getAggression(nbt.getInteger("Aggression"));
	}
	
	public boolean isAIEnabled() {
		return true;
	}
}
