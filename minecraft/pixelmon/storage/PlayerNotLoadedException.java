package pixelmon.storage;

public class PlayerNotLoadedException extends Exception {
	public PlayerNotLoadedException() {
	}

	public PlayerNotLoadedException(String message) {
		super(message);
	}
}
