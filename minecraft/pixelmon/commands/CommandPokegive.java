package pixelmon.commands;

import java.util.ArrayList;
import java.util.List;

import pixelmon.config.PixelmonEntityList;
import pixelmon.database.SpawnLocation;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumBossMode;
import pixelmon.enums.EnumPokeballs;
import pixelmon.enums.EnumPokemon;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerNotLoadedException;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldServer;

public class CommandPokegive extends CommandBase {

	@Override
	public String getCommandName() {
		return "pokegive";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/pokegive <player> <pokemon>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length < 2) {
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("Incorrect usage. " + getCommandUsage(sender)));
			return;
		}
		
		EntityPlayer player = getPlayer(sender, args[0]);
		if (player == null) {
			sender.sendChatToPlayer(ChatMessageComponent.createFromText(args[0] + " does not exist."));
			return;
		}
		
		String name = args[1].substring(0, 1).toUpperCase() + args[1].substring(1);
		
		if (EnumPokemon.hasPokemon(name)) {
			if (name.equalsIgnoreCase("mrmime"))
				name = "MrMime";
			EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(name, player.worldObj);

			if (args.length > 2) {
				for (int i = 2; i < args.length; i++) {
					String s = args[i];
					if (s.equalsIgnoreCase("s"))
						pokemon.setIsShiny(true);
					else if (s.startsWith("lvl")) {
						try {
							int lvl = Integer.parseInt(s.replaceAll("[^0-9]", ""));
							if (lvl <= 0 || lvl > 100) {
								sender.sendChatToPlayer(ChatMessageComponent.createFromText("Cheater!"));
								return;
							}
							pokemon.getLvl().setLevel(lvl);
						} catch (Exception e) {
							sender.sendChatToPlayer(ChatMessageComponent.createFromText("Error in level."));
							return;
						}
					}
				}
			}
			try {
				pokemon.caughtBall = EnumPokeballs.PokeBall;
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).addToParty(pokemon);
			} catch (PlayerNotLoadedException e) {
				e.printStackTrace();
			}
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully gave " + player.username + " a " + name + "!"));
			notifyAdmins(sender, 1, sender.getCommandSenderName() + " gave " + player.username + " a " + name +"!");
		} else 
			sender.sendChatToPlayer(ChatMessageComponent.createFromText(name + " is not in the game!"));
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args) {
		ArrayList<String> pokemon = new ArrayList<String>();
		if (args.length == 1)
			return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
		else if (args.length == 2) {
			for (EnumPokemon p : EnumPokemon.values())
				pokemon.add(p.name);
			return getListOfStringsMatchingLastWord(args, pokemon.toArray(new String[] {}));
		}
		return null;
	}

}
