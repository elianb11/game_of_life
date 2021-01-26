package fr.elian.gameoflife;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MenuScene extends Scene {

	private static VBox menuRoot = new VBox();;
	private static double width = 500;
	private static double height = 600;
	private Button playButton;
	private Button loadButton;
	private Button periodicityButton;


	protected Button helpButton;

	public MenuScene() {
		
		super(menuRoot, width, height);
		
		this.getStylesheets().add(getClass().getResource("/resources/css/menu.css").toString());
		menuRoot.getStyleClass().add("menuRoot");
		
		ImageView menuTitle = new ImageView();
		menuTitle.getStyleClass().add("imageview");
		
		playButton = new Button("PLAY NEW GAME");
		loadButton = new Button("LOAD GAME");
		periodicityButton = new Button("PERIODICITY GAME");
		helpButton = new Button("HELP !");
		helpButton.setId("helpButton");
		
		periodicityButton.setId("periodicityButton");
		
		menuRoot.getChildren().addAll(menuTitle, playButton, loadButton, periodicityButton,helpButton);
	}
	
	
	public Button getHelpButton() {
		return helpButton;
	}
	
	public Button getPlayButton() {
		return playButton;
	}


	public Button getLoadButton() {
		return loadButton;
	}
	
	public Button getPeriodicityButton() {
		return periodicityButton;
	}
	
	
}

