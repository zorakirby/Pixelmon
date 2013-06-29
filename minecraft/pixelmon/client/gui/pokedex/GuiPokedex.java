package pixelmon.client.gui.pokedex;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.Rectangle;

import cpw.mods.fml.common.network.PacketDispatcher;

import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.config.PixelmonConfig;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.gui.ContainerEmpty;
import pixelmon.pokedex.Pokedex;
import pixelmon.pokedex.PokedexEntry;
import pixelmon.storage.PixelmonStorage;

public class GuiPokedex extends GuiContainer {

	public int top, left;
	public int currentEntry;
	public GuiPokedexSlot scrollPane;
	private boolean optionsHover;
	Pokedex pokedex;

	private GuiPokedex(String username) {
		super(new ContainerEmpty());
		currentEntry = 1;
		xSize = 256;
		ySize = 226;
	}

	public GuiPokedex(String username, int id) {
		this(username);
		currentEntry = id;
	}

	/*
	 * public GuiPokedex(String lookup) { this(); currentEntry =
	 * Pokedex.nameToID(lookup); if(currentEntry == 0) currentEntry = 1; }
	 */

	public void initGui() {
		super.initGui();
		left = (width - xSize) / 2;
		top = (height - ySize) / 2;
		buttonList.clear();
		if (scrollPane == null)
			scrollPane = new GuiPokedexSlot(this);
		// scrollPane.elementClicked(0, false);
	}

	int mouseX, mouseY;
	float mfloat;

	protected void drawGuiContainerBackgroundLayer(float par3, int par2, int par1) {
		pokedex = ClientPokedexManager.pokedex;
		if (pokedex == null)
			return;
		// drawDefaultBackground();
		left = (width - xSize) / 2;
		top = (height - ySize) / 2;
		mouseX = par2;
		mouseY = par1;
		mfloat = par3;
		PokedexEntry selectedEntry = pokedex.getEntry(currentEntry);
		RenderHelper.disableStandardItemLighting();
		mc.renderEngine.bindTexture("/pixelmon/gui/pokedex.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
		fontRenderer.drawString("Pokedex", left + 6, top + 5, 0xFFFFFF);
		String s = selectedEntry.getPokedexDisplayNumber() + " "
				+ (pokedex.hasSeen(currentEntry) || Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode ? selectedEntry.name : "???");
		drawCenteredString(fontRenderer, s, left + 174, top + 38 - 3, 0x575757);
		s = "Description";
		drawCenteredString(fontRenderer, s, left + 141, top + 125, 0x575757);
		boolean b = pokedex.hasCaught(currentEntry) || Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode;
		// b = true;
		s = "";
		if (b) {
			s = selectedEntry.description;
			fontRenderer.drawSplitString(s, left + 104, top + 141 - 3, 97, 0x575757);
		}
		s = "Height: ";
		if (b)
			s += PixelmonConfig.isInMetric ? selectedEntry.heightM : selectedEntry.heightC;
		else
			s += "??? " + (PixelmonConfig.isInMetric ? "m" : "ft");
		fontRenderer.drawString(s, left + 164, top + 69 - 10, 0x575757);
		s = "Weight: ";
		if (b)
			s += PixelmonConfig.isInMetric ? selectedEntry.weightM : selectedEntry.weightC;
		else
			s += "??? " + (PixelmonConfig.isInMetric ? "kg" : "lbs");
		fontRenderer.drawString(s, left + 164, top + 69 + 0, 0x575757);
		fontRenderer.drawString("More...", left + 46, top + 204 - 3, optionsHover ? 0x0000FF : 0x575757);
		scrollPane.drawScreen(mouseX, mouseY, mfloat);
		b = pokedex.hasSeen(currentEntry)|| Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode;
		// b = !pokedex.isUnknown(currentEntry);
		GL11.glDepthMask(true);
		if (b) {
			EntityPixelmon ep = selectedEntry.getRenderTarget(mc.theWorld);
			if (ep != null) {
				if (ep.getModel() != null)
					drawEntityToScreen(left + 130, top + 106, 51, 55, ep, par3, true);
			}
		}
		// fontRenderer.drawString(mouseX + ", " + mouseY + ": " +
		// rect.contains(mouseX, mouseY), mouseX, mouseY, 0xFFFFFF);
		hasDrawn = true;
	}

	static float spinCount = 0;

	public static void drawEntityToScreen(int x, int y, int w, int l, Entity e, float pt, boolean spin) {
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glEnable(GL11.GL_DEPTH_TEST); // <--This right here needs to be
											// enabled for the model to have
											// correct depth
		RenderHelper.enableStandardItemLighting(); // If not it'll render all
													// weirdly. -Jaryt
		GL11.glTranslatef(x, y, 100);
		float eheight = l / e.height / 4;
		float ewidth = l / e.width / 4;
		float scalar = eheight > ewidth ? eheight : ewidth;
		GL11.glScalef(scalar, scalar, scalar);
		GL11.glRotatef(180, 0, 0, 1);
		if (spin)
			GL11.glRotatef(spinCount += 0.66F, 0, 1, 0);
		RenderHelper.enableStandardItemLighting();
		try {
			RenderManager.instance.renderEntityWithPosYaw(e, 0, e.yOffset, 0, 0, pt);
			RenderManager.instance.playerViewY = 180.0F;
		} catch (Exception ex) {
		}
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		RenderHelper.disableStandardItemLighting();
		GL11.glPopMatrix();
	}

	boolean hasDrawn;

	public void updateScreen() {
		int x = 44;
		int y = 200;
		int width = 44;
		int height = 9;
		Rectangle rect = new Rectangle(x, y, width, height);
		if (rect.contains(mouseX - left, mouseY - top) && hasDrawn) {
			optionsHover = true;
			if (Mouse.isButtonDown(0))
				mc.displayGuiScreen(new GuiPokedexOptions(this));
		} else
			optionsHover = false;
		hasDrawn = false;
	}

	public void drawCenteredString(FontRenderer f, String s, int x, int y, int c) {
		f.drawString(s, x - f.getStringWidth(s) / 2, y, c);
	}

	public void keyTyped(char c, int i) {
		if (i == Keyboard.KEY_ESCAPE) {
			mc.displayGuiScreen(null);
		}
		if (Character.isLetter(c))
			mc.displayGuiScreen(new GuiPokedexMore(this, "" + c));
	}

	public boolean doesGuiPauseGame() {
		return true;
	}

}