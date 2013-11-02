package pixelmon.client.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.RotationHelper;
import pixelmon.Pixelmon;
import pixelmon.client.PixelmonServerStore;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.client.camera.CameraTargetEntity;
import pixelmon.client.camera.GuiCamera;
import pixelmon.client.gui.battles.ClientBattleManager;
import pixelmon.client.gui.battles.GuiBattle;
import pixelmon.client.render.GraphicsHelper;
import pixelmon.client.render.RenderPixelmon;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonEntityList;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.stats.BaseStats;
import pixelmon.enums.EnumGui;
import pixelmon.gui.ContainerEmpty;

public class GuiEvolve extends GuiCamera {


	public EntityPixelmon currentPokemon;
	String newPokemon;
	String oldNickname;

	public GuiEvolve(int pokemonID) {
		super(Minecraft.getMinecraft().thePlayer.inventoryContainer);
		newPokemon = PixelmonServerStore.evolutionTarget;

		currentPokemon = getEntity(pokemonID);
		if (currentPokemon == null) {
			Minecraft mc = Minecraft.getMinecraft();
			PixelmonDataPacket p = ServerStorageDisplay.get(pokemonID);
			currentPokemon = (EntityPixelmon) PixelmonEntityList.createEntityByName(p.name, mc.theWorld);
			currentPokemon.setPositionAndRotation(mc.thePlayer.posX + 1, mc.thePlayer.posY + 1, mc.thePlayer.posZ + 1, mc.thePlayer.rotationYaw + 180,
					mc.thePlayer.rotationPitch);
			currentPokemon.setPokemonId(p.pokemonID);
			currentPokemon.setIsShiny(p.isShiny);
			currentPokemon.setGrowth(p.growth);
			currentPokemon.isMale = p.isMale;
			currentPokemon.releaseFromPokeball();
		}
		if (currentPokemon == null) {
			Minecraft.getMinecraft().thePlayer.closeScreen();
			return;
		}
		camera.setTarget(new CameraTargetEntity(currentPokemon));
		currentPokemon.evolvingInto = newPokemon;
		oldNickname = currentPokemon.getNickname();
		calcSizeDifference();
	}

	private void calcSizeDifference() {
		BaseStats bs = EntityPixelmon.getBaseStats(newPokemon);
		currentPokemon.heightDiff = bs.height - currentPokemon.baseStats.height;
		currentPokemon.widthDiff = bs.width - currentPokemon.baseStats.width;
		currentPokemon.lengthDiff = bs.length - currentPokemon.baseStats.length;
	}

	private EntityPixelmon getEntity(int pokemonID) {
		List<Entity> EntityList = Minecraft.getMinecraft().theWorld.loadedEntityList;
		for (int i = 0; i < EntityList.size(); i++) {
			Entity e = EntityList.get(i);
			if (e instanceof EntityPixelmon) {
				int existingId = ((EntityPixelmon) e).getPokemonId();
				if (existingId != -1) {
					if (((EntityPixelmon) e).getPokemonId() == pokemonID) {
						return (EntityPixelmon) e;
					}
				}
			}
		}
		return null;
	}

	@Override
	public void handleKeyboardInput() {
		return;
	}

	@Override
	public void drawBackground(int par1) {
	}

	@Override
	public void drawDefaultBackground() {
	}

	public void initGui() {
		super.initGui();
		buttonList.clear();
	}

	int ticks = 0;
	int fadeCount = 0;
	int stage = 0;

	@Override
	public void updateScreen() {
		super.updateScreen();
		ticks++;
		int ticks2 = ticks;
		if (ticks == 40 && stage == 0) {
			currentPokemon.evolving = 1;
			stage = 1;
			ticks = 0;
		}
		if (stage == 1) {
			int numeffects = currentPokemon.getRNG().nextInt(10);
			for (int i = 0; i < numeffects; i++)
				currentPokemon.worldObj.spawnParticle("reddust", currentPokemon.posX + (currentPokemon.getRNG().nextFloat() * 2.2f - 1)
						* currentPokemon.baseStats.width, currentPokemon.posY + (currentPokemon.getRNG().nextFloat() * 2.2f - 1)
						* currentPokemon.baseStats.height, currentPokemon.posZ + (currentPokemon.getRNG().nextFloat() * 2.2f - 1)
						* currentPokemon.baseStats.length, 255D, 255.0D, 255.0D);

			if (ticks >= 0 && ticks < 20)
				currentPokemon.evolvingVal++;
			else if (ticks == 20) {
				fadeCount++;
			}
			if (ticks >= 20 && ticks < 40)
				currentPokemon.evolvingVal--;
			if (ticks == 40 && fadeCount < 5)
				ticks = 0;
			if (ticks == 20 && fadeCount == 5) {
				stage = 2;
				currentPokemon.evolving = 2;
				currentPokemon.evolvingVal = 0;
				ticks = 0;
			}
		}
		if (stage == 2) {
			float length = currentPokemon.baseStats.length + ticks / 200f * currentPokemon.lengthDiff;
			float height = currentPokemon.baseStats.height + ticks / 200f * currentPokemon.heightDiff;
			float width = currentPokemon.baseStats.width + ticks / 200f * currentPokemon.widthDiff;
			int numeffects = currentPokemon.getRNG().nextInt(50);
			for (int i = 0; i < numeffects; i++)
				currentPokemon.worldObj.spawnParticle("reddust", currentPokemon.posX + (currentPokemon.getRNG().nextFloat() * 2.2f - 1) * width,
						currentPokemon.posY + (currentPokemon.getRNG().nextFloat() * 2.2f - 1) * height, currentPokemon.posZ
								+ (currentPokemon.getRNG().nextFloat() * 2.2f - 1) * length, 255D, 255.0D, 255.0D);
			currentPokemon.evolvingVal = ticks;
			if (ticks == 140)
				spawnEvolution();
			if (ticks == 200)
				stage = 3;
		}
		if (stage == 4) {
			currentPokemon.evolvingVal = 0;
			currentPokemon.evolving = 0;
		}
	}

