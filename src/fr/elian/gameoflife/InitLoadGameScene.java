package fr.elian.gameoflife;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class InitLoadGameScene extends Scene {

	private static VBox root = new VBox();;
	private static double width = 800;
	private static double height = 800;
	
	private Button loadButton;
	private String fileName;
	
	private ChoiceBox<String> gameChoiceBox;
	ImageView gridView;
	


	public InitLoadGameScene(GameScene gameScene) {
		
			super(root, width, height);
			
			this.getStylesheets().add(getClass().getResource("/resources/css/initLoadGame.css").toString());
			
			System.out.println(getClass().getResource("/resources/css/initLoadGame.css").toString());
			
			Label gameChooseLabel = new Label("Select your game/pattern save:");
			gameChooseLabel.setId("gameChooseLabel");
			root.getChildren().add(gameChooseLabel);
			
			ObservableList<String> fileNames = FXCollections.observableArrayList();
			
			File file = new File("src/resources/text");
			
			String[] fileNameArray = file.list();
			
			for(String newFileName:fileNameArray) {
				newFileName = newFileName.replace(".txt","");
				fileNames.add(newFileName);
			}
			
			gameChoiceBox = new ChoiceBox<String>(fileNames);
			root.getChildren().add(gameChoiceBox);
			
			gridView = new ImageView();
			gridView.setFitHeight(550);
			gridView.setFitWidth(550);
			gridView.getStyleClass().add("imageview");
			root.getChildren().add(gridView);
			
			loadButton = new Button("LOAD THE GAME");
			loadButton.setVisible(false);
			root.getChildren().add(loadButton);
			
			gameChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
				public void changed(ObservableValue<?extends String> observable,String oldValue, String newValue) {
					if(newValue != null) {
						fileName = newValue;
						try {
							gridView.setImage(new Image(new FileInputStream(new File("src/resources/images/saves/"+fileName+".png"))));
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						
						gridView.setVisible(true);
						loadButton.setVisible(true);
						gameScene.setFileName(fileName);
					}
				}
			});
			
			
	}
	
	
	public void refreshChoiceBox() {
		gameChoiceBox.getItems().clear();
		
		ObservableList<String> fileNames = FXCollections.observableArrayList();
		
		File file = new File("src/resources/text");
		
		String[] fileNameArray = file.list();
		
		for(String fileName:fileNameArray) {
			fileName = fileName.replace(".txt","");
			fileNames.add(fileName);
		}
		gameChoiceBox.setItems(fileNames);
		gridView.setVisible(false);
	}
	
	public Button getLoadButton() {
		return loadButton;
	}

}


