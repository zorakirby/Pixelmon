package pixelmon.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import pixelmon.Pixelmon;
import pixelmon.ServerStorageDisplay;
import pixelmon.StarterList;
import pixelmon.battles.BattleController;
import pixelmon.battles.BattleRegistry;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.enums.EnumGui;
import pixelmon.gui.pc.GuiPC;
import pixelmon.gui.pokedex.GuiPokedex;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerEmpty();
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == EnumGui.ChooseStarter.getIndex())
			return new GuiChooseStarter();
		else if (ID == EnumGui.LearnMove.getIndex())
			return new GuiLearnMove(Pixelmon.PokeballManager.getPlayerStorage(ModLoader.getMinecraftInstance().thePlayer).getAlreadyExists(x, ModLoader.getMinecraftInstance().theWorld)
					.getHelper(), DatabaseMoves.getAttack(y));
		else if (ID == EnumGui.ChooseAttack.getIndex()) {
			if (world.isRemote) {
				return new GuiAttacking(x, y, z);
			} else {
				PixelmonEntityHelper pixelmon1 = null, pixelmon2 = null;
				BattleController bc = BattleRegistry.getBattle(x);
				if (y == 1) {
					pixelmon1 = bc.participant1.currentPokemon();
					pixelmon2 = bc.participant2.currentPokemon();
				} else {
					pixelmon1 = bc.participant2.currentPokemon();
					pixelmon2 = bc.participant1.currentPokemon();
				}

				return new GuiAttacking(bc, pixelmon1, pixelmon2);
			}
		} else if (ID == EnumGui.ChoosePokemon.getIndex()) {
			if (world.isRemote) {
				PixelmonDataPacket p = ServerStorageDisplay.get(y);
				return new GuiChoosePokemon(p,x,null);
			} else {
				BattleController bc = BattleRegistry.getBattle(x);
				PixelmonEntityHelper p;
				if (y == 1)
					p = bc.participant1.currentPokemon();
				else
					p = bc.participant2.currentPokemon();
				return new GuiChoosePokemon(bc, p, null);
			}
		} else if (ID == EnumGui.Pokedex.getIndex()) {
			return new GuiPokedex();
		} else if (ID == EnumGui.PC.getIndex()) {
			return new GuiPC();
		} else if (ID == EnumGui.Healer.getIndex()) {
			return new GuiHealer();
		} else if (ID == EnumGui.PokeChecker.getIndex()) {
			if (ModLoader.getMinecraftInstance().theWorld.isRemote)
				return new GuiScreenPokeChecker(ServerStorageDisplay.get(x));
			return new GuiScreenPokeChecker(Pixelmon.PokeballManager.getPlayerStorage(player).getAlreadyExists(x, world).getHelper());
		}
		return null;
	}
}
