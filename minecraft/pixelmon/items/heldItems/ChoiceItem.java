package pixelmon.items.heldItems;

import pixelmon.enums.heldItems.EnumChoiceItems;
import pixelmon.enums.heldItems.EnumHeldItems;
import pixelmon.items.ItemHeld;

public class ChoiceItem extends ItemHeld {

	private EnumChoiceItems choiceItemType;

	public ChoiceItem(int id, EnumChoiceItems choiceItemType) {
		super(id, EnumHeldItems.choiceItem);
		this.choiceItemType = choiceItemType;
		if (choiceItemType == EnumChoiceItems.ChoiceBand)
			setIconCoord(12, 0);
		else if (choiceItemType == EnumChoiceItems.ChoiceScarf)
			setIconCoord(12, 1);
		else if (choiceItemType == EnumChoiceItems.ChoiceSpecs)
			setIconCoord(12, 2);
	}

	public double affectAttack(double attack) {
		if (choiceItemType == EnumChoiceItems.ChoiceBand)
			attack *= 1.5;
		return attack;
	}

	public double affectSpecialAttack(double attack) {
		if (choiceItemType == EnumChoiceItems.ChoiceSpecs)
			attack *= 1.5;
		return attack;
	}

	public double affectSpeed(int speed) {
		if (choiceItemType == EnumChoiceItems.ChoiceScarf)
			speed *= 1.5;
		return speed;
	}

}
