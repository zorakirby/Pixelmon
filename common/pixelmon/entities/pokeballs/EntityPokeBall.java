package pixelmon.entities.pokeballs;

import java.util.Random;

import pixelmon.Pixelmon;
import pixelmon.RandomHelper;
import pixelmon.battles.BattleController;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.participants.IBattleParticipant;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.battles.participants.TrainerParticipant;
import pixelmon.battles.participants.WildPixelmonParticipant;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.trainers.EntityTrainer;
import pixelmon.enums.EnumPokeballs;
import pixelmon.items.ItemPokeBall;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PokeballManager;
import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EntityThrowable;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;

public class EntityPokeBall extends EntityThrowable {
	public enum Mode {
		empty, full, battle
	};

	private Mode mode;
	public int shakePokeball;
	private EntityLiving thrower;
	private EntityPixelmon p;
	private int waitTime;
	private boolean canCatch = false;
	private EntityPixelmon pixelmon;
	private float endRotationYaw = 0;
	public boolean dropItem;

	private boolean isBattleThrown = false;

	public float openAngle = 0;

	private boolean isInitialized = false;

	public EntityPokeBall(World world) {
		super(world);
		this.mode = null;
		dataWatcher.addObject(10, EnumPokeballs.PokeBall.getIndex());
		dataWatcher.addObject(11, (short) 0);// IsCaptured
		dataWatcher.addObject(12, (short) 0);// IsWaiting
		dataWatcher.addObject(13, (short) 0);// IsOnGround
		dataWatcher.addObject(14, (short) 0);// IsOpen
		isInitialized = true;
	}

	public EntityPokeBall(World world, EntityLiving entityliving, EnumPokeballs type, boolean dropItem) {
		super(world, entityliving);
		thrower = entityliving;
		this.mode = Mode.empty;
		dataWatcher.addObject(10, type.getIndex());
		dataWatcher.addObject(11, (short) 0);// IsCaptured
		dataWatcher.addObject(12, (short) 0);// IsWaiting
		dataWatcher.addObject(13, (short) 0);// IsOnGround
		dataWatcher.addObject(14, (short) 0);// IsOpen
		isInitialized = true;
		this.dropItem = dropItem;
	}

	private void setIsWaiting(boolean value) {
		dataWatcher.updateObject(12, value ? (short) 1 : (short) 0);
	}

	private boolean getIsWaiting() {
		if (!isInitialized)
			return false;
		return dataWatcher.getWatchableObjectShort(12) == (short) 1;
	}

	private void setIsOnGround(boolean value) {
		dataWatcher.updateObject(13, value ? (short) 1 : (short) 0);
	}

	private boolean getIsOnGround() {
		if (!isInitialized)
			return false;
		return dataWatcher.getWatchableObjectShort(13) == (short) 1;
	}

	private void setIsCaptured(boolean value) {
		dataWatcher.updateObject(11, value ? (short) 1 : (short) 0);
	}

	public boolean getIsCaptured() {
		if (!isInitialized)
			return false;
		return dataWatcher.getWatchableObjectShort(11) == (short) 1;
	}

	private void setIsOpen(boolean value) {
		dataWatcher.updateObject(14, value ? (short) 1 : (short) 0);
	}

	public boolean getIsOpen() {
		if (!isInitialized)
			return false;
		return dataWatcher.getWatchableObjectShort(14) == (short) 1;
	}

	private BattleController battleController;

