import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Nick Clarity Alicia Struble, Maya Gaforova
 * Project 2 Iteration 1
 * Apr 15, 2015
 * 
 * GUI for Project 2 Iteration 2.  Class is titled "Project2Iteration1" because
 * it contains the main method and it will be run from the command line with 
 * that title.
 */
public class GUI extends RefrigeratorDisplay implements ActionListener {
	//private Refrigerator refrigerator;
	private RefrigeratorFrame frame;
	private RefrigeratorContext context;

	private int[] data = new int[14];

	public static final String FRIDGE_LIGHT_ON = "Fridge light <on>";
	public static final String FRIDGE_LIGHT_OFF = "Fridge light <off>";
	public static final String FREEZER_LIGHT_ON = "Freezer light <on>";
	public static final String FREEZER_LIGHT_OFF = "Freezer light <off>";
	public static final String FRIDGE_COOLING_ON = "Fridge <cooling>";
	public static final String FRIDGE_COOLING_OFF = "Fridge <idle>";
	public static final String FREEZER_COOLING_ON = "Freezer <cooling>";
	public static final String FREEZER_COOLING_OFF = "Freezer <idle>";
	public static final String FRIDGE_TEMP = "Fridge temp";
	public static final String FREEZER_TEMP = "Freezer temp";

	//Input variables
	private JFileChooser fileOpen;
	private File txtFile;
	private String location;
	private List<String> content = new ArrayList<String>();

	private Point defaultLocation;
	private final int frameWidth = 525;
	private final int frameHeight = 375;

	private JButton openFridgeDoor;
	private JButton closeFridgeDoor;
	private JButton openFreezerDoor;
	private JButton closeFreezerDoor;

	private JButton setRoomTemp;
	private JButton setFridgeTemp;
	private JButton setFreezerTemp;

	private JTextField roomField;
	private JTextField fridgeField;
	private JTextField freezerField;

	private JLabel fridgeCoolingLbl;
	private JLabel fridgeTempLbl;
	private JLabel fridgeLightLbl;
	private JLabel freezerLightLbl;
	private JLabel freezerTempLbl;
	private JLabel freezerCoolingLbl;
	private JLabel errorLbl;

	//private Listen listen;

	/**
	 * Constructor with a File param.
	 * @param file is the config File passed in as a commandline arg.
	 */
	public GUI(File file){
		fileScan(file);
		//listen = new Listen();
		frame = new RefrigeratorFrame();
		context = RefrigeratorContext.instance();
		frame.add(new Panel());

		frame.centerGUI();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLocation(this.defaultLocation);
		frame.setTitle("Refrigerator Dashboard");
		frame.setPreferredSize(new Dimension(frameWidth, frameHeight));
		frame.pack();
		frame.setVisible(true);


		//refrigerator = Refrigerator.instance();

		//setData() and init() because can't pass args to contructor of a singleton
		//refrigerator.init(this);

		context.setData(data);
		//start the clock.
	}



	/**
	 * @author Nick Clarity
	 * Project 2 Iteration 1
	 * Apr 1, 2015
	 * 
	 * Action Listener class for all the Button presses.
	 */
	//	private class Listen implements ActionListener{
	//		@Override
	//		public void actionPerformed(ActionEvent e) {
	//			if(e.getSource() == setRoomTemp){
	//				
	//				// parse temps to send to Refrigerator
	//				
	//
	//				// parse temps to send to Refrigerator
	//			} else if (e.getSource() == setFridgeTemp){
	//				try{
	//					String t1 = fridgeField.getText();
	//					float t2 = Float.parseFloat(t1);
	//					
	//					setErrorLbl("");
	//					setErrorLblVisible(false);
	//
	//					//refrigerator.setFridgeTemp(t2);
	//				}catch(NumberFormatException nfe){
	//					fridgeField.setText("");
	//					
	//					setErrorLblVisible(true);
	//					setErrorLbl("Fridge Temp must be a number.");
	//				}
	//
	//				// parse temps to send to Refrigerator
	//			} else if (e.getSource() == setFreezerTemp){
	//				try{
	//					String t1 = freezerField.getText();
	//					float t2 = Float.parseFloat(t1);
	//
	//					setErrorLbl("");
	//					setErrorLblVisible(false);
	//					
	//					//refrigerator.setFreezerTemp(t2);
	//				}catch(NumberFormatException nfe){
	//					freezerField.setText("");
	//					
	//					setErrorLblVisible(true);
	//					setErrorLbl("Freezer Temp must be a number.");
	//				}
	//		}
	//	}

