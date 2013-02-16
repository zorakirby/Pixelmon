package pixelmon.entities.pixelmon.interactions;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import pixelmon.entities.pixelmon.Entity8HoldsItems;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class PixelmonInteraction {

	EntityPixelmon pixelmon;
	int maxInteractions;
	int maxCount = 400;

	public PixelmonInteraction(EntityPixelmon pixelmon, int maxInteractions, boolean isFirstInteraction) {
		this.pixelmon = pixelmon;
		this.maxInteractions = maxInteractions;
		setNumInteractions(maxInteractions);
		maxCount = pixelmon.getRNG().nextInt(1200) + 800;
	}

	public abstract boolean interact(EntityPlayer par1EntityPlayer);

	int count = 0;

	public int getNumInteractions() {
		return pixelmon.getDataWatcher().getWatchableObjectShort(24);
	}

	public void setNumInteractions(int newValue) {
		pixelmon.getDataWatcher().updateObject(24, (short) newValue);
	}

	public void tick() {
		if (!pixelmon.worldObj.isRemote) {
			count++;
			if (count > maxCount) {
				if (getNumInteractions() < maxInteractions)
					setNumInteractions(getNumInteractions() + 1);
				count = 0;
				maxCount = pixelmon.getRNG().nextInt(600) + 400;
			}
		}
	}

	public void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setShort("NumInteractions", (short) getNumInteractions());
		nbt.setShort("InteractionCount", (short) count);
	}

	public void readEntityFromNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("NumInteractions"))
			setNumInteractions(nbt.getShort("NumInteractions"));
		if (nbt.hasKey("InteractionCount"))
			count = nbt.getShort("InteractionCount");

	}

	private static HashMap<String, Class> pixelmonInteractions = new HashMap<>();
	static {
		pixelmonInteractions.put("Miltank", MiltankInteraction.class);
		pixelmonInteractions.put("Camerupt", CameruptInteraction.class);
		pixelmonInteractions.put("Mareep", MareepInteraction.class);
	}

	public static PixelmonInteraction getInteraction(Entity8HoldsItems pixelmon, boolean isFirstInteraction) {
		if (!pixelmonInteractions.containsKey(pixelmon.getName()))
			return null;
		Class c = pixelmonInteractions.get(pixelmon.getName());
		try {
			PixelmonInteraction p = (PixelmonInteraction) c.getConstructor(new Class[] { EntityPixelmon.class, boolean.class }).newInstance(
					new Object[] { (EntityPixelmon) pixelmon, isFirstInteraction });
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
