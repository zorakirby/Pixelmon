package pixelmon.enums;

public enum EnumApricornTrees {

	RedTree(EnumApricorns.Red, new String[]{ "ModelApricornTreeSprout", "ModelApricornTreeStage1", "ModelApricornTreeStage2", "ModelApricornTreeStage3", "ModelApricornTreeFinal" });
	
	public String[] modelList;
	public EnumApricorns apricorn;
	
	private EnumApricornTrees(EnumApricorns apricorn, String[] modelList){
		this.modelList = modelList;
	}
}
