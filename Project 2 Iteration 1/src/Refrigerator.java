
public class Refrigerator {
	private static Refrigerator refrigerator;
	private static Fridge fridge;
	private static Freezer freezer;
	
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
	
	

}
