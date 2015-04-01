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
import javax.swing.filechooser.FileFilter;

public class GUI extends JFrame {
	public static final String FRIDGE_LIGHT_ON = "Fridge Light <on>";
	public static final String FRIDGE_LIGHT_OFF = "Fridge Light <off>";
	public static final String FREEZER_LIGHT_ON = "Freezer Light <on>";
	public static final String FREEZER_LIGHT_OFF = "Freezer Light <off>";
	public static final String FRIDGE_COOLING_ON = "Fridge Cool <on>";
	public static final String FRIDGE_COOLING_OFF = "Fridge Cool <off>";
	public static final String FREEZER_COOLING_ON = "Freezer Cool <on>";
	public static final String FREEZER_COOLING_OFF = "Freezer Cool <off>";

	private Refrigerator refrigerator;

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

	private JTextField fieldShowName;
	private JTextField roomField;
	private JTextField fridgeField;
	private JTextField freezerField;

	private JLabel fridgeCoolingLbl;
	private JLabel fridgeTempLbl;
	private JLabel fridgeLightLbl;
	private JLabel freezerLightLbl;
	private JLabel freezerTempLbl;
	private JLabel freezerCoolingLbl;

	private Listen listen;



	public GUI(File file){
		refrigerator = Refrigerator.instance();
		refrigerator.setGUI(this);

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
		refrigerator.setData(acceptFile());
	}

	private void centerGUI() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();

		width /= 2;
		height /= 2;

