//
///**
// * @author Nick Clarity Alicia Struble, Maya Gaforova
// * Project 2 Iteration 1
// * Apr 1, 2015
// * 
// * This is the Main Class for all of the Refrigerator Logic.  Implements
// * Facade pattern and Singleton patter.
// */
//public class Refrigerator {
//	private static Refrigerator refrigerator;
//	private static Fridge fridge;
//	private static Freezer freezer;
//	
//	private static final boolean DOOR_OPENED = true;
//	private static final boolean DOOR_CLOSED = false;
//
//	private float roomTemp;
//	
//	// config file variables
//	private int fridgeLow; 
//	private int fridgeHigh; 
//	private int freezerLow;
//	private int freezerHigh; 
//	private int roomLow;
//	private int roomHigh; 
//	private int fridgeUp1DoorClosed;
//	private int fridgeUp1DoorOpen;  
//	private int freezerUp1DoorClosed; 
//	private int freezerUp1DoorOpen;
//	private int tempDiffToStartCoolFridge;
//	private int tempDifftoStartCoolFreezer;
//	private int minutesToCoolFridge1;
//	private int minutesToCoolFreezer1;
//
//	// reference back to the gui so this class can set the status labels
//	private GUI gui;
//
//	/*
//	 * Private Constructor for Singleton pattern.
//	 */
//	private Refrigerator(){
//		fridge = Fridge.instance();
//		freezer = Freezer.instance();
//	}
//
//	/**
//	 * Returns the only instance of Refrigerator to adhere to the Singleton pattern.
//	 * @return Refrigerator
//	 */
//	public static Refrigerator instance(){
//		if (refrigerator == null){
//			refrigerator = new Refrigerator();
//		}
//
//		return refrigerator;
//	}
//
//	/*
//	 * Called by the GUI explicitly because reference can't be passed during
//	 * construction in the singleton pattern.
//	 */
//	public void init(GUI gui){
//		this.gui = gui;
//		
//		//initiate labels to default values
//		gui.turnFridgeLightOff();
//		gui.turnFreezerLightOff();
//	}
//
//	/**
//	 * Called by the GUI when the button is pressed.  Sets default temp values
//	 * to room temp based on 1) from Miscellaneous in the spec.
//	 * @param temp - Room temp
//	 */
//	public void setRoomTemp(float temp){
//		// verifies in range
//		if (temp <= roomHigh && temp >= roomLow){
//			fridge.setTemp(temp);
//			freezer.setTemp(temp);
//			
//			gui.setRoomFieldText("");
//			gui.setErrorLbl("");
//			gui.setErrorLblVisible(false);
//		} else {
//			gui.setErrorLblVisible(true);
//			gui.setErrorLbl("Temp outside of range. (" + roomLow + " - " + roomHigh + ")");
//		}
//	}
//
//	/**
//	 * Called by the GUI when the button is pressed.  Sets the fridge's temp and
//	 * adjusts GUI labels.
//	 * @param temp
//	 */
//	public void setFridgeTemp(float temp){
//		// verifies in range
//		if(temp <= fridgeHigh && temp >= fridgeLow){
//			fridge.setTemp(temp);
//			gui.setFridgeFieldText("");
//			gui.setErrorLbl("");
//			gui.setErrorLblVisible(false);
//		}else{
//			gui.setErrorLblVisible(true);
//			gui.setErrorLbl("Temp outside of range. (" + fridgeLow + " - " + fridgeHigh + ")");
//		}
//	}
//
//	/**
//	 * Called by the GUI when the button is pressed.  Sets the freezer's temp and
//	 * adjusts GUI labels.
//	 * @param temp
//	 */
//	public void setFreezerTemp(float temp){
//		// verifies in range
//		if(temp <= freezerHigh && temp >= freezerLow){
//			freezer.setTemp(temp);
//			gui.setFreezerFieldText("");
//			gui.setErrorLbl("");
//			gui.setErrorLblVisible(false);
//		}else{
//			gui.setErrorLblVisible(true);
//			gui.setErrorLbl("Temp outside of range. (" + freezerLow + " - " + freezerHigh + ")");
//		}
//	}
//	
//	/**
//	 * Called by GUI when button is pressed.  Sets the fridge's door.  Updates GUI Labels.
//	 */
//	public void openFridgeDoor(){
//		fridge.setDoor(DOOR_OPENED);
//		gui.turnFridgeLightOn();;
//	}
//
//	/**
//	 * Called by GUI when button is pressed.  Sets the fridge's door.  Updates GUI Labels.
//	 */
//	public void closeFridgeDoor(){
//		fridge.setDoor(DOOR_CLOSED);
//		gui.turnFridgeLightOff();
//	}
//
//	/**
//	 * Called by GUI when button is pressed.  Sets the freezer's door.  Updates GUI Labels.
//	 */
//	public void openFreezerDoor(){
//		freezer.setDoor(DOOR_OPENED);
//		gui.turnFreezerLightOn();
//	}
//
//	/**
//	 * Called by GUI when button is pressed.  Sets the freezer's door.  Updates GUI Labels.
//	 */
//	public void closeFreezerDoor(){
//		freezer.setDoor(DOOR_CLOSED);
//		gui.turnFreezerLightOff();
//	}
//
//	/*
//	 * Called by clockTicked().  Increments the fridge's temp by the right rate.
//	 */
//	private void raiseFridgeTemp(){
//		if(fridge.doorIsOpen()){
//			fridge.setTemp(fridge.getTemp() + ((float) 1/(float) fridgeUp1DoorOpen));
//		} else {
//			fridge.setTemp(fridge.getTemp() + ((float) 1/(float) fridgeUp1DoorClosed));
//		}
//	}
//
//	/*
//	 * Called by clockTicked().  Decrements the fridge's temp by the right rate.
//	 */
//	private void lowerFridgeTemp(){
//		fridge.setTemp(fridge.getTemp() - ((float) 1 / (float) minutesToCoolFridge1));
//		
//	}
//
//	/*
//	 * Called by clockTicked().  Increments the freezer's temp by the right rate.
//	 */
//	private void raiseFreezerTemp(){
//		if(freezer.doorIsOpen()){
//			freezer.setTemp(freezer.getTemp() + ((float) 1/(float) freezerUp1DoorOpen));
//		} else {
//			freezer.setTemp(freezer.getTemp() + ((float) 1/(float) freezerUp1DoorClosed));
//		}		
//	}
//
//	/*
//	 * Called by clockTicked().  Decrements the freezer's temp by the right rate.
//	 */
//	private void lowerFreezerTemp(){
//		freezer.setTemp(freezer.getTemp() - ((float) 1 / (float) minutesToCoolFreezer1));
//	}
//	
//	/**
//	 * The heart beat of the program.
//	 * 
//	 * This method contains all the Logic of the state changes for both
//	 * fridge and freezer.
//	 * 
//	 * Does not implement the State Pattern as Spec.
//	 * 
//	 */
//	public void clockTicked(){
////		System.out.println("----Tick----");
//		
//		// If we are cooling or we are over the upper threshold.
//		if(fridge.coolerIsRunning() || fridge.getTemp() > fridgeHigh){
//			lowerFridgeTemp();
//			
//			if(Math.abs(fridge.getTemp() - fridgeLow) < tempDiffToStartCoolFridge){
//				fridge.setCooler(false);
//			}
//			gui.turnFridgeCoolerOn();
//		
//		// If we are not cooling or we are below the lower threshold.
//		} else if (!fridge.coolerIsRunning() || fridge.getTemp() < fridgeLow){
//			raiseFridgeTemp();
//			
//			if(Math.abs(fridge.getTemp() - fridgeHigh) < tempDiffToStartCoolFridge){
//				fridge.setCooler(true);
//			}
//			gui.turnFridgeCoolerOff();
//		}
//		
//		// Update the GUI labels
//		gui.setFridgeTempLbl(GUI.FRIDGE_TEMP +
//				" <" + String.format("%2.3f", fridge.getTemp()) + ">");
//		
//		// If we are cooling or we are over the upper threshold.
//		if(freezer.coolerIsRunning() || freezer.getTemp() > freezerHigh){
//			lowerFreezerTemp();
//			
//			if(Math.abs(freezer.getTemp() - freezerLow) < tempDifftoStartCoolFreezer){
//				freezer.setCooler(false);
//			}
//			gui.turnFreezerCoolerOn();
//			
//			// If we are not cooling or we are below the lower threshold.
//		} else if (!freezer.coolerIsRunning() || freezer.getTemp() < freezerLow){
//			raiseFreezerTemp();
//			
//			if(Math.abs(freezer.getTemp() - freezerHigh) < tempDifftoStartCoolFreezer){
//				freezer.setCooler(true);
//			}
//			gui.turnFreezerCoolerOff();
//		}
//	
//		// Update the GUI labels
//		gui.setFreezerTempLbl(GUI.FREEZER_TEMP +
//				" <" + String.format("%2.3f", freezer.getTemp()) + ">");
//	}
//
//	/**
//	 * Method called by GUI to initialized default/config variables.
//	 * @param data
//	 */
//	public void setData(int[] data){
//		if(data.length == 14){
//			fridgeLow = data[0]; 
//			fridgeHigh = data[1]; 
//			freezerLow = data[2];
//			freezerHigh = data[3]; 
//			roomLow = data[4];
//			roomHigh = data[5]; 
//			fridgeUp1DoorClosed = data[6];
//			fridgeUp1DoorOpen = data[7]; 
//			freezerUp1DoorClosed = data[8]; 
//			freezerUp1DoorOpen = data[9];
//			tempDiffToStartCoolFridge = data[10];
//			tempDifftoStartCoolFreezer = data[11];
//			minutesToCoolFridge1 = data[12];
//			minutesToCoolFreezer1 = data[13];
//		}
//	}
//}
