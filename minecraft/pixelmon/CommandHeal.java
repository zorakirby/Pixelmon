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
import pixelmon.storage.PixelmonStorage;
import pixelmon.storage.PlayerStorage;

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
	public void processCommand(ICommandSender var1, String[] var2) {
		try {
			if (var2.length != 1){
				var1.sendChatToPlayer("Invalid Arguments");
				var1.sendChatToPlayer(this.getCommandUsage(var1));
				return;
			}

			if(!(var2 instanceof EntityPlayer))
				{
				var1.sendChatToPlayer(var2 + " is not a valid playername");
				return;
				}

			this.player = var2;
			storage = PixelmonStorage.PokeballManager.getPlayerStorage((EntityPlayerMP) player);
			storage.healAllPokemon();
			return;

		} catch (Exception e) {
			var1.sendChatToPlayer("Invalid Name!");
		}
	}
}
