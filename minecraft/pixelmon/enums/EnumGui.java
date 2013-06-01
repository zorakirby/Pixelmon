package pixelmon.enums;


public enum EnumGui {
	ChoosePokemon(0),
	ChooseAttack(1),
	ChooseStarter(2),
	FaintedChoice(3),
	Healer(4),
	Pokedex(5),
	PokeChecker(6),
	RenamePokemon(7), 
	LearnMove(8), 
	PC(9), 
	Battle(10), 
	LevelUp(11),
	PokeCheckerStats(12),
	PokeCheckerMoves(13), 
	Trading(14), 
	Doctor(15);
	
	private int index;
	
	private EnumGui(int i)
	{
		index = i;
	}
	
	public Integer getIndex()
	{
		return index;
	}
}
