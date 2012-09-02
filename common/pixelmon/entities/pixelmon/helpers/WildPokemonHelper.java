package pixelmon.entities.pixelmon.helpers;

import pixelmon.entities.pixelmon.EntityPixelmon;

import net.minecraft.src.World;

public class WildPokemonHelper {

	@SuppressWarnings("unchecked")
	public static IHaveHelper getCapturedPokemonEntity(String name, World world) {
		try 
		{
			Class<? extends EntityPixelmon> entity = (Class<? extends EntityPixelmon>) Class.forName("Pokemon.Entity" + name); 
			return  (IHaveHelper) entity.getConstructor(new Class[] { World.class })
					.newInstance(new Object[] { world });
		} catch (Exception e) 
		{
			System.err.println("Can not find class Entity" + name );
			e.printStackTrace();
		}
		return null;
	}

}
