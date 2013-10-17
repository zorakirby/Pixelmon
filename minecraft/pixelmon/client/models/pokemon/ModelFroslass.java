package pixelmon.client.models.pokemon;

import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.AdvancedModelLoader;
import pixelmon.client.models.ModelCustomWrapper;
import pixelmon.client.models.PixelmonModelBase;
import pixelmon.client.models.PixelmonModelRenderer;
import pixelmon.client.models.PixelmonModelSmd;
import pixelmon.client.models.animations.ModuleHead;
import pixelmon.client.models.animations.biped.SkeletonBiped;
import pixelmon.client.models.smd.ValveStudioModel;
import pixelmon.enums.EnumCustomModel;

public class ModelFroslass extends PixelmonModelSmd{
	
	PixelmonModelRenderer body;
	
	public ModelFroslass(){
		body = new PixelmonModelRenderer(this, "body");
		ValveStudioModel model = (ValveStudioModel) EnumCustomModel.Froslass.theModel;
		body.addCustomModel(new ModelCustomWrapper(model));
		this.theModel = model;
		skeleton = new SkeletonBiped(body, new ModuleHead(theModel.body.getBoneByName("Head")), null, null,
                null, null, null);
	}
	
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        body.objs.get(0).renderSmd(f5);
    }

}
