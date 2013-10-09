package pixelmon.commands;

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
import pixelmon.database.SpawnLocation;
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
	public void processCommand(ICommandSender sender, String[] args) {
		try {
			ChunkCoordinates cc = sender.getPlayerCoordinates();
			WorldServer world = MinecraftServer.getServer().worldServerForDimension(0);
			String name = args[0].substring(0, 1).toUpperCase() + args[0].substring(1);
			if (EnumPokemon.hasPokemon(name)) {
				if (name.equalsIgnoreCase("mrmime"))
					name = "MrMime";
				EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(name, world);
				pokemon.setPosition(cc.posX, cc.posY + 1, cc.posZ);
				if (args.length > 1)
					for (String s : args) {
						if (s.equalsIgnoreCase("s"))
							pokemon.setIsShiny(true);
						else if (s.startsWith("boss")) {
							if (s.endsWith("1"))
								pokemon.setBoss(EnumBossMode.Uncommon);
							else if (s.endsWith("2"))
								pokemon.setBoss(EnumBossMode.Rare);
							else if (s.endsWith("3"))
								pokemon.setBoss(EnumBossMode.Legendary);
							else if (s.endsWith("4"))
								pokemon.setBoss(EnumBossMode.Ultimate);
						} else if (s.startsWith("lvl")) {
							String lvlString = s.substring(3);
							try {
								int lvl = Integer.parseInt(lvlString);
								if (lvl > 100) {
									sender.sendChatToPlayer(ChatMessageComponent.createFromText("Cheater!"));
									return;
								}
								if (lvl <=0){
									sender.sendChatToPlayer(ChatMessageComponent.createFromText("Error in lvl"));
									return;
								}
								pokemon.getLvl().setLevel(lvl);
							} catch (Exception e) {
								sender.sendChatToPlayer(ChatMessageComponent.createFromText("Error in lvl"));
								return;
							}
						}
					}
				world.spawnEntityInWorld(pokemon);
				sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully spawned a " + name));
				notifyAdmins(sender, 1, sender.getCommandSenderName() + " successfully spawned " + name, new Object[] { name });
			} else if (EnumTrainers.has(name)) {
				Entity trainer = PixelmonEntityList.createEntityByName(name, world);
				trainer.setPosition(cc.posX, cc.posY + 1, cc.posZ);
				world.spawnEntityInWorld(trainer);
				sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully spawned a " + name));
				notifyAdmins(sender, 1, sender.getCommandSenderName() + " successfully spawned " + name, new Object[] { name });
			} else if (NPCType.has(name)) {
				Entity npc = PixelmonEntityList.createEntityByName(name, world);
				npc.setPosition(cc.posX, cc.posY + 1, cc.posZ);
				world.spawnEntityInWorld(npc);
				sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully spawned a " + name));
				notifyAdmins(sender, 1, sender.getCommandSenderName() + " successfully spawned " + name, new Object[] { name });
			} else {
				sender.sendChatToPlayer(ChatMessageComponent.createFromText(name + " is not in game!"));
			}
		} catch (Exception e) {
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("Invalid Name!"));
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
