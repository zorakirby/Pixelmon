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
	
	BattleController bc;
	PlayerParticipant player1;
	PlayerParticipant player2;

	@Override
	public String getCommandName() {
		return "pokebattle";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender){
		return "pokebattle <playername1> <playername2>";
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
            throw new WrongUsageException("commands.pokebattle.usage", new Object[0]);
        }
        else
        {
            EntityPlayerMP entityplayermp1 = func_82359_c(par1ICommandSender, par2ArrayOfStr[0]);

            if (entityplayermp1 == null)
            {
            	throw new PlayerNotFoundException();
            }
            else
            {
            	EntityPlayerMP entityplayermp2 = func_82359_c(par1ICommandSender, par2ArrayOfStr[1]);

                if (entityplayermp2 == null) {
                	throw new PlayerNotFoundException();
                }
                else
                {
                	if (entityplayermp1.worldObj != entityplayermp2.worldObj)
                    {
                        notifyAdmins(par1ICommandSender, "commands.pokebattle.notSameDimension", new Object[0]);
                        return;
                    }
                	else
                	{
                		try {
                			EntityPixelmon player1firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage(entityplayermp1).getFirstAblePokemon(entityplayermp1.worldObj);
                			this.player1 = new PlayerParticipant(entityplayermp1, player1firstPokemon);
                		
                			EntityPixelmon player2firstPokemon = PixelmonStorage.PokeballManager.getPlayerStorage(entityplayermp2).getFirstAblePokemon(entityplayermp2.worldObj);
                			this.player2 = new PlayerParticipant(entityplayermp2, player2firstPokemon);
                		
                			this.player1.StartBattle(bc, this.player2);
                			notifyAdmins(par1ICommandSender, 1, "commands.pokebattle.success", new Object[] {par2ArrayOfStr[0], par2ArrayOfStr[1]});
                			return;
                		}
                		catch (PlayerNotLoadedException e){
                			if (PixelmonConfig.printErrors) {
                				System.out.println("Error loading player for command /pokebattle " + par2ArrayOfStr[0] + "  " + par2ArrayOfStr[1]);
                				System.out.println(e.getStackTrace());
                			}
                		}
                		catch (Exception e) {
                			if (PixelmonConfig.printErrors) {
                				System.out.println("Error loading player for command /pokebattle " + par2ArrayOfStr[0] + "  " + par2ArrayOfStr[1]);
                				System.out.println(e.getStackTrace());
                			}
            			}
                	}
                }
            }
        }
    }
}
