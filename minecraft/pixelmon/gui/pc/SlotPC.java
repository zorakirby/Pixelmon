package pixelmon.gui.pc;

import java.awt.Rectangle;

import pixelmon.comm.PixelmonDataPacket;


import net.minecraft.client.Minecraft;
import net.minecraft.src.NBTTagCompound;

public class SlotPC {

	public PixelmonDataPacket pokemonData;
	public int x, y;

	public SlotPC(int x, int y, PixelmonDataPacket pokemon) {
		this.pokemonData = pokemon;
		this.x = x;
		this.y = y;
	}

	public int getRenderInt() {
		if (pokemonData == null)
			return 0;
		String pokeNum = "";
		if (pokemonData.nationalPokedexNumber < 10)
			pokeNum = "00" + pokemonData.nationalPokedexNumber;
		else if (pokemonData.nationalPokedexNumber < 100)
			pokeNum = "0" + pokemonData.nationalPokedexNumber;
		else
			pokeNum = "" + pokemonData.nationalPokedexNumber;
		return Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/sprites/" + pokeNum);
	}

	public void clearPokemon() {
		pokemonData = null;
	}

	public void setPokemon(PixelmonDataPacket p) {
		pokemonData = p;
	}

	public void setXandY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 30, 30);
	}

}
