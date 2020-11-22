package view;

import javafx.scene.control.TextArea;


public class TextBox {
	private TextArea editorField;
	
	public TextBox() {
		
		editorField= new TextArea();
		editorField.setWrapText(true);
		editorField.setScaleShape(true);
		
	}
	
	public TextArea getTextBox() {
		return editorField;
	}
}
