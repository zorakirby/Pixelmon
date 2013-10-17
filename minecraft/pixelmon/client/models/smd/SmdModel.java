package pixelmon.client.models.smd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.Face;
import net.minecraftforge.client.model.obj.GroupObject;
import net.minecraftforge.client.model.obj.TextureCoordinate;
import net.minecraftforge.client.model.obj.Vertex;

public class SmdModel{
	//format = <int|Parent bone> <float|PosX PosY PosZ> <normal|NormX NormY NormZ> <normal|U V> <int|links> <int|Bone ID> <normal|Weight> 
	public final ValveStudioModel owner;
	public ArrayList<Face> faces = new ArrayList<Face>();
	public ArrayList<DeformVertex> verts = new ArrayList<DeformVertex>();
	public ArrayList<Bone> bones = new ArrayList<Bone>();
	public HashMap<String, Bone> nameToBoneMapping = new HashMap<String, Bone>();
	public SmdAnimation currentAnim;
	public String fileName;
	
	public SmdModel(ValveStudioModel owner, URL fileURL){
		this.owner = owner;
		loadSmdModel(fileURL);
		setBoneChildren();
		reformBones();
		ValveStudioModel.print("Number of vertices = " + verts.size());
	}

	
	private void loadSmdModel(URL fileURL){
		BufferedReader reader = null;
        InputStream inputStream = null;

        String currentLine = null;
        int lineCount = 0;
        try{
        	inputStream = fileURL.openStream();
        	reader = new BufferedReader(new InputStreamReader(inputStream));
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
                	ValveStudioModel.print("Number of model bones = " + this.bones.size());
                }
                else if (currentLine.startsWith("skeleton")){
                	lineCount++;
                	reader.readLine();
                	lineCount++;
                	while(!(currentLine = reader.readLine()).startsWith("end")){
                		lineCount++;
                		parseBoneValues(currentLine, lineCount);
                	}
                }
                else if (currentLine.startsWith("triangles")){
                	lineCount++;
                	while(!(currentLine = reader.readLine()).startsWith("end")){
                		//lineCount++;
                		//reader.readLine(); //this line will be the "material" line, this is unimportant due to the way minecraft uses textures.
                		String[] params = new String[3];
                		for(int i = 0; i < 3; i++){
                			lineCount++;
                			params[i] = reader.readLine();
                		}
                		parseFace(params, lineCount);
                	}
                	
                }
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
        ValveStudioModel.print("Number of faces = " + faces.size());
	}
	
	private void parseBone(String line, int lineCount){
		String[] params = line.split(" ");
		int id = Integer.parseInt(params[0]);
		String boneName = params[1].replaceAll("\"", "");
		int parentID = Integer.parseInt(params[2]);
		Bone parent = parentID >= 0 ? bones.get(parentID) : null;
		Bone theBone = new Bone(boneName, id, parent, this);
		bones.add(id, theBone);
		nameToBoneMapping.put(boneName, theBone);
		ValveStudioModel.print(boneName);
	}
	

	
	private void parseBoneValues(String line, int lineCount){
		String[] params = line.split(" ");
		int id = Integer.parseInt(params[0]);
		
		float[] locRots = new float[6];
		for(int i = 1; i < 7; i++){
			locRots[i-1] = Float.parseFloat(params[i]);
		}
		Bone theBone = bones.get(id);
		theBone.rest = VectorHelper.matrix4FromLocRot(locRots[0], -locRots[1], -locRots[2], locRots[3], -locRots[4], -locRots[5]);
	}

	
	private void parseFace(String[] params, int lineCount){
		try{
			//<int|Parent bone> <float|PosX PosY PosZ> <normal|NormX NormY NormZ> <normal|U V> <int|links> <int|Bone ID> <normal|Weight> 
			Face face = new Face();
			DeformVertex[] faceVerts = new DeformVertex[3];
			DeformVertex[] normVerts = new DeformVertex[3];
			TextureCoordinate[] uvs = new TextureCoordinate[3];
			for(int i  = 0; i < 3; i++){
				String[] values = params[i].split(" ");
				faceVerts[i] = new DeformVertex(Float.parseFloat(values[1]), -Float.parseFloat(values[2]), -Float.parseFloat(values[3]));
				normVerts[i] = new DeformVertex(Float.parseFloat(values[4]), -Float.parseFloat(values[5]), -Float.parseFloat(values[6])); 
				uvs[i] = new TextureCoordinate(Float.parseFloat(values[7]), 1 - Float.parseFloat(values[8]));
				verts.add(faceVerts[i]);
				if(values.length > 10){
					doBoneWeights(values, faceVerts[i]);
				}
			}
			face.vertices = faceVerts;
			face.vertexNormals = normVerts;
			face.textureCoordinates = uvs;
			faces.add(face);
		}catch(Exception e){
			System.out.println("AN ERROR occurred reading from line #" + lineCount);
			e.printStackTrace();
		}
	}
	
	private void doBoneWeights(String[] values, DeformVertex vert){
		int links = Integer.parseInt(values[9]);
		for(int i = 0; i < links; i++){
			int boneID = Integer.parseInt(values[i*2+10]);
			float weight = Float.parseFloat(values[i*2+11]);
			bones.get(boneID).verts.put(vert, weight);
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
	
	private void reformBones(){
		Bone root = bones.get(0);
		root.reformChildren();
		for(Bone b : bones){
			b.invertMatrix();
		}
	}
	
	public void setAnimation(SmdAnimation anim){
		this.currentAnim = anim;
	}
	
	/**
	 * @param id - The ID of the Bone contained within this model. (can be seen in the original SMD file)
	 * @return The Bone associated with <code>id</code> or <code>null</code> if a Bone with said ID does not exist.
	 */
	public Bone getBoneByID(int id){
		try{
			return bones.get(id);
		}catch(IndexOutOfBoundsException e){
			return null;
		}
	}
	
	/**
	 * @param name - The name of the Bone contained within this model. (can be seen in the original SMD file)
	 * @return The Bone with the input <code>name</code> or <code>null<code> if a Bone with such a name does not exist.
	 */
	public Bone getBoneByName(String name){
		return nameToBoneMapping.get(name);
	}
	
	/**
	 * sets all the vertices in this model to their base, untransformed locations.
	 */
	public void resetVerts(){
		for(DeformVertex v : verts){
			v.reset();
			}
	}
	
	public void applyPrecalc(AnimFrame key){
		for(DeformVertex v : verts){
			v.applyPrecalc(key);
		}
	}
	

	
/*	public void applyVerts(AnimFrame key){
		for(DeformVertex v : verts){
			v.apply(key);
		}
	}*/
	
    public void render()
    {
/*    	if(ValveStudioModel.DEBUG_HIDE_MODEL){
    		return;
    	}*/
    	GL11.glPushMatrix();
    	//GL11.glRotatef(90, 1, 0, 0); //SMD up is Z.
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawing(GL11.GL_TRIANGLES); //SMD files can ONLY be triangles.
        for (Face f: faces)
        {
        	f.addFaceForRender(tessellator);
        }
        tessellator.draw();
        GL11.glPopMatrix();
    }
    

	


}
