package pixelmon;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> types, Object... tickData) {
		for(TickType type : types)
		{
			if(type == TickType.RENDER && !Minecraft.getMinecraft().session.username.equals("ASH") && !ObfuscationReflectionHelper.obfuscation)
			{
				Minecraft.getMinecraft().session.username = "ASH";
			}
			if(type == TickType.RENDER)
			{
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> types, Object... tickData) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		return EnumSet.of(TickType.RENDER, TickType.WORLD);
	}

	@Override
	public String getLabel() {
		return "Pixelmon Ticker";
	}

}
