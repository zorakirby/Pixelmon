package pixelmon.client.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.RotationHelper;
import pixelmon.Pixelmon;
import pixelmon.client.EntityCamera;
import pixelmon.client.PixelmonServerStore;
import pixelmon.client.ServerStorageDisplay;
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

public class GuiEvolve extends GuiContainer {
	public static ResourceLocation evo = new ResourceLocation("pixelmon:gui/EvolutionPopup.png");

	public EntityPixelmon currentPokemon;
	String newPokemon;
	EntityCamera camera;
	String oldNickname;

	public GuiEvolve(EntityPixelmon start, EntityPixelmon end) {
		super(new ContainerEmpty());
	}

	public GuiEvolve(int pokemonID) {
		super(new ContainerEmpty());
		newPokemon = PixelmonServerStore.evolutionTarget;

		currentPokemon = getEntity(pokemonID);
		if (currentPokemon == null) {
			Minecraft.getMinecraft().thePlayer.closeScreen();
			return;
		}
		camera = new EntityCamera(Minecraft.getMinecraft().theWorld);
		camera.setLocationAndAngles(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ,
				0.0f, 0.0F);
		Minecraft.getMinecraft().theWorld.spawnEntityInWorld(camera);
		Minecraft.getMinecraft().renderViewEntity = camera;
		camera.target = currentPokemon;
		currentPokemon.evolvingInto = newPokemon;
		oldNickname = currentPokemon.getNickname();
		calcSizeDifference();
	}

	private void calcSizeDifference() {
		BaseStats bs = EntityPixelmon.getBaseStats(newPokemon);
		currentPokemon.heightDiff = bs.height / currentPokemon.baseStats.height;
		currentPokemon.widthDiff = bs.width / currentPokemon.baseStats.width;
		currentPokemon.lengthDiff = bs.length / currentPokemon.baseStats.length;
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
		super.updateScreen();
	}

	private void spawnEvolution() {
		Packet250CustomPayload packet = PacketCreator.createPacket(EnumPackets.Evolution, currentPokemon.getPokemonId());
		PacketDispatcher.sendPacketToServer(packet);
		EntityPixelmon p = (EntityPixelmon) PixelmonEntityList.createEntityByName(newPokemon, currentPokemon.worldObj);
		p.setPositionAndRotation(currentPokemon.posX, currentPokemon.posY, currentPokemon.posZ, currentPokemon.rotationYaw, currentPokemon.rotationPitch);
		p.evolving = 2;
		p.evolvingVal = ticks;
		p.heightDiff = 1f / currentPokemon.heightDiff;
		p.widthDiff = 1f / currentPokemon.widthDiff;
		p.lengthDiff = 1f / currentPokemon.lengthDiff;
		p.setGrowth(currentPokemon.getGrowth());
		p.setIsShiny(currentPokemon.getIsShiny());
		currentPokemon.stopRender = true;
		currentPokemon.catchInPokeball();
		currentPokemon = p;
		currentPokemon.releaseFromPokeball();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		mc.renderEngine.func_110577_a(evo);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if (stage != 2)
			GuiHelper.drawImageQuad(width / 2 - 100, height / 4 - 40, 200, 40, 0, 0, 1, 1, zLevel);
		String s;
		if (stage == 0) {
			s = "Huh?";
			fontRenderer.drawString(s, width / 2 - fontRenderer.getStringWidth(s) / 2, height / 4, 0xFFFFFF);
		}
		if (stage == 1) {
			s = "What? " + currentPokemon.getNickname() + " is evolving!";
			fontRenderer.drawString(s, width / 2 - fontRenderer.getStringWidth(s) / 2, height / 4, 0xFFFFFF);
		}
		if (stage == 3) {
			s = "Your " + oldNickname + " evolved into a " + currentPokemon.getName() + "!";
			fontRenderer.drawString(s, width / 2 - fontRenderer.getStringWidth(s) / 2, height / 4, 0xFFFFFF);
		}
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		if (stage == 3){
			Minecraft.getMinecraft().thePlayer.closeScreen();
			if (GuiBattle.evolveList.size() > 0) {
				int pokemonID = GuiBattle.evolveList.get(0);
				GuiBattle.evolveList.remove(0);
				Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.Evolution.getIndex(), Minecraft.getMinecraft().theWorld, pokemonID, 0, 0);
			}
			else if (ClientBattleManager.newAttackList.size()>0)
					Minecraft.getMinecraft().thePlayer.openGui(Pixelmon.instance, EnumGui.LearnMove.getIndex(), Minecraft.getMinecraft().theWorld, 0, 0, 0);
			Minecraft.getMinecraft().renderViewEntity = Minecraft.getMinecraft().thePlayer;
		}
	}

	@Override
	public void onGuiClosed() {
		currentPokemon.setDead();
		PacketDispatcher.sendPacketToServer(PacketCreator.createPacket(EnumPackets.SendPokemon, ServerStorageDisplay.pokemon[GuiPixelmonOverlay.selectedPixelmon].pokemonID));
		super.onGuiClosed();
	}
}