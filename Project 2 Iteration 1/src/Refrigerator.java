
public class Refrigerator {
	private static Refrigerator refrigerator;
	private static Fridge fridge;
	private static Freezer freezer;

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
	
	// called explicitly by the gui because a reference cant be passed durring
	// construction.
	public void setGUI(GUI gui){
		this.gui = gui;
	}
	
	public void setRoomTemp(float temp){
		
	}
	
	public void setFridgeTemp(float temp){
		
	}
	
	public void setFreezerTemp(float temp){
	
	}
	
	public void openFridgeDoor(){
		
	}
	
	public void closeFridgeDoor(){
		
	}
	
	public void openFreezerDoor(){
		
	}
	
	public void closeFreezerDoor(){
		
	}
}
