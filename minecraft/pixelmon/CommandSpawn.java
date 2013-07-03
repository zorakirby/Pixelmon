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
import pixelmon.entities.npcs.NPCType;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumBossMode;
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
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/pokespawn <pokemon>";
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
				if (var2.length > 1)
					for (String s : var2) {
						if (s.equalsIgnoreCase("s"))
							((EntityPixelmon) var6).setIsShiny(true);
						else if (s.startsWith("boss")) {
							if (s.endsWith("1")) ((EntityPixelmon) var6).setBoss(EnumBossMode.Uncommon);
							else if (s.endsWith("2")) ((EntityPixelmon) var6).setBoss(EnumBossMode.Rare);
							else if (s.endsWith("3")) ((EntityPixelmon) var6).setBoss(EnumBossMode.Legendary);
						}
					}
				world.spawnEntityInWorld(var6);
				var1.sendChatToPlayer("Successfully spawned a " + var5);
				notifyAdmins(var1, 1, var1 + " successfully spawned " + var5, new Object[] { var5 });
			} else if (EnumTrainers.has(var5)) {
				Entity var6 = PixelmonEntityList.createEntityByName(var5, world);
				var6.setPosition(cc.posX, cc.posY + 1, cc.posZ);
				world.spawnEntityInWorld(var6);
				var1.sendChatToPlayer("Successfully spawned a " + var5);
				notifyAdmins(var1, 1, var1 + " successfully spawned " + var5, new Object[] { var5 });
			} else if (NPCType.has(var5)) {
				Entity var6 = PixelmonEntityList.createEntityByName(var5, world);
				var6.setPosition(cc.posX, cc.posY + 1, cc.posZ);
				world.spawnEntityInWorld(var6);
				var1.sendChatToPlayer("Successfully spawned a " + var5);
				notifyAdmins(var1, 1, var1 + " successfully spawned " + var5, new Object[] { var5 });
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
