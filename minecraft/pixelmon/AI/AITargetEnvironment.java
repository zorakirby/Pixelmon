package pixelmon.AI;

import java.util.ArrayList;

import pixelmon.entities.pixelmon.Entity7HasAI;
import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.entities.pixelmon.stats.BaseStats;
import pixelmon.enums.EnumType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import pixelmon.enums.EnumType;

public class AITargetEnvironment extends EntityAIBase{
	
	public World world = ModLoader.getMinecraftInstance().theWorld;
	public Entity7HasAI pickUpPixelmon;
	public ArrayList<EnumType> type = new ArrayList<EnumType>();
	public BaseStats baseStats;
	
	
	public AITargetEnvironment(Entity7HasAI entity){
        this.pickUpPixelmon = entity;
	}
	
	public boolean shouldExecute(){
		if(pickUpPixelmon.hasOwner()){
			
			if (isCorrectBiomeForType()){
				
			
			String x = "Hello";
			
			shouldSearch();
			}
			
		}
		
		return true;
	}
	
	public boolean isCorrectBiomeForType(){
		
		int intX = (int) pickUpPixelmon.posX;
		int intZ = (int) pickUpPixelmon.posZ;
		BiomeGenBase biome = world.getBiomeGenForCoordsBody(intX, intZ);
		boolean boolIsCorrect = false;
		
		for (EnumType type : pickUpPixelmon.type){
			 if (type == EnumType.Water){
				 
				 if (biome == BiomeGenBase.ocean || biome == BiomeGenBase.river || biome == BiomeGenBase.swampland || biome == BiomeGenBase.frozenRiver){
					 boolIsCorrect = true;
				 }
			 }
				 
			 else if (type == EnumType.Normal){
			 }
			
				 else if (type == EnumType.Fire){
					 
				 }
				 else if (type == EnumType.Electric){
					 
				 }
				 else if (type == EnumType.Grass){
					 
					 if (biome == BiomeGenBase.plains || biome == BiomeGenBase.forest || biome == BiomeGenBase.forestHills || biome == BiomeGenBase.jungle || biome == BiomeGenBase.jungleHills){
						 boolIsCorrect = true;
					 }
					 
				 }
				 else if (type == EnumType.Poison){
					 
				 }
				 else if (type == EnumType.Flying){
					 
				 }
				 else if (type == EnumType.Ground){
					 
				 }
				 else if (type == EnumType.Bug){
					 
				 }
				 else if (type == EnumType.Psychic){
					 
				 }
				 else if (type == EnumType.Rock){
					 
				 }
				 else if (type == EnumType.Fighting){
					 
				 }
				 else if (type == EnumType.Dark){
					 
				 }
				 else if (type == EnumType.Steel){
					 
				 }
				 else if (type == EnumType.Ghost){
					 
				 }
				 else if (type == EnumType.Dragon){
					 
				 }
				 
			 }
			 
			return boolIsCorrect;
		}
		
		
		
		
		
		
		
				 
	public boolean shouldSearch(){
		int intSuccessChances = 1;
		int intTotalChances = 10;
		int rand = MinecraftServer.getServer().worldServerForDimension(0).rand.nextInt(intTotalChances);
		sendPlayerMessage("Your Pokemon Sees Something" + pickUpPixelmon.type);
		if (rand <=intSuccessChances){
			return true;
		} else {
			return false;
		}    
	}
	
	
	public void sendPlayerMessage(String message){
		  EntityPlayerMP player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(this.pickUpPixelmon.getOwnerName());
		  player.sendChatToPlayer(ChatMessageComponent.createFromText(message));
		 }
	
}
