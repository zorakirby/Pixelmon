package pixelmon.migration;

import java.util.ArrayList;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import pixelmon.Pixelmon;

import net.minecraft.src.EntityPlayerMP;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

public class MigrationLoader {

	@ForgeSubscribe
	public void onWorldLoad(WorldEvent.Load event) {
		if (event.world.provider.worldType == 0)
			Pixelmon.migration = new Migration(event.world.provider);
	}
}
