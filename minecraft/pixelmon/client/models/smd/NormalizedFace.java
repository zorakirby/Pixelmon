package pixelmon.client.models.smd;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.model.obj.Face;
import net.minecraftforge.client.model.obj.TextureCoordinate;
import net.minecraftforge.client.model.obj.Vertex;

public class NormalizedFace extends Face{

	public NormalizedFace(Vertex[] xyz, Vertex[] xyzn, TextureCoordinate[] uvs){
		this.vertices = xyz;
		this.textureCoordinates = uvs;
		this.vertexNormals = xyzn;
		//this.calcVertNorms2(xyz, xyzn);
		this.faceNormal = calculateFaceNormal();
	}
	
	@Override
    public Vertex calculateFaceNormal()
    {
        Vec3 v1 = Vec3.createVectorHelper(vertexNormals[1].x - vertexNormals[0].x, vertexNormals[1].y - vertexNormals[0].y, vertexNormals[1].z - vertexNormals[0].z);
        Vec3 v2 = Vec3.createVectorHelper(vertexNormals[2].x - vertexNormals[0].x, vertexNormals[2].y - vertexNormals[0].y, vertexNormals[2].z - vertexNormals[0].z);
        Vec3 normalVector = v1.crossProduct(v2).normalize();

        return new Vertex((float) normalVector.xCoord, (float) normalVector.yCoord, (float) normalVector.zCoord);
    }
	
	protected void calcVertNorms1(Vertex[] xyz, Vertex[] xyzn){
		this.vertexNormals = new Vertex[3];
		for(int i = 0; i < 3; i++){
			this.vertexNormals[i] = new Vertex(xyz[i].x+xyzn[i].x, xyz[i].y + xyzn[i].y, xyz[i].z + xyzn[i].z);
		}
	}
	
	protected void calcVertNorms2(Vertex[] xyz, Vertex[] xyzn){
		this.vertexNormals = new Vertex[3];
		for(int i = 0; i < 3; i++){
			Vec3 temp = Vec3.createVectorHelper(xyzn[i].x, xyzn[i].y, xyzn[i].z).normalize();
			this.vertexNormals[i] = new Vertex((float)temp.xCoord, (float)temp.yCoord, (float)temp.zCoord);
		}
	}
	
	public void addFaceForRender(Tessellator tess, float something){
		GL11.glPushMatrix();
		GL11.glBegin(GL11.GL_TRIANGLES);
		for(int i = 0; i < 3; i++){
			GL11.glTexCoord2f(this.textureCoordinates[i].u, this.textureCoordinates[i].v);
			GL11.glNormal3f(this.vertexNormals[i].x, this.vertexNormals[i].y, this.vertexNormals[i].z);
			GL11.glVertex3d(this.vertices[i].x, this.vertices[i].y, this.vertices[i].z);
		}
		
    	GL11.glEnd();
    	
    	GL11.glPopMatrix();
	}
}
