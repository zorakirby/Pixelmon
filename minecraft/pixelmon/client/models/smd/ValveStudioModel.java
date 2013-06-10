package pixelmon.client.models.smd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.util.vector.Matrix4f;

import net.minecraftforge.client.model.IModelCustom;

public class ValveStudioModel implements IModelCustom{
	public SmdModel body;
	public ArrayList<BodyGroup> bodyGroups = new ArrayList();
	public HashMap<String, SmdAnimation> anims;
	public SmdAnimation currentAnimation;
	public String fileName;
//	public float rotation;
	

	
	public ValveStudioModel(String fileName, URL fileURL){
		this.fileName = fileName;
		loadQC(fileURL);
		precalculateAnims();
	}



	private void loadQC(URL fileURL){
		BufferedReader reader = null;
        InputStream inputStream = null;

        String currentLine = null;
        int lineCount = 0;
        try{
        	inputStream = fileURL.openStream();
        	reader = new BufferedReader(new InputStreamReader(inputStream));
        	while ((currentLine = reader.readLine()) != null){
        		lineCount++;
        		String[] params = currentLine.split(" ");
        		if(params[0].equalsIgnoreCase("$body")){
        			URL modelPath = getFileInSameDirectoryAs(fileURL, params[1]);
        			this.body = new SmdModel(this, modelPath);
        		}
        		else if(params[0].equalsIgnoreCase("$anim")){
        			if(this.anims == null){
        				this.anims = new HashMap();
        			}
        			String animName = params[1];
        			URL animPath = getFileInSameDirectoryAs(fileURL, params[2]);
        			anims.put(animName, new SmdAnimation(this, animPath));
        			if(animName.equalsIgnoreCase("idle")){
        				this.currentAnimation = anims.get(animName);
        			}
        		}
/*        		else if(params[0].equalsIgnoreCase("$up")){
        			String up = params[1].toLowerCase();
        			if(up.equalsIgnoreCase("z")){
        				this.rotation = -90;
        			}
        			else if(up.equalsIgnoreCase("-z")){
        				this.rotation = 90;
        			}
        			else if(up.equalsIgnoreCase("-y")){
        				this.rotation =180;
        			}
        			else if(up.equalsIgnoreCase("y"));
        			else{
        				System.out.println(String.format("THE UP AXIS FOR THE FILE \"%s\" CANNOT BE '%s'! Setting rotation to 0 instead!", new Object[]{fileURL.getFile().substring(fileURL.getFile().lastIndexOf("/") + 1), up}));
        			}
        		}*/
        	}
        }catch(Exception e){
        	System.out.println("AN ERROR occurred reading the PQC file on line #" + lineCount);
        	e.printStackTrace();
        }
	}
	
	
	public URL getFileInSameDirectoryAs(URL theURL, String fileName) throws MalformedURLException{
		String urlAsString = theURL.toExternalForm();
		int lastSlash = urlAsString.lastIndexOf('/');
		return new URL(urlAsString.substring(0, lastSlash)+"/"+fileName);
	}

	@Override
	public String getType() {
		return "pqc";
	}
	
	
	private void precalculateAnims() {
		for(SmdAnimation anim: anims.values()){
			anim.precalculateAnimation(body);
			for(BodyGroup group : bodyGroups){
				for(SmdModel model : group.models){
					anim.precalculateAnimation(model);
				}
			}
		}
	}
	
	@Override
	public void renderAll() {
/*		if((this.currentAnimation != null) == false){
			currentAnimation.doAnimationOld(this.body);
			currentAnimation.doAnimationOld(this.bodyGroups);
		}*/
		if(this.currentAnimation != null){
			currentAnimation.doAnimation(this.body);
		//	currentAnimation.doAnimation(this.bodygroups);
		}
		if(this.body != null){
			body.render();
		}
		/*if(DEBUG_TOGGLE_FRAME){
			this.currentAnimation.nextFrameDebugMode();
		}
		else{
			this.currentAnimation.nextFrame();
		}*/

	}
	
	public void setAnimation(String animname){
		if(anims.containsKey(animname)){
			this.currentAnimation = anims.get(animname);
		}
		else
			this.currentAnimation = anims.get("idle");
		body.setAnimation(this.currentAnimation);
		for(BodyGroup b : bodyGroups){
			b.setAnimation(currentAnimation);
		}
	}
	
	@Override
	public void renderPart(String partName) {
		// TODO Auto-generated method stub
		
	}
	


/*	private void fixUpAxis() {
		Matrix4f rotateFactor = new Matrix4f();
		rotateFactor.rotate((float) Math.toRadians(rotation), VectorHelper.X_AXIS);
		
	}*/

}
