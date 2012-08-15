package pixelmon;

import java.util.Iterator;
import java.util.Map;

import pixelmon.PixelmonEntityList.ClassType;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.enums.EnumGui;
import pixelmon.gui.ContainerEmpty;
import pixelmon.gui.GuiAttacking;
import pixelmon.gui.GuiChoosePokemon;
import pixelmon.gui.GuiChooseStarter;
import pixelmon.gui.GuiHealer;
import pixelmon.gui.GuiLearnMove;
import pixelmon.gui.GuiScreenPokeChecker;
import pixelmon.gui.pc.GuiPC;
import pixelmon.gui.pokedex.GuiPokedex;
import pixelmon.render.RenderFreeWaterPixelmon;
import pixelmon.render.RenderPixelmon;
import pixelmon.render.RenderPokeball;
import pixelmon.render.RenderTrainer;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderLiving;
import net.minecraft.src.World;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {

		RenderingRegistry.instance().registerEntityRenderingHandler(EntityPokeBall.class, new RenderPokeball());

		addPokemonRenderers();
	}

	private void addPokemonRenderers() {
		Iterator i = PixelmonEntityList.idToStringMapping.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry entry = (Map.Entry) i.next();
			String name = (String) entry.getValue();
			int id = (Integer) entry.getKey();
			Class pokeClass = null;
			try {
				pokeClass = Class.forName("pixelmon.entities.pokemon.Entity" + name);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			ModelBase model = null;
			try {

				Class<?> var3 = (Class<?>) Class.forName("pixelmon.models.pokemon.Model" + name);

				if (var3 != null) {
					model = (ModelBase) var3.getConstructor(new Class[] { World.class }).newInstance(new Object[] {});
				}
			} catch (Exception var4) {
				var4.printStackTrace();
			}
			if (model == null)
				return;

			RenderLiving renderer;
			ClassType ctype = PixelmonEntityList.getClassTypeFromID(id);
			if (ctype == ClassType.Pixelmon)
				renderer = new RenderPixelmon(model, 0.5f);
			else if (ctype == ClassType.WaterPixelmon)
				renderer = new RenderFreeWaterPixelmon(model, 0.5f);
			else
				renderer = new RenderTrainer(model, 0.5f);

			RenderingRegistry.instance().registerEntityRenderingHandler(pokeClass, renderer);
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == EnumGui.ChooseStarter.getIndex())
			return new GuiChooseStarter();
		else if (ID == EnumGui.LearnMove.getIndex())
			return new GuiLearnMove(x, player, DatabaseMoves.getAttack(y));
		else if (ID == EnumGui.ChooseAttack.getIndex()) {
			return new GuiAttacking(x, y, z);
		} else if (ID == EnumGui.ChoosePokemon.getIndex()) {
			PixelmonDataPacket p = ServerStorageDisplay.get(y);
			return new GuiChoosePokemon(p, x, null);
		} else if (ID == EnumGui.Pokedex.getIndex()) {
			return new GuiPokedex();
		} else if (ID == EnumGui.PC.getIndex()) {
			return new GuiPC();
		} else if (ID == EnumGui.Healer.getIndex()) {
			return new GuiHealer();
		} else if (ID == EnumGui.PokeChecker.getIndex()) {
			return new GuiScreenPokeChecker(ServerStorageDisplay.get(x));
		}
		return null;
	}
}
