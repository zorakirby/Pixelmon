package pixelmon.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import pixelmon.battles.BattleRegistry;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumTrainers;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerStorage;

import pixelmon.battles.controller.BattleController;

public class CommandHeal extends CommandBase {

	private PlayerStorage storage;
	private EntityPlayer player;

	@Override
	public String getCommandName() {
		return "pokeheal";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/pokeheal <player>";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
		try {
			if (par2ArrayOfStr.length == 0) {
				this.processCommand(par1ICommandSender, new String[] {par1ICommandSender.getCommandSenderName()});
				return;
			}

			if (par2ArrayOfStr.length > 1) {
				for (String player : par2ArrayOfStr) {
					this.processCommand(par1ICommandSender, new String[] { player });
				}
				return;
			}

			if (par2ArrayOfStr[0].contains("@a") || par2ArrayOfStr[0].contains("@p")) {
				for (EntityPlayerMP p : PlayerSelector.matchPlayers(par1ICommandSender, par2ArrayOfStr[0])) {
					String playername = p.username;
					this.processCommand(par1ICommandSender, new String[] { playername });
				}
				return;
			} else {
				EntityPlayer entityplayer;
				entityplayer = getPlayer(par1ICommandSender, par2ArrayOfStr[0]);

				if (!(entityplayer instanceof EntityPlayer)) {
					par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(par2ArrayOfStr[0] + " is not a valid playername"));
					return;
				}

				if (BattleRegistry.getBattle(entityplayer) != null) {
					par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Cannot heal " + par2ArrayOfStr[0]
							+ "'s Pokemon while they are in battle!"));
					return;
				}

				storage = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entityplayer);
				storage.healAllPokemon();
				notifyAdmins(par1ICommandSender, 1, par1ICommandSender.getCommandSenderName() + " successfully healed " + par2ArrayOfStr[0] + "'s Pokemon!",
						new Object[] { par2ArrayOfStr[0] });

				if (par2ArrayOfStr[0].contains("@")) {
					par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Pokemon successfully healed!"));
				} else {
					par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Successfully healed " + par2ArrayOfStr[0] + "'s Pokemon!"));
				}
				return;
			}
		} catch (PlayerNotFoundException e) {
			// Catches the case where in cracked clients player's name defaults is "ASH"
			par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Invalid Name! Try again."));
		} catch (Exception e) {
			par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Invalid Name! Try again."));
		}
	}

    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        return par2ArrayOfStr.length != 1 && par2ArrayOfStr.length != 2 ? null : getListOfStringsMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getAllUsernames());
    }
}
