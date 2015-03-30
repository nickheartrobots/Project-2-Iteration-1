
public class Refrigerator {
	private static Refrigerator refrigerator;
	private static Fridge fridge;
	private static Freezer freezer;
	private static final boolean DOOR_OPENED = true;
	private static final boolean DOOR_CLOSED = false;

	// reference back to the gui so this class can set the status labels
	private GUI gui;
	
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
	public void setGUI(GUI gui){
		this.gui = gui;
	}
	
	public void setRoomTemp(float temp){
		
	}
	
	public void setFridgeTemp(float temp){
		fridge.setTemp(temp);
	}
	
	public void setFreezerTemp(float temp){
		freezer.setTemp(temp);
	}
	
	public void openFridgeDoor(){
		fridge.setDoor(DOOR_OPENED);
	}
	
	public void closeFridgeDoor(){
		fridge.setDoor(DOOR_CLOSED);
	}
	
	public void openFreezerDoor(){
		freezer.setDoor(DOOR_OPENED);
		
	}
	
	public void closeFreezerDoor(){
		freezer.setDoor(DOOR_CLOSED);
	}
	
	public void clockTicked(){
		
	}
}
