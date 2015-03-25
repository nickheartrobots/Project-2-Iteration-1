
public class Freezer extends RefrigeratorComponent {
	private static Freezer freezer;
	
	private Freezer(){
		
		
	}
	
	public static Freezer instance(){
		if (freezer == null){
			freezer = new Freezer();
		}
		
		return freezer;
	}
	
	
	public void clockTicked(){
		
	}
}
