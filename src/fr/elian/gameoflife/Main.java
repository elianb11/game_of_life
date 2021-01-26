package fr.elian.gameoflife;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

	
	@Override
	public void start(Stage primaryStage) {
		try {
			
//			set of the window icon and title
			
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image(getClass().getResource("/resources/images/icon.png").toString()));
			primaryStage.setTitle("Game of Life by Elian");
			
//			set of the primary stage controller that manages all the listeners
			
			Controller.setController(primaryStage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
