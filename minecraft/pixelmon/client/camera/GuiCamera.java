package pixelmon.client.camera;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.inventory.Container;

public class GuiCamera extends GuiContainer{

	private static final ItemRenderer vanItemRenderer = Minecraft.getMinecraft().entityRenderer.itemRenderer;
	private static final ItemRenderer camItemRenderer = new CameraModeItemRenderer(Minecraft.getMinecraft());
	
	private int thirdP;
		
	protected EntityCamera camera;
	
	public GuiCamera(Container par1Container) {
		super(par1Container);
		camera = new EntityCamera(Minecraft.getMinecraft().theWorld);
		camera.setMovement(new CMSphereSmartRotate());
	}
	
	public GuiCamera(Container par1Container, EntityCamera cam) {
		super(par1Container);
		camera = cam;
	}

	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	
	@Override
	public void handleKeyboardInput() {
	}
	
	@Override
	public void drawBackground(int par1) {
	}

	@Override
	public void drawDefaultBackground() {
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
	}
	
	public EntityCamera getCamera(){
		return camera;
	}
	
	public CameraTarget getCameraTarget(){
		return camera.getTarget();
	}
	
	@Override
	public void initGui() {
		super.initGui();
		//mc.gameSettings.hideGUI = true;
		thirdP = mc.gameSettings.thirdPersonView;
		mc.gameSettings.thirdPersonView = 0;
		this.allowUserInput = false;
		mc.entityRenderer.itemRenderer = camItemRenderer;
		mc.renderViewEntity = camera;
		mc.theWorld.spawnEntityInWorld(camera);
	}
	@Override
	public void onGuiClosed() {
		System.out.println("Camera Closing");
		//mc.gameSettings.hideGUI = false;
		super.onGuiClosed();
		mc.gameSettings.thirdPersonView = thirdP;
		mc.entityRenderer.itemRenderer = vanItemRenderer;
		mc.renderViewEntity = mc.thePlayer;
		camera.setDead();
		mc.setIngameFocus();
	}
	@Override
	public void updateScreen() {
		super.updateScreen();
		if(getCameraTarget() != camera.getTarget())
			camera.setTarget(getCameraTarget());
		if(getCameraTarget() != null && getCameraTarget().isValidTarget()){
			camera.updatePositionAndRotation();
		}
		//else
			//mc.thePlayer.closeScreen();
	}

}
