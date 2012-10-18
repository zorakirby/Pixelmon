package pixelmon.gui.battles;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.network.PacketDispatcher;

import pixelmon.ServerStorageDisplay;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.comm.PixelmonMovesetDataPacket;
import pixelmon.entities.EntityCamera;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.gui.ContainerEmpty;
import pixelmon.gui.GuiPixelmonOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderManager;
import net.minecraft.src.Slot;
import net.minecraft.src.Tessellator;

public class GuiBattle extends GuiContainer {

	public enum BattleMode {
		Waiting, MainMenu, ChoosePokemon, ChooseBag, UseBag, ChooseAttack;
	}

	private int battleControllerIndex;
	public static BattleMode mode;
	private int guiWidth = 300;
	private int guiHeight = 60;

	boolean cameraEnabled = false;

	public GuiBattle(int battleControllerIndex) {
		super(new ContainerEmpty());
		this.battleControllerIndex = battleControllerIndex;
		mode = BattleMode.Waiting;
		GuiPixelmonOverlay.isVisible = false;
		ClientBattleManager.clearMessages();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float mfloat, int mouseX, int mouseY) {
		if (cameraEnabled && ClientBattleManager.camera != null)
			if (Minecraft.getMinecraft().renderViewEntity != ClientBattleManager.camera)
				Minecraft.getMinecraft().renderViewEntity = ClientBattleManager.camera;

		int left = (width - xSize) / 2;
		int top = (height - ySize) / 2;
		RenderHelper.disableStandardItemLighting();
		if (ClientBattleManager.hasMoreMessages() || mode == BattleMode.Waiting)
			drawMessageScreen();
		else if (mode == BattleMode.MainMenu)
			drawMainMenu(mouseX, mouseY);
		else if (mode == BattleMode.ChooseAttack)
			drawChooseAttack(mouseX, mouseY);
		else if (mode == BattleMode.ChoosePokemon)
			drawChoosePokemon(mouseX, mouseY);
		else if (mode == BattleMode.ChooseBag)
			drawChooseBag(mouseX, mouseY);

	}

	private void drawChooseBag(int mouseX, int mouseY) {
		
	}

	int flashCount = 0;

	private void drawMessageScreen() {
		int guiIndex = -1;
		guiIndex = mc.renderEngine.getTexture("/pixelmon/gui/battleGui3.png");

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawImageQuad(guiIndex, width / 2 - guiWidth / 2, height - guiHeight, guiWidth, guiHeight, 0, 0, 1, 146f / 480f);
		if (mode != BattleMode.Waiting || ClientBattleManager.hasMoreMessages()) {
			drawCenteredString(fontRenderer, ClientBattleManager.getNextMessage(), width / 2, height - 35, 0xFFFFFF);
			flashCount++;
			if (flashCount > 30) {
				drawImageQuad(guiIndex, width / 2 + 130, height - 15, 10, 6, 611f / 640f, 149f / 480f, 628f / 640f, 159f / 480f);
				if (flashCount > 60)
					flashCount = 0;
			}
		} else {
			flashCount++;
			if (flashCount >= 160)
				flashCount = 0;
			if (flashCount < 40)
				drawCenteredString(fontRenderer, "Waiting", width / 2, height - 35, 0xFFFFFF);
			else if (flashCount < 80)
				drawCenteredString(fontRenderer, "Waiting.", width / 2, height - 35, 0xFFFFFF);
			else if (flashCount < 120)
				drawCenteredString(fontRenderer, "Waiting..", width / 2, height - 35, 0xFFFFFF);
			else if (flashCount < 160)
				drawCenteredString(fontRenderer, "Waiting...", width / 2, height - 35, 0xFFFFFF);

		}
	}

