package pixelmon.comm.packetHandlers;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.src.EntityPlayer;

import pixelmon.comm.EnumPackets;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.helpers.PlayerRiding;
import pixelmon.enums.EnumMovement;

import cpw.mods.fml.common.network.Player;

public class Movement extends PacketHandlerBase {

	public Movement() {
		packetsHandled.add(EnumPackets.Movement);
	}

	@Override
	public void handlePacket(int index, Player player, DataInputStream dataStream) throws IOException {
		int numMovements = dataStream.readShort();
		EnumMovement[] movement = new EnumMovement[numMovements];
		for (int i = 0; i < numMovements; i++) {
			movement[i] = EnumMovement.getMovement(dataStream.readShort());
		}
		ArrayList<PlayerRiding> rl = EntityPixelmon.PlayerRidingList;
		boolean prExists = false;
		do {
			for (int i = 0; i < rl.size(); i++) {
				if (rl.get(i).player == (EntityPlayer) player) {
					for (int j = 0; j < numMovements; j++) {
						if (movement[j] == EnumMovement.Accelerate)
							rl.get(i).acceleration++;
						else if (movement[j] == EnumMovement.Decelerate)
							rl.get(i).deceleration++;
						else if (movement[j] == EnumMovement.Left)
							rl.get(i).moveLeft++;
						else if (movement[j] == EnumMovement.Right)
							rl.get(i).moveRight++;
						else if (movement[j] == EnumMovement.Jump)
							rl.get(i).jump++;
					}
					prExists = true;
					break;
				}
			}
			if (!prExists) {
				PlayerRiding pr = new PlayerRiding();
				pr.player = (EntityPlayer) player;
				rl.add(pr);
			}
		} while (!prExists);
	}
}
