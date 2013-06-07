package pixelmon;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
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
	public String getCommandUsage(ICommandSender icommandsender){
		return "pokeheal <playername>";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
		try {
			if (par2ArrayOfStr.length != 1){
				par1ICommandSender.sendChatToPlayer("Invalid Arguments");
				par1ICommandSender.sendChatToPlayer(this.getCommandUsage(par1ICommandSender));
				return;
			}
			
			EntityPlayer entityplayer;
			entityplayer = func_82359_c(par1ICommandSender, par2ArrayOfStr[0]);
						
			if(!(entityplayer instanceof EntityPlayer)){
				par1ICommandSender.sendChatToPlayer(par2ArrayOfStr[0] + " is not a valid playername");
				return;
			}
			
			if (BattleRegistry.getBattle(entityplayer) != null) {
				par1ICommandSender.sendChatToPlayer("Cannot heal " + par2ArrayOfStr[0] + " while they are in battle!");
				return;
			}

			storage = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) entityplayer);
			storage.healAllPokemon();
			notifyAdmins(par1ICommandSender, 0, "Successfully healed " + par2ArrayOfStr[0] + "'s Pokemon!", new Object[] {par2ArrayOfStr[0]});
			return;

		} 
		catch (PlayerNotFoundException e){
			//Catches the case where in cracked clients player's name defaults is "ASH"
			par1ICommandSender.sendChatToPlayer("Invalid Name! Try again or try using 'ash'.");
		}
		catch (Exception e) {
			par1ICommandSender.sendChatToPlayer("Invalid Name! Try again or try using 'ash'.");
		}
	}
}
