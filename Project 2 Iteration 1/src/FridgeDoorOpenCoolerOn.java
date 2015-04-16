
public class FridgeDoorOpenCoolerOn extends RefrigeratorState implements 
	FridgeDoorCloseListener, FridgeTempUnderThresholdListener, ClockTickedListener{
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
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp() + "");
		
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processEvent(ClockTickedEvent event) {
		context.setFridgeTemp(context.getFridgeTemp() - ((float) 1 / (float) minutesToCoolFridge1));
		((GUI)display).setFridgeTempLbl(context.getFridgeTemp() + "");
		
		if((Math.abs(context.getFridgeTemp() - fridgeLow) < tempDiffToStartCoolFridge)){
			context.handleEvent(new FridgeTempUnderThresholdEvent(display));
		}	
		
	}


}
