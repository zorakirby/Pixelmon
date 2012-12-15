package pixelmon.enums;

import java.lang.reflect.Field;
import java.util.Random;

public enum EnumTownStructureType{
	houseBasic(0, EnumTownType.basicTown, 2, false, "house"),
	pokecenterBasic(1, EnumTownType.basicTown, 5, false, "pokecenter"),
	pokemartBasic(2, EnumTownType.basicTown, 5, false, "pokemart"),
	tradingCenterBasic(3, EnumTownType.basicTown, 5, false, "tradingCenter"),
	fountainSnow(4, EnumTownType.snowTown, 1, true, "fountain"),
	snowmanSnow(5, EnumTownType.snowTown, 10, true, "snowman"),
	houseSnow(6, EnumTownType.snowTown, 2, false, "house"),
	pokecenterSnow(7, EnumTownType.snowTown, 5, false, "pokecenter"),
	pokemartSnow(8, EnumTownType.snowTown, 5, false, "pokemart"),
	tradingCenterSnow(9, EnumTownType.snowTown, 5, false, "tradingCenter"),
	healingCenterSnow(10, EnumTownType.snowTown, 7, false, "healingcenter"),
	;
	
	int structureId;
	EnumTownType townToGenIn;
	int rarity;
	boolean centerPiece;
	String schematicName;
	
	private EnumTownStructureType(int structureId, EnumTownType townToGenIn, int rarity, boolean centerPiece, String schematicName){
		this.structureId = structureId;
		this.townToGenIn = townToGenIn;
		this.rarity = rarity;
		this.centerPiece = centerPiece;
		this.schematicName = schematicName;
	}
	
	public int getStructureId(){
		return this.structureId;
	}
	
	public EnumTownType getTownToGenIn(){
		return this.townToGenIn;
	}
	
	public int getRarity(){
		return new Random().nextInt(this.rarity);
	}
	
	public boolean isCenterPiece(){
		return this.centerPiece;
	}
	
	public String getschematicName(){
		return this.getTownToGenIn().folderPath + this.schematicName + ".schematic";
	}
	
	public static EnumTownStructureType getHouseFromTown(EnumTownType town) {// May need to work on this stuff,
		try {
			for (Field field : EnumTownStructureType.class.getFields()) {
				EnumTownStructureType structure = (EnumTownStructureType) field.get(null);
				if (structure.townToGenIn == town)
					return structure;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static EnumTownStructureType getCenterPieceForTown(EnumTownType town) {
		try {
			for (Field field : EnumTownStructureType.class.getFields()) {
				EnumTownStructureType structure = (EnumTownStructureType) field.get(null);
				if (structure.townToGenIn == town && structure.isCenterPiece())
					return structure;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
