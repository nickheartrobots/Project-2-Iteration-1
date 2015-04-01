
public class Refrigerator {
	private static Refrigerator refrigerator;
	private static Fridge fridge;
	private static Freezer freezer;
	private static final boolean DOOR_OPENED = true;
	private static final boolean DOOR_CLOSED = false;
	int fridgeLow; 
	int fridgeHigh; 
	int freezerLow;
	int freezerHigh; 
	int roomLow;
	int roomHigh; 
	int fridgeUp1DoorClosed;
	int fridgeUp1DoorOpen;  
	int freezerUp1DoorClosed; 
	int freezerUp1DoorOpen;
	int tempDiffToStartCoolFridge;
	int tempDifftoStartCoolFreezer;
	int minutesToCoolFridge1;
	int minutesToCoolFreezer1;


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

	public void setRoomTemp(float temp){

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

	public void openFridgeDoor(){
		fridge.setDoor(DOOR_OPENED);
		gui.setFridgeLightLbl(gui.FRIDGE_LIGHT_ON);
	}

	public void closeFridgeDoor(){
		fridge.setDoor(DOOR_CLOSED);
		gui.setFridgeLightLbl(gui.FRIDGE_LIGHT_OFF);
	}

	public void openFreezerDoor(){
		freezer.setDoor(DOOR_OPENED);
		gui.setFreezerLightLbl(gui.FREEZER_LIGHT_ON);

	}

	public void closeFreezerDoor(){
		freezer.setDoor(DOOR_CLOSED);
		gui.setFreezerLightLbl(gui.FREEZER_LIGHT_OFF);
	}

	public void turnFridgeCoolOn(){
		gui.setFridgeCoolingLbl(gui.FRIDGE_COOLING_ON);
	}

	public void turnFridgeCoolOff(){
		gui.setFridgeCoolingLbl(gui.FRIDGE_COOLING_OFF);
	}

	public void turnFreezerCoolOn(){
		gui.setFridgeCoolingLbl(gui.FREEZER_COOLING_ON);
	}

	public void turnFreezerCoolOff(){
		gui.setFridgeCoolingLbl(gui.FREEZER_COOLING_OFF);
	}

	public void clockTicked(){

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
		}

	}
}
