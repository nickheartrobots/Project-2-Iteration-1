import javax.swing.JButton;

/**
 * The abstract GUI JButton object. Helps to get rid of conditionals
 *
 */
public abstract class GUIButton extends JButton {
	/**
	 * Create the button with the proper text
	 * 
	 * @param string
	 *            the text
	 */
	public GUIButton(String string) {
		super(string);
	}

	/**
	 * Tell the listener that the button has been clicked.
	 * 
	 * @param context
	 *            the Microwave context
	 * @param display
	 *            the GUI
	 */
	public abstract void inform(RefrigeratorContext context, RefrigeratorDisplay display);
}