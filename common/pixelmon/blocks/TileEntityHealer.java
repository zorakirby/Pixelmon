package pixelmon.blocks;

import pixelmon.Pixelmon;
import pixelmon.enums.EnumGui;
import pixelmon.enums.EnumPokeballs;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerStorage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.INetworkManager;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet132TileEntityData;
import net.minecraft.src.TileEntity;
import net.minecraft.src.WorldServer;

public class TileEntityHealer extends TileEntity {

	private static final int ticksToPlace = 10;
	private static final int ticksToHeal = 100;

	public boolean beingUsed = false;
	public EnumPokeballs pokeballType[] = new EnumPokeballs[6];

	private PlayerStorage storage;
	private EntityPlayer player;
	private int pokemonLastPlaced = -1;

	public void use(EntityPlayer player) {
		this.player = player;
		beingUsed = true;
		player.openGui(Pixelmon.instance, EnumGui.Healer.getIndex(), player.worldObj, 0, 0, 0);
		tickCount = 0;
		allPlaced = false;
		storage = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player);
		pokemonLastPlaced = -1;
		for (int i = 0; i < pokeballType.length; i++)
			pokeballType[i] = null;
		stayDark = false;
	}

	private int tickCount = 0;

	public int rotation = 0;
	public int flashTimer = 0;

	public boolean allPlaced = false;
	public boolean stayDark = false;

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;
		if (beingUsed) {
			tickCount++;
			if (!allPlaced && tickCount == ticksToPlace) {
				boolean hasNextPokemon = false;
				for (int i = pokemonLastPlaced + 1; i < storage.partyPokemon.length; i++) {
					if (storage.partyPokemon[i] != null) {
						pokemonLastPlaced = i;
						hasNextPokemon = true;
						pokeballType[i] = EnumPokeballs.getFromIndex(storage.partyPokemon[i].getInteger("CaughtBall"));
						break;
					}
				}
				if (!hasNextPokemon) {
					allPlaced = true;
				}
				tickCount = 0;
				((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(getDescriptionPacket());
			}
			if (tickCount == ticksToHeal - 30) {
				stayDark = true;
				((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(getDescriptionPacket());
			}
			if (tickCount == ticksToHeal) {
				storage.healAllPokemon();
				beingUsed = false;
				((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(getDescriptionPacket());
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
		readFromNBT(pkt.customParam1);
	}

}
