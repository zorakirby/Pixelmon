package pixelmon.client.materials;

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
import net.minecraft.client.resources.ResourceManager;
import net.minecraft.util.ResourceLocation;
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
import pixelmon.util.ImageHelper;
import pixelmon.util.testing.AbstractDrawable;
import pixelmon.util.testing.TestingCanvas;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Cubemaps draw simulated reflections via a texture. The width:height ratio of an
 * image must be exactly 3:4 to be used as a cubemap. Cubemaps currently override 
 * standard textures completely: if a model that normally has a regular texture 
 * applied to it starts a cubemap, the regular texture will not be used.
 * @see pixelmon.client.models.pokemon.ModelGlalie#render(net.minecraft.entity.Entity, float, float, float, float, float, float)
 */
@SideOnly(Side.CLIENT)
public class Cubemap extends SimpleTexture{
	
	public int sqr, width, height;
	public final ByteBuffer[] buffers = new ByteBuffer[6];
	private static boolean saidEnough = false;
	protected static HashMap<ResourceLocation, Cubemap> cubemaps = new HashMap();
	
	public final ResourceLocation resourceLoc;
	
	private static String dimError = "The Dimensions of %s are invalid! A Cubemap image must have an exact width-to-height ratio of 3:4!";
	
	static{
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
	    GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL12.GL_TEXTURE_WRAP_R, GL12.GL_CLAMP_TO_EDGE);
	    GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
	    GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
	}

	
	/**
	 * @param resLoc A {@link ResourceLocation} denoting the location of the Image file.
	 * @throws IOException If the file could not be found, or if the width-to-height ratio is not 3:4
	 */
	public Cubemap(ResourceLocation resLoc)throws IOException{
		super(resLoc);
		this.resourceLoc  = resLoc;
		BufferedImage fullImg = ImageHelper.getImage(resLoc);
		initBuffers(fullImg, resLoc.toString());
		//newBinding();
		cubemaps.put(resLoc, this);
	}
	//GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X+i, 0, GL11.GL_RGBA8, sqr, sqr, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
	
	protected void initBuffers(BufferedImage fullImg, String fileLoc)throws IOException{
		width = fullImg.getWidth();
		height = fullImg.getHeight();
		if(height % 4.0 != 0 || width % 3.0 != 0 || height / 4 != width / 3){
			throw new IOException(String.format(dimError, fileLoc));
		}
		sqr = width / 3;
		BufferedImage subImg = null;
		for(int i = 0; i < 6; i++){
			switch(i){
			case 0 : subImg = ImageHelper.rotateImg(fullImg.getSubimage(2*sqr, sqr, sqr, sqr), 90);break; //east
			case 1 : subImg = ImageHelper.rotateImg(fullImg.getSubimage(0, sqr, sqr, sqr), -90);break; //west
			case 2 : subImg = fullImg.getSubimage(sqr, sqr, sqr, sqr);break; //up
			case 3 : subImg = fullImg.getSubimage(sqr, 3*sqr, sqr, sqr);break; //down
			case 4 : subImg = fullImg.getSubimage(sqr, 2*sqr, sqr, sqr);break; //south
			case 5 : subImg = ImageHelper.rotateImg(fullImg.getSubimage(sqr, 0, sqr, sqr), 180);break;//north
			}
			//TestingCanvas canvas = TestingCanvas.createSimpleScreen("Cubemap " + fileLoc + "_" + i);
			//canvas.setDrawables(new AbstractDrawable.ImageDrawing(subImg));
			ByteBuffer buffer = ImageHelper.getBuffer(subImg);
			buffers[i] = buffer;
			GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X+i, 0, GL11.GL_RGBA8, sqr, sqr, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		}
	}

	
	public static void preloadCubemaps(){
		for(Field field : RenderResources.class.getFields()){
			if(field.isAnnotationPresent(CubemapTexture.class)){
				ResourceLocation resLoc = null;
				try {
					resLoc = (ResourceLocation) field.get(null);
					new Cubemap(resLoc);
				} catch (Exception e){
					e.printStackTrace();
				}
				
			}
		}
	}

	/**
	 * runs the OpenGL that enables the cubemap. this will do nothing if "advanced OpenGL" is 
	 * disabled. This should be run before rendering anything to be cubemapped, then Cubemap.end() 
	 * should be called to disable the cubemap, otherwise everything on the screen will be cubemapped!
	 * @param cubemapName - the name of the cubemap, this would be the same name that was used in "preloadCubemap"
	 * 
	 */
	public static void begin(ResourceLocation resLoc){
		//currentCubemap = resLoc;
		if(!Minecraft.getMinecraft().isFancyGraphicsEnabled()){
			return;
		}
		Cubemap map = cubemaps.get(resLoc);
		if(map == null){
			try {
				map = new Cubemap(resLoc);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.start();
	}
	
	protected void newBinding(){
			int bindID = this.getGlTextureId();
			GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, bindID);
			for(int i = 0; i < 6; i++){
				GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X+i, 0, GL11.GL_RGBA8, sqr, sqr, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffers[i]);
			}
			//GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, bindID);
	}
	
	protected void start(){
		for(int i = 0; i < 6; i++){
			GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X+i, 0, GL11.GL_RGBA8, sqr, sqr, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffers[i]);
			}
	    GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL13.GL_REFLECTION_MAP);
		GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL13.GL_REFLECTION_MAP);
		GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL13.GL_REFLECTION_MAP);
	    GL11.glEnable(GL11.GL_TEXTURE_GEN_S);
	    GL11.glEnable(GL11.GL_TEXTURE_GEN_T);
	    GL11.glEnable(GL11.GL_TEXTURE_GEN_R);
	    GL11.glEnable(GL13.GL_TEXTURE_CUBE_MAP);
	    GL11.glEnable(GL11.GL_NORMALIZE);
	}
	
	/**
	 * Performs the necessary steps to ensure that the next thing rendered does not also 
	 * have a {@code CubeMap} applied to it, assuming it isn't supposed to.
	 */
	public static void end(){
	    GL11.glEnable(GL11.GL_TEXTURE_2D);
	    GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
	    GL11.glDisable(GL11.GL_TEXTURE_GEN_S);
	    GL11.glDisable(GL11.GL_TEXTURE_GEN_T);
	    GL11.glDisable(GL11.GL_TEXTURE_GEN_R);
	    GL11.glDisable(GL13.GL_TEXTURE_CUBE_MAP);
	}
	
	public static void debugResourceStuff(ResourceLocation resLoc){
		Field texManagerResManager = null;
		TextureManager texManager = Minecraft.getMinecraft().renderEngine;
		ResourceManager resManager = null; //class net.minecraft.client.resources.SimpleReloadableResourceManager
		Resource resource = null;
		BufferedImage img = null;
		try {
			texManagerResManager = TextureManager.class.getDeclaredField("theResourceManager");
			texManagerResManager.setAccessible(true);
			resManager = (ResourceManager) texManagerResManager.get(texManager);
			resource = resManager.getResource(resLoc);
			InputStream stream = resource.getInputStream();
			img = ImageIO.read(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String description = "We start with a fresh patty, grilled and juicy. Add some crisp undersea veggies and cheese. Topped off with secret sauce and some buns. Voila! A Krabby Patty.";
		String resLocName = "ResourceLocation("+resLoc+")";
		SimpleTexture texture = new SimpleTexture(resLoc);
		System.out.println(img);
		
		
	}
	
	@Override
	public String toString(){
		return "Cubemap(" + sqr + "x" + sqr + ";" + resourceLoc + ")";
	}

	
	/**
	 * Mark a field for use with a Cubemap Texture (fields in {@link RenderResources})
	 */
	@Retention(RetentionPolicy.RUNTIME)
	public @interface CubemapTexture{};
}
