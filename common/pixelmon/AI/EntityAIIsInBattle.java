package pixelmon.AI;

import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import net.minecraft.src.EntityAIBase;

public class EntityAIIsInBattle extends EntityAIBase {

	private PixelmonEntityHelper pokemon;

	public EntityAIIsInBattle(PixelmonEntityHelper pokemon) {
		this.pokemon = pokemon;
		setMutexBits(0);
	}

	@Override
	public boolean shouldExecute() {
		if (pokemon.bc != null)
			return true;
		return false;
	}

	@Override
	public boolean continueExecuting() {
		return pokemon.bc != null;
	}

}
