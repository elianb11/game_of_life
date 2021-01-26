package fr.elian.gameoflife;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.imageio.ImageIO;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import javafx.scene.control.Alert;

public class GameScene extends Scene{

	private static BorderPane root = new BorderPane();;
	private static double width = 940;
	private static double height = 1000;
	
	private boolean execution;
	private Timeline refresh;
	private Calculator calculations;
	private int [][] refreshTab;
	private Cell[][] gridTab;
	private GridPane grid;
	
	private ArrayList<String> periodList;
	private int actualPeriod;
	private String periodicityLevel;
	private SimpleBooleanProperty validatedLevel;

	private int generation;
	private Label generationLabel;
	
	private Button menuButton;
	private Button startButton;
	private Button savePatternButton;
	
	private int gridSize;
	private String username;
	private int generationTime;
	private String fileName;
	

	public GameScene() {
		
		super(root, width, height);
		
		this.getStylesheets().add(getClass().getResource("/resources/css/game.css").toString());
		
		setBottomBox();
		
		validatedLevel = new SimpleBooleanProperty();
		
//		default values
		execution = false;
		generation = 0;
		actualPeriod = 0;
		gridSize = 35;
		generationTime = 200;
		username = "unknown";
		periodicityLevel = "";
	}
	
	
	public void setBottomBox() {
		HBox bottomBox = new HBox();
		bottomBox.getStyleClass().add("bottomBox");
		
		menuButton = new Button("MENU");
		menuButton.setId("menuButton");
		
		Label spaceLabel = new Label("");
		spaceLabel.setId("spaceLabel");
		
		savePatternButton = new Button("SAVE GAME");
		savePatternButton.setId("savePatternButton");
		
		Label spaceLabel2 = new Label("");
		spaceLabel2.setId("spaceLabel");
		
		startButton = new Button("START !");
		startButton.setId("startButton");
	
		Label generationTextLabel = new Label("GENERATION :");
		generationTextLabel.setId("generationTextLabel");
		generationLabel = new Label("0");
		
		bottomBox.getChildren().addAll(menuButton, spaceLabel, savePatternButton, spaceLabel2, startButton, generationTextLabel, generationLabel);
		root.setBottom(bottomBox);
		
//		listener that changes the state of the start button when it is pressed
		
		startButton.setOnAction(e -> {
			if(!execution) {
				startButton.setText("STOP !");
				startButton.setId("stopButton");
				execution = true;
			}
			else {
				startButton.setText("START !");
				startButton.setId("startButton");
				execution = false;
			}
		});		
		
		menuButton.setOnAction(e -> {
			startButton.setText("START !");
			startButton.setId("startButton");
			execution = false;
		});
		
//		listener that calls the two functions that the program needs to save the grid
		
		savePatternButton.setOnAction(e -> {
			try {
				writePattern();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			saveAsPng();
			new Alert(Alert.AlertType.INFORMATION, "The game saving has been completed successfully !").showAndWait();
		});
	}
	
//	function that is called when a new game is launched
	public void setBlankGrid() {
		grid = new GridPane();
		grid.setHgap(0);
		grid.setVgap(0);
		grid.setGridLinesVisible(false);
		grid.getStyleClass().add("grid");
		root.setTop(grid);
		
		gridTab = new Cell[gridSize][gridSize];
		refreshTab = new int[gridSize][gridSize];
		
//		setting of the cell size for the grid size given in order to have a grid in good proportions with the window
		double cellSize = (double)(900-gridSize)/(double)gridSize;
		
//		creations of all cells and insertion of these into the gridPane
		for(int i=0;i<gridSize;i++) {
			for(int j=0;j<gridSize;j++) {
				gridTab[i][j] = new Cell(i,j,cellSize, this);
				grid.add(gridTab[i][j],i,j);
			}
		}
		
		grid.setOnMouseEntered(e -> {
			if(!execution) {
				grid.setId("gridInExecution");
			}
			else {
				grid.setId("gridInBreak");
			}
		});
		
		setCalculator();
	}
	
//	function that is called when the user want to load a existing game
	public void setPatternGrid() {
		grid = new GridPane();
		grid.setHgap(0);
		grid.setVgap(0);
		grid.setGridLinesVisible(false);
		grid.getStyleClass().add("grid");
		root.setTop(grid);
		
		try {
			readPattern();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		grid.setOnMouseEntered(e -> {
			if(!execution) {
				grid.setId("gridInExecution");
			}
			else {
				grid.setId("gridInBreak");
			}
		});
		
		setCalculator();
	}
	
//	function that contains an indefinite Timeline that allows the game to always be ready to refresh the grid by calling the Calculator class
//	this function also calculate a period for the periodicity game
	public void setCalculator() {
		calculations = new Calculator(this);
		calculations.start();
		periodList = new ArrayList<String>();
		
		refresh = new Timeline(new KeyFrame(Duration.millis(generationTime),new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				
//				refresh of the number of generations
				generationLabel.setText(""+generation+"");
				String period = new String();
				
//				refresh of the cells of the grid
				for(int i=0;i<gridSize;i++) {
					for(int j=0;j<gridSize;j++) {
						
						if(refreshTab[i][j] == 1) {
							if(!gridTab[i][j].isAlive()) {
								gridTab[i][j].birth();
							}
							period += "1";
						}
						
						if(refreshTab[i][j] == 0) {
							if(gridTab[i][j].isAlive()) {
								gridTab[i][j].death();
							}
							period += "0";
						}
					}
				}
				
//				calculations of the period when the game is started
				if(execution == true) {
					periodList.add(period);
					if(period.equals(periodList.get(0)) && periodList.size() > 1) {
						actualPeriod = periodList.size()-1;
						periodList.clear();
						System.out.println("period = "+actualPeriod);
					}
				}
				else {
					periodList.clear();
				}
				
//				for 4 period given for the 4 levels of the periodicity game this function says whether the level is validated or not
				if(periodicityLevel.equals("easy")) {
					if(actualPeriod == 3) {
						validatedLevel.setValue(true);
					}
				}
				if(periodicityLevel.equals("intermediate")) {
					if(actualPeriod == 15) {
						validatedLevel.setValue(true);
					}
				}
				if(periodicityLevel.equals("hard")) {
					if(actualPeriod == 40) {
						validatedLevel.setValue(true);
					}
				}
				if(periodicityLevel.equals("legendary")) {
					if(actualPeriod == 60) {
						validatedLevel.setValue(true);
					}
				}
				
				synchronized(calculations) {
					calculations.notify();
				}
			}
		}));
		
		refresh.setCycleCount(Timeline.INDEFINITE);
		refresh.play();	
	}
	
//	function that stop the Timeline and clear the grid that is called when the backMenu button is pressed
	public void removeGrid() {
		refresh.stop();
		grid.getChildren().clear();
	}
	
//	function necessary to load a game and create again the saved grid
	public void readPattern() throws FileNotFoundException {
		
		Scanner myReader = new Scanner(new File("src/resources/text/"+fileName+".txt"));
		
		username = myReader.nextLine();
		System.out.println("username is : "+username);
		gridSize = myReader.nextInt();
		System.out.println("gridSize is : "+gridSize);
		generationTime = myReader.nextInt();
		System.out.println("GT is : "+generationTime);
		myReader.nextLine();
		gridTab = new Cell[gridSize][gridSize];
		refreshTab = new int[gridSize][gridSize];
		
		double cellSize = (double)(900-gridSize)/(double)gridSize;
		
		for(int i=0;i<gridSize;i++) {
			String data = myReader.nextLine();
			String[] values = data.split(";");
			for(int j=0;j<gridSize;j++) {
				gridTab[i][j] = new Cell(i,j,cellSize, this);
				if(values[j].equals("1")) {
					gridTab[i][j].birth();
					refreshTab[i][j] = 1;
				}
				grid.add(gridTab[i][j],i,j);
			}
		}
	}
	
//	function that save the game by creating a new .txt file with all information that are necessary to load it after
	public void writePattern() throws IOException {
		Date today = new Date();
		fileName = username+System.currentTimeMillis();
		FileWriter f = new FileWriter(new File("src/resources/text/"+fileName+".txt"));
		f.write(username+"'s game "+today.toString()+"\n"+gridSize+"\n"+generationTime);
		for(int i=0;i<gridSize;i++) {
			f.write("\n");
			for(int j=0;j<gridSize;j++) {
				if(gridTab[i][j].isAlive()) {
					f.write("1;");
				}
				else {
					f.write("0;");
				}
			}
		}
		f.close();
	}
	
//	function that save a .png file to have an overview for the loading
	public void saveAsPng() {
		WritableImage image = grid.snapshot(new SnapshotParameters(), null);
		
		File file = new File("src/resources/images/saves/"+fileName+".png");
		
	    try {
	        ImageIO.write(SwingFXUtils.fromFXImage(image,null),"png",file);
	    } catch (IOException e) {
	    	
	    }
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	public void setGenerationTime(int newGenerationTime){
		generationTime = newGenerationTime; 
	}
	
	
	public void setUsername(String newUsername){
		username = newUsername; 
	}
	
	
	public void setGridSize(int newGridSize){
		gridSize = newGridSize; 
	}
	
	public Button getMenuButton() {
		return menuButton;
	}


	public String getUsername() {
		return username;
	}
	
	public int[][] getRefreshTab() {
		return refreshTab;
	}


	public void setRefreshTab(int[][] refreshTab) {
		this.refreshTab = refreshTab;
	}


	public Cell[][] getGridTab() {
		return gridTab;
	}
	

	public int getGeneration() {
		return generation;
	}


	public void setGeneration(int generation) {
		this.generation = generation;
	}


	public int getGridSize() {
		return gridSize;
	}

	
	public boolean isInExecution() {
		return execution;
	}
	
	
	public void stopCalculations() {
		execution = false;
		startButton.setText("START !");
		startButton.setId("startButton");
	}
	
	public String getPeriodicityLevel() {
		return periodicityLevel;
	}

	public void setPeriodicityLevel(String periodicityLevel) {
		this.periodicityLevel = periodicityLevel;
	}
	
	public SimpleBooleanProperty getValidatedLevel() {
		return validatedLevel;
	}
}
