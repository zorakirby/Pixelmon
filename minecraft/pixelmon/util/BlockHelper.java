package pixelmon.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.logging.Level;

import com.google.common.collect.Multimap;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderException;
import cpw.mods.fml.common.LoaderState;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.registry.BlockProxy;
import cpw.mods.fml.common.registry.GameRegistry;
import pixelmon.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTextureTile;
import net.minecraftforge.common.ForgeDirection;

public class BlockHelper {
	
	public static Field blockRegistryField;
	
	static{
		try{
			blockRegistryField = GameRegistry.class.getDeclaredField("blockRegistry");
			blockRegistryField.setAccessible(true);
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static boolean isBlockNormalCube(Block block){
		return block.blockMaterial.isOpaque() && block.renderAsNormalBlock() && !block.canProvidePower();
	}
	
	public static void registerMultiNameBlock(Block block, String name, String[] namesByID)
    {
        if (Loader.instance().isInState(LoaderState.CONSTRUCTING))
        {
            FMLLog.warning("The mod %s is attempting to register a block whilst it it being constructed. This is bad modding practice - please use a proper mod lifecycle event.", Loader.instance().activeModContainer());
        }
        try
        {
        	ItemMultiTextureTile itemBlock = new ItemMultiTextureTile(block.blockID - 256, block, namesByID);
            GameRegistry.registerItem(itemBlock, name, Loader.instance().activeModContainer().getModId());
        }
        catch (Exception e)
        {
            FMLLog.log(Level.SEVERE, e, "Caught an exception during block registration");
            throw new LoaderException(e);
        }
        Multimap<ModContainer, BlockProxy> blockRegistry;
		try {
			blockRegistry = (Multimap<ModContainer, BlockProxy>) blockRegistryField.get(null);
			blockRegistry.put(Loader.instance().activeModContainer(), (BlockProxy) block);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
