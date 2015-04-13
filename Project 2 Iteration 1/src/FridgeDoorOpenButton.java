
public class FridgeDoorOpenButton extends GUIButton{
	
	public FridgeDoorOpenButton(String string){
		super(string);
	}
	
	@Override
	public void inform(RefrigeratorContext context, RefrigeratorDisplay source){
		context.handleEvent(new FridgeDoorOpenEvent(source));
	}


}
