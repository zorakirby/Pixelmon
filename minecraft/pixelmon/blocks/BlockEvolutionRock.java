package pixelmon.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pixelmon.blocks.decorative.BlockContainerPlus;
import pixelmon.config.PixelmonCreativeTabs;
import pixelmon.config.PixelmonItems;
import pixelmon.enums.EnumEvolutionRock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEvolutionRock extends BlockContainerPlus {

	public EnumEvolutionRock rockType;
	
	public BlockEvolutionRock(int par1, Material par2Material, EnumEvolutionRock rockType) {
		super(par1, par2Material);		
		this.rockType = rockType;
		this.setIdDropped(-1);
		this.setAmountDropped(0);
		this.setRenderOptions(-1, false, false);
	}
}
