package pixelmon.client.models.smd;

import java.lang.reflect.Field;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3Pool;

public abstract class VectorHelper {
	static final Vector3f
		X_AXIS = new Vector3f(1, 0, 0),
		Y_AXIS = new Vector3f(0, 1, 0),
		Z_AXIS = new Vector3f(0, 0, 1);
	
	public static Vec3 add(Vec3 one, Vec3 other){
		return one.addVector(other.xCoord, other.yCoord, other.zCoord);
	}

	/**
	 * @param me  - the vector to be rotated
	 * @param orbit - the vector around which to rotate
	 * @param xr - x axis rotation
	 * @param yr - y axis rotation
	 * @param zr - z axis rotation
	 * @return the newly rotated vector
	 */
	public static Vec3 getVecRotatedAround(Vec3 me, Vec3 orbit, float xr, float yr, float zr){
		Vec3 temp = orbit.subtract(me);
		temp.rotateAroundX(xr);
		temp.rotateAroundY(yr);
		temp.rotateAroundZ(zr);
		return add(temp, orbit);
	}
	
	public static Vec3 rotateInReverse(Vec3 me, Vec3 orbit, float xr, float yr, float zr){
		Vec3 temp = orbit.subtract(me);
		temp.rotateAroundZ(zr);
		temp.rotateAroundY(yr);
		temp.rotateAroundX(xr);
		return add(temp, orbit);
	}
	
	public static Vec3 rotateTest(Vec3 me, Vec3 orbit, float xr, float yr, float zr){
		Vec3 temp = orbit.subtract(me);

		temp.rotateAroundZ(zr);
		temp.rotateAroundX(xr);
		temp.rotateAroundY(yr);

		return add(temp, orbit);
	}

	
	public static void print(Vec3 target){
		System.out.println("Vector x = " + target.xCoord);
		System.out.println("Vector y = " + target.yCoord);
		System.out.println("Vector z = " + target.zCoord);
	}
	
	public static void print(Matrix4f target){
		System.out.println(target);
	}
	
	public static void printAlternate(Matrix4f target){
		Field[] fields = target.getClass().getFields();
		System.out.println("MATRIX DATA");
		System.out.println("~~~Standard Print~~~");
		print(target);
		System.out.println("~~~In-Depth Print~~~");
		for(int i = 0; i < fields.length; i++){
			Field f = fields[i];
			String descript = f.getName() + " = ";
			try {
				descript += f.getFloat(target);
			} catch (Exception e){
				descript += "ERROR";
			}
			System.out.println(descript);
		}
	}
	
	public static Matrix4f matrix4FromLocRot(float xl, float yl, float zl, float xr, float yr, float zr){
		Vector3f loc = new Vector3f(xl, yl, zl);
		Matrix4f part1 = new Matrix4f();
		Matrix4f part2 = new Matrix4f();
		
		part1.translate(loc);
		part1.rotate(zr, Z_AXIS);
		part1.rotate(yr, Y_AXIS);
		part1.rotate(xr, X_AXIS);
		
		return part1;
	}
	
	public static Matrix4f matrix4FromFloatArray(float[] vals){
		return matrix4FromLocRot(vals[0], vals[1], vals[2], vals[3], vals[4], vals[5]);
	}
	
	public static float[] getLoc(Matrix4f target){
		return new float[]{target.m30, target.m31, target.m32};
	}
	
	public static Vector3f v3LocFromM4(Matrix4f target){
		return new Vector3f(target.m30, target.m31, target.m32);
	}
	
	
	public static Vector3f getInverse(Vector3f target){
		return new Vector3f(-target.x, -target.y, -target.z);
	}
	
	public static Vector3f vec3fFromStrings(String x, String y, String z){
		float xl = Float.parseFloat(x);
		float yl = Float.parseFloat(y);
		float zl = Float.parseFloat(z);
		return new Vector3f(xl, yl, zl);
	}
	
	public static Vector4f copyVector4f(Vector4f src){
		return new Vector4f(src.x, src.y, src.z, src.w);
	}

}
