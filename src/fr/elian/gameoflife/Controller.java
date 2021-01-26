package fr.elian.gameoflife;

import javafx.stage.Stage;

public class Controller {
	public static void setController(Stage primaryStage) {
		
//		creation of all instances of the classes that inherits from the JavaFX Scene class 
		
		MenuScene menuScene = new MenuScene();
		
		GameScene gameScene = new GameScene();
		
		HelpScene helpScene = new HelpScene();
		
		ValidatedLevelScene validatedLevelScene = new ValidatedLevelScene();

		InitGameScene initGameScene = new InitGameScene(gameScene);
		
		InitLoadGameScene initLoadGameScene = new InitLoadGameScene(gameScene);
		
		InitPeriodicityGameScene initPeriodicityGameScene = new InitPeriodicityGameScene(gameScene);
		
//		setting of the first scene when we start the game
		
		primaryStage.setScene(menuScene);
		primaryStage.show();
		
//		setting of all listeners necessary to switch scenes in the primary stage
		
		menuScene.getPlayButton().setOnAction(e -> {
			primaryStage.close();
			primaryStage.setScene(initGameScene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		});
		
		initGameScene.getPlayButton().setOnAction(e -> {
			gameScene.setBlankGrid();
			primaryStage.close();
			primaryStage.setScene(gameScene);
			primaryStage.setTitle("Game of Life by Elian - "+gameScene.getUsername()+"'s game");
			primaryStage.centerOnScreen();
			primaryStage.show();
		});
			
		gameScene.getMenuButton().setOnAction(e -> {
			gameScene.stopCalculations();
			gameScene.removeGrid();
			gameScene.setGeneration(0);
			primaryStage.close();
			primaryStage.setScene(menuScene);
			primaryStage.setTitle("Game of Life by Elian");
			primaryStage.centerOnScreen();
			primaryStage.show();
		});
		
		menuScene.getLoadButton().setOnAction(e -> {
			initLoadGameScene.refreshChoiceBox();
			primaryStage.close();
			primaryStage.setScene(initLoadGameScene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		});
		
		initLoadGameScene.getLoadButton().setOnAction(e -> {
			initLoadGameScene.getLoadButton().setVisible(false);
			gameScene.setPatternGrid();
			primaryStage.close();
			primaryStage.setScene(gameScene);
			primaryStage.setTitle("Game of Life by Elian - "+gameScene.getUsername()+" Pattern");
			primaryStage.centerOnScreen();
			primaryStage.show();
		});
		
		helpScene.getMenuButton().setOnAction(e -> {
			primaryStage.close();
			primaryStage.setScene(menuScene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		});
		
		menuScene.getHelpButton().setOnAction(e -> {
			primaryStage.close();
			primaryStage.setScene(helpScene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		});
		
		menuScene.getPeriodicityButton().setOnAction(e -> {
			primaryStage.close();
			primaryStage.setScene(initPeriodicityGameScene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		});
		
		initPeriodicityGameScene.getEasyButton().setOnAction(e -> {
			gameScene.setGridSize(30);
			gameScene.setBlankGrid();
			gameScene.setPeriodicityLevel("easy");
			primaryStage.close();
			primaryStage.setScene(gameScene);
			primaryStage.setTitle("Game of Life by Elian - Periodicity game / Easy level | Period 3");
			primaryStage.centerOnScreen();
			primaryStage.show();
		});
		
		initPeriodicityGameScene.getIntermediateButton().setOnAction(e -> {
			gameScene.setGridSize(30);
			gameScene.setBlankGrid();
			gameScene.setPeriodicityLevel("intermediate");
			primaryStage.close();
			primaryStage.setScene(gameScene);
			primaryStage.setTitle("Game of Life by Elian - Periodicity game / Intermediate level | Period 15");
			primaryStage.centerOnScreen();
			primaryStage.show();
		});
		
		initPeriodicityGameScene.getHardButton().setOnAction(e -> {
			gameScene.setGridSize(30);
			gameScene.setBlankGrid();
			gameScene.setPeriodicityLevel("hard");
			primaryStage.close();
			primaryStage.setScene(gameScene);
			primaryStage.setTitle("Game of Life by Elian - Periodicity game / Hard level | Period 40");
			primaryStage.centerOnScreen();
			primaryStage.show();
		});
		
		initPeriodicityGameScene.getLegendaryButton().setOnAction(e -> {
			gameScene.setGridSize(30);
			gameScene.setBlankGrid();
			gameScene.setPeriodicityLevel("legendary");
			primaryStage.close();
			primaryStage.setScene(gameScene);
			primaryStage.setTitle("Game of Life by Elian - Periodicity game / Legendary level | Period 60");
			primaryStage.centerOnScreen();
			primaryStage.show();
		});
		
		gameScene.getValidatedLevel().addListener(e -> {
			gameScene.stopCalculations();
			gameScene.removeGrid();
			gameScene.setGeneration(0);
			primaryStage.close();
			primaryStage.setScene(validatedLevelScene);
			primaryStage.setTitle("Game of Life by Elian");
			primaryStage.centerOnScreen();
			primaryStage.show();
		});
		
		validatedLevelScene.getMenuButton().setOnAction(e -> {
			primaryStage.close();
			primaryStage.setScene(menuScene);
			primaryStage.centerOnScreen();
			primaryStage.show();
		});
	}
}
