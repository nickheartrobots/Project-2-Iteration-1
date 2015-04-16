
public class FridgeDoorClosedCoolerOn extends RefrigeratorState implements 
	FridgeTempUnderThresholdListener, FridgeDoorOpenListener, ClockTickedListener, FridgeDoorCloseListener {

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
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp() + "");
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

	@Override
	public void processEvent(ClockTickedEvent event) {
		
		context.setFridgeTemp(context.getFridgeTemp() - ((float) 1 / (float) minutesToCoolFridge1));
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp() + "");
		
		if((Math.abs(context.getFridgeTemp() - fridgeLow) < tempDiffToStartCoolFridge)){
			context.handleEvent(new FridgeTempUnderThresholdEvent(display));
		}	
	}

	@Override
	public void processEvent(FridgeDoorCloseEvent event) {
		//do nothing
	}

}