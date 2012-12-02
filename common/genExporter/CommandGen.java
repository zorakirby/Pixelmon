//package genExporter;
//
//import cpw.mods.fml.common.ObfuscationReflectionHelper;
//import cpw.mods.fml.common.Side;
//import cpw.mods.fml.common.asm.SideOnly;
//import net.minecraft.client.Minecraft;
//import net.minecraft.src.CommandBase;
//import net.minecraft.src.EntityPlayer;
//import net.minecraft.src.ICommandSender;
//import net.minecraft.src.MovingObjectPosition;
//
///**
// * 
// * DO NOT SHIP WITH MOD!
// * 
// * @author Gerald -xkyouchoux-
// *
// */
//
//public class CommandGen extends CommandBase {
//
//	@Override
//	public String getCommandName() {
//		return "gen";
//	}
//
//	@Override
//	public void processCommand(ICommandSender var1, String[] var2) 
//	{
//		if(!(var1 instanceof EntityPlayer))
//		{
//			return;
//		}
//		String var3 = null;
//		EntityPlayer var4 = (EntityPlayer)var1;
//		MovingObjectPosition var5 = Minecraft.getMinecraft().objectMouseOver;
//		try
//		{
//			if(var2.length == 1 && var2[0].equals("list"))
//			{
//				var3 = "Generation Commands\n";
//				var3 += "Exporter\n";
//				var3 += GenerationExporter.instance.getCommandList();
//				var3 += "\n";
//			}
//			else
//			{
//				var3 = GenerationExporter.instance.handleGenCommand(var4, var5, var2);
//			}
//			if(var3 == null)
//			{
//				var3 = "Type '/gen list' for a full list of commands!";
//			}
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		if(var3 != null)
//		{
//			var1.sendChatToPlayer(var3);
//			System.out.println(var3);
//		}
//	}
//}
