package pixelmon.battles.controller;

import java.util.ArrayList;

import pixelmon.battles.status.Clear;
import pixelmon.battles.status.GlobalStatusBase;
import pixelmon.battles.status.Hail;
import pixelmon.battles.status.Rainy;
import pixelmon.battles.status.Sandstorm;
import pixelmon.battles.status.Sunny;

public class GlobalStatusController {
	
	private ArrayList<GlobalStatusBase> globalStatuses = new ArrayList<GlobalStatusBase>();
	
	public GlobalStatusBase getWeather()
	{
		for (int i = 0; i < globalStatuses.size(); i++)
		{
			GlobalStatusBase g = globalStatuses.get(i);
			if (g instanceof Rainy || g instanceof Clear || g instanceof Sandstorm || g instanceof Sunny || g instanceof Hail)
				return globalStatuses.get(i);
		}
		return null;
	}
	
	public void removeGlobalStatus(GlobalStatusBase g)
	{
		for (int i = 0; i < globalStatuses.size(); i++)
		{
			if (globalStatuses.get(i) == g)
			{
				if (g instanceof Rainy || g instanceof Sandstorm  || g instanceof Sunny  || g instanceof Hail)
					globalStatuses.add(new Clear());
				globalStatuses.remove(i);	
			}
		}
	}
	
	public void addGlobalStatus(GlobalStatusBase g)
	{
		if (g instanceof Rainy || g instanceof Sandstorm || g instanceof Sunny || g instanceof Hail)
			for (int i = 0; i < globalStatuses.size(); i++)
				if (globalStatuses.get(i) instanceof Clear)
					globalStatuses.remove(i);
		globalStatuses.add(g);
			
	}
	
	public GlobalStatusBase getGlobalStatus(int index)
	{
		return globalStatuses.get(index);
	}
	public int getGlobalStatusSize()
	{
		return globalStatuses.size();
	}
}


