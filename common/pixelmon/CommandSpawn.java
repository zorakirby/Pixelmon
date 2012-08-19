package pixelmon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import pixelmon.config.IDListPixelmon;
import pixelmon.config.PixelmonEntityList;

import net.minecraft.client.Minecraft;
import net.minecraft.src.CommandBase;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;

public class CommandSpawn extends CommandBase {

	@Override
	public String getCommandName() {
		return "spawn";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if(var2.length == 1)
		{
			try
			{
				MovingObjectPosition var3 = Minecraft.getMinecraft().objectMouseOver;
				EntityPlayer var4 = getCommandSenderAsPlayer(var1);
				Entity var5 = PixelmonEntityList.createEntityByName(var2[0], var4.worldObj);
				if(var3.typeOfHit == EnumMovingObjectType.TILE)
				{
					var5.setPosition(var3.blockX, var3.blockY + 1, var3.blockZ);
				}
				else
				{
					var5.setPosition(var4.posX, var4.posY + 1, var4.posZ);
				}
				var4.worldObj.spawnEntityInWorld(var5);
			}
			catch(Exception e)
			{
				var1.sendChatToPlayer("Invalid Name!");
			}
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
