package pixelmon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import pixelmon.config.IDListPixelmon;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.helpers.*;

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
		try
		{
			EntityPlayer var4 = getCommandSenderAsPlayer(var1);
			String var5 = String.valueOf(Character.toUpperCase(var2[0].charAt(0)));
			var5 += var2[0].substring(1);
			Entity var6 = PixelmonEntityList.createEntityByName(var5, var4.worldObj);
			var6.setPosition(var4.posX, var4.posY + 1, var4.posZ);
			var4.worldObj.spawnEntityInWorld(var6);
		}
		catch(Exception e)
		{
			var1.sendChatToPlayer("Invalid Name!");
		}
	}
	
	public boolean isStringBoolean(String var1)
	{
		if(var1.equals("true") || var1.equals("false"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public List addTabCompletionOptions(ICommandSender sender, String[] par2ArrayOfStr)
	{
		ArrayList<String> pokemon = new ArrayList<String>();
		Iterator it = PixelmonEntityList.idToStringMapping.entrySet().iterator();
		while(it.hasNext())
		{
			pokemon.add((String)((Entry)it.next()).getValue());
		}
		return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, pokemon.toArray(new String[]{})): null;
	}

}
