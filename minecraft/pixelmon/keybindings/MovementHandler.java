package pixelmon.keybindings;

import java.util.ArrayList;
import java.util.EnumSet;

import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.enums.EnumMovement;

import net.minecraft.client.Minecraft;
import net.minecraft.src.World;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class MovementHandler implements ITickHandler {

	private int acceleration = 0;
	private int strafe = 0;
	private int jump = 0;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if (Minecraft.getMinecraft().thePlayer.ridingEntity == null)
			return;
		if (Minecraft.getMinecraft().gameSettings.keyBindForward.pressed)
			acceleration++;
		if (Minecraft.getMinecraft().gameSettings.keyBindBack.pressed)
			acceleration--;
		if (Minecraft.getMinecraft().gameSettings.keyBindLeft.pressed)
			strafe++;
		if (Minecraft.getMinecraft().gameSettings.keyBindRight.pressed)
			strafe--;
		if (Minecraft.getMinecraft().gameSettings.keyBindJump.pressed)
			jump++;

		int numMovements = 0;
		if (acceleration != 0)
			numMovements++;
		if (strafe != 0)
			numMovements++;
		if (jump != 0)
			numMovements++;
		EnumMovement[] movements = new EnumMovement[numMovements];
		int i = 0;
		if (acceleration > 0)
			movements[i++] = EnumMovement.Accelerate;
		else if (acceleration < 0)
			movements[i++] = EnumMovement.Decelerate;
		if (strafe > 0)
			movements[i++] = EnumMovement.Left;
		else if (strafe < 0)
			movements[i++] = EnumMovement.Right;
		if (jump > 0)
			movements[i++] = EnumMovement.Jump;

		if (numMovements > 0)
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.Movement, movements));
		acceleration = 0;
		strafe = 0;
		jump = 0;
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

}
