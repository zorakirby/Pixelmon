package pixelmon.battles.participants;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;

import net.minecraft.src.NBTTagCompound;
import pixelmon.Pixelmon;
import pixelmon.battles.BattleController;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.attacks.Attack;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.EntityCamera;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumGui;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerStorage;

public class PlayerParticipant implements IBattleParticipant {
	public EntityPlayerMP player;
	PlayerStorage storage;
	EntityPixelmon currentPixelmon;
	BattleController bc;
	EntityCamera cam;

	public PlayerParticipant(EntityPlayerMP p, EntityPixelmon firstPixelmon) {
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
	public void StartBattle(IBattleParticipant opponent) {
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.ClearTempStore, 0));
		cam = new EntityCamera(player.worldObj, player, bc);
		player.worldObj.spawnEntityInWorld(cam);
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.SetOpponentType, opponent.getType().index));
		player.openGui(Pixelmon.instance, EnumGui.Battle.getIndex(), player.worldObj, BattleRegistry.getIndex(bc), 0, 0);
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.SetBattlingPokemon, currentPixelmon.getPokemonId()));
	}

	@Override
	public void EndBattle(boolean didWin, IBattleParticipant foe) {
		currentPixelmon.battleStats.clearBattleStats();
		for (int i = 0; i < currentPixelmon.status.size(); i++) {
			if (currentPixelmon.status.get(i).clearsOnBattleEnd()) {
				currentPixelmon.status.remove(i);
				i--;
			}
		}
		currentPixelmon.EndBattle();
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.ExitBattle, 0));
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.ClearTempStore, 0));
	}

	@Override
	public void getNextPokemon(IBattleParticipant opponent) {
		switchPokemon(opponent, storage.getFirstAblePokemonID(player.worldObj));
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.SetBattlingPokemon, currentPixelmon.getPokemonId()));
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
	public Attack getMove(IBattleParticipant participant2) {
		if (bc == null)
			return null;
		if (currentPixelmon.moveset.size() == 0) {
			bc.endBattle(false);
			return null;
		}
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.BackToMainMenu, 0));
		bc.waitForMove(this);
		return null;
	}

	@Override
	public void switchPokemon(IBattleParticipant participant2, int newPixelmonId) {
		currentPixelmon.battleStats.clearBattleStats();
		if (!currentPixelmon.isFainted) {
			ChatHandler.sendBattleMessage(player, "That's enough " + currentPixelmon.getNickname() + "!");
			ChatHandler.sendBattleMessage(participant2.currentPokemon().getOwner(), player.username + " withdrew " + currentPixelmon.getNickname() + "!");
		}
		currentPixelmon.catchInPokeball();
		storage.retrieve(currentPixelmon);

		if (storage.EntityAlreadyExists(newPixelmonId, player.worldObj)) {
			EntityPixelmon oldPokemon = storage.getAlreadyExists(newPixelmonId, player.worldObj);
			oldPokemon.catchInPokeball();
			storage.retrieve(oldPokemon);
		}

		EntityPixelmon newPixelmon = storage.sendOut(newPixelmonId, currentPixelmon.getOwner().worldObj);
		newPixelmon.setLocationAndAngles(currentPixelmon.posX, currentPixelmon.posY, currentPixelmon.posZ, currentPixelmon.rotationYaw, 0.0F);
		newPixelmon.motionX = newPixelmon.motionY = newPixelmon.motionZ = 0;
		newPixelmon.releaseFromPokeball();
		player.playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.SetBattlingPokemon, newPixelmon.getPokemonId()));
		ChatHandler.sendBattleMessage(player, "Go " + newPixelmon.getNickname() + "!");
		ChatHandler.sendBattleMessage(participant2.currentPokemon().getOwner(), player.username + " sent out " + newPixelmon.getNickname() + "!");
		currentPixelmon = newPixelmon;
		participant2.updateOpponent(this);
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
	public void setBattleController(BattleController bc) {
		this.bc = bc;
	}

	@Override
	public void updatePokemon() {
		storage.updateNBT(currentPixelmon);
	}

	@Override
	public void update() {
		cam.updatePosition();
	}

	@Override
	public EntityLiving getEntity() {
		return player;
	}

	@Override
	public void updateOpponent(IBattleParticipant opponent) {
		PixelmonDataPacket p = new PixelmonDataPacket(opponent.currentPokemon(), EnumPackets.SetOpponent);
		player.playerNetServerHandler.sendPacketToPlayer(p.getPacket());
	}

	public void updateOpponentHealth(EntityPixelmon pixelmon) {
		PixelmonDataPacket p = new PixelmonDataPacket(pixelmon, EnumPackets.SetOpponent);
		player.playerNetServerHandler.sendPacketToPlayer(p.getPacket());
	}
}
