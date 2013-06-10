package pixelmon.client.models.smd;

import java.util.ArrayList;

public class BodyGroup {
	public ArrayList<SmdModel> models = new ArrayList();
	public int currentModel;

	
	public void setAnimation(SmdAnimation anim){
		for(SmdModel model : models){
			model.setAnimation(anim);
		}
	}
}
