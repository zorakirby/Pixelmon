package pixelmon.api.events;

import java.util.ArrayList;

public class PixelmonEventHandler {
	private static ArrayList<IPixelmonEventHandler> eventHandlers = new ArrayList<IPixelmonEventHandler>();
	
	public static void registerEventHandler(IPixelmonEventHandler e){
		eventHandlers.add(e);
	}

	public static void deRegisterEventHandler(IPixelmonEventHandler e){
		eventHandlers.remove(e);
	}

	public static void fireEvent(EventType e){
		for (int i =0; i < eventHandlers.size(); i++){
			eventHandlers.get(i).eventFired(e);
		}
	}
}
