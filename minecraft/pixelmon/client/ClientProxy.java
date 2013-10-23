package pixelmon.client;

import java.io.File;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import pixelmon.CommonProxy;
import pixelmon.battles.animations.particles.EntityGastlyParticle;
import pixelmon.blocks.TileEntityAnvil;
import pixelmon.blocks.TileEntityEvolutionRock;
import pixelmon.blocks.TileEntityFossilCleaner;
import pixelmon.blocks.TileEntityFossilMachine;
import pixelmon.blocks.TileEntityHealer;
import pixelmon.blocks.TileEntityPC;
import pixelmon.blocks.TileEntityShrine;
import pixelmon.blocks.TileEntityTradeMachine;
import pixelmon.blocks.apricornTrees.TileEntityApricornTree;
import pixelmon.client.camera.EntityCamera;
import pixelmon.client.gui.GuiDoctor;
import pixelmon.client.gui.GuiEvolve;
import pixelmon.client.gui.GuiHealer;
import pixelmon.client.gui.GuiItemDrops;
import pixelmon.client.gui.GuiPixelmonOverlay;
import pixelmon.client.gui.GuiTrading;
import pixelmon.client.gui.battles.GuiAcceptDeny;
import pixelmon.blocks.decorative.BlockContainerPlus;
import pixelmon.blocks.decorative.BlockUnown;
import pixelmon.blocks.decorative.TileEntityDecorativeBase;
import pixelmon.client.gui.*;
import pixelmon.client.gui.battles.GuiBattle;
import pixelmon.client.gui.inventoryExtended.InventoryDetectionTickHandler;
import pixelmon.client.gui.pc.GuiPC;
import pixelmon.client.gui.pokechecker.GuiScreenPokeChecker;
import pixelmon.client.gui.pokechecker.GuiScreenPokeCheckerMoves;
import pixelmon.client.gui.pokechecker.GuiScreenPokeCheckerStats;
import pixelmon.client.gui.pokedex.GuiPokedex;
import pixelmon.client.gui.starter.GuiChooseStarter;
import pixelmon.client.keybindings.MinimizeMaximizeOverlayKey;
import pixelmon.client.keybindings.MovementHandler;
import pixelmon.client.keybindings.NextPokemonKey;
import pixelmon.client.keybindings.PreviousPokemonKey;
import pixelmon.client.keybindings.RidingBindings;
import pixelmon.client.keybindings.SendPokemonKey;
import pixelmon.client.materials.Cubemap;
import pixelmon.client.models.fossils.ModelFossil;
import pixelmon.client.render.RenderHook;
import pixelmon.client.render.RenderPixelmon;
import pixelmon.client.render.RenderPokeball;
import pixelmon.client.render.RenderTrainer;
import pixelmon.client.render.tileEntities.RenderTileEntityShrine;
import pixelmon.client.render.tileEntities.RenderTileEntityAnvil;
import pixelmon.client.render.tileEntities.RenderTileEntityApricornTrees;
import pixelmon.client.render.tileEntities.RenderTileEntityEvolutionRock;
import pixelmon.client.render.tileEntities.RenderTileEntityHealer;
import pixelmon.client.render.tileEntities.RenderTileEntityPC;
import pixelmon.client.render.tileEntities.RenderTileEntityTradingMachine;
import pixelmon.client.render.tileEntities.RenderTileFossilCleaner;
import pixelmon.client.render.tileEntities.RenderTileFossilMachine;

