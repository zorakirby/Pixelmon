package pixelmon.entities.pixelmon.stats;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

import net.minecraft.nbt.NBTTagCompound;
import pixelmon.battles.attacks.Attack;
import pixelmon.database.DatabaseMoves;

public class Moveset extends AbstractList<Attack> implements List<Attack>, RandomAccess, Cloneable {
	public Attack[] attacks = new Attack[4];

	public Moveset() {
	}

	public Moveset(int index, Attack a) {
		attacks[0] = a;
	}

	public Attack get(int index) {
		if (index < 0 || index > 3)
			return null;
		return attacks[index];
	}

	public boolean add(Attack a) {
		if (size() >= 4)
			return false;
		attacks[size()] = a;
		return true;
	}

	public Attack set(int index, Attack a) {
		Attack retval = attacks[index];
		attacks[index] = a;
		return retval;
	}

	public void swap(int index, int index2) {
		Attack a = attacks[index];
		attacks[index] = attacks[index2];
		attacks[index2] = a;
	}

	public Attack remove(int index) {
		Attack a = get(index);
		set(index, null);
		return a;
	}

	public boolean remove(Object o) {
		if (!(o instanceof Attack))
			return false;
		for (int i = 0; i < this.size(); i++) {
			if (attacks[i] == (Attack) o) {
				attacks[i] = null;
				return true;
			}
		}
		return false;
	}

	public int size() {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			if (attacks[i] != null)
				count++;
		}
		return count;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean isAt(int index, Attack a) {
		if (isEmpty())
			return false;
		if (!contains(a))
			return false;
		return attacks[index] == a;
	}

	public boolean contains(Object o) {
		if (isEmpty())
			return false;
		if (o instanceof Attack) {
			for (int i = 0; i < this.size(); i++) {
				if (attacks[i] == (Attack) o)
					return true;
			}
		}
		return false;
	}

	public void clear() {
		attacks = new Attack[4];
	}

	public void writeToNBT(NBTTagCompound var1) {
		var1.setInteger("PixelmonNumberMoves", size());
		for (int i = 0; i < size(); i++) {
			var1.setString("PixelmonMoveName" + i, get(i).baseAttack.attackName);
			var1.setInteger("PixelmonMoveType" + i, get(i).baseAttack.attackType.getIndex());
			var1.setInteger("PixelmonMovePP" + i, get(i).pp);
			var1.setInteger("PixelmonMovePPBase" + i, get(i).ppBase);
			var1.setInteger("PixelmonMovePPMax" + i, get(i).baseAttack.ppMax);
		}
	}

	public void readFromNBT(NBTTagCompound var1) {
		clear();
		int numMoves = var1.getInteger("PixelmonNumberMoves");
		for (int i = 0; i < numMoves; i++) {
			Attack a = DatabaseMoves.getAttack(var1.getString("PixelmonMoveName" + i));
			if (a != null) {
				if (var1.hasKey("PixelmonMovePP" + i))
					a.pp = var1.getInteger("PixelmonMovePP" + i);
				if (var1.hasKey("PixelmonMovePPBase" + i))
					a.ppBase = var1.getInteger("PixelmonMovePPBase" + i);
				add(a);
			}
		}
	}
}
