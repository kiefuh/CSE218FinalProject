package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Exceptions {

	public static void nullTextArea() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Null TextArea");
		alert.setHeaderText("There was a problem");
		alert.setContentText("There is no text available. Please open a new text document");
		alert.showAndWait();
	}
	
	public static void fileIOException() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("No File Found");
		alert.setHeaderText("There was a problem");
		alert.setContentText("File can not be found");
		alert.showAndWait();
	}
	
	public static void noFileChosen() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("No File Chosen");
		alert.setHeaderText("There was a problem");
		alert.setContentText("You did not choose a file");
		alert.showAndWait();
	}
	
	public static void noDestinationChosen() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("No Destination Chosen");
		alert.setHeaderText("There was a problem");
		alert.setContentText("You did not choose a destination");
		alert.showAndWait();
	}
	
}
