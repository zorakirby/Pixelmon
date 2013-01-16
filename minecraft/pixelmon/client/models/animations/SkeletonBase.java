package pixelmon.client.models.animations;

import net.minecraft.client.model.ModelRenderer;

public class SkeletonBase {
	float toDegrees = 57.29578F;
	float toRadians = 1 / toDegrees;
	ModelRenderer headPiece;
	float headStartAngleX, headStartAngleY;

	public SkeletonBase(ModelRenderer headPiece) {
		this.headPiece = headPiece;
		headStartAngleX = headPiece.rotateAngleX;
		headStartAngleY = headPiece.rotateAngleY;
	}

	public void walk(float f, float f1, float f2, float f3, float f4) {
		animateHead(f3, f4);
	}

	public void animateHead(float rotateAnglePitch, float rotateAngleYaw) {
		headPiece.rotateAngleX = rotateAngleYaw * toRadians + headStartAngleX;
		headPiece.rotateAngleY = rotateAnglePitch * toRadians + headStartAngleY;
	}
}
