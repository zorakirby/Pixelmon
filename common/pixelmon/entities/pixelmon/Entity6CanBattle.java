package pixelmon.entities.pixelmon;

import java.util.ArrayList;

import pixelmon.battles.BattleController;
import pixelmon.battles.Moveset;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.participants.IBattleParticipant;
import pixelmon.database.BattleStats;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.EntityTrainer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public abstract class Entity6CanBattle extends Entity5Rideable {
	public BattleStats battleStats = new BattleStats();
	public ArrayList<StatusEffectBase> status = new ArrayList<StatusEffectBase>();
	public Moveset moveset = new Moveset();
	public BattleController battleController;
	public boolean wasBattleInitiator = false;
	public EntityTrainer trainer;
	
	public Entity6CanBattle(World par1World) {
		super(par1World);
	}

	public void loadMoveset() {
		moveset = DatabaseMoves.GetInitialMoves(getName(), getLvl().getLevel());
	}
	
	public void StartBattle(IBattleParticipant p1, IBattleParticipant p2) {
		if (moveset.size() == 0)
			loadMoveset();

		battleController = new BattleController(p1, p2);
		wasBattleInitiator = true;
		p2.currentPokemon().battleController = battleController;
		
		//pixelmon.isSwimming = false;
	}
	
	public void EndBattle() {
		//pixelmon.isSwimming = true;
		battleController = null;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		moveset.writeToNBT(nbt);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		moveset.readFromNBT(nbt);
	}
}
