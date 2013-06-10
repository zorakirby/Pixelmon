package pixelmon.client.models.smd;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import pixelmon.client.models.animations.IModulizable;
import pixelmon.client.models.animations.IModulizable.EnumGeomData;

import net.minecraft.util.Vec3;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.Vertex;

public class Bone implements IModulizable{
	public String name;
	public int ID;
	public Bone parent;
	public SmdModel owner;
	public ArrayList<Bone> children = new ArrayList();
	public Matrix4f rest, modified, lastFrame = new Matrix4f();
	public HashMap<DeformVertex, Float> verts = new HashMap();
	public HashMap<AnimFrame, Matrix4f> precalcGeom = new HashMap();
	public float prevAngleX, prevAngleY, prevAngleZ;
	public float[] currentVals = new float[6];
	
	private static IModelCustom boneModel;
	
	public Bone(String name, int ID, Bone parent, SmdModel owner){
		this.name = name;
		this.ID = ID;
		this.parent = parent;
		this.owner = owner;
	}
	
	public static void setDebugModel(){
		boneModel = AdvancedModelLoader.loadModel("/pixelmon/models/SMDTest/bone.obj");
	}
	
	public void addChild(Bone child){
		this.children.add(child);
	}
	
	/**
	 * changes the transformations so that they are global transforms, instead of local ones.
	 */
	public void reform(Matrix4f parentMatrix){
		this.rest = Matrix4f.mul(parentMatrix, rest, null);
		VectorHelper.print(this.rest);
		reformChildren();
	}
	

	public void reformChildren(){
		for(Bone child : children){
			child.reform(rest);
		}
	}
	
	public void invertMatrix(){
		Matrix4f.invert(rest, rest);
	}


	
	public void reset(){
		this.lastFrame = this.modified;
		this.modified = new Matrix4f();
	}
	
	
	public void doAnimation(AnimFrame key, Matrix4f delta){
		precalcGeom.put(key, delta);
		for(DeformVertex v : verts.keySet()){
			float weight = verts.get(v);
			v.deform(delta, rest, weight);
		}
	}
	
	
	
	public void setModified(){
		AnimFrame currentFrame = owner.currentAnim.frames.get(owner.currentAnim.currentFrameIndex);
		Matrix4f change = VectorHelper.matrix4FromFloatArray(currentVals); //current programmatically-modified delta geometry values

		Matrix4f realLoc = currentFrame.transformations.get(ID); //current animated global values
		
		Matrix4f realInverted = currentFrame.invertTransformations.get(ID); //current animated global values, INVERTED
		
		Matrix4f delta = Matrix4f.mul(realLoc, change, null); //current programmatically-modified GLOBAL transformation matrix, without parent values factored in
		
		Matrix4f.mul(delta, realInverted, delta); //current programmatically-modified DELTA transformation matrix
		
		this.modified = parent != null ? Matrix4f.mul(parent.modified, delta, null) : delta;

		for(Bone child : children){
			child.setModified();
		}
	}
	
	public void applyModified(){
		for(DeformVertex v : verts.keySet()){
			v.applyModified(this);
		}
		reset();
	}
	
	public void applyPrecalc(AnimFrame frame){
		for(DeformVertex v : verts.keySet()){
			v.applyPrecalc(frame);
		}
	}

	@Override
	public void setValue(float value, EnumGeomData d) {
		switch(d){
			case xloc: currentVals[0] = value;
				break;
			case yloc: currentVals[1] = value;
				break;
			case zloc: currentVals[2] = value;
				break;
			case xrot: currentVals[3] = value;
				break;
			case yrot: currentVals[4] = value;
				break;
			case zrot: currentVals[5] = value;
				break;
			}
	}


	@Override
	public float getValue(EnumGeomData d) {
		return 0; //normally, Modules use the base value to determine and literally set the new transformed value. In Bone's case, however, the transformed value is going to be a delta CHANGE, so returning 0 here will ensure that.
	}

/*	public void render(GeomData locrot){
		GL11.glPushMatrix();
		GL11.glTranslated(locrot.loc.xCoord, locrot.loc.yCoord, locrot.loc.zCoord);
		float[] rots = {(float) Math.toDegrees(locrot.xr), (float) Math.toDegrees(locrot.yr), (float) Math.toDegrees(locrot.zr)};
		GL11.glRotatef(-90, 1, 0, 0);
		GL11.glRotatef(rots[0], 1, 0, 0);
		GL11.glRotatef(rots[1], 0, 1, 0);
		GL11.glRotatef(rots[2], 0, 0, 1);
		GL11.glScalef(.6F, .6F, .6F);
		boneModel.renderAll();
		GL11.glPopMatrix();
	}*/
}
