
public class FridgeDoorClosedCoolerOff extends RefrigeratorState 
	implements FridgeDoorOpenListener, FridgeTempOverThresholdListener, ClockTickedListener{

	private static FridgeDoorClosedCoolerOff instance;
	
	/**
	 * Private to make it a singleton
	 */
	private FridgeDoorClosedCoolerOff(){
	}
	
	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static FridgeDoorClosedCoolerOff instance() {
		if (instance == null) {
			instance = new FridgeDoorClosedCoolerOff();
		}
		return instance;
	}
	
	@Override
	public void run() {
		display.turnFridgeLightOff();
		display.turnFridgeCoolerOff();
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp() + "");
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * handle door open event
	 * 
	 */

	@Override
	public void processEvent(FridgeDoorOpenEvent event) {

		context.changeCurrentState(FridgeDoorOpenCoolerOff.instance());
	}

	/**
	 * handle temp goes over threshold event
	 * 
	 */

	@Override
	public void processEvent(FridgeTempOverThresholdEvent event) {
		context.changeCurrentState(FridgeDoorClosedCoolerOn.instance());
		
	}
	
	/**
	 * handle clockTicked even
	 */

	@Override
	public void processEvent(ClockTickedEvent event) {
		context.setFridgeTemp(context.getFridgeTemp() + ((float) 1/(float) fridgeUp1DoorClosed));
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp() + "");
	
		if(Math.abs(context.getFridgeTemp() - fridgeHigh) < tempDiffToStartCoolFridge){
			context.handleEvent(new FridgeTempOverThresholdEvent(display));
		}	
	}
}
