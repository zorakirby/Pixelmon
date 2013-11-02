package pixelmon.client.models.smd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;

import org.lwjgl.util.vector.Matrix4f;

public class SmdAnimation {
	public final ValveStudioModel owner;
	public ArrayList<AnimFrame> frames = new ArrayList();
	public ArrayList<Bone> bones = new ArrayList();
	public int currentFrameIndex = 0, lastFrameIndex, totalFrames, increase = 0;
	public boolean shouldApplyAnimThisFrame = true;

	public SmdAnimation(ValveStudioModel owner, URL resource) {
		this.owner = owner;
		loadSmdAnim(resource);
		setBoneChildren();
		reform();
	}
	
	private void loadSmdAnim(URL fileURL)throws GabeNewellException{
		BufferedReader reader = null;
		InputStream inputStream = null;
		String currentLine = null;
		int lineCount = -1;
		try{
			inputStream = fileURL.openStream();
			reader = new BufferedReader(new InputStreamReader(inputStream));
			lineCount++;
			while ((currentLine = reader.readLine()) != null)
			{
				lineCount++;
				if(currentLine.startsWith("version")){
					continue;
				}
                else if (currentLine.startsWith("nodes")){
                	lineCount++;
                	while(!(currentLine = reader.readLine()).startsWith("end")){
                		lineCount++;
                		parseBone(currentLine, lineCount);
                	}
                	//print("Number of model bones = " + this.bones.size());
				}
				else if (currentLine.startsWith("skeleton")){
					startParsingAnimation(reader, lineCount, fileURL);
				}
			}
		}catch (IOException e){
			if(lineCount == -1){
        		throw new GabeNewellException("there was a problem opening the model file : " + fileURL);
        	}
        	else
        		throw new GabeNewellException("an error occurred reading the SMD file \"" + fileURL + "\" on line #" + lineCount);
		}
	}
	
	private void parseBone(String line, int lineCount){
		String[] params = line.split(" ");
		int id = Integer.parseInt(params[0]);
		String boneName = params[1].replaceAll("\"", "");
		int parentID = Integer.parseInt(params[2]);
		Bone parent = parentID >= 0 ? bones.get(parentID) : null;
		bones.add(id, new Bone(boneName, id, parent, null));
		ValveStudioModel.print(boneName);
	}
	
	private void startParsingAnimation(BufferedReader reader, int count, URL fileURL)throws GabeNewellException {
		int lineCount = count;
		int currentTime = 0;
		lineCount++;
		String currentLine = null;
		try {
			while((currentLine = reader.readLine())!= null){
				lineCount++;
				String[] params = currentLine.split(" ");
				
				if(params[0].equalsIgnoreCase("time")){
					currentTime = Integer.parseInt(params[1]);
					frames.add(currentTime, new AnimFrame(this));
				}
				else if(currentLine.startsWith("end")){
					this.totalFrames = frames.size();
					ValveStudioModel.print("Total number of frames = " + this.totalFrames);
					return;
				}
				else{
					int boneIndex = Integer.parseInt(params[0]);
					float[] locRots = new float[6];
					for(int i = 1; i < 7; i++){
						locRots[i-1] = Float.parseFloat(params[i]);
					}
					Matrix4f temp = VectorHelper.matrix4FromLocRot(locRots[0], -locRots[1], -locRots[2], locRots[3], -locRots[4], -locRots[5]);
					frames.get(currentTime).addTransforms(boneIndex, temp);

				}
			}
		} catch (Exception e){
        		throw new GabeNewellException("an error occurred reading the SMD file \"" + fileURL + "\" on line #" + lineCount);
		}

		
	}
	
	private void setBoneChildren(){
		for(int i = 0; i < bones.size(); i++){
			Bone theBone = bones.get(i);
			for(Bone child : bones){
				if(child.parent == theBone){
					theBone.addChild(child);
				}
			}
		}
	}

	public void reform(){
		for(int i = 0; i < frames.size(); i++){
			AnimFrame frame = frames.get(i);
			frame.fixUp(0);
			frame.reform();
		}
	}
	
	
	public void precalculateAnimation(SmdModel model){
		for(int i = 0; i < frames.size(); i++){
			model.resetVerts();
			AnimFrame frame = frames.get(i);
			for(int j = 0; j < model.bones.size(); j++){
				Bone bone = model.bones.get(j);
				Matrix4f delta = frame.transformations.get(j);
				bone.doAnimation(frame, delta);
			}
			model.applyPrecalc(frame);
		}
	}


	
/*	public void doAnimation(SmdModel model){
		if(shouldApplyAnimThisFrame){
			if(model != null){
				for(DeformVertex v : model.verts){
					v.apply(frames.get(currentFrameIndex));
				}
			}
		}
	}*/
	
	public void doAnimation(SmdModel model){
		if(shouldApplyAnimThisFrame){
			if(model != null){
				for(DeformVertex v : model.verts){
					v.resetToPrecalcPos(this.frames.get(this.currentFrameIndex));
				}
				model.bones.get(0).setModified();
				for(Bone b : model.bones){
					b.applyModified();
				}
			}
		}
	}
	

	
	//Switch only between frame 0 and frame 1, every 10 ticks
	public void nextFrameDebugMode(){
		if(this.increase + 1 >= 20){
			this.increase = 0;
		}
		else{
			this.increase++;
		}
		this.currentFrameIndex = this.increase / 10;
	}
	
	public void nextFrame(){
		if(this.currentFrameIndex == totalFrames-1){
			this.currentFrameIndex = 0;
		}
		else{
			this.currentFrameIndex++;
		}
	}
	
	public int getNumFrames(){
		return this.frames.size();
	}
	
	public void setCurrentFrame(int i){
		if(lastFrameIndex == i)
			this.shouldApplyAnimThisFrame = false;
		else{
			this.currentFrameIndex = i;
			this.lastFrameIndex = i;
			this.shouldApplyAnimThisFrame = true;
		}
			
	}


}
