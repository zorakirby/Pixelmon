package pixelmon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import pixelmon.api.PixelmonApi;
import pixelmon.client.models.fossils.ModelFossil;
import pixelmon.client.render.RenderOldHook;
import pixelmon.entities.npcs.NPCType;
import pixelmon.entities.pixelmon.helpers.DropItemHelper;
import pixelmon.entities.pixelmon.interactions.InteractionEther;
import pixelmon.entities.pixelmon.interactions.InteractionEvolutionStone;
import pixelmon.entities.pixelmon.interactions.InteractionHeldItem;
import pixelmon.entities.pixelmon.interactions.InteractionPokedex;
import pixelmon.entities.pixelmon.interactions.InteractionPotion;
import pixelmon.entities.pixelmon.interactions.InteractionRareCandy;
import pixelmon.entities.pixelmon.interactions.InteractionStatusAilment;
import pixelmon.entities.pixelmon.interactions.InteractionTM;
import pixelmon.entities.projectiles.EntityOldHook;
import pixelmon.gui.GuiHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {
	public void registerRenderers() {
	}

	public World GetClientWorld() {
		return null;
	}

	public GuiHandler guiHandler = new GuiHandler();

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return guiHandler.getServerGuiElement(ID, player, world, x, y, z);
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	public int addArmor(String name){
		return RenderingRegistry.addNewArmourRendererPrefix(name);
	}
	
	public void registerPacketHandlers() {
	}

	public void registerKeyBindings() {
	}

	public ModelBase loadModel(String name) {
		return null;
	}

	public ModelBase getNPCModel(NPCType type, String model) {
		return null;
	}

	public void registerSounds() {
	}

	public void registerTickHandlers() {
	}

	public void loadEvents() {
	}

	public Object[] getModels() {
		return null;
	}

	public ModelFossil loadFossilModel(String modelName) {
		return null;
	}
	
	public void registerBossDropItem(Item item){
		DropItemHelper.bossDropItems.add(item);
	}
	
	public void registerInteractions(){
		PixelmonApi.addInteraction(new InteractionPokedex());
		PixelmonApi.addInteraction(new InteractionTM());
		PixelmonApi.addInteraction(new InteractionEther());
		PixelmonApi.addInteraction(new InteractionEvolutionStone());
		PixelmonApi.addInteraction(new InteractionHeldItem());
		PixelmonApi.addInteraction(new InteractionPotion());
		PixelmonApi.addInteraction(new InteractionRareCandy());
		PixelmonApi.addInteraction(new InteractionStatusAilment());
	}
}