import pixelmon.client.render.RenderPixelmon;
import pixelmon.client.render.RenderPokeball;
import pixelmon.client.render.RenderTrainer;
import pixelmon.client.render.tileEntities.RenderTileEntityAnvil;
import pixelmon.client.render.tileEntities.RenderTileEntityApricornTrees;
import pixelmon.client.render.tileEntities.RenderTileEntityDecorativeBase;
import pixelmon.client.render.tileEntities.RenderTileEntityHealer;
import pixelmon.client.render.tileEntities.RenderTileEntityPC;
import pixelmon.client.render.tileEntities.RenderTileEntityTradingMachine;
import pixelmon.client.render.tileEntities.RenderTileFossilCleaner;
import pixelmon.client.render.tileEntities.RenderTileFossilMachine;
import pixelmon.client.render.*;
import pixelmon.client.render.tileEntities.*;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.npcs.EntityTrainer;
import pixelmon.entities.npcs.NPCType;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.entities.projectiles.EntityHook;
import pixelmon.enums.EnumCustomModel;
import pixelmon.enums.EnumGui;
import pixelmon.enums.EnumPixelmonParticles;
import pixelmon.items.ItemRendererExperience;
import pixelmon.sounds.Sounds;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		EnumCustomModel.preloadModels();
		
		RenderingRegistry.registerEntityRenderingHandler(EntityPokeBall.class, new RenderPokeball());
		RenderingRegistry.registerEntityRenderingHandler(EntityHook.class, new RenderHook());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHealer.class, new RenderTileEntityHealer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPC.class, new RenderTileEntityPC());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityApricornTree.class, new RenderTileEntityApricornTrees());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAnvil.class, new RenderTileEntityAnvil());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFossilMachine.class, new RenderTileFossilMachine());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFossilCleaner.class, new RenderTileFossilCleaner());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTradeMachine.class, new RenderTileEntityTradingMachine());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEvolutionRock.class, new RenderTileEntityEvolutionRock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShrine.class, new RenderTileEntityShrine());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecorativeBase.class, new RenderTileEntityDecorativeBase());
		//MinecraftForgeClient.registerItemRenderer(PixelmonItems.unoOrb.itemID, new ItemRendererExperience());
		//MinecraftForgeClient.registerItemRenderer(PixelmonItems.dosOrb.itemID, new ItemRendererExperience());
		//MinecraftForgeClient.registerItemRenderer(PixelmonItems.tresOrb.itemID, new ItemRendererExperience());
		addPokemonRenderers();
		MinecraftForge.EVENT_BUS.register(new GuiPixelmonOverlay());
		RenderingRegistry.registerBlockHandler(BlockContainerPlus.renderingID, RenderTileEntityDecorativeBase.INSTANCE);
		Cubemap.preloadCubemaps();
	}

	@Override
	public World GetClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public void registerKeyBindings() {
		MinecraftForge.EVENT_BUS.register(this);

		KeyBindingRegistry.registerKeyBinding(new SendPokemonKey());
		KeyBindingRegistry.registerKeyBinding(new NextPokemonKey());
		KeyBindingRegistry.registerKeyBinding(new PreviousPokemonKey());
		KeyBindingRegistry.registerKeyBinding(new MinimizeMaximizeOverlayKey());
		KeyBindingRegistry.registerKeyBinding(new RidingBindings());
		TickRegistry.registerTickHandler(new MovementHandler(), Side.CLIENT);
	}

	private void addPokemonRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityTrainer.class, new RenderTrainer(0.5f));
		System.out.println("Binding Renderer");
		RenderingRegistry.registerEntityRenderingHandler(EntityPixelmon.class, new RenderPixelmon(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityCamera.class, new RenderInvisible());
		// RenderingRegistry.registerEntityRenderingHandler(EntityDoctor.class,
		// new RenderDoctor(0.5f));
	}

	public static ArrayList<String> modelPaths = new ArrayList<String>();
	static {
		modelPaths.add("pixelmon.client.models.pokemon");
	}

	public ModelBase loadModel(String name) {
		ModelBase model = null;
		for (String path : modelPaths) {
			try {
				Class<?> var3 = (Class<?>) Class.forName(path + ".Model" + name);
				try {
					if (var3 != null) {
						model = (ModelBase) var3.getConstructor(new Class[] {}).newInstance(new Object[] {});
						break;
					}
				} catch (Exception e) {
					if (PixelmonConfig.printErrors) {
						System.out.println("Failed to construct model for " + name);
					}
					e.printStackTrace();
					
				}
			} catch (Exception e) {
				if (PixelmonConfig.printErrors) {
					e.printStackTrace();
				}
			}
		}

		if (model == null)
			if (PixelmonConfig.printErrors)
				System.out.println("Can't find model for " + name);
		return model;
	}

	@Override
	public ModelBase loadFlyingModel(String name) {
		ModelBase model = null;
		for (String path : modelPaths) {
			try {
				Class<?> var3 = (Class<?>) Class.forName(path + ".flying" + ".Model" + name);
				try {
					if (var3 != null) {
						model = (ModelBase) var3.getConstructor(new Class[] {}).newInstance(new Object[] {});
						break;
					}
				} catch (Exception e) {
				}
			} catch (Exception e) {
			}
		}

		return model;
	}

	@Override
	public ModelFossil loadFossilModel(String name) {
		ModelFossil model = null;
		try {
			Class<?> var3 = (Class<?>) Class.forName("pixelmon.client.models.fossils.Model" + name);
			if (var3 != null) {
				model = (ModelFossil) var3.getConstructor(new Class[] {}).newInstance(new Object[] {});
			}

		} catch (Exception e) {
			if (PixelmonConfig.printErrors)
				System.out.println("Can't find Model for " + name);
		}
		if (model == null)
			if (PixelmonConfig.printErrors)
				System.out.println("Can't find Model for " + name);
		return model;
	}

	@Override
	public ModelBase getNPCModel(NPCType type, String name) {
		ModelBase model = null;
		try {
			Class<?> var3 = (Class<?>) Class.forName("pixelmon.client.models." + type.textureDirectory + ".Model" + name);
			if (var3 != null) {
				model = (ModelBase) var3.getConstructor(new Class[] {}).newInstance(new Object[] {});
			}

		} catch (Exception e) {
			if (PixelmonConfig.printErrors) {
				System.out.println("Error in Model for " + name);
				System.out.println(e.getMessage());
			}
			return null;
		}
		if (model == null)
			if (PixelmonConfig.printErrors)
				System.out.println("Can't find Model for " + name);
		return model;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == EnumGui.ChooseStarter.getIndex())
			return new GuiChooseStarter();
		else if (ID == EnumGui.LearnMove.getIndex())
			return new GuiBattle();
		else if (ID == EnumGui.LevelUp.getIndex())
			return new GuiBattle();
		else if (ID == EnumGui.Battle.getIndex())
			return new GuiBattle(x);
		else if (ID == EnumGui.Pokedex.getIndex())
			return new GuiPokedex(player.username, x);
		else if (ID == EnumGui.PC.getIndex())
			return new GuiPC();
		else if (ID == EnumGui.Healer.getIndex())
			return new GuiHealer();
		else if (ID == EnumGui.PokeChecker.getIndex())
			return new GuiScreenPokeChecker(ServerStorageDisplay.get(x), false);
		else if (ID == EnumGui.PokeCheckerStats.getIndex())
			return new GuiScreenPokeCheckerStats(ServerStorageDisplay.get(x), false);
		else if (ID == EnumGui.PokeCheckerMoves.getIndex())
			return new GuiScreenPokeCheckerMoves(ServerStorageDisplay.get(x), false);
		else if (ID == EnumGui.Trading.getIndex())
			return new GuiTrading(x);
		else if (ID == EnumGui.Doctor.getIndex())
			return new GuiDoctor();
		else if (ID == EnumGui.AcceptDeny.getIndex())
			return new GuiAcceptDeny(x);
		else if (ID == EnumGui.Evolution.getIndex())
			return new GuiEvolve(x);
		else if (ID == EnumGui.ItemDrops.getIndex())
			return new GuiItemDrops();
		return null;
	}

	public static File getMinecraftDir() {
		return Minecraft.getMinecraft().mcDataDir;
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
	public void registerSounds() {
		Sounds.installSounds();
	}

	@Override
	public void registerTickHandlers() {
		TickRegistry.registerTickHandler(new InventoryDetectionTickHandler(), Side.CLIENT);
	}

	public Object[] models = new Object[650];
	public Object[] flyingModels = new Object[650];

	@Override
	public Object[] getModels() {
		return models;
	}

	@Override
	public Object[] getFlyingModels() {
		return flyingModels;
	}
}
