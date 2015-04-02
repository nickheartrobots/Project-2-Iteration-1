
public abstract class RefrigeratorComponent {
	private float temp;
	private boolean door;
	private boolean light;
	private boolean cooler;
	
	public float getTemp() {
		return temp;
	}
	public void setTemp(float temp) {
		this.temp = temp;
	}

	public boolean doorIsOpen() {
		return door == true;
	}
	
	public void setDoor(boolean door) {
		this.door = door;
	}
	
	public boolean lightIsOn(){
		return light == true;
	}
	
	public void setLight(boolean light){
		this.light = light;
	}
	
	public boolean coolerIsRunning(){
		return cooler;
	}
	
	public void setCooler(boolean cooler){
		this.cooler = cooler;
	}
}
