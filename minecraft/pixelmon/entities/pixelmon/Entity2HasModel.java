package pixelmon.entities.pixelmon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import pixelmon.Pixelmon;
import pixelmon.client.models.objHandling.ModelObj;
import pixelmon.client.models.objHandling.Object3D;
import pixelmon.entities.pixelmon.Entity7HasAI.Aggression;
import pixelmon.enums.EnumPokemon;

public abstract class Entity2HasModel extends Entity1Base {

	public ModelBase model;
	public ModelObj objModel;
	public float animationNum1 = 0f;

	public int animationCounter = 0; //counter increases every "Entity-update" tick.
	public int animationIncrement = 2;
	public int animationLimit = 360;
	public int animationCounter2 = 0; //counter increases every single tick.
	public int animationIncrement2 = 1;

	public Entity2HasModel(World par1World) {
		super(par1World);
	}

	protected void init(String name) {
		super.init(name);
		oldName = name;
	}

	public void evolve(String evolveTo) {
		if (!EnumPokemon.hasPokemon(evolveTo))
			return;
		setName(evolveTo);
	}

	public void loadModel() {
		if (Pixelmon.proxy.getModels().length == 0)
			return;
		int n = ((Entity3HasStats) this).baseStats.nationalPokedexNumber;
		if (Pixelmon.proxy.getModels()[n] != null) {
			Object mod = Pixelmon.proxy.getModels()[n];
			if (mod instanceof ModelBase)
				model = (ModelBase) mod;
			else if (mod instanceof ModelObj)
				objModel = (ModelObj) mod;
		} else {
			File newFile = new File(Minecraft.getMinecraftDir() + "/resources/pixelmon/models/" + getName().toLowerCase());
			if (!newFile.exists() || !newFile.isDirectory()) {
				ModelBase m = Pixelmon.proxy.loadModel(getName());
				Pixelmon.proxy.getModels()[n] = m;
				model = m;
			} else {
				Object3D[] objects = new Object3D[newFile.listFiles().length];
				int i = 0;
				for (File f : newFile.listFiles()) {
					try {
						Object3D obj = new Object3D(new BufferedReader(new FileReader(f)), false);
						objects[i++] = obj;
					} catch (Exception e) {

					}
				}
				//ModelObj model = new ModelObj(objects);
			}
		}
	}

	String oldName;

	public float getScaleFactor() {
		return getGrowth().scaleValue * getBossMode().scaleFactor;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote && !oldName.equals(getName())) {
			isInitialised = false;
			((Entity3HasStats) this).getBaseStats(getName());
			loadModel();
			oldName = getName();
		}
		if (worldObj.isRemote) {
			animationCounter += animationIncrement;
			if (animationCounter >= animationLimit)
				animationCounter = 0;
/*			animationCounter2 = animationCounter2 + animationIncrement2;
			if (animationCounter2 >= animationLimit2)
				animationCounter2 = 0;*/
		}
	}
	
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("animationCounter2", animationCounter2); //writing to NBT, because otherwise, when the games is loaded again, all entities' animationCounter2 will be synchronized.
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		animationCounter2 = nbt.getInteger("animationCounter2");
	}
	
	/**
	 * Increases animationC0unter2 by 1 or resets it to zero if it reaches over the maximum integer limit. <br>
	 * This is called in <code>PixelmonModelBase.render</code>, so that it increases every single tick, instead of just on "entity-update" ticks
	 */
	public void increaseAnimCounter2(){
		if(!Minecraft.getMinecraft().isGamePaused){
			animationCounter2 += animationIncrement2;
			if(animationCounter2 < 0){
				animationCounter2 = 0;
			}
		}
	}
}
