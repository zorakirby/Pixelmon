package pixelmon.battles.status;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import pixelmon.battles.attacks.Attack;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.comm.PixelmonLevelUpPacket;
import pixelmon.comm.PixelmonTransformPacket;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.stats.Moveset;

public class Transformed extends StatusBase {

	EntityPixelmon target;

	public Transformed(EntityPixelmon user, EntityPixelmon target) {
		super(StatusType.Transformed, false, false, false);
		this.target = target;
		PixelmonTransformPacket p = new PixelmonTransformPacket(user.getPokemonId(), target.getName());
		((EntityPlayerMP) user.getOwner()).playerNetServerHandler.sendPacketToPlayer(p.getPacket());

	}

	Moveset moveset;

	public Moveset getMoveset() {
		if (moveset == null) {
			moveset = new Moveset();
			for (int i = 0; i < target.moveset.size(); i++) {
				Attack a = DatabaseMoves.getAttack(target.moveset.get(i).baseAttack.attackName);
				a.pp = 5;
				moveset.add(a);
			}
		}
		return moveset;
	}

	@SideOnly(Side.CLIENT)
	public static void applyToClientEntity(PixelmonTransformPacket p) {
		World world = Minecraft.getMinecraft().theWorld;
		List<Entity> EntityList = world.loadedEntityList;
		for (int i = 0; i < EntityList.size(); i++) {
			Entity e = EntityList.get(i);
			if (e instanceof EntityPixelmon) {
				if (((EntityPixelmon) e).getPokemonId() == p.pixelmonID) {
					((EntityPixelmon)e).transform(p.transformedModel);
				}
			}
		}
	}

}
