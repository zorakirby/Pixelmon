package net.minecraft.src;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pixelmon.*;
import pixelmon.Pokemon.EntityBulbasaur;
import pixelmon.Pokemon.EntityCharmander;
import pixelmon.Pokemon.EntityEevee;
import pixelmon.Pokemon.EntitySquirtle;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.attacks.Attack;
import pixelmon.blocks.BlockEvolutionStoneOre;
import pixelmon.blocks.BlockHealer;
import pixelmon.blocks.BlockPC;
import pixelmon.blocks.PixelmonBlocks;
import pixelmon.comm.PacketHandler;
import pixelmon.database.DatabaseHelper;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.*;
import pixelmon.entities.pokeballs.EntityPokeBall;
import pixelmon.enums.EnumEvolutionStone;
import pixelmon.gui.GuiHandler;
import pixelmon.items.ItemEvolutionStone;
import pixelmon.items.ItemPokedex;
import pixelmon.items.PixelmonItems;
import pixelmon.items.PixelmonRecipes;
import pixelmon.storage.*;
import pixelmon.storage.PokeballManager.PokeballManagerMode;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.ForgeHooksServer;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;
import net.minecraft.src.forge.Property;

import cpw.mods.fml.common.registry.FMLRegistry;
import cpw.mods.fml.server.FMLServerHandler;

public class mod_Pixelmon extends NetworkMod {
	static Configuration configuration = new Configuration(new File(FMLServerHandler.instance().getMinecraftRootDirectory(), "config/pixelmon.cfg"));
	public static boolean spawnSheep = configurationProperties();;
	public static boolean spawnCow;
	public static boolean spawnPig;
	public static boolean spawnChicken;
	public static boolean spawnOcelot;
	public static boolean spawnWolf;
	public static boolean spawnCaveSpider;
	public static boolean spawnCreeper;
	public static boolean spawnEnderman;
	public static boolean spawnGhast;
	public static boolean spawnGiantZombie;
	public static boolean spawnGolem;
	public static boolean spawnMooshroom;
	public static boolean spawnPigZombie;
	public static boolean spawnSilverFish;
	public static boolean spawnSkeleton;
	public static boolean spawnSlime;
	public static boolean spawnSpider;
	public static boolean spawnSquid;
	public static boolean spawnZombie;

	public static boolean isInMetric;
	public static boolean isGuiMinimized = false;
	public static int numGroundPokemon;
	public static int numWaterPokemon;
	public static int spawnFrequency;

	private static final List<Class> starterList = new ArrayList<Class>();

	
	public static final PokeballManager pokeballManager = new PokeballManager();
	public static final ComputerManager computerManager = new ComputerManager();

	/*
	 * public static final String BLACK = "/2470", DARKBLUE = "/2471", DARKGREEN
	 * = "/2472", DARKAQUA = "/2473", DARKRED = "/2474", PURPLE = "/2475", GOLD
	 * = "/2476", GRAY = "/2477", DARKGRAY = "/2478", INDIGO = "/2479",
	 * BRIGHTGREEN = "/247a", AQUA = "/247b", RED = "/247c", PINK = "/247d",
	 * YELLOW = "/247e", WHITE = "/247f", RANDOM = "/247k";
	 */

	@Override
	public boolean clientSideRequired() {
		return true;
	}

	public String getVersion() {
		return "version 1.5 for 1.2.5";
	}

