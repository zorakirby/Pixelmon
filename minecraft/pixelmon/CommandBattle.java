package pixelmon;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import pixelmon.battles.BattleRegistry;
import pixelmon.battles.controller.BattleController;
import pixelmon.battles.participants.BattleParticipant;
import pixelmon.battles.participants.PlayerParticipant;
import pixelmon.comm.ChatHandler;
import pixelmon.config.PixelmonConfig;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumTrainers;
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PokeballManager;
import pixelmon.storage.PlayerNotLoadedException;
import pixelmon.battles.controller.BattleController;

public class CommandBattle extends CommandBase {
	
	BattleController bc1;
	BattleController bc2;
	PlayerParticipant player1;
	PlayerParticipant player2;

	@Override
	public String getCommandName() {
		return "pokebattle";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender){
		return "/pokebattle <player1> <player2>";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        if (par2ArrayOfStr.length != 2)
        {
        	par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Invalid Arguments."));
			par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText(this.getCommandUsage(par1ICommandSender)));
			return;
        }

        notifyAdmins(par1ICommandSender, 1, "Battle between " + par2ArrayOfStr[0] + " and " + par2ArrayOfStr[1] + " attempted...", new Object[] {par2ArrayOfStr[0], par2ArrayOfStr[1]});

        EntityPlayerMP entityplayermp1 = getPlayer(par1ICommandSender, par2ArrayOfStr[0]);

        if (entityplayermp1 == null)
        {
        	throw new PlayerNotFoundException();
        }
           
        EntityPlayerMP entityplayermp2 = getPlayer(par1ICommandSender, par2ArrayOfStr[1]);

        if (entityplayermp2 == null)
        {
        	throw new PlayerNotFoundException();
        }

        if (entityplayermp1 == entityplayermp2)
        {
        	notifyAdmins(par1ICommandSender, "Both participants cannot be the same person.", new Object[0]);
        	return;    	
        }

		if (BattleRegistry.getBattle(entityplayermp1) != null) {
			par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Cannot challenge " + par2ArrayOfStr[0] + " while they are in battle!"));
			return;
		}

		if (BattleRegistry.getBattle(entityplayermp2) != null) {
			par1ICommandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Cannot challenge " + par2ArrayOfStr[1] + " while they are in battle!"));
			return;
		}        	
        
        if (entityplayermp1.worldObj != entityplayermp2.worldObj)
    	{
	        notifyAdmins(par1ICommandSender, "Both participants are not in same dimension.", new Object[0]);
	        return;
        }
        
		try {
			EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage(entityplayermp1).getFirstAblePokemon(entityplayermp1.worldObj);
			player1 = new PlayerParticipant(entityplayermp1, player1firstPokemon);
			
			EntityPixelmon player2firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage(entityplayermp2).getFirstAblePokemon(entityplayermp2.worldObj);
			player2 = new PlayerParticipant(entityplayermp2, player2firstPokemon);
		
			notifyAdmins(par1ICommandSender, 1, "Battle between " + par2ArrayOfStr[0] + " and " + par2ArrayOfStr[1] + " started!", new Object[] {par2ArrayOfStr[0], par2ArrayOfStr[1]});
			bc1 = new BattleController(player1, player2);
			return;
		}
		catch (PlayerNotLoadedException e){
			if (PixelmonConfig.printErrors)
			{
				System.out.println("Error loading player for command /pokebattle " + par2ArrayOfStr[0] + "  " + par2ArrayOfStr[1]);
				System.out.println(e.getStackTrace());
			}
		}
		catch (Exception e)
		{
			if (PixelmonConfig.printErrors) {
				System.out.println("Error loading player for command /pokebattle " + par2ArrayOfStr[0] + "  " + par2ArrayOfStr[1]);
				System.out.println(e.getStackTrace());
			}
		}
    }
	
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        return par2ArrayOfStr.length != 1 && par2ArrayOfStr.length != 2 ? null : getListOfStringsMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getAllUsernames());
    }
}
