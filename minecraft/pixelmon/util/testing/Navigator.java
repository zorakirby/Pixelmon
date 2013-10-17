package pixelmon.util.testing;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

import com.google.common.eventbus.EventBus;

public class Navigator {
	public static final EventBus INPUT_BUS = new EventBus();
	
	public static void init(){
		try{
			Mouse.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
	}
	
	
	public static void onTick(){
		while(Mouse.next()){
			int button = Mouse.getEventButton();
			int scrollWheel = Mouse.getEventDWheel();
			int x = Mouse.getEventX();
			int y = Mouse.getEventY();
			boolean state = Mouse.getEventButtonState();
			new MouseEvent(button, scrollWheel, x, y, state);
		}
	}
	
	public static class MouseEvent{
		public final int button, scrollwheel, x, y;
		public final boolean pressed;
		
		public MouseEvent(int button, int scroll, int x, int y, boolean pressed){
			this.button = button;
			this.scrollwheel = scroll;
			this.x = x;
			this.y = y;
			this.pressed = pressed;
			INPUT_BUS.post(this);
		}
	}
	
	public static void register(Object obj){
		INPUT_BUS.register(obj);
	}

}
