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
			JOptionPane.showMessageDialog(null, "Can't cast event to class FridgeDoorCloseListener.", 
					"Class Cast Exception", JOptionPane.ERROR_MESSAGE);
			cce.printStackTrace();
		}
		
	}

}
