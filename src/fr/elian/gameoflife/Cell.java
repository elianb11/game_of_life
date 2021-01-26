package fr.elian.gameoflife;

import javafx.scene.Parent;
import javafx.scene.shape.Rectangle;
import javafx.beans.property.SimpleDoubleProperty;

public class Cell extends Parent {

	private int row;
	private int column;
	private SimpleDoubleProperty height;
	private SimpleDoubleProperty width;
	
	private boolean alive;
	private Rectangle cell;
	
	public Cell(int row, int column, double size, GameScene gameScene) {
		
		this.getStylesheets().add(getClass().getResource("/resources/css/cell.css").toString());
		
		this.row = row;
		this.column = column;
		height = new SimpleDoubleProperty();
		width = new SimpleDoubleProperty();
		
		height.set(size);
		width.set(size);
		width.bind(height);
		
		Rectangle background = new Rectangle(0,0,size,size);
		background.setId("background");
		
		double background2Size = size/15;
		Rectangle background2 = new Rectangle(background2Size,background2Size,size-background2Size,size-background2Size);
		background2.setId("background2");
		
		background.heightProperty().bind(height);
		background.widthProperty().bind(width);
	
		this.getChildren().addAll(background,background2);
		
		alive = false;
		cell = new Rectangle(0,0,size,size);
		cell.setId("cellDeath");
		
		cell.heightProperty().bind(height);
		cell.widthProperty().bind(width);
		
		this.getChildren().add(cell);
		
//		configuration of the modifications to add to the grid when mouse actions are detected
		
		this.setOnMouseClicked(e -> {
			if(!gameScene.isInExecution()) {
				if(alive) {
					this.death();
					gameScene.getRefreshTab()[getRow()][getColumn()] = 0;
				}
				else {
					this.birth();
					gameScene.getRefreshTab()[getRow()][getColumn()] = 1;
				}
			}
		});
		
		this.setOnMouseEntered(e -> {
			if(!gameScene.isInExecution()) {
				background2.setId("background2OnMouse");
			}
		});
		
		this.setOnMouseExited(e -> {
			if(!gameScene.isInExecution()) {
				background2.setId("background2");
			}
		});
		
	}
	
	public void birth() {
		cell.setId("cellAlive");
		alive = true;
	}
	
	public void death() {
		cell.setId("cellDeath");		
		alive = false;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public SimpleDoubleProperty getHeightProperty() {
		return height;
	}
	
	public SimpleDoubleProperty getWidthProperty() {
		return width;
	}
	
	public boolean isAlive() {
		return alive;
	}
}
