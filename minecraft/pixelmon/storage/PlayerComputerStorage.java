package pixelmon.storage;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class PlayerComputerStorage {
	private ComputerBox[] storageBoxes = new ComputerBox[boxCount];
	public static final int boxCount = 8;
	private NBTTagCompound data = new NBTTagCompound();
	public EntityPlayer player;

	public PlayerComputerStorage(EntityPlayer player) {
		this.player = player;
		for (int i = 0; i < boxCount; i++) {
			storageBoxes[i] = new ComputerBox(this, i);
		}
	}

	public void addToComputer(EntityPixelmon p) {
		for (ComputerBox c : storageBoxes) {
			if (c.hasSpace()) {
				c.add(p, getId());
				return;
			}
		}
		ChatHandler.sendChat(p.getOwner(), "You have no space left in your computer!");
	}

	public int getId() {
		int id = new Random().nextInt(1000000);
		boolean isUsed = false;
		do {
			isUsed = false;
			for (ComputerBox c : storageBoxes) {
				for (NBTTagCompound nbt : c.getStoredPokemon()) {
					if (nbt != null) {
						if (nbt.getInteger("pixelmonID") == id) {
							id = new Random().nextInt(1000000);
							isUsed = true;
						}
					}
				}
			}
			for (ComputerBox c : storageBoxes) {
				for (int i = 0; i < ComputerBox.boxLimit; i++) {
					if (c.getStoredPokemon()[i] != null) {
						if (c.getStoredPokemon()[i].getInteger("pixelmonID") == id) {
							id = new Random().nextInt(1000000);
							isUsed = true;
						}
					}
				}
			}
		} while (isUsed);
		return id;
	}

	public void changePokemon(int box, int boxPos, NBTTagCompound n) {
		if (n != null) {
			n.setInteger("BoxNumber", box);
			n.setInteger("PixelmonOrder", boxPos);
		}
		ComputerBox c = storageBoxes[box];
		c.changePokemon(boxPos, n);
	}

	public void addToBox(int originalBox, NBTTagCompound n) {
		ComputerBox c = storageBoxes[originalBox];
		if (n != null) {
			n.setInteger("BoxNumber", originalBox);
		}
		c.addToFirstSpace(n);
	}

	public ComputerBox getBox(int boxNumber) {
		return storageBoxes[boxNumber];
	}

	public ComputerBox getBoxFromPosition(int pos) {
		for (int i = 0; i < boxCount; i++) {
			if (storageBoxes[i].position == pos)
				return storageBoxes[i];
		}
		return null;
	}

	public ComputerBox[] getBoxList() {
		return storageBoxes;
	}

	@SuppressWarnings("rawtypes")
	public void readFromNBT(NBTTagCompound var1) {
		Iterator<NBTTagCompound> i = var1.getTags().iterator();

		while (i.hasNext()) {
			NBTTagCompound tag = i.next();
			ComputerBox c = new ComputerBox(this, Integer.parseInt(tag.getName()));
			c.load(tag);
			storageBoxes[Integer.parseInt(tag.getName())] = c;
		}
	}

	public void writeToNBT(NBTTagCompound n) {
		for (int i = 0; i < storageBoxes.length; i++) {
			ComputerBox c = storageBoxes[i];
			NBTTagCompound cTag = new NBTTagCompound();
			c.save(cTag);
			n.setCompoundTag("" + c.position, cTag);
		}
	}

	public boolean hasChanges() {
		for (ComputerBox c : storageBoxes) {
			if (c.hasChanged)
				return true;
		}
		return false;
	}

}