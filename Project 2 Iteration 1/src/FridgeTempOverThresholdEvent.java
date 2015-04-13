import javax.swing.JOptionPane;


public class FridgeTempOverThresholdEvent extends RefrigeratorEvent{

	public FridgeTempOverThresholdEvent(Object source) {
		super(source);
	}

		@Override
		public void connectToListener(RefrigeratorEventListener listener) {
			try{
				((FridgeTempOverThresholdListener) listener).fridgeTempOverThreshold(this);
			}catch(ClassCastException cce){
				JOptionPane.showMessageDialog(null, "Can't cast event to class FridgeTempOverThresholdListener.", 
						"Class Cast Exception", JOptionPane.ERROR_MESSAGE);
				cce.printStackTrace();
			}
		
	}

}
