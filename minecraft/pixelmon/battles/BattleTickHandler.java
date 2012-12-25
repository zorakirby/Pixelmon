package pixelmon.battles;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class BattleTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		BattleRegistry.updateBattles();
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.WORLD);
	}

	@Override
	public String getLabel() {
		return "Battle Tick Handler";
	}

}
