
/**
 * @author Nick Clarity Alicia Struble, Maya Gaforova
 * Project 2 Iteration 1
 * Apr 1, 2015
 * 
 * This class extends a generic RefrigeratorComponent class.
 * It adheres to the Singleton Pattern.
 */
public class Fridge extends RefrigeratorComponent {
	private static Fridge fridge;
	
	/*
	 * Private constructor for Singleton Pattern.
	 */
	private Fridge(){}
	
	/**
	 * Returns instance of Fridge
	 * @return Fridge
	 */
	public static Fridge instance(){
		if (fridge == null){
			fridge = new Fridge();
		}
		
		return fridge;
	}
}
