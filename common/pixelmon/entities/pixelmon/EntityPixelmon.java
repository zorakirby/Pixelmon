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
import pixelmon.entities.EntityTrainer;
import pixelmon.entities.pixelmon.helpers.LevelHelper;
import pixelmon.entities.pixelmon.helpers.RidingHelper;
import pixelmon.enums.EnumGui;
import pixelmon.enums.EnumType;
import pixelmon.items.ItemEvolutionStone;
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
import net.minecraft.src.EnumSkyBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public abstract class EntityPixelmon extends Entity8HoldsItems {

	public EntityPixelmon(World par1World) {
		super(par1World);

		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		getNavigator().setAvoidsWater(true);
	}

	protected void init(String name) {
		super.init(name);
		moveSpeed = getMoveSpeed();
		health = 11;
	}

	public void onDeath(DamageSource damagesource) {
		if (worldObj.isRemote) {
			super.onDeath(damagesource);
			if (getOwner() != null && PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).isIn(this)) {
				String s = "Your " + getName() + " fainted!";
				ChatHandler.sendChat(getOwner(), s);
				isFainted = true;
				setHealth(0);
				catchInPokeball();
			} else {
				super.onDeath(damagesource);
				this.setDead();
			}
		}
	}

	// Getters and Setters
	public float getMoveSpeed() {
		return 0.3f;
	}

	public int getMaxHealth() {
		if (stats == null)
			return 1;
		return stats.HP;
	}

	public void setHealth(int i) {
		health = i;
	}

	public boolean isAIEnabled() {
		return true;
	}

	public boolean interact(EntityPlayer entity) {
		if (entity instanceof EntityPlayerMP) {
			EntityPlayer entity1 = (EntityPlayer) entity;
			ItemStack itemstack = entity1.getCurrentEquippedItem();
			boolean flag = false;
			// if (itemstack == null) {
			// if (stats.BaseStats.IsRideable &&
			// PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)entity).isIn(this))
			// {
			// entity.mountEntity((EntityLiving) pixelmon);
			// return true;
			// }
			// return false;
			// }

			if (itemstack != null && itemstack.itemID == PixelmonItems.rareCandy.shiftedIndex && getOwner() == entity) {
				getLvl().awardEXP(getLvl().getExpToNextLevel() - getLvl().getExp());
				if (!entity.capabilities.isCreativeMode)
					itemstack.stackSize--;
				flag = true;
			}
			if (itemstack != null && itemstack.itemID == PixelmonItems.pokeChecker.shiftedIndex && getOwner() != null) {
				if (getOwner() != null)
					getOwner().openGui(Pixelmon.instance, EnumGui.PokeChecker.getIndex(), getOwner().worldObj, getPokemonId(), 0, 0); // Pokechecker
				flag = true;
			}
			if (itemstack != null && itemstack.itemID == PixelmonItems.potion.shiftedIndex && getOwner() == entity) {
				if (getHealth() + 20 > stats.HP)
					setHealth(stats.HP);
				else
					setHealth(getHealth() + 20);
				if (getOwner() != null)
					PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).updateNBT(this);
				getLvl().updateEntityString();
				if (!entity.capabilities.isCreativeMode)
					itemstack.stackSize--;
				if (getHealth() > stats.HP)
					setHealth(stats.HP);
				flag = true;
			}
			// if (itemstack.itemID == mod_Pixelmon.pokeDex.shiftedIndex) {
			// if (getOwner() == entity) {
			// getOwner().openGui(mod_Pixelmon.instance,
			// EnumGui.Pokedex.getIndex(), getOwner().worldObj, getPokemonId(),
			// 0, 0); // Pokedex
			// flag = true;
			// }
			// }
			if (itemstack != null && itemstack.getItem() instanceof ItemEvolutionStone && getOwner() == entity) {
				ItemEvolutionStone i = (ItemEvolutionStone) itemstack.getItem();
				for (EvolutionInfo e : DatabaseStats.getEvolveList(getName())) {
					if (e.evolutionStone == i.getType()) {
						String evolveTo = e.pokemonName;
						if (evolveTo == null) {
							System.out.println(e.pokemonName + " isn't coded yet");
							break;
						}
						evolve(evolveTo);
						flag = true;
						itemstack.stackSize--;
						break;
					}
				}
			}
			if (!flag && getOwner() == entity) {
				if (getHeldItem() != null) {
					if (!worldObj.isRemote) {
						entityDropItem(heldItem.copy(), 1f);
					}
					setHeldItem(null);
				}
				if (itemstack != null) {
					ItemStack itemstack1 = itemstack.copy();
					itemstack1.stackSize = 1;
					itemstack.stackSize--;
					this.setHeldItem(itemstack1);
				}
				if (entity instanceof EntityPlayerMP) {
					PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) getOwner()).updateNBT(this);
				}
				flag = true;
			}
			if (itemstack != null && itemstack.stackSize <= 0)
				entity.inventory.setInventorySlotContents(entity.inventory.currentItem, null);
			return flag;
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

	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		return null;
	}

	public void clearAttackTarget() {
		setTarget(null);
		setAttackTarget(null);
	}

	public void setAttackTarget(EntityLiving e) {
		super.setAttackTarget(e);
		super.setTarget(e);
		if (e instanceof EntityPixelmon) {
			EntityPixelmon e1 = (EntityPixelmon) e;
			if (e1.getAttackTarget() == null)
				e1.setAttackTarget(this);
		}
	}

	public boolean hasOwner() {
		return !MathHelper.stringNullOrLengthZero(getOwnerName());
	}

	public World getWorldObj() {
		return worldObj;
	}

	// public void renderLevelUpEffects() {
	// EntityCrit2FX entitycrit2fx = new EntityCrit2FX(worldObj, this,
	// "magicCrit");
	// Minecraft.getMinecraft().effectRenderer.addEffect(entitycrit2fx);
	// }



	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID && this.worldObj.getFullBlockLightValue(var1, var2, var3) > 8 && super.getCanSpawnHere();
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
	}

	public void setMotion(int i, int j, int k) {
		motionX = i;
		motionY = j;
		motionZ = k;
	}

	public void unloadEntity() {
		ArrayList<Entity> list = new ArrayList<Entity>();
		list.add(this);
		worldObj.unloadEntities(list);
		clearAttackTarget();
		if (bc != null) {
			helper.bc = null;
		}
	}

	public void setPokemonId(int id) {
		dataWatcher.updateObject(19, id);
	}

	public boolean getIsShiny() {
		return dataWatcher.getWatchableObjectShort(20) == 1;
	}

	public void setIsShiny(boolean isShiny) {
		if (isShiny)
			dataWatcher.updateObject(20, (short) 1);
		else
			dataWatcher.updateObject(20, (short) 0);
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound() {
		return ("pixelmon." + name.toLowerCase());
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	// @Override
	// protected String getHurtSound()
	// {
	// return "mob.cowhurt";
	// }
	//
	// /**
	// * Returns the sound this mob makes on death.
	// */
	// @Override
	// protected String getDeathSound()
	// {
	// return "mob.cowhurt";
	// }

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	public void setTrainer(EntityTrainer entityTrainer) {
		// TODO Auto-generated method stub

	}
}
