package pixelmon.client.models.smd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import net.minecraftforge.client.model.IModelCustom;

public class ValveStudioModel implements IModelCustom{
	public SmdModel body;
	public ArrayList<BodyGroup> bodyGroups = new ArrayList();
	public HashMap<String, SmdAnimation> anims = new HashMap();
	public SmdAnimation currentAnimation;
	public String fileName;
	public static boolean debugModel = false;
//	public float rotation;
	

	
	public ValveStudioModel(String fileName, URL fileURL) throws GabeNewellException{
		this.fileName = fileName;
		loadQC(fileURL);
		precalculateAnims();
	}



	private void loadQC(URL fileURL) throws GabeNewellException{
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
        	}
        }catch(Exception e){
        	throw new GabeNewellException("An error occurred reading the PQC file on line #" + lineCount);
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
		GL11.glShadeModel(GL11.GL_SMOOTH);
		if(this.currentAnimation != null){
			currentAnimation.doAnimation(this.body);
		//	currentAnimation.doAnimation(this.bodygroups);
		}
		if(this.body != null){
			body.render();
		}
		GL11.glShadeModel(GL11.GL_FLAT);
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
	
	/**
	 *Shorthand for System.out.println(Object). DOES NOT PRINT IF <code>ValveStudioModel.debugModel</code> is <code>false</code>
	 */
	public static void print(Object o){
		if(ValveStudioModel.debugModel)
		System.out.println(o);
	}



	@Override
	public void renderOnly(String... groupNames) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void renderAllExcept(String... excludedGroupNames) {
		// TODO Auto-generated method stub
		
	}
	


/*	private void fixUpAxis() {
		Matrix4f rotateFactor = new Matrix4f();
		rotateFactor.rotate((float) Math.toRadians(rotation), VectorHelper.X_AXIS);
		
	}*/

}
