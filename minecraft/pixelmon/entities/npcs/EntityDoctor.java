package pixelmon.entities.npcs;

import org.lwjgl.opengl.GL11;

import pixelmon.Pixelmon;
import pixelmon.client.gui.battles.ClientBattleManager;
import pixelmon.client.gui.battles.GuiBattle.BattleMode;
import pixelmon.enums.EnumGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityDoctor extends EntityNPC {

	public EntityDoctor(World par1World) {
		super(par1World, NPCType.Doctor);
		setName("Doctor");
	}

	@Override
	public boolean interactWithNPC(EntityPlayer player) {
		player.openGui(Pixelmon.instance, EnumGui.Doctor.getIndex(), player.worldObj, 0, 0, 0);
		return true;
	}
}
