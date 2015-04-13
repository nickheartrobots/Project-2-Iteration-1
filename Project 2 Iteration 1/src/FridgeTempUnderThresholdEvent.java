import javax.swing.JOptionPane;


public class FridgeTempUnderThresholdEvent extends RefrigeratorEvent{

	public FridgeTempUnderThresholdEvent(Object source) {
		super(source);

	}

	@Override
	public void connectToListener(RefrigeratorEventListener listener) {
		try{
			((FridgeTempUnderThresholdListener) listener).FridgeTempUnderThreshold(this);
		}catch(ClassCastException cce){
			JOptionPane.showMessageDialog(null, "Can't cast event to class FridgeTempUnderThresholdListener.", 
					"Class Cast Exception", JOptionPane.ERROR_MESSAGE);
			cce.printStackTrace();
		}
	}

}
