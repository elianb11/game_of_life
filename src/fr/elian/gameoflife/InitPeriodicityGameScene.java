package fr.elian.gameoflife;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InitPeriodicityGameScene extends Scene {
	
	private static VBox root = new VBox();;
	private static double width = 600;
	private static double height = 520;

	private Button easyButton;
	private Button intermediateButton;
	private Button hardButton;
	private Button legendaryButton;
	
	public InitPeriodicityGameScene(GameScene gameScene) {
		
		super(root, width, height);
		
		this.getStylesheets().add(getClass().getResource("/resources/css/initPeriodicityGame.css").toString());
		
		setButtons();
	}

	private void setButtons() {
		
		VBox infoLabelBox = new VBox();
		infoLabelBox.setId("infoLabelBox");
		Label infoLabel1 = new Label("Find and run the right pattern for the");
		Label infoLabel2 = new Label("period given by the level to achieve it !");
		infoLabelBox.getChildren().addAll(infoLabel1,infoLabel2);
		
		easyButton = new Button("EASY | PERIOD 3");
		easyButton.setId("easyButton");
		
		intermediateButton = new Button("INTERMEDIATE | PERIOD 15");
		intermediateButton.setId("intermediateButton");
		
		hardButton = new Button("HARD | PERIOD 40");
		hardButton.setId("hardButton");
		
		legendaryButton = new Button("LEGENDARY | PERIOD 60");
		legendaryButton.setId("legendaryButton");
		
		root.getChildren().addAll(infoLabelBox,easyButton,intermediateButton,hardButton,legendaryButton);
	}
	
	
	public Button getEasyButton() {
		return easyButton;
	}

	public void setEasyButton(Button easyButton) {
		this.easyButton = easyButton;
	}

	public Button getIntermediateButton() {
		return intermediateButton;
	}

	public void setIntermediateButton(Button intermediateButton) {
		this.intermediateButton = intermediateButton;
	}

	public Button getHardButton() {
		return hardButton;
	}

	public void setHardButton(Button hardButton) {
		this.hardButton = hardButton;
	}

	public Button getLegendaryButton() {
		return legendaryButton;
	}

	public void setLegendaryButton(Button legendaryButton) {
		this.legendaryButton = legendaryButton;
	}
}
