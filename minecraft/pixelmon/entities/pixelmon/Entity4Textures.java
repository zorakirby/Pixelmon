package pixelmon.entities.pixelmon;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import pixelmon.Pixelmon;
import pixelmon.entities.pixelmon.particleEffects.ParticleEffects;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class Entity4Textures extends Entity3HasStats {
	public float maxScale = 1.25F;
	public float hoverTimer;
	public boolean alreadyInitialised = false;
	private ParticleEffects particleEffects;

	public Entity4Textures(World par1World) {
		super(par1World);
		dataWatcher.addObject(EntityPixelmon.dwShiny, (short) 0); // shiny
		dataWatcher.addObject(EntityPixelmon.dwRoasted, (short) 0); // roasted
		dataWatcher.addObject(EntityPixelmon.dwRed, (short) 0); // red
	}

	protected void init(String name) {
		super.init(name);
		if (!worldObj.isRemote) {
			if (rand.nextFloat() < 1 / 8192f) {
				dataWatcher.updateObject(EntityPixelmon.dwShiny, (short) 1);
			}
		}
		alreadyInitialised = true;

		if (worldObj.isRemote)
			particleEffects = ParticleEffects.getParticleEffects(this);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if (par1DamageSource.isFireDamage())
			dataWatcher.updateObject(EntityPixelmon.dwRoasted, (short) 1);
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	boolean hasRoastedTexture = false;
	boolean checkedForRoastedTexture = false;

	@SideOnly(Side.CLIENT)
	public String getTexture() {
		try {
			if (dataWatcher.getWatchableObjectShort(EntityPixelmon.dwRoasted) == (short) 1 && !checkedForRoastedTexture || hasRoastedTexture) {
				if (!checkedForRoastedTexture) {
					if (Pixelmon.class.getResourceAsStream("pixelmon:textures/pokemon/pokemon-roasted/roasted" + getName().toLowerCase() + ".png") != null)
						hasRoastedTexture = true;
					checkedForRoastedTexture = true;
				}
			}
			if (getIsShiny() && Pixelmon.class.getResourceAsStream("pixelmon:textures/pokemon/pokemon-shiny/shiny" + getName().toLowerCase() + ".png") != null)
				return "pixelmon:textures/pokemon/pokemon-shiny/shiny" + getName().toLowerCase() + ".png";
			else if (dataWatcher.getWatchableObjectShort(EntityPixelmon.dwRoasted) == (short) 1 && hasRoastedTexture) {
				return "pixelmon:textures/pokemon/pokemon-roasted/roasted" + getName().toLowerCase() + ".png";
			} else
				return "pixelmon:textures/pokemon/" + getName().toLowerCase() + ".png";
		} catch (Exception e) {
			return "";
		}
	}

	public boolean getIsShiny() {
		return dataWatcher.getWatchableObjectShort(EntityPixelmon.dwShiny) == (short) 1;
	}

	public void setIsShiny(boolean isShiny) {
		if (isShiny)
			dataWatcher.updateObject(EntityPixelmon.dwShiny, (short) 1);
		else
			dataWatcher.updateObject(EntityPixelmon.dwShiny, (short) 0);
	}

	public boolean getIsRed() {
		return dataWatcher.getWatchableObjectShort(EntityPixelmon.dwRed) == (short) 1;
	}

	public void setIsRed(boolean isRed) {
		if (isRed)
			dataWatcher.updateObject(EntityPixelmon.dwRed, (short) 1);
		else
			dataWatcher.updateObject(EntityPixelmon.dwRed, (short) 0);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote)
			if (particleEffects != null)
				particleEffects.onUpdate();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("IsShiny", dataWatcher.getWatchableObjectShort(EntityPixelmon.dwShiny) == (short) 1);
		nbt.setBoolean("IsRoasted", dataWatcher.getWatchableObjectShort(EntityPixelmon.dwRoasted) == (short) 1);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		dataWatcher.updateObject(EntityPixelmon.dwShiny, nbt.getBoolean("IsShiny") ? (short) 1 : (short) 0);
		dataWatcher.updateObject(EntityPixelmon.dwRoasted, nbt.getBoolean("IsRoasted") ? (short) 1 : (short) 0);
		alreadyInitialised = true;
	}
}
