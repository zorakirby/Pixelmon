package pixelmon.enums;

import java.util.Random;

import pixelmon.entities.pixelmon.stats.StatsType;

public enum EnumNature {
	Hardy(0, StatsType.None, StatsType.None),
	Serious(1, StatsType.None, StatsType.None),
	Docile(2, StatsType.None, StatsType.None),
	Bashful(3, StatsType.None, StatsType.None),
	Quirky(4, StatsType.None, StatsType.None),
	Lonely(5, StatsType.Attack, StatsType.Defence), 
	Brave(6, StatsType.Attack, StatsType.Speed),
	Adamant(7, StatsType.Attack, StatsType.SpecialAttack), 
	Naughty(8, StatsType.Attack, StatsType.SpecialDefence),
	Bold(9, StatsType.Defence, StatsType.Attack),
	Relaxed(10, StatsType.Defence, StatsType.Speed),
	Impish(11, StatsType.Defence, StatsType.SpecialAttack),
	Lax(12, StatsType.Defence, StatsType.SpecialDefence),
	Timid(13, StatsType.Speed, StatsType.Attack),
	Hasty(14, StatsType.Speed, StatsType.Defence),
	Jolly(15, StatsType.Speed, StatsType.SpecialAttack),
	Naive(16, StatsType.Speed, StatsType.SpecialDefence),
	Modest(17, StatsType.SpecialAttack, StatsType.Attack),
	Mild(18, StatsType.SpecialAttack, StatsType.Defence),
	Quiet(19, StatsType.SpecialAttack, StatsType.Speed),
	Rash(20, StatsType.SpecialAttack, StatsType.SpecialDefence),
	Calm(21, StatsType.SpecialDefence, StatsType.Attack),
	Gentle(22, StatsType.SpecialDefence, StatsType.Defence),
	Sassy(23, StatsType.SpecialDefence, StatsType.Speed),
	Careful(24, StatsType.SpecialDefence, StatsType.SpecialAttack);
	
	public StatsType increasedStat;
	public StatsType decreasedStat;
	public int index;

	private EnumNature(int index, StatsType increasedStat, StatsType decreasedStat){
		this.index = index;
		this.increasedStat = increasedStat;
		this.decreasedStat = decreasedStat;
	}
	
	public static EnumNature getNatureFromIndex(int index){
		for (EnumNature n: values())
			if (n.index == index) return n;
		return null;
	}
	
	public static EnumNature getRandomNature(){
		int rndm = new Random().nextInt(25);
		return getNatureFromIndex(rndm);
	}
}
