package fr.elian.gameoflife;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class InitGameScene extends Scene {

	private static VBox root = new VBox();;
	private static double width = 1000;
	private static double height = 600;
	
	private Button playButton;
	

	public InitGameScene(GameScene gameScene) {
		
			super(root, width, height);
			
			this.getStylesheets().add(getClass().getResource("/resources/css/initGame.css").toString());
				
			setUsernameTextFieldBox(gameScene);
			
			setGridSizeSliderBox(gameScene);
			
			setGenerationTimeSliderBox(gameScene);

			setPlayButton();	
	}
	
	
	private void setUsernameTextFieldBox(GameScene gameScene) {
		
		HBox boxUsername = new HBox();
		Label usernameLabel = new Label("Username : ");
		TextField usernameTextField = new TextField();
		boxUsername.getChildren().addAll(usernameLabel, usernameTextField);
		
		usernameTextField.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<?extends String>observable, String oldValue, String newValue) {
				gameScene.setUsername(usernameTextField.getText());
			}
		});
		
		root.getChildren().add(boxUsername);
	}
	
	
	private void setGridSizeSliderBox(GameScene gameScene) {
		
		HBox boxGridSize = new HBox();
		Label gridSizeLabel = new Label("Grid size : ");
		Label gridSizeInfoLabel = new Label("20 cells x 20 cells");
		gridSizeInfoLabel.setId("infoLabel");
		boxGridSize.getChildren().addAll(gridSizeLabel, gridSizeInfoLabel);
		
		HBox boxGridSize2 = new HBox();
		Slider gridSizeSlider = new Slider(0, 100, 20);
		boxGridSize2.getChildren().add(gridSizeSlider);
		
		gameScene.setGridSize(20);
		
		gridSizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<?extends Number>observable, Number oldValue, Number newValue) {
				if(newValue.intValue()<=1) {
					gridSizeInfoLabel.setText("1 cell x 1 cell (min)");
					if(newValue.intValue()==0) { 
						gridSizeSlider.increment();
					}
					gameScene.setGridSize(1);
				}
				else
				{
					gridSizeInfoLabel.setText(""+newValue.intValue()+" cells x "+newValue.intValue()+" cells");
					gameScene.setGridSize(newValue.intValue());

				}
			}
		});
		
		root.getChildren().addAll(boxGridSize, boxGridSize2);
	}
	
	
	private void setGenerationTimeSliderBox(GameScene gameScene) {
		
		HBox boxGenerationTime = new HBox();
		Label generationTimeLabel = new Label("Time per generation : ");
		Label generationTimeInfoLabel = new Label("500 ms");
		generationTimeInfoLabel.setId("infoLabel");
		boxGenerationTime.getChildren().addAll(generationTimeLabel, generationTimeInfoLabel);
		
		HBox boxGenerationTime2 = new HBox();
		Slider generationTimeSlider = new Slider(0, 2000, 500);
		generationTimeSlider.setId("generationTimeSlider");
		boxGenerationTime2.getChildren().add(generationTimeSlider);
		
		gameScene.setGenerationTime(500);
		
		generationTimeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			
			public void changed(ObservableValue<?extends Number>observable, Number oldValue, Number newValue) {
				if(newValue.intValue()<=100) {
					generationTimeInfoLabel.setText("100 ms (min)");
					if(newValue.intValue() == 0) { 
						generationTimeSlider.increment();
					}
					gameScene.setGenerationTime(100);
				}
				else
				{
					generationTimeInfoLabel.setText(""+newValue.intValue()+" ms");
					gameScene.setGenerationTime(newValue.intValue());
				}
			}
		});
		
		root.getChildren().addAll(boxGenerationTime, boxGenerationTime2);
	}
	
	
	private void setPlayButton() {
		StackPane boxButton = new StackPane();
		playButton = new Button("PLAY");
		boxButton.getChildren().add(playButton);
		
		root.getChildren().add(boxButton);
	}
	
	public Button getPlayButton() {
		return playButton;
	}

}
