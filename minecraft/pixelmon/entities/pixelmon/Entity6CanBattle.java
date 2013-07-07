package pixelmon.entities.pixelmon;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import pixelmon.Pixelmon;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.EffectParser;
import pixelmon.battles.controller.BattleController;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.battles.status.StatusBase;
import pixelmon.battles.status.StatusPersist;
import pixelmon.battles.status.StatusType;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.npcs.EntityTrainer;
import pixelmon.entities.pixelmon.helpers.BattleVariables;
import pixelmon.entities.pixelmon.stats.BattleStats;
import pixelmon.entities.pixelmon.stats.Moveset;
import pixelmon.enums.EnumBossMode;
import pixelmon.enums.EnumGui;
import pixelmon.storage.PixelmonStorage;

public abstract class Entity6CanBattle extends Entity5Rideable {
	public BattleStats battleStats = new BattleStats(this);
	public ArrayList<StatusBase> status = new ArrayList<StatusBase>();
	public Moveset moveset = new Moveset();
	public BattleController battleController;
	protected EntityTrainer trainer;
	public boolean isLockedInBattle = false;
	public Attack lastMoveUsed;
	public Attack disabledMove;

	public BattleVariables battleVariables = new BattleVariables();

	public Entity6CanBattle(World par1World) {
		super(par1World);
		dataWatcher.addObject(15, ""); // Trainer Name
	}

	public void loadMoveset() {
		moveset = DatabaseMoves.GetInitialMoves(getName(), getLvl().getLevel());
	}

	@Override
	public void setBoss(EnumBossMode mode) {
		super.setBoss(mode);
	}

	public void StartBattle(BattleParticipant p1, BattleParticipant p2) {
		if (moveset.size() == 0)
			loadMoveset();

		try {
			battleController = new BattleController(p1, p2);
		} catch (Exception e) {
			battleController = null;
			return;
		}
		p2.currentPokemon().battleController = battleController;
		if (p2.currentPokemon().moveset.size() == 0)
			p2.currentPokemon().loadMoveset();

		
		// pixelmon.isSwimming = false;
	}

	public void EndBattle() {
		// pixelmon.isSwimming = true;
		this.wipeLastMoveUsed();
		battleController = null;
		
	}

	public void setTrainer(EntityTrainer trainer) {
		this.trainer = trainer;
		dataWatcher.updateObject(15, trainer.info.name);
	}

	public EntityTrainer getTrainer() {
		return trainer;
	}

	public String getTrainerName() {
		return dataWatcher.getWatchableObjectString(15);
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if (!worldObj.isRemote) {
			if (par1DamageSource.damageType == "player" || par1DamageSource == DamageSource.cactus || par1DamageSource.damageType == "arrow")
				return false;
			if (battleController != null) {
				if (par1DamageSource == DamageSource.cactus || par1DamageSource == DamageSource.drown || par1DamageSource == DamageSource.fall 
						|| par1DamageSource == DamageSource.inFire || par1DamageSource == DamageSource.inWall
						|| par1DamageSource == DamageSource.lava || par1DamageSource == DamageSource.onFire)
					return false;
			}
			boolean flag = super.attackEntityFrom(par1DamageSource, par2);
			updateHealth();
			if (battleController != null) {
				for (BattleParticipant p : battleController.participants)
					if (p instanceof PlayerParticipant && p.currentPokemon() != this)
						((PlayerParticipant) p).updateOpponentHealth((EntityPixelmon) this);
			}
			if (func_110143_aJ() <= 0) {
				this.onDeath(par1DamageSource);
			}

			Entity entity = par1DamageSource.getEntity();
			if (getOwner() != null)
				updateNBT();
			if (isValidTarget(entity)) {
				setAttackTarget((EntityLiving) entity);
				setTarget(entity);
			}
			return flag;
		}
		return false;
	}

	public void LearnMove() {
		if (moveset.size() >= 4) {
			ArrayList<Attack> attacks = getAttacksAtLevel(getLvl().getLevel());
			for (int i = 0; i < attacks.size(); i++)
				((EntityPlayerMP) getOwner()).openGui(Pixelmon.instance, EnumGui.LearnMove.getIndex(), worldObj, getPokemonId(), attacks.get(i).baseAttack.attackIndex, 0);
		}
	}

	public ArrayList<Attack> getAttacksAtLevel(int level) {
		return DatabaseMoves.getAttacksAtLevel(getName(), level);
	}

	public boolean LearnsAttackAtLevel(int level) {
		return DatabaseMoves.LearnsAttackAtLevel(getName(), level);
	}

	protected boolean isValidTarget(Entity entity) {
		return entity instanceof EntityPixelmon;
	}

	public boolean removeStatus(StatusType s) {
		for (int i = 0; i < status.size(); i++) {
			StatusBase base = status.get(i);
			if (base.type == s) {
				status.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean hasStatus(StatusType s) {
		for (int i = 0; i < status.size(); i++) {
			StatusBase base = status.get(i);
			if (base.type == s) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		moveset.writeToNBT(nbt);
		for (int i = 0; i < status.size(); i++) {
			try {
				if (status.get(i) instanceof StatusPersist)
					((StatusPersist) status.get(i)).writeToNBT(status.get(i).type.index, nbt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		nbt.setShort("EffectCount", (short) status.size());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		moveset.readFromNBT(nbt);
		int statusCount = 0;
		statusCount = nbt.getShort("EffectCount");
		for (int i = 0; i < statusCount; i++) {
			StatusPersist s = StatusType.getEffectInstance(nbt.getInteger("Effect" + i));
			status.add(s.restoreFromNBT(nbt));
		}
	}
	public Attack getLastMoveUsed(){

		return lastMoveUsed;
	}
	
	public void wipeLastMoveUsed(){
		lastMoveUsed = null;
	}
}
