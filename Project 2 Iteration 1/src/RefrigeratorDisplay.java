
import java.util.Observable;

public abstract class RefrigeratorDisplay extends Observable {


	/**
	 * Specifies what the display system should do. Note that the implementation has
	 * a lot of freedom to choose its display.
	 */
	protected static RefrigeratorContext context;
	protected static RefrigeratorDisplay instance;

	/**
	 * Initializes the context and instance	
	 */
	protected RefrigeratorDisplay() {
		instance = this;
		context = RefrigeratorContext.instance();
	}

	/**
	 * For singleton
	 * 
	 * @return the object
	 */
	public static RefrigeratorDisplay instance() {
		return instance;
	}

	/**
	 * Do the initializations to make the context an observer
	 */
	public void initialize() {
		context.initialize();
	}

	/**
	 * Indicate that the Fridge light is on
	 */
	public abstract void turnFridgeLightOn();

	/**
	 * Indicate that the Fridge light is off
	 */
	public abstract void turnFridgeLightOff();

	/**
	 * Indicate that the Fridge cooler is on
	 */
	public abstract void turnFridgeCoolerOn();

	/**
	 * Indicate that the Fridge cooler is off
	 */
	public abstract void turnFridgeCoolerOff();
	
	/**
	 * Indicate that the Freezer light is on
	 */
	public abstract void turnFreezerLightOn();

	/**
	 * Indicate that the Freezer light is off
	 */
	public abstract void turnFreezerLightOff();

	/**
	 * Indicate that the Freezer cooler is on
	 */
	public abstract void turnFreezerCoolerOn();

	/**
	 * Indicate that the Freezer cooler is off
	 */
	public abstract void turnFreezerCoolerOff();


}

