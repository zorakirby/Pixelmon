package pixelmon.items;

import net.minecraft.item.Item;

/**
 * Used for marking an Enum as having a unique item associated with each value.<br>
 * @author Jack
 *
 */
public interface IEnumItem{
	
	/**
	 * Gets the item associated with this value.
	 * @param type - The category index the item. For example, if an Enum 
	 * has 3 different categories, 0 should assiciate with the first
	 * category, 1 with the second, and 2 with the third.
	 * @return Item associated with this value, in the {@code type}th
	 * category. If there is no category associated with {@code type} then
	 * {@code null} should be returned.
	 */
	public Item getItem(int type);
	
	
	/**
	 * @return The number of Item categories this Enum is associated with.
	 */
	public int numTypes();

}
