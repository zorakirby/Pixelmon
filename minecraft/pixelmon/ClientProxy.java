package pixelmon;

import java.util.Iterator;
import java.util.Map;

import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonEntityList;
import pixelmon.config.PixelmonEntityList.ClassType;
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
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {

		RenderingRegistry.registerEntityRenderingHandler(EntityPokeBall.class, new RenderPokeball());

		addPokemonRenderers();
	}
	
	@Override
	public void preloadTextures(){
		Iterator i = PixelmonEntityList.idToStringMapping.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry entry = (Map.Entry) i.next();
			String name = (String) entry.getValue();
			ClassType type = PixelmonEntityList.getClassTypeFromID((Integer) entry.getKey());
			if (type == ClassType.Pixelmon || type == ClassType.WaterPixelmon){
				MinecraftForgeClient.preloadTexture("/pixelmon/texture/pokemon-shiny/shiny" + name.toLowerCase() + ".png");
				MinecraftForgeClient.preloadTexture("/pixelmon/texture/pokemon/" + name.toLowerCase() + ".png");
			}
		}
	}

	private void addPokemonRenderers() {
		Iterator i = PixelmonEntityList.idToStringMapping.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry entry = (Map.Entry) i.next();
			String name = (String) entry.getValue();
			int id = (Integer) entry.getKey();
			ClassType type = PixelmonEntityList.getClassTypeFromID((Integer) entry.getKey());
			Class pokeClass = null;
			try {
				if (type == ClassType.Pixelmon || type == ClassType.WaterPixelmon)
					pokeClass = Class.forName("pixelmon.entities.pokemon.Entity" + name);
				else if (type == ClassType.Trainer)
					pokeClass = Class.forName("pixelmon.entities.trainers.EntityTrainer" + name);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			ModelBase model = null;
			try {
				Class<?> var3 = null;
				if (type == ClassType.Pixelmon || type == ClassType.WaterPixelmon)
					var3 = (Class<?>) Class.forName("pixelmon.models.pokemon.Model" + name);
				else if (type == ClassType.Trainer)
					var3 = (Class<?>) Class.forName("pixelmon.models.trainers.Model" + name);

				if (var3 != null) {
					model = (ModelBase) var3.getConstructor(new Class[] { }).newInstance(new Object[] {});
				}
			} catch (Exception var4) {
				var4.printStackTrace();
			}
			if (model == null)
				return;

			RenderLiving renderer;
			if (type == ClassType.Pixelmon)
				renderer = new RenderPixelmon(model, 0.5f);
			else if (type == ClassType.WaterPixelmon)
				renderer = new RenderFreeWaterPixelmon(model, 0.5f);
			else
				renderer = new RenderTrainer(model, 0.5f);

			RenderingRegistry.registerEntityRenderingHandler(pokeClass, renderer);
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
