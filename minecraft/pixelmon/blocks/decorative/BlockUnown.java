package pixelmon.blocks.decorative;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMultiTextureTile;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import pixelmon.config.PixelmonBlocks;
import pixelmon.util.PixelmonDebug;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/*2013-11-02 20:08:12 [INFO] [STDOUT] [Begin stack trace]
2013-11-02 20:08:12 [INFO] [STDOUT] Thread.getStackTrace(line -1)
2013-11-02 20:08:12 [INFO] [STDOUT] PixelmonDebug.printStackElements(line 117)
2013-11-02 20:08:12 [INFO] [STDOUT] BlockUnown.getUnlocalizedName(line 135)
2013-11-02 20:08:12 [INFO] [STDOUT] ItemBlock.getUnlocalizedName(line 189)
2013-11-02 20:08:12 [INFO] [STDOUT] Item.getUnlocalizedNameInefficiently(line 469)
2013-11-02 20:08:12 [INFO] [STDOUT] Item.getItemDisplayName(line 616)
2013-11-02 20:08:12 [INFO] [STDOUT] ItemStack.getDisplayName(line 559)
2013-11-02 20:08:12 [INFO] [STDOUT] GuiIngameForge.renderToolHightlight(line 568)
2013-11-02 20:08:12 [INFO] [STDOUT] GuiIngameForge.renderGameOverlay(line 154)
2013-11-02 20:08:12 [INFO] [STDOUT] EntityRenderer.updateCameraAndRender(line 1014)
2013-11-02 20:08:12 [INFO] [STDOUT] Minecraft.runGameLoop(line 945)
2013-11-02 20:08:12 [INFO] [STDOUT] Minecraft.run(line 837)
2013-11-02 20:08:12 [INFO] [STDOUT] Main.main(line 93)*/
public class BlockUnown extends Block{
	public static final String[] 
			alphabet1 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"},
			alphabet2 = {"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "!", "?", "_"};
	public Icon[] learningWithUnown;
	public boolean first16;
	
	public BlockUnown(int id, Material mat, boolean first16) {
		super(id, mat);
		this.setUnlocalizedName("unownblock_"+(first16 ? '1' : '2'));
		this.first16 = first16;
	}

	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		int amt = this.first16 ? 16 : 10;
		int arraySize = this.first16 ? 16 : 13;
		int offset = this.first16 ? 0 : 16;
		this.learningWithUnown = new Icon[arraySize];
		for(int i = 0; i < amt; i++){
			String tex = "pixelmon:unown/" + ((char) (i + 65 + offset)); //65 -> char = A
			learningWithUnown[i] = par1IconRegister.registerIcon(tex);
		}
		if(!this.first16){
			learningWithUnown[10] = par1IconRegister.registerIcon("pixelmon:unown/EX"); //!
			learningWithUnown[11] = par1IconRegister.registerIcon("pixelmon:unown/QU"); //?
			learningWithUnown[12] = par1IconRegister.registerIcon("pixelmon:unown/base");
		}
	}
	
	 /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }
    

	
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack){
    	world.setBlockMetadataWithNotify(x, y, z, stack.getItemDamage(), 2);
    }
    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return this.learningWithUnown[par2];
    }
	
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(int id, CreativeTabs par2CreativeTabs, List list)
    {
    	if(!this.first16)
    		return;
        for (int i = 0; i < 29; i++)
        {
        	char letter = i == 26 ? '!' : i == 27 ? '?' : i == 28 ? ' ' : (char) ('A'+i);
        	Integer[] idAndMeta = getBlockIDAndMetaFor(letter);
        	ItemStack stack = new ItemStack(idAndMeta[0], 1, idAndMeta[1]);
        	list.add(stack);
        	LanguageRegistry.addName(stack, "Unown Block '"+letter + "'");
        }
    }
    
    public static void initNaming(){
    	for (int i = 0; i < 29; i++)
        {
        	char letter = i == 26 ? '!' : i == 27 ? '?' : i == 28 ? ' ' : (char) ('A'+i);
        	Integer[] idAndMeta = getBlockIDAndMetaFor(letter);
        	ItemStack stack = new ItemStack(idAndMeta[0], 1, idAndMeta[1]);
        	String name = letter == ' ' ? "Blank Unown Block" : "Unown Block '"+letter + "'";
        	LanguageRegistry.addName(stack, name);
        }
    }
    
    /**
     * @param letter
     * @return The index of the character in the alphabet, 26 if the character is '!', 27 if it's '?', -1 if it's ' ', or <code>null</code> otherwise
     */
    public static Integer[] getBlockIDAndMetaFor(char letter){
    	return new Integer[]{getBlockIDFor(letter), getMetaFor(letter)};
    }
    
    private static Integer getBlockIDFor(char letter){
    	char upper = Character.toUpperCase(letter);
    	if(upper >= 65 && upper < 81)
    		return PixelmonBlocks.unownBlockId;
    	if((upper >= 81 && upper <= 90) || upper == '!' || upper == '?' || upper == ' ')
    		return PixelmonBlocks.unownBlockId2;
    	return null;
    }
    
    private static Integer getMetaFor(char letter){
    	switch(letter){
    		case '!' : return 10;
    		case '?' : return 11;
    		case ' ' : return 12;
    		default : {
    			char upper = Character.toUpperCase(letter);
    			if(upper >= 'A' && upper <= 90)
    				return upper  > 80 ? upper - 81 : upper - 65;
    			return null;
    		}
    	}
    }
    
/*    @Override
    public String getUnlocalizedName()
    {
    	PixelmonDebug.printStackElements(13);
    	return super.getUnlocalizedName();
    }*/


}
