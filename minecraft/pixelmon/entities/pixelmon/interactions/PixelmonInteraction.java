package pixelmon.entities.pixelmon.interactions;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import pixelmon.entities.pixelmon.Entity8HoldsItems;
import pixelmon.entities.pixelmon.EntityPixelmon;

public abstract class PixelmonInteraction {

	String name;
	int maxInteractions;
	int numInteractions;

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

	public static PixelmonInteraction getInteraction(Entity8HoldsItems pixelmon) {
		for (PixelmonInteraction p : pixelmonInteractions) {
			if (p.name.equalsIgnoreCase(pixelmon.getName())) {
				return p.getInstance();
			}
		}

		return null;
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
}
