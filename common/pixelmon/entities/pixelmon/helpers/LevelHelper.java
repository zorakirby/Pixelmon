package pixelmon.entities.pixelmon.helpers;

import java.util.ArrayList;

import pixelmon.Pixelmon;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.database.DatabaseMoves;
import pixelmon.database.ExperienceGroup;
import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import pixelmon.enums.EnumGui;
import pixelmon.storage.PixelmonStorage;

import net.minecraft.src.DataWatcher;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;

import net.minecraft.src.NBTTagCompound;

public class LevelHelper {

	private EntityPixelmon pixelmon;
	private int baseLevel = 0;

	public LevelHelper(EntityPixelmon p) {
		this.pixelmon = p;
		pixelmon.getDataWatcher().addObject(9, (short) 1); // Level
		pixelmon.getDataWatcher().addObject(11, (short) 0); // Experience
		pixelmon.getDataWatcher().addObject(13, (short) 0); // Experience to
															// next level
		setScale();
	}

	protected void updateStats() {
		pixelmon.updateStats();
	}

	public void writeToNBT(NBTTagCompound var1) {
		var1.setInteger("Level", getLevel());
		var1.setInteger("EXP", getExp());
		var1.setInteger("EXPToNextLevel", getExpToNextLevel());
	}

	public void readFromNBT(NBTTagCompound var1) {
		setLevel(var1.getInteger("Level"));
		setExp(var1.getInteger("EXP"));
		setExpToNextLevel(getExpForLevel(getLevel() + 1) - getExpForLevel(getLevel()));
	}

	public int getLevel() {
		return pixelmon.getDataWatcher().getWatchableObjectShort(9);
	}

	public void setLevel(int i) {
		pixelmon.getDataWatcher().updateObject(9, (short) i);
		setScale();
		setExpToNextLevel(getExpForLevel(getLevel() + 1) - getExpForLevel(getLevel()));
		setExp(0);
		float oldHp = pixelmon.stats.HP;
		updateStats();
		float percentGain = ((float) pixelmon.stats.HP) / oldHp;
		float newHealth;
		if (oldHp == 0)
			newHealth = pixelmon.getMaxHealth();
		else
			newHealth = ((float) pixelmon.getHealth()) * percentGain;
		pixelmon.setHealth((int) Math.ceil(newHealth));
	}

	private int getExpForLevel(int level2) {
		double l = level2;
		if (pixelmon.baseStats.ExperienceGroup == null)
			;// .getMinecraftInstance().ingameGUI.addChatMessage("Database error with "
				// + pixelmon.getName());
		if (pixelmon.baseStats.ExperienceGroup == ExperienceGroup.Erratic) {
			if (l <= 50)
				return (int) (l * l * l * (100 - l)) / 50;
			if (l <= 68)
				return (int) (l * l * l * (150 - l)) / 100;
			if (l <= 98)
				return (int) (l * l * l * (1911 - 10 * l)) / 3;
			if (l <= 100)
				return (int) (l * l * l * (160 - l)) / 100;
		} else if (pixelmon.baseStats.ExperienceGroup == ExperienceGroup.Fast) {
			return (int) (4 * l * l * l / 5);
		} else if (pixelmon.baseStats.ExperienceGroup == ExperienceGroup.MediumFast) {
			return (int) (l * l * l);
		} else if (pixelmon.baseStats.ExperienceGroup == ExperienceGroup.MediumSlow) {
			return (int) ((6 / 5) * l * l * l - 15 * l * l + 100 * l - 140);
		} else if (pixelmon.baseStats.ExperienceGroup == ExperienceGroup.Slow) {
			return (int) (5 * l * l * l / 4);
		} else if (pixelmon.baseStats.ExperienceGroup == ExperienceGroup.Fluctuating) {
			if (l <= 15)
				return (int) (l * l * l * ((l + 1) / 3 + 24) / 50);
			if (l <= 36)
				return (int) (l * l * l * (l + 14) / 50);
			if (l <= 100)
				return (int) (l * l * l * ((l / 2) + 32) / 50);
		}

		return -1;
	}

	public int getExp() {
		return pixelmon.getDataWatcher().getWatchableObjectShort(11);
	}

	public void setExp(int i) {
		pixelmon.getDataWatcher().updateObject(11, (short) i);
	}

	public int getExpToNextLevel() {
		return pixelmon.getDataWatcher().getWatchableObjectShort(13);
	}

	public void setExpToNextLevel(int i) {
		pixelmon.getDataWatcher().updateObject(13, (short) i);
	}

	public boolean canLevelUp() {
		return getLevel() != 100;
	}

	protected void onLevelUp() {
		float oldHp = pixelmon.stats.HP;
		updateStats();
		float percentGain = ((float) pixelmon.stats.HP) / oldHp;
		float newHealth = ((float) pixelmon.getHealth()) * percentGain;
		pixelmon.setEntityHealth((int) Math.ceil(newHealth));
		if (pixelmon.getOwner() != null && pixelmon.getOwner() instanceof EntityPlayerMP) {
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) pixelmon.getOwner()).updateNBT(pixelmon);
			if (PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) pixelmon.getOwner()).contains(pixelmon.getPokemonId())) {
				ChatHandler.sendChat(pixelmon.getOwner(), "Your " + pixelmon.getName() + " leveled up to level " + getLevel() + "!");
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) pixelmon.getOwner()).updateNBT(pixelmon);
			}
		}
		String name = pixelmon.getName();

		if (DatabaseMoves.LearnsAttackAtLevel(name, getLevel())) {
			ArrayList<Attack> newAttacks = DatabaseMoves.getAttacksAtLevel(name, getLevel());
			for (Attack a : newAttacks) {
				if (pixelmon.moveset.size() >= 4) {
					((EntityPlayer) pixelmon.getOwner()).openGui(Pixelmon.instance, EnumGui.LearnMove.getIndex(), pixelmon.getOwner().worldObj, pixelmon.getPokemonId(), a.attackIndex, 0); // guiLearnMove
				} else {
					pixelmon.moveset.add(a);
					ChatHandler.sendChat(pixelmon.getOwner(), pixelmon.getName() + " just learnt " + a.attackName + "!");
				}
			}
		}
		setScale();
	}

	public void awardEXP(int i) {
		setExp(getExp() + i);
		if ((pixelmon.getOwner() != null) && getLevel() != 100)
			if (pixelmon.getOwner() != null)
				ChatHandler.sendChat(pixelmon.getOwner(), "Your " + pixelmon.getName() + " gained " + i + " EXP!");
		if (!canLevelUp() || getExpToNextLevel() == -1) {
			setExp(0);
			return;
		}
		while (getExp() >= getExpToNextLevel()) {
			setLevel(getLevel() + 1);
			onLevelUp();
			if (getLevel() >= pixelmon.baseStats.EvolveLevel) {
				pixelmon.evolve(pixelmon.baseStats.EvolveInto);
			}
			if (!canLevelUp())
				return;
			setExp(getExp() - getExpToNextLevel());
			setExpToNextLevel(getExpForLevel(getLevel() + 1) - getExpForLevel(getLevel()));
		}
	}

	private void setScale() {
		float percent = 1;
		percent = 0.8f + 0.4f * (getLevel()) / (100);
		if (percent > pixelmon.maxScale)
			percent = pixelmon.maxScale;
		pixelmon.scale = percent;
	}

	public void recalculateXP() {
		setExp(0);
		setExpToNextLevel(getExpForLevel(getLevel() + 1) - getExpForLevel(getLevel()));
	}
}
