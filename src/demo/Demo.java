package demo;

import javafx.scene.control.TextArea;

public class Demo {

	public static void main(String[] args) {
		TextArea ta = new TextArea();
		ta.textProperty().addListener((observable, oldValue, newValue) -> {
		  System.out.println(newValue);
		});

	}

}
