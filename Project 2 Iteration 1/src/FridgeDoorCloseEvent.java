import javax.swing.JOptionPane;


public class FridgeDoorCloseEvent extends RefrigeratorEvent{

	public FridgeDoorCloseEvent(Object source) {
		super(source);
	}

	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((FridgeDoorCloseListener) listener).processEvent(this);
		}catch(ClassCastException cce){
			cce.printStackTrace();
		}
		
	}

}
