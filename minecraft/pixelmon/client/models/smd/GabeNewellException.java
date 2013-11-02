package pixelmon.client.models.smd;

public class GabeNewellException extends Exception{
	private static final String prefix = "Uhh, nope. It's just ";
	public GabeNewellException(String message){
		super(prefix + message);
	}
}
