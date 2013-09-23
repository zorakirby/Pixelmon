package pixelmon.entities.pixelmon.stats;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import pixelmon.battles.attacks.Attack;
import pixelmon.blocks.BlockEvolutionRock;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.EnumUpdateType;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonLevelUpPacket;
import pixelmon.comm.PixelmonStatsPacket;
import pixelmon.comm.packetHandlers.ReplaceMove;
import pixelmon.database.DatabaseMoves;
import pixelmon.database.DatabaseStats;
import pixelmon.database.EvolutionInfo;
import pixelmon.database.EvolutionInfo.InfoMode;
import pixelmon.database.ExperienceGroup;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumBiomes;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;
import pixelmon.storage.PixelmonStorage;

public class Level {

	private EntityPixelmon pixelmon;
	private int baseLevel = 0;
	public int expToNextLevel = 0;

	public Level(EntityPixelmon p) {
		this.pixelmon = p;
		pixelmon.getDataWatcher().addObject(EntityPixelmon.dwLevel, (short) -1); // Level
		pixelmon.getDataWatcher().addObject(EntityPixelmon.dwExp, (int) 0); // ExperiencePercent
		setScale();
	}

	protected void updateStats() {
		pixelmon.updateStats();
	}

	public void writeToNBT(NBTTagCompound var1) {
		var1.setInteger("Level", getLevel());
		var1.setInteger("EXP", getExp());
	}

	public void readFromNBT(NBTTagCompound var1) {
		setExp(var1.getInteger("EXP"));
		expToNextLevel = getExpForLevel(getLevel() + 1) - getExpForLevel(getLevel());
		setLevel(var1.getInteger("Level"));
	}

	public int getLevel() {
		return pixelmon.getDataWatcher().getWatchableObjectShort(EntityPixelmon.dwLevel);
	}

	public void setLevel(int i) {
		pixelmon.getDataWatcher().updateObject(EntityPixelmon.dwLevel, (short) i);
		setScale();
		expToNextLevel = getExpForLevel(getLevel() + 1) - getExpForLevel(getLevel());
		if (pixelmon.func_110143_aJ() == pixelmon.stats.HP) {
			updateStats();
			pixelmon.setEntityHealth(pixelmon.stats.HP);
		} else {
			float oldHp = pixelmon.stats.HP;
			float oldHealth = pixelmon.func_110143_aJ();
			updateStats();
			float newHealth = pixelmon.stats.HP;
			if (oldHp != 0)
				newHealth = oldHealth / oldHp * pixelmon.stats.HP;
			pixelmon.setEntityHealth((int) Math.ceil(newHealth));
		}
	}

