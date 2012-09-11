package pixelmon.entities.pixelmon;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import pixelmon.*;
import pixelmon.battles.*;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.attacks.statusEffects.StatusEffectBase;
import pixelmon.battles.participants.IBattleParticipant;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.battles.participants.TrainerParticipant;
import pixelmon.battles.participants.WildPixelmonParticipant;
import pixelmon.comm.ChatHandler;
import pixelmon.config.PixelmonEntityList;
import pixelmon.config.PixelmonItems;
import pixelmon.database.*;
import pixelmon.entities.pixelmon.helpers.RidingHelper;
import pixelmon.entities.pixelmon.stats.LevelHelper;
import pixelmon.entities.trainers.EntityTrainer;
import pixelmon.enums.EnumGui;
import pixelmon.enums.EnumType;
import pixelmon.items.ItemEvolutionStone;
import pixelmon.items.ItemEther;
import pixelmon.items.ItemHeld;
import pixelmon.items.ItemPotion;
import pixelmon.items.ItemStatusAilmentHealer;
import pixelmon.items.PixelmonItem;
import pixelmon.storage.PixelmonStorage;

import net.minecraft.client.Minecraft;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAIOwnerHurtByTarget;
import net.minecraft.src.EntityAIOwnerHurtTarget;
import net.minecraft.src.EntityAITasks;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EntityTameable;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.EnumSkyBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntityPixelmon extends Entity9HasSounds {

	public EntityPixelmon(World par1World) {
		super(par1World);

		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		getNavigator().setAvoidsWater(true);
	}

	public void init(String name) {
		super.init(name);
		moveSpeed = getMoveSpeed();
	}

	public void onDeath(DamageSource damagesource) {
		if (worldObj.isRemote) {
			super.onDeath(damagesource);
			if (getOwner() != null && PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).isIn(this)) {
				String s = "Your " + getName() + " fainted!";
				ChatHandler.sendChat(getOwner(), s);
				isFainted = true;
				setEntityHealth(0);
				catchInPokeball();
			} else {
				super.onDeath(damagesource);
				this.setDead();
			}
		}
	}

	public boolean interact(EntityPlayer entity) {
		if (entity instanceof EntityPlayerMP) {
			ItemStack itemstack = ((EntityPlayer) entity).getCurrentEquippedItem();
			// if (itemstack == null) {
			// if (baseStats.IsRideable &&
			// PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)
			// entity).isIn(this)) {
			// entity.mountEntity(this);
			// return true;
			// }
			// return false;
			// }
			if (itemstack != null) {
				if (itemstack.itemID == PixelmonItems.pokeChecker.shiftedIndex && getOwner() != null) {
					if (getOwner() == entity)
						((EntityPlayer) getOwner()).openGui(Pixelmon.instance, EnumGui.PokeChecker.getIndex(), getOwner().worldObj, getPokemonId(), 0, 0); // Pokechecker
					return true;
				}

				if (getOwner() == entity) {
					if (itemstack.itemID == PixelmonItems.rareCandy.shiftedIndex) {
						getLvl().awardEXP(getLvl().getExpToNextLevel() - getLvl().getExp());
						if (!entity.capabilities.isCreativeMode)
							entity.inventory.consumeInventoryItem(itemstack.itemID);
						return true;
					}
					if (itemstack.getItem() instanceof ItemPotion) {
						if (getHealth() < stats.HP){
							((ItemPotion)itemstack.getItem()).healPokemon(this);
							if (!entity.capabilities.isCreativeMode)
								entity.inventory.consumeInventoryItem(itemstack.itemID);
							return true;
						}
					}
					if (itemstack.getItem() instanceof ItemStatusAilmentHealer) {
						if (((ItemStatusAilmentHealer)itemstack.getItem()).healPokemon(this)){
							if (!entity.capabilities.isCreativeMode)
								entity.inventory.consumeInventoryItem(itemstack.itemID);
							return true;
						}
					}
					if (itemstack.getItem() instanceof ItemEther) {
						boolean canUseEther = false;
						for(Attack a: this.moveset) {
							if (a.pp < a.ppBase) {
								canUseEther = true;
								break;
							}
						}
						if (canUseEther){
							ItemEther ether = (ItemEther) itemstack.getItem();
							if (ether.type.restoresAllMoves()){
								ether.restoreAllMoves(this);
								if (!entity.capabilities.isCreativeMode)
									entity.inventory.consumeInventoryItem(itemstack.itemID);
								return true;
							} else {
								
							}
						}
					}
					if (itemstack.getItem() instanceof ItemEvolutionStone) {
						ItemEvolutionStone i = (ItemEvolutionStone) itemstack.getItem();
						return i.useOnEntity(itemstack, this, entity);
					}
					if (itemstack.getItem() instanceof ItemHeld) {
						if (getHeldItem() != null) {
							if (!worldObj.isRemote) {
								entityDropItem(heldItem.copy(), 1f);
							}
							setHeldItem(null);
						}
						ItemStack itemstack1 = itemstack.copy();
						itemstack1.stackSize = 1;
						entity.inventory.consumeInventoryItem(itemstack.itemID);
						this.setHeldItem(itemstack1);
						PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).updateNBT(this);
						return true;
					}
				}
				
			}
			// if (itemstack.itemID == mod_Pixelmon.pokeDex.shiftedIndex) {
			// if (getOwner() == entity) {
			// getOwner().openGui(mod_Pixelmon.instance,
			// EnumGui.Pokedex.getIndex(), getOwner().worldObj, getPokemonId(),
			// 0, 0); // Pokedex
			// flag = true;
			// }
			// }
		}

		return false;
	}

	public void catchInPokeball() {
		PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).updateNBT(this);
		isInBall = true;
		unloadEntity();
	}

	public void releaseFromPokeball() {
		aggression = Aggression.passive;
		worldObj.spawnEntityInWorld(this);
		isInBall = false;
	}

	public void clearAttackTarget() {
		setTarget(null);
		setAttackTarget(null);
	}

	// public void renderLevelUpEffects() {
	// EntityCrit2FX entitycrit2fx = new EntityCrit2FX(worldObj, this,
	// "magicCrit");
	// Minecraft.getMinecraft().effectRenderer.addEffect(entitycrit2fx);
	// }

	public boolean getCanSpawnHere() {
		if (baseStats.creatureType == EnumCreatureType.waterCreature) {
			int wdepth = WorldHelper.getWaterDepth((int) posX, (int) posY, (int) posZ, worldObj);
			if (wdepth > baseStats.swimmingParameters.depthRangeStart && wdepth < baseStats.swimmingParameters.depthRangeEnd)
				return true;
			else {
				double y = posY - (baseStats.swimmingParameters.depthRangeStart + rand.nextInt(baseStats.swimmingParameters.depthRangeEnd - baseStats.swimmingParameters.depthRangeStart));
				wdepth = WorldHelper.getWaterDepth((int) posX, (int) y, (int) posZ, worldObj);
				if (wdepth > baseStats.swimmingParameters.depthRangeStart && wdepth < baseStats.swimmingParameters.depthRangeEnd)
					return false;
				else {
					posY = y;
					return true;
				}
			}

		}

		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);

		int blockId = this.worldObj.getBlockId(var1, var2 - 1, var3);
		int lightLevel = this.worldObj.getFullBlockLightValue(var1, var2, var3);
		boolean[] conds = { true, lightLevel > 8 };
		for (SpawnConditions s : baseStats.spawnConditions) {
			if (s == SpawnConditions.Grass && blockId != Block.grass.blockID)
				conds[s.index] = false;
			if (s == SpawnConditions.Rock && blockId != Block.stone.blockID)
				conds[s.index] = false;
			if (s == SpawnConditions.Sand && blockId != Block.sand.blockID)
				conds[s.index] = false;
			if (s == SpawnConditions.Darkness && lightLevel > 8)
				conds[s.index] = false;
			if (s == SpawnConditions.DayLight && lightLevel < 8)
				conds[s.index] = false;
		}
		return conds[0] && conds[1];
	}

	@Override
	public void onUpdate() {
		if (getOwner() == null && baseStats != null && baseStats.spawnConditions != null && baseStats.spawnConditions.length > 0) {
			if (baseStats.spawnConditions[0] == SpawnConditions.Darkness && worldObj.isDaytime())
				setDead();
			if (baseStats.spawnConditions[0] == SpawnConditions.DayLight && !worldObj.isDaytime())
				setDead();
		}
		super.onUpdate();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		if (trainer != null && !isStorage)
			return;
		super.writeEntityToNBT(nbt);
		if (getOwner() != null)
			nbt.setString("pixelmonOwner", getOwnerName());
	}

	boolean isStorage = false;

	public void writeEntityToStorageNBT(NBTTagCompound var1) {
		isStorage = true;
		writeEntityToNBT(var1);
		isStorage = false;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		if (nbt.hasKey("pixelmonOwner"))
			super.setOwner("pixelmonOwner");
		int h = health;
		level.readFromNBT(nbt);
		setEntityHealth(h);
	}

	public void unloadEntity() {
		ArrayList<Entity> list = new ArrayList<Entity>();
		list.add(this);
		worldObj.unloadEntities(list);
		clearAttackTarget();
		if (battleController != null) {
			battleController = null;
		}
	}

	public void setTrainer(EntityTrainer entityTrainer) {
		trainer = entityTrainer;
	}
}
