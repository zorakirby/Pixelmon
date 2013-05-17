package pixelmon.blocks;

import pixelmon.enums.EnumEvolutionRock;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEvolutionRock extends TileEntity {
	public EnumEvolutionRock rockType;
	public TileEntityEvolutionRock(EnumEvolutionRock rockType) {
		this.rockType = rockType;
	}

}
