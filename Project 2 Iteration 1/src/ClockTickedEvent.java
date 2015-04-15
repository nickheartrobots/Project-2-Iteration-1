import javax.swing.JOptionPane;


public class ClockTickedEvent extends RefrigeratorEvent {
	public ClockTickedEvent(Object source) {
		super(source);
	}

	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((ClockTickedListener) listener).processEvent(this);
		}catch(ClassCastException cce){
			cce.printStackTrace();
		}
		
	}

}
