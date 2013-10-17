package pixelmon.client.shading;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureObject;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.Resource;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLUConstants;

import pixelmon.Pixelmon;
import pixelmon.client.render.RenderResources;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Cubemap {
	
	public int sqr;
	public final ByteBuffer[] buffers = new ByteBuffer[6];
	public static HashMap<String, Cubemap> cubemaps = new HashMap();
	protected static String currentCubemap, lastCubemap;
	
	static{
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
	    GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL12.GL_TEXTURE_WRAP_R, GL12.GL_CLAMP_TO_EDGE);
	    GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
	    GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
	}
	
	public Cubemap(String fileLoc)throws IOException{
		System.out.println("TextureName = " + fileLoc);
		BufferedImage fullImg = getImage(fileLoc);
		int fullWidth = fullImg.getWidth();
		int fullHeight = fullImg.getHeight();
		if(fullHeight % 4.0 != 0 || fullWidth % 3.0 != 0 || fullHeight / 4 != fullWidth / 3){
			throw new IOException(String.format("The Dimensions of %s are invalid!", fileLoc));
		}
		sqr = fullWidth / 3;
		BufferedImage subImg = null;
		for(int i = 0; i < 6; i++){
			switch(i){
			case 0 : subImg = rotateImg(fullImg.getSubimage(2*sqr, sqr, sqr, sqr), 90);break; //east
			case 1 : subImg = rotateImg(fullImg.getSubimage(0, sqr, sqr, sqr), -90);break; //west
			case 2 : subImg = fullImg.getSubimage(sqr, sqr, sqr, sqr);break; //up
			case 3 : subImg = fullImg.getSubimage(sqr, 3*sqr, sqr, sqr);break; //down
			case 4 : subImg = fullImg.getSubimage(sqr, 2*sqr, sqr, sqr);break; //south
			case 5 : subImg = rotateImg(fullImg.getSubimage(sqr, 0, sqr, sqr), 180);break;//north
			}
			ByteBuffer buffer = getBuffer(subImg);
			buffers[i] = buffer;
			GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X+i, 0, GL11.GL_RGBA8, sqr, sqr, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		}
}
	
	/**
	 * @param texture - name of the texture of the cubemap. You would use this the same way you would with "Preload texture".
	 */
	public static void preloadCubemap(String fileLoc){
		try {
			cubemaps.put(fileLoc, new Cubemap(fileLoc));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void preloadCubemaps(){
		System.out.println("preloading Cubemaps");
		for(Field field : RenderResources.class.getFields()){
			if(field.isAnnotationPresent(CubemapTexture.class)){
				String fileLoc = null;
				try {
					fileLoc = (String) field.get(null);
				} catch (Exception e){
					e.printStackTrace();
				}
				System.out.println("Preloading Cubemap: " + fileLoc);
				preloadCubemap(fileLoc);
			}
		}
	}
	
	
	public BufferedImage rotateImg(BufferedImage img, double rotation){
		double radians = Math.toRadians(rotation);
		AffineTransform trans = new AffineTransform();
		trans.rotate(radians, sqr/2, sqr/2);
		AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(img, null);
	}
	
	public static ByteBuffer getBuffer(BufferedImage img){
		int width = img.getWidth();
		int height = img.getHeight();
		int[] pixels = new int[img.getWidth() * img.getHeight()];
        img.getRGB(0, 0, width, height, pixels, 0, width);
        ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);
        
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int pixel = pixels[y * width + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                buffer.put((byte) (pixel & 0xFF));               // Blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
            }
        }
        buffer.flip();
        buffer.rewind();
        return buffer;
	}
	/**
	 * runs the OpenGL that enables the cubemap. this will do nothing if "advanced OpenGL" is disabled.
	 * <br> to use, you would run this before anything you want to be cubemapped, then render the thing, then BE SURE to call Cubemap.end() to disable the cubemap, otherwise everything on the screen will be cubemapped!
	 * @param cubemapName - the name of the cubemap, this would be the same name that was used in "preloadCubemap"
	 * 
	 */
	public static void begin(String fileLoc){
		currentCubemap = fileLoc;
		if(!Minecraft.getMinecraft().isFancyGraphicsEnabled()){
			return;
		}
		Cubemap map = cubemaps.get(fileLoc);
		if(map == null){
			throw new Error(String.format("Cubemap Error! The cubemap with the name %s could not be found!", fileLoc));
		}
		map.start();
	}
	
	public void start(){

	    //GL11.glDisable(GL11.GL_TEXTURE_2D);

	    
	    if(currentCubemap != lastCubemap){
	    	for(int i = 0; i < 6; i++){
	    		GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X+i, 0, GL11.GL_RGBA8, sqr, sqr, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffers[i]);
	    	}
	    }
		
		//GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_SPHERE_MAP);
		//GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_SPHERE_MAP);
	    //GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_SPHERE_MAP);
		//GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL13.GL_NORMAL_MAP);
		//GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL13.GL_NORMAL_MAP);
	    //GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL13.GL_NORMAL_MAP);
		GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL13.GL_REFLECTION_MAP);
		GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL13.GL_REFLECTION_MAP);
		GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL13.GL_REFLECTION_MAP);
	    GL11.glEnable(GL11.GL_TEXTURE_GEN_S);
	    GL11.glEnable(GL11.GL_TEXTURE_GEN_T);
	    GL11.glEnable(GL11.GL_TEXTURE_GEN_R);
	    GL11.glEnable(GL13.GL_TEXTURE_CUBE_MAP);
	    //GL30.glGenerateMipmap(GL13.GL_TEXTURE_CUBE_MAP);

	    //GL11.glEnable(GL11.GL_AUTO_NORMAL);
	    GL11.glEnable(GL11.GL_NORMALIZE);
	}
	
	/**
	 * Performs the neccessary steps to ensure that the next thing rendered doesnt also have a cubemap applied to it.
	 */
	public static void end(){
	    GL11.glEnable(GL11.GL_TEXTURE_2D);
	    GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
	    //GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
	    //GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
	    //GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL12.GL_TEXTURE_WRAP_R, GL12.GL_CLAMP_TO_EDGE);
	    //GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
	    //GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);

		//GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL13.GL_NORMAL_MAP);
		//GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL13.GL_NORMAL_MAP);
	    //GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL13.GL_NORMAL_MAP);
	    GL11.glDisable(GL11.GL_TEXTURE_GEN_S);
	    GL11.glDisable(GL11.GL_TEXTURE_GEN_T);
	    GL11.glDisable(GL11.GL_TEXTURE_GEN_R);
	    GL11.glDisable(GL13.GL_TEXTURE_CUBE_MAP);
	    //GL11.glDisable(GL11.GL_AUTO_NORMAL);
	    //GL11.glDisable(GL11.GL_NORMALIZE);
		
	}
	
	public void debugCubeMap(){
		print("CUBEMAP square size  = " + sqr);
		print("GL11 stuff");
		print(GL11.glGetString(GL11.GL_EXTENSIONS));
	}
	
	private void print(String text){
		System.out.println(text);

	}
	
	public BufferedImage getImage(String fileLoc) throws IOException{
		InputStream in = null;
		try{
			if(!fileLoc.startsWith("assets"))
				fileLoc = "/assets/" + fileLoc;
			fileLoc = fileLoc.replaceAll("//", "/");
			if(!fileLoc.endsWith(".png"))
				fileLoc+=".png";
			System.out.println(fileLoc);
			in = Pixelmon.class.getResourceAsStream(fileLoc);
			return ImageIO.read(in);
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface CubemapTexture{};
}
