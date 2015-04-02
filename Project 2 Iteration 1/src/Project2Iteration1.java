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
 * Apr 1, 2015
 * 
 * GUI for Project 2 Iteration 1.  Class is titled "Project2Iteration1" because
 * it contains the main method and it will be run from the command line with 
 * that title.
 */
public class Project2Iteration1 extends JFrame {
	private Refrigerator refrigerator;

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

	private Listen listen;

	/**
	 * Constructor with a File param.
	 * @param file is the config File passed in as a commandline arg.
	 */
	public Project2Iteration1(File file){
		listen = new Listen();
		add(new Panel());

		centerGUI();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(this.defaultLocation);
		setTitle("Refrigerator Dashboard");
		setPreferredSize(new Dimension(frameWidth, frameHeight));
		pack();
		setVisible(true);

		fileScan(file);
		refrigerator = Refrigerator.instance();
		
		//setData() and init() because cant pass args to contructor of a singleton
		refrigerator.setData(acceptFile());
		refrigerator.init(this);
		
		//start the clock.
		new Clock();
	}

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

	/**
	 * @author Nick Clarity
	 * Project 2 Iteration 1
	 * Apr 1, 2015
	 * 
	 * Action Listener class for all the Button presses.
	 */
	private class Listen implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == setRoomTemp){
				
				// parse temps to send to Refrigerator
				try{
					String t1 = roomField.getText();
					float t2 = Float.parseFloat(t1);
					setErrorLbl("");
					setErrorLblVisible(false);

					refrigerator.setRoomTemp(t2);
				}catch(NumberFormatException nfe){
					roomField.setText("");
					
					setErrorLblVisible(true);
					setErrorLbl("Room Temp must be a number.");
				}

				// parse temps to send to Refrigerator
			} else if (e.getSource() == setFridgeTemp){
				try{
					String t1 = fridgeField.getText();
					float t2 = Float.parseFloat(t1);
					
					setErrorLbl("");
					setErrorLblVisible(false);

					refrigerator.setFridgeTemp(t2);
				}catch(NumberFormatException nfe){
					fridgeField.setText("");
					
					setErrorLblVisible(true);
					setErrorLbl("Fridge Temp must be a number.");
				}

				// parse temps to send to Refrigerator
			} else if (e.getSource() == setFreezerTemp){
				try{
					String t1 = freezerField.getText();
					float t2 = Float.parseFloat(t1);

					setErrorLbl("");
					setErrorLblVisible(false);
					
					refrigerator.setFreezerTemp(t2);
				}catch(NumberFormatException nfe){
					freezerField.setText("");
					
					setErrorLblVisible(true);
					setErrorLbl("Freezer Temp must be a number.");
				}
			} else if (e.getSource() == openFridgeDoor){
				refrigerator.openFridgeDoor();
			} else if (e.getSource() == closeFridgeDoor){
				refrigerator.closeFridgeDoor();
			} else if (e.getSource() == openFreezerDoor){
				refrigerator.openFreezerDoor();
			} else if (e.getSource() == closeFreezerDoor){
				refrigerator.closeFreezerDoor();
			}
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
	 * Setter for Fridge Cooling Label text
	 * @param fridgeCoolingLbl - String
	 */
	public void setFridgeCoolingLbl(String fridgeCoolingLbl) {
		this.fridgeCoolingLbl.setText(fridgeCoolingLbl);
		repaint();
	}

	/**
	 * Getter for Fridge Temp Label text
	 * @return String
	 */
	public String getFridgeTempLbl() {
		return fridgeTempLbl.getText();
	}

	/**
	 * Setter for Fridge Temp Label text
	 * @param fridgeTempLbl - String
	 */
	public void setFridgeTempLbl(String fridgeTempLbl) {
		this.fridgeTempLbl.setText(fridgeTempLbl);
		repaint();
	}

	/**
	 * Getter for Fridge Light Label text
	 * @return String
	 */
	public String getFridgeLightLbl() {
		return fridgeLightLbl.getText();
	}

	/**
	 * Setter for Fridge Light Label text
	 * @param fridgeLightLbl - String
	 */
	public void setFridgeLightLbl(String fridgeLightLbl) {
		this.fridgeLightLbl.setText(fridgeLightLbl);
		repaint();
	}

	/**
	 * Getter for Freezer Light Label text
	 * @return String
	 */
	public String getFreezerLightLbl() {
		return freezerLightLbl.getText();
	}

	/**
	 * Setter for Freezer Light Label text
	 * @param freezerLightLbl - String
	 */
	public void setFreezerLightLbl(String freezerLightLbl) {
		this.freezerLightLbl.setText(freezerLightLbl);
		repaint();
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
		repaint();
	}

	/**
	 * Getter for Freezer Cooling Label text
	 * @return String
	 */
	public String getFreezerCoolingLbl() {
		return freezerCoolingLbl.getText();
	}

	/**
	 * Setter for Freezer Cooling Label text
	 * @param freezerCoolingLbl - String
	 */
	public void setFreezerCoolingLbl(String freezerCoolingLbl) {
		this.freezerCoolingLbl.setText(freezerCoolingLbl);
		repaint();
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
		repaint();
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
			setRoomTemp.addActionListener(listen);
			setRoomTemp.setBounds(280, 23, 200, 23);
			add(setRoomTemp);

			setFridgeTemp = new JButton("Set Desired Fridge Temp");
			setFridgeTemp.addActionListener(listen);
			setFridgeTemp.setBounds(280, 52, 200, 23);
			add(setFridgeTemp);

			setFreezerTemp = new JButton("Set Desired Freezer Temp");
			setFreezerTemp.addActionListener(listen);
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

			openFridgeDoor = new JButton("Open Fridge Door");
			openFridgeDoor.addActionListener(listen);
			openFridgeDoor.setBounds(69, 125, 150, 23);
			add(openFridgeDoor);

			openFreezerDoor = new JButton("Open Freezer Door");
			openFreezerDoor.addActionListener(listen);
			openFreezerDoor.setBounds(69, 159, 150, 23);
			add(openFreezerDoor);

			closeFridgeDoor = new JButton("Close Fridge Door");
			closeFridgeDoor.addActionListener(listen);
			closeFridgeDoor.setBounds(263, 125, 150, 23);
			add(closeFridgeDoor);

			closeFreezerDoor = new JButton("Close Freezer Door");
			closeFreezerDoor.addActionListener(listen);
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
			//TODO output
		}

		//Read the stream
		try {
			while((oneLine = input.readLine()) != null){
				content.add(oneLine);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unable to read file.", "No can do,  boss.", JOptionPane.ERROR_MESSAGE);
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
			// TODO output
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
	private int[] acceptFile(){
		ListIterator <String> input = content.listIterator();
		int[] data = new int[14];
		int i = 0;
		while(input.hasNext()){
			data[i] = Integer.parseInt(input.next());
			i++;
		}

		//print to console so we know it's there.
		for(int val:data){
			System.out.println(val);
		}
		return data;
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
			new Project2Iteration1(new File(args[0]));
		} else {
			new Project2Iteration1(new File("input.txt"));
		}
	}
}