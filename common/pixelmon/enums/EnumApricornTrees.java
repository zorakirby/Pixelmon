package pixelmon.enums;

public enum EnumApricornTrees {

	RedTree(0, EnumApricorns.Red, new String[]{ "ModelApricornTreeSprout", "ModelApricornTreeStage1", "ModelApricornTreeStage2", "ModelApricornTreeStage3", "ModelApricornTreeFinal" });
	
	public String[] modelList;
	public EnumApricorns apricorn;
	public int id;
	
	private EnumApricornTrees(int id, EnumApricorns apricorn, String[] modelList){
		this.id = id;
		this.modelList = modelList;
		this.apricorn = apricorn;
	}
	
	public static EnumApricornTrees getFromID(int id){
		for (EnumApricornTrees e: values())
			if (e.id == id) return e;
		
		return null;
	}
}
