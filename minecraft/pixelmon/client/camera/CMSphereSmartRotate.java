package pixelmon.client.camera;

public class CMSphereSmartRotate extends CMSphereSmartStill {
	long lastMoveFrame = 0;
	
	@Override
	public void updateCameraPosition() {
		long frameStart = System.nanoTime();
		if(lastMoveFrame == 0)
			lastMoveFrame = frameStart;
		double deltaTime = (double)(frameStart - lastMoveFrame)/1000000000;
		lastMoveFrame = frameStart;
		CameraTarget tar = camera.getTarget();
		double bPosYaw = posYaw+(0.3*deltaTime);
		double bPosPitch = posPitch;
		moveRadius = preferedMoveRadius;
		double pX = tar.getX() + (moveRadius*Math.cos(bPosYaw)*Math.sin(bPosPitch));
		double pY = tar.getY() + (moveRadius*Math.cos(bPosPitch));
		double pZ = tar.getZ() + (moveRadius*Math.sin(bPosYaw)*Math.sin(bPosPitch));
		
		while(bPosYaw > twoPI)
			bPosYaw-=twoPI;
		
		while(!SmartCameraUtils.canCameraSeeTargetFrom(camera, pX, pY, pZ) || !camera.worldObj.isAirBlock((int)Math.floor(pX), (int)Math.floor(pY), (int)Math.floor(pZ))){
			moveRadius--;
			if(moveRadius < 1)
				break;
			pX = tar.getX() + (moveRadius*Math.cos(bPosYaw)*Math.sin(bPosPitch));
			pY = tar.getY() + (moveRadius*Math.cos(bPosPitch));
			pZ = tar.getZ() + (moveRadius*Math.sin(bPosYaw)*Math.sin(bPosPitch));
		}
		posYaw = bPosYaw;
		posPitch = bPosPitch;
		camera.prevPosX = camera.posX = pX;
		camera.prevPosY = camera.posY = pY;
		camera.prevPosZ = camera.posZ = pZ;
	}
}
