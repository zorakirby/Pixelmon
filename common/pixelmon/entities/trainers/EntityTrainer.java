package pixelmon.entities.trainers;

import java.util.Random;

import pixelmon.Pixelmon;
import pixelmon.comm.ChatHandler;
import pixelmon.config.PixelmonEntityList;
import pixelmon.database.DatabaseTrainers;
import pixelmon.database.TrainerInfo;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PlayerStorage;
import pixelmon.storage.PokeballManager;
import pixelmon.storage.PokeballManager.PokeballManagerMode;

import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntityTrainer extends EntityCreature {

	public PlayerStorage pokemonStorage;
	public EntityPixelmon releasedPokemon;
	public String name;
	public TrainerInfo info;
	public ModelBase model;

	public EntityTrainer(World par1World) {
		super(par1World);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIWander(this, moveSpeed));
	}

	public void init(String name) {
		this.name = name;
		pokemonStorage = new PlayerStorage(this);
		info = DatabaseTrainers.GetTrainerInfo(name);
		model = Pixelmon.proxy.getTrainerModel(info.model);
		health=100;
	}
	
	@Override
	public int getMaxHealth() {
		return 100;
	}

	public void releasePokemon() {
		if (pokemonStorage.count() == 0)
			loadPokemon();
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
				p.setTrainer(this);
				pokemonStorage.addToParty(p);
			}
		}
	}

	public void startBattle(EntityPlayer player) {
		ChatHandler.sendChat(player, info.greeting);
	}

	public void loseBattle(EntityLiving entityLiving) {
		ChatHandler.sendChat(entityLiving, info.loseMessage);
	}

	public void winBattle(EntityLiving entityLiving) {
		ChatHandler.sendChat(entityLiving, info.winMessage);
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
}
