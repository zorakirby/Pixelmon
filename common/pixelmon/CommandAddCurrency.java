package pixelmon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.helpers.*;
import pixelmon.enums.EnumPokemon;
import pixelmon.enums.EnumTrainers;
import pixelmon.storage.PlayerStorage;

import net.minecraft.client.Minecraft;
import net.minecraft.src.CommandBase;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.NumberInvalidException;
import net.minecraft.src.World;

public class CommandAddCurrency extends CommandBase {

	@Override
	public String getCommandName() {
		return "pokedollars";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		try {
			EntityPlayer var4 = getCommandSenderAsPlayer(var1);
			String var5 = String.valueOf(Character.toUpperCase(var2[0].charAt(0)));
			var5 += var2[0].substring(1);
			int var3 = Integer.parseInt(var5);
			if (var3 > 999998 || PlayerStorage.getCurrency() == 999999 && var3 > 0){
				var1.sendChatToPlayer("You already have the max amount of Pokedollars!");
			}
			else if (PlayerStorage.getCurrency() - var3 < 0 && var3 < 0){
				var1.sendChatToPlayer("You already have the least amount of Pokedollars!");
			}
			else if (var3 > 0) {
				PlayerStorage.setCurrency(PlayerStorage.getCurrency() + var3);
				var1.sendChatToPlayer("Added " + var3 + " to your currency");
			}
			else if (var3 < 0) {
				PlayerStorage.setCurrency(PlayerStorage.getCurrency() + var3);
				var1.sendChatToPlayer("Subtracted " + var3 + " from your currency");
			} else {
				var1.sendChatToPlayer("Invalid format!");
			}
		} catch (Exception e) {
			var1.sendChatToPlayer("Invalid input!");
		}
	}

	public boolean isStringBoolean(String var1) {
		if (var1.equals("true") || var1.equals("false")) {
			return true;
		} else {
			return false;
		}
	}

}
