package pixelmon;

import java.util.Random;

import pixelmon.structure.SchematicImporter;
import pixelmon.structure.StructureData;
import pixelmon.structure.StructureRegistry;
import pixelmon.structure.generation.GeneralScattered;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class CommandStruc extends CommandBase {

	@Override
	public String getCommandName() {
		return "struc";
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		World world = getCommandSenderAsPlayer(icommandsender).worldObj;
		Random random = new Random();
		ChunkCoordinates cc = icommandsender.getPlayerCoordinates();
		int xPos = cc.posX;
		int yPos = cc.posY;
		int zPos = cc.posZ;

		StructureData structure = StructureRegistry.getScatteredStructureFromBiome(world.getBiomeGenForCoords(xPos, zPos));
		if (structure == null)
			return;
		SchematicImporter s = new SchematicImporter(structure.path);
		s.readSchematic();
		GeneralScattered g = new GeneralScattered(random, xPos, yPos, zPos, s, structure);
		g.generate(world, random);
		if (structure.hasPokemon) {
		}
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/struc";
	}
}
