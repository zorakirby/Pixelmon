package pixelmon.render;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.MathHelper;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModelBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.RenderLiving;
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;

import pixelmon.Pixelmon;
import pixelmon.ServerStorageDisplay;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.helpers.LevelHelper;

public class RenderPixelmon extends RenderLiving {
	public RenderPixelmon(float par2) { // par2 = shadow size
		super(null, par2);
	}

	private LevelHelper lvlInstance;

	public void doRenderLiving(EntityLiving entityLiving, double d, double d1, double d2, float f, float f1) {
		EntityPixelmon pixelmon = (EntityPixelmon)entityLiving;
		mainModel = pixelmon.model;
		if (mainModel==null){
			if (pixelmon.getName().equals(""))
				return;
			pixelmon.init(((EntityPixelmon)entityLiving).getName());
			mainModel = ((EntityPixelmon)entityLiving).model;
		}
		super.doRenderLiving(entityLiving, d, d1, d2, f, f1);
		float var10 = entityLiving.getDistanceToEntity(this.renderManager.livingPlayer);
		if (var10 <= (float) 8 || pixelmon.hasOwner() || ServerStorageDisplay.contains(pixelmon.getPokemonId())) {
			lvlInstance = pixelmon.getClientLvl();
			drawHealthBar(entityLiving, d, d1, d2, f, f1);
			if (ServerStorageDisplay.contains(pixelmon.getPokemonId()))
				drawExpBar(entityLiving, d, d1, d2, f, f1);
			drawNameTag(entityLiving, d, d1, d2);
		}

	}

	public void drawNameTag(EntityLiving entityliving, double par2, double par4, double par6) {
		if (Minecraft.isGuiEnabled() && (entityliving instanceof EntityPixelmon)) {
			EntityPixelmon entitypixelmon = (EntityPixelmon) entityliving;
			PixelmonDataPacket p = null;
			if (ServerStorageDisplay.contains(entitypixelmon.getPokemonId()))
				p = ServerStorageDisplay.get(entitypixelmon.getPokemonId());
			boolean flag;
			if (p == null) {
				flag = true;
			} else {
				flag = MathHelper.stringNullOrLengthZero(p.nickname);
			}
			String s = "";
			if (lvlInstance != null)
				s = " Lv: " + lvlInstance.getLevel() + " ";
			s += (flag ? entitypixelmon.getName() : p.nickname);
			if (entitypixelmon.getOwner() != null) {
				s += " (" + ((EntityPlayer)entitypixelmon.getOwner()).username + ")";
			} else {
				s += " (Wild)";
			}
			if (!entitypixelmon.isSneaking()) {
				renderLivingLabel(entitypixelmon, s, par2, par4, par6, 64);
			}
		}
	}

