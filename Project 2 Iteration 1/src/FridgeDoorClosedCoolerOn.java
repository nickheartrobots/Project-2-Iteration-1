
public class FridgeDoorClosedCoolerOn extends RefrigeratorState implements FridgeTempUnderThresholdListener, FridgeDoorOpenListener {

	private static FridgeDoorClosedCoolerOn instance;

	/**
	 * Private to make it a singleton
	 */
	private FridgeDoorClosedCoolerOn(){
	}

	/**
	 * returns the instance
	 * 
	 * @return this object
	 */
	public static FridgeDoorClosedCoolerOn instance() {
		if (instance == null) {
			instance = new FridgeDoorClosedCoolerOn();
		}
		return instance;
	}



	@Override
	public void run() {
		display.turnFridgeLightOff();
		display.turnFridgeCoolerOn();
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub

	}

	@Override
	public void processEvent(FridgeDoorOpenEvent event) {

		context.changeCurrentState(FridgeDoorOpenCoolerOn.instance());
	}

	@Override
	public void processEvent(FridgeTempUnderThresholdEvent event) {
		context.changeCurrentState(FridgeDoorClosedCoolerOff.instance());
		
	}

}



