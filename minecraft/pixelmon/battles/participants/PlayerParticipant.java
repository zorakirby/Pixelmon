package pixelmon.battles.participants;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import pixelmon.Pixelmon;
import pixelmon.PixelmonMethods;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.controller.BattleController;
import pixelmon.battles.status.StatusBase;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonConfig;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumGui;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import pixelmon.storage.PlayerStorage;

public class PlayerParticipant extends BattleParticipant {
	public EntityPlayerMP player;
	PlayerStorage storage;
	EntityPixelmon currentPixelmon;

	public PlayerParticipant(EntityPlayerMP p, EntityPixelmon firstPixelmon) throws PlayerNotLoadedException {
		player = p;
		currentPixelmon = firstPixelmon;
		storage = PixelmonStorage.PokeballManager.getPlayerStorage(player);
	}

	@Override
	public ParticipantType getType() {
		return ParticipantType.Player;
	}

	@Override
	public boolean canGainXP() {
		return true;
	}

	@Override
	public EntityPixelmon currentPokemon() {
		return currentPixelmon;
	}

	@Override
	public boolean hasMorePokemon() {
		if (storage.countAblePokemon() > 0)
			return true;
		return false;
	}

	@Override
	public void StartBattle(BattleController bc, BattleParticipant opponent) {
		super.StartBattle(bc, opponent);
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.ClearTempStore, 0));
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.SetOpponentType, opponent.getType().index));
		player.openGui(Pixelmon.instance, EnumGui.Battle.getIndex(), player.worldObj, BattleRegistry.getIndex(bc), 0, 0);
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.SetBattlingPokemon, currentPixelmon.getPokemonId()));
		for (EntityPixelmon p : PixelmonMethods.getAllActivePokemon(player)) {
			for (int i = 0; i < 4; i++) {
				if (p.moveset.get(i) != null)
					p.moveset.get(i).setDisabled(false, p);
			}
		}
	}

	@Override
	public void EndBattle() {
		currentPixelmon.battleStats.clearBattleStats();
		for (int i = 0; i < currentPixelmon.status.size(); i++) {
			try {
				if (currentPixelmon.status.get(i).clearsOnBattleEnd()) {
					currentPixelmon.status.remove(i);
					i--;
				}
			} catch (Exception exc) {
				if (PixelmonConfig.printErrors) {
					System.out.println("Error in clearsOnBattleEnd for " + currentPixelmon.status.get(i).type.toString());
					System.out.println(exc.getStackTrace());
				}
			}
		}
		currentPixelmon.EndBattle();
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.ExitBattle, 0));
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.ClearTempStore, 0));
	}

	@Override
	public void getNextPokemon() {
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.EnforcedSwitch, 0));
	}

	@Override
	public boolean getIsFaintedOrDead() {
		return currentPixelmon.isDead || currentPixelmon.isFainted || currentPixelmon.getHealth() <= 0;
	}

	@Override
	public String getName() {
		return player.username;
	}

	@Override
	public Attack getMove() {
		if (bc == null)
			return null;
		if (currentPixelmon.moveset.size() == 0) {
			bc.endBattle();
			return null;
		}
		boolean canSwitch = true;
		for (StatusBase status : currentPixelmon.status) {
			try {
				if (status.stopsSwitching())
					canSwitch = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.BackToMainMenu, canSwitch ? 1 : 0));
		wait = true;
		return null;
	}

	@Override
	public void switchPokemon(int newPixelmonId) {
		double x = currentPixelmon.posX;
		double y = currentPixelmon.posY;
		double z = currentPixelmon.posZ;
		currentPixelmon.battleStats.clearBattleStats();
		if (!currentPixelmon.isFainted) {
			ChatHandler.sendBattleMessage(player, "That's enough " + currentPixelmon.getNickname() + "!");
			bc.sendToOtherParticipants(this, player.username + " withdrew " + currentPixelmon.getNickname() + "!");
		}
		currentPixelmon.catchInPokeball();
		storage.retrieve(currentPixelmon);

		if (storage.EntityAlreadyExists(newPixelmonId, player.worldObj)) {
			EntityPixelmon oldPokemon = storage.getAlreadyExists(newPixelmonId, player.worldObj);
			oldPokemon.catchInPokeball();
			storage.retrieve(oldPokemon);
		}
		EntityPixelmon newPixelmon = storage.sendOut(newPixelmonId, currentPixelmon.getOwner().worldObj);
		newPixelmon.motionX = newPixelmon.motionY = newPixelmon.motionZ = 0;
		newPixelmon.releaseFromPokeball();
		newPixelmon.setLocationAndAngles(x, y, z, currentPixelmon.rotationYaw, 0.0F);
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.SetBattlingPokemon, newPixelmon.getPokemonId()));
		ChatHandler.sendBattleMessage(player, "Go " + newPixelmon.getNickname() + "!");
		bc.sendToOtherParticipants(this, player.username + " sent out " + newPixelmon.getNickname() + "!");
		currentPixelmon = newPixelmon;
		for (BattleParticipant p : bc.participants)
			p.updateOpponent();
	}

	@Override
	public boolean checkPokemon() {
		for (NBTTagCompound n : storage.partyPokemon) {
			if (n != null && n.getInteger("PixelmonNumberMoves") == 0) {
				ChatHandler.sendChat(currentPixelmon.getOwner(), "Couldn't load pokemon's moves");
				return false;
			}
		}
		return true;
	}

	@Override
	public void updatePokemon() {
		storage.updateNBT(currentPixelmon);
	}

	@Override
	public EntityLiving getEntity() {
		return player;
	}

	@Override
	public void updateOpponent() {
		PixelmonDataPacket p = new PixelmonDataPacket(opponent.currentPokemon(), EnumPackets.SetOpponent);
		player.playerNetServerHandler.sendPacketToPlayer(p.getPacket());
	}

	public void updateOpponentHealth(EntityPixelmon pixelmon) {
		PixelmonDataPacket p = new PixelmonDataPacket(pixelmon, EnumPackets.SetOpponent);
		player.playerNetServerHandler.sendPacketToPlayer(p.getPacket());
	}

	@Override
	public String getFaintMessage() {
		return player.username + "'s " + currentPokemon().getNickname() + " fainted!";
	}

	public int getHighestLevel() {
		int lvl = -1;
		for (NBTTagCompound nbt : storage.partyPokemon) {
			if (nbt != null)
				if (nbt.getInteger("Level") > lvl)
					lvl = nbt.getInteger("Level");
		}
		return lvl;
	}

}