		defaultLocation = new Point(width - (frameWidth / 2), height
				- (frameHeight / 2));
	}

	private class Listen implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == setRoomTemp){
				try{
					String t1 = roomField.getText();
					float t2 = Float.parseFloat(t1);
					refrigerator.setRoomTemp(t2);
					roomField.setText("Temp set.");

				}catch(NumberFormatException nfe){
					roomField.setText("Use a number.");
				}

			} else if (e.getSource() == setFridgeTemp){
				try{
					String t1 = fridgeField.getText();
					float t2 = Float.parseFloat(t1);
					refrigerator.setFridgeTemp(t2);

				}catch(NumberFormatException nfe){
					fridgeField.setText("Use a number.");
				}

			} else if (e.getSource() == setFreezerTemp){
				try{
					String t1 = freezerField.getText();
					float t2 = Float.parseFloat(t1);
					refrigerator.setFreezerTemp(t2);

				}catch(NumberFormatException nfe){
					freezerField.setText("Use a number.");
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
	public void freezerFieldSetText(String text){
		freezerField.setText(text);
	}
	public void fridgeFieldSetText(String text){
		fridgeField.setText(text);
	}
	public String getFridgeCoolingLbl() {
		return fridgeCoolingLbl.getText();
	}

	public void setFridgeCoolingLbl(String fridgeCoolingLbl) {
		this.fridgeCoolingLbl.setText(fridgeCoolingLbl);
		repaint();
	}

	public String getFridgeTempLbl() {
		return fridgeTempLbl.getText();
	}

	public void setFridgeTempLbl(String fridgeTempLbl) {
		this.fridgeTempLbl.setText(fridgeTempLbl);
		repaint();
	}

	public String getFridgeLightLbl() {
		return fridgeLightLbl.getText();
	}

	public void setFridgeLightLbl(String fridgeLightLbl) {
		this.fridgeLightLbl.setText(fridgeLightLbl);
		repaint();
	}

	public String getFreezerLightLbl() {
		return freezerLightLbl.getText();
	}

	public void setFreezerLightLbl(String freezerLightLbl) {
		this.freezerLightLbl.setText(freezerLightLbl);
		repaint();
	}

	public String getFreezerTempLbl() {
		return freezerTempLbl.getText();
	}

	public void setFreezerTempLbl(String freezerTempLbl) {
		this.freezerTempLbl.setText(freezerTempLbl);
		repaint();
	}

	public String getFreezerCoolingLbl() {
		return freezerCoolingLbl.getText();
	}

	public void setFreezerCoolingLbl(String freezerCoolingLbl) {
		this.freezerCoolingLbl.setText(freezerCoolingLbl);
		repaint();
	}

	class Panel extends JPanel{
		public Panel(){
			setLayout(null);
			JLabel lblFileInfo = new JLabel("File Info");
			lblFileInfo.setBounds(27, 13, 133, 14);
			add(lblFileInfo);
			fieldShowName = new JTextField();
			fieldShowName.setText("Name of file");
			fieldShowName.setBounds(170, 10, 86, 20);
			add(fieldShowName);

			JLabel lblNewLabel = new JLabel("Room Temp");
			lblNewLabel.setBounds(27, 42, 115, 14);
			add(lblNewLabel);

			JLabel lblNewLabel_1 = new JLabel("Desired Fridge Temp");
			lblNewLabel_1.setBounds(27, 71, 133, 14);
			add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel("Desired Freezer Temp");
			lblNewLabel_2.setBounds(27, 99, 133, 14);
			add(lblNewLabel_2);

			setRoomTemp = new JButton("Set Room Temp");
			setRoomTemp.addActionListener(listen);
			setRoomTemp.setBounds(280, 38, 200, 23);
			add(setRoomTemp);

			setFridgeTemp = new JButton("Set Desired Fridge Temp");
			setFridgeTemp.addActionListener(listen);
			setFridgeTemp.setBounds(280, 67, 200, 23);
			add(setFridgeTemp);

			setFreezerTemp = new JButton("Set Desired Freezer Temp");
			setFreezerTemp.addActionListener(listen);
			setFreezerTemp.setBounds(280, 95, 200, 23);
			add(setFreezerTemp);

			roomField = new JTextField();
			roomField.setBounds(170, 39, 86, 20);
			add(roomField);
			roomField.setColumns(10);

			fridgeField = new JTextField();
			fridgeField.setBounds(170, 68, 86, 20);
			add(fridgeField);
			fridgeField.setColumns(10);

			freezerField = new JTextField();
			freezerField.setBounds(170, 96, 86, 20);
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
			fridgeLightLbl.setBounds(49, 232, 130, 14);
			add(fridgeLightLbl);

			fridgeTempLbl = new JLabel("Fridge temp <nn>");
			fridgeTempLbl.setBounds(49, 257, 130, 14);
			add(fridgeTempLbl);

			fridgeCoolingLbl = new JLabel("Fridge <cooling/idle>");
			fridgeCoolingLbl.setBounds(49, 282, 130, 14);
			add(fridgeCoolingLbl);

			freezerLightLbl = new JLabel("Freezer Light <on/off>");
			freezerLightLbl.setBounds(256, 232, 130, 14);
			add(freezerLightLbl);

			freezerTempLbl = new JLabel("Freezer Temp <nn>");
			freezerTempLbl.setBounds(256, 257, 130, 14);
			add(freezerTempLbl);

			freezerCoolingLbl = new JLabel("Freezer <cooling/idle>");
			freezerCoolingLbl.setBounds(256, 282, 130, 14);
			add(freezerCoolingLbl);
		}
	}

	//Method to input the content of the chosen file
	public String fileScan(File aFile){
		String fileName = aFile.getName().replaceFirst("[.][^.]+$", "");
		String whereFile = aFile.getParent();

		fieldShowName.setText(fileName);
		BufferedReader input = null;
		String oneLine;

		//Open the input stream
		try {
			input = new BufferedReader(new FileReader(aFile));
		} catch (FileNotFoundException e) {
			fieldShowName.setText("File not found.");
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
			fieldShowName.setText("Can't close file.");
			e.printStackTrace();
		}
		acceptFile();
		return whereFile;
	}

	public int[] acceptFile(){
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

	public static void main(String[] args) {
		if(args.length > 0){
			new GUI(new File(args[0]));
		}else{
			new GUI(new File("input.txt"));
		}
	}
}