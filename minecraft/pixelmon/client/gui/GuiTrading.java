package pixelmon.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.blocks.TileEntityTradeMachine;
import pixelmon.blocks.TradingRegistry;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.comm.EnumPackets;
import pixelmon.comm.PacketCreator;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.gui.ContainerEmpty;

public class GuiTrading extends GuiContainer {

	private int tradeIndex = -1;
	public boolean ready = false;
	public TileEntityTradeMachine entity;
	private int selected = -1;
	
	public GuiTrading(int tradeIndex) {
		super(new ContainerEmpty());
		this.tradeIndex = tradeIndex;
		entity = TradingRegistry.getTileEntity(tradeIndex);
	}
	
	protected void drawEntity(EntityLiving entity, int par1, int par2, int par3, float par4, float par5)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par1, (float)par2, 50.0F);
        GL11.glScalef((float)(-par3), (float)par3, (float)par3);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float var6 = entity.renderYawOffset;
        float var7 = entity.rotationYaw;
        float var8 = entity.rotationPitch;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(par5 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        entity.renderYawOffset = (float)Math.atan((double)(par4 / 40.0F)) * 20.0F;
        entity.rotationYaw = (float)Math.atan((double)(par4 / 40.0F)) * 40.0F;
        entity.rotationPitch = -((float)Math.atan((double)(par5 / 40.0F))) * 20.0F;
        entity.rotationYawHead = entity.rotationYaw;
        GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        entity.renderYawOffset = var6;
        entity.rotationYaw = var7;
        entity.rotationPitch = var8;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
	
    public void onGuiClosed() {
    	entity.playerCount--;
    }

    @Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		int var6 = var5.getScaledWidth();
		int var7 = var5.getScaledHeight();

		int bg = mc.renderEngine.getTexture("/pixelmon/gui/tradeGui.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(bg);
		drawTexturedModalRect((width - 256) / 2, (height - 206) / 2, 0, 0, 256, 204);

		for (PixelmonDataPacket p : ServerStorageDisplay.pokemon) {
			int offset = 0;
			if (p != null) {
				String displayName = p.name;
				if (!p.nickname.equals(""))
					displayName = p.nickname;

				int i = p.order;
				String numString = "";
				if (p.getNationalPokedexNumber() < 10)
					numString = "00" + p.getNationalPokedexNumber();
				else if (p.getNationalPokedexNumber() < 100)
					numString = "0" + p.getNationalPokedexNumber();
				else
					numString = "" + p.getNationalPokedexNumber();
				int var9;
				if (p.isShiny)
					var9 = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/shinysprites/" + numString + ".png");
				else
					var9 = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/sprites/" + numString + ".png");
				drawImageQuad(var9, width / 2 - 93 + 25 * i, height / 2 + 68, 24f, 24f, 0f, 0f, 1f, 1f);
				if (p.heldItemId != -1) {
					var9 = Minecraft.getMinecraft().renderEngine.getTexture("/pixelmon/image/helditem.png");
					drawImageQuad(var9, width / 2 - 97 + 25 * i+18, height / 2 + 68+18, 6, 6, 0f, 0f, 1f, 1f);
				}
			}
		}
		
		/**Characters**/
		
		if (entity.player1 !=null)
			drawEntity(entity.player1, (width - 210) / 2, (height - 82) / 2, 20, 0, 0);
		else{
			GL11.glPushMatrix();
			bg = mc.renderEngine.getTexture("/pixelmon/gui/tradeGui.png");
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			mc.renderEngine.bindTexture(bg);
			GL11.glScalef(1.5f, 1.5f, 0f);
			drawTexturedModalRect((width - 75), (height - 265), 227, 242, 10, 14);
			GL11.glPopMatrix();
		}
		if (entity.player2 !=null)
			drawEntity(entity.player2, (width - 60) / 2, (height - 82) / 2, 20, 0, 0);
		else{
			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glScalef(1.5f, 1.5f, 0f);
			mc.renderEngine.bindTexture(bg);
			drawTexturedModalRect(width/3 + 15, height/3 - 46, 227, 242, 10, 14);
			GL11.glPopMatrix();
		}
		
		/**Stats**/
		
		GL11.glPushMatrix();
		GL11.glScalef(0.5f, 0.5f, 0f);
		
		drawString(fontRenderer, "HP:", (width + 87), (height - 153), 0xFFFFFF);
		drawString(fontRenderer, "Attack:", (width + 87), (height - 137), 0xFFFFFF);
		drawString(fontRenderer, "Defence:", (width + 87), (height - 121), 0xFFFFFF);
		drawString(fontRenderer, "SP.Attack:", (width + 87), (height - 105), 0xFFFFFF);
		drawString(fontRenderer, "SP.Defense:", (width + 87), (height - 89), 0xFFFFFF);
		drawString(fontRenderer, "Speed:", (width + 87), (height - 74), 0xFFFFFF);
		if (entity.pokemon2!=null){
			drawString(fontRenderer, String.valueOf(entity.pokemon2.stats.HP), (width + 87), (height - 153), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(entity.pokemon2.stats.Attack), (width + 87), (height - 137), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(entity.pokemon2.stats.Defence), (width + 87), (height - 121), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(entity.pokemon2.stats.SpecialAttack), (width + 87), (height - 105), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(entity.pokemon2.stats.SpecialDefence), (width + 87), (height - 89), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(entity.pokemon2.stats.Speed), (width + 87), (height - 74), 0xFFFFFF);
		}else
			for(int i = 0; i < 6; i++){
				drawString(fontRenderer, "?", (width + 227), (height - 153) + i * 16, 0xFFFFFF);
			}
		drawString(fontRenderer, "HP:", (width - 183), (height - 153), 0xFFFFFF);
		drawString(fontRenderer, "Attack:", (width - 183), (height - 137), 0xFFFFFF);
		drawString(fontRenderer, "Defence:", (width - 183), (height - 121), 0xFFFFFF);
		drawString(fontRenderer, "SP.Attack:", (width - 183), (height - 105), 0xFFFFFF);
		drawString(fontRenderer, "SP.Defense:", (width - 183), (height - 89), 0xFFFFFF);
		drawString(fontRenderer, "Speed:", (width - 183), (height - 74), 0xFFFFFF);
		if (entity.pokemon1!=null)
		{
			drawString(fontRenderer, String.valueOf(entity.pokemon1.stats.HP), (width - 183), (height - 153), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(entity.pokemon1.stats.Attack), (width - 183), (height - 137), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(entity.pokemon1.stats.Defence), (width - 183), (height - 121), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(entity.pokemon1.stats.SpecialAttack), (width - 183), (height - 105), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(entity.pokemon1.stats.SpecialDefence), (width - 183), (height - 89), 0xFFFFFF);
			drawString(fontRenderer, String.valueOf(entity.pokemon1.stats.Speed), (width - 183), (height - 74), 0xFFFFFF);
		}else
			for(int i = 0; i < 6; i++){
			drawString(fontRenderer, "?", (width - 43), (height - 153) + i * 16, 0xFFFFFF);
			}
		GL11.glPopMatrix();
		
		if (entity.pokemon1!=null)
			drawEntity(entity.pokemon1, (width - 210) / 2, (height - 82) / 2, 20, 0, 0);
		else{
			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glScalef(1.5f, 1.5f, 0f);
			mc.renderEngine.bindTexture(bg);
			drawTexturedModalRect(width/3 - 75, height/3 - 20, 227, 242, 10, 14);
			GL11.glPopMatrix();
		}
		if (entity.pokemon2!=null)
			drawEntity(entity.pokemon2, (width - 60) / 2, (height - 82) / 2, 20, 0, 0);
		else{
			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glScalef(1.5f, 1.5f, 0f);
			mc.renderEngine.bindTexture(bg);
			drawTexturedModalRect(width/3 + 15, height/3 - 20, 227, 242, 10, 14);
			GL11.glPopMatrix();
		}
		
		/**Buttons**/
		
		drawString(fontRenderer, "Ready", (width + 130) / 2, (height + 157) / 2, 0xFFFFFF);
		drawButtonReady(var2, var3);
		
		if (entity.ready1 && entity.ready2){
			drawString(fontRenderer, "Trade", (width - 30) / 2, (height + 38) / 2, 0xFFFFFF);
			drawButtonTrade(var2, var3);
		}
		else
			drawString(fontRenderer, "Not Ready", (width - 45) / 2, (height + 38) / 2, 0xFFFFFF);
		
		drawPokemonSelection(var2, var3);
			
		GL11.glPushMatrix();
		GL11.glScalef(0.5f, 0.5f, 0f);
		if (entity.player1!=null)
			drawString(fontRenderer, entity.player1.username + " wants to trade", (width - 235), (height - 178), 0xFFFFFF);
		else
			drawString(fontRenderer, "No user found!", (width - 235), (height - 178), 0xFFFFFF);
		if (entity.player2!=null)
			drawString(fontRenderer, entity.player2.username + " wants to trade", (width - 230), (height - 178), 0xFFFFFF);
		else
			drawString(fontRenderer, "No user found!", (width + 35), (height - 178), 0xFFFFFF);
		GL11.glPopMatrix();
		
		if (selected != -1){
			GL11.glPushMatrix();
			GL11.glColor3f(0f, 1.0f, 0f);
			mc.renderEngine.bindTexture(bg);
			drawTexturedModalRect((width - 190 + selected*50) / 2, (height + 140) / 2, 1, 206, 26, 24);
			GL11.glPopMatrix();
		}
		
		GL11.glPushMatrix();
		GL11.glScalef(0.5f, 0.5f, 0f);
		fontRenderer.drawSplitString("Select a Pokemon", (width/2-118)*2, (height/2+77)*2, 50, 0xFFFFFF);
		GL11.glPopMatrix();
	}
	
    PixelmonDataPacket[] p = ServerStorageDisplay.pokemon;
    
	public int drawPokemonSelection(int par1, int par2){
		int bg = mc.renderEngine.getTexture("/pixelmon/gui/tradeGui.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(bg);
		if (par1 >= (width - 190) / 2 && par1 <= (width - 140) / 2 && par2 >= (height + 140) / 2 && par2 <= (height + 190) / 2 && p[0] != null){
			drawTexturedModalRect((width - 190) / 2, (height + 140) / 2, 1, 206, 26, 24);
			return 0;
		}
		if (par1 >= (width - 140) / 2 && par1 <= (width - 90) / 2 && par2 >= (height + 140) / 2 && par2 <= (height + 190) / 2 && p[1] != null){
			drawTexturedModalRect((width - 140) / 2, (height + 140) / 2, 1, 206, 26, 24);
			return 1;
		}
		if (par1 >= (width - 90) / 2 && par1 <= (width - 40) / 2 && par2 >= (height + 140) / 2 && par2 <= (height + 190) / 2 && p[2] != null){
			drawTexturedModalRect((width - 90) / 2, (height + 140) / 2, 1, 206, 26, 24);
			return 2;
		}
		if (par1 >= (width - 40) / 2 && par1 <= (width + 10) / 2 && par2 >= (height + 140) / 2 && par2 <= (height + 190) / 2 && p[3] != null){
			drawTexturedModalRect((width - 40) / 2, (height + 140) / 2, 1, 206, 26, 24);
			return 3;
		}
		if (par1 >= (width + 10) / 2 && par1 <= (width + 60) / 2 && par2 >= (height + 140) / 2 && par2 <= (height + 190) / 2 && p[4] != null){
			drawTexturedModalRect((width + 10) / 2, (height + 140) / 2, 1, 206, 26, 24);
			return 4;
		}
		if (par1 >= (width + 60) / 2 && par1 <= (width + 110) / 2 && par2 >= (height + 140) / 2 && par2 <= (height + 190) / 2 && p[5] != null){
			drawTexturedModalRect((width + 60) / 2, (height + 140) / 2, 1, 206, 26, 24);
			return 5;
		}
		
		return -1;
	}
	
	public boolean drawButtonTrade(int par1, int par2){
		int bg = mc.renderEngine.getTexture("/pixelmon/gui/tradeGui.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(bg);
		
		if (par1 >= (width - 72) / 2 && par1 <= (width + 70) / 2 && par2 >= (height + 26) / 2 && par2 <= (height + 62) / 2){
			drawTexturedModalRect((width - 72) / 2, (height + 26) / 2, 28, 205, 72, 19);
			drawString(fontRenderer, "Trade", (width - 30) / 2, (height + 38) / 2, 16777120);
			return true;
		}
		
		return false;
	}
	
	public boolean drawButtonReady(int par1, int par2){
		int bg = mc.renderEngine.getTexture("/pixelmon/gui/tradeGui.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(bg);
		
		if (par1 >= (width + 123) / 2 && par1 <= (width + 197) / 2 && par2 <= (height + 179) / 2 && par2 >= (height + 148) / 2){//Highlight
			drawTexturedModalRect((width + 122) / 2, (height + 148) / 2, 28, 225, 38, 16);
			drawString(fontRenderer, "Ready", (width + 130) / 2, (height + 157) / 2, 16777120);
			return true;
		}
		
		return false;
	}

    protected void mouseClicked(int par1, int par2, int par3){
    	super.mouseClicked(par1, par2, par3);
    	if (drawButtonReady(par1, par2) && selected >= 0){
    		this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
    		entity.ready((EntityPlayer)mc.thePlayer, selected);
    	}
    	if (drawButtonTrade(par1, par2)){
    		this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
    	}
    	if (drawPokemonSelection(par1, par2) != -1){
    		this.selected = drawPokemonSelection(par1, par2);
    	}
    }
	
	@Override
	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		GL11.glNormal3f(0.0F, -1.0F, 0.0F);
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
	
	@Override
	public void drawScreen(int par1, int par2, float par3){
		super.drawScreen(par1, par2, par3);
		
	}
}
