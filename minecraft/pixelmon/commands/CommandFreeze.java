package pixelmon.commands;

import pixelmon.Pixelmon;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;

public class CommandFreeze extends CommandBase {

	@Override
	public String getCommandName() {
		return "freeze";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		EntityPlayer var4 = getCommandSenderAsPlayer(var1);
		Pixelmon.freeze = !Pixelmon.freeze;
		if (Pixelmon.freeze) {
			var4.sendChatToPlayer(ChatMessageComponent.createFromText("Pixelmon are frozen in place!"));
			notifyAdmins(var1, 1, "Pixelmon are frozen in place!", new Object[] {});
		} else {
			var4.sendChatToPlayer(ChatMessageComponent.createFromText("Pixelmon are unfrozen!"));
			notifyAdmins(var1, 1, "Pixelmon are unfrozen!", new Object[] {});
		}
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return "/freeze";
	}

}
