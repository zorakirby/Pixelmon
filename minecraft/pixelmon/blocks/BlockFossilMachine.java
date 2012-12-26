package pixelmon.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import pixelmon.config.PixelmonEntityList;
import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsFossils;
import pixelmon.config.PixelmonItemsPokeballs;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pokeballs.PokeballTypeHelper;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumTrainers;
import pixelmon.items.ItemPokeBall;
import pixelmon.items.ItemPokemonFossil;
import pixelmon.storage.PixelmonStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFossilMachine extends BlockContainer {

	public BlockFossilMachine(int i) {
		super(i, Material.iron);
		setHardness(1f);
	}

	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(PixelmonItemsFossils.fossilMachineItem, 1);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBounds(par1World.getBlockMetadata(par2, par3, par4));
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns the bounding box of the wired rectangular prism to render.
	 */
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBounds(par1World.getBlockMetadata(par2, par3, par4));
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		TileEntityFossilMachine tile = ((TileEntityFossilMachine) par1World.getBlockTileEntity(par2, par3, par4));
		int var6 = par1World.getBlockMetadata(par2, par3, par4);
		double var7 = (double) ((float) par2 + 0.05);
		double var9 = (double) ((float) par3 + 0.5F);
		double var11 = (double) ((float) par4);
		double var15 = 0.27000001072883606D;

		if (tile.fossilProgress > 0 || tile.pokemonProgress > 0)
			for (int i = 50; i > ((int) (((tile.fossilProgress + tile.pokemonProgress) * 2) / 96) / 2); i--)
				par1World.spawnParticle("reddust", var7 + par5Random.nextFloat(), var9 + par5Random.nextFloat() + par5Random.nextFloat(), var11 + par5Random.nextFloat(), -255D,
						1.0D, 255.0D);

		if (tile.fossilProgress > 1 && tile.pokemonProgress < tile.pokemonMaxProgress) {
			par1World.playSound((double) par2 + 0.5D, (double) par3 + 0.5D, (double) par4 + 0.5D, "portal.portal", 0.01F, par5Random.nextFloat() * 0.4F + 0.8F, true);
		}
	}

	public void capturePokemonInMachine(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9) {
		TileEntityFossilMachine tile = ((TileEntityFossilMachine) world.getBlockTileEntity(x, y, z));
		EntityPixelmon p = (EntityPixelmon) PixelmonEntityList.createEntityByName(tile.currentPokemon, world);
		if(tile!=null)
			if(EnumPokemon.hasPokemon(tile.currentPokemon) && !EnumTrainers.has(tile.currentPokemon)){
				p.setTamed(true);
				p.setOwner(((EntityPlayer) player).username);
				p.caughtBall = (((ItemPokeBall) PixelmonItemsPokeballs.getItemFromID(tile.currentPokeball)).type);
				p.clearAttackTarget();
				if (tile.isShiny)
					p.setIsShiny(true);
				PokeballTypeHelper.doAfterEffect((((ItemPokeBall) PixelmonItemsPokeballs.getItemFromID(tile.currentPokeball)).type), p);
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).addToParty(p);
				world.spawnEntityInWorld(p);
				p.catchInPokeball();
				p.friendship.initFromCapture();
				((WorldServer) world).getPlayerManager().flagChunkForUpdate(x, y, z);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9) {
		TileEntityFossilMachine tile = ((TileEntityFossilMachine) world.getBlockTileEntity(x, y, z));
		if (world.isRemote)
			return false;
		// Item Retrieval
		if (tile.currentPokeball != -1 && player.getCurrentEquippedItem() != null && !(player.getCurrentEquippedItem().getItem() instanceof ItemPokemonFossil)) {
			int itemId = tile.currentPokeball;
			Item item = PixelmonItemsPokeballs.getItemFromID(itemId);
			if (item == null) {
				tile.currentPokeball = -1;
				tile.currentPokeballTexture = "";
				return true;
			}
			EntityItem var3 = new EntityItem(world, x, y + maxY, z, new ItemStack(item));

			var3.delayBeforeCanPickup = 10;

			world.spawnEntityInWorld(var3);
			tile.currentPokeball = -1;
			((WorldServer) world).getPlayerManager().flagChunkForUpdate(x, y, z);
		}
		if (tile.currentFossil != -1 && player.getCurrentEquippedItem() != null && !(player.getCurrentEquippedItem().getItem() instanceof ItemPokeBall)) {
			int itemId = tile.currentFossil;
			Item item = PixelmonItems.getFossilItem(itemId);
			if (item == null) {
				tile.currentFossil = -1;
				tile.fossilProgress = 0.0f;
				tile.pokemonProgress = 0.0f;
				return true;
			}
			EntityItem var3 = new EntityItem(world, x, y + maxY, z, new ItemStack(item));

			var3.delayBeforeCanPickup = 10;

			world.spawnEntityInWorld(var3);
			tile.currentFossil = -1;
			tile.fossilProgress = 0.0f;
			tile.pokemonProgress = 0.0f;
			((WorldServer) world).getPlayerManager().flagChunkForUpdate(x, y, z);
		}
		// Item Placing
		if (player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem().getItem() instanceof ItemPokeBall) && tile.currentPokeball == -1) {
			tile.currentPokeball = player.getCurrentEquippedItem().itemID;
			tile.currentPokeballTexture = ((ItemPokeBall) player.getCurrentEquippedItem().getItem()).type.getTexture();
			player.getCurrentEquippedItem().stackSize--;
			((WorldServer) world).getPlayerManager().flagChunkForUpdate(x, y, z);
			return true;
		}
		if (player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem().getItem() instanceof ItemPokemonFossil) && tile.currentFossil == -1
				&& !tile.pokemonOccupied) {
			tile.currentFossil = player.getCurrentEquippedItem().itemID;
			player.getCurrentEquippedItem().stackSize--;
			((WorldServer) world).getPlayerManager().flagChunkForUpdate(x, y, z);
			return true;
		}
		// Pokemon retrieval
		if (tile.currentPokeball != -1 && tile.pokemonOccupied && tile.pokemonProgress == tile.pokemonMaxProgress) {
			capturePokemonInMachine(world, x, y, z, player, side, par7, par8, par9);
			tile.pokemonOccupied = false;
			tile.fossilProgress = 0.0f;
			tile.pokemonProgress = 0.0f;
			tile.currentFossil = -1;
			tile.currentPokemon = "";
			tile.currentPokeball = -1;
			tile.completionRate = 0;
			((WorldServer) world).getPlayerManager().flagChunkForUpdate(x, y, z);
		}
		if (!tile.pokemonOccupied && player.getCurrentEquippedItem() != null
				&& !(player.getCurrentEquippedItem().getItem() instanceof ItemPokemonFossil && !(player.getCurrentEquippedItem().getItem() instanceof ItemPokeBall))) {
			tile.pokemonOccupied = false;
			tile.fossilProgress = 0.0f;
			tile.pokemonProgress = 0.0f;
			tile.currentFossil = -1;
			tile.currentPokemon = "";
			tile.currentPokeball = -1;
			tile.completionRate = 0;
			((WorldServer) world).getPlayerManager().flagChunkForUpdate(x, y, z);
		}

		((WorldServer) world).getPlayerManager().flagChunkForUpdate(x, y, z);
		return false;
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 * 
	 * @param world
	 */
	public void setBlockBounds(int stage) {
		this.setBlockBounds(0f, 0, 0f, 1.2f, 2.7f, 1f);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return PixelmonItemsFossils.fossilMachineItem.shiftedIndex;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityFossilMachine();
	}

}
