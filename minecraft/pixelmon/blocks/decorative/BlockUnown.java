package pixelmon.blocks.decorative;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import pixelmon.config.PixelmonBlocks;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BlockUnown extends Block{
	public Icon[] learningWithUnown;
	public boolean first16;
	
	public BlockUnown(int id, Material mat) {
		super(id, mat);
		this.setUnlocalizedName("unownblock");
	}
	
    public BlockUnown setAlphaFlag(boolean flag){
    	this.first16 = flag;
    	return this;
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
		int offset = this.first16 ? 0 : 16;
        return this.learningWithUnown[par2];
    }
	
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(int id, CreativeTabs par2CreativeTabs, List par3List)
    {
    	int amt = first16 ? 16 : 13;
        for (int i = 0; i < amt ; i++)
        {
            par3List.add(new ItemStack(id, 1, i));
        }
    }
    
    public static void registerNames(){
    	String baseName = LanguageRegistry.instance().getStringLocalization("tile.unownblock.name");
    	for (int i = 0; i < 28; i++){
    		ItemStack stackForNaming = new ItemStack(PixelmonBlocks.unownBlockId, 1, i);
    		LanguageRegistry.addName(stackForNaming, baseName + " " + ((char)(i + 65)));
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
    	if((upper >= 81 && upper < 91) || upper == '!' || upper == '?' || upper == ' ')
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
    			if(upper >= 65 && upper < 91)
    				return upper - 65 < 17 ? upper - 65 : upper - 81;
    			return null;
    		}
    	}
    }


}
