package pixelmon;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldServer;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumTrainers;

public class CommandSpawn extends CommandBase {

	@Override
	public String getCommandName() {
		return "pokespawn";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		try {
			ChunkCoordinates cc = var1.getPlayerCoordinates();
			WorldServer world = MinecraftServer.getServer().worldServerForDimension(0);
			String var5 = String.valueOf(Character.toUpperCase(var2[0].charAt(0)));
			var5 += var2[0].substring(1);
			if (EnumPokemon.hasPokemon(var5)) {
				Entity var6 = PixelmonEntityList.createEntityByName(var5, world);
				var6.setPosition(cc.posX, cc.posY + 1, cc.posZ);
				if (var2.length>1) if (var2[1].equalsIgnoreCase("s"))
					((EntityPixelmon)var6).setIsShiny(true);
				world.spawnEntityInWorld(var6);
			}else if(EnumTrainers.has(var5)){	
				Entity var6 = PixelmonEntityList.createEntityByName(var5, world);
				var6.setPosition(cc.posX, cc.posY + 1, cc.posZ);
				world.spawnEntityInWorld(var6);
			} else {
				var1.sendChatToPlayer(var5 + " is not in game!");
			}
		} catch (Exception e) {
			var1.sendChatToPlayer("Invalid Name!");
		}
	}

	public boolean isStringBoolean(String var1) {
		if (var1.equals("true") || var1.equals("false")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] par2ArrayOfStr) {
		ArrayList<String> pokemon = new ArrayList<String>();
		for (EnumPokemon p : EnumPokemon.values())
			pokemon.add(p.name);
		return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, pokemon.toArray(new String[] {})) : null;
	}

}