	public EntityPokeBall(World world, EntityLiving thrower, EntityPixelmon target, EnumPokeballs type, BattleController battleController) {
		super(world, thrower);
		this.thrower = thrower;
		endRotationYaw = thrower.rotationYawHead;
		pixelmon = target;
		dataWatcher.addObject(10, type.getIndex());
		dataWatcher.addObject(11, (short) 0);// IsCaptured
		dataWatcher.addObject(12, (short) 0);// IsWaiting
		dataWatcher.addObject(13, (short) 0);// IsOnGround
		dataWatcher.addObject(14, (short) 0);// IsOpen
		mode = Mode.battle;
		isBattleThrown = true;
		this.battleController = battleController;
		worldObj = thrower.worldObj;
		battleController.waitForCapture();
		this.setLocationAndAngles(thrower.posX, thrower.posY + (double) thrower.getEyeHeight(), thrower.posZ, thrower.rotationYaw, thrower.rotationPitch);
		this.posX -= (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.posY -= 0.10000000149011612D;
		this.posZ -= (double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		isInitialized = true;
		Vec3 posVec = Vec3.createVectorHelper(posX, posY, posZ);
		posVec.subtract(Vec3.createVectorHelper(pixelmon.posX, pixelmon.posY, pixelmon.posZ));
		this.motionX = (double) (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI)) * 0.8;
		this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI)) * 0.8;
		this.motionY = (double) (-MathHelper.sin(0)) * 0.8;
	}

	public EntityPokeBall(World world, EntityLiving entityliving, EntityPixelmon e, EnumPokeballs type) {
		super(world, entityliving);
		thrower = entityliving;
		endRotationYaw = entityliving.rotationYawHead;
		pixelmon = e;
		dataWatcher.addObject(10, type.getIndex());
		mode = Mode.full;
		float speed = 0.3f;
		this.setLocationAndAngles(entityliving.posX, entityliving.posY + (double) entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw,
				entityliving.rotationPitch);
		this.posX -= (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.posY -= 0.10000000149011612D;
		this.posZ -= (double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = (double) (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI)) * 0.8;
		this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI)) * 0.8;
		this.motionY = (double) (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI)) * 0.8;
	}

	public void init() {
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {

		if (isBattleThrown && !worldObj.isRemote) {
			p = pixelmon;
			p.hitByPokeball = true;
			doCaptureCalc(p);
			setIsWaiting(true);
			motionX = motionZ = 0;
			motionY = 0;
//			}
		} else if (mode == Mode.full) {
			if (movingobjectposition != null && !worldObj.isRemote) {
				if (pixelmon != null) {
					if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE) {
						if (movingobjectposition.sideHit == 0)// Bottom
							pixelmon.setLocationAndAngles(movingobjectposition.blockX + 0.5, movingobjectposition.blockY - 1,
									movingobjectposition.blockZ + 0.5, rotationYaw, 0.0F);
						if (movingobjectposition.sideHit == 1)// Top
							pixelmon.setLocationAndAngles(movingobjectposition.blockX + 0.5, movingobjectposition.blockY + 1,
									movingobjectposition.blockZ + 0.5, rotationYaw, 0.0F);
						if (movingobjectposition.sideHit == 2)// East
							pixelmon.setLocationAndAngles(movingobjectposition.blockX + 0.5, movingobjectposition.blockY,
									movingobjectposition.blockZ + 0.5 - 1, rotationYaw, 0.0F);
						if (movingobjectposition.sideHit == 3)// West
							pixelmon.setLocationAndAngles(movingobjectposition.blockX + 0.5, movingobjectposition.blockY,
									movingobjectposition.blockZ + 0.5 + 1, rotationYaw, 0.0F);
						if (movingobjectposition.sideHit == 4)// North
							pixelmon.setLocationAndAngles(movingobjectposition.blockX + 0.5 - 1, movingobjectposition.blockY,
									movingobjectposition.blockZ + 0.5, rotationYaw, 0.0F);
						if (movingobjectposition.sideHit == 5)// South
							pixelmon.setLocationAndAngles(movingobjectposition.blockX + 0.5 + 1, movingobjectposition.blockY,
									movingobjectposition.blockZ + 0.5, rotationYaw, 0.0F);
					} else {
						pixelmon.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
					}
					pixelmon.motionX = pixelmon.motionY = pixelmon.motionZ = 0;
					pixelmon.releaseFromPokeball();
					if (movingobjectposition.entityHit != null
							&& (movingobjectposition.entityHit instanceof EntityPixelmon)
							&& !PixelmonStorage.PokeballManager.getPlayerStorage(((EntityPlayerMP) thrower)).isIn(
									(EntityPixelmon) movingobjectposition.entityHit)) {
						IBattleParticipant part;
						if (((EntityPixelmon) movingobjectposition.entityHit).getOwner() != null)
							part = new PlayerParticipant((EntityPlayerMP) ((EntityPixelmon) movingobjectposition.entityHit).getOwner(),
									(EntityPixelmon) movingobjectposition.entityHit);
						else
							part = new WildPixelmonParticipant((EntityPixelmon) movingobjectposition.entityHit);

						pixelmon.StartBattle(new PlayerParticipant((EntityPlayerMP) thrower, pixelmon), part);

					} else if (movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityTrainer) {
						TrainerParticipant trainer = new TrainerParticipant((EntityTrainer) movingobjectposition.entityHit, (EntityPlayer) thrower);
						pixelmon.StartBattle(new PlayerParticipant((EntityPlayerMP) thrower, pixelmon), trainer);
					} else
						pixelmon.clearAttackTarget();
					if (thrower instanceof EntityPlayer) {

					}
					// spawnCaptureParticles();
				}
				setDead();
			}
		} else {
			if (movingobjectposition != null) {
				if (movingobjectposition.entityHit != null && (movingobjectposition.entityHit instanceof EntityPixelmon)) {
					EntityPixelmon entitypixelmon = (EntityPixelmon) movingobjectposition.entityHit;
					p = entitypixelmon;

					if (p.battleController != null) {
						ChatHandler.sendChat((EntityPlayer) thrower, "You can't catch pokemon who are battling in another battle!");
						if (dropItem) {
							entityDropItem(new ItemStack(getType().getItem()), 0.0F);
						}
						setDead();
						return;

					}

					if (p.getOwner() != null || p.getTrainer() != null) {
						if (p.getOwner() == thrower)
							ChatHandler.sendChat((EntityPlayer) thrower, "You can't catch Pokemon you already own!");
						else
							ChatHandler.sendChat((EntityPlayer) thrower, "You can't catch other people's Pokemon!");
						if (dropItem) {
							entityDropItem(new ItemStack(getType().getItem()), 0.0F);
						}
						setDead();
						return;
					}

					if (p.hitByPokeball) {
						if (dropItem) {
							entityDropItem(new ItemStack(getType().getItem()), 0.0F);
						}
						setDead();
						return;
					}
					p.hitByPokeball = true;
					doCaptureCalc(p);
					setIsWaiting(true);
					motionX = motionZ = 0;
					motionY = -0.1;
				} else {
					Material mat = worldObj.getBlockMaterial(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ);
					if (!getIsWaiting() && mat != null && mat.isSolid()) {
						if (dropItem) {
							entityDropItem(new ItemStack(getType().getItem()), 0.0F);
						}
						setDead();
					}
				}
			}
		}
	}

	int numRocks = 0;
	boolean isUnloaded = false;

	Vec3 initPos;
	Vec3 diff;
	float initialScale;

	@Override
	public void onEntityUpdate() {
		if (!worldObj.isRemote && getIsWaiting()) {
			if (!isUnloaded) {
				if (waitTime == 0) {
					p.setIsRed(true);
					setIsOpen(true);
					initialScale = p.getScale();
					initPos = Vec3.createVectorHelper(p.posX, p.posY, p.posZ);
					Vec3 current = Vec3.createVectorHelper(posX, posY, posZ);
					current.xCoord -= initPos.xCoord;
					current.yCoord -= initPos.yCoord;
					current.zCoord -= initPos.zCoord;
					diff = current;
					p.setScale(initialScale / 1.1f);
				}
				if (waitTime == 10) {
					p.setScale(initialScale / 1.3f);
					moveCloser();
				}
				if (waitTime == 14) {
					p.setScale(initialScale / 1.7f);
					moveCloser();
				}
				if (waitTime == 18) {
					p.setScale(initialScale / 2.2f);
					moveCloser();
				}
				if (waitTime == 22) {
					p.setScale(initialScale / 3);
					moveCloser();
					p.unloadEntity();
					isUnloaded = true;
					waitTime = 0;
					setIsOpen(false);
					p.setIsRed(false);
				}
			}
			if (!thrower.worldObj.isAirBlock((int) this.posX, (int) Math.ceil(this.posY) - 2, (int) this.posZ) && this.posY % 1 <= this.height) {
				this.motionY = 0;
				this.motionX = 0;
				this.motionZ = 0;
				setIsOnGround(true);
			}

			waitTime++;
		}
	}

	int i = 0;

	private void moveCloser() {
		Vec3 newVec = initPos.addVector(diff.xCoord * i / 4, diff.yCoord * i / 4, diff.zCoord * i / 4);
		p.posX = newVec.xCoord;
		p.posY = newVec.yCoord;
		p.posZ = newVec.zCoord;
		i++;
	}

	int initialDelay = 30;
	int wobbleTime = 5;
	public boolean flashRed = false;
	int flashTime = 10;
	int flashCounter = 0;

	@Override
	public void onUpdate() {
		if (getIsOpen() && openAngle > (float) (-Math.PI / 2)) {
			openAngle -= (float) (Math.PI / 20);
		}
		if (!getIsOpen() && openAngle < 0)
			openAngle += (float) (Math.PI / 20);

		super.onUpdate();
		if (!getIsOnGround()) {
			rotationYaw += 50;
		} else {
			motionY = 0;
		}
		rotationPitch = 0;
		if (getIsOnGround() && worldObj.isRemote) {
			// rotationYaw = endRotationYaw;
			flashCounter++;
			if (flashCounter < 15)
				flashRed = true;
			else
				flashRed = false;
			if (flashCounter == 30)
				flashCounter = -1;
		}
		if (getIsCaptured()) {
			if (waitTime > 20) {
				p.setTamed(true);
				p.setOwner(((EntityPlayer) thrower).username);
				p.caughtBall = getType();
				p.clearAttackTarget();
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) thrower).addToParty(p);
				p.catchInPokeball();
				p.friendship.initFromCapture();
				PokeballTypeHelper.doAfterEffect(getType(), p);
				if (mode== Mode.battle) {
					battleController.endBattleWithoutXP();
				}
				setIsWaiting(false);
				setDead();
			}
		} else {
			if (waitTime >= initialDelay && waitTime < initialDelay + wobbleTime) {
				p.setScale(initialScale);
				if (numShakes == 0)
					catchPokemon();
				this.rotationPitch = ((float) (waitTime - initialDelay)) / wobbleTime * (float) 35;
			} else if (waitTime >= initialDelay + wobbleTime && waitTime < initialDelay + 3 * wobbleTime) {
				this.rotationPitch = -1 * ((float) (waitTime - (initialDelay + wobbleTime))) / wobbleTime * (float) 35 + 35;
			} else if (waitTime >= initialDelay + 3 * wobbleTime && waitTime < initialDelay + 4 * wobbleTime) {
				this.rotationPitch = ((float) (waitTime - (initialDelay + 3 * wobbleTime))) / wobbleTime * (float) 35 - 35;
			} else if (waitTime == initialDelay + 4 * wobbleTime + initialDelay) {
				waitTime = 0;
				shakeCount++;
				if (shakeCount == numShakes - 1 || numShakes == 1) {
					catchPokemon();
				}
			}
		}
	}

	int numShakes = 0;
	int shakeCount = 0;

	private void doCatchCheck() {

	}

	private void catchPokemon() {
		if (canCatch) {
			ChatHandler.sendChat((EntityPlayer) thrower, "You captured " + p.getName());
			
			spawnCaptureParticles();
			setIsCaptured(true);
			waitTime = 0;
		} else {
			openAngle = (float) -Math.PI / 2;
			spawnFailParticles();
			waitTime = 0;
			setIsWaiting(false);
			p.setPosition(posX, posY, posZ);
			p.hitByPokeball = false;
			worldObj.spawnEntityInWorld(p);
			p.setPosition(posX, posY, posZ);
			p.isDead = false;
			if (mode==Mode.battle)
				battleController.endWaitForCapture();
			setDead();
		}
	}

	private void spawnCaptureParticles() {
		// for (int i = RandomHelper.getRandomNumberBetween(5, 7); i > 0; i--) {
		// EntityCrit2FX entitycrit2fx = new EntityCrit2FX(worldObj, this,
		// "crit");
		// Minecraft.getMinecraft().effectRenderer.addEffect(entitycrit2fx);
		// }
	}

	private void spawnFailParticles() {

		// for (int i = 0; i < 30; i++) {
		// EntityReddustFX entityred = new EntityReddustFX(worldObj, posX, posY,
		// posZ, 1, 0, 0);
		// entityred.setVelocity(worldObj.rand.nextFloat() / 5,
		// worldObj.rand.nextFloat() / 5, worldObj.rand.nextFloat() / 5);
		// Minecraft.getMinecraft().effectRenderer.addEffect(entityred);
		// }
	}

	private int b;

	protected void doCaptureCalc(EntityPixelmon p2) {
		int pokemonRate = p2.baseStats.CatchRate;
		int hpMax = p2.getMaxHealth();
		int hpCurrent = p2.getHealth();
		int bonusStatus = 1;
		double ballBonus = PokeballTypeHelper.getBallBonus(getType(), thrower, p2, mode);
		double a, b, p;
		a = (((3 * hpMax - 2 * hpCurrent) * pokemonRate * ballBonus) / (3 * hpMax)) * bonusStatus;
		b = (Math.pow(2, 16) - 1) * Math.sqrt(Math.sqrt((a / (Math.pow(2, 8) - 1))));
		p = Math.pow(((b + 1) / Math.pow(2, 16)), 4);
		p = (p * 10000) / 100;
		b = (int) Math.floor(65536 / Math.pow((255 / p), 1f / 4f));
		int passedShakes = 0;
		for (int i = 0; i < 4; i++) {
			int roll = new Random().nextInt(65536);
			if (roll <= b) {
				passedShakes++;
			}
		}
		if (passedShakes == 4) {
			canCatch = true;
		} else {
			canCatch = false;
		}
		numShakes = passedShakes;
	}

	public EnumPokeballs getType() {
		return EnumPokeballs.getFromIndex(dataWatcher.getWatchableObjectInt(10));
	}
}
