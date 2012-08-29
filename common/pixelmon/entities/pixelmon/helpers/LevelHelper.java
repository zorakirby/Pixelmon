package pixelmon.entities.pixelmon.helpers;

import java.util.ArrayList;

import pixelmon.Pixelmon;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.database.DatabaseMoves;
import pixelmon.database.ExperienceGroup;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import pixelmon.enums.EnumGui;
import pixelmon.storage.PixelmonStorage;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayerMP;

import net.minecraft.src.NBTTagCompound;

public class LevelHelper {

	private PixelmonEntityHelper pixelmon;
	private int exp;
	private int expToNextLevel;
	private int baseLevel = 0;
	private int level;
	public int maxHealth = 10;
	public int health;

	public LevelHelper() {
		exp = 0;
	}

	public LevelHelper(PixelmonEntityHelper p) {
		this.pixelmon = p;
		exp = 0;
		setScale();
	}

	protected void updateStats() {
		pixelmon.stats.setLevelStats(level);
		maxHealth = pixelmon.stats.HP;
	}

	public void writeToNBT(NBTTagCompound var1) {
		var1.setInteger("Level", level);
		var1.setInteger("EXP", exp);
		var1.setInteger("EXPToNextLevel", expToNextLevel);
	}

	public void readFromNBT(NBTTagCompound var1) {
		level = var1.getInteger("Level");
		exp = var1.getInteger("EXP");
		expToNextLevel = getExpForLevel(level + 1) - getExpForLevel(level);
		updateEntityString();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int i) {
		level = i;
		setScale();
		expToNextLevel = getExpForLevel(level + 1) - getExpForLevel(level);
		exp = 0;
		float oldHp = pixelmon.stats.HP;
		updateStats();
		float percentGain = ((float) pixelmon.stats.HP) / oldHp;
		float newHealth;
		if (oldHp == 0)
			newHealth = pixelmon.getMaxHealth();
		else
			newHealth = ((float) pixelmon.getHealth()) * percentGain;
		pixelmon.setHealth((int) Math.ceil(newHealth));
		updateEntityString();
	}

