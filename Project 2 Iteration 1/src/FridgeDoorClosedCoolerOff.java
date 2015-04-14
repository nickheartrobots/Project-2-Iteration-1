
public class FridgeDoorClosedCoolerOff extends RefrigeratorState 
	implements FridgeDoorOpenListener, FridgeTempOverThresholdListener{

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
	 * handle temp goes over threshold
	 * 
	 */

	@Override
	public void processEvent(FridgeTempOverThresholdEvent event) {
		context.changeCurrentState(FridgeDoorClosedCoolerOn.instance());
		
	}





}
