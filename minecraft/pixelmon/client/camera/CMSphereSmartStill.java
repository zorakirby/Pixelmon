package pixelmon.client.camera;

public class CMSphereSmartStill extends CMSphere{

	boolean random = false;
	
	public CMSphereSmartStill(){
		random = true;
	}
	
	protected void smartPositionCamera(double HAngle, double VAngle){
		CameraTarget tar = camera.getTarget();
		if(tar != null){
			double bPosYaw, bStartPosYaw = bPosYaw = HAngle;
			double bPosPitch, bStartPosPitch= bPosPitch = VAngle;
			double pX = tar.getX() + (moveRadius*Math.cos(bPosYaw)*Math.sin(bPosPitch));
			double pY = tar.getY() + (moveRadius*Math.cos(bPosPitch));
			double pZ = tar.getZ() + (moveRadius*Math.sin(bPosYaw)*Math.sin(bPosPitch));
			while(!SmartCameraUtils.canCameraSeeTargetFrom(camera, pX, pY, pZ)){
				while(bPosYaw > twoPI)
					bPosYaw-=twoPI;
				if(bPosYaw<(twoPI)-bStartPosYaw)
					bPosYaw+=0.05;
				else{
					if(bPosPitch > 0){
						bPosPitch -= 0.5;
					}
					else{
						bPosPitch = 0;
					}
				}
				pX = tar.getX() + (moveRadius*Math.cos(bPosYaw)*Math.sin(bPosPitch));
				pY = tar.getY() + (moveRadius*Math.cos(bPosPitch));
				pZ = tar.getZ() + (moveRadius*Math.sin(bPosYaw)*Math.sin(bPosPitch));
				if(bPosPitch == 0)
					break;
			}
			posYaw = bPosYaw;
			posPitch = prefPosPitch = bPosPitch;
			camera.prevPosX = camera.posX = pX;
			camera.prevPosY = camera.posY = pY;
			camera.prevPosZ = camera.posZ = pZ;
		}
	}
	
	@Override
	public void updateCameraPosition() {
		if(!SmartCameraUtils.canCameraSeeTarget(camera)){
			if(random)
				this.smartPositionCamera(camera.worldObj.rand.nextDouble()*2*Math.PI, Math.PI/2.5);
		}
	}

	@Override
	public void cameraTargetSet(){
		this.smartPositionCamera(camera.worldObj.rand.nextDouble()*2*Math.PI, Math.PI/2.5);
	}
	
}
