package pixelmon.render;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.MathHelper;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.ModelBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.OpenGlHelper;
import net.minecraft.src.RenderLiving;
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.Pixelmon;
import pixelmon.ServerStorageDisplay;
import pixelmon.comm.PixelmonDataPacket;
import pixelmon.config.PixelmonConfig;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.stats.LevelHelper;

public class RenderPixelmon extends RenderLiving {
	public RenderPixelmon(float par2) { // par2 = shadow size
		super(null, par2);
	}

	public void doRenderLiving(EntityLiving entityLiving, double d, double d1, double d2, float f, float f1) {
		EntityPixelmon pixelmon = (EntityPixelmon) entityLiving;
		mainModel = pixelmon.model;
		if (mainModel == null) {
			if (pixelmon.getName().equals(""))
				return;
			pixelmon.init(((EntityPixelmon) entityLiving).getName());
			pixelmon.loadModel();
			mainModel = ((EntityPixelmon) entityLiving).model;
		}
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1f);
		renderPixelmon((EntityPixelmon) entityLiving, d, d1, d2, f, f1);
		float var10 = entityLiving.getDistanceToEntity(this.renderManager.livingPlayer);
		if (var10 <= (float) 8 || pixelmon.getOwner() != null || ServerStorageDisplay.contains(pixelmon.getPokemonId())) {
			drawHealthBar(entityLiving, d, d1, d2, f, f1);
			if (ServerStorageDisplay.contains(pixelmon.getPokemonId()))
				drawExpBar(entityLiving, d, d1, d2, f, f1);
			drawNameTag(entityLiving, d, d1, d2);
		}

	}

	private float func_77034_a(float par1, float par2, float par3) {
		float var4;

		for (var4 = par2 - par1; var4 < -180.0F; var4 += 360.0F) {
			;
		}

		while (var4 >= 180.0F) {
			var4 -= 360.0F;
		}

		return par1 + par3 * var4;
	}

	public void renderPixelmon(EntityPixelmon pixelmon, double par2, double par4, double par6, float par8, float par9) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		this.mainModel.onGround = this.renderSwingProgress(pixelmon, par9);

		if (this.renderPassModel != null) {
			this.renderPassModel.onGround = this.mainModel.onGround;
		}

		this.mainModel.isRiding = pixelmon.isRiding();

		if (this.renderPassModel != null) {
			this.renderPassModel.isRiding = this.mainModel.isRiding;
		}

		this.mainModel.isChild = pixelmon.isChild();

		if (this.renderPassModel != null) {
			this.renderPassModel.isChild = this.mainModel.isChild;
		}

		try {
			float var10 = this.func_77034_a(pixelmon.prevRenderYawOffset, pixelmon.renderYawOffset, par9);
			float var11 = this.func_77034_a(pixelmon.prevRotationYawHead, pixelmon.rotationYawHead, par9);
			float var12 = pixelmon.prevRotationPitch + (pixelmon.rotationPitch - pixelmon.prevRotationPitch) * par9;
			this.renderLivingAt(pixelmon, par2, par4, par6);
			float var13 = this.handleRotationFloat(pixelmon, par9);
			this.rotateCorpse(pixelmon, var13, var10, par9);
			float var14 = 0.0625F;
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(-1.0F, -1.0F, 1.0F);
			this.preRenderCallback(pixelmon, par9);
			GL11.glTranslatef(0.0F, -24.0F * var14 - 0.0078125F, 0.0F);
			float var15 = pixelmon.prevLegYaw + (pixelmon.legYaw - pixelmon.prevLegYaw) * par9;
			float var16 = pixelmon.field_70754_ba - pixelmon.legYaw * (1.0F - par9);

			if (pixelmon.isChild()) {
				var16 *= 3.0F;
			}

			if (var15 > 1.0F) {
				var15 = 1.0F;
			}

			GL11.glEnable(GL11.GL_ALPHA_TEST);
			this.mainModel.setLivingAnimations(pixelmon, var16, var15, par9);
			if (!pixelmon.getIsRed())
				this.renderModel(pixelmon, var16, var15, var13, var11 - var10, var12, var14);
			else {
				GL11.glColor4f(0, 0, 0, 1F);
				this.renderModel(pixelmon, var16, var15, var13, var11 - var10, var12, var14);
			}
			float var19;
			int var18;
			float var20;
			float var22;

			for (int var17 = 0; var17 < 4; ++var17) {
				var18 = this.shouldRenderPass(pixelmon, var17, par9);

				if (var18 > 0) {
					this.renderPassModel.setLivingAnimations(pixelmon, var16, var15, par9);
					if (!pixelmon.getIsRed())
						this.renderPassModel.render(pixelmon, var16, var15, var13, var11 - var10, var12, var14);

					if (var18 == 15) {
						var19 = (float) pixelmon.ticksExisted + par9;
						this.loadTexture("%blur%/misc/glint.png");
						GL11.glEnable(GL11.GL_BLEND);
						var20 = 0.5F;
						GL11.glColor4f(var20, var20, var20, 1.0F);
						GL11.glDepthFunc(GL11.GL_EQUAL);
						GL11.glDepthMask(false);

						for (int var21 = 0; var21 < 2; ++var21) {
							GL11.glDisable(GL11.GL_LIGHTING);
							var22 = 0.76F;
							GL11.glColor4f(0.5F * var22, 0.25F * var22, 0.8F * var22, 1.0F);
							GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
							GL11.glMatrixMode(GL11.GL_TEXTURE);
							GL11.glLoadIdentity();
							float var23 = var19 * (0.001F + (float) var21 * 0.003F) * 20.0F;
							float var24 = 0.33333334F;
							GL11.glScalef(var24, var24, var24);
							GL11.glRotatef(30.0F - (float) var21 * 60.0F, 0.0F, 0.0F, 1.0F);
							GL11.glTranslatef(0.0F, var23, 0.0F);
							GL11.glMatrixMode(GL11.GL_MODELVIEW);
							if (!pixelmon.getIsRed())
								this.renderPassModel.render(pixelmon, var16, var15, var13, var11 - var10, var12, var14);
						}

						GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
						GL11.glMatrixMode(GL11.GL_TEXTURE);
						GL11.glDepthMask(true);
						GL11.glLoadIdentity();
						GL11.glMatrixMode(GL11.GL_MODELVIEW);
						GL11.glEnable(GL11.GL_LIGHTING);
						GL11.glDisable(GL11.GL_BLEND);
						GL11.glDepthFunc(GL11.GL_LEQUAL);
					}

					GL11.glDisable(GL11.GL_BLEND);
					GL11.glEnable(GL11.GL_ALPHA_TEST);
				}
			}

			this.renderEquippedItems(pixelmon, par9);
			float var26 = pixelmon.getBrightness(par9);
			var18 = this.getColorMultiplier(pixelmon, var26, par9);
			OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

			if ((var18 >> 24 & 255) > 0 || pixelmon.hurtTime > 0 || pixelmon.deathTime > 0 || pixelmon.getIsRed()) {
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glDepthFunc(GL11.GL_EQUAL);

				if (pixelmon.hurtTime > 0 || pixelmon.deathTime > 0 || pixelmon.getIsRed()) {
					GL11.glColor4f(var26, 0.0F, 0.0F, 0.4F);
					this.mainModel.render(pixelmon, var16, var15, var13, var11 - var10, var12, var14);

					for (int var27 = 0; var27 < 4; ++var27) {
						if (this.inheritRenderPass(pixelmon, var27, par9) >= 0) {
							GL11.glColor4f(var26, 0.0F, 0.0F, 0.4F);
							this.renderPassModel.render(pixelmon, var16, var15, var13, var11 - var10, var12, var14);
						}
					}
				}

				if ((var18 >> 24 & 255) > 0) {
					var19 = (float) (var18 >> 16 & 255) / 255.0F;
					var20 = (float) (var18 >> 8 & 255) / 255.0F;
					float var29 = (float) (var18 & 255) / 255.0F;
					var22 = (float) (var18 >> 24 & 255) / 255.0F;
					GL11.glColor4f(var19, var20, var29, var22);
					this.mainModel.render(pixelmon, var16, var15, var13, var11 - var10, var12, var14);

					for (int var28 = 0; var28 < 4; ++var28) {
						if (this.inheritRenderPass(pixelmon, var28, par9) >= 0) {
							GL11.glColor4f(var19, var20, var29, var22);
							this.renderPassModel.render(pixelmon, var16, var15, var13, var11 - var10, var12, var14);
						}
					}
				}

				GL11.glDepthFunc(GL11.GL_LEQUAL);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glEnable(GL11.GL_ALPHA_TEST);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
			}

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		} catch (Exception var25) {
			var25.printStackTrace();
		}

		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
		this.passSpecialRender(pixelmon, par2, par4, par6);
	}

	public void drawNameTag(EntityLiving entityliving, double par2, double par4, double par6) {
		if (Minecraft.isGuiEnabled() && (entityliving instanceof EntityPixelmon)) {
			EntityPixelmon entitypixelmon = (EntityPixelmon) entityliving;
			String s = " Lv: " + entitypixelmon.getLvl().getLevel() + " ";
			s += entitypixelmon.getNickname();
			if (entitypixelmon.getOwner() != null) {
				s += " (" + ((EntityPlayer) entitypixelmon.getOwner()).username + ")";
			} else if (!entitypixelmon.getTrainerName().equals("")) {
				s += " (" + entitypixelmon.getTrainerName() + ")";
			} else
				s += " (Wild)";
			if (!entitypixelmon.isSneaking()) {
				renderLivingLabel(entitypixelmon, s, par2, par4, par6, 64);
			}
		}
	}

	protected void renderLivingLabel(EntityLiving entityLiving, String par2Str, double par3, double par5, double par7, int par9) {
		double var10 = entityLiving.getDistanceSqToEntity(this.renderManager.livingPlayer);

		if (var10 <= (double) (par9 * par9)) {
			FontRenderer var12 = this.getFontRendererFromRenderManager();
			float var13 = 1.6F;
			float var14 = 0.016666668F * var13;
			GL11.glPushMatrix();
			float scaleFactor = PixelmonConfig.scaleModelsUp ? 1.3f : 1;
			GL11.glTranslatef((float) par3 + 0.0F, (float) par5 + 1.1f + entityLiving.height * ((EntityPixelmon) entityLiving).getScale() * scaleFactor, (float) par7);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(-var14, -var14, var14);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDepthMask(false);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			Tessellator var15 = Tessellator.instance;
			byte var16 = 0;

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			var15.startDrawingQuads();
			int var17 = var12.getStringWidth(par2Str) / 2;
			var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
			var15.addVertex((double) (-var17 - 1), (double) (-1 + var16), 0.0D);
			var15.addVertex((double) (-var17 - 1), (double) (8 + var16), 0.0D);
			var15.addVertex((double) (var17 + 1), (double) (8 + var16), 0.0D);
			var15.addVertex((double) (var17 + 1), (double) (-1 + var16), 0.0D);
			var15.draw();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, 553648127);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(true);
			var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, -1);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPopMatrix();
		}
	}

	public void drawExpBar(EntityLiving entityLiving, double d, double d1, double d2, float f, float f1) {
		float f2 = 1.6F;
		float f3 = 0.01666667F * f2;
		if ((float) entityLiving.getDistanceToEntity(renderManager.livingPlayer) < 28F && Minecraft.isGuiEnabled()) {
			GL11.glPushMatrix();
			float scaleFactor = PixelmonConfig.scaleModelsUp ? 1.3f : 1;
			GL11.glTranslatef((float) d + 0.0F, (float) d1 + entityLiving.height * ((EntityPixelmon) entityLiving).getScale() * scaleFactor, (float) d2);
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
			float f5 = ((EntityPixelmon) entityLiving).getLvl().getExp();
			float f6 = ((EntityPixelmon) entityLiving).getLvl().getExpToNextLevel();
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
			float scaleFactor = PixelmonConfig.scaleModelsUp ? 1.3f : 1;
			GL11.glTranslatef((float) d + 0.0F, (float) d1 + entityLiving.height * ((EntityPixelmon) entityLiving).getScale() * scaleFactor, (float) d2);
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
			float f5 = ((EntityPixelmon) entityLiving).getHealth();
			float f6 = ((EntityPixelmon) entityLiving).getMaxHealth();
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
		float scaleFactor = PixelmonConfig.scaleModelsUp ? 1.3f : 1;
		GL11.glScalef(scaleFactor * entity.getScale() * entity.baseStats.giScale, scaleFactor * entity.getScale() * entity.baseStats.giScale, scaleFactor * entity.getScale()
				* entity.baseStats.giScale);
		if (entity.doesHover) {
			GL11.glTranslatef(0, -1 * entity.hoverHeight, 0);
		}
	}

	protected void preRenderCallback(EntityLiving entityliving, float f) {
		preRenderScale((EntityPixelmon) entityliving, f);
	}
}
