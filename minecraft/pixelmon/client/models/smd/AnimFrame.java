package pixelmon.client.models.smd;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.util.vector.Matrix4f;

import net.minecraft.util.Vec3;

public class AnimFrame {
	public ArrayList<Matrix4f> transformations = new ArrayList(), invertTransformations = new ArrayList();
	public SmdAnimation owner;
	public AnimFrame(SmdAnimation parent){
		this.owner = parent;
	}
	
	public void addTransforms(int index, Matrix4f data){
		this.transformations.add(index, data);
		this.invertTransformations.add(index, Matrix4f.invert(data, null));
	}
	
	public void fixUp(float degrees){
		float radians = (float) Math.toRadians(degrees);
		Matrix4f rotator = VectorHelper.matrix4FromLocRot(0, 0, 0, radians, 0, 0);
		Matrix4f.mul(rotator, this.transformations.get(0),this.transformations.get(0));
		Matrix4f.mul(Matrix4f.invert(rotator, null), this.invertTransformations.get(0), this.invertTransformations.get(0));
	}
	
	/**
	 * changes the transformations so that they are global transforms, instead of local ones.
	 */
	public void reform(){
		for(int i = 0; i < transformations.size(); i++){
			Bone bone = owner.bones.get(i);
			if(bone.parent != null){
				Matrix4f temp = Matrix4f.mul(transformations.get(bone.parent.ID), transformations.get(i), null);
				transformations.set(i, temp);
				invertTransformations.set(i, Matrix4f.invert(temp, null));
			}
		}
	}
}