	private int getExpForLevel(int level2) {
		double l = level2;
		ExperienceGroup ex = pixelmon.baseStats.experienceGroup;
		if (ex == ExperienceGroup.Erratic) {
			if (l <= 50)
				return (int) (l * l * l * (100 - l)) / 50;
			if (l <= 68)
				return (int) (l * l * l * (150 - l)) / 100;
			if (l <= 98)
				return (int) (l * l * l * (1911 - 10 * l)) / 3;
			if (l <= 100)
				return (int) (l * l * l * (160 - l)) / 100;
		} else if (ex == ExperienceGroup.Fast) {
			return (int) (4 * l * l * l / 5);
		} else if (ex == ExperienceGroup.MediumFast) {
			return (int) (l * l * l);
		} else if (ex == ExperienceGroup.MediumSlow) {
			return (int) ((6 / 5) * l * l * l - 15 * l * l + 100 * l - 140);
		} else if (ex == ExperienceGroup.Slow) {
			return (int) (5 * l * l * l / 4);
		} else if (ex == ExperienceGroup.Fluctuating) {
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
		return pixelmon.getDataWatcher().getWatchableObjectInt(EntityPixelmon.dwExp);
	}

	public void setExp(int i) {
		pixelmon.getDataWatcher().updateObject(EntityPixelmon.dwExp, (int) i);
	}

	public boolean canLevelUp() {
		return getLevel() != 100;
	}

	protected void onLevelUp(PixelmonStatsPacket stats) {
		float oldHp = pixelmon.stats.HP;
		updateStats();
		float percentGain = ((float) pixelmon.stats.HP) / oldHp;
		float newHealth = pixelmon.func_110143_aJ() * percentGain;
		pixelmon.setEntityHealth((int) Math.ceil(newHealth));
		if (pixelmon.getOwner() != null && pixelmon.getOwner() instanceof EntityPlayerMP) {
			PixelmonStatsPacket stats2 = PixelmonStatsPacket.createPacket(pixelmon);
			PixelmonLevelUpPacket p = new PixelmonLevelUpPacket(pixelmon, getLevel(), stats, stats2, EnumPackets.LevelUp);
			((EntityPlayerMP) pixelmon.getOwner()).playerNetServerHandler.sendPacketToPlayer(p.getPacket());
			pixelmon.update(EnumUpdateType.Stats);
		}

		if (pixelmon.getOwner() != null)
			pixelmon.friendship.onLevelUp();

		setScale();
	}

	public void awardEXP(int i) {
		if (!pixelmon.doesLevel)
			return;
		setExp(getExp() + i);
		if ((pixelmon.getOwner() != null) && getLevel() != 100)
			if (pixelmon.getOwner() != null)
				ChatHandler.sendChat(pixelmon.getOwner(), "Your " + pixelmon.getNickname() + " gained " + i + " EXP!");
		if (!canLevelUp() || expToNextLevel == -1) {
			setExp(0);
			return;
		}
		while (getExp() >= expToNextLevel) {
			int newExp = getExp() - expToNextLevel;
			if (!canLevelUp())
				return;

			PixelmonStatsPacket stats = null;
			if (pixelmon.getOwner() != null)
				stats = PixelmonStatsPacket.createPacket(pixelmon);
			setLevel(getLevel() + 1);
			onLevelUp(stats);
			setExp(newExp);
			if (!ItemHeld.isItemOfType(pixelmon.getHeldItem(), EnumHeldItems.everStone)) {
				if (pixelmon.baseStats.evolveInto != null && pixelmon.baseStats.evolveLevel != -1 && getLevel() >= pixelmon.baseStats.evolveLevel) {
					pixelmon.startEvolution(pixelmon.baseStats.evolveInto.name);
				}
				for (EvolutionInfo e : DatabaseStats.getEvolveList(pixelmon.getName())) {
					if (EnumPokemon.hasPokemon(e.pokemonName) && e.mode == InfoMode.friendship && pixelmon.friendship.isFriendshipHighEnoughToEvolve()) {
						boolean evolves = true;
						if (e.extraParam != null) {
							if (e.extraParam.equalsIgnoreCase("day") && !pixelmon.worldObj.isDaytime())
								evolves = false;
							else if (e.extraParam.equalsIgnoreCase("night") && pixelmon.worldObj.isDaytime())
								evolves = false;
						}
						if (evolves) {
							pixelmon.startEvolution(e.pokemonName);
							break;
						}
					} else if (EnumPokemon.hasPokemon(e.pokemonName) && e.mode == InfoMode.biome) {
						if (pixelmon.worldObj.getBiomeGenForCoords((int) pixelmon.posX, (int) pixelmon.posZ) == EnumBiomes.parseBiome(e.extraParam).getBiome()) {
							pixelmon.startEvolution(e.pokemonName);
							break;
						}
					} else if (EnumPokemon.hasPokemon(e.pokemonName) && e.mode == InfoMode.evolutionRock) {
						boolean evolves = false;
						EntityPlayer player = (EntityPlayer) pixelmon.getOwner();
						for (int j = 0; j < pixelmon.worldObj.loadedTileEntityList.size(); j++) {
							TileEntity t = (TileEntity) pixelmon.worldObj.loadedTileEntityList.get(j);
							if (t.getBlockType() instanceof BlockEvolutionRock && ((BlockEvolutionRock) t.getBlockType()).rockType == e.evolutionRock) {
								if (getDistanceFrom(t, player.posX, player.posY, player.posZ) < 100) {
									evolves = true;
									break;
								}
							}
						}
						if (evolves) {
							pixelmon.startEvolution(e.pokemonName);
							break;
						}
					}
				}
			}
			String name = pixelmon.getName();
			if (DatabaseMoves.LearnsAttackAtLevel(name, getLevel())) {
				ArrayList<Attack> newAttacks = DatabaseMoves.getAttacksAtLevel(name, getLevel());
				for (Attack a : newAttacks) {
					if (pixelmon.getMoveset().size() >= 4) {
						ReplaceMove.tmID = -1;
						((EntityPlayerMP) pixelmon.getOwner()).playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(
								EnumPackets.ChooseMoveToReplace, pixelmon.getPokemonId(), a.baseAttack.attackIndex, getLevel()));
					} else {
						pixelmon.getMoveset().add(a);
						pixelmon.update(EnumUpdateType.Moveset);
						ChatHandler.sendChat(pixelmon.getOwner(), pixelmon.getNickname() + " just learnt " + a.baseAttack.attackName + "!");
					}
				}
			}
		}
	}

	public double getDistanceFrom(TileEntity te, double par1, double par3, double par5) {
		double d3 = (double) te.xCoord + 0.5D - par1;
		double d4 = (double) te.yCoord + 0.5D - par3;
		double d5 = (double) te.zCoord + 0.5D - par5;
		return d3 * d3 + d4 * d4 + d5 * d5;
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
		expToNextLevel = getExpForLevel(getLevel() + 1) - getExpForLevel(getLevel());
	}

	int oldLevel = -1;

	public int getExtForNextLevelClient() {
		if (oldLevel != getLevel()){
			expToNextLevel = getExpForLevel(getLevel() + 1) - getExpForLevel(getLevel());
			oldLevel = getLevel();
		}
		return expToNextLevel;
	}
}
