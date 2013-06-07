package pixelmon.entities.npcs;

import pixelmon.Pixelmon;
import pixelmon.config.PixelmonConfig;
import pixelmon.database.DatabaseTrainers;
import pixelmon.database.SpawnLocation;
import pixelmon.storage.PlayerStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityNPC extends EntityCreature {
	private ModelBase model = null;
	public SpawnLocation npcLocation;

	public EntityNPC(World par1World, NPCType npcType) {
		this(par1World);
		setNPCType(npcType);
	}

	public EntityNPC(World world) {
		super(world);
		dataWatcher.addObject(3, ""); // Name
		dataWatcher.addObject(4, "");// Model
		dataWatcher.addObject(14, (int) 0); // NPCType
		dataWatcher.addObject(26, ""); // Nickname
	}

	public void init(String name) {
		setName(name);
		health = 100;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getTexture() {
		return "/pixelmon/texture/" + getNPCType().textureDirectory + "/" + dataWatcher.getWatchableObjectString(4).toLowerCase() + ".png";
	}

	public ModelBase getModel() {
		if (model == null)
			model = Pixelmon.proxy.getNPCModel(getNPCType(), dataWatcher.getWatchableObjectString(4));
		return model;
	}

	public int getAge() {
		return 0;
	};

	@Override
	protected boolean canDespawn() {
		return false;
	}

	public String getName() {
		return dataWatcher.getWatchableObjectString(3);
	}

	public String getNickName() {
		return dataWatcher.getWatchableObjectString(26);
	}

	public NPCType getNPCType() {
		return NPCType.getType(dataWatcher.getWatchableObjectInt(14));
	}

	public void setNPCType(NPCType type) {
		dataWatcher.updateObject(14, type.index);
	}

	public void setName(String name) {
		dataWatcher.updateObject(3, name);
	}

	@Override
	public int getMaxHealth() {
		return 100;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setString("Name", getName());
		nbt.setString("Model", dataWatcher.getWatchableObjectString(4));
		if (npcLocation == null)
			npcLocation = SpawnLocation.Land;
		nbt.setInteger("trainerLocation", npcLocation.index);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		setName(nbt.getString("Name"));
		if (nbt.hasKey("Model")) {
			dataWatcher.updateObject(4, nbt.getString("Model"));
		}
		if (nbt.hasKey("trainerLocation"))
			npcLocation = SpawnLocation.getFromIndex(nbt.getInteger("trainerLocation"));
		else
			npcLocation = SpawnLocation.Land;
		init(getName());
	}

	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);

		int blockId = this.worldObj.getBlockId(var1, var2 - 1, var3);
		return blockId == Block.grass.blockID || blockId == Block.sand.blockID;
	}

	@Override
	public boolean interact(EntityPlayer player) {
		return interactWithNPC(player);
	}

	public boolean interactWithNPC(EntityPlayer player) {
		return false;
	};
}
