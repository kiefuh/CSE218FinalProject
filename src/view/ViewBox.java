package view;



import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import markov.MasterLinkList;
import markov.SongGenerator;



public class ViewBox {
	MasterLinkList mLL;
	VBox vBox;
	HBox box1;
	HBox box2;
	HBox box3;
	Button generate;
	
	TextArea songArea;
	
	TextField startingWordField;
	TextField lengthField;
	
	public ViewBox(MasterLinkList filledList) {
		this.mLL=filledList;
		generate= new Button("Generate Text");
		
		
		startingWordField= new TextField();
		startingWordField.setPromptText("Start Word");
		lengthField= new TextField();
		lengthField.setPromptText("Text Length");
		
		songArea= new TextArea();
		songArea.setWrapText(true);
		
		vBox= new VBox();
		box1= new HBox();
		box1.getChildren().addAll(startingWordField,lengthField);
		box1.setAlignment(Pos.CENTER);
		box2= new HBox();
		box2.getChildren().addAll(generate);
		box2.setAlignment(Pos.CENTER);
		
		//box3.getChildren().addAll(studentNameGpaArea);
		vBox.getChildren().addAll(box1,box2);
		setEventListeners();
	}

	private void setEventListeners() {
		generate.setOnAction(e->{
			String startingWord=startingWordField.getText();
			int length=Integer.parseInt(lengthField.getText());
			SongGenerator sG = new SongGenerator(mLL);
			String song=sG.generate(length, startingWord);
			songArea.setText(song);
		});
		
		
	}
	
	public VBox getViewBox() {
		return vBox;
	}
	
	public TextArea getTextArea() {
		return songArea;
	}
}
