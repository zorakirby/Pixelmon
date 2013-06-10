package pixelmon.client.models.smd;

import java.util.HashMap;

import net.minecraftforge.client.model.obj.Vertex;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

public class DeformVertex extends Vertex{
	private Vector4f baseLoc;
	public Vector4f currentLoc = new Vector4f();
	public Matrix4f currentTransform, lastTransform;
	private HashMap<AnimFrame, Vector4f> precalcPositions = new HashMap();
	
	public DeformVertex(float x, float y, float z) {
		super(x, y, z);
		this.baseLoc = new Vector4f(x, y, z, 1);
	}
	
	public void fixUpAxis(float rotation){
		
	}
	
	
	public void reset(){
		this.currentLoc = VectorHelper.copyVector4f(baseLoc);
	}
	
	public void resetToPrecalcPos(AnimFrame key){
		this.currentLoc = VectorHelper.copyVector4f(precalcPositions.get(key));
	}
	
	
	public void applyOld(){
		this.x = currentLoc.x;
		this.y = currentLoc.y;
		this.z = currentLoc.z;
	}
	
	/**
	 * @param delta - The Worldspace transform matrix, gotten from <code>AnimFrame.tranformations</code>. Contrary to the name, it is not actually just the difference values - It contains the full position and rotation.
	 * @param rest - The "at-rest" values of the bone that called this function. The "at-rest" values are actually the INVERSE (as in negative) of the bone's Worldspace location/rotation.
	 * @param weight - The strength at which the bone that called this function influences the position of this vertex.
	 */
	public void deform(Matrix4f delta, Matrix4f rest, float weight){
		Matrix4f temp = Matrix4f.mul(delta, rest, null);
		Matrix4f.transform(temp, currentLoc, currentLoc);
	}
	
	public void applyPrecalc(AnimFrame key){
		precalcPositions.put(key, VectorHelper.copyVector4f(currentLoc));
	}
	
	public void applyModified(Bone bone){
		if(bone.modified != null){
			Matrix4f.transform(bone.modified, currentLoc, currentLoc);
		}
		this.x = currentLoc.x;
		this.y = currentLoc.y;
		this.z = currentLoc.z;
	}

}
