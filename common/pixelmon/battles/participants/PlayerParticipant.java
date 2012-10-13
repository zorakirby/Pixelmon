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
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumGui;
import pixelmon.storage.PixelmonStorage;

public class PlayerParticipant implements IBattleParticipant {
	public EntityPlayerMP player;
	EntityPixelmon currentPixelmon;
	BattleController bc;

	public PlayerParticipant(EntityPlayerMP p, EntityPixelmon firstPixelmon) {
		player = p;
		currentPixelmon = firstPixelmon;
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
		if (PixelmonStorage.PokeballManager.getPlayerStorage(player).countAblePokemon() > 0)
			return true;
		return false;
	}

	@Override
	public void EndBattle(boolean didWin, IBattleParticipant foe) {
		currentPixelmon.battleStats.clearBattleStats();
		currentPixelmon.EndBattle();
		((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.ClearTempStore, 0));
	}

	@Override
	public void getNextPokemon() {
		player.openGui(Pixelmon.instance, EnumGui.ChoosePokemon.getIndex(), player.worldObj, BattleRegistry.getIndex(bc), currentPokemon().getPokemonId(), 0);
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
		if (bc==null) return null;
		if (currentPixelmon.moveset.size()==0) {
			bc.endBattle(false);
			return null;
		}
		((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(PacketCreator.createPacket(EnumPackets.ClearTempStore, 0));
		int x = 0, y = 0;
		x = currentPokemon().getPokemonId();
		PixelmonDataPacket p = new PixelmonDataPacket(participant2.currentPokemon(), EnumPackets.AddToTempStore);
		y = 0;
		player.playerNetServerHandler.sendPacketToPlayer(p.getPacket());

		bc.waitForMove(this);
		player.openGui(Pixelmon.instance, EnumGui.ChooseAttack.getIndex(), player.worldObj, x, y, BattleRegistry.getIndex(bc));
		return null;
	}

	@Override
	public void switchPokemon(IBattleParticipant participant2, int newPixelmonId) {
		currentPixelmon.battleStats.clearBattleStats();
		ChatHandler.sendChat(player, participant2.currentPokemon().getOwner(), "That's enough " + currentPixelmon.getNickname() + "!");
		currentPixelmon.catchInPokeball();
		PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) currentPixelmon.getOwner()).retrieve(currentPixelmon);

		if (PixelmonStorage.PokeballManager.getPlayerStorage(player).EntityAlreadyExists(newPixelmonId, player.worldObj)) {
			EntityPixelmon oldPokemon = PixelmonStorage.PokeballManager.getPlayerStorage(player).getAlreadyExists(newPixelmonId, player.worldObj);
			oldPokemon.catchInPokeball();
			PixelmonStorage.PokeballManager.getPlayerStorage(player).retrieve(oldPokemon);
		}

		EntityPixelmon newPixelmon = PixelmonStorage.PokeballManager.getPlayerStorage(player).sendOut(newPixelmonId, currentPixelmon.getOwner().worldObj);
		newPixelmon.setLocationAndAngles(currentPixelmon.posX, currentPixelmon.posY, currentPixelmon.posZ, currentPixelmon.rotationYaw, 0.0F);
		newPixelmon.motionX = newPixelmon.motionY = newPixelmon.motionZ = 0;
		newPixelmon.releaseFromPokeball();

		ChatHandler.sendChat(player, participant2.currentPokemon().getOwner(), "Go " + newPixelmon.getNickname() + "!");
		currentPixelmon = newPixelmon;
	}

	@Override
	public boolean checkPokemon() {
		for (NBTTagCompound n : PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) currentPokemon().getOwner()).partyPokemon) {
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
		PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) currentPixelmon.getOwner()).getNBT(currentPixelmon.getPokemonId()).setBoolean("IsFainted", true);
	}
}
