import java.io.ObjectInputStream.GetField;


public class Refrigerator {
	private static Refrigerator refrigerator;
	private static Fridge fridge;
	private static Freezer freezer;
	
	private static final boolean DOOR_OPENED = true;
	private static final boolean DOOR_CLOSED = false;

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

	// reference back to the gui so this class can set the status labels
	private Project2Iteration1 gui;

	private Refrigerator(){
		fridge = Fridge.instance();
		freezer = Freezer.instance();

	}

	public static Refrigerator instance(){
		if (refrigerator == null){
			refrigerator = new Refrigerator();
		}

		return refrigerator;
	}

	// called explicitly by the gui because a reference can't be passed during
	// construction.
	public void setGUI(Project2Iteration1 gui){
		this.gui = gui;
	}


	public void setFridgeTemp(float temp){
		if(temp <= fridgeHigh && temp >= fridgeLow){
			fridge.setTemp(temp);
			gui.setFridgeTempLbl("Fridge Temp " + temp);
		}else{
			gui.fridgeFieldSetText("Temp outside range.");
		}
	}

	public void setFreezerTemp(float temp){
		if(temp <= freezerHigh && temp >= freezerLow){
			freezer.setTemp(temp);
			gui.setFreezerTempLbl("Freezer Temp " + temp);
		}else{
			gui.freezerFieldSetText("Temp outside range.");
		}
	}

	public void setRoomTemp(float temp){

	}

	public void openFridgeDoor(){
		fridge.setDoor(DOOR_OPENED);
		gui.setFridgeLightLbl(Project2Iteration1.FRIDGE_LIGHT_ON);
	}

	public void closeFridgeDoor(){
		fridge.setDoor(DOOR_CLOSED);
		gui.setFridgeLightLbl(Project2Iteration1.FRIDGE_LIGHT_OFF);
	}

	public void openFreezerDoor(){
		freezer.setDoor(DOOR_OPENED);
		gui.setFreezerLightLbl(Project2Iteration1.FREEZER_LIGHT_ON);
	}

	public void closeFreezerDoor(){
		freezer.setDoor(DOOR_CLOSED);
		gui.setFreezerLightLbl(Project2Iteration1.FREEZER_LIGHT_OFF);
	}

	private void raiseFridgeTemp(){
		if(fridge.doorIsOpen()){
			fridge.setTemp(fridge.getTemp() + ((float) 1/(float) (fridgeUp1DoorOpen * 60)));
		} else {
			fridge.setTemp(fridge.getTemp() + ((float) 1/(float) (fridgeUp1DoorClosed * 60)));
		}
	}

	private void lowerFridgeTemp(){
		fridge.setTemp(fridge.getTemp() + ((float) 1 / (float) (minutesToCoolFridge1 * 60)));
		
	}

	private void raiseFreezerTemp(){
		if(freezer.doorIsOpen()){
			freezer.setTemp(freezer.getTemp() + ((float) 1/(float) (freezerUp1DoorOpen * 60)));
		} else {
			freezer.setTemp(freezer.getTemp() + ((float) 1/(float) (freezerUp1DoorClosed * 60)));
		}		
	}

	private void lowerFreezerTemp(){
		freezer.setTemp(freezer.getTemp() + ((float) 1 / (float) (minutesToCoolFreezer1 * 60)));
	}
	
	
	
	
	
	
	
	
	
	
	
	public void clockTicked(){
		System.out.println("Tick");
		if(fridge.coolerIsRunning()){
			lowerFridgeTemp();
			
			if(Math.abs(fridge.getTemp() - fridgeLow) < tempDiffToStartCoolFridge){
				fridge.setCooler(false);
			}
			gui.setFridgeCoolingLbl(Project2Iteration1.FRIDGE_COOLING_OFF);
			
		} else {
			raiseFridgeTemp();
			
			if(Math.abs(fridge.getTemp() - fridgeHigh) < tempDiffToStartCoolFridge){
				fridge.setCooler(true);
			}
			gui.setFridgeCoolingLbl(Project2Iteration1.FRIDGE_COOLING_ON);
		}

		gui.setFridgeTempLbl(Project2Iteration1.FRIDGE_TEMP +
				" <" + String.format("%2.3f", fridge.getTemp()) + ">");
		
		
		
		
		
		if(freezer.coolerIsRunning()){
			lowerFreezerTemp();
			
			if(Math.abs(freezer.getTemp() - freezerLow) < tempDifftoStartCoolFreezer){
				freezer.setCooler(false);
			}
			gui.setFreezerCoolingLbl(Project2Iteration1.FREEZER_COOLING_OFF);
			
		} else {
			raiseFreezerTemp();
			
			if(Math.abs(freezer.getTemp() - freezerHigh) < tempDifftoStartCoolFreezer){
				freezer.setCooler(true);
			}
			gui.setFreezerCoolingLbl(Project2Iteration1.FREEZER_COOLING_ON);
		}
	
		gui.setFreezerTempLbl(Project2Iteration1.FREEZER_TEMP +
				" <" + String.format("%2.3f", freezer.getTemp()) + ">");
	}

	
	
	
	
	
	
	
	
	
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
			
			fridge.setTemp((fridgeHigh + fridgeLow)/2);
			freezer.setTemp((freezerHigh + freezerLow)/2);
		}
	}
}
