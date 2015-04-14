import java.util.Observable;
import java.util.Observer;


public class RefrigeratorContext implements Observer{
	private static RefrigeratorDisplay refrigeratorDisplay;
	private RefrigeratorState currentState;
	private static RefrigeratorContext instance;

	
	private static final boolean DOOR_OPENED = true;
	private static final boolean DOOR_CLOSED = false;

	private float roomTemp;
	
	// config file variables
	private int fridgeLow; 
	private int fridgeHigh; 
	private int freezerLow;
	private int freezerHigh; 
	private int roomLow;
	private int roomHigh; 
	private int fridgeUp1DoorClosed;
	private int fridgeUp1DoorOpen;  
	private int freezerUp1DoorClosed; 
	private int freezerUp1DoorOpen;
	private int tempDiffToStartCoolFridge;
	private int tempDifftoStartCoolFreezer;
	private int minutesToCoolFridge1;
	private int minutesToCoolFreezer1;

	/**
	 * Make it a singleton
	 */
	private RefrigeratorContext() {
		instance = this;
		refrigeratorDisplay = GUI.instance();
		currentState = FridgeDoorClosedCoolerOff.instance();

	}

	/**
	 * Return the instance
	 * 
	 * @return the object
	 */
	public static RefrigeratorContext instance() {
		if (instance == null) {
			instance = new RefrigeratorContext();
		}
		return instance;
	}

	/**
	 * lets door closed state be the starting state adds the object as an
	 * observable for clock
	 */
	public void initialize() {
		instance.changeCurrentState(FridgeDoorClosedCoolerOff.instance());
	}

	/**
	 * Called from the states to change the current state
	 * 
	 * @param nextState
	 *            the next state
	 */
	public void changeCurrentState(RefrigeratorState nextState) {
		currentState.leave();
		currentState = nextState;
		nextState.run();
	}

	/**
	 * Gets the display
	 * 
	 * @return the display
	 */
	public RefrigeratorDisplay getDisplay() {
		return refrigeratorDisplay;
	}
	
	public void handleEvent(RefrigeratorEvent event){
		try{
			event.connectToListener((RefrigeratorEventListener) currentState);
		}catch(ClassCastException cce){
			cce.printStackTrace();
		}	
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
		
	}
	
	/**
	 * Called by the GUI when the button is pressed.  Sets default temp values
	 * to room temp based on 1) from Miscellaneous in the spec.
	 * @param temp - Room temp
	 */
	public void setRoomTemp(float temp){
		// verifies in range
		if (temp <= roomHigh && temp >= roomLow){
//			fridge.setTemp(temp);
//			freezer.setTemp(temp);
			
			((GUI) refrigeratorDisplay).setRoomFieldText("");
			((GUI) refrigeratorDisplay).setErrorLbl("");
			((GUI) refrigeratorDisplay).setErrorLblVisible(false);
		} else {
			((GUI) refrigeratorDisplay).setErrorLblVisible(true);
			((GUI) refrigeratorDisplay).setErrorLbl("Temp outside of range. (" + roomLow + " - " + roomHigh + ")");
		}
	}
	
	/**
	 * Method called by GUI to initialized default/config variables.
	 * @param data
	 */
	public void setData(int[] data){
		if(data.length == 14){
			fridgeLow = data[0]; 
			fridgeHigh = data[1]; 
			freezerLow = data[2];
			freezerHigh = data[3]; 
			roomLow = data[4];
			roomHigh = data[5]; 
			fridgeUp1DoorClosed = data[6];
			fridgeUp1DoorOpen = data[7]; 
			freezerUp1DoorClosed = data[8]; 
			freezerUp1DoorOpen = data[9];
			tempDiffToStartCoolFridge = data[10];
			tempDifftoStartCoolFreezer = data[11];
			minutesToCoolFridge1 = data[12];
			minutesToCoolFreezer1 = data[13];
		}
	}

}
