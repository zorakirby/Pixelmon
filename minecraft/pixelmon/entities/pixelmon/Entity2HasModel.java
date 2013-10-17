package pixelmon.entities.pixelmon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.model.ModelBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import pixelmon.Pixelmon;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.client.models.objHandling.ModelObj;
import pixelmon.client.models.objHandling.Object3D;
import pixelmon.entities.pixelmon.Entity7HasAI.Aggression;
import pixelmon.enums.EnumPokemon;

public abstract class Entity2HasModel extends Entity1Base {
	
	public static Random animStarter = new Random();
	
	@SideOnly(Side.CLIENT)
	ModelBase model; //ModelBase Disguised as an Object to prevent server crashing
	
	@SideOnly(Side.CLIENT)
	public float animationNum1;
	
	@SideOnly(Side.CLIENT)
	public int animationCounter; //counter increases every "Entity-update" tick.
	
	@SideOnly(Side.CLIENT)
	public int animationIncrement;
	
	@SideOnly(Side.CLIENT)
	public int animationLimit;
	
	@SideOnly(Side.CLIENT)
	public int animationCounter2;
	@SideOnly(Side.CLIENT)
	public int animationIncrement2;
	//animationLimit2 has been removed, because it is currently only used in SMD animations. If this variable was reset to 0 after reaching 360, the animation would suddenly jump
	@SideOnly(Side.CLIENT)
	public boolean transformed;
	@SideOnly(Side.CLIENT)
	String transformedModel;
	
	String oldName;
	
	public Entity2HasModel(World par1World) {
		super(par1World);
		if(par1World.isRemote)
			clientInit();
	}
	
	@SideOnly(Side.CLIENT)
	public void clientInit(){
		animationNum1 = 0f;
		animationCounter = animStarter.nextInt(359);
		animationIncrement = 2;
		animationLimit = 360;
		animationCounter2 = animStarter.nextInt(360);
		animationIncrement2 = 1;
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

	@SideOnly(Side.CLIENT)
	public ModelBase getModel(){
		if (model ==null) loadModel();
		return (ModelBase)model;
	}

	@SideOnly(Side.CLIENT)
	void loadModel() {
		if (Pixelmon.proxy.getModels().length == 0)
			return;
		int n = ((Entity3HasStats) this).baseStats.nationalPokedexNumber;
		if (Pixelmon.proxy.getModels()[n] != null) {
			Object mod = Pixelmon.proxy.getModels()[n];
			if (mod instanceof ModelBase)
				model = (ModelBase) mod;
		} else {
			ModelBase m = Pixelmon.proxy.loadModel(getName());
			Pixelmon.proxy.getModels()[n] = m;
			model = m;
		}
	}

	
	@SideOnly(Side.CLIENT)
	public void transform(String transformedModel) {
		transformed = true;
		this.transformedModel = transformedModel;
		ModelBase m = Pixelmon.proxy.loadModel(transformedModel);
		model = m;
	}

	public float getScaleFactor() {
		return getGrowth().scaleValue * getBossMode().scaleFactor;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote && !oldName.equals(getName())) {
			isInitialised = false;
			((Entity3HasStats) this).getBaseStats(getName());
			model = null;
			oldName = getName();
		}
		if (worldObj.isRemote) {
			animationCounter += animationIncrement;
			if (animationCounter >= animationLimit)
				animationCounter = 0;
		}
	}
}
