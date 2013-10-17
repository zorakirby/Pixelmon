package pixelmon.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import net.minecraft.util.WeightedRandomItem;

public class WeightedWrapper<T> extends WeightedRandomItem{

	public T object;
	public WeightedWrapper(T object, int par1) {
		super(par1);
		this.object = object;
	}
	
	public void setWeight(int i){
		this.itemWeight = i;
	}
	
	public ChancedWrapper<T> toChanced(){
		return new ChancedWrapper<T>(object, (float)itemWeight/100F);
	}
}
