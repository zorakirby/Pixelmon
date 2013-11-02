package pixelmon.client.models;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import pixelmon.client.models.smd.SmdAnimation;
import pixelmon.client.models.smd.ValveStudioModel;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class PixelmonModelSmd extends PixelmonModelBase{
	public ValveStudioModel theModel;//this should be set in the subclass constructor
	
	@Override
	public void render(Entity var1, float f, float f1, float f2, float f3, float f4, float f5) {
		EntityPixelmon pixelmon = ((EntityPixelmon)var1);
		
		doAnimation(var1, f, f1, f2, f3, f4, f5);
	}

	
	public void doAnimation(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		EntityPixelmon pixelmon = (EntityPixelmon) entity;		
		increaseAnimCounter2(pixelmon);
		if (entity.worldObj.getBlockMaterial((int) Math.ceil(entity.posX), (int) Math.ceil(entity.posY), (int) Math.ceil(entity.posZ)) == Material.water){
			skeleton.swim(pixelmon, f, f1, f2, f3, f4);
			theModel.setAnimation("swim");
		}
		else if (entity.isAirBorne || pixelmon.baseStats.doesHover){
			skeleton.fly(pixelmon, f, f1, f2, f3, f4);
			theModel.setAnimation("fly");
		}
		else{
			skeleton.walk(pixelmon, f, f1, f2, f3, f4);
			theModel.setAnimation("idle");
		}
		SmdAnimation theAnim = theModel.currentAnimation;
		int frame = pixelmon.animationCounter2 % theAnim.totalFrames;
		theAnim.setCurrentFrame(frame);

	}
	
	public boolean isMinecraftPaused(){
		Minecraft m = Minecraft.getMinecraft();
		return (m.isSingleplayer() && m.currentScreen != null && m.currentScreen.doesGuiPauseGame() && !m.getIntegratedServer().getPublic());
	}
	
	public void increaseAnimCounter2(EntityPixelmon pixelmon){
		if(!isMinecraftPaused()){
			pixelmon.animationCounter2++;
			if(pixelmon.animationCounter2 < 0)
			pixelmon.animationCounter2 += Integer.MAX_VALUE;
		}
	}
}