	private int getExpForLevel(int level2) {
		double l = level2;
		if (pixelmon.stats.BaseStats.ExperienceGroup == null)
			;// .getMinecraftInstance().ingameGUI.addChatMessage("Database error with "
				// + pixelmon.getName());
		if (pixelmon.stats.BaseStats.ExperienceGroup == ExperienceGroup.Erratic) {
			if (l <= 50)
				return (int) (l * l * l * (100 - l)) / 50;
			if (l <= 68)
				return (int) (l * l * l * (150 - l)) / 100;
			if (l <= 98)
				return (int) (l * l * l * (1911 - 10 * l)) / 3;
			if (l <= 100)
				return (int) (l * l * l * (160 - l)) / 100;
		} else if (pixelmon.stats.BaseStats.ExperienceGroup == ExperienceGroup.Fast) {
			return (int) (4 * l * l * l / 5);
		} else if (pixelmon.stats.BaseStats.ExperienceGroup == ExperienceGroup.MediumFast) {
			return (int) (l * l * l);
		} else if (pixelmon.stats.BaseStats.ExperienceGroup == ExperienceGroup.MediumSlow) {
			return (int) ((6 / 5) * l * l * l - 15 * l * l + 100 * l - 140);
		} else if (pixelmon.stats.BaseStats.ExperienceGroup == ExperienceGroup.Slow) {
			return (int) (5 * l * l * l / 4);
		} else if (pixelmon.stats.BaseStats.ExperienceGroup == ExperienceGroup.Fluctuating) {
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
		return exp;
	}

	public int getExpToNextLevel() {
		return expToNextLevel;
	}

	public boolean canLevelUp() {
		return level != 100;
	}

	protected void onLevelUp() {
		float oldHp = pixelmon.stats.HP;
		updateStats();
		float percentGain = ((float) pixelmon.stats.HP) / oldHp;
		float newHealth = ((float) pixelmon.getHealth()) * percentGain;
		pixelmon.setHealth((int) Math.ceil(newHealth));
		if (pixelmon.getOwner() != null && pixelmon.getOwner() instanceof EntityPlayerMP)
		{
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)pixelmon.getOwner()).updateNBT(pixelmon);
			if (PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)pixelmon.getOwner()).contains(pixelmon.getPokemonId())) 
			{
				ChatHandler.sendChat(pixelmon.getOwner(), "Your " + pixelmon.getName() + " leveled up to level " + level + "!");
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP)pixelmon.getOwner()).updateNBT(pixelmon);
			}
		}
		String name = pixelmon.getName();

		if (DatabaseMoves.LearnsAttackAtLevel(name, level)) {
			ArrayList<Attack> newAttacks = DatabaseMoves.getAttacksAtLevel(name, level);
			for (Attack a : newAttacks) {
				if (pixelmon.moveset.size() >= 4) {
					pixelmon.getOwner().openGui(Pixelmon.instance, EnumGui.LearnMove.getIndex(), pixelmon.getOwner().worldObj, pixelmon.getPokemonId(), a.attackIndex, 0); // guiLearnMove
				} else {
					pixelmon.moveset.add(a);
					ChatHandler.sendChat(pixelmon.getOwner(), pixelmon.getName() + " just learnt " + a.attackName + "!");
				}
			}
		}
		setScale();
		updateEntityString();
	}

	public void awardEXP(int i) {
		exp += i;
		if ((pixelmon.getOwner() != null) && getLevel() != 100)
			if (pixelmon.getOwner() != null)
				ChatHandler.sendChat(pixelmon.getOwner(), "Your " + pixelmon.getName() + " gained " + i + " EXP!");
		if (!canLevelUp() || expToNextLevel == -1) {
			exp = 0;
			return;
		}
		while (exp >= expToNextLevel) {
			level++;
			onLevelUp();
			if (level >= pixelmon.stats.BaseStats.EvolveLevel) {
				if (pixelmon.getIHaveHelper() instanceof BaseEntityPixelmon)
					((BaseEntityPixelmon) pixelmon.getIHaveHelper()).evolve();
				else if (pixelmon.getIHaveHelper() instanceof EntityWaterPixelmon)
					((EntityWaterPixelmon) pixelmon.getIHaveHelper()).evolve();
			}
			if (!canLevelUp())
				return;
			exp -= expToNextLevel;
			expToNextLevel = getExpForLevel(level + 1) - getExpForLevel(level);
		}
		updateEntityString();
	}

	public void setEXP(int i) {
		exp = i;
		updateEntityString();
	}

	private void setScale() {
		float percent = 1;
		percent = 0.8f + 0.4f * (level) / (100);
		if (percent > pixelmon.maxScale)
			percent = pixelmon.maxScale;
		pixelmon.scale = percent;
	}

	public static LevelHelper readFromLvlString(String lvlString) {
		if (lvlString == "")
			return null;
		LevelHelper l = new LevelHelper();
		String[] splits = lvlString.split(";");
		l.exp = Integer.parseInt(splits[0]);
		l.expToNextLevel = Integer.parseInt(splits[1]);
		l.level = Integer.parseInt(splits[2]);
		l.health = Integer.parseInt(splits[3]);
		l.maxHealth = Integer.parseInt(splits[4]);
		return l;
	}

	public void updateEntityString() {
		String lvlString = "" + exp + ";" + expToNextLevel + ";" + level + ";" + (int) pixelmon.getHealth() + ";" + pixelmon.stats.HP;
		pixelmon.updateLvlString(lvlString);
	}

	public void recalculateXP() {
		exp = 0;
		expToNextLevel = getExpForLevel(level + 1) - getExpForLevel(level);
	}
}
