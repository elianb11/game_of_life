package fr.elian.gameoflife;

public class Calculator extends Thread {

	private int refreshTab[][];
	private Cell[][] gridTab;
	private boolean gameInProgress;
	private int gridSize;
	private int generation;
	GameScene gameScene;
	
	Calculator(GameScene gameScene) {
		gameInProgress = true;
		this.refreshTab = gameScene.getRefreshTab();
		this.gridTab = gameScene.getGridTab();
		this.gridSize = gameScene.getGridSize();
		this.generation = gameScene.getGeneration();
		this.gameScene = gameScene;
	}
	
	public synchronized void run() {
		int neighbours;
		while(gameInProgress) {
			try {
				this.wait();
			} catch(InterruptedException e) {
				System.out.println("Interruption");
				gameInProgress = false;
				return;
			}
			
			if(gameScene.isInExecution()) {

				System.out.println("calculations");
				
				gameScene.setGeneration(generation++);
				
//				application of the rules to create the refresh tab
				for(int i=0;i<gridSize;i++) {
					for(int j=0;j<gridSize;j++) {
						neighbours = 0;
						
						if(i+1 != gridSize && j+1 != gridSize) {
							if(gridTab[i+1][j+1].isAlive()) {neighbours++;}
						}
						if(j+1 != gridSize) {
							if(gridTab[i][j+1].isAlive()) {neighbours++;}
						}
						if(i+1 != gridSize) {
							if(gridTab[i+1][j].isAlive()) {neighbours++;}
						}
						if(i-1 >= 0 && j-1 >= 0) {
							if(gridTab[i-1][j-1].isAlive()) {neighbours++;}
						}
						if(i+1 != gridSize && j-1 >= 0) {
							if(gridTab[i+1][j-1].isAlive()) {neighbours++;}
						}
						if(j-1 >= 0) {
							if(gridTab[i][j-1].isAlive()) {neighbours++;}
						}
						if(i-1 >= 0) {
							if(gridTab[i-1][j].isAlive()) {neighbours++;}
						}
						if(i-1 >= 0 && j+1 != gridSize) {
							if(gridTab[i-1][j+1].isAlive()) {neighbours++;}
						}
						
						switch(neighbours) {
							case 2 :
								if(gridTab[i][j].isAlive()) {
									refreshTab[i][j] = 1;
								}
								break;
							case 3 :
								refreshTab[i][j] = 1;
								break;
							default :
								refreshTab[i][j] = 0;
						}	
					}
				}
				
			}
		}
	}
}
