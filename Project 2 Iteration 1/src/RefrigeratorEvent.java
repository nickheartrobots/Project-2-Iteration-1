import java.util.EventObject;


public abstract class RefrigeratorEvent extends EventObject {

	public RefrigeratorEvent(Object source) {
		super(source);
	}

	public abstract void connectToListener(RefrigeratorEventListener listener);
}