	@Override
	public void handleKeyboardInput() {
		return;
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (ClientBattleManager.hasMoreMessages()) {
			ClientBattleManager.removeMessage();
			return;
		}
		if (mode == BattleMode.MainMenu) {
			int x1 = width / 2 + 31;
			int y1 = height - guiHeight + 9;
			int x2 = width / 2 + 90;
			int y2 = height - guiHeight + 35;
			int w = 48, h = 16;
			if (mouseX > x1 && mouseX < x1 + w && mouseY > y1 && mouseY < y1 + h)
				mode = BattleMode.ChooseAttack;
			else if (mouseX > x2 && mouseX < x2 + w && mouseY > y1 && mouseY < y1 + h)
				mode = BattleMode.ChoosePokemon;
			else if (mouseX > x2 && mouseX < x2 + w && mouseY > y2 && mouseY < y2 + h){
				PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.Flee, 0));
				mode = BattleMode.Waiting;
			}
			return;

		} else if (mode == BattleMode.ChooseAttack) {
			int x1 = width / 2 - 141;
			int x2 = width / 2 - 50;
			int y1 = height - guiHeight + 9;
			int y2 = height - guiHeight + 33;
			int w = 87, h = 20;
			if (mouseX > x1 && mouseX < x1 + w && mouseY > y1 && mouseY < y1 + h) {
				PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.ChooseAttack, 0, battleControllerIndex,
						ClientBattleManager.getUserPokemon().pokemonID));
				mode = BattleMode.Waiting;
				return;
			} else if (mouseX > x2 && mouseX < x2 + w && mouseY > y1 && mouseY < y1 + h) {
				PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.ChooseAttack, 1, battleControllerIndex,
						ClientBattleManager.getUserPokemon().pokemonID));
				mode = BattleMode.Waiting;
				return;
			} else if (mouseX > x1 && mouseX < x1 + w && mouseY > y2 && mouseY < y2 + h) {
				PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.ChooseAttack, 2, battleControllerIndex,
						ClientBattleManager.getUserPokemon().pokemonID));
				mode = BattleMode.Waiting;
				return;
			} else if (mouseX > x2 && mouseX < x2 + w && mouseY > y2 && mouseY < y2 + h) {
				PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.ChooseAttack, 3, battleControllerIndex,
						ClientBattleManager.getUserPokemon().pokemonID));
				mode = BattleMode.Waiting;
				return;
			}
		} else if (mode == BattleMode.ChoosePokemon) {
			int pos = 0;
			for (int i = 0; i < 6; i++) {
				if (i != ClientBattleManager.getUserPokemon().order) {
					PixelmonDataPacket pdata = ServerStorageDisplay.pokemon[i];
					if (pdata != null) {
						int xpos = width / 2 - 30;
						int ypos = height - 195 + pos * 30;
						if (mouseX > xpos && mouseX < xpos + 150 && mouseY > ypos + 1 && mouseY < ypos + 31) {
							PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.SwitchPokemon, pdata.order, battleControllerIndex, 0));
							mode = BattleMode.Waiting;
							return;
						}
					}
					pos++;
				}
			}
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	private void drawMainMenu(int mouseX, int mouseY) {
		int guiIndex = -1;
		guiIndex = mc.renderEngine.getTexture("/pixelmon/gui/battleGui1.png");

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawImageQuad(guiIndex, width / 2 - guiWidth / 2, height - guiHeight, guiWidth, guiHeight, 0, 0, 1, 146f / 480f);
		drawButton(width / 2 + 31, height - guiHeight + 9, 48, 16, "FIGHT", mouseX, mouseY, guiIndex, 0);
		drawButton(width / 2 + 31, height - guiHeight + 35, 48, 16, "BAG", mouseX, mouseY, guiIndex, 1);
		drawButton(width / 2 + 90, height - guiHeight + 9, 48, 16, "POKEMON", mouseX, mouseY, guiIndex, 2);
		drawButton(width / 2 + 90, height - guiHeight + 35, 48, 16, "RUN", mouseX, mouseY, guiIndex, 3);
		drawString(fontRenderer, "What will " + ClientBattleManager.getUserPokemon().name + " do?", width / 2 - 130, height - 35, 0xFFFFFF);
	}

	private void drawChoosePokemon(int mouseX, int mouseY) {
		int guiIndex = -1;
		guiIndex = mc.renderEngine.getTexture("/pixelmon/gui/choosePokemon.png");

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawImageQuad(guiIndex, width / 2 - 128, height - 203, 256, 203, 0, 0, 1, 203f / 256f);

		drawString(fontRenderer, "Choose a Pokemon.", width / 2 - 120, height - 100, 0xFFFFFF);

		PixelmonDataPacket p = ClientBattleManager.getUserPokemon();
		String numString = "";
		if (p.nationalPokedexNumber < 10)
			numString = "00" + p.nationalPokedexNumber;
		else if (p.nationalPokedexNumber < 100)
			numString = "0" + p.nationalPokedexNumber;
		else
			numString = "" + p.nationalPokedexNumber;
		int var9;
		if (p.isShiny)
			var9 = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/shinysprites/" + numString + ".png");
		else
			var9 = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/sprites/" + numString + ".png");
		drawImageQuad(var9, width / 2 - 121, height - 176, 24f, 24f, 0f, 0f, 1f, 1f);
		drawHealthBar(width / 2 - 85, height - 135, 9, 56, p);
		drawImageQuad(guiIndex, width / 2 - 95, height - 135, 61, 9, 86f / 256f, 240f / 256f, 147f / 256f, 249f / 256f);
		drawCenteredString(fontRenderer, p.health + "/" + p.hp, width / 2 - 59, height - 123, 0xffffff);
		drawString(fontRenderer, p.nickname.equals("") ? p.name : p.nickname, width / 2 - 90, height - 161, 0xffffff);
		drawString(fontRenderer, "Lv. " + p.lvl, width / 2 - 90, height - 148, 0xffffff);
		if (p.isMale)
			drawImageQuad(guiIndex, width / 2 - 60, height - 149, 6, 9, 32f / 256f, 208f / 256f, 38f / 256f, 217f / 256f);
		else
			drawImageQuad(guiIndex, width / 2 - 60, height - 149, 6, 9, 32f / 256f, 218f / 256f, 38f / 256f, 227f / 256f);

		int pos = -1;
		for (int i = 0; i < 6; i++) {
			if (i != p.order) {
				pos++;
				PixelmonDataPacket pdata = ServerStorageDisplay.pokemon[i];
				if (pdata != null) {

					numString = "";
					if (pdata.nationalPokedexNumber < 10)
						numString = "00" + pdata.nationalPokedexNumber;
					else if (pdata.nationalPokedexNumber < 100)
						numString = "0" + pdata.nationalPokedexNumber;
					else
						numString = "" + pdata.nationalPokedexNumber;
					if (pdata.isShiny)
						var9 = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/shinysprites/" + numString + ".png");
					else
						var9 = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/sprites/" + numString + ".png");
					drawImageQuad(var9, width / 2 - 23, height - 192 + pos * 30, 24f, 24f, 0f, 0f, 1f, 1f);
					drawHealthBar(width / 2 + 65, height - 192 + pos * 30, 9, 56, pdata);
					drawImageQuad(guiIndex, width / 2 + 55, height - 192 + pos * 30, 61, 9, 86f / 256f, 240f / 256f, 147f / 256f, 249f / 256f);
					drawString(fontRenderer, pdata.health + "/" + pdata.hp, width / 2 + 75, height - 180 + pos * 30, 0xffffff);
					drawString(fontRenderer, pdata.nickname.equals("") ? pdata.name : pdata.nickname, width / 2 + 5, height - 190 + pos * 30, 0xffffff);
					drawString(fontRenderer, "Lv. " + pdata.lvl, width / 2 + 5, height - 176 + pos * 30, 0xffffff);
					if (pdata.isMale)
						drawImageQuad(guiIndex, width / 2 + 40, height - 176 + pos * 30, 6, 9, 32f / 256f, 208f / 256f, 38f / 256f, 217f / 256f);
					else
						drawImageQuad(guiIndex, width / 2 + 40, height - 176 + pos * 30, 6, 9, 32f / 256f, 218f / 256f, 38f / 256f, 227f / 256f);

					int xpos = width / 2 - 30;
					int ypos = height - 195 + pos * 30;
					if (mouseX > xpos && mouseX < xpos + 150 && mouseY > ypos + 1 && mouseY < ypos + 31)
						drawImageQuad(guiIndex, xpos, ypos, 150, 32, 43f / 256f, 205f / 256f, 194f / 256f, 237f / 256f);
				}
			}
		}
	}

	public void drawHealthBar(int x, int y, int height, int width, PixelmonDataPacket p) {
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		tessellator.startDrawingQuads();

		int barWidth = (int) (((float) p.health) / ((float) p.hp) * (((float) width) - 6f));
		tessellator.setColorRGBA_F(1.0f - ((float) p.health / (float) p.hp) * 0.8F, 0.2F + ((float) p.health / (float) p.hp) * 0.8F, 0.2F, 1.0F);
		tessellator.addVertex(x, y, 0.0);
		tessellator.addVertex(x, y + height, 0.0);
		tessellator.addVertex(x + barWidth, y + height, 0.0);
		tessellator.addVertex(x + barWidth, y, 0.0);
		tessellator.draw();
		GL11.glPopMatrix();
		GL11.glEnable(3553);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
	}

	private void drawChooseAttack(int mouseX, int mouseY) {
		int guiIndex = -1;
		guiIndex = mc.renderEngine.getTexture("/pixelmon/gui/battleGui2.png");

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawImageQuad(guiIndex, width / 2 - guiWidth / 2, height - guiHeight, guiWidth, guiHeight, 0, 0, 1, 146f / 480f);
		PixelmonMovesetDataPacket[] moveset = ClientBattleManager.getUserPokemon().moveset;
		int numMoves = ClientBattleManager.getUserPokemon().numMoves;
		if (numMoves > 0)
			drawButton(width / 2 - 141, height - guiHeight + 9, 87, 20, moveset[0].attackName, mouseX, mouseY, guiIndex, 0);
		if (numMoves > 1)
			drawButton(width / 2 - 50, height - guiHeight + 9, 87, 20, moveset[1].attackName, mouseX, mouseY, guiIndex, 1);
		if (numMoves > 2)
			drawButton(width / 2 - 141, height - guiHeight + 33, 87, 20, moveset[2].attackName, mouseX, mouseY, guiIndex, 2);
		if (numMoves > 3)
			drawButton(width / 2 - 50, height - guiHeight + 33, 87, 20, moveset[3].attackName, mouseX, mouseY, guiIndex, 3);
		drawCenteredString(fontRenderer, "PP: " + moveset[mouseOverButton].pp + "/" + moveset[mouseOverButton].ppBase, width / 2 + 99, height - guiHeight + 18,
				0xFFFFFF);
		drawString(fontRenderer, "Type: ", width / 2 + 99 - fontRenderer.getStringWidth("Type: " + moveset[mouseOverButton].type.toString()) / 2, height
				- guiHeight + 33, 0xFFFFFF);
		drawString(fontRenderer, moveset[mouseOverButton].type.toString(),
				width / 2 + 99 - fontRenderer.getStringWidth("Type: " + moveset[mouseOverButton].type.toString()) / 2 + fontRenderer.getStringWidth("Type: "),
				height - guiHeight + 33, moveset[mouseOverButton].type.getColor());

	}

	private int mouseOverButton = 0;

	private void drawButton(int x, int y, int buttonWidth, int buttonHeight, String string, int mouseX, int mouseY, int guiIndex, int ind) {
		if (mode == BattleMode.MainMenu) {
			if (mouseX > x && mouseX < x + buttonWidth && mouseY > y && mouseY < y + buttonHeight) {
				drawImageQuad(guiIndex, x, y, buttonWidth, buttonHeight, 387f / 640f, 158f / 480f, 489f / 640f, 196f / 480f);
			}
			drawCenteredString(fontRenderer, string, x + buttonWidth / 2, y + buttonHeight / 2 - 3, 0xFFFFFF);
		} else if (mode == BattleMode.ChooseAttack) {
			drawImageQuad(guiIndex, x, y, buttonWidth, buttonHeight, 206f / 640f, 152f / 480f, 393f / 640f, 202f / 480f);
			if (mouseX > x && mouseX < x + buttonWidth && mouseY > y && mouseY < y + buttonHeight) {
				drawImageQuad(guiIndex, x + 2, y + 2, buttonWidth - 5, buttonHeight - 4, 23f / 640f, 155f / 480f, 200f / 640f, 195f / 480f);
				mouseOverButton = ind;
			}
			drawCenteredString(fontRenderer, string, x + buttonWidth / 2, y + buttonHeight / 2 - 3, 0xFFFFFF);
		}
	}

	@Override
	public void drawBackground(int par1) {
	}

	@Override
	public void drawDefaultBackground() {
	}

	private void drawImageQuad(int textureHandle, int x, int y, float w, float h, float us, float vs, float ue, float ve) {
		// activate the specified texture
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureHandle);

		float var7 = 0.00390625F;
		float var8 = 0.00390625F;
		Tessellator var9 = Tessellator.instance;
		var9.startDrawingQuads();
		var9.addVertexWithUV((double) (x + 0), (double) (y + h), (double) this.zLevel, (double) ((float) us), (double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + h), (double) this.zLevel, (double) ((float) ue), (double) ((float) ve));
		var9.addVertexWithUV((double) (x + w), (double) (y + 0), (double) this.zLevel, (double) ((float) ue), (double) ((float) vs));
		var9.addVertexWithUV((double) (x + 0), (double) (y + 0), (double) this.zLevel, (double) ((float) us), (double) ((float) vs));
		var9.draw();
	}
}
