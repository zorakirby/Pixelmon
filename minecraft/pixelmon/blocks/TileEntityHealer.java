package pixelmon.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import pixelmon.Pixelmon;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumGui;
import pixelmon.enums.EnumPokeballs;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerStorage;

public class TileEntityHealer extends TileEntity {

	private static final int ticksToPlace = 15;
	private static final int ticksToHeal = 130;

	public boolean beingUsed = false;
	public EnumPokeballs pokeballType[] = new EnumPokeballs[6];

	private PlayerStorage storage;
	private EntityPlayer player;
	private int pokemonLastPlaced = -1;

	public void use(EntityPlayer player) {
		try {

			storage = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player);
			this.player = player;
			beingUsed = true;
			player.openGui(Pixelmon.instance, EnumGui.Healer.getIndex(), player.worldObj, 0, 0, 0);
			tickCount = 0;
			allPlaced = false;

			pokemonLastPlaced = -1;
			for (int i = 0; i < pokeballType.length; i++)
				pokeballType[i] = null;
			stayDark = false;
		} catch (Exception e) {
		}
	}

	private int tickCount = 0;

	public float rotation = 0;
	public int flashTimer = 0;

	public boolean allPlaced = false;
	public boolean stayDark = false;

	@Override
	public void updateEntity() {
		if (worldObj.isRemote) {
			rotation += 1.5f;
			flashTimer++;
			return;
		}
		if (beingUsed) {
			tickCount++;
			if (!allPlaced && tickCount == ticksToPlace) {
				boolean hasNextPokemon = false;
				for (int i = pokemonLastPlaced + 1; i < storage.partyPokemon.length; i++) {
					if (storage.partyPokemon[i] != null) {
						pokemonLastPlaced = i;
						hasNextPokemon = true;
						pokeballType[i] = EnumPokeballs.getFromIndex(storage.partyPokemon[i].getInteger("CaughtBall"));
						try {
							if (PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).EntityAlreadyExists(
									storage.partyPokemon[i].getInteger("pixelmonID"), player.worldObj)) {
								EntityPixelmon pixelmon = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).getAlreadyExists(
										storage.partyPokemon[i].getInteger("pixelmonID"), player.worldObj);
								PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).retrieve(pixelmon);
								pixelmon.catchInPokeball();
							}
						} catch (Exception e) {
						}
						break;
					}
				}
				if (!hasNextPokemon) {
					allPlaced = true;
				}
				tickCount = 0;
				MinecraftServer.getServer().getConfigurationManager().sendToAllNear(xCoord, yCoord, zCoord, 10, 0, getDescriptionPacket());

				// ((EntityPlayerMP)
				// player).playerNetServerHandler.sendPacketToPlayer(getDescriptionPacket());
			}
			if (tickCount == ticksToHeal - 30) {
				stayDark = true;
				MinecraftServer.getServer().getConfigurationManager().sendToAllNear(xCoord, yCoord, zCoord, 10, 0, getDescriptionPacket());
			}
			if (tickCount == ticksToHeal) {
				storage.healAllPokemon();
				beingUsed = false;
				MinecraftServer.getServer().getConfigurationManager().sendToAllNear(xCoord, yCoord, zCoord, 10, 0, getDescriptionPacket());
				player.closeScreen();
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		for (int i = 0; i < pokeballType.length; i++) {
			if (pokeballType[i] != null)
				nbt.setShort("PokeballType" + i, (short) pokeballType[i].getIndex());
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		for (int i = 0; i < pokeballType.length; i++) {
			pokeballType[i] = null;
			if (nbt.hasKey("PokeballType" + i))
				pokeballType[i] = EnumPokeballs.getFromIndex(nbt.getShort("PokeballType" + i));
		}
		if (nbt.hasKey("BeingUsed")) {
			beingUsed = nbt.getBoolean("BeingUsed");
			allPlaced = nbt.getBoolean("AllPlaced");
			stayDark = nbt.getBoolean("StayDark");
		}
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		nbt.setBoolean("BeingUsed", beingUsed);
		nbt.setBoolean("AllPlaced", allPlaced);
		nbt.setBoolean("StayDark", stayDark);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		readFromNBT(pkt.data);
	}

}
