package pixelmon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import net.minecraft.src.World;
import pixelmon.battles.attacks.Attack;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pokemon.EntityBulbasaur;
import pixelmon.entities.pokemon.EntityCharmander;
import pixelmon.entities.pokemon.EntityEevee;
import pixelmon.entities.pokemon.EntitySquirtle;

public class StarterList {
	private static String[] starterList = new String[]{"Bulbasaur", "Squirtle", "Charmander", "Eevee"};
	public static String[] getStarterStringList(){
		return starterList;
	}
}
