package pixelmon.entities.pixelmon.stats;

import java.util.ArrayList;

import pixelmon.Pixelmon;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonLevelUpPacket;
import pixelmon.comm.PixelmonStatsPacket;
import pixelmon.database.DatabaseMoves;
import pixelmon.database.DatabaseStats;
import pixelmon.database.EvolutionInfo;
import pixelmon.database.ExperienceGroup;
import pixelmon.database.EvolutionInfo.InfoMode;
import pixelmon.entities.pixelmon.Entity3HasStats;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.EntityWaterPixelmon;
import pixelmon.enums.EnumBiomes;
import pixelmon.enums.EnumGui;
import pixelmon.storage.PixelmonStorage;

import net.minecraft.src.DataWatcher;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;

import net.minecraft.src.NBTTagCompound;

public class Level {

	private EntityPixelmon pixelmon;
	private int baseLevel = 0;

	public Level(EntityPixelmon p) {
		this.pixelmon = p;
		pixelmon.getDataWatcher().addObject(9, (short) -1); // Level
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
		updateStats();
	}

	public int getLevel() {
		return pixelmon.getDataWatcher().getWatchableObjectShort(9);
	}

	public void setLevel(int i) {
		pixelmon.getDataWatcher().updateObject(9, (short) i);
		setScale();
		setExpToNextLevel(getExpForLevel(getLevel() + 1) - getExpForLevel(getLevel()));
		if (pixelmon.getHealth() == pixelmon.stats.HP) {
			updateStats();
			pixelmon.setEntityHealth(pixelmon.stats.HP);
		} else {
			float oldHp = pixelmon.stats.HP;
			float oldHealth = pixelmon.getHealth();
			updateStats();
			float newHealth = pixelmon.stats.HP;
			if (oldHp != 0)
				newHealth = oldHealth / oldHp * pixelmon.stats.HP;
			pixelmon.setEntityHealth((int) Math.ceil(newHealth));
		}
	}

	private int getExpForLevel(int level2) {
		double l = level2;
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

	protected void onLevelUp(PixelmonStatsPacket stats) {
		float oldHp = pixelmon.stats.HP;
		updateStats();
		float percentGain = ((float) pixelmon.stats.HP) / oldHp;
		float newHealth = ((float) pixelmon.getHealth()) * percentGain;
		pixelmon.setEntityHealth((int) Math.ceil(newHealth));
		if (pixelmon.getOwner() != null && pixelmon.getOwner() instanceof EntityPlayerMP) {
			PixelmonStatsPacket stats2 = PixelmonStatsPacket.createPacket(pixelmon);
			PixelmonLevelUpPacket p = new PixelmonLevelUpPacket(pixelmon, getLevel(), stats, stats2, EnumPackets.LevelUp);
			((EntityPlayerMP) pixelmon.getOwner()).playerNetServerHandler.sendPacketToPlayer(p.getPacket());
			PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) pixelmon.getOwner()).updateNBT(pixelmon);
		}
		String name = pixelmon.getName();

		if (pixelmon.getOwner() != null)
			pixelmon.friendship.onLevelUp();

		if (DatabaseMoves.LearnsAttackAtLevel(name, getLevel())) {
			ArrayList<Attack> newAttacks = DatabaseMoves.getAttacksAtLevel(name, getLevel());
			for (Attack a : newAttacks) {
				if (pixelmon.moveset.size() >= 4) {
					((EntityPlayerMP) pixelmon.getOwner()).playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(
							EnumPackets.ChooseMoveToReplace, pixelmon.getPokemonId(), a.attackIndex, getLevel()));
				} else {
					pixelmon.moveset.add(a);
					ChatHandler.sendChat(pixelmon.getOwner(), pixelmon.getName() + " just learnt " + a.attackName + "!");
				}
			}
		}
		setScale();
	}

	public void learnNextMove() {
	}

	public void awardEXP(int i) {
		if (!pixelmon.doesLevel)
			return;
		setExp(getExp() + i);
		if ((pixelmon.getOwner() != null) && getLevel() != 100)
			if (pixelmon.getOwner() != null)
				ChatHandler.sendChat(pixelmon.getOwner(), "Your " + pixelmon.getName() + " gained " + i + " EXP!");
		if (!canLevelUp() || getExpToNextLevel() == -1) {
			setExp(0);
			return;
		}
		while (getExp() >= getExpToNextLevel()) {
			int newExp = getExp() - getExpToNextLevel();
			PixelmonStatsPacket stats = null;
			if (pixelmon.getOwner() != null)
				stats = PixelmonStatsPacket.createPacket(pixelmon);
			setLevel(getLevel() + 1);
			onLevelUp(stats);
			if (getLevel() >= pixelmon.baseStats.EvolveLevel) {
				pixelmon.evolve(pixelmon.baseStats.EvolveInto);
			}
			for (EvolutionInfo e : DatabaseStats.getEvolveList(pixelmon.getName())) {
				if (e.mode == InfoMode.friendship && pixelmon.friendship.isFriendshipHighEnoughToEvolve()) {
					boolean evolves = true;
					if (e.extraParam != null) {
						if (e.extraParam.equalsIgnoreCase("day") && !pixelmon.worldObj.isDaytime())
							evolves = false;
						else if (e.extraParam.equalsIgnoreCase("night") && pixelmon.worldObj.isDaytime())
							evolves = false;
					}
					if (evolves)
						pixelmon.evolve(e.pokemonName);
				} else if (e.mode == InfoMode.biome) {
					if (pixelmon.worldObj.getBiomeGenForCoords((int) pixelmon.posX, (int) pixelmon.posZ) == EnumBiomes.parseBiome(e.extraParam).getBiome())
						pixelmon.evolve(e.pokemonName);

				}
			}
			if (!canLevelUp())
				return;
			setExp(newExp);
		}
	}

	private void setScale() {
		float percent = 1;
		percent = 0.8f + 0.4f * (getLevel()) / (100);
		if (percent > pixelmon.maxScale)
			percent = pixelmon.maxScale;
		pixelmon.setScale(percent);
	}

	public void recalculateXP() {
		setExp(0);
		setExpToNextLevel(getExpForLevel(getLevel() + 1) - getExpForLevel(getLevel()));
	}
}
