
public class Fridge extends RefrigeratorComponent {
	private static Fridge fridge;
	
	private Fridge(){
		
		
	}
	
	public static Fridge instance(){
		if (fridge == null){
			fridge = new Fridge();
		}
		
		return fridge;
	}
	
	
	

}
