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
	};

	public Aggression aggression;

	private AIHelper aiHelper;
	
	public Entity7HasAI(World par1World) {
		super(par1World);
	}

	@Override
	protected void init(String name) {
		super.init(name);
		aiHelper = new AIHelper(name, this, tasks);
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
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("Aggression", aggression.index);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		int a = nbt.getInteger("Aggression");
		switch (a) {
		case 0:
			aggression = Aggression.timid;
			break;
		case 1:
			aggression = Aggression.passive;
			break;
		case 2:
			aggression = Aggression.aggressive;
			break;
		}
	}
	
	public boolean isAIEnabled() {
		return true;
	}
}
