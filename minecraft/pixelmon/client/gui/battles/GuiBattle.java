package pixelmon.client.gui.battles;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.Pixelmon;
import pixelmon.battles.attacks.Attack;
import pixelmon.battles.participants.ParticipantType;
import pixelmon.client.PixelmonServerStore;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.client.camera.CameraTarget;
import pixelmon.client.camera.CameraTargetEntity;
import pixelmon.client.camera.GuiCamera;
import pixelmon.client.gui.GuiHelper;
import pixelmon.client.gui.GuiPixelmonOverlay;
import pixelmon.client.gui.GuiResources;
import pixelmon.comm.ChatHandler;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.comm.PixelmonMovesetDataPacket;
import pixelmon.comm.PixelmonStatsPacket;
import pixelmon.config.PixelmonItems;
import pixelmon.config.PixelmonItemsPokeballs;
import pixelmon.database.DatabaseMoves;
import pixelmon.enums.BagSection;
import pixelmon.enums.EnumGui;
import pixelmon.gui.ContainerEmpty;
import pixelmon.items.ItemData;
import pixelmon.items.ItemEther;
import pixelmon.items.ItemPokeBall;
import pixelmon.items.ItemPotion;
import pixelmon.items.PixelmonItem;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiBattle extends GuiCamera {

	public enum BattleMode {
		Waiting, MainMenu, ChoosePokemon, ChooseBag, UseBag, ChooseAttack, ApplyToPokemon, YesNo, EnforcedSwitch;
	}

	private int battleControllerIndex = -1;
	public static BattleMode mode;
	public static BagSection bagSection;
	public static boolean battleEnded = true;
	private int guiWidth = 300;
	private int guiHeight = 60;
	int limitFrameRate = 0;

	public GuiBattle(int battleControllerIndex) {
		super(Minecraft.getMinecraft().thePlayer.inventoryContainer);
		this.battleControllerIndex = battleControllerIndex;
		mode = BattleMode.Waiting;
		GuiPixelmonOverlay.isVisible = false;
		ClientBattleManager.clearMessages();
		battleEnded = false;
		limitFrameRate = Minecraft.getMinecraft().gameSettings.limitFramerate;
	}
	
	public GuiBattle() {
		super(Minecraft.getMinecraft().thePlayer.inventoryContainer);
		this.mode = BattleMode.Waiting;
		GuiPixelmonOverlay.isVisible = false;
		ClientBattleManager.clearMessages();
		ClientBattleManager.canSwitch = true;
		battleEnded = false;
	}

	boolean first = true;

	public void setCameraToPlayer() {
		// Keep playercam in firstperson mode
		// mc.gameSettings.thirdPersonView = 0;
		// mc.gameSettings.hideGUI = true;
		// mc.renderViewEntity = mc.thePlayer;
		if (camera != null){
			CameraTarget tar = camera.getTarget();
			if(tar != null){
				if(tar.getTargetData() != mc.thePlayer){
					if(tar instanceof CameraTargetEntity)
						tar.setTargetData(mc.thePlayer);
					else
						camera.setTarget(new CameraTargetEntity(mc.thePlayer));
				}
			}
			else
				camera.setTarget(new CameraTargetEntity(mc.thePlayer));
		}
	}

	public void setCameraToPixelmon() {
		// Keep pokecam in thirdperson mode
		// mc.gameSettings.thirdPersonView = 1;
		// mc.gameSettings.hideGUI = true;
		// mc.renderViewEntity = ClientBattleManager.getUserPokemon();
		if (camera != null){
			CameraTarget tar = camera.getTarget();
			if(tar != null){
				if(tar.getTargetData() != ClientBattleManager.getUserPokemon()){
					if(tar instanceof CameraTargetEntity)
						tar.setTargetData(ClientBattleManager.getUserPokemon());
					else
						camera.setTarget(new CameraTargetEntity(ClientBattleManager.getUserPokemon()));
				}
			}
			else
				camera.setTarget(new CameraTargetEntity(mc.thePlayer));
		}
	}

	protected void restoreSettingsAndClose() {
		GuiPixelmonOverlay.isVisible = true;
		mc.gameSettings.limitFramerate = limitFrameRate;
		mc.thePlayer.closeScreen();
		if (evolveList.size() > 0) {
			int pokemonID = evolveList.get(0);
			evolveList.remove(0);
			Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.Evolution.getIndex(), Minecraft.getMinecraft().theWorld, pokemonID, 0, 0);
		} else if (PixelmonServerStore.bossDrops != null)
			Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.ItemDrops.getIndex(), Minecraft.getMinecraft().theWorld, 0, 0, 0);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float mfloat, int mouseX, int mouseY) {
		if (first) {
			first = false;
			if (camera != null)
				setCameraToPlayer();
		}

		if (!ClientBattleManager.hasMoreMessages() && battleEnded && !ClientBattleManager.hasLevelUps() && !ClientBattleManager.hasNewAttacks()) {
			restoreSettingsAndClose();
			return;
		}
		int left = (width - xSize) / 2;
		int top = (height - ySize) / 2;
		RenderHelper.disableStandardItemLighting();

		if (!ClientBattleManager.hasLevelUps() && !ClientBattleManager.hasNewAttacks()
				&& (mode == BattleMode.Waiting || mode == BattleMode.MainMenu || mode == BattleMode.ChooseAttack)) {
			drawPokemonOverlays();
		}
		if (ClientBattleManager.hasMoreMessages())
			drawMessageScreen();
		else if (mode == BattleMode.YesNo)
			drawYesNoDialog(mouseX, mouseY);
		else if (ClientBattleManager.hasLevelUps() || ClientBattleManager.hasNewAttacks()) {
			if (!ClientBattleManager.hasNewAttacks()
					|| (ClientBattleManager.hasLevelUps() && ClientBattleManager.levelUpList.get(0).level <= ClientBattleManager.newAttackList.get(0).level)) {
				mode = oldMode;
				drawLevelUp(mouseX, mouseY);
			} else
				drawReplaceAttack(mouseX, mouseY);

		} else if (mode == BattleMode.MainMenu)
			drawMainMenu(mouseX, mouseY);
		else if (mode == BattleMode.ChooseAttack)
			drawChooseAttack(mouseX, mouseY);
		else if (mode == BattleMode.ChoosePokemon || mode == BattleMode.ApplyToPokemon || mode == BattleMode.EnforcedSwitch)
			drawChoosePokemon(mouseX, mouseY);
		else if (mode == BattleMode.ChooseBag)
			drawChooseBag(mouseX, mouseY);
		else if (mode == BattleMode.UseBag)
			drawUseBag(mouseX, mouseY);
		else if (mode == BattleMode.Waiting)
			drawMessageScreen();
	}

	private void drawPokemonOverlays() {
		mc.renderEngine.bindTexture(GuiResources.pokemonInfoP1);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(width - 120, height - (guiHeight + 45), 120, 45, 0, 0, 120f / 128f, 45f / 64f, zLevel);
		PixelmonDataPacket userPokemon = ClientBattleManager.getUserPokemonPacket();
		if (userPokemon == null)
			return;
		String name = userPokemon.nickname.equals("") ? userPokemon.name : userPokemon.nickname;
		drawString(fontRenderer, name, width - 113, height - (guiHeight + 37), 0xFFFFFF);
		mc.renderEngine.bindTexture(GuiResources.pokemonInfoP1);
		drawExpBar(width - 114, height - (guiHeight + 8), 105, 4, userPokemon);
		GuiHelper.drawImageQuad(width - 115, height - (guiHeight + 11), 109, 7, 1f / 128f, 56f / 64f, 110f / 128f, 62f / 64f, zLevel);
		// GuiHelper.drawImageQuad(-1, width - 113, height - (guiHeight + 7),
		// 105, 2,
		// 0,0,1,1);

		drawHealthBar(width - 101, height - (guiHeight + 26), 97, 6, userPokemon);
		GuiHelper.drawImageQuad(width - 111, height - (guiHeight + 28), 103, 9, 1f / 128f, 45f / 64f, 104f / 128f, 55f / 64f, zLevel);
		drawString(fontRenderer, "" + userPokemon.health + "/" + userPokemon.hp,
				width - 10 - fontRenderer.getStringWidth("" + userPokemon.health + "/" + userPokemon.hp), height - (guiHeight + 18), 0xFFFFFF);
		mc.renderEngine.bindTexture(GuiResources.pokemonInfoP1);
		if (userPokemon.isMale)
			GuiHelper.drawImageQuad(width - 113 + fontRenderer.getStringWidth(name), height - (guiHeight + 39), 7, 10, 119f / 128f, 52f / 64f, 126f / 128f,
					62f / 64f, zLevel);
		else
			GuiHelper.drawImageQuad(width - 113 + fontRenderer.getStringWidth(name), height - (guiHeight + 39), 7, 10, 111f / 128f, 52f / 64f, 118f / 128f,
					62f / 64f, zLevel);
		drawString(fontRenderer, "Lv. " + userPokemon.lvl, width - 10 - fontRenderer.getStringWidth("Lv. " + userPokemon.lvl), height - (guiHeight + 37),
				0xFFFFFF);
		mc.renderEngine.bindTexture(GuiResources.pokemonInfoP1);

		mc.renderEngine.bindTexture(GuiResources.pokemonInfoP2);
		PixelmonDataPacket targetPokemon = ClientBattleManager.getOpponent();
		if (targetPokemon != null) {
			String targetName = targetPokemon.nickname.equals("") ? targetPokemon.name : targetPokemon.nickname;
			GuiHelper.drawImageQuad(0, 0, 119, 34, 0, 0, 119f / 128f, 34f / 64f, zLevel);
			drawString(fontRenderer, targetName, 8, 8, 0xFFFFFF);
			mc.renderEngine.bindTexture(GuiResources.pokemonInfoP2);
			drawHealthBar(18, 19, 56, 6, targetPokemon);
			GuiHelper.drawImageQuad(8, 18, 62, 9, 1f / 128f, 43f / 64f, 63f / 128f, 53f / 64f, zLevel);
			if (targetPokemon.isShiny) {
				if (targetPokemon.isMale) {
					GuiHelper.drawImageQuad(8 + fontRenderer.getStringWidth(targetName), 6, 7, 10, 72f / 128f, 42f / 64f, 79f / 128f, 52f / 64f, zLevel);
					drawString(fontRenderer, "Lv. " + targetPokemon.lvl, 111 - fontRenderer.getStringWidth("Lv. " + targetPokemon.lvl), 8, 0xFFFFFF);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mc.renderEngine.bindTexture(GuiResources.shiny);
					this.drawImageQuad(100, 20, 10, 10, 0, 0, 1, 1);
				} else if (!targetPokemon.isMale) {
					GuiHelper.drawImageQuad(8 + fontRenderer.getStringWidth(targetName), 6, 7, 10, 64f / 128f, 42f / 64f, 71f / 128f, 52f / 64f, zLevel);
					drawString(fontRenderer, "Lv. " + targetPokemon.lvl, 111 - fontRenderer.getStringWidth("Lv. " + targetPokemon.lvl), 8, 0xFFFFFF);
					// fontRenderer.drawString("*", 100, 20, 0xFFFF00);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					mc.renderEngine.bindTexture(GuiResources.shiny);
					this.drawImageQuad(100, 20, 10, 10, 0, 0, 1, 1);
				}
			} else {
				if (targetPokemon.isMale) {
					GuiHelper.drawImageQuad(8 + fontRenderer.getStringWidth(targetName), 6, 7, 10, 72f / 128f, 42f / 64f, 79f / 128f, 52f / 64f, zLevel);
					drawString(fontRenderer, "Lv. " + targetPokemon.lvl, 111 - fontRenderer.getStringWidth("Lv. " + targetPokemon.lvl), 8, 0xFFFFFF);
				} else
					GuiHelper.drawImageQuad(8 + fontRenderer.getStringWidth(targetName), 6, 7, 10, 64f / 128f, 42f / 64f, 71f / 128f, 52f / 64f, zLevel);
				drawString(fontRenderer, "Lv. " + targetPokemon.lvl, 111 - fontRenderer.getStringWidth("Lv. " + targetPokemon.lvl), 8, 0xFFFFFF);
			}
		}
	}

	private void drawImageQuad(int x, int y, float w, float h, float us, float vs, float ue, float ve) {
		// activate the specified texture
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

	private void drawExpBar(int x, int y, int width, int height, PixelmonDataPacket p) {
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		tessellator.startDrawingQuads();

		int barWidth = (int) (((float) p.xp) / ((float) p.nextLvlXP) * (((float) width) - 6f));
		tessellator.setColorRGBA_F(0.0f, 0.0f, 0.4f, 1.0F);
		tessellator.addVertex(x, y, 0.0);
		tessellator.addVertex(x, y + height, 0.0);
		tessellator.addVertex(x + width, y + height, 0.0);
		tessellator.addVertex(x + width, y, 0.0);
		tessellator.setColorRGBA_F(0.3f, 1.0f, 1.0f, 1.0F);
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

	private enum LevelStage {
		First, Second, Third
	}

	private LevelStage drawLevelStage = LevelStage.First;

	private void drawLevelUp(int mouseX, int mouseY) {
		if (camera != null)
			setCameraToPlayer();
		mc.renderEngine.bindTexture(GuiResources.levelUpPopup);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(width / 2 - 52, height / 2 - 66, 104, 113, 0, 0, 104f / 256f, 113f / 256f, zLevel);
		drawString(fontRenderer, "HP", width / 2 - 43, height / 2 - 54, 0xFFFFFF);
		drawString(fontRenderer, "Attack", width / 2 - 43, height / 2 - 38, 0xFFFFFF);
		drawString(fontRenderer, "Defence", width / 2 - 43, height / 2 - 22, 0xFFFFFF);
		drawString(fontRenderer, "Sp. Attack", width / 2 - 43, height / 2 - 6, 0xFFFFFF);
		drawString(fontRenderer, "Sp. Defence", width / 2 - 43, height / 2 + 10, 0xFFFFFF);
		drawString(fontRenderer, "Speed", width / 2 - 43, height / 2 + 26, 0xFFFFFF);
		if (drawLevelStage == LevelStage.First) {
			drawString(fontRenderer, "+" + (ClientBattleManager.levelUpList.get(0).statsLevel2.HP - ClientBattleManager.levelUpList.get(0).statsLevel1.HP),
					width / 2 + 25, height / 2 - 54, 0xFFFFFF);
			drawString(fontRenderer, "+"
					+ (ClientBattleManager.levelUpList.get(0).statsLevel2.Attack - ClientBattleManager.levelUpList.get(0).statsLevel1.Attack), width / 2 + 25,
					height / 2 - 38, 0xFFFFFF);
			drawString(fontRenderer, "+"
					+ (ClientBattleManager.levelUpList.get(0).statsLevel2.Defence - ClientBattleManager.levelUpList.get(0).statsLevel1.Defence),
					width / 2 + 25, height / 2 - 22, 0xFFFFFF);
			drawString(fontRenderer, "+"
					+ (ClientBattleManager.levelUpList.get(0).statsLevel2.SpecialAttack - ClientBattleManager.levelUpList.get(0).statsLevel1.SpecialAttack),
					width / 2 + 25, height / 2 - 6, 0xFFFFFF);
			drawString(fontRenderer, "+"
					+ (ClientBattleManager.levelUpList.get(0).statsLevel2.SpecialDefence - ClientBattleManager.levelUpList.get(0).statsLevel1.SpecialDefence),
					width / 2 + 25, height / 2 + 10, 0xFFFFFF);
			drawString(fontRenderer, "+"
					+ (ClientBattleManager.levelUpList.get(0).statsLevel2.Speed - ClientBattleManager.levelUpList.get(0).statsLevel1.Speed), width / 2 + 25,
					height / 2 + 26, 0xFFFFFF);
		} else if (drawLevelStage == LevelStage.Second) {
			PixelmonStatsPacket stats = ClientBattleManager.levelUpList.get(0).statsLevel2;
			drawString(fontRenderer, "" + stats.HP, stats.HP < 100 ? width / 2 + 28 : width / 2 + 22, height / 2 - 54, 0xFFFFFF);
			drawString(fontRenderer, "" + stats.Attack, stats.Attack < 100 ? width / 2 + 28 : width / 2 + 22, height / 2 - 38, 0xFFFFFF);
			drawString(fontRenderer, "" + stats.Defence, stats.Defence < 100 ? width / 2 + 28 : width / 2 + 22, height / 2 - 22, 0xFFFFFF);
			drawString(fontRenderer, "" + stats.SpecialAttack, stats.SpecialAttack < 100 ? width / 2 + 28 : width / 2 + 22, height / 2 - 6, 0xFFFFFF);
			drawString(fontRenderer, "" + stats.SpecialDefence, stats.SpecialDefence < 100 ? width / 2 + 28 : width / 2 + 22, height / 2 + 10, 0xFFFFFF);
			drawString(fontRenderer, "" + stats.Speed, stats.Speed < 100 ? width / 2 + 28 : width / 2 + 22, height / 2 + 26, 0xFFFFFF);
		}

		mc.renderEngine.bindTexture(GuiResources.battleGui3);

		String name = "";
		if (ServerStorageDisplay.get(ClientBattleManager.levelUpList.get(0).pokemonID).nickname.equals(""))
			name = ServerStorageDisplay.get(ClientBattleManager.levelUpList.get(0).pokemonID).name;
		else
			name = ServerStorageDisplay.get(ClientBattleManager.levelUpList.get(0).pokemonID).nickname;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(width / 2 - guiWidth / 2, height - guiHeight, guiWidth, guiHeight, 0, 0, 1, 146f / 480f, zLevel);
		drawCenteredString(fontRenderer, "Your " + name + " has grown to level " + ClientBattleManager.levelUpList.get(0).level + "!", width / 2, height - 35,
				0xFFFFFF);
		flashCount++;
		if (flashCount > 30) {
			mc.renderEngine.bindTexture(GuiResources.battleGui3);
			GuiHelper.drawImageQuad(width / 2 + 130, height - 15, 10, 6, 611f / 640f, 149f / 480f, 628f / 640f, 159f / 480f, zLevel);
			if (flashCount > 60)
				flashCount = 0;
		}
	}

	private void drawYesNoDialog(int mouseX, int mouseY) {
		mc.renderEngine.bindTexture(GuiResources.yesNo);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(width / 2 - 256 / 2, height / 2 - 50, 256, 100, 0, 0, 1, 100f / 128f, zLevel);

		Attack newAttack = ClientBattleManager.newAttackList.get(0).attack;
		float textAreaWidth = 170;
		if (selectedAttack == -1) {
			float textWidth = fontRenderer.getStringWidth("Do you want to give up learning " + newAttack.baseAttack.attackName + "?");
			int numLines = (int) (textWidth / textAreaWidth) + 1;
			fontRenderer.drawSplitString("Do you want to give up learning " + newAttack.baseAttack.attackName + "?", width / 2 - 109, height / 2 + 1 - numLines
					* 10 / 2, (int) textAreaWidth, 0x000000);
			fontRenderer.drawSplitString("Do you want to give up learning " + newAttack.baseAttack.attackName + "?", width / 2 - 110, height / 2 - numLines
					* 10 / 2, (int) textAreaWidth, 0xFFFFFF);
		} else {
			String text = "Do you want to replace " + attacks[selectedAttack].baseAttack.attackName + " with " + newAttack.baseAttack.attackName + "?";
			float textWidth = fontRenderer.getStringWidth(text);
			int numLines = (int) (textWidth / textAreaWidth) + 1;
			fontRenderer.drawSplitString(text, width / 2 - 109, height / 2 + 1 - numLines * 10 / 2, (int) textAreaWidth, 0x000000);
			fontRenderer.drawSplitString(text, width / 2 - 110, height / 2 - numLines * 10 / 2, (int) textAreaWidth, 0xFFFFFF);
		}
		mc.renderEngine.bindTexture(GuiResources.yesNo);
		if (mouseX > width / 2 + 63 && mouseX < width / 2 + 108 && mouseY > height / 2 - 33 && mouseY < height / 2 - 7)
			GuiHelper.drawImageQuad(width / 2 + 63, height / 2 - 33, 45, 26, 154f / 256f, 101f / 128f, 199f / 256f, 127f / 128f, zLevel);
		if (mouseX > width / 2 + 63 && mouseX < width / 2 + 108 && mouseY > height / 2 + 5 && mouseY < height / 2 + 31)
			GuiHelper.drawImageQuad(width / 2 + 63, height / 2 + 5, 45, 26, 154f / 256f, 101f / 128f, 199f / 256f, 127f / 128f, zLevel);
		drawString(fontRenderer, "Yes", width / 2 + 76, height / 2 - 23, 0xFFFFFF);
		drawString(fontRenderer, "No", width / 2 + 80, height / 2 + 15, 0xFFFFFF);
	}

	private int selectedAttack = -1;
	private Attack[] attacks = new Attack[4];

	private void drawReplaceAttack(int mouseX, int mouseY) {
		mc.renderEngine.bindTexture(GuiResources.chooseMove);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(width / 2 - 256 / 2, height / 2 - 102, 256, 205, 0, 0, 1, 205f / 256f, zLevel);
		PixelmonDataPacket pokemonToLearnAttack = ServerStorageDisplay.get(ClientBattleManager.newAttackList.get(0).pokemonID);
		for (int i = 0; i < pokemonToLearnAttack.numMoves; i++) {
			PixelmonMovesetDataPacket move = pokemonToLearnAttack.moveset[i];
			drawString(fontRenderer, move.attackName, width / 2 + 11, height / 2 - 85 + 22 * i, 0xFFFFFF);
			drawString(fontRenderer, move.pp + "/" + move.ppBase, width / 2 + 90, height / 2 - 83 + 22 * i, 0xFFFFFF);
			float x = move.type.textureX;
			float y = move.type.textureY;
			mc.renderEngine.bindTexture(GuiResources.types);
			GuiHelper.drawImageQuad(width / 2 - 30, height / 2 - 92 + 22 * i, 38, 21, x / 256f, y / 128f, (x + 38f) / 256f, (y + 21f) / 128f, zLevel);
		}
		for (int i = 0; i < pokemonToLearnAttack.numMoves; i++) {
			if (mouseX > width / 2 - 30 && mouseX < width / 2 + 120 && mouseY > height / 2 - 94 + 22 * i && mouseY < height / 2 - 94 + 22 * (i + 1)) {
				mc.renderEngine.bindTexture(GuiResources.chooseMove);
				GuiHelper.drawImageQuad(width / 2 - 30, height / 2 - 94 + 22 * i, 152, 24, 97f / 256f, 209f / 256f, 249f / 256f, 234f / 256f, zLevel);
				if (attacks[i] == null || !attacks[i].baseAttack.attackName.equals(pokemonToLearnAttack.moveset[i].attackName))
					attacks[i] = DatabaseMoves.getAttack(pokemonToLearnAttack.moveset[i].attackName);
				drawMoveInfo(attacks[i]);
			}
		}

		Attack newAttack = ClientBattleManager.newAttackList.get(0).attack;

		drawString(fontRenderer, newAttack.baseAttack.attackName, width / 2 + 11, height / 2 - 78 + 22 * 4, 0xFFFFFF);
		drawString(fontRenderer, newAttack.pp + "/" + newAttack.ppBase, width / 2 + 90, height / 2 - 76 + 22 * 4, 0xFFFFFF);
		float x = newAttack.baseAttack.attackType.textureX;
		float y = newAttack.baseAttack.attackType.textureY;
		mc.renderEngine.bindTexture(GuiResources.types);
		GuiHelper.drawImageQuad(width / 2 - 30, height / 2 + 3, 38, 21, x / 256f, y / 128f, (x + 38f) / 256f, (y + 21f) / 128f, zLevel);
		if (mouseX > width / 2 - 30 && mouseX < width / 2 + 120 && mouseY > height / 2 + 3 && mouseY < height / 2 + 25) {
			mc.renderEngine.bindTexture(GuiResources.chooseMove);
			GuiHelper.drawImageQuad(width / 2 - 30, height / 2 + 1, 152, 24, 97f / 256f, 209f / 256f, 249f / 256f, 234f / 256f, zLevel);
			drawMoveInfo(newAttack);
		}
		String numString = "";
		if (pokemonToLearnAttack.getNationalPokedexNumber() < 10)
			numString = "00" + pokemonToLearnAttack.getNationalPokedexNumber();
		else if (pokemonToLearnAttack.getNationalPokedexNumber() < 100)
			numString = "0" + pokemonToLearnAttack.getNationalPokedexNumber();
		else
			numString = "" + pokemonToLearnAttack.getNationalPokedexNumber();
		int var9;
		if (pokemonToLearnAttack.isShiny)
			mc.renderEngine.bindTexture(GuiResources.shinySprite(numString));
		else
			mc.renderEngine.bindTexture(GuiResources.sprite(numString));
		GuiHelper.drawImageQuad(width / 2 - 114, height / 2 - 76, 64f, 64f, 0f, 0f, 1f, 1f, zLevel);
		drawCenteredString(fontRenderer, pokemonToLearnAttack.nickname.equals("") ? pokemonToLearnAttack.name : pokemonToLearnAttack.nickname, width / 2 - 82,
				height / 2 + 8, 0xFFFFFF);

		drawString(fontRenderer, "Effect", width / 2 - 96, height / 2 + 38, 0xFFFFFF);
		drawString(fontRenderer, "Description", width / 2 - 20, height / 2 + 38, 0xFFFFFF);
	}

	private void drawMoveInfo(Attack attack) {
		drawString(fontRenderer, "Power", width / 2 - 120, height / 2 + 58, 0xFFFFFF);
		drawString(fontRenderer, "Accuracy", width / 2 - 120, height / 2 + 78, 0xFFFFFF);
		int bpextra = 0, acextra = 0;

		if (attack.baseAttack.basePower >= 100)
			bpextra = fontRenderer.getCharWidth('0');
		if (attack.baseAttack.accuracy >= 100)
			acextra = fontRenderer.getCharWidth('0');
		if (attack.baseAttack.basePower != -1)
			drawString(fontRenderer, "" + attack.baseAttack.basePower, width / 2 - 55 - bpextra, height / 2 + 58, 0xFFFFFF);
		else
			drawString(fontRenderer, "--", width / 2 - 55 - bpextra, height / 2 + 58, 0xFFFFFF);

		if (attack.baseAttack.accuracy == -1)
			drawString(fontRenderer, "--", width / 2 - 55 - acextra, height / 2 + 78, 0xFFFFFF);
		else
			drawString(fontRenderer, "" + attack.baseAttack.accuracy, width / 2 - 55 - acextra, height / 2 + 78, 0xFFFFFF);

		fontRenderer.drawSplitString(attack.baseAttack.description, width / 2 - 25, height / 2 + 55, 100, 0xFFFFFF);
	}

	private int startIndex = 0;

	private void drawUseBag(int mouseX, int mouseY) {
		mc.renderEngine.bindTexture(GuiResources.itemGui2);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(width / 2 - 256 / 2, height / 2 - 102, 256, 205, 0, 0, 1, 205f / 256f, zLevel);
		if (mouseX > width / 2 + 63 && mouseX < width / 2 + 111 && mouseY > height / 2 - 91 && mouseY < height / 2 - 74)
			GuiHelper.drawImageQuad(width / 2 + 63, height / 2 - 91, 48, 17, 198f / 256f, 234 / 256f, 246f / 256f, 251f / 256f, zLevel);
		drawString(fontRenderer, "Back", width / 2 + 76, height / 2 - 85, 0xFFFFFF);

		drawCenteredString(fontRenderer, bagSection.displayString, width / 2 - 40, height / 2 - 80, 0xFFFFFF);

		for (int i = startIndex; i < 6 + startIndex; i++) {
			if (i < ClientBattleManager.bagStore.size()) {
				mc.renderEngine.bindTexture(GuiResources.itemGui2);
				if (mouseX > width / 2 - 98 && mouseX < width / 2 - 98 + 187 && mouseY > height / 2 - 44 + (i - startIndex) * 21
						&& mouseY < height / 2 - 24 + (i - startIndex) * 21)
					GuiHelper.drawImageQuad(width / 2 - 98, height / 2 - 44 + (i - startIndex) * 21, 187, 20, 3f / 256f, 206 / 256f, 194f / 256f, 225f / 256f,
							zLevel);
				else
					GuiHelper.drawImageQuad(width / 2 - 98, height / 2 - 44 + (i - startIndex) * 21, 187, 20, 3f / 256f, 227 / 256f, 194f / 256f, 246f / 256f,
							zLevel);
				Item item = PixelmonItems.getItem(ClientBattleManager.bagStore.get(i).id);
				if (item == null)
					item = PixelmonItemsPokeballs.getItemFromID(ClientBattleManager.bagStore.get(i).id);
				drawString(fontRenderer, item.getItemDisplayName(null), width / 2 - 55, height / 2 - 38 + (i - startIndex) * 21, 0xFFFFFF);
				drawString(fontRenderer, "x" + ClientBattleManager.bagStore.get(i).count, width / 2 + 55, height / 2 - 38 + (i - startIndex) * 21, 0xFFFFFF);
			}
		}

		mc.renderEngine.bindTexture(GuiResources.itemGui2);
		if (startIndex > 0) {
			if (mouseX > width / 2 - 11 && mouseX < width / 2 + 6 && mouseY > height / 2 - 55 && mouseY < height / 2 - 45)
				GuiHelper.drawImageQuad(width / 2 - 11, height / 2 - 55, 17, 10, 211f / 256f, 220 / 256f, 228f / 256f, 230f / 256f, zLevel);
		} else
			GuiHelper.drawImageQuad(width / 2 - 11, height / 2 - 55, 17, 10, 10f / 256f, 10 / 256f, 27f / 256f, 20f / 256f, zLevel);
		if (startIndex + 6 < ClientBattleManager.bagStore.size() - 1) {
			if (mouseX > width / 2 - 11 && mouseX < width / 2 + 6 && mouseY > height / 2 + 82 && mouseY < height / 2 + 92)
				GuiHelper.drawImageQuad(width / 2 - 11, height / 2 + 82, 17, 10, 236f / 256f, 220 / 256f, 253f / 256f, 230f / 256f, zLevel);
		} else
			GuiHelper.drawImageQuad(width / 2 - 11, height / 2 + 82, 17, 10, 10f / 256f, 10 / 256f, 27f / 256f, 20f / 256f, zLevel);
		for (int i = startIndex; i < 6 + startIndex; i++) {
			if (i < ClientBattleManager.bagStore.size()) {
				Item item = PixelmonItems.getItem(ClientBattleManager.bagStore.get(i).id);
				if (item == null)
					item = PixelmonItemsPokeballs.getItemFromID(ClientBattleManager.bagStore.get(i).id);
				itemRenderer.renderItemIntoGUI(this.fontRenderer, this.mc.renderEngine, new ItemStack(item), width / 2 - 85, height / 2 - 42 + (i - startIndex)
						* 21);
			}
		}

	}

	private void drawChooseBag(int mouseX, int mouseY) {
		mc.renderEngine.bindTexture(GuiResources.itemGui1);
		setCameraToPlayer();

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(width / 2 - 256 / 2, height / 2 - 76, 256, 153, 0, 0, 1, 153f / 256f, zLevel);
		drawCenteredString(fontRenderer, "Poke Balls", width / 2 + 53, height / 2 - 36, 0xFFFFFF);
		drawCenteredString(fontRenderer, "HP/PP Restore", width / 2 + 53, height / 2 + 31, 0xFFFFFF);
		drawCenteredString(fontRenderer, "Status Restore", width / 2 - 53, height / 2 - 36, 0xFFFFFF);
		drawCenteredString(fontRenderer, "Battle Items", width / 2 - 53, height / 2 + 31, 0xFFFFFF);
		int x1, x2, y1, y2;
		x1 = width / 2 - 103;
		x2 = width / 2 + 3;
		y1 = height / 2 - 63;
		y2 = height / 2 + 4;
		mc.renderEngine.bindTexture(GuiResources.itemGui1);
		int buttonWidth = 100, buttonHeight = 62;
		if (mouseX > x1 && mouseX < x1 + buttonWidth && mouseY > y1 && mouseY < y1 + buttonHeight)
			GuiHelper.drawImageQuad(x1, y1, buttonWidth, buttonHeight, 28f / 256f, 176f / 256f, 128f / 256f, 238f / 256f, zLevel);
		if (mouseX > x1 && mouseX < x1 + buttonWidth && mouseY > y2 && mouseY < y2 + buttonHeight)
			GuiHelper.drawImageQuad(x1, y2, buttonWidth, buttonHeight, 28f / 256f, 176f / 256f, 128f / 256f, 238f / 256f, zLevel);
		if (mouseX > x2 && mouseX < x2 + buttonWidth && mouseY > y1 && mouseY < y1 + buttonHeight)
			GuiHelper.drawImageQuad(x2, y1, buttonWidth, buttonHeight, 28f / 256f, 176f / 256f, 128f / 256f, 238f / 256f, zLevel);
		if (mouseX > x2 && mouseX < x2 + buttonWidth && mouseY > y2 && mouseY < y2 + buttonHeight)
			GuiHelper.drawImageQuad(x2, y2, buttonWidth, buttonHeight, 28f / 256f, 176f / 256f, 128f / 256f, 238f / 256f, zLevel);

		if (mouseX > width / 2 + 106 && mouseX < width / 2 + 126 && mouseY > height / 2 + 55 && mouseY < height / 2 + 77)
			GuiHelper.drawImageQuad(width / 2 + 106, height / 2 + 55, 20, 22, 234f / 256f, 154f / 256f, 254f / 256f, 176f / 256f, zLevel);
	}

	int flashCount = 0;

	private void drawMessageScreen() {
		mc.renderEngine.bindTexture(GuiResources.battleGui3);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(width / 2 - guiWidth / 2, height - guiHeight, guiWidth, guiHeight, 0, 0, 1, 146f / 480f, zLevel);
		if (mode != BattleMode.Waiting || ClientBattleManager.hasMoreMessages()) {
			drawCenteredString(fontRenderer, ClientBattleManager.getNextMessage(), width / 2, height - 35, 0xFFFFFF);
			flashCount++;
			if (flashCount > 30) {
				mc.renderEngine.bindTexture(GuiResources.battleGui3);
				GuiHelper.drawImageQuad(width / 2 + 130, height - 15, 10, 6, 611f / 640f, 149f / 480f, 628f / 640f, 159f / 480f, zLevel);
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

	private void drawMainMenu(int mouseX, int mouseY) {
		setCameraToPixelmon();
		mc.renderEngine.bindTexture(GuiResources.battleGui1);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(width / 2 - guiWidth / 2, height - guiHeight, guiWidth, guiHeight, 0, 0, 1, 146f / 480f, zLevel);
		drawButton(width / 2 + 31, height - guiHeight + 9, 48, 16, "FIGHT", mouseX, mouseY, 0);
		mc.renderEngine.bindTexture(GuiResources.battleGui1);
		drawButton(width / 2 + 31, height - guiHeight + 35, 48, 16, "BAG", mouseX, mouseY, 1);
		mc.renderEngine.bindTexture(GuiResources.battleGui1);
		drawButton(width / 2 + 90, height - guiHeight + 9, 48, 16, "POKEMON", mouseX, mouseY, 2);
		mc.renderEngine.bindTexture(GuiResources.battleGui1);
		drawButton(width / 2 + 90, height - guiHeight + 35, 48, 16, "RUN", mouseX, mouseY, 3);
		if (ClientBattleManager.getUserPokemonPacket() != null)
			drawString(fontRenderer, "What will " + ClientBattleManager.getUserPokemonPacket().name + " do?", width / 2 - 130, height - 35, 0xFFFFFF);
	}

	boolean isHealing = false;

	int healAmount = 0;

	private void drawChoosePokemon(int mouseX, int mouseY) {
		isHealing = false;
		setCameraToPlayer();

		if (mode == BattleMode.ApplyToPokemon && pixelmonToHeal != null) {
			if (pixelmonToHeal.health >= healAmount || pixelmonToHeal.health >= pixelmonToHeal.hp) {
				PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.BagPacket, itemToUse.id, battleControllerIndex, 0));
				removeItem(itemToUse);
				mode = BattleMode.Waiting;
				return;
			} else {
				isHealing = true;
			}
		}

		mc.renderEngine.bindTexture(GuiResources.choosePokemon);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(width / 2 - 128, height - 203, 256, 203, 0, 0, 1, 203f / 256f, zLevel);

		if (mode == BattleMode.ApplyToPokemon)
			drawCenteredString(fontRenderer, "Select a Pokemon to use on", width / 2 - 40, height - 23, 0xFFFFFF);
		else
			drawCenteredString(fontRenderer, "Choose a Pokemon to send out", width / 2 - 40, height - 23, 0xFFFFFF);

		if (mode != BattleMode.EnforcedSwitch) {
			if (mouseX > width / 2 + 63 && mouseX < width / 2 + 63 + 48 && mouseY > height - 27 && mouseY < height - 27 + 17 && !isHealing) {
				mc.renderEngine.bindTexture(GuiResources.choosePokemon);
				GuiHelper.drawImageQuad(width / 2 + 63, height - 27, 48, 17, 198f / 256f, 210f / 256f, 246f / 256, 227f / 256f, zLevel);
			}
			drawString(fontRenderer, "Back", width / 2 + 75, height - 22, 0xFFFFFF);
		}

		PixelmonDataPacket p = ClientBattleManager.getUserPokemonPacket();
		String numString = "";
		if (p.getNationalPokedexNumber() < 10)
			numString = "00" + p.getNationalPokedexNumber();
		else if (p.getNationalPokedexNumber() < 100)
			numString = "0" + p.getNationalPokedexNumber();
		else
			numString = "" + p.getNationalPokedexNumber();
		if (p.isShiny)
			mc.renderEngine.bindTexture(GuiResources.shinySprite(numString));
		else
			mc.renderEngine.bindTexture(GuiResources.sprite(numString));
		GuiHelper.drawImageQuad(width / 2 - 121, height - 176, 24f, 24f, 0f, 0f, 1f, 1f, zLevel);
		drawHealthBar(width / 2 - 85, height - 135, 56, 9, p);
		mc.renderEngine.bindTexture(GuiResources.choosePokemon);
		GuiHelper.drawImageQuad(width / 2 - 95, height - 135, 61, 9, 86f / 256f, 240f / 256f, 147f / 256f, 249f / 256f, zLevel);
		drawCenteredString(fontRenderer, p.health + "/" + p.hp, width / 2 - 59, height - 123, 0xffffff);
		drawString(fontRenderer, p.nickname.equals("") ? p.name : p.nickname, width / 2 - 90, height - 161, 0xffffff);
		drawString(fontRenderer, "Lv. " + p.lvl, width / 2 - 90, height - 148, 0xffffff);
		mc.renderEngine.bindTexture(GuiResources.choosePokemon);
		if (p.isMale)
			GuiHelper.drawImageQuad(width / 2 - 60, height - 149, 6, 9, 32f / 256f, 208f / 256f, 38f / 256f, 217f / 256f, zLevel);
		else
			GuiHelper.drawImageQuad(width / 2 - 60, height - 149, 6, 9, 32f / 256f, 218f / 256f, 38f / 256f, 227f / 256f, zLevel);

		if (mode == BattleMode.ApplyToPokemon && !isHealing) {
			if (mouseX > width / 2 - 120 && mouseX < width / 2 - 21 && mouseY > height - 165 && mouseY < height - 113)
				mc.renderEngine.bindTexture(GuiResources.selectCurrentPokemon);
			GuiHelper.drawImageQuad(width / 2 - 120, height - 165, 89, 52, 0, 0, 1, 1, zLevel);
		}

		int pos = -1;
		for (int i = 0; i < 6; i++) {
			if (i != p.order) {
				pos++;
				PixelmonDataPacket pdata = ServerStorageDisplay.pokemon[i];
				if (pdata != null) {

					numString = "";
					if (pdata.getNationalPokedexNumber() < 10)
						numString = "00" + pdata.getNationalPokedexNumber();
					else if (pdata.getNationalPokedexNumber() < 100)
						numString = "0" + pdata.getNationalPokedexNumber();
					else
						numString = "" + pdata.getNationalPokedexNumber();
					if (pdata.isShiny)
						mc.renderEngine.bindTexture(GuiResources.shinySprite(numString));
					else
						mc.renderEngine.bindTexture(GuiResources.sprite(numString));
					GuiHelper.drawImageQuad(width / 2 - 23, height - 192 + pos * 30, 24f, 24f, 0f, 0f, 1f, 1f, zLevel);
					drawHealthBar(width / 2 + 65, height - 192 + pos * 30, 56, 9, pdata);
					mc.renderEngine.bindTexture(GuiResources.choosePokemon);
					GuiHelper.drawImageQuad(width / 2 + 55, height - 192 + pos * 30, 61, 9, 86f / 256f, 240f / 256f, 147f / 256f, 249f / 256f, zLevel);
					drawString(fontRenderer, pdata.health + "/" + pdata.hp, width / 2 + 75, height - 180 + pos * 30, 0xffffff);
					drawString(fontRenderer, pdata.nickname.equals("") ? pdata.name : pdata.nickname, width / 2 + 5, height - 190 + pos * 30, 0xffffff);
					drawString(fontRenderer, "Lv. " + pdata.lvl, width / 2 + 5, height - 176 + pos * 30, 0xffffff);
					mc.renderEngine.bindTexture(GuiResources.choosePokemon);
					if (pdata.isMale)
						GuiHelper.drawImageQuad(width / 2 + 40, height - 176 + pos * 30, 6, 9, 32f / 256f, 208f / 256f, 38f / 256f, 217f / 256f, zLevel);
					else
						GuiHelper.drawImageQuad(width / 2 + 40, height - 176 + pos * 30, 6, 9, 32f / 256f, 218f / 256f, 38f / 256f, 227f / 256f, zLevel);

					int xpos = width / 2 - 30;
					int ypos = height - 195 + pos * 30;
					if (mouseX > xpos && mouseX < xpos + 150 && mouseY > ypos + 1 && mouseY < ypos + 31 && !isHealing)
						// if (mode==BattleMode.ApplyToPokemon &&
						// PixelmonItems.getItem(itemToUse.id) instanceof
						// ItemRevive)
						// else
						if (!pdata.isFainted)
							GuiHelper.drawImageQuad(xpos, ypos, 150, 32, 43f / 256f, 205f / 256f, 194f / 256f, 237f / 256f, zLevel);
				}
			}
		}
	}

	public void drawHealthBar(int x, int y, int width, int height, PixelmonDataPacket p) {
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		Tessellator tessellator = Tessellator.instance;
		GL11.glDisable(3553); // GL_TEXTURE_2D
		tessellator.startDrawingQuads();

		int barWidth = width - 6;
		tessellator.setColorRGBA_F(0.4F, 0.4F, 0.4F, 1.0F);
		tessellator.addVertex(x, y, 0.0);
		tessellator.addVertex(x, y + height, 0.0);
		tessellator.addVertex(x + barWidth, y + height, 0.0);
		tessellator.addVertex(x + barWidth, y, 0.0);

		// Get value as percent.
		// Get Width of bar
		float Percent = ((float) (p.health)) / ((float) p.hp);
		float CurWidth = Percent * (float) (barWidth);

		if (p.health <= (p.hp / 5))
			tessellator.setColorRGBA_F(0.8F, 0.0F, 0.0F, 1.0F);
		else if (p.health <= (p.hp / 2))
			tessellator.setColorRGBA_F(1.0F, 1.0F, 0.4F, 1.0F);
		else
			tessellator.setColorRGBA_F(0.2F, 1F, 0.2F, 1.0F);

		tessellator.addVertex(x, y, 0.0);
		tessellator.addVertex(x, y + height, 0.0);
		tessellator.addVertex(x + CurWidth, y + height, 0.0);
		tessellator.addVertex(x + CurWidth, y, 0.0);
		tessellator.draw();
		GL11.glPopMatrix();
		GL11.glEnable(3553);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
	}

	private void drawChooseAttack(int mouseX, int mouseY) {
		mc.renderEngine.bindTexture(GuiResources.battleGui2);
		setCameraToPlayer();

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiHelper.drawImageQuad(width / 2 - guiWidth / 2, height - guiHeight, guiWidth, guiHeight, 0, 0, 1, 146f / 480f, zLevel);
		PixelmonMovesetDataPacket[] moveset = ClientBattleManager.getUserPokemonPacket().moveset;
		int numMoves = ClientBattleManager.getUserPokemonPacket().numMoves;
		if (numMoves > 0) {
			drawButton(width / 2 - 141, height - guiHeight + 9, 87, 20, moveset[0].attackName, mouseX, mouseY, 0);
		}
		if (numMoves > 1) {
			mc.renderEngine.bindTexture(GuiResources.battleGui2);
			drawButton(width / 2 - 50, height - guiHeight + 9, 87, 20, moveset[1].attackName, mouseX, mouseY, 1);
		}
		if (numMoves > 2) {
			mc.renderEngine.bindTexture(GuiResources.battleGui2);
			drawButton(width / 2 - 141, height - guiHeight + 33, 87, 20, moveset[2].attackName, mouseX, mouseY, 2);
		}
		if (numMoves > 3) {
			mc.renderEngine.bindTexture(GuiResources.battleGui2);
			drawButton(width / 2 - 50, height - guiHeight + 33, 87, 20, moveset[3].attackName, mouseX, mouseY, 3);
		}
		if (moveset[mouseOverButton] != null) {
			drawCenteredString(fontRenderer, "PP: " + moveset[mouseOverButton].pp + "/" + moveset[mouseOverButton].ppBase, width / 2 + 99, height - guiHeight
					+ 18, 0xFFFFFF);
			drawString(fontRenderer, "Type: ", width / 2 + 99 - fontRenderer.getStringWidth("Type: " + moveset[mouseOverButton].type.toString()) / 2, height
					- guiHeight + 33, 0xFFFFFF);
			fontRenderer.drawString(
					moveset[mouseOverButton].type.toString(),
					width / 2 + 99 - fontRenderer.getStringWidth("Type: " + moveset[mouseOverButton].type.toString()) / 2
							+ fontRenderer.getStringWidth("Type: "), height - guiHeight + 33, moveset[mouseOverButton].type.getColor());
		}
		if (mouseX > width / 2 + 137 && mouseX < width / 2 + 148 && mouseY > height - 11 && mouseY < height - 1) {
			mc.renderEngine.bindTexture(GuiResources.battleGui2);
			GuiHelper.drawImageQuad(width / 2 + 137, height - 11, 11, 10, 613f / 640f, 151f / 480f, 635f / 640f, 171f / 480f, zLevel);
		}

	}

	private int mouseOverButton = 0;

	private void drawButton(int x, int y, int buttonWidth, int buttonHeight, String string, int mouseX, int mouseY, int ind) {
		if (mode == BattleMode.MainMenu) {
			if (mouseX > x && mouseX < x + buttonWidth && mouseY > y && mouseY < y + buttonHeight) {
				GuiHelper.drawImageQuad(x, y, buttonWidth, buttonHeight, 387f / 640f, 158f / 480f, 489f / 640f, 196f / 480f, zLevel);
			}
			drawCenteredString(fontRenderer, string, x + buttonWidth / 2, y + buttonHeight / 2 - 3, 0xFFFFFF);
		} else if (mode == BattleMode.ChooseAttack) {
			GuiHelper.drawImageQuad(x, y, buttonWidth, buttonHeight, 206f / 640f, 152f / 480f, 393f / 640f, 202f / 480f, zLevel);
			if (mouseX > x && mouseX < x + buttonWidth && mouseY > y && mouseY < y + buttonHeight) {
				GuiHelper.drawImageQuad(x + 2, y + 2, buttonWidth - 5, buttonHeight - 4, 23f / 640f, 155f / 480f, 200f / 640f, 195f / 480f, zLevel);
				mouseOverButton = ind;
			}
			drawCenteredString(fontRenderer, string, x + buttonWidth / 2, y + buttonHeight / 2 - 3, 0xFFFFFF);
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
		if (mode == BattleMode.YesNo)
			YesNoDialogClicked(mouseX, mouseY);
		else if (ClientBattleManager.hasLevelUps() || ClientBattleManager.hasNewAttacks()) {
			if (!ClientBattleManager.hasNewAttacks()
					|| (ClientBattleManager.hasLevelUps() && ClientBattleManager.levelUpList.get(0).level <= ClientBattleManager.newAttackList.get(0).level))
				LevelUpClick(mouseX, mouseY);
			else
				ReplaceAttackClicked(mouseX, mouseY);
		} else if (mode == BattleMode.MainMenu) {
			int x1 = width / 2 + 31;
			int y1 = height - guiHeight + 9;
			int x2 = width / 2 + 90;
			int y2 = height - guiHeight + 35;
			int w = 48, h = 16;
			if (mouseX > x1 && mouseX < x1 + w && mouseY > y1 && mouseY < y1 + h) {
				mode = BattleMode.ChooseAttack;
				setCameraToPixelmon();
			} else if (mouseX > x2 && mouseX < x2 + w && mouseY > y1 && mouseY < y1 + h) {
				if (!ClientBattleManager.canSwitch) {
					ClientBattleManager.addMessage("You can't switch pokemon right now!");
				} else {
					mode = BattleMode.ChoosePokemon;
				}
			} else if (mouseX > x1 && mouseX < x1 + w && mouseY > y2 && mouseY < y2 + h) {
				mode = BattleMode.ChooseBag;
			} else if (mouseX > x2 && mouseX < x2 + w && mouseY > y2 && mouseY < y2 + h) {
				if (ClientBattleManager.opponentType == ParticipantType.WildPokemon && ClientBattleManager.canSwitch) {
					PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.Flee, 0));
					mode = BattleMode.Waiting;
				} else {
					if (!ClientBattleManager.canSwitch)
						ClientBattleManager.addMessage(ClientBattleManager.getUserPokemon().getNickname() + " is trapped! You cannot flee!");
					else
						ClientBattleManager.addMessage("You can't run from a trainer battle!");
				}
			}
			return;

		} else if (mode == BattleMode.ChooseAttack) {
			ChooseAttackClick(mouseX, mouseY);
		} else if (mode == BattleMode.ChoosePokemon || mode == BattleMode.EnforcedSwitch) {
			ChoosePokemonClick(mouseX, mouseY);
		} else if (mode == BattleMode.ApplyToPokemon) {
			ApplyToPokemonClick(mouseX, mouseY);
		} else if (mode == BattleMode.ChooseBag) {
			ChooseBagClick(mouseX, mouseY);
		} else if (mode == BattleMode.UseBag) {
			UseBagClick(mouseX, mouseY);
		}
	}

	private void LevelUpClick(int mouseX, int mouseY) {
		if (drawLevelStage == LevelStage.First)
			drawLevelStage = LevelStage.Second;
		else {
			ClientBattleManager.levelUpList.remove(0);
			if (ClientBattleManager.hasLevelUps())
				drawLevelStage = LevelStage.First;
			else {
				if (battleControllerIndex == -1 && !ClientBattleManager.hasNewAttacks()) {
					restoreSettingsAndClose();
					return;
				} else {
					mode = oldMode;
				}

			}
		}
	}

	private void ChooseAttackClick(int mouseX, int mouseY) {
		if (mouseX > width / 2 + 137 && mouseX < width / 2 + 148 && mouseY > height - 11 && mouseY < height - 1) {
			mode = BattleMode.MainMenu;
			return;
		}
		int x1 = width / 2 - 141;
		int x2 = width / 2 - 50;
		int y1 = height - guiHeight + 9;
		int y2 = height - guiHeight + 33;
		int w = 87, h = 20;

		PixelmonMovesetDataPacket[] moveset = ClientBattleManager.getUserPokemonPacket().moveset;
		int numMoves = ClientBattleManager.getUserPokemonPacket().numMoves;
		if (mouseX > x1 && mouseX < x1 + w && mouseY > y1 && mouseY < y1 + h && numMoves > 0 && moveset[0].pp > 0 && !moveset[0].disabled) {
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.ChooseAttack, 0, battleControllerIndex,
					ClientBattleManager.getUserPokemonPacket().pokemonID));
			mode = BattleMode.Waiting;
			setCameraToPixelmon();
			return;
		} else if (mouseX > x2 && mouseX < x2 + w && mouseY > y1 && mouseY < y1 + h && numMoves > 1 && moveset[1].pp > 0 && !moveset[1].disabled) {
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.ChooseAttack, 1, battleControllerIndex,
					ClientBattleManager.getUserPokemonPacket().pokemonID));
			mode = BattleMode.Waiting;
			setCameraToPixelmon();
			return;
		} else if (mouseX > x1 && mouseX < x1 + w && mouseY > y2 && mouseY < y2 + h && numMoves > 2 && moveset[2].pp > 0 && !moveset[2].disabled) {
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.ChooseAttack, 2, battleControllerIndex,
					ClientBattleManager.getUserPokemonPacket().pokemonID));
			mode = BattleMode.Waiting;
			setCameraToPixelmon();
			return;
		} else if (mouseX > x2 && mouseX < x2 + w && mouseY > y2 && mouseY < y2 + h && numMoves > 3 && moveset[3].pp > 0 && !moveset[3].disabled) {
			PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.ChooseAttack, 3, battleControllerIndex,
					ClientBattleManager.getUserPokemonPacket().pokemonID));
			mode = BattleMode.Waiting;
			setCameraToPixelmon();
			return;
		}
	}

	private void ChoosePokemonClick(int mouseX, int mouseY) {
		if (mode != BattleMode.EnforcedSwitch && mouseX > width / 2 + 63 && mouseX < width / 2 + 63 + 48 && mouseY > height - 27 && mouseY < height - 27 + 17) {
			mode = BattleMode.MainMenu;
			return;
		}
		int pos = 0;
		for (int i = 0; i < 6; i++) {
			if (i != ClientBattleManager.getUserPokemonPacket().order) {
				PixelmonDataPacket pdata = ServerStorageDisplay.pokemon[i];
				if (pdata != null) {
					int xpos = width / 2 - 30;
					int ypos = height - 195 + pos * 30;
					if (mouseX > xpos && mouseX < xpos + 150 && mouseY > ypos + 1 && mouseY < ypos + 31) {
						if (!pdata.isFainted) {
							PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.SwitchPokemon, pdata.order, battleControllerIndex, 0));
							mode = BattleMode.Waiting;
							return;
						}
					}
				}
				pos++;
			}
		}
	}

	private void YesNoDialogClicked(int mouseX, int mouseY) {
		if (mouseX > width / 2 + 63 && mouseX < width / 2 + 108 && mouseY > height / 2 - 33 && mouseY < height / 2 - 7) {
			if (selectedAttack != -1) {
				PacketDispatcher.sendPacketToServer(sendPacket);
			}
			ClientBattleManager.newAttackList.remove(0);
			if (battleControllerIndex != -1 || ClientBattleManager.hasNewAttacks() || ClientBattleManager.hasLevelUps())
				mode = oldMode;
			else {
				restoreSettingsAndClose();
				return;
			}
		}
		if (mouseX > width / 2 + 63 && mouseX < width / 2 + 108 && mouseY > height / 2 + 5 && mouseY < height / 2 + 31) {
			mode = oldMode;
		}
	}

	private Packet250CustomPayload sendPacket;

	public static BattleMode oldMode;

	public static List<Integer> evolveList = new ArrayList<Integer>();

	private void ReplaceAttackClicked(int mouseX, int mouseY) {
		Attack newAttack = ClientBattleManager.newAttackList.get(0).attack;
		PixelmonDataPacket pokemonToLearnAttack = ServerStorageDisplay.get(ClientBattleManager.newAttackList.get(0).pokemonID);

		for (int i = 0; i < pokemonToLearnAttack.numMoves; i++) {
			if (mouseX > width / 2 - 30 && mouseX < width / 2 + 120 && mouseY > height / 2 - 94 + 22 * i && mouseY < height / 2 - 94 + 22 * (i + 1)) {
				sendPacket = PacketCreator.createPacket(EnumPackets.ReplaceMove, pokemonToLearnAttack.pokemonID, newAttack.baseAttack.attackIndex, i);
				selectedAttack = i;
				oldMode = mode;
				mode = BattleMode.YesNo;
			}
		}

		if (mouseX > width / 2 - 30 && mouseX < width / 2 + 120 && mouseY > height / 2 + 3 && mouseY < height / 2 + 25) {
			oldMode = mode;
			mode = BattleMode.YesNo;
			selectedAttack = -1;
		}
	}

	private void ApplyToPokemonClick(int mouseX, int mouseY) {
		if (mouseX > width / 2 + 63 && mouseX < width / 2 + 63 + 48 && mouseY > height - 27 && mouseY < height - 27 + 17) {
			mode = BattleMode.UseBag;
			return;
		}
		int pokemonToApplyTo = -1;
		if (mouseX > width / 2 - 120 && mouseX < width / 2 - 21 && mouseY > height - 165 && mouseY < height - 113) {
			pokemonToApplyTo = ClientBattleManager.getUserPokemonPacket().order;
		}
		int pos = 0;
		for (int i = 0; i < 6; i++) {
			if (i != ClientBattleManager.getUserPokemonPacket().order) {
				PixelmonDataPacket pdata = ServerStorageDisplay.pokemon[i];
				if (pdata != null) {
					int xpos = width / 2 - 30;
					int ypos = height - 195 + pos * 30;
					if (mouseX > xpos && mouseX < xpos + 150 && mouseY > ypos + 1 && mouseY < ypos + 31) {
						pokemonToApplyTo = i;
					}
				}
				pos++;
			}
		}
		if (pokemonToApplyTo != -1) {
			if (ServerStorageDisplay.pokemon[pokemonToApplyTo].isFainted// &&
																		// PixelmonItems.getItem(itemToUse.id)
																		// instanceof
																		// ItemRevive)
			)
				return;
			if (PixelmonItems.getItem(itemToUse.id) instanceof ItemPotion) {
				pixelmonToHeal = ServerStorageDisplay.pokemon[pokemonToApplyTo];
				healAmount = pixelmonToHeal.health + ((ItemPotion) PixelmonItems.getItem(itemToUse.id)).type.getHealAmount();
				Timer timer = new Timer();
				timer.scheduleAtFixedRate(new HealerTask(), 100, 100);
			}
		}
	}

	private ItemData itemToUse = null;

	private void UseBagClick(int mouseX, int mouseY) {
		if (mouseX > width / 2 - 11 && mouseX < width / 2 + 6 && mouseY > height / 2 - 55 && mouseY < height / 2 - 45) {
			if (startIndex > 0) {
				startIndex--;
				return;
			}
		}
		if (mouseX > width / 2 - 11 && mouseX < width / 2 + 6 && mouseY > height / 2 + 82 && mouseY < height / 2 + 92) {
			if (startIndex + 6 < ClientBattleManager.bagStore.size() - 1) {
				startIndex++;
				return;
			}
		}

		if (mouseX > width / 2 + 63 && mouseX < width / 2 + 111 && mouseY > height / 2 - 91 && mouseY < height / 2 - 74) {
			mode = BattleMode.ChooseBag;
			return;
		}

		for (int i = startIndex; i < 6 + startIndex; i++) {
			if (i < ClientBattleManager.bagStore.size()) {
				if (mouseX > width / 2 - 98 && mouseX < width / 2 - 98 + 187 && mouseY > height / 2 - 44 + (i - startIndex) * 21
						&& mouseY < height / 2 - 24 + (i - startIndex) * 21) {
					itemToUse = ClientBattleManager.bagStore.get(i);
					if (bagSection == bagSection.Pokeballs) {
						PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.BagPacket, ClientBattleManager.bagStore.get(i).id,
								battleControllerIndex, 0));
						mode = BattleMode.Waiting;
					} else {
						mode = BattleMode.ApplyToPokemon;
					}
					ItemStack[] inv = mc.thePlayer.inventory.mainInventory;
					((PixelmonItem) PixelmonItems.getItem(itemToUse.id)).removeFromInventory(inv);
				}
			}
		}
	}

	private void ChooseBagClick(int mouseX, int mouseY) {
		if (mouseX > width / 2 + 106 && mouseX < width / 2 + 126 && mouseY > height / 2 + 55 && mouseY < height / 2 + 77)
			mode = BattleMode.MainMenu;

		int x1, x2, y1, y2;
		x1 = width / 2 - 103;
		x2 = width / 2 + 3;
		y1 = height / 2 - 63;
		y2 = height / 2 + 4;
		int buttonWidth = 100, buttonHeight = 62;
		bagSection = null;
		if (mouseX > x1 && mouseX < x1 + buttonWidth && mouseY > y1 && mouseY < y1 + buttonHeight)
			bagSection = BagSection.StatusRestore;

		else if (mouseX > x1 && mouseX < x1 + buttonWidth && mouseY > y2 && mouseY < y2 + buttonHeight)
			bagSection = BagSection.BattleItems;

		else if (mouseX > x2 && mouseX < x2 + buttonWidth && mouseY > y1 && mouseY < y1 + buttonHeight) {
			if (ClientBattleManager.opponentType == ParticipantType.WildPokemon && !ClientBattleManager.opponent.isBoss)
				bagSection = BagSection.Pokeballs;
			else {
				ClientBattleManager.addMessage("You can't use Poke Balls in this battle!");
			}
		}

		else if (mouseX > x2 && mouseX < x2 + buttonWidth && mouseY > y2 && mouseY < y2 + buttonHeight)
			bagSection = BagSection.HP;

		if (bagSection != null) {
			mode = BattleMode.UseBag;
			ClientBattleManager.bagStore.clear();
			getInventory();
			startIndex = 0;
		}
	}

	private PixelmonDataPacket pixelmonToHeal = null;

	class HealerTask extends TimerTask {
		public void run() {
			pixelmonToHeal.health++;
			if (pixelmonToHeal.health >= pixelmonToHeal.hp)
				this.cancel();
		}
	}

	@Override
	public void drawBackground(int par1) {
	}

	@Override
	public void drawDefaultBackground() {
	}

	private void getInventory() {
		InventoryPlayer inventory = mc.thePlayer.inventory;
		for (int i = 0; i < inventory.mainInventory.length; i++) {
			if (bagSection == BagSection.Pokeballs) {
				if (inventory.mainInventory[i] != null && inventory.mainInventory[i].getItem() instanceof ItemPokeBall)
					checkExists(inventory.mainInventory[i].itemID, inventory.mainInventory[i].stackSize);
			} else if (bagSection == BagSection.HP) {
				if (inventory.mainInventory[i] != null && inventory.mainInventory[i].getItem() instanceof ItemPotion)
					checkExists(inventory.mainInventory[i].itemID, inventory.mainInventory[i].stackSize);
				else if (inventory.mainInventory[i] != null && inventory.mainInventory[i].getItem() instanceof ItemEther)
					checkExists(inventory.mainInventory[i].itemID, inventory.mainInventory[i].stackSize);
			}
		}
	}

	private void removeItem(ItemData itemToUse) {
		InventoryPlayer inventory = mc.thePlayer.inventory;
		for (int i = 0; i < inventory.mainInventory.length; i++) {
			if (inventory.mainInventory[i] != null && inventory.mainInventory[i].itemID == itemToUse.id) {
				inventory.mainInventory[i].stackSize--;
				return;
			}
		}
	}

	private void checkExists(int itemID, int count) {
		boolean hasItem = false;
		for (ItemData d : ClientBattleManager.bagStore) {
			if (d.id == itemID) {
				hasItem = true;
				d.count += count;
			}
		}
		if (!hasItem)
			ClientBattleManager.bagStore.add(new ItemData(itemID, count));

	}
	
	 /**
     * Draws the screen and all the components in it.
     */
	@Override
    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        int k = this.guiLeft;
        int l = this.guiTop;
        this.drawGuiContainerBackgroundLayer(par3, par1, par2);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
       
        //Forge: Force lighting to be disabled as there are some issue where lighting would
        //incorrectly be applied based on items that are in the inventory.
        GL11.glDisable(GL11.GL_LIGHTING);
        this.drawGuiContainerForegroundLayer(par1, par2);
        GL11.glEnable(GL11.GL_LIGHTING);
    }

}
