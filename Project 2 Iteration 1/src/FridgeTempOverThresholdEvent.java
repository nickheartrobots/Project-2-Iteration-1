import javax.swing.JOptionPane;


public class FridgeTempOverThresholdEvent extends RefrigeratorEvent{

	public FridgeTempOverThresholdEvent(Object source) {
		super(source);
	}

		@Override
		public void connectToListener(RefrigeratorEventListener listener) {
			try{
				((FridgeTempOverThresholdListener) listener).processEvent(this);
			}catch(ClassCastException cce){
				cce.printStackTrace();
			}
		
	}

}
