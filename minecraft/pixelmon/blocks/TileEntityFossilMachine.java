package pixelmon.blocks;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import pixelmon.config.PixelmonItemsFossils;

public class TileEntityFossilMachine extends TileEntity {

	public int currentPokeball = -1;
	public int currentFossil = -1;
	public String currentPokeballTexture = "";
	public String currentPokemon = "";
	public boolean pokemonOccupied = false;
	public int fossilTicks = 0;
	public float fossilProgress = 0;
	public int fossilMaxProgress = 1600;
	public int pokemonTicks = 0;
	public float pokemonProgress = 0;
	public int pokemonMaxProgress = 3200;
	public int completionRate = ((int) (((fossilProgress + pokemonProgress) * 2) / 96));
	public float y;
	public boolean staticFlicker = false;
	public boolean isShiny = false;
	public int shinyChance = new Random().nextInt(8192);
	public float displayFlicker;
	public float displayFlicker2;
	public int dotTicks = 0;
	public String dots = "";

	public TileEntityFossilMachine() {
	}

	public boolean anyPlayerInRange() {
		return this.worldObj.getClosestPlayer((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D, (double) 16) != null;
	}

	public void updateEntity() {
		if (this.fossilProgress > 0 || this.pokemonProgress > 0) {
			completionRate = ((int) (((fossilProgress + pokemonProgress) * 2) / 96));
		}
		if (this.currentFossil != -1) {
			if (this.fossilTicks < 0)
				++fossilTicks;
			if (this.fossilTicks == 0 && fossilProgress != fossilMaxProgress) {
				fossilTicks = 0;
				fossilProgress++;
			} else if (fossilProgress >= fossilMaxProgress) {
				swapFossilForPokemon();
			}
		}
		if (pokemonOccupied) {
			if (this.pokemonTicks < 0)
				++pokemonTicks;
			if (this.pokemonTicks == 0 && pokemonProgress != pokemonMaxProgress) {
				pokemonTicks = 0;
				pokemonProgress++;
			}
		}

		if (this.dotTicks < 10) {
			dotTicks++;
		} else if (dots.length() <= 6) {
			this.dotTicks = 0;
			this.dots = dots + ".";
		}
		if (dots.length() >= 6) {
			this.dotTicks = 0;
			this.dots = "";
		}

		if (y == 0)
			y += 0.01;
		else
			y = 0;
		if (displayFlicker <= 0.8f && displayFlicker2 == 0) {
			this.staticFlicker = true;
			displayFlicker += 0.1f;
		}
		if (displayFlicker >= 0.8f) {
			displayFlicker2 += 0.1f;
		}
		if (displayFlicker2 >= 0.5f) {
			this.staticFlicker = false;
			displayFlicker = 0;
			displayFlicker2 = 0;
		}

		if (shinyChance == 1)
			this.isShiny = true;
		super.updateEntity();
	}

	public void swapFossilForPokemon() {
		currentPokemon = PixelmonItemsFossils.getFossilFromIndex(currentFossil).getPokemon();
		this.pokemonOccupied = true;
		this.currentFossil = -1;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("CurrentPokeball", currentPokeball);
		nbt.setInteger("CurrentFossil", currentFossil);
		nbt.setInteger("CompletionRate", completionRate);
		nbt.setFloat("PokemonProgress", pokemonProgress);
		nbt.setFloat("FossilProgress", fossilProgress);
		nbt.setString("CurrentPokemonTexture", currentPokeballTexture);
		nbt.setString("CurrentPokemon", currentPokemon);
		nbt.setBoolean("PokemonOccupied", pokemonOccupied);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		currentPokeball = nbt.getInteger("CurrentPokeball");
		currentFossil = nbt.getInteger("CurrentFossil");
		completionRate = nbt.getInteger("CompletionRate");
		fossilProgress = nbt.getFloat("FossilProgress");
		pokemonProgress = nbt.getFloat("PokemonProgress");
		currentPokeballTexture = nbt.getString("CurrentPokemonTexture");
		currentPokemon = nbt.getString("CurrentPokemon");
		pokemonOccupied = nbt.getBoolean("PokemonOccupied");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound var1 = new NBTTagCompound();
		this.writeToNBT(var1);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		readFromNBT(pkt.data);
	}

	public World getWorldObj() {
		return this.worldObj;
	}

}
