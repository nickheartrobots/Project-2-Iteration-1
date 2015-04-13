
public class FridgeDoorOpenCoolerOn extends RefrigeratorState implements FridgeDoorCloseListener, FridgeTempUnderThresholdListener{
	private static FridgeDoorOpenCoolerOn instance;

	/**
	 * Private to make it a singleton
	 */
	private FridgeDoorOpenCoolerOn(){
	}
	
	public static FridgeDoorOpenCoolerOn instance() {
		if (instance == null) {
			instance = new FridgeDoorOpenCoolerOn();
		}
		return instance;
	}
	
	@Override
	public void processEvent(FridgeTempUnderThresholdEvent event) {
		context.changeCurrentState(FridgeDoorOpenCoolerOff.instance());
		
	}

	@Override
	public void processEvent(FridgeDoorCloseEvent event) {
		context.changeCurrentState(FridgeDoorClosedCoolerOn.instance());
		
	}

	@Override
	public void run() {
		display.turnFridgeCoolerOn();
		display.turnFridgeLightOn();
		
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub
		
	}


}