	private static boolean configurationProperties() {
		configuration.load();
		PixelmonBlocks.load(configuration);

		IDListPixelmon.load(configuration);
		IDListTrainer.load(configuration);
		

		isInMetric = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("Metric", "Pokedex", true)).value);

		spawnCaveSpider = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnCaveSpider", "Vanilla Spawns", false)).value);
		spawnChicken = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnChicken", "Vanilla Spawns", false)).value);
		spawnCow = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnCow", "Vanilla Spawns", false)).value);
		spawnCreeper = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnCreeper", "Vanilla Spawns", false)).value);
		spawnEnderman = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnEnderman", "Vanilla Spawns", false)).value);
		spawnGhast = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnGhast", "Vanilla Spawns", false)).value);
		spawnGiantZombie = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnGiantZombie", "Vanilla Spawns", false)).value);
		spawnGolem = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnGolem", "Vanilla Spawns", false)).value);
		spawnMooshroom = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnMooshroom", "Vanilla Spawns", false)).value);
		spawnOcelot = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnOcelot", "Vanilla Spawns", false)).value);
		spawnPig = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnPig", "Vanilla Spawns", false)).value);
		spawnPigZombie = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnPigZombie", "Vanilla Spawns", false)).value);
		spawnSheep = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnSheep", "Vanilla Spawns", false)).value);
		spawnSilverFish = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnSilverFish", "Vanilla Spawns", false)).value);
		spawnSkeleton = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnSkeleton", "Vanilla Spawns", false)).value);
		spawnSlime = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnSlime", "Vanilla Spawns", false)).value);
		spawnSpider = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnSpider", "Vanilla Spawns", false)).value);
		spawnSquid = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnSquid", "Vanilla Spawns", false)).value);
		spawnWolf = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnWolf", "Vanilla Spawns", false)).value);
		spawnZombie = Boolean.parseBoolean((configuration.getOrCreateBooleanProperty("SpawnZombie", "Vanilla Spawns", false)).value);

		numGroundPokemon = Integer.parseInt((configuration.getOrCreateIntProperty("NumberNonWaterPixelmonSpawns", "Overall Spawning settings", 100)).value);
		numWaterPokemon = Integer.parseInt((configuration.getOrCreateIntProperty("NumberWaterPixelmonSpawns", "Overall Spawning settings", 100)).value);
		spawnFrequency = Integer.parseInt((configuration.getOrCreateIntProperty("SpawnFrequency", "Overall Spawning settings", 10)).value);
		configuration.save();
		return spawnSheep;
	}

	public static void savePokedexProps() {
		Property p = configuration.getOrCreateBooleanProperty("Metric", "Pokedex", true);
		p.value = String.valueOf(isInMetric);
		configuration.save();
	}

	public static void setPokedexProp(int i, boolean b) {
		if (i == 1)
			isInMetric = b;
	}

	private static boolean alreadySet = false;

	public void load() {
		if (!DatabaseHelper.has()) {
			ModLoader.throwException("Can not start Pixelmon without SQLite jar or database!!! Please reinstall!!", new java.lang.Error(
					"Can not start Pixelmon without SQLite jar or database!!! Please reinstall!!"));
		}
		if (!(ModLoader.isModLoaded("mod_MinecraftForge")))
			ModLoader.throwException("Can not start Pixelmon without Minecraft Forge!!! Please download it!!!", new java.lang.Error(
					"Can not start Pixelmon without Minecraft Forge!!! Please download it!!!"));
		if (ModLoader.isModLoaded("mod_pokemobs"))
			System.exit(1);
		instance = this;

		MinecraftForge.setGuiHandler(this, new GuiHandler());
		
		ModLoader.setInGUIHook(this, true, true);
		ModLoader.setInGameHook(this, true, true);
		starterList.add(EntityBulbasaur.class);
		starterList.add(EntitySquirtle.class);
		starterList.add(EntityCharmander.class);
		starterList.add(EntityEevee.class);
		addNames();
		registerEntities();
		addSpawns();
		addRecipes();
		MinecraftForge.registerSaveHandler(pokeballManager);
		MinecraftForge.registerSaveHandler(computerManager);
		MinecraftForge.registerConnectionHandler(new PacketHandler());
		
		alreadySet = true;
	}

	public static int pcFront = ModLoader.addOverride("/terrain.png", "/pixelmon/block/PcFrontInactive.png");

	public void addNames() {
		// ModLoader.addName(pokeBall2, "PokeBall2");
		PixelmonItems.addNames();
		PixelmonBlocks.setTextureIds();
		PixelmonBlocks.registerBlocks();
		PixelmonBlocks.addNames();
	}

	public void registerEntities() {
		removeNormalMobsAndCreatures();
		PixelmonEntityList.registerEntities();
		MinecraftForge.registerEntity(EntityPokeBall.class, this, IDListPixelmon.i, 50, 1, true); IDListPixelmon.i++;
	}

	public void addSpawns() {
		PixelmonSpawner spawner = new PixelmonSpawner();
		spawner.init();

		// ModLoader.addSpawn(EntityTrainer.class, 150, 1, 1,
		// EnumCreatureType.creature, new BiomeGenBase[] {
		// BiomeGenBase.desert, BiomeGenBase.desertHills,
		// BiomeGenBase.extremeHills,
		// BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest,
		// BiomeGenBase.forestHills, BiomeGenBase.frozenOcean,
		// BiomeGenBase.frozenRiver, BiomeGenBase.iceMountains,
		// BiomeGenBase.icePlains, BiomeGenBase.jungle,
		// BiomeGenBase.jungleHills, BiomeGenBase.plains,
		// BiomeGenBase.river, BiomeGenBase.swampland,
		// BiomeGenBase.taiga, BiomeGenBase.taigaHills });

	}

	public void addRecipes() {
		PixelmonRecipes.addRecipes();

	}

	private void removeNormalMobsAndCreatures() {
		BiomeGenBase[] allbiomes = new BiomeGenBase[] { BiomeGenBase.beach, BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.extremeHills,
				BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver,
				BiomeGenBase.iceMountains, BiomeGenBase.icePlains, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.mushroomIsland,
				BiomeGenBase.mushroomIslandShore, BiomeGenBase.ocean, BiomeGenBase.plains, BiomeGenBase.river, BiomeGenBase.sky, BiomeGenBase.swampland,
				BiomeGenBase.taiga, BiomeGenBase.taigaHills };
		if (!spawnSheep)
			FMLRegistry.removeSpawn(EntitySheep.class, EnumCreatureType.creature, allbiomes);
		if (!spawnCow)
			FMLRegistry.removeSpawn(EntityCow.class, EnumCreatureType.creature, allbiomes);
		if (!spawnPig)
			FMLRegistry.removeSpawn(EntityPig.class, EnumCreatureType.creature, allbiomes);
		if (!spawnChicken)
			FMLRegistry.removeSpawn(EntityChicken.class, EnumCreatureType.creature, allbiomes);
		if (!spawnOcelot)
			FMLRegistry.removeSpawn(EntityOcelot.class, EnumCreatureType.monster, allbiomes);
		if (!spawnWolf)
			FMLRegistry.removeSpawn(EntityWolf.class, EnumCreatureType.creature, allbiomes);
		if (!spawnCaveSpider)
			FMLRegistry.removeSpawn(EntityCaveSpider.class, EnumCreatureType.monster, allbiomes);
		if (!spawnCreeper)
			FMLRegistry.removeSpawn(EntityCreeper.class, EnumCreatureType.monster, allbiomes);
		if (!spawnEnderman)
			FMLRegistry.removeSpawn(EntityEnderman.class, EnumCreatureType.monster, allbiomes);
		if (!spawnGhast)
			FMLRegistry.removeSpawn(EntityGhast.class, EnumCreatureType.monster, allbiomes);
		if (!spawnGiantZombie)
			FMLRegistry.removeSpawn(EntityGiantZombie.class, EnumCreatureType.monster, allbiomes);
		if (!spawnGolem)
			FMLRegistry.removeSpawn(EntityGolem.class, EnumCreatureType.monster, allbiomes);
		if (!spawnMooshroom)
			FMLRegistry.removeSpawn(EntityMooshroom.class, EnumCreatureType.creature, allbiomes);
		if (!spawnPigZombie)
			FMLRegistry.removeSpawn(EntityPigZombie.class, EnumCreatureType.monster, allbiomes);
		if (!spawnSilverFish)
			FMLRegistry.removeSpawn(EntitySilverfish.class, EnumCreatureType.monster, allbiomes);
		if (!spawnSkeleton)
			FMLRegistry.removeSpawn(EntitySkeleton.class, EnumCreatureType.monster, allbiomes);
		if (!spawnSlime)
			FMLRegistry.removeSpawn(EntitySlime.class, EnumCreatureType.monster, allbiomes);
		if (!spawnSpider)
			FMLRegistry.removeSpawn(EntitySpider.class, EnumCreatureType.monster, allbiomes);
		if (!spawnSquid)
			FMLRegistry.removeSpawn(EntitySquid.class, EnumCreatureType.waterCreature, allbiomes);
		if (!spawnZombie)
			FMLRegistry.removeSpawn(EntityZombie.class, EnumCreatureType.monster, allbiomes);
	}

	public int addFuel(int i, int i1) {
		if (i == PixelmonItems.coalDust.shiftedIndex) {
			return 400;
		}
		return 0;
	}

	public boolean dispenseEntity(World world, double d, double d1, double d2, int i, int j, ItemStack itemstack) {
		return false;
	}

	public static int getRandomNumberBetween(int i, int i1) {
		return getRandomNumberBetween(i, i1, true);
	}

	public static int getRandomNumberBetween(int i, int i1, boolean flag) {
		Random rand = new Random();
		int number = 0;
		for (int i2 = -1; !(i2 >= i && i2 <= i1);) {
			i2 = number = rand.nextInt(i1 + 1);
		}
		if (!flag)
			number *= -1;
		return number;
	}

	static float spinCount = 0;
	public static NetworkMod instance;
	public static BattleRegistry battleRegistry = new BattleRegistry();

	@Override
	public void generateSurface(World world, Random rand, int x, int z) {
		// thunderstone ore
		for (int i = 0; i < 30; i++) {
			int xPos = rand.nextInt(16) + x;
			int zPos = rand.nextInt(16) + z;
			int yPos = rand.nextInt(50) + 75; // generates 75 to 125
			new WorldGenMinable(PixelmonBlocks.thunderStoneOre.blockID, 2 + rand.nextInt(2)).generate(world, rand, xPos, yPos, zPos);
		}

		// leafstone ore
		for (int i = 0; i < 20; i++) {
			int xPos = rand.nextInt(16) + x;
			int zPos = rand.nextInt(16) + z;
			int yPos = rand.nextInt(100) + 28;
			new WorldGenLeafStoneOre().generate(world, rand, xPos, yPos, zPos);
		}
		
		for (int i = 0; i < 10; i++) {
			int xPos = rand.nextInt(16) + x;
			int zPos = rand.nextInt(16) + z;
			int yPos = rand.nextInt(40) + 40;
			new WorldGenWaterStoneOre().generate(world, rand, xPos, yPos, zPos);
		}
	}
	
	@Override
	public boolean onTickInGUI(float tick, Object game, Object gui) {
		// TODO Auto-generated method stub
		return super.onTickInGUI(tick, game, gui);
	}
	
	@Override
	public boolean onTickInGame(MinecraftServer minecraftServer)
    {
        return false;
    }
}
