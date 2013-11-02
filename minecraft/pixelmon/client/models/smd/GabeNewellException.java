package pixelmon.client.models.smd;

import net.minecraftforge.client.model.ModelFormatException;

public class GabeNewellException extends ModelFormatException{
	private static final String prefix = "Uhh, nope. It's just ";
	public GabeNewellException(String message){
		super(prefix + message);
	}
}
