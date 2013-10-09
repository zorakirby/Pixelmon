package pixelmon.commands;

import java.util.ArrayList;
import java.util.List;

import pixelmon.config.PixelmonEntityList;
import pixelmon.database.SpawnLocation;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumBossMode;
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
		ChunkCoordinates cc = sender.getPlayerCoordinates();
		WorldServer world = MinecraftServer.getServer().worldServerForDimension(0);
		String playerName = args[0];
		EntityPlayer player = world.getPlayerEntityByName(playerName);
		if (player == null) {
			sender.sendChatToPlayer(ChatMessageComponent.createFromText(playerName + " does not exist."));
			return;
		}

		String name = args[1].substring(0, 1).toUpperCase() + args[1].substring(1);
		if (EnumPokemon.hasPokemon(name)) {
			if (name.equalsIgnoreCase("mrmime"))
				name = "MrMime";
			EntityPixelmon pokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(name, world);
			pokemon.setPosition(cc.posX, cc.posY + 1, cc.posZ);
			if (args.length > 2) {
				for (int i = 2; i < args.length; i++) {
					String s = args[i];
					if (s.equalsIgnoreCase("s"))
						pokemon.setIsShiny(true);
					else if (s.startsWith("lvl")) {
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
			}
			try {
				PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player).addToParty(pokemon);
			} catch (PlayerNotLoadedException e) {
				e.printStackTrace();
			}
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully gave " + playerName + " a " + name));
			notifyAdmins(sender, 1, sender.getCommandSenderName() + " successfully gave");
		}
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
