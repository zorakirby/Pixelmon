package pixelmon;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumTrainers;
import pixelmon.battles.controller.BattleController;

public class CommandBattle extends CommandBase {
	
	private BattleControler battlecontroller;
	private EntityPlayer challenger1;
	private EntityPlayer challenger2;

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
	public void processCommand(ICommandSender var1, String[] var2) {
		try {
			if (var2.length != 2){
				var1.sendChatToPlayer("Invalid Arguments");
				var1.sendChatToPlayer(this.getCommandUsage(var1));
				return;
				}

			if(!(var2[0] instanceof EntityPlayer)){
				var1.sendChatToPlayer(var2[0] + " is not a valid playername");
				return;
				}
			
			if(!(var2[1] instanceof EntityPlayer)){
				var1.sendChatToPlayer(var2[1] + " is not a valid playername");
				return;
				}

			this.challenger1 = var2[0];
			this.challenger2 = var2[1];
			battlecontroller.BattleController(challenger1, challenger2);
			notifyAdmins(var1, 1, "commands.pokebattle.success", new Object[] {var2[0], var2[1]});
			return;

		} catch (Exception e) {
			var1.sendChatToPlayer("Invalid Name!");
		}
	}
}
