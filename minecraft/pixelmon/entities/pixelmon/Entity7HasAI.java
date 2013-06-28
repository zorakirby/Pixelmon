package pixelmon.entities.pixelmon;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import pixelmon.RandomHelper;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.entities.pixelmon.helpers.AIHelper;

public abstract class Entity7HasAI extends Entity6CanBattle {

	private int lastOwnerX;
	private int lastOwnerY;
	private int lastOwnerZ;
	private ChunkCoordinates idleSpot;

	public enum Aggression {
		timid(0), passive(1), aggressive(2);
		Aggression(int i) {
			index = i;
		}

		public int index;

		public static Aggression getAggression(int index) {
			for (Aggression a : values())
				if (a.index == index)
					return a;
			return Aggression.passive;
		}
	};

	public Aggression aggression;
	public int aggressionTimer = 0;

	protected AIHelper aiHelper;

	public Entity7HasAI(World par1World) {
		super(par1World);
	}

	@Override
	protected void init(String name) {
		super.init(name);
		int r = rand.nextInt(100) + 1;
		lastOwnerX = lastOwnerY = lastOwnerZ = 0;
		idleSpot = null;
		pixelmon.entities.pixelmon.stats.Aggression a = baseStats.aggression;
		if (a == null) {
			aggression = Aggression.passive;
			return;
		}
		if (r < a.timid)
			aggression = Aggression.timid;
		else if (r < a.timid + a.passive)
			aggression = Aggression.passive;
		else if (r < a.timid + a.passive + a.aggressive)
			aggression = Aggression.aggressive;
		else
			aggression = Aggression.passive;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		if (aggression == null)
			aggression = Aggression.passive;
		nbt.setInteger("Aggression", aggression.index);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		aggression = Aggression.getAggression(nbt.getInteger("Aggression"));
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (aiHelper == null && baseStats != null)
			aiHelper = new AIHelper(getName(), this, tasks);
		if (aggressionTimer > 0)
			aggressionTimer--;
	}

	public void setIdleSpot(ChunkCoordinates coords) {
		idleSpot = coords;
	}

	public ChunkCoordinates getIdleSpot() {
		return idleSpot;
	}

	public void updateOwnerCoords() {
		lastOwnerX = (int) (getOwner().posX + 0.5D);
		lastOwnerY = (int) (getOwner().posY + 0.5D);
		lastOwnerZ = (int) (getOwner().posZ + 0.5D);
	}

	public int getLastOwnerX() {
		return lastOwnerX;
	}

	public int getLastOwnerY() {
		return lastOwnerY;
	}

	public int getLastOwnerZ() {
		return lastOwnerZ;
	}

	public boolean getIsIdle() {
		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	public void setIsIdle(boolean par1) {
		setIdleSpot(null);

		byte var2 = this.dataWatcher.getWatchableObjectByte(16);

		if (par1) {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (var2 | 1)));
		} else {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (var2 & -2)));
		}
	}

	@Override
	public void StartBattle(BattleParticipant p1, BattleParticipant p2) {
		super.StartBattle(p1, p2);
		if (aggression == Aggression.aggressive) {
			aggressionTimer = RandomHelper.getRandomNumberBetween(500, 2000);
		}
	}
}
