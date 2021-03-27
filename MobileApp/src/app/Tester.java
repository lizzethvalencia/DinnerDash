package app;
import ordering.*;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException {
		try {
			File inputFile = new File("creditCardInfo");
			File inputFile2 = new File("menu");
			Driver guiFrame = new Driver(inputFile, inputFile2);
		} catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Error! Menu File not found!", "Please reinput", JOptionPane.ERROR_MESSAGE);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error! Program terminated", " Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
