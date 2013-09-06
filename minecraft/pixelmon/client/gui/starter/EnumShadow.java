package pixelmon.client.gui.starter;

import pixelmon.client.gui.starter.Shadow.MoveDirection;

public enum EnumShadow {
	Large(0.02f, 600, 120), Medium(0.1f, 400, 80), Small(0.2f, 300, 60);

	public float moveSpeedModifier;
	public int width;
	public int height;

	private EnumShadow(float moveSpeedModifier, int width, int height) {
		this.moveSpeedModifier = moveSpeedModifier;
		this.width = width;
		this.height = height;
	}
}
