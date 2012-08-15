package pixelmon.gui;

import java.awt.Rectangle;
import java.util.ArrayList;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

import org.lwjgl.input.Keyboard;

import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonItems;
import pixelmon.entities.pixelmon.helpers.PixelmonEntityHelper;
import pixelmon.items.ItemPokeBall;

public class GuiAttackingBag extends GuiScreen{
	
	private GuiAttacking parent; 
	private boolean choiceSelected;
	private ArrayList<GuiAttackingBagSlot> bagSlots;
	private GuiAttackingBagSlot selected;
	
	public GuiAttackingBag(GuiAttacking parent){
		this.parent = parent;
		choiceSelected = false;
		bagSlots = new ArrayList<GuiAttackingBagSlot>();
		selected = null;
		loadInventory();
	}
	
	public void loadInventory(){
		InventoryPlayer ip = ModLoader.getMinecraftInstance().thePlayer.inventory;
		for(ItemStack i : ip.mainInventory){
			if(i == null){
				continue;
			}
			Item it = i.getItem();
			if(it instanceof ItemPokeBall || (it.shiftedIndex == PixelmonItems.potion.shiftedIndex)){
				if(getBagSlot(i) == null){
					GuiAttackingBagSlot bs = new GuiAttackingBagSlot(i);
					for(int z = 0; z < i.stackSize; z++){
						bs.addItem();
					}
					bagSlots.add(bs);
				}
				else{
					GuiAttackingBagSlot bs = getBagSlot(i);
					for(int z = 0; z < i.stackSize; z++){
						bs.addItem();
					}
				}
			}
		}
	}
	
	public GuiAttackingBagSlot getBagSlot(ItemStack i){
		for(GuiAttackingBagSlot b: bagSlots){
			if(b.getItem().getItem() == i.getItem()){
				return b;
			}
		}
		return null;
	}
	
	public void initGui(){
		super.initGui();
		controlList.clear();
		controlList.add(new GuiButton(0, width / 2 - 25, height / 2 - 100, 50, 20, "Back"));
		controlList.add(new GuiButton(1, width / 2 - 25, height / 2 - 70 + bagSlots.size() * 20 + 10, 50, 20, "Use"));
	}
	
	public void actionPerformed(GuiButton b){
		if(b.id == 0){
			mc.displayGuiScreen(parent);
		}
		if(b.id == 1){
			if(selected == null){
				mc.displayGuiScreen(parent);
				return;
			}
			Item item = selected.getItem().getItem();
			if(ModLoader.getMinecraftInstance().theWorld.isRemote){
				PixelmonDataPacket mine = parent.getMyPixelmonPacket();
				PixelmonDataPacket other = parent.getOtherPixelmonPacket();
				if(item.shiftedIndex == PixelmonItems.potion.shiftedIndex){
					if(mine.health + 20 >= mine.hp){
						mine.health = mine.hp;
					}
					else{
						mine.health = mine.health + 20;
					}
				}
				else if(item instanceof ItemPokeBall){
					//addpokeball action stuff in here
				}
			}
			else{
				PixelmonEntityHelper mine = parent.getMyPixelmon();
				PixelmonEntityHelper other = parent.getOtherPixelmon();
				if(item.shiftedIndex == PixelmonItems.potion.shiftedIndex){
					if(mine.getHealth() + 20 >= mine.getMaxHealth()){
						mine.setHealth(mine.getMaxHealth());
					}
					else{
						mine.setHealth(mine.getHealth() + 20);
					}
				}
				else if(item instanceof ItemPokeBall){
					//addpokeball action stuff in here
				}
			}
			ModLoader.getMinecraftInstance().thePlayer.inventory.consumeInventoryItem(item.shiftedIndex);
			mc.displayGuiScreen(parent);
		}
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		for(int i = 0; i < bagSlots.size(); i++){
			GuiAttackingBagSlot bs = bagSlots.get(i);
			if(selected != bs){
				drawRect(width / 2 - 55, height / 2 - 75 + i * 20, width / 2 + 55, height / 2 - 55 + i * 20, 0x6f000000);
			fontRenderer.drawString(bs.getName() + " : " + bs.getCount(), width / 2 - 50, height / 2 - 70 + i * 20, 0xffffff);
			}
			else{
				drawRect(width / 2 - 55, height / 2 - 75 + i * 20, width / 2 + 55, height / 2 - 55 + i * 20, 0x6f000000);
				drawRect(width / 2 - 55, height / 2 - 75 + i * 20, width / 2 + 55, height / 2 - 55 + i * 20, 0x1fffff00);
				fontRenderer.drawString(bs.getName() + " : " + bs.getCount(), width / 2 - 50, height / 2 - 70 + i * 20, 0xffff00);
			}
		}
		super.drawScreen(par1, par2, par3);
	}
	
	public void keyTyped(char i, int i1) {
	}
	
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		for(int i = 0; i < bagSlots.size(); i++){
			Rectangle r = new Rectangle(width / 2 - 55, height / 2 - 75 + i * 20, 110, 20);
			if(r.contains(par1, par2)){
				GuiAttackingBagSlot bs = bagSlots.get(i);
				selected = bs;
				break;
			}
		}
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

}
