package view;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProgressBox {
	private ProgressBar pb;
	private Label label;
	private HBox box1;
	private HBox box2;
	private VBox vBox;
	
	public ProgressBox(String labelText) {
		pb= new ProgressBar();
		box1= new HBox();
		box1.getChildren().addAll(pb);
		label= new Label(labelText);
		box2= new HBox();
		box2.getChildren().addAll(label);
		vBox= new VBox();
		vBox.getChildren().addAll(box1,box2);
	}
	
	public VBox getVBox() {
		return vBox;
	}
	
	public ProgressBar getProgressBar() {
		return pb;
	}
	
	public void setProgress(double progress) {
		pb.setProgress(progress);
	}
	
	
}
