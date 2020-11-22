package view;

import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class SpellCheckBox {
	ListView<String> listView;
	VBox vBox;
	
	public SpellCheckBox() {
		listView= new ListView<>();
		vBox= new VBox();
		vBox.getChildren().addAll(listView);
	}
	
	public VBox getSpellCheckBox() {
		return vBox;
	}
	
	public ListView<String> getListView(){
		return listView;
	}
	
	public void putInListView(String string) {
		listView.getItems().add(string);
	}
	
}
