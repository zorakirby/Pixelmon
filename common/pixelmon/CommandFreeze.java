package pixelmon;

import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumPokemon;
import net.minecraft.src.CommandBase;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICommandSender;

public class CommandFreeze extends CommandBase {

	@Override
	public String getCommandName() {
		return "freeze";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		EntityPlayer var4 = getCommandSenderAsPlayer(var1);
		Pixelmon.freeze = !Pixelmon.freeze;
		if (Pixelmon.freeze)
			var4.sendChatToPlayer("Pixelmon are frozen in place!");
		else
			var4.sendChatToPlayer("Pixelmon are unfrozen!");
	}

}
