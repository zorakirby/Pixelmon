package pixelmon.entities.pokeballs;

import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import pixelmon.comm.ChatHandler;
import pixelmon.entities.EntityTrainer;
import pixelmon.entities.pixelmon.helpers.IHaveHelper;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumPokeballs;
import pixelmon.storage.PokeballManager;
import net.minecraft.src.EntityCrit2FX;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityReddustFX;
import net.minecraft.src.EntityThrowable;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.Vec3D;
import net.minecraft.src.World;
import net.minecraft.src.mod_Pixelmon;

public class EntityPokeBall extends EntityThrowable {
	public EnumPokeballs type;
	public int shakePokeball;
	private EntityLiving thrower;
	private PixelmonEntityHelper p;
	private boolean isWaiting;
	private int waitTime;
	private boolean canCatch = false;
	private PixelmonEntityHelper pixelmon;
	private boolean isEmpty;

	public EntityPokeBall(World world) {
		super(world);
		type = EnumPokeballs.PokeBall;
	}

	public EntityPokeBall(World world, EntityLiving entityliving, EnumPokeballs type) {
		super(world, entityliving);
		thrower = entityliving;
		this.type = type;
		isEmpty = true;
	}

	public EntityPokeBall(World world, EntityLiving entityliving, PixelmonEntityHelper e, EnumPokeballs type) {
		super(world, entityliving);
		pixelmon = e;
		this.type = type;
		isEmpty = false;
		float speed = 0.3f;
		this.setLocationAndAngles(entityliving.posX, entityliving.posY + (double) entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
		this.posX -= (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.posY -= 0.10000000149011612D;
		this.posZ -= (double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = (double) (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI)) / 2;
		this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI)) / 2;
		this.motionY = (double) (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI)) / 2;
	}

	public void init() {
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (!worldObj.isRemote) {
			if (!isEmpty) {
				if (movingobjectposition != null && !worldObj.isRemote) {
					if (pixelmon != null) {
						if (worldObj.getBlockId((int) posX, (int) posY, (int) posZ) > 0)
							pixelmon.setLocationAndAngles(prevPosX, prevPosY, prevPosZ, rotationYaw, 0.0F);
						else
							pixelmon.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
						pixelmon.setMotion(0, 0, 0);
						pixelmon.releaseFromPokeball();
						if (movingobjectposition.entityHit != null && (movingobjectposition.entityHit instanceof IHaveHelper)
								&& !mod_Pixelmon.pokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).isIn(((IHaveHelper) movingobjectposition.entityHit).getHelper()))
							pixelmon.StartBattle(((IHaveHelper) movingobjectposition.entityHit).getHelper());
						if (movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityTrainer)
							pixelmon.StartBattle((EntityTrainer) movingobjectposition.entityHit, (EntityPlayer) thrower);
						else
							pixelmon.clearAttackTarget();
						if (thrower instanceof EntityPlayer) {

						}
						spawnCaptureParticles();
					}
					setDead();
				}
			} else {
				if (movingobjectposition != null) {
					if (movingobjectposition.entityHit != null && (movingobjectposition.entityHit instanceof IHaveHelper)) {
						IHaveHelper entitypixelmon = (IHaveHelper) movingobjectposition.entityHit;
						p = entitypixelmon.getHelper();
						if (p.getOwner() == (EntityPlayer) thrower) {
							ChatHandler.sendChat((EntityPlayer) thrower, "You can't catch other people's Pokemon!");
							spawnFailParticles();
							return;
						}
						doCaptureCalc(p);
						isWaiting = true;
						motionX = motionZ = 0;
						motionY = -0.1;
					}

					else {
						if (!isWaiting) {
							entityDropItem(new ItemStack(type.getItem()), 0.0F);
							setDead();
						}
					}
				}
			}
		}
	}

	int numRocks = 0;
	boolean isUnloaded = false;

	Vec3D initPos;
	Vec3D diff;
	float initialScale;

	@Override
	public void onEntityUpdate() {
		if (!onGround) {
			rotationYaw += 0.01;
		}
		if (worldObj.isRemote) {
			motionX = motionY = motionZ = 0;
		}
		if (isWaiting) {
			if (waitTime == 0 && !isUnloaded) {
				initialScale = p.scale;
				initPos = p.getPosition();
				diff = Vec3D.createVector(posX, posY, posZ).subtract(initPos);
				p.scale = initialScale / 1.1f;
			}
			if (waitTime == 1 && !isUnloaded) {
				p.scale = initialScale / 1.3f;
				moveCloser();
			}
			if (waitTime == 2 && !isUnloaded) {
				p.scale = initialScale / 1.7f;
				moveCloser();
			}
			if (waitTime == 3 && !isUnloaded) {
				p.scale = initialScale / 2.2f;
				moveCloser();
			}
			if (waitTime == 4 && !isUnloaded) {
				p.scale = initialScale / 3;
				moveCloser();
			}
			if (waitTime == 4 && !isUnloaded) {
				p.unloadEntity();
				isUnloaded = true;
				waitTime = 0;
			}
			if (!thrower.worldObj.isAirBlock((int) this.posX, (int) Math.ceil(this.posY) - 2, (int) this.posZ) && this.posY % 1 <= this.height) {
				this.motionY = 0;
				this.motionX = 0;
				this.motionZ = 0;
			}

			waitTime++;
		}
	}

	int i = 0;

	private void moveCloser() {
		p.setPosition(initPos.addVector(diff.xCoord * i / 4, diff.yCoord * i / 4, diff.zCoord * i / 4));
		i++;
	}

	int initialDelay = 15;
	int wobbleTime = 5;
	public boolean flashRed = false;

	@Override
	public void onUpdate() {
		super.onUpdate();
		rotationPitch = 0;
		if (isWaiting)
			rotationYaw = 0;

		if (waitTime >= initialDelay && waitTime < initialDelay + wobbleTime) {
			flashRed = true;
			p.scale = initialScale;
			if (numShakes == 0)
				catchPokemon();
			this.rotationPitch = ((float) (waitTime - initialDelay)) / wobbleTime * (float) 35;
		} else if (waitTime >= initialDelay + wobbleTime && waitTime < initialDelay + 3 * wobbleTime) {
			flashRed = false;
			this.rotationPitch = -1 * ((float) (waitTime - (initialDelay + wobbleTime))) / wobbleTime * (float) 35 + 35;
		} else if (waitTime >= initialDelay + 3 * wobbleTime && waitTime < initialDelay + 4 * wobbleTime) {
			this.rotationPitch = ((float) (waitTime - (initialDelay + 3 * wobbleTime))) / wobbleTime * (float) 35 - 35;
		} else if (waitTime == initialDelay + 4 * wobbleTime) {
			waitTime = 0;
			shakeCount++;
			if (shakeCount == numShakes - 1 || numShakes == 1) {
				catchPokemon();
			}
		}
	}

	int numShakes = 0;
	int shakeCount = 0;

	private void doCatchCheck() {

	}

	private void catchPokemon() {
		if (canCatch) {
			p.setTamed(true);
			p.setOwner((EntityPlayer) thrower);
			mod_Pixelmon.pokeballManager.getPlayerStorage((EntityPlayer) thrower).addToParty(p);
			p.clearAttackTarget();
			p.catchInPokeball();
			spawnCaptureParticles();
			waitTime = 0;
			isWaiting = false;
			setDead();
		} else {
			spawnFailParticles();
			waitTime = 0;
			isWaiting = false;
			p.getEntity().setPosition(posX, posY, posZ);
			worldObj.spawnEntityInWorld(p.getEntity());
			p.getEntity().setPosition(posX, posY, posZ);
			p.setIsDead(false);
			setDead();
		}
	}

	private void spawnCaptureParticles() {
		for (int i = mod_Pixelmon.getRandomNumberBetween(5, 7); i > 0; i--) {
			EntityCrit2FX entitycrit2fx = new EntityCrit2FX(worldObj, this, "crit");
			ModLoader.getMinecraftInstance().effectRenderer.addEffect(entitycrit2fx);
		}
	}

	private void spawnFailParticles() {

		for (int i = 0; i < 30; i++) {
			EntityReddustFX entityred = new EntityReddustFX(worldObj, posX, posY, posZ, 1, 0, 0);
			entityred.setVelocity(worldObj.rand.nextFloat() / 5, worldObj.rand.nextFloat() / 5, worldObj.rand.nextFloat() / 5);
			ModLoader.getMinecraftInstance().effectRenderer.addEffect(entityred);
		}
	}

	private int b;

	protected void doCaptureCalc(PixelmonEntityHelper entitypixelmon) {
		int pokemonRate = entitypixelmon.stats.BaseStats.CatchRate;
		int hpMax = entitypixelmon.getMaxHealth();
		int hpCurrent = entitypixelmon.getHealth();
		int bonusStatus = 1;
		double a, b, p;
		a = (((3 * hpMax - 2 * hpCurrent) * pokemonRate * type.getBallBonus()) / (3 * hpMax)) * bonusStatus;
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
}
