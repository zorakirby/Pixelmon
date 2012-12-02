package pixelmon.pokedex;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.World;
import pixelmon.entities.EntityQuestionMarks;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPokemon;

public class PokedexEntry {

	public final String name, description;
	public final int natPokedexNum;
	public final String heightM, weightM, heightC, weightC;

	public PokedexEntry(int i, String n, String d, float w, float h) {
		natPokedexNum = i;
		name = n;
		description = d;
		heightM = h + " m";
		weightM = w + " kg";
		float hc = h * 3.2808399F;
		if (hc == 0)
			heightC = "??? ft";
		else {
			// String[] sp = (String.format("%f", hc)).split(".");
			String s = (hc + "");
			int i1 = s.indexOf('.');
			String feet = s.substring(0, i1);
			float inches = Float.parseFloat(0 + s.substring(i1)) * 12;
			int in = Math.round(inches);
			heightC = feet + "\'" + in + "\"";
		}
		float wc = w * 2.20462262F;
		weightC = (wc == 0 ? "???" : (Math.round(wc))) + " lbs";
	}

	public String getPokedexDisplayNumber() {
		String s = "" + natPokedexNum;
		while (s.length() < 3)
			s = "0" + s;
		return s;
	}

	private EntityPixelmon renderTarget;

	public EntityPixelmon getRenderTarget(World w) {
		if (renderTarget != null && w == renderTarget.worldObj)
			return renderTarget;
		if (name != null && !name.equals("???") && EnumPokemon.hasPokemon(name)) {
			renderTarget = new EntityPixelmon(w);
			renderTarget.init(name);
		} else
			return null;
		return renderTarget;
	}

	public String toString() {
		return getPokedexDisplayNumber() + " " + name;
	}
}