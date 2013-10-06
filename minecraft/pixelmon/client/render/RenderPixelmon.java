package pixelmon.client.render;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import pixelmon.RandomHelper;
import pixelmon.client.ServerStorageDisplay;
import pixelmon.config.PixelmonConfig;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumBossMode;

public class RenderPixelmon extends RenderLiving {

	private int defaultNameRenderDistance = 8;
	private int defaultBossNameRenderDistanceExtension = 8;
	private int configNameRenderMultiplier = (int) Math.max(1, Math.min(PixelmonConfig.nameplateRangeModifier, 3));
	private int nameRenderDistanceNormal = defaultNameRenderDistance * configNameRenderMultiplier;
	private int nameRenderDistanceBoss = nameRenderDistanceNormal + defaultBossNameRenderDistanceExtension;

	public RenderPixelmon(float par2) { // par2 = shadow size
		super(null, par2);
	}

	public void doRenderLiving(EntityLiving entityLiving, double d, double d1, double d2, float f, float f1) {
		EntityPixelmon pixelmon = (EntityPixelmon) entityLiving;
		if (pixelmon.stopRender)
			return;
		if (pixelmon.getName().equals(""))
			return;
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1f);
		if (!pixelmon.isInitialised)
			pixelmon.init(pixelmon.getName());
		if (pixelmon.getModel() != null){
			renderPixelmon(pixelmon, d, d1, d2, f, f1);
//			this.func_110827_b(pixelmon, d, d1, d2, f, f1);
		}
		if (pixelmon.evolving != 0)
			return;
		boolean owned = ServerStorageDisplay.contains(pixelmon.getPokemonId());

		float var10 = pixelmon.getDistanceToEntity(this.renderManager.livingPlayer);
		float renderdistance = pixelmon.getBossMode() != EnumBossMode.Normal ? nameRenderDistanceBoss : nameRenderDistanceNormal;
		if (var10 <= renderdistance || owned) {
			drawHealthBar(pixelmon, d, d1, d2, f, f1, owned);
			if (ServerStorageDisplay.contains(pixelmon.getPokemonId()))
				drawExpBar(pixelmon, d, d1, d2, f, f1, owned);
			drawNameTag(pixelmon, d, d1, d2, owned);
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
		mainModel = pixelmon.getModel();
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
			float f2 = this.interpolateRotation(pixelmon.prevRenderYawOffset, pixelmon.renderYawOffset, par9);
            float f3 = this.interpolateRotation(pixelmon.prevRotationYawHead, pixelmon.rotationYawHead, par9);
            float f4;

            if (pixelmon.isRiding() && pixelmon.ridingEntity instanceof EntityLivingBase)
            {
                EntityLivingBase entitylivingbase1 = (EntityLivingBase)pixelmon.ridingEntity;
                f2 = this.interpolateRotation(entitylivingbase1.prevRenderYawOffset, entitylivingbase1.renderYawOffset, par9);
                f4 = MathHelper.wrapAngleTo180_float(f3 - f2);

                if (f4 < -85.0F)
                {
                    f4 = -85.0F;
                }

                if (f4 >= 85.0F)
                {
                    f4 = 85.0F;
                }

                f2 = f3 - f4;

                if (f4 * f4 > 2500.0F)
                {
                    f2 += f4 * 0.2F;
                }
            }
            float f5 = pixelmon.prevRotationPitch + (pixelmon.rotationPitch - pixelmon.prevRotationPitch) * par9;
            this.renderLivingAt(pixelmon, par2, par4, par6);
			f4 = this.handleRotationFloat(pixelmon, par9);
			this.rotateCorpse(pixelmon, f4, f2, par9);
			float f6 = 0.0625F;
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(-1.0F, -1.0F, 1.0F);
			this.preRenderCallback(pixelmon, par9);
            GL11.glTranslatef(0.0F, -24.0F * f6 - 0.0078125F, 0.0F);
			float f7 = pixelmon.prevLimbSwingAmount + (pixelmon.limbSwingAmount - pixelmon.prevLimbSwingAmount) * par9;
			float f8 = pixelmon.limbSwing - pixelmon.limbSwingAmount * (1.0F - par9);

			if (pixelmon.isChild()) {
				f8 *= 3.0F;
			}

			if (f7 > 1.0F) {
				f7 = 1.0F;
			}

			GL11.glEnable(GL11.GL_ALPHA_TEST);
			this.mainModel.setLivingAnimations(pixelmon, f8, f7, par9);
			if (pixelmon.getIsRed()) {
				GL11.glColor4f(0, 0, 0, 1F);
				this.renderModel(pixelmon, f8, f7, f4, f3- f2, f5, f6);
			} else if (pixelmon.transformed) {
				GL11.glColor4f(RandomHelper.getRandomNumberBetween(0.85f, 1f), RandomHelper.getRandomNumberBetween(0.85f, 1f),
						RandomHelper.getRandomNumberBetween(0.85f, 1f), 1);
				this.renderModel(pixelmon, f8, f7, f4, f3- f2, f5, f6);
			} else if (pixelmon.getBossMode() != EnumBossMode.Normal) {
				GL11.glColor4f(pixelmon.getBossMode().r, pixelmon.getBossMode().g, pixelmon.getBossMode().b, 1f);
				this.renderModel(pixelmon, f8, f7, f4, f3- f2, f5, f6);
			} else if (pixelmon.evolving == 1) {
				this.renderModel(pixelmon, f8, f7, f4, f3- f2, f5, f6);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glColor4f(1, 1, 1, ((float) pixelmon.evolvingVal) / 20f);
				this.renderModel(pixelmon, f8, f7, f4, f3- f2, f5, f6);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
			} else if (pixelmon.evolving == 2) {
				if (pixelmon.evolvingVal < 180) {
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glColor4f(1, 1, 1, 1);
					this.renderModel(pixelmon, f8, f7, f4, f3- f2, f5, f6);
					GL11.glEnable(GL11.GL_TEXTURE_2D);
				} else {
					this.renderModel(pixelmon, f8, f7, f4, f3- f2, f5, f6);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glColor4f(1, 1, 1, (200f - (float) pixelmon.evolvingVal) / 20f);
					this.renderModel(pixelmon, f8, f7, f4, f3- f2, f5, f6);
					GL11.glEnable(GL11.GL_TEXTURE_2D);
				}
			} else {
				GL11.glColor4f(1f, 1, 1, 1F);
				this.renderModel(pixelmon, f8, f7, f4, f3- f2, f5, f6);
			}
			float f9;
			int i;
			float f10;
			float f11;

			for (int var17 = 0; var17 < 4; ++var17) {
				i = this.shouldRenderPass(pixelmon, var17, par9);

				if (i > 0) {
					this.renderPassModel.setLivingAnimations(pixelmon, f8, f7, par9);
					if (!pixelmon.getIsRed())
						this.renderPassModel.render(pixelmon, f8, f7, f4, f3 - f2, f5, f6);

					if (i == 15) {
						f9 = (float) pixelmon.ticksExisted + par9;
						this.bindTexture(new ResourceLocation("%blur%/misc/glint.png"));
						GL11.glEnable(GL11.GL_BLEND);
						f10 = 0.5F;
						GL11.glColor4f(f10, f10, f10, 1.0F);
						GL11.glDepthFunc(GL11.GL_EQUAL);
						GL11.glDepthMask(false);

						for (int var21 = 0; var21 < 2; ++var21) {
							GL11.glDisable(GL11.GL_LIGHTING);
							f11 = 0.76F;
							GL11.glColor4f(0.5F * f11, 0.25F * f11, 0.8F * f11, 1.0F);
							GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
							GL11.glMatrixMode(GL11.GL_TEXTURE);
							GL11.glLoadIdentity();
							float var23 = f9 * (0.001F + (float) var21 * 0.003F) * 20.0F;
							float var24 = 0.33333334F;
							GL11.glScalef(var24, var24, var24);
							GL11.glRotatef(30.0F - (float) var21 * 60.0F, 0.0F, 0.0F, 1.0F);
							GL11.glTranslatef(0.0F, var23, 0.0F);
							GL11.glMatrixMode(GL11.GL_MODELVIEW);
							if (!pixelmon.getIsRed())
								 this.renderPassModel.render(pixelmon, f8, f7, f4, f3 - f2, f5, f6);
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
			i = this.getColorMultiplier(pixelmon, var26, par9);
			OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

			if ((i >> 24 & 255) > 0 || pixelmon.hurtTime > 0 || pixelmon.deathTime > 0 || pixelmon.getIsRed()) {
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glDepthFunc(GL11.GL_EQUAL);

				if (pixelmon.hurtTime > 0 || pixelmon.deathTime > 0 || pixelmon.getIsRed()) {
					GL11.glColor4f(var26, 0.0F, 0.0F, 0.4F);
					this.mainModel.render(pixelmon, f8, f7, f4, f3 - f2, f5, f6);

					for (int var27 = 0; var27 < 4; ++var27) {
						if (this.inheritRenderPass(pixelmon, var27, par9) >= 0) {
							GL11.glColor4f(var26, 0.0F, 0.0F, 0.4F);
							this.renderPassModel.render(pixelmon, f8, f7, f4, f3 - f2, f5, f6);
						}
					}
				}

				if ((i >> 24 & 255) > 0) {
					f9 = (float) (i >> 16 & 255) / 255.0F;
					f10 = (float) (i >> 8 & 255) / 255.0F;
					float var29 = (float) (i & 255) / 255.0F;
					f11 = (float) (i >> 24 & 255) / 255.0F;
					GL11.glColor4f(f9, f10, var29, f11);
					this.mainModel.render(pixelmon, f8, f7, f4, f3 - f2, f5, f6);

					for (int var28 = 0; var28 < 4; ++var28) {
						if (this.inheritRenderPass(pixelmon, var28, par9) >= 0) {
							GL11.glColor4f(f9, f10, var29, f11);
							this.renderPassModel.render(pixelmon, f8, f7, f4, f3 - f2, f5, f6);
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

	public void drawNameTag(EntityPixelmon entitypixelmon, double par2, double par4, double par6, boolean owned) {
		if (Minecraft.isGuiEnabled()) {
			String s = "";
			if (entitypixelmon.getBossMode() == EnumBossMode.Normal)
				s += " Lv: " + entitypixelmon.getLvl().getLevel() + " ";
			s += entitypixelmon.getNickname();
			if (entitypixelmon.getBossMode() == EnumBossMode.Normal) {
				if (entitypixelmon.hasOwner()) {
					s += " (" + entitypixelmon.getOwnerName() + ")";
				} else if (!entitypixelmon.getTrainerName().equals("")) {
					s += " (" + entitypixelmon.getTrainerName() + ")";
				} else
					s += " (Wild)";
			}
			if (!entitypixelmon.isSneaking()) {
				renderLivingLabel(entitypixelmon, s, par2, par4, par6, owned);
			}
		}
	}

	protected void renderLivingLabel(EntityPixelmon entitypixelmon, String par2Str, double par3, double par5, double par7, boolean owned) {
		double var10 = entitypixelmon.getDistanceSqToEntity(this.renderManager.livingPlayer);
		FontRenderer var12 = this.getFontRendererFromRenderManager();
		float var13 = 1.6F;
		float var14 = 0.016666668F * var13;
		GL11.glPushMatrix();
		float scaleFactor = PixelmonConfig.scaleModelsUp ? 1.3f : 1;
		scaleFactor *= entitypixelmon.getScaleFactor();
		GL11.glTranslatef((float) par3 + 0.0F, (float) par5 + 1.1f + entitypixelmon.height * entitypixelmon.getPixelmonScale() * scaleFactor, (float) par7);
		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		GL11.glScalef(-var14, -var14, var14);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDepthMask(false);
		if (owned)
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
		if (owned)
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, entitypixelmon.getBossMode().getColourInt());
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glPopMatrix();
	}

	public void drawExpBar(EntityLiving entityLiving, double d, double d1, double d2, float f, float f1, boolean owned) {
		float f2 = 1.6F;
		float f3 = 0.01666667F * f2;
		if (Minecraft.isGuiEnabled()) {
			GL11.glPushMatrix();
			float scaleFactor = PixelmonConfig.scaleModelsUp ? 1.3f : 1;
			scaleFactor *= ((EntityPixelmon) entityLiving).getScaleFactor();
			GL11.glTranslatef((float) d + 0.0F, (float) d1 + entityLiving.height * ((EntityPixelmon) entityLiving).getPixelmonScale() * scaleFactor, (float) d2);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(-f3, -f3, f3);
			GL11.glDisable(2896 /* GL_LIGHTING */);
			GL11.glDepthMask(false);
			if (owned)
				GL11.glDisable(2929 /* GL_DEPTH_TEST */);
			GL11.glEnable(3042 /* GL_BLEND */);
			GL11.glBlendFunc(770, 771);
			Tessellator tessellator = Tessellator.instance;
			byte byte0 = -20;
			GL11.glDisable(3553 /* GL_TEXTURE_2D */);
			tessellator.startDrawingQuads();
			float f5 = ((EntityPixelmon) entityLiving).getLvl().getExp();
			float f6 = ((EntityPixelmon) entityLiving).getLvl().getExtForNextLevelClient();
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
			if (owned)
				GL11.glEnable(2929 /* GL_DEPTH_TEST */);
			GL11.glDepthMask(true);
			GL11.glEnable(2896 /* GL_LIGHTING */);
			GL11.glDisable(3042 /* GL_BLEND */);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPopMatrix();
		}
	}

	public void drawHealthBar(EntityLiving entityLiving, double d, double d1, double d2, float f, float f1, boolean owned) {
		float f2 = 1.6F;
		float f3 = 0.01666667F * f2;
		if (Minecraft.isGuiEnabled()) {
			GL11.glPushMatrix();
			float scaleFactor = PixelmonConfig.scaleModelsUp ? 1.3f : 1;
			scaleFactor *= ((EntityPixelmon) entityLiving).getScaleFactor();
			GL11.glTranslatef((float) d + 0.0F, (float) d1 + entityLiving.height * ((EntityPixelmon) entityLiving).getPixelmonScale() * scaleFactor, (float) d2);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(-f3, -f3, f3);
			GL11.glDisable(2896 /* GL_LIGHTING */);
			GL11.glDepthMask(false);
			if (owned)
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

			tessellator.setColorRGBA_F(0.5F, 0.5F, 0.5F, 1.0F);
			tessellator.addVertex(-25F + f8, -7 + byte0, 0.0D);
			tessellator.addVertex(-25F + f8, -6 + byte0, 0.0D);
			tessellator.addVertex(25D, -6 + byte0, 0.0D);
			tessellator.addVertex(25D, -7 + byte0, 0.0D);

			if (f5 <= (f6 / 5))
				tessellator.setColorRGBA_F(0.8F, 0.0F, 0.0F, 1.0F);
			else if (f5 <= (f6 / 2))
				tessellator.setColorRGBA_F(1.0F, 1.0F, 0.2F, 1.0F);
			else
				tessellator.setColorRGBA_F(0.2F, 1F, 0.2F, 1.0F);

			tessellator.addVertex(-25D, -7 + byte0, 0.0D);
			tessellator.addVertex(-25D, -6 + byte0, 0.0D);
			tessellator.addVertex(f8 - 25F, -6 + byte0, 0.0D);
			tessellator.addVertex(f8 - 25F, -7 + byte0, 0.0D);
			tessellator.draw();
			GL11.glEnable(3553 /* GL_TEXTURE_2D */);
			if (owned)
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
		scaleFactor *= entity.getScaleFactor();

		GL11.glScalef(scaleFactor * entity.getPixelmonScale() * entity.baseStats.giScale, scaleFactor * entity.getPixelmonScale() * entity.baseStats.giScale, scaleFactor
				* entity.getPixelmonScale() * entity.baseStats.giScale);
		if (entity.evolving == 2) {
			float scale = (entity.evolvingVal) / 200f;
			GL11.glScalef(1 + scale * entity.widthDiff, 1 + scale * entity.heightDiff, 1 + scale * entity.lengthDiff);
		}
		if (entity.baseStats.doesHover) {
			GL11.glTranslatef(0, -1 * entity.baseStats.hoverHeight, 0);
		}
	}

	protected void preRenderCallback(EntityLiving entityliving, float f) {
		preRenderScale((EntityPixelmon) entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(((EntityPixelmon) entity).getTexture());
	}
	
	  /**
     * Returns a rotation angle that is inbetween two other rotation angles. par1 and par2 are the angles between which
     * to interpolate, par3 is probably a float between 0.0 and 1.0 that tells us where "between" the two angles we are.
     * Example: par1 = 30, par2 = 50, par3 = 0.5, then return = 40
     */
    private float interpolateRotation(float par1, float par2, float par3)
    {
        float f3;

        for (f3 = par2 - par1; f3 < -180.0F; f3 += 360.0F)
        {
            ;
        }

        while (f3 >= 180.0F)
        {
            f3 -= 360.0F;
        }

        return par1 + par3 * f3;
    }
}