	private class RefrigeratorFrame extends JFrame{

		/*
		 * Centers the GUI on he screen instead of it appear at the top left corner.
		 */
		private void centerGUI() {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) screenSize.getWidth();
			int height = (int) screenSize.getHeight();

			width /= 2;
			height /= 2;

			defaultLocation = new Point(width - (frameWidth / 2), height
					- (frameHeight / 2));
		}

	}

	/**
	 * Getter for Fridge Cooling Label text
	 * @return String
	 */
	public String getFridgeCoolingLbl() {
		return fridgeCoolingLbl.getText();
	}

	/**
	 * Sets Fridge Cooling Label to ON
	 * 
	 */
	public void turnFridgeCoolerOn() {
		this.fridgeCoolingLbl.setText(FRIDGE_COOLING_ON);
		frame.repaint();
	}

	/**
	 * Sets Fridge Cooling Label to OFF
	 * 
	 */
	public void turnFridgeCoolerOff() {
		this.fridgeCoolingLbl.setText(FRIDGE_COOLING_OFF);
		frame.repaint();
	}

	/**
	 * Getter for Fridge Temp Label text
	 * @return String
	 */
	public String getFridgeTempLbl() {
		return fridgeTempLbl.getText();
	}

	/**
	 * Sets Fridge light label to OFF
	 * 
	 */
	public void turnFridgeLightOff() {
		this.fridgeTempLbl.setText(FRIDGE_LIGHT_OFF);
		frame.repaint();
	}

	/**
	 * Sets Fridge light label to ON 
	 */
	public void turnFridgeLightOn() {
		this.fridgeTempLbl.setText(FRIDGE_LIGHT_ON);
		frame.repaint();
	}

	/**
	 * Sets label for Fridge temp, so the simulated temperature is visible
	 * @param string
	 */

	public void setFridgeTempLbl(String string) {
		fridgeTempLbl.setText(string);
		frame.repaint();
	}

	/**
	 * Getter for Fridge Light Label text
	 * @return String
	 */
	public String getFridgeLightLbl() {
		return fridgeLightLbl.getText();
	}


	/**
	 * Getter for Freezer Light Label text
	 * @return String
	 */
	public String getFreezerLightLbl() {
		return freezerLightLbl.getText();
	}

	/**
	 * Getter for Freezer Temp Label text
	 * @return String
	 */
	public String getFreezerTempLbl() {
		return freezerTempLbl.getText();
	}

	/**
	 * Setter for Freezer Temp Label
	 * @param freezerTempLbl - String
	 */
	public void setFreezerTempLbl(String freezerTempLbl) {
		this.freezerTempLbl.setText(freezerTempLbl);
		frame.repaint();
	}

	/**
	 * Getter for Freezer Cooling Label text
	 * @return String
	 */
	public String getFreezerCoolingLbl() {
		return freezerCoolingLbl.getText();
	}

	/**
	 * Getter for Error Label text
	 * @return String
	 */
	public String getErrorLbl(){
		return errorLbl.getText();
	}

	/**
	 * Setter for Error Label text
	 * @param string - String
	 */
	public void setErrorLbl(String string){
		this.errorLbl.setText(string);
		frame.repaint();
	}

	/**
	 * Setter for Error Label visibility
	 * @param bool - boolean
	 */
	public void setErrorLblVisible(boolean bool){
		errorLbl.setVisible(bool);
	}

	/**
	 * Getter for Room Field text
	 * @return String
	 */
	public String getRoomFieldText(){
		return roomField.getText();
	}

	/**
	 * Setter for Room Field text
	 * @param string - String
	 */
	public void setRoomFieldText(String string){
		roomField.setText(string);
	}

	/**
	 * Setter for Fridge Field text
	 * @param string - String
	 */
	public void setFridgeFieldText(String string){
		fridgeField.setText(string);
	}

	/**
	 * Setter for Freezer Field text
	 * @param string - String
	 */
	public void setFreezerFieldText(String string){
		freezerField.setText(string);
	}

	/**
	 * Sets Freezer Light Label to ON
	 */

	@Override
	public void turnFreezerLightOn() {
		freezerLightLbl.setText(FREEZER_LIGHT_ON);

	}

	/**
	 * Sets Freezer Light Label to OFF
	 */

	@Override
	public void turnFreezerLightOff() {
		freezerLightLbl.setText(FREEZER_LIGHT_OFF);

	}

	/**
	 * Sets Freezer Cooling Label to OFF
	 */

	@Override
	public void turnFreezerCoolerOn() {
		freezerCoolingLbl.setText(FREEZER_COOLING_ON);

	}

	/**
	 * Sets Freezer Cooling Label to OFF
	 */

	@Override
	public void turnFreezerCoolerOff() {
		freezerCoolingLbl.setText(FREEZER_COOLING_OFF);

	}

	/**
	 * @author Nick Clarity
	 * Project 2 Iteration 1
	 * Apr 1, 2015
	 * 
	 * Instantiates, Aligns and Adds all components to the screen.
	 */
	private class Panel extends JPanel{
		public Panel(){
			setLayout(null);

			JLabel lblNewLabel = new JLabel("Room Temp:");
			lblNewLabel.setBounds(27, 29, 115, 14);
			add(lblNewLabel);

			JLabel lblNewLabel_1 = new JLabel("Desired Fridge Temp:");
			lblNewLabel_1.setBounds(27, 56, 133, 14);
			add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel("Desired Freezer Temp:");
			lblNewLabel_2.setBounds(27, 84, 133, 14);
			add(lblNewLabel_2);

			setRoomTemp = new JButton("Set Room Temp");
			setRoomTemp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try{
						String t1 = roomField.getText();
						float t2 = Float.parseFloat(t1);
						setErrorLbl("");
						setErrorLblVisible(false);

						context.setRoomTemp(t2);
					}catch(NumberFormatException nfe){
						roomField.setText("");

						setErrorLblVisible(true);
						setErrorLbl("Room Temp must be a number.");
					}

				}
			});
			setRoomTemp.setBounds(280, 23, 200, 23);
			add(setRoomTemp);

			setFridgeTemp = new JButton("Set Desired Fridge Temp");
			setFridgeTemp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try{
						String t1 = fridgeField.getText();
						float t2 = Float.parseFloat(t1);

						setErrorLbl("");
						setErrorLblVisible(false);

						//context.setFridgeTemp(t2);
					}catch(NumberFormatException nfe){
						fridgeField.setText("");

						setErrorLblVisible(true);
						setErrorLbl("Fridge Temp must be a number.");
					}		        	
				}});
			setFridgeTemp.setBounds(280, 52, 200, 23);
			add(setFridgeTemp);

			setFreezerTemp = new JButton("Set Desired Freezer Temp");
			setFreezerTemp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try{
						String t1 = freezerField.getText();
						float t2 = Float.parseFloat(t1);

						setErrorLbl("");
						setErrorLblVisible(false);

						//context.setFreezerTemp(t2);
					}catch(NumberFormatException nfe){
						freezerField.setText("");

						setErrorLblVisible(true);
						setErrorLbl("Freezer Temp must be a number.");
					}
				}
			});
			setFreezerTemp.setBounds(280, 80, 200, 23);
			add(setFreezerTemp);

			roomField = new JTextField();
			roomField.setBounds(190, 24, 50, 20);
			add(roomField);
			roomField.setColumns(10);

			fridgeField = new JTextField();
			fridgeField.setBounds(190, 53, 50, 20);
			add(fridgeField);
			fridgeField.setColumns(10);

			freezerField = new JTextField();
			freezerField.setBounds(190, 81, 50, 20);
			add(freezerField);
			freezerField.setColumns(10);

			openFridgeDoor = new FridgeDoorOpenButton("Open Fridge Door");
			openFridgeDoor.addActionListener(GUI.this);
			openFridgeDoor.setBounds(69, 125, 150, 23);
			add(openFridgeDoor);

			openFreezerDoor = new JButton("Open Freezer Door");
			openFreezerDoor.addActionListener(GUI.this);
			openFreezerDoor.setBounds(69, 159, 150, 23);
			add(openFreezerDoor);

			closeFridgeDoor = new FridgeDoorCloseButton("Close Fridge Door");
			closeFridgeDoor.addActionListener(GUI.this);
			closeFridgeDoor.setBounds(263, 125, 150, 23);
			add(closeFridgeDoor);

			closeFreezerDoor = new JButton("Close Freezer Door");
			closeFreezerDoor.addActionListener(GUI.this);
			closeFreezerDoor.setBounds(263, 159, 150, 23);
			add(closeFreezerDoor);

			JLabel lblNewLabel_3 = new JLabel("Status");
			lblNewLabel_3.setBounds(39, 207, 46, 14);
			add(lblNewLabel_3);

			fridgeLightLbl = new JLabel("Fridge Light <on/off>");
			fridgeLightLbl.setBounds(49, 232, 150, 14);
			add(fridgeLightLbl);

			fridgeTempLbl = new JLabel("Fridge temp <nn>");
			fridgeTempLbl.setBounds(49, 257, 150, 14);
			add(fridgeTempLbl);

			fridgeCoolingLbl = new JLabel("Fridge <cooling/idle>");
			fridgeCoolingLbl.setBounds(49, 282, 150, 14);
			add(fridgeCoolingLbl);

			freezerLightLbl = new JLabel("Freezer Light <on/off>");
			freezerLightLbl.setBounds(256, 232, 150, 14);
			add(freezerLightLbl);

			freezerTempLbl = new JLabel("Freezer Temp <nn>");
			freezerTempLbl.setBounds(256, 257, 150, 14);
			add(freezerTempLbl);

			freezerCoolingLbl = new JLabel("Freezer <cooling/idle>");
			freezerCoolingLbl.setBounds(256, 282, 150, 14);
			add(freezerCoolingLbl);

			errorLbl = new JLabel();
			errorLbl.setBounds(20, 310, 400, 14);
			errorLbl.setForeground(Color.red);
			errorLbl.setVisible(false);
			add(errorLbl);
		}
	}

	/*
	 * Method to input the content of the config File.
	 * 
	 * @param aFile - The config file for the Refrigerator System
	 * @return the location of the file.
	 */
	private String fileScan(File aFile){
		String fileName = aFile.getName().replaceFirst("[.][^.]+$", "");
		String whereFile = aFile.getParent();

		BufferedReader input = null;
		String oneLine;

		//Open the input stream
		try {
			input = new BufferedReader(new FileReader(aFile));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Couldn't find file.", "File not found.", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		//Read the stream
		try {
			while((oneLine = input.readLine()) != null){
				content.add(oneLine);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unable to read file.", "IO Exception", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch(NullPointerException npe){
			JOptionPane.showMessageDialog(null, "That file isn't there.", 
					"Null Pointer", JOptionPane.ERROR_MESSAGE);
			npe.printStackTrace();
		}

		//Close the input stream
		try {
			input.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Can't close output.", 
					"IO Exception", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		acceptFile();
		return whereFile;
	}

	/*
	 * If a file has been successfully read, this method parses variables from the text.
	 * 
	 * @return an array of ints containing all the config variables.
	 */
	private void acceptFile(){
		ListIterator <String> input = content.listIterator();
		int i = 0;
		while(input.hasNext()){
			data[i] = Integer.parseInt(input.next());
			i++;
		}

		//print to console so we know it's there.
		for(int val:data){
			System.out.println(val);
		}
	}

	/**
	 * Main method
	 * 
	 * If no args are passed in the default config file is used.
	 * 
	 * @param args - path to config file.
	 */
	public static void main(String[] args) {
		if(args.length > 0){
			new GUI(new File(args[0]));
		} else {
			new GUI(new File("input.txt"));
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		((GUIButton) event.getSource()).inform(context, this);

	}




}