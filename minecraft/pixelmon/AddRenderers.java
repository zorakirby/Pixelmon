package pixelmon;

import java.util.Map;

import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.models.ModelPC;
import pixelmon.render.RenderPokeball;

public class AddRenderers {
	public static void addRenderers(Map map)
	{
		map.put(EntityPokeBall.class, new RenderPokeball());
		PixelmonEntityList.addRenderer(map);
	}
}
