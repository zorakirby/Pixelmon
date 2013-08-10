package pixelmon.comm;

public enum EnumPackets {
	ChooseStarter(10), RegisterPlayer(11), AddToStorage(12), RemoveFromStorage(13), SendPokemon(14), AddToTempStore(15), ChooseAttack(16), ClearTempStore(17), UpdateStorage(
			18), HealPokemon(19), SwitchPokemon(20), Flee(21), RenamePokemon(22), ReplaceMove(24), BagPacket(25), StopStartLevelling(26), BattleMessage(27), SetOpponent(
			28), SetBattlingPokemon(29), BackToMainMenu(30), ExitBattle(31), ChooseMoveToReplace(32), LevelUp(33), SetOpponentType(34), SetHeldItem(35), SwapMove(
			36), EnforcedSwitch(37), DeleteMove(38), Pokedex(39), Movement(40), RegisterTrader(41), SelectPokemonForTrade(42), SetTradeTarget(43), SetTradeTargetStats(
			44), SetSelectedStats(45), DeRegisterTrader(46), SetTradingReady(47), SetTradingReadyClient(48), RemoveFromTempStore(49), PCClickOnBox(50), PCClickOnParty(
			51), PCClosed(52), PCTrashPokemon(53), Trade(54), RequestPCData(55), RequestUpdatedPokemonList(56), SetMousePokemon(57), ClearMousePokemon(58), Transform(
			59), SwitchCamera(60), PlayerDeath(61);
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
