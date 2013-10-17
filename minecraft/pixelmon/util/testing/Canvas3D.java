package pixelmon.util.testing;

import java.awt.Canvas;
import java.util.Collection;
import java.util.Random;

import net.minecraft.util.MouseHelper;
import net.minecraft.util.Vec3;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

import pixelmon.client.models.smd.VectorHelper;
import pixelmon.util.AbstractList2D;
import pixelmon.util.testing.Navigator.MouseEvent;
import pixelmon.worldGeneration.WorldGenFogboundLake;

import com.google.common.eventbus.Subscribe;

public class Canvas3D{
	
	int displayWidth, displayHeight;
	float xr = 0, yr, zr, farPlaneDistance, zoom = 0;
	boolean fullscreen = false;
	
	Canvas canvas;
	MouseHelper mouseHelper;
	static AbstractList2D<Float>[] drawables;
	
	public Canvas3D(int displayWidth, int displayHeight, float farPlaneDist){
		this.displayWidth = displayWidth;
		this.displayHeight = displayHeight;
		this.farPlaneDistance = farPlaneDist;
		initDrawable();
		try{
      	Display.setDisplayMode(new DisplayMode(this.displayWidth, this.displayHeight));
    	Display.setTitle("3D Testing Grid");
      	Display.create();
		}catch(Exception e){
			e.printStackTrace();
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(30, (float)displayWidth/(float)displayHeight, .5F, farPlaneDist);
    	GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	public static void main(String[] args){
		Canvas3D c3d = new Canvas3D(1000, 700, 3000);
		Navigator.register(c3d);
		Navigator.init();
		try {
			c3d.start();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}


    public void start() throws LWJGLException{
    	GL11.glTranslatef(0, 0, -1000);
    	while (!Display.isCloseRequested()) {
    		Navigator.onTick();
    		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    		GL11.glPushMatrix();
    		GL11.glTranslatef(0, 0, zoom);
    		GL11.glRotatef(xr, 1, 0, 0);
    		GL11.glRotatef(-45, 0, 1, 0);
    		GL11.glTranslatef(0, -200, 0);
    		drawSomething(400);
    		GL11.glPopMatrix();     
    	    Display.update();
    	    xr-=.2F;
    	    try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	Display.destroy();
    	//timer.start();
    }


	private void initDrawable() {
		drawables = WorldGenFogboundLake.fogboundLake(new Random(),  2.5F, 1.5F);
	}

	public static void drawSomething(int yScale){
		if(drawables == null) return;
		for(AbstractList2D<Float> drawable : drawables){
			for(Integer i : drawable.xList()){
				Collection<Integer> column = drawable.zList(i);
				for(Integer j : column){
					Vector3f current = new Vector3f(i*10F, drawable.get(i, j) * yScale, j*10F);
					Vector3f east = VectorHelper.createOrNull((i+1)*10F, drawable.get(i+1, j), j*10F);
					Vector3f south = VectorHelper.createOrNull(i*10F, drawable.get(i, j+1), (j+1)*10F);
					if(east != null){
						east.y *= yScale;
						drawLine(current, east);
					}
					if(south != null){
						south.y *= yScale;
						drawLine(current, south);
					}
				}
			}
		}
	}
	
	@Subscribe
	public void onMouseEvent(MouseEvent e){
		zoom += e.scrollwheel;
	}
    
    public static void drawLine(Vector3f point1, Vector3f point2){
    	drawLine(point1.x, point1.y, point1.z, point2.x, point2.y, point2.z);
    }
    
    public static void drawLine(Vec3 point1, Vec3 point2){
    	drawLine(point1.xCoord, point1.yCoord, point1.zCoord, point2.xCoord, point2.yCoord, point2.zCoord);
    }
    
    public static void drawLine(double x1, double y1, double z1, double x2, double y2, double z2){
    	GL11.glBegin(GL11.GL_LINE_STRIP);
    	GL11.glVertex3d(x1, y1, z1);
    	GL11.glVertex3d(x2, y2, z2);
    	GL11.glEnd();
    }

}
