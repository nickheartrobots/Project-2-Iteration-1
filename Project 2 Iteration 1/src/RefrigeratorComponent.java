
public abstract class RefrigeratorComponent {
	private float temp;
	private boolean door;
	public float getTemp() {
		return temp;
	}
	public void setTemp(float temp) {
		this.temp = temp;
	}
	public boolean doorIsOpen() {
		return door == true;
	}
	
	public boolean doorIsClosed(){
		return door == false;
	}
	public void setDoor(boolean door) {
		this.door = door;
	}
	
	
	
	
	
}
