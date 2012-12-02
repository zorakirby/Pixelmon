package pixelmon.gui.pokedex;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.GuiScreen;

import org.lwjgl.input.Keyboard;

import pixelmon.config.PixelmonConfig;
import pixelmon.gui.ContainerEmpty;

public class GuiPokedexOptions extends GuiContainer {

	private GuiScreen parentScreen;
	private ArrayList<GuiButton> buttonList = new ArrayList<GuiButton>();

	public GuiPokedexOptions(GuiScreen gui) {
		super(new ContainerEmpty());
		mc = Minecraft.getMinecraft();
		parentScreen = gui;
		for (EnumPokedexButtons e : EnumPokedexButtons.allButtons)
			buttonList.add(new GuiButton(e.index, 100, 100, 75, 20, e.getState()));
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
		controlList.clear();
		controlList.add(new GuiButton(0, width / 2 - 100, height - 25, "Done"));
		controlList.addAll(buttonList);
	}

	public void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		drawDefaultBackground();
		String s = "Pokedex Options";
		fontRenderer.drawString(s, width / 2 - fontRenderer.getStringWidth(s) / 2, 10, 0xFFFFFF);
	}

	public void actionPerformed(GuiButton button) {
		if (button.id == 0)
			saveAndClose();
		else {
			for (GuiButton button1 : buttonList)
				if (button1.id == button.id) {
					EnumPokedexButtons b = EnumPokedexButtons.getFromIndex(button1.id);
					if (b == null)
						continue;
					b.toggle();
					button.displayString = b.getState();
					PixelmonConfig.isInMetric = b.state;
				}
		}
	}

	public void keyTyped(char c, int i) {
		if (i == Keyboard.KEY_ESCAPE)
			saveAndClose();
	}

	private void saveAndClose() {
		//mod_Pixelmon.savePokedexProps();
		mc.displayGuiScreen(parentScreen);
	}
}

enum EnumPokedexButtons {
	metric(1, "Metric", "Imperial/US Customary");

	public int index;
	private String onValue;
	private String offValue;
	public boolean state;
	public static ArrayList<EnumPokedexButtons> allButtons;

	private EnumPokedexButtons(int i, String s, String s1) {
		index = i;
		onValue = s;
		offValue = s1;
		state = true;
		addToList(this);
	}

	private static void addToList(EnumPokedexButtons e) {
		if (allButtons == null)
			allButtons = new ArrayList<EnumPokedexButtons>();
		allButtons.add(e);
	}

	public static EnumPokedexButtons getFromIndex(int i) {
		for (EnumPokedexButtons b : allButtons)
			if (b.index == i)
				return b;
		return null;
	}

	public boolean toggle() {
		state = !state;
		return state;
	}

	public String getState() {
		return (state ? onValue : offValue);
	}

}