package fr.elian.gameoflife;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ValidatedLevelScene extends Scene {

	private static VBox root = new VBox();;
	private static double width = 600;
	private static double height = 500;

	private Button menuButton;

	public ValidatedLevelScene() {
		
		super(root, width, height);
		
		this.getStylesheets().add(getClass().getResource("/resources/css/initPeriodicityGame.css").toString());
		
		Label infoLabel = new Label("You have successfully validated the level\n   with the right period, congratulations !");
		
		ImageView congratulationsImage = new ImageView();
		congratulationsImage.setFitHeight(200);
		congratulationsImage.setFitWidth(200);
		congratulationsImage.getStyleClass().add("imageview");
		
		menuButton = new Button("MENU");
		menuButton.setId("menuButton");
		
		root.getChildren().addAll(infoLabel,congratulationsImage,menuButton);
	}
	
	public Button getMenuButton() {
		return menuButton;
	}
}
