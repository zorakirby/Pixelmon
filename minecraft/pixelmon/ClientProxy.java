package pixelmon;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityFX;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModelBase;
import net.minecraft.src.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import pixelmon.battles.animations.particles.EntityGastlyParticle;
import pixelmon.blocks.TileEntityAnvil;
import pixelmon.blocks.TileEntityHealer;
import pixelmon.blocks.TileEntityPC;
import pixelmon.blocks.apricornTrees.TileEntityApricornTree;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.entities.trainers.EntityTrainer;
import pixelmon.enums.EnumGui;
import pixelmon.enums.EnumPixelmonParticles;
import pixelmon.enums.EnumPokeballs;
import pixelmon.enums.EnumPokemon;
import pixelmon.gui.GuiAttacking;
import pixelmon.gui.GuiChoosePokemon;
import pixelmon.gui.GuiChooseStarter;
import pixelmon.gui.GuiHealer;
import pixelmon.gui.GuiLearnMove;
import pixelmon.gui.GuiPixelmonOverlay;
import pixelmon.gui.GuiScreenPokeChecker;
import pixelmon.gui.inventoryExtended.InventoryDetectionTickHandler;
import pixelmon.gui.pc.GuiPC;
import pixelmon.gui.pokedex.GuiPokedex;
import pixelmon.keybindings.MinimizeMaximizeOverlayKey;
import pixelmon.keybindings.NextPokemonKey;
import pixelmon.keybindings.PreviousPokemonKey;
import pixelmon.keybindings.SendPokemonKey;
import pixelmon.render.RenderPixelmon;
import pixelmon.render.RenderPokeball;
import pixelmon.render.RenderTileEntityAnvil;
import pixelmon.render.RenderTileEntityApricornTrees;
import pixelmon.render.RenderTileEntityHealer;
import pixelmon.render.RenderTileEntityPC;
import pixelmon.render.RenderTrainer;
import pixelmon.sounds.Sounds;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.registry.TickRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		for (EnumPokeballs p : EnumPokeballs.values()) {
			MinecraftForgeClient.preloadTexture("/pixelmon/texture/pokeballs/" + p.getTexture());
			MinecraftForgeClient.preloadTexture("/pixelmon/texture/pokeballs/" + p.getFlashRedTexture());
			MinecraftForgeClient.preloadTexture("/pixelmon/texture/pokeballs/" + p.getCaptureTexture());
		}
		RenderingRegistry.registerEntityRenderingHandler(EntityPokeBall.class, new RenderPokeball());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHealer.class, new RenderTileEntityHealer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPC.class, new RenderTileEntityPC());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityApricornTree.class, new RenderTileEntityApricornTrees());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAnvil.class, new RenderTileEntityAnvil());
		MinecraftForgeClient.preloadTexture("/pixelmon/image/pitems.png");
		addPokemonRenderers();
		MinecraftForge.EVENT_BUS.register(new GuiPixelmonOverlay());
	}

	@Override
	public World GetClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public void preloadTextures() {
		for (EnumPokemon pokemon : EnumPokemon.values())
			MinecraftForgeClient.preloadTexture("/pixelmon/texture/pokemon/" + pokemon.name.toLowerCase() + ".png");
	}

	@Override
	public void registerKeyBindings() {
		MinecraftForge.EVENT_BUS.register(this);

		KeyBindingRegistry.registerKeyBinding(new SendPokemonKey());
		KeyBindingRegistry.registerKeyBinding(new NextPokemonKey());
		KeyBindingRegistry.registerKeyBinding(new PreviousPokemonKey());
		KeyBindingRegistry.registerKeyBinding(new MinimizeMaximizeOverlayKey());
	}

	private void addPokemonRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityTrainer.class, new RenderTrainer(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPixelmon.class, new RenderPixelmon(0.5f));
	}

	public ModelBase loadModel(String name) {
		ModelBase model = null;
		try {
			Class<?> var3 = (Class<?>) Class.forName("pixelmon.models.pokemon.Model" + name);
			if (var3 != null) {
				model = (ModelBase) var3.getConstructor(new Class[] {}).newInstance(new Object[] {});
			}

		} catch (Exception e) {
			System.out.println("Can't find Model for " + name);
		}
		if (model == null)
			System.out.println("Can't find Model for " + name);
		return model;
	}

	public ModelBase getTrainerModel(String name) {
		ModelBase model = null;
		try {
			Class<?> var3 = (Class<?>) Class.forName("pixelmon.models.trainers.Model" + name);
			if (var3 != null) {
				model = (ModelBase) var3.getConstructor(new Class[] {}).newInstance(new Object[] {});
			}

		} catch (Exception e) {
			System.out.println("Can't find Model for " + name);
		}
		if (model == null)
			System.out.println("Can't find Model for " + name);
		return model;
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

	public static File getMinecraftDir() {
		return Minecraft.getMinecraftDir();
	}

	@ForgeSubscribe
	public void onWorldLoad(WorldEvent.Load event) {
		ServerStorageDisplay.clear();
		PixelmonServerStore.clearList();
	}

	public static void spawnParticle(EnumPixelmonParticles particle, World worldObj, double posX, double posY, double posZ, boolean isShiny) {
		try {
			EntityFX fx;
			if (particle.particleClass == EntityGastlyParticle.class)
				fx = new EntityGastlyParticle(worldObj, posX, posY, posZ, 0, 0, 0, isShiny);
			else
				fx = (EntityFX) particle.particleClass.getConstructor(World.class, double.class, double.class, double.class, double.class, double.class,
						double.class).newInstance(worldObj, posX, posY, posZ, 0d, 0d, 0d);
			Minecraft.getMinecraft().effectRenderer.addEffect(fx);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getTexture(String string, String string2) {
		return RenderingRegistry.addTextureOverride(string, string2);
	}
	
	@Override
	public void registerSounds() {
		Sounds.installSounds();
	}
	
	@Override
	public void registerTickHandlers() {
		TickRegistry.registerTickHandler(new InventoryDetectionTickHandler(), Side.CLIENT);
	}
}
