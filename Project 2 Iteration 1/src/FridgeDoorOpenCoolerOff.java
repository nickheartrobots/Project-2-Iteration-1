
public class FridgeDoorOpenCoolerOff extends RefrigeratorState implements 
	FridgeDoorCloseListener, FridgeTempOverThresholdListener, ClockTickedListener{

	private static FridgeDoorOpenCoolerOff instance;
	
	/**
	 * Private to make it a singleton
	 */
	private FridgeDoorOpenCoolerOff(){
	}
	
	public static FridgeDoorOpenCoolerOff instance() {
		if (instance == null) {
			instance = new FridgeDoorOpenCoolerOff();
		}
		return instance;
	}
	
	@Override
	public void processEvent(FridgeTempOverThresholdEvent event) {
		context.changeCurrentState(FridgeDoorOpenCoolerOn.instance());
		
	}

	@Override
	public void processEvent(FridgeDoorCloseEvent event) {
		context.changeCurrentState(FridgeDoorClosedCoolerOff.instance());
		
	}

	@Override
	public void run() {
		display.turnFridgeLightOn();
		display.turnFridgeCoolerOff();
		
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processEvent(ClockTickedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
