package fr.elian.gameoflife;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class HelpScene extends Scene{

	private static VBox root = new VBox();;
	private static double width = 1500;
	private static double height = 900;
	
	Button menuButton;
	
	
	public HelpScene() {
			
			super(root, width, height);
			
			this.getStylesheets().add(getClass().getResource("/resources/css/help.css").toString());
			
			ImageView helpView = new ImageView();
			helpView.getStyleClass().add("imageview");
			root.getChildren().add(helpView);
			
			menuButton = new Button("MENU");
			root.getChildren().add(menuButton);
	
		}
	
	
	public Button getMenuButton() {
		return menuButton;
	}
}
