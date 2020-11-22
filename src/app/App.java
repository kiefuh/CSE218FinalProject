package app;

import java.io.File;
import java.util.Locale;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.SpellCheck;
import util.ViewHelper;
import view.MenuBox;
import view.StatusBox;

public class App extends Application{

	public static void main(String[] args) {
		launch(args);

	}
	
	static BorderPane root= new BorderPane();
	static Scene scene;
	public static BorderPane getRoot() {
		return root;
	}
	
	public static SpellCheck checker;
	
	public static StatusBox sBox= new StatusBox();
	public static StatusBox getStatusBox() {
		return sBox;
	}
	
	public static void setStatusBox(StatusBox sbox) {
		sBox=sbox;
	}
	public static Scene getScene() {
		return scene;
	}

	@Override
	
	public void start(Stage primaryStage) throws Exception {
		File file = new File("dictionary.txt");
		String string = ViewHelper.fileOpener(file);
		string=string.toLowerCase();
		String[] arr=string.split("\n");
		checker = new SpellCheck(arr.length*2);
		for(int i=0;i<arr.length;i++) {
		checker.fillDictionary(arr[i]);
		}
		MenuBox menuBox = new MenuBox(primaryStage);
		root.setTop(menuBox.getMenuBar());
		root.setBottom(sBox.getStatusBox());
		scene = new Scene(root,800,300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Kleister Word Processor 2020");
		primaryStage.show();
		
	}

}
