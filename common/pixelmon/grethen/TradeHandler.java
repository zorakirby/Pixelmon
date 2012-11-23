package pixelmon.grethen;

import pixelmon.entities.pixelmon.EntityPixelmon;
import pixelmon.storage.PlayerStorage;
import pixelmon.storage.PokeballManager.PokeballManagerMode;

public class TradeHandler
{
	
	public final PlayerStorage[] players;
	public final boolean[] confirmed;
	public final EntityPixelmon[] offers;
	
	public TradeHandler(PlayerStorage... p)
	{
		players = new PlayerStorage[2];
		players[0] = p[0];
		players[1] = p[1];
		confirmed = new boolean[2];
		offers = new EntityPixelmon[2];
	}
	
	public void setCurrentOffer(int player, EntityPixelmon e)
	{
		if(player > offers.length)
			return;
		offers[player] = e;
	}
	
	public EntityPixelmon getCurrentOffer(int player)
	{
		if(player > offers.length)
			return null;
		return offers[player];
	}
	
	public void setConfirmed(int player, boolean confirm)
	{
		if(player > confirmed.length)
			return;
		confirmed[player] = confirm;
	}
	
	public void trade()
	{
		PlayerStorage t1 = players[0];
		PlayerStorage t2 = players[1];
		EntityPixelmon p1 = offers[0];
		EntityPixelmon p2 = offers[1];
		if(confirmed[0] && confirmed[1] && p1 != null && p2 != null)
		{
			t1.retrieve(p1);
			t2.retrieve(p2);
			String s = p1.getOwnerName();
			if(t2.mode == PokeballManagerMode.Trainer)
				p1.setTrainer(t2.trainer);
			else
				p1.setOwner(p2.getOwnerName());
			if(t1.mode == PokeballManagerMode.Trainer)
				p2.setTrainer(t1.trainer);
			else
				p2.setOwner(s);
			t1.addToParty(p2);
			t2.addToParty(p1);
		}
	}
	
}