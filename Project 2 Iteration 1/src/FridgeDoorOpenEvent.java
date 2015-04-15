import javax.swing.JOptionPane;



public class FridgeDoorOpenEvent extends RefrigeratorEvent{

	public FridgeDoorOpenEvent(Object source) {
		super(source);
	}

	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((FridgeDoorOpenListener) listener).processEvent(this);
		}catch(ClassCastException cce){
			cce.printStackTrace();
		}
		
	}

}


