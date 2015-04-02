/**
 * @author Nick Clarity Alicia Struble, Maya Gaforova
 * Project 2 Iteration 1
 * Apr 1, 2015
 * 
 * Generic RefrigeratorComponent class.  Not intended for direct implementation.
 */
public abstract class RefrigeratorComponent {
	private float temp;
	private boolean door;
	private boolean light;
	private boolean cooler;
	
	/**
	 * Getter for temp
	 * @return temp
	 */
	public float getTemp() {
		return temp;
	}
	/**
	 * Setter for temp
	 * @param temp
	 */
	public void setTemp(float temp) {
		this.temp = temp;
	}

	/**
	 * Getter for door
	 * @return door == true
	 */
	public boolean doorIsOpen() {
		return door == true;
	}
	
	/**
	 * Setter for door
	 * @param door
	 */
	public void setDoor(boolean door) {
		this.door = door;
	}
	
	/**
	 * Getter for light
	 * @return light == true
	 */
	public boolean lightIsOn(){
		return light == true;
	}
	
	/**
	 * Setter for light
	 * @param light
	 */
	public void setLight(boolean light){
		this.light = light;
	}
	
	/**
	 * Getter for cooler
	 * @return cooler == true;
	 */
	public boolean coolerIsRunning(){
		return cooler == true;
	}
	
	/**
	 * Setter for cooler
	 * @param cooler
	 */
	public void setCooler(boolean cooler){
		this.cooler = cooler;
	}
}
