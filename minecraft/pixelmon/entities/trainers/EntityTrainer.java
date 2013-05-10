package pixelmon.entities.trainers;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import pixelmon.Pixelmon;
import pixelmon.AI.AITrainerInBattle;
import pixelmon.api.events.EventType;
import pixelmon.api.events.PixelmonEventHandler;
import pixelmon.comm.ChatHandler;
import pixelmon.config.PixelmonEntityList;
import pixelmon.database.DatabaseTrainers;
import pixelmon.database.SpawnLocation;
import pixelmon.database.TrainerInfo;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PlayerStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityTrainer extends EntityCreature {

	public PlayerStorage pokemonStorage = new PlayerStorage(this);
	public EntityPixelmon releasedPokemon;
	public TrainerInfo info;
	private ModelBase model = null;
	public SpawnLocation trainerLocation;

	public EntityTrainer(World par1World) {
		super(par1World);
		dataWatcher.addObject(3, ""); // Name
		dataWatcher.addObject(4, "");// Model
		dataWatcher.addObject(25, (int) 0);
		dataWatcher.addObject(26, ""); // Nickname
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new AITrainerInBattle(this));
		tasks.addTask(2, new EntityAIWander(this, moveSpeed));
	}

	public void init(String name) {
		setName(name);
		pokemonStorage = new PlayerStorage(this);
		info = DatabaseTrainers.GetTrainerInfo(name);
		if (info == null) {
			System.out.println("Database entry error/missing for trainer " + name);
			setDead();
			return;
		}
		dataWatcher.updateObject(25, info.level);
		if (dataWatcher.getWatchableObjectString(4).equals(""))
			dataWatcher.updateObject(4, info.model);
		dataWatcher.updateObject(26, info.name);
		health = 100;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getTexture() {
		return "/pixelmon/texture/trainers/" + dataWatcher.getWatchableObjectString(4).toLowerCase() + ".png";
	}

	public int getAge() {
		return 0;
	};

	@Override
	protected boolean canDespawn() {
		return true;
	}

	public ModelBase getModel() {
		if (model == null)
			model = Pixelmon.proxy.getTrainerModel(dataWatcher.getWatchableObjectString(4));
		return model;
	}

	public String getName() {
		return dataWatcher.getWatchableObjectString(3);
	}

	public String getNickName() {
		return dataWatcher.getWatchableObjectString(26);
	}

	public void setName(String name) {
		dataWatcher.updateObject(3, name);
	}

	@Override
	public int getMaxHealth() {
		return 100;
	}

	public void releasePokemon() {
		if (pokemonStorage.count() == 0)
			loadPokemon();
		else {

		}
		EntityPixelmon p = pokemonStorage.getFirstAblePokemon(worldObj);
		if (p != null) {
			releasedPokemon = p;
			p.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
			motionX = 0;
			motionY = 0;
			motionZ = 0;
			p.releaseFromPokeball();
		}
	}

	public boolean hasAblePokemon() {
		if (pokemonStorage.countAblePokemon() == 0)
			return false;
		return true;
	}

	public void loadPokemon() {
		for (String pokemonName : info.partypokemon) {
			EntityPixelmon p = (EntityPixelmon) PixelmonEntityList.createEntityByName(pokemonName, worldObj);
			if (p != null) {
				p.getLvl().setLevel((new Random()).nextInt(3) - 1 + info.level);
				p.setEntityHealth(p.stats.HP);
				p.setTrainer(this);
				pokemonStorage.addToParty(p);
			}
		}
	}

	public void startBattle(EntityPlayer player) {
		ChatHandler.sendBattleMessage(player, info.greeting);
	}

	public void loseBattle(EntityLiving entityLiving) {
		if (entityLiving instanceof EntityPlayer){
			ChatHandler.sendBattleMessage(entityLiving, info.loseMessage);
			PixelmonEventHandler.fireEvent(EventType.BeatTrainer, (EntityPlayer)entityLiving);
		}
	}

	public void winBattle(EntityLiving entityLiving) {
		ChatHandler.sendBattleMessage(entityLiving, info.winMessage);
	}

	public void retrievePokemon() {
		pokemonStorage.retrieve(releasedPokemon);
	}

	public void healAllPokemon() {
		for (NBTTagCompound nbt : pokemonStorage.partyPokemon) {
			if (nbt != null) {
				nbt.setShort("Health", (short) nbt.getInteger("StatsHP"));
				nbt.setBoolean("IsFainted", false);
				int numMoves = nbt.getInteger("PixelmonNumberMoves");
				for (int i = 0; i < numMoves; i++) {
					nbt.setInteger("PixelmonMovePP" + i, nbt.getInteger("PixelmonMovePPBase" + i));
				}
				if (nbt.hasKey("EffectCount")) {
					int numStatus = nbt.getShort("EffectCount");
					for (int i = 0; i < numStatus; i++) {
						nbt.removeTag("Effect" + i);
					}
					nbt.setShort("EffectCount", (short) 0);
				}
			}
		}
	}

	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);

		int blockId = this.worldObj.getBlockId(var1, var2 - 1, var3);
		return blockId == Block.grass.blockID || blockId == Block.sand.blockID;

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setString("Name", getName());
		nbt.setString("Model", dataWatcher.getWatchableObjectString(4));
		if (trainerLocation == null)
			trainerLocation = SpawnLocation.Land;
		nbt.setInteger("trainerLocation", trainerLocation.index);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setName(nbt.getString("Name"));
		if (nbt.hasKey("Model")) {
			dataWatcher.updateObject(4, nbt.getString("Model"));
		}
		if (nbt.hasKey("trainerLocation"))
			trainerLocation = SpawnLocation.getFromIndex(nbt.getInteger("trainerLocation"));
		else
			trainerLocation = SpawnLocation.Land;
		init(getName());
	}

	public int getNextPokemonID() {
		EntityPixelmon p = pokemonStorage.getFirstAblePokemon(worldObj);
		return p.getPokemonId();
	}

	public int getLvl() {
		return dataWatcher.getWatchableObjectInt(25);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}
}
