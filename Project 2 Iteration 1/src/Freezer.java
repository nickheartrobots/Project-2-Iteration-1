
/**
 * @author Nick Clarity Alicia Struble, Maya Gaforova
 * Project 2 Iteration 1
 * Apr 1, 2015
 * 
 * This class extends a generic RefrigeratorComponent class.
 * It adheres to the Singleton Pattern.
 */
public class Freezer extends RefrigeratorComponent {
	private static Freezer freezer;
	
	/*
	 * Private constructor for Singleton Pattern.
	 */
	private Freezer(){}
	
	/**
	 * Returns instance of Freezer
	 * @return Freezer
	 */
	public static Freezer instance(){
		if (freezer == null){
			freezer = new Freezer();
		}
		
		return freezer;
	}
	
}
