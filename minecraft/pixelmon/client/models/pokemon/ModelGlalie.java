package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import pixelmon.client.models.ModelCustomWrapper;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.biped.SkeletonBiped;
import pixelmon.client.render.RenderResources;
import pixelmon.client.shading.Cubemap;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.enums.EnumCustomModel;

public class ModelGlalie extends PixelmonModelBase{
	public static final String cubemap = "/pixelmon/texture/pokemon/glalie_cubemap.png", shinyCubemap = "/pixelmon/texture/pokemon-shiny/shinyglalie_cubemap.png";
	PixelmonModelRenderer matte, reflective;
	
	public ModelGlalie(){
		matte = new PixelmonModelRenderer(this, "matte");
		matte.addCustomModel(new ModelCustomWrapper(EnumCustomModel.GlalieMatte.theModel));
		reflective = new PixelmonModelRenderer(this, "reflective");
		reflective.addCustomModel(new ModelCustomWrapper(EnumCustomModel.GlalieReflective.theModel));
		//matte.addChild(reflective);
		skeleton = new SkeletonBiped(matte, new ModuleHead(matte).addModule(new ModuleHead(reflective)), null, null,
                null, null, null);
	}
	
	@Override //Overriding this because: need to know whether to use normal or shiny cubemap
	public void render(Entity var1, float f, float f1, float f2, float f3, float f4, float f5) {
		boolean isShiny = ((EntityPixelmon)var1).getIsShiny();
		doAnimation(var1, f, f1, f2, f3, f4, f5);
		matte.render(f5);
		if(isShiny){
			Cubemap.begin(RenderResources.glalieCubemapShiny);
		}
		else{
			Cubemap.begin(RenderResources.glalieCubemap);
		}
		reflective.render(f5);
		Cubemap.end();
	}
	
}
