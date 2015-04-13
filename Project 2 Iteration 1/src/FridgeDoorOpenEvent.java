import javax.swing.JOptionPane;



public class FridgeDoorOpenEvent extends RefrigeratorEvent{

	public FridgeDoorOpenEvent(Object source) {
		super(source);
	}

	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((FridgeDoorOpenListener) listener).fridgeDoorOpened(this);
		}catch(ClassCastException cce){
			JOptionPane.showMessageDialog(null, "Can't cast event to class FridgeDoorOpenListener.", 
					"Class Cast Exception", JOptionPane.ERROR_MESSAGE);
			cce.printStackTrace();
		}
		
	}

}


