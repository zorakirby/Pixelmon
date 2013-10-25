package pixelmon.entities.pixelmon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.world.World;
import pixelmon.Pixelmon;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.client.models.objHandling.ModelObj;
import pixelmon.client.models.objHandling.Object3D;
import pixelmon.enums.EnumPokemon;

public abstract class Entity2HasModel extends Entity1Base {

	ModelBase model;
	ModelBase flyingModel;
	public float animationNum1 = 0f;
	public int animationCounter = 0;
	public int animationIncrement = 2;
	public int animationLimit = 360;
	public int animationCounter2 = 0;
	public int animationIncrement2 = 3;
	public int animationLimit2 = 360;
	public int flyingDelayCounter = 0;
	public int flyingDelayIncrement = 1;
	public int flyingDelayLimit = 30;
	
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

	@SideOnly(Side.CLIENT)
	public ModelBase getModel() {
		if (model == null)
			loadModel();
		if(flyingModel != null)
		if(flyingDelayCounter >= flyingDelayLimit)
				return flyingModel;	
		return model;
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
			flyingModel = (ModelBase)Pixelmon.proxy.getFlyingModels()[n];
		} else {
			ModelBase m = Pixelmon.proxy.loadModel(getName());
			Pixelmon.proxy.getModels()[n] = m;
			model = m;
			m = Pixelmon.proxy.loadFlyingModel(getName());
			if (m != null)
				Pixelmon.proxy.getFlyingModels()[n] = m;
			flyingModel = m;
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean transformed;
	@SideOnly(Side.CLIENT)
	String transformedModel;

	@SideOnly(Side.CLIENT)
	public void transform(String transformedModel) {
		transformed = true;
		this.transformedModel = transformedModel;
		ModelBase m = Pixelmon.proxy.loadModel(transformedModel);
		model = m;
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
			model = null;
			oldName = getName();
		}
		if (worldObj.isRemote) {
			animationCounter = animationCounter + animationIncrement;
			if (animationCounter >= animationLimit)
				animationCounter = 0;
			animationCounter2 = animationCounter2 + animationIncrement2;
			if (animationCounter2 >= animationLimit2)
				animationCounter2 = 0;
			if (!onGround && !inWater) {
				if (flyingDelayCounter < flyingDelayLimit)
					flyingDelayCounter = flyingDelayCounter
							+ flyingDelayIncrement;
			} else
			flyingDelayCounter = 0;	
		}

	}
}
