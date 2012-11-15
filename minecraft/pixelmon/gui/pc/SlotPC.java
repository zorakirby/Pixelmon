package pixelmon.gui.pc;

import java.awt.Rectangle;

import pixelmon.comm.PixelmonDataPacket;


import net.minecraft.client.Minecraft;
import net.minecraft.src.NBTTagCompound;

public class SlotPC {

	public PixelmonDataPacket pokemonData;
	public int x, y, swidth;

	public SlotPC(int x, int y, PixelmonDataPacket pokemon) {
		this.pokemonData = pokemon;
		this.x = x;
		this.y = y;
		swidth = 30;
	}

	public int getRenderInt() {
		if (pokemonData == null)
			return 0;
		String pokeNum = "";
		if (pokemonData.getNationalPokedexNumber() < 10)
			pokeNum = "00" + pokemonData.getNationalPokedexNumber();
		else if (pokemonData.getNationalPokedexNumber() < 100)
			pokeNum = "0" + pokemonData.getNationalPokedexNumber();
		else
			pokeNum = "" + pokemonData.getNationalPokedexNumber();
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
		return new Rectangle(x, y, swidth, swidth);
	}

}
