package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class StatusBox {
	HBox hBox1;
	
	TextField wordCountField;
	TextField sentenceCountField;
	TextField fleschScoreField;
	
	Label wordCountLabel;
	Label sentenceCountLabel;
	Label fleschScoreLabel;
	
	public StatusBox() {
		hBox1 = new HBox();
		wordCountLabel= new Label("Word Count");
		wordCountLabel.setWrapText(true);
		wordCountField = new TextField();
		wordCountField.setEditable(false);
		wordCountField.setVisible(false);
		sentenceCountLabel= new Label("Sentence Count");
		sentenceCountLabel.setWrapText(true);
		sentenceCountField = new TextField();
		sentenceCountField.setEditable(false);
		sentenceCountField.setVisible(false);
		fleschScoreLabel= new Label("Flesch Score");
		fleschScoreLabel.setWrapText(true);
		fleschScoreField= new TextField();
		fleschScoreField.setVisible(false);
		fleschScoreField.setEditable(false);
		
		hBox1.setMaxHeight(100);
		
		hBox1.getChildren().addAll(wordCountLabel,wordCountField,sentenceCountLabel,sentenceCountField,fleschScoreLabel,fleschScoreField);	
	}
	
	public HBox getStatusBox() {
		return hBox1;
	}
}
