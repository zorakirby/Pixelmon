package pixelmon.entities.pixelmon;

import java.util.Random;

import pixelmon.entities.pixelmon.particleEffects.ParticleEffects;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.src.DamageSource;
import net.minecraft.src.ModelBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public abstract class Entity4Textures extends Entity3HasStats {
	public float maxScale = 1.25F;
	public float hoverTimer;
	public boolean alreadyInitialised = false;
	private ParticleEffects particleEffects;

	public Entity4Textures(World par1World) {
		super(par1World);
		dataWatcher.addObject(5, (short) 0); // shiny
		dataWatcher.addObject(6, (short) 0); // roasted
	}

	protected void init(String name) {
		super.init(name);
		if (!alreadyInitialised) {
			if ((new Random()).nextFloat() < 1 / 8192f) {
				System.out.println("Shiny " + getName() + " spawned");
				dataWatcher.updateObject(5, (short) 1);
			}
		}
		alreadyInitialised = true;
		
		particleEffects = ParticleEffects.getParticleEffects(this);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if (par1DamageSource.fireDamage())
			dataWatcher.updateObject(6, (short) 1);
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getTexture() {
		if (dataWatcher.getWatchableObjectShort(5) == (short) 1
				&& Minecraft.getMinecraft().renderEngine.texturePack.getSelectedTexturePack().getResourceAsStream("/pixelmon/texture/pokemon-shiny/shiny" + getName().toLowerCase() + ".png") != null)
			return "/pixelmon/texture/pokemon-shiny/shiny" + getName().toLowerCase() + ".png";
		else if (dataWatcher.getWatchableObjectShort(6) == (short) 1
				&& Minecraft.getMinecraft().renderEngine.texturePack.getSelectedTexturePack().getResourceAsStream("/pixelmon/texture/pokemon-roasted/roasted" + getName().toLowerCase() + ".png") != null)
			return "/pixelmon/texture/pokemon-roasted/roasted" + getName().toLowerCase() + ".png";
		else
			return "/pixelmon/texture/pokemon/" + getName().toLowerCase() + ".png";
	}

	public boolean getIsShiny() {
		return dataWatcher.getWatchableObjectShort(5) == (short) 1;
	}

	public void setIsShiny(boolean isShiny) {
		if (isShiny)
			dataWatcher.updateObject(5, (short) 1);
		else
			dataWatcher.updateObject(5, (short) 0);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote)
			if (particleEffects!=null) particleEffects.onUpdate();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("IsShiny", dataWatcher.getWatchableObjectShort(5) == (short) 1);
		nbt.setBoolean("IsRoasted", dataWatcher.getWatchableObjectShort(6) == (short) 1);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		dataWatcher.updateObject(5, nbt.getBoolean("IsShiny") ? (short) 1 : (short) 0);
		dataWatcher.updateObject(6, nbt.getBoolean("IsRoasted") ? (short) 1 : (short) 0);
		alreadyInitialised = true;
	}
}
