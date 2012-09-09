package pixelmon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.helpers.*;
import pixelmon.enums.EnumPokemon;

import net.minecraft.client.Minecraft;
import net.minecraft.src.CommandBase;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.World;

public class CommandSpawn extends CommandBase {

	@Override
	public String getCommandName() {
		return "spawn";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		try {
			EntityPlayer var4 = getCommandSenderAsPlayer(var1);
			String var5 = String.valueOf(Character.toUpperCase(var2[0].charAt(0)));
			var5 += var2[0].substring(1);
			if (EnumPokemon.hasPokemon(var5)) {
				Entity var6 = PixelmonEntityList.createEntityByName(var5, var4.worldObj);
				var6.setPosition(var4.posX, var4.posY + 1, var4.posZ);
				var4.worldObj.spawnEntityInWorld(var6);
			} else {
				var1.sendChatToPlayer(var5 + " is not in game!");
			}
		} catch (Exception e) {
			var1.sendChatToPlayer("Invalid Name!");
		}
	}

	public boolean isStringBoolean(String var1) {
		if (var1.equals("true") || var1.equals("false")) {
			return true;
		} else {
			return false;
		}
	}

	public List addTabCompletionOptions(ICommandSender sender, String[] par2ArrayOfStr) {
		ArrayList<String> pokemon = new ArrayList<String>();
		for (EnumPokemon p : EnumPokemon.values())
			pokemon.add(p.name);
		return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, pokemon.toArray(new String[] {})) : null;
	}

}
