package markov;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExceptionAlerts {

	public static void fileNotFound() {
		Alert alert1= new Alert(AlertType.ERROR);
		alert1.setTitle("File Not Found");
		alert1.setHeaderText("Your request was not able to go through");
		alert1.setContentText("Can not find the specified file");
		alert1.showAndWait();	
	}

}
