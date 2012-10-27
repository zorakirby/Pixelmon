package pixelmon.comm;

public enum EnumPackets {
	ChooseStarter(10), RegisterPlayer(11), AddToStorage(12), RemoveFromStorage(13), SendPokemon(14), AddToTempStore(15), ChooseAttack(16), ClearTempStore(17), UpdateStorage(
			18), HealPokemon(19), SwitchPokemon(20), Flee(21), RenamePokemon(22), PCClick(23), ReplaceMove(24), BagPacket(25), StopStartLevelling(26), BattleMessage(
			27), SetOpponent(28), SetBattlingPokemon(29), BackToMainMenu(30), ExitBattle(31), ChooseMoveToReplace(32), LevelUp(33);

	private int index;

	private EnumPackets(int i) {
		index = i;
	}

	public Integer getIndex() {
		return index;
	}

	public static EnumPackets getEnum(int readInt) {
		for (int i = 0; i < values().length; i++) {
			if (values()[i].index == readInt)
				return values()[i];
		}
		return null;
	}
}
