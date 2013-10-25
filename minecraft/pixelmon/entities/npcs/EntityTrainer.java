package pixelmon.entities.npcs;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import pixelmon.Pixelmon;
import pixelmon.RandomHelper;
import pixelmon.AI.AITrainerInBattle;
import pixelmon.api.events.EventType;
import pixelmon.api.events.PixelmonEventHandler;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.comm.ChatHandler;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonEntityList;
import pixelmon.config.PixelmonItems;
import pixelmon.database.DatabaseTrainers;
import pixelmon.database.SpawnLocation;
import pixelmon.database.TrainerInfo;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPokemon;
import pixelmon.storage.PlayerStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityTrainer extends EntityNPC {

	public PlayerStorage pokemonStorage = new PlayerStorage(this);
	public EntityPixelmon releasedPokemon;
	public TrainerInfo info;
	BattleParticipant opponent;

	public EntityTrainer(World par1World) {
		super(par1World, NPCType.Trainer);
		dataWatcher.addObject(25, (int) 0);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new AITrainerInBattle(this));
		tasks.addTask(2, new EntityAIWander(this, SharedMonsterAttributes.movementSpeed.getDefaultValue()));
	}

	public void init(String name) {
		super.init(name);
		pokemonStorage = new PlayerStorage(this);
		info = DatabaseTrainers.GetTrainerInfo(name);
		if (info == null) {
			if (PixelmonConfig.printErrors)
				System.out.println("Database entry error/missing for trainer " + name);
			setDead();
			return;
		}
		dataWatcher.updateObject(25, info.level);
		if (dataWatcher.getWatchableObjectString(4).equals(""))
			dataWatcher.updateObject(4, info.model);
		dataWatcher.updateObject(26, info.name);
	}

	@Override
	protected boolean canDespawn() {
		return true;
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
		for (EnumPokemon pokemon : info.partypokemon) {
			EntityPixelmon p = (EntityPixelmon) PixelmonEntityList.createEntityByName(pokemon.name, worldObj);
			if (p != null) {
				p.getLvl().setLevel((new Random()).nextInt(3) - 1 + info.level);
				p.setHealth(p.stats.HP);
				p.setTrainer(this);
				pokemonStorage.addToParty(p);
			}
		}
	}

	public void startBattle(EntityPlayer player) {
		ChatHandler.sendBattleMessage(player, info.greeting);
	}

	public void loseBattle(Entity entityLiving) {
		if (entityLiving instanceof EntityPlayer) {

			ChatHandler.sendBattleMessage(entityLiving, info.loseMessage);
			PixelmonEventHandler.fireEvent(EventType.BeatTrainer, (EntityPlayer) entityLiving);
			this.despawnEntity();
			if (info.model.toString().equals("Fisherman") || info.model.toString().equals("Fisherman2") || info.model.toString().equals("Fisherman3")) {
				int number = RandomHelper.getRandomNumberBetween(1, 100);
				int number2 = RandomHelper.getRandomNumberBetween(1, 1000);
				if (number == 43) {
					((EntityPlayer) entityLiving).inventory.addItemStackToInventory(new ItemStack(PixelmonItems.goodRod));
				} else if (number2 == 564) {
					((EntityPlayer) entityLiving).inventory.addItemStackToInventory(new ItemStack(PixelmonItems.superRod));
				}
			}

		}
	}

	public void winBattle(Entity entity) {
		ChatHandler.sendBattleMessage(entity, info.winMessage);
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

	@Override
	public boolean interactWithNPC(EntityPlayer player) {
		return false;
	}

}