	public void drawExpBar(EntityLiving entityLiving, double d, double d1, double d2, float f, float f1) {
		float f2 = 1.6F;
		float f3 = 0.01666667F * f2;
		if ((float) entityLiving.getDistanceToEntity(renderManager.livingPlayer) < 28F && Minecraft.isGuiEnabled()) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float) d + 0.0F, (float) d1 + 1.1F, (float) d2);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(-f3, -f3, f3);
			GL11.glDisable(2896 /* GL_LIGHTING */);
			GL11.glDepthMask(false);
			GL11.glDisable(2929 /* GL_DEPTH_TEST */);
			GL11.glEnable(3042 /* GL_BLEND */);
			GL11.glBlendFunc(770, 771);
			Tessellator tessellator = Tessellator.instance;
			byte byte0 = -20;
			GL11.glDisable(3553 /* GL_TEXTURE_2D */);
			tessellator.startDrawingQuads();
			float f5 = lvlInstance.getExp();
			float f6 = lvlInstance.getExpToNextLevel();
			if (f5 >= f6)
				f5 = 56;
			float f8 = 50F * (f5 / f6);
			tessellator.setColorRGBA_F(0.0039F, 0.03137F, 0.4196F, 1.0F);
			tessellator.addVertex(-25F + f8, -7 + byte0, 0.0D);
			tessellator.addVertex(-25F + f8, -6 + byte0, 0.0D);
			tessellator.addVertex(25D, -6 + byte0, 0.0D);
			tessellator.addVertex(25D, -7 + byte0, 0.0D);
			tessellator.setColorRGBA_F(0.0F, 0.8901F, 0.8901F, 1.0F);
			tessellator.addVertex(-25D, -7 + byte0, 0.0D);
			tessellator.addVertex(-25D, -6 + byte0, 0.0D);
			tessellator.addVertex(f8 - 25F, -6 + byte0, 0.0D);
			tessellator.addVertex(f8 - 25F, -7 + byte0, 0.0D);
			tessellator.draw();
			GL11.glEnable(3553 /* GL_TEXTURE_2D */);
			GL11.glEnable(2929 /* GL_DEPTH_TEST */);
			GL11.glDepthMask(true);
			GL11.glEnable(2896 /* GL_LIGHTING */);
			GL11.glDisable(3042 /* GL_BLEND */);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPopMatrix();
		}
	}

	public void drawHealthBar(EntityLiving entityLiving, double d, double d1, double d2, float f, float f1) {
		float f2 = 1.6F;
		float f3 = 0.01666667F * f2;
		if ((float) entityLiving.getDistanceToEntity(renderManager.livingPlayer) < 28F && Minecraft.isGuiEnabled()) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float) d + 0.0F, (float) d1 + 1.1F, (float) d2);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(-f3, -f3, f3);
			GL11.glDisable(2896 /* GL_LIGHTING */);
			GL11.glDepthMask(false);
			GL11.glDisable(2929 /* GL_DEPTH_TEST */);
			GL11.glEnable(3042 /* GL_BLEND */);
			GL11.glBlendFunc(770, 771);
			Tessellator tessellator = Tessellator.instance;
			byte byte0 = -25;
			GL11.glDisable(3553 /* GL_TEXTURE_2D */);
			tessellator.startDrawingQuads();
			float f5 = lvlInstance.health;
			float f6 = lvlInstance.maxHealth;
			float f8 = 50F * (f5 / f6);
			tessellator.setColorRGBA_F(1.0F, 0.0F, 0.0F, 1.0F);
			tessellator.addVertex(-25F + f8, -7 + byte0, 0.0D);
			tessellator.addVertex(-25F + f8, -6 + byte0, 0.0D);
			tessellator.addVertex(25D, -6 + byte0, 0.0D);
			tessellator.addVertex(25D, -7 + byte0, 0.0D);
			tessellator.setColorRGBA_F(0.0F, 1.0F, 0.0F, 1.0F);
			tessellator.addVertex(-25D, -7 + byte0, 0.0D);
			tessellator.addVertex(-25D, -6 + byte0, 0.0D);
			tessellator.addVertex(f8 - 25F, -6 + byte0, 0.0D);
			tessellator.addVertex(f8 - 25F, -7 + byte0, 0.0D);
			tessellator.draw();
			GL11.glEnable(3553 /* GL_TEXTURE_2D */);
			GL11.glEnable(2929 /* GL_DEPTH_TEST */);
			GL11.glDepthMask(true);
			GL11.glEnable(2896 /* GL_LIGHTING */);
			GL11.glDisable(3042 /* GL_BLEND */);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPopMatrix();
		}
	}

	protected void preRenderScale(EntityPixelmon entity, float f) {
		GL11.glScalef(entity.scale * entity.baseStats.giScale, entity.scale * entity.baseStats.giScale, entity.scale * entity.baseStats.giScale);
		if (entity.doesHover) {
			GL11.glTranslatef(0, -1 * entity.hoverHeight, 0);
		}
	}

	protected void preRenderCallback(EntityLiving entityliving, float f) {
		preRenderScale((EntityPixelmon) entityliving, f);
	}
}
