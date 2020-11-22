package view;

import java.io.File;
import java.util.LinkedList;

import app.App;
import javafx.scene.chart.LineChart;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import markov.MasterLinkList;
import markov.TextAnalyzer;
import model.Plot;
import model.SpellCheck;
import model.Truncate;
import util.Exceptions;
import util.ViewHelper;

public class MenuBox {
	private MenuBar menuBar;
	private Clipboard systemClipBoard = Clipboard.getSystemClipboard();

	public MenuBox(Stage stage) {
		menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(stage.widthProperty());

		Menu viewMenu = new Menu("View");
		CheckMenuItem wordCountItem = new CheckMenuItem("Word Count");
		CheckMenuItem sentenceCountItem = new CheckMenuItem("Sentence Count");
		CheckMenuItem fleschScore = new CheckMenuItem("Flesch Score");
		CheckMenuItem spellCheckItem = new CheckMenuItem("Spell Check");

		wordCountItem.setOnAction(e -> {
				TextArea t = (TextArea) App.getRoot().getCenter();
				if (wordCountItem.isSelected()) {
					try {
						App.getStatusBox().wordCountField.setVisible(true);
						String initCount = ViewHelper.wordCounter(t.getText());
						App.getStatusBox().wordCountField.setText(initCount);
						t.textProperty().addListener((observable, oldValue, newValue) -> {
							App.getStatusBox().wordCountField.setText(ViewHelper.wordCounter(newValue));
						});
					} catch (Exception e1) {
						wordCountItem.setSelected(false);
						App.getStatusBox().wordCountField.setVisible(false);
						Exceptions.nullTextArea();
					}
				} else {
					App.getStatusBox().wordCountField.setVisible(false);
				}
			
		});

		
		sentenceCountItem.setOnAction(e -> {
				TextArea t = (TextArea) App.getRoot().getCenter();
				if (sentenceCountItem.isSelected()) {
					try {
						App.getStatusBox().sentenceCountField.setVisible(true);
						String initCount = ViewHelper.sentenceCounterGUI(t.getText());
						App.getStatusBox().sentenceCountField.setText(initCount);
						t.textProperty().addListener((observable, oldValue, newValue) -> {
							App.getStatusBox().sentenceCountField.setText(ViewHelper.sentenceCounterGUI(newValue));
						});
					} catch (Exception e1) {
						App.getStatusBox().sentenceCountField.setVisible(false);
						sentenceCountItem.setSelected(false);
						Exceptions.nullTextArea();
					}
				} else {
					App.getStatusBox().sentenceCountField.setVisible(false);
				}
		});

		fleschScore.setOnAction(e -> {
				TextArea t = (TextArea) App.getRoot().getCenter();
				if (fleschScore.isSelected()) {
					try {
						App.getStatusBox().fleschScoreField.setVisible(true);
						String initCount = ViewHelper.fleschScore(t.getText());
						App.getStatusBox().fleschScoreField.setText(initCount);
						t.textProperty().addListener((observable, oldValue, newValue) -> {
							App.getStatusBox().fleschScoreField.setText(ViewHelper.fleschScore(newValue));
						});
					} catch (Exception e1) {
						App.getStatusBox().fleschScoreField.setVisible(false);
						fleschScore.setSelected(false);
						Exceptions.nullTextArea();
					}
				} else {
					App.getStatusBox().fleschScoreField.setVisible(false);
				}

		});

		spellCheckItem.setOnAction(e -> {
			
			SpellCheckBox checkBox = new SpellCheckBox();
			VBox check = checkBox.getSpellCheckBox();
			if(spellCheckItem.isSelected()) {
			try {
				TextArea t = (TextArea) App.getRoot().getCenter();
				App.getRoot().setLeft(check);
				String text = t.getText().toLowerCase();
				//text = text.replaceAll("[!.?,\"-;—:“”_‘„”«»——_]+", "\t");
				String[] arr = text.split("[^\\w']+");
				for (int i = 0; i < arr.length; i++) {
					if (!App.checker.isInDictionary(arr[i])) {
							checkBox.getListView().getItems().add(arr[i]);
							System.out.println(arr[i]);
						}
					}
				t.textProperty().addListener((observable, oldValue, newValue) -> {
					checkBox.getListView().getItems().clear();
					String text2 = t.getText().toLowerCase();
					//text2 = text2.replaceAll("[//[!.?,\"-;—:“”_‘„”«»——_//]]+", "\t");
					String[] arr2 = text2.split("[^\\w']+");
					for (int i = 0; i < arr2.length; i++) {
						if (App.checker.isInDictionary(arr2[i]) == false) {
								checkBox.getListView().getItems().add(arr2[i]);
								System.out.println(arr2[i]);
							}
						}

					});
			} catch (Exception e1) {
				App.getRoot().setLeft(null);
				spellCheckItem.setSelected(false);
				Exceptions.nullTextArea();
			}
			}
			else {
				App.getRoot().setLeft(null);
			}
		});

		viewMenu.getItems().addAll(wordCountItem, sentenceCountItem, fleschScore, spellCheckItem);

		Menu fileMenu = new Menu("File");
		MenuItem newItem = new MenuItem("New");
		MenuItem openItem = new MenuItem("Open");
		MenuItem closeItem = new MenuItem("Close");
		MenuItem saveItem = new MenuItem("Save");
		MenuItem exitItem = new MenuItem("Exit");

		newItem.setOnAction(e -> {
			TextBox newViewBox = new TextBox();
			StatusBox sBox = new StatusBox();
			clearRoot();
			App.getRoot().setCenter(newViewBox.getTextBox());
			App.getRoot().setBottom(sBox.getStatusBox());
			App.setStatusBox(sBox);
			wordCountItem.setSelected(false);
			sentenceCountItem.setSelected(false);
			fleschScore.setSelected(false);
			spellCheckItem.setSelected(false);
			clearStatusBar();

		});

		openItem.setOnAction(e -> {
			clearRoot();
			try {
				FileChooser fC = new FileChooser();
				ExtensionFilter filter = new ExtensionFilter("text filter", "*.txt");
				fC.getExtensionFilters().add(filter);
				File file = fC.showOpenDialog(stage);
				if (file != null) {
					String text = ViewHelper.fileOpener(file);
					clearStatusBar();
					TextBox newViewBox = new TextBox();
					TextArea newArea = newViewBox.getTextBox();
					newArea.setText(text);
					StatusBox sBox = new StatusBox();
					wordCountItem.setSelected(false);
					sentenceCountItem.setSelected(false);
					fleschScore.setSelected(false);
					spellCheckItem.setSelected(false);
					App.getRoot().setBottom(sBox.getStatusBox());
					App.getRoot().setCenter(newArea);
					App.setStatusBox(sBox);
				}
			} catch (Exception e1) {
				Exceptions.noFileChosen();
			}
		});

		closeItem.setOnAction(e -> {
			clearRoot();
			wordCountItem.setSelected(false);
			sentenceCountItem.setSelected(false);
			fleschScore.setSelected(false);
			spellCheckItem.setSelected(false);
			clearStatusBar();
		});

		saveItem.setOnAction(e -> {
			try {
				TextInputDialog dialog = new TextInputDialog("name");
				dialog.setTitle("Set File Name");
				dialog.setHeaderText("Please input your desired file name in format file.txt");
				dialog.setContentText("File Name");
				dialog.showAndWait();
				String fileName = dialog.getResult();
				DirectoryChooser directoryChooser = new DirectoryChooser();
				String fileLocation = directoryChooser.showDialog(stage).getAbsolutePath() + File.separator + fileName;
				ViewHelper.save(fileLocation);
			} catch (Exception e1) {
				
				Exceptions.noDestinationChosen();
				}
		});

		exitItem.setOnAction(e -> {
			System.exit(0);
		});

		fileMenu.getItems().addAll(newItem, openItem, closeItem, saveItem, exitItem);

		Menu editMenu = new Menu("Edit");
		MenuItem copyItem = new MenuItem("Copy");
		MenuItem cutItem = new MenuItem("Cut");
		MenuItem deleteItem = new MenuItem("Delete");
		MenuItem pasteItem = new MenuItem("Paste");

		copyItem.setOnAction(e -> {
			try {
				ClipboardContent content = new ClipboardContent();
				TextArea text = (TextArea) App.getRoot().getCenter();
				content.putString(text.getSelectedText());
				systemClipBoard.setContent(content);
			} catch (Exception e1) {
				Exceptions.nullTextArea();
			}
		});

		cutItem.setOnAction(e -> {
			try {
				TextArea textArea = (TextArea) App.getRoot().getCenter();
				ClipboardContent content = new ClipboardContent();
				content.putString(textArea.getSelectedText());
				systemClipBoard.setContent(content);
				String text = textArea.getText();
				IndexRange range = textArea.getSelection();
				String leftSide = text.substring(0, range.getStart());
				String rightSide = text.substring(range.getEnd(), text.length());
				String newText = leftSide + rightSide;
				textArea.setText(newText);
				App.getRoot().setCenter(textArea);
			} catch (Exception e1) {
				Exceptions.nullTextArea();
			}
		});

		deleteItem.setOnAction(e -> {
			try {
				TextArea textArea = (TextArea) App.getRoot().getCenter();
				String text = textArea.getText();
				IndexRange range = textArea.getSelection();
				String leftSide = text.substring(0, range.getStart());
				String rightSide = text.substring(range.getEnd(), text.length());
				String newText = leftSide + rightSide;
				textArea.setText(newText);
				App.getRoot().setCenter(textArea);
			} catch (Exception e1) {
				Exceptions.nullTextArea();
			}
		});

		pasteItem.setOnAction(e -> {
			try {
				TextArea textArea = (TextArea) App.getRoot().getCenter();
				String text = textArea.getText();
				IndexRange range = textArea.getSelection();
				String leftSide = text.substring(0, range.getStart());
				String rightSide = text.substring(range.getEnd(), text.length());
				String newText = leftSide + systemClipBoard.getString() + rightSide;
				textArea.setText(newText);
				App.getRoot().setCenter(textArea);
			} catch (Exception e1) {
				Exceptions.nullTextArea();
			}
		});

		Menu actionMenu = new Menu("Action");
		MenuItem markovItem = new MenuItem("Markov");
		MenuItem truncateItem = new MenuItem("Truncate");
		MenuItem showItem = new MenuItem("Show");
		MenuItem plotItem = new MenuItem("Plot");
		MenuItem discussionItem = new MenuItem("Discussion");

		markovItem.setOnAction(e -> {
			 try {
			ViewHelper.saveMarkov("outsideSource/markov.txt");
			MasterLinkList song = TextAnalyzer.importMarkovNew();
			ViewBox displayBox = new ViewBox(song);
			VBox viewBox = displayBox.getViewBox();
			App.getRoot().setRight(viewBox);
			App.getRoot().setCenter(displayBox.getTextArea());
			 } catch (Exception e1) {
			 Exceptions.nullTextArea();
			 }
		});
		

		truncateItem.setOnAction(e -> {
			try {
				Truncate truncate = new Truncate(20);
				truncate.createFiles();
			} catch (Exception e1) {
				Exceptions.nullTextArea();
			}
		});

		showItem.setOnAction(e -> {
			clearRoot();
			File file = new File("outsideSource/results.txt");
			String text = ViewHelper.fileOpener(file);
			clearStatusBar();
			TextBox newViewBox = new TextBox();
			TextArea newArea = newViewBox.getTextBox();
			newArea.setText(text);
			App.getRoot().setCenter(newArea);
		});

		plotItem.setOnAction(e -> {
			try {
				Truncate truncate = new Truncate(20);
				truncate.createFiles();
				clearRoot();
				Plot plot = new Plot("truncateFiles/truncate");
				LineChart lineChart = plot.createGraph();
				App.getRoot().setCenter(lineChart);
				App.getRoot().setBottom(null);
			} catch (Exception e1) {
				Exceptions.nullTextArea();
			}
		});
		
		discussionItem.setOnAction(e->{
			clearRoot();
			File file= new File("discussion.txt");
			String discussion = ViewHelper.fileOpener(file);
			TextArea t = new TextArea();
			t.setText(discussion);
			App.getRoot().setCenter(t);
			
		});

		editMenu.getItems().addAll(copyItem, cutItem, deleteItem, pasteItem);

		actionMenu.getItems().addAll(markovItem, truncateItem, showItem,discussionItem, plotItem);
		menuBar.getMenus().addAll(fileMenu, viewMenu, editMenu, actionMenu);

	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	private void clearRoot() {
		App.getRoot().setCenter(null);
		App.getRoot().setBottom(null);
		App.getRoot().setLeft(null);
		App.getRoot().setRight(null);
	}

	private void clearStatusBar() {
		App.getStatusBox().wordCountField.clear();
		App.getStatusBox().fleschScoreField.clear();
		App.getStatusBox().sentenceCountField.clear();
	}
}
