
public class FridgeDoorCloseButton extends GUIButton{

	public FridgeDoorCloseButton(String string) {
		super(string);
	}

	@Override
	public void inform(RefrigeratorContext context, RefrigeratorDisplay source) {
		context.handleEvent(new FridgeDoorCloseEvent(source));
		
	}

}
