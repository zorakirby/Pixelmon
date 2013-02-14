package pixelmon.entities.pixelmon.interactions;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import pixelmon.entities.pixelmon.Entity8HoldsItems;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class PixelmonInteraction {

	String name;
	int maxInteractions;
	public int numInteractions;

	public PixelmonInteraction(String string, int maxInteractions) {
		this.name = string;
		this.maxInteractions = maxInteractions;
		numInteractions = maxInteractions;
	}

	public abstract boolean interact(EntityPlayer par1EntityPlayer);

	private static ArrayList<PixelmonInteraction> pixelmonInteractions = new ArrayList<PixelmonInteraction>();
	static {
		pixelmonInteractions.add(new MiltankInteraction());
		pixelmonInteractions.add(new CameruptInteraction());
		pixelmonInteractions.add(new MareepInteraction());
	}

	public abstract PixelmonInteraction getInstance();

	int count = 0;

	public void tick() {
		count++;
		if (count > 400) {
			if (numInteractions < maxInteractions)
				numInteractions++;
			count = 0;
		}
	}

	public void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setShort("NumInteractions", (short) numInteractions);
		nbt.setShort("InteractionCount", (short) count);
	}

	public void readEntityFromNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("NumInteractions"))
			numInteractions = nbt.getShort("NumInteractions");
		if (nbt.hasKey("InteractionCount"))
			count = nbt.getShort("InteractionCount");

	}

	public static PixelmonInteraction getInteraction(Entity8HoldsItems pixelmon) {
		for (PixelmonInteraction p : pixelmonInteractions) {
			if (p.name.equalsIgnoreCase(pixelmon.getName())) {
				return p.getInstance();
			}
		}

		return null;
	}

}