	private void spawnEvolution() {
		Packet250CustomPayload packet = PacketCreator.createPacket(EnumPackets.Evolution, currentPokemon.getPokemonId());
		PacketDispatcher.sendPacketToServer(packet);
		EntityPixelmon p = (EntityPixelmon) PixelmonEntityList.createEntityByName(newPokemon, currentPokemon.worldObj);
		p.setPositionAndRotation(currentPokemon.posX, currentPokemon.posY, currentPokemon.posZ, currentPokemon.rotationYaw, currentPokemon.rotationPitch);
		p.evolving = -2;
		p.evolvingVal = ticks;
		p.heightDiff = -1 * currentPokemon.heightDiff;
		p.widthDiff = -1 * currentPokemon.widthDiff;
		p.lengthDiff = -1 * currentPokemon.lengthDiff;
		p.setGrowth(currentPokemon.getGrowth());
		p.setIsShiny(currentPokemon.getIsShiny());
		if (currentPokemon.getDataWatcher().getWatchableObjectShort(EntityPixelmon.dwRoasted) == (short) 1)
			p.getDataWatcher().updateObject(EntityPixelmon.dwRoasted, (short) 2);
		currentPokemon.stopRender = true;
		currentPokemon.catchInPokeball();
		currentPokemon.setDead();
		currentPokemon.unloadEntity();
		
		currentPokemon = p;
		currentPokemon.releaseFromPokeball();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		mc.renderEngine.bindTexture(GuiResources.evo);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if (stage != 2)
			GuiHelper.drawImageQuad(width / 2 - 120, height / 4 - 40, 240, 40, 0, 0, 1, 1, zLevel);
		String s;
		if (stage == 0) {
			s = "Huh?";
			fontRenderer.drawString(s, width / 2 - fontRenderer.getStringWidth(s) / 2, height / 4 - 30, 0xFFFFFF);
		}
		if (stage == 1) {
			s = "What? " + currentPokemon.getNickname() + " is evolving!";
			fontRenderer.drawString(s, width / 2 - fontRenderer.getStringWidth(s) / 2, height / 4 - 30, 0xFFFFFF);
			int xPos = width / 2 - 30;
			int yPos = height / 4 - 15;
			if (mouseX >= xPos && mouseX <= xPos + 60 && mouseY >= yPos && mouseY <= yPos + 17)
				mc.renderEngine.bindTexture(GuiResources.buttonOver);
			else
				mc.renderEngine.bindTexture(GuiResources.button);
			GuiHelper.drawImageQuad(xPos, yPos, 60, 17, 0, 0, 1, 1, zLevel);
			s = "Cancel";
			fontRenderer.drawString(s, width / 2 - fontRenderer.getStringWidth(s) / 2, height / 4 - 11, 0xFFFFFF);
		}
		if (stage == 3) {
			s = "Your " + oldNickname + " evolved into a " + currentPokemon.getName() + "!";
			fontRenderer.drawString(s, width / 2 - fontRenderer.getStringWidth(s) / 2, height / 4 - 30, 0xFFFFFF);
		}
		if (stage == 4) {
			s = "Evolution cancelled!";
			fontRenderer.drawString(s, width / 2 - fontRenderer.getStringWidth(s) / 2, height / 4 - 30, 0xFFFFFF);
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int par3) {
		if (stage == 1) {
			int xPos = width / 2 - 30;
			int yPos = height / 4 - 15;
			if (mouseX >= xPos && mouseX <= xPos + 60 && mouseY >= yPos && mouseY <= yPos + 17) {
				Packet250CustomPayload packet = PacketCreator.createPacket(EnumPackets.StopEvolution, currentPokemon.getPokemonId());
				PacketDispatcher.sendPacketToServer(packet);
				stage = 4;
			}
		} else if (stage == 3 || stage == 4) {
			Minecraft.getMinecraft().thePlayer.closeScreen();
			if (GuiBattle.evolveList.size() > 0) {
				int pokemonID = GuiBattle.evolveList.get(0);
				GuiBattle.evolveList.remove(0);
				Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.Evolution.getIndex(), Minecraft.getMinecraft().theWorld, pokemonID, 0, 0);
			} else if (PixelmonServerStore.bossDrops != null)
				Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.ItemDrops.getIndex(), Minecraft.getMinecraft().theWorld, 0, 0, 0);
			else if (ClientBattleManager.newAttackList.size() > 0)
				Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.LearnMove.getIndex(), Minecraft.getMinecraft().theWorld, 0, 0, 0);
			Minecraft.getMinecraft().renderViewEntity = Minecraft.getMinecraft().thePlayer;
		}
	}

	@Override
	public void onGuiClosed() {
		currentPokemon.setDead();
		currentPokemon.unloadEntity();		
		super.onGuiClosed();
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		int k = this.guiLeft;
		int l = this.guiTop;
		this.drawGuiContainerBackgroundLayer(par3, par1, par2);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);

		// Forge: Force lighting to be disabled as there are some issue where
		// lighting would
		// incorrectly be applied based on items that are in the inventory.
		GL11.glDisable(GL11.GL_LIGHTING);
		this.drawGuiContainerForegroundLayer(par1, par2);
		GL11.glEnable(GL11.GL_LIGHTING);
	}
}