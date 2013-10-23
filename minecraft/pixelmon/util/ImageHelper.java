package pixelmon.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import pixelmon.Pixelmon;

public class ImageHelper {
	
	private static String LOAD_ERROR = "There was an error loading the %s \"%s\".";
	
	public static BufferedImage getImage(String fileLoc) throws IOException{
		InputStream in = null;
		try{
			fileLoc = fileLoc.replaceAll("//", "/");
			in = Pixelmon.class.getResourceAsStream(fileLoc);
			return ImageIO.read(in);
		}catch(IOException e){
			throw new IOException(String.format(LOAD_ERROR, "file", fileLoc));
		}
	}
	
	public static BufferedImage getImage(ResourceLocation resLoc) throws IOException{
		try{
			InputStream in = Minecraft.getMinecraft().getResourceManager().getResource(resLoc).getInputStream();
			return ImageIO.read(in);
		}catch(IOException e){
			throw new IOException(String.format(LOAD_ERROR, "ResourceLocation", resLoc));
		}
	}
	
	public static BufferedImage rotateImg(BufferedImage img, double rotation){
		double midX = img.getWidth()*.5;
		double midY = img.getHeight()*.5;
		double radians = Math.toRadians(rotation);
		AffineTransform trans = new AffineTransform();
		trans.rotate(radians, midX, midY);
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

}
