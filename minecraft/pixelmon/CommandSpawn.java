package pixelmon;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
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
	public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
		try {
			ChunkCoordinates cc = par1ICommandSender.getPlayerCoordinates();
			WorldServer world = MinecraftServer.getServer().worldServerForDimension(0);
			String name = par2ArrayOfStr[0].substring(0,1).toUpperCase() + par2ArrayOfStr[0].substring(1);
			if (EnumPokemon.hasPokemon(name)) {
				if(name.equalsIgnoreCase("mrmime"))
					name = "MrMime";
				Entity var6 = PixelmonEntityList.createEntityByName(name, world);
				var6.setPosition(cc.posX, cc.posY + 1, cc.posZ);
				if (par2ArrayOfStr.length > 1)
					for (String s : par2ArrayOfStr) {
						if (s.equalsIgnoreCase("s"))
							((EntityPixelmon) var6).setIsShiny(true);
						else if (s.startsWith("boss")) {
							if (s.endsWith("1")) ((EntityPixelmon) var6).setBoss(EnumBossMode.Uncommon);
							else if (s.endsWith("2")) ((EntityPixelmon) var6).setBoss(EnumBossMode.Rare);
							else if (s.endsWith("3")) ((EntityPixelmon) var6).setBoss(EnumBossMode.Legendary);
						}
					}
				world.spawnEntityInWorld(var6);
				par1ICommandSender.sendChatToPlayer(ChatMessageComponent.func_111066_d("Successfully spawned a " + name));
				notifyAdmins(par1ICommandSender, 1, par1ICommandSender + " successfully spawned " + name, new Object[] { name });
			} else if (EnumTrainers.has(name)) {
				Entity var6 = PixelmonEntityList.createEntityByName(name, world);
				var6.setPosition(cc.posX, cc.posY + 1, cc.posZ);
				world.spawnEntityInWorld(var6);
				par1ICommandSender.sendChatToPlayer(ChatMessageComponent.func_111066_d("Successfully spawned a " + name));
				notifyAdmins(par1ICommandSender, 1, par1ICommandSender + " successfully spawned " + name, new Object[] { name });
			} else if (NPCType.has(name)) {
				Entity var6 = PixelmonEntityList.createEntityByName(name, world);
				var6.setPosition(cc.posX, cc.posY + 1, cc.posZ);
				world.spawnEntityInWorld(var6);
				par1ICommandSender.sendChatToPlayer(ChatMessageComponent.func_111066_d("Successfully spawned a " + name));
				notifyAdmins(par1ICommandSender, 1, par1ICommandSender + " successfully spawned " + name, new Object[] { name });
			} else {
				par1ICommandSender.sendChatToPlayer(ChatMessageComponent.func_111066_d(name + " is not in game!"));
			}
		} catch (Exception e) {
			par1ICommandSender.sendChatToPlayer(ChatMessageComponent.func_111066_d("Invalid Name!"));
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
