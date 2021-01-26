# game_of_life
Game of Life implementation in Java with the javaFX GUI tool. Several features have been added.

The Game of Life is a cellular automation, introduced by John Horton Conway in 1970, and considered as a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves.

The rules are pretty simple. The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, live or dead. Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:
-	Any live cell with fewer than two live neighbours dies, as if by underpopulation.
-	Any live cell with two or three live neighbours lives on to the next generation.
-	Any live cell with more than three live neighbours dies, as if by overpopulation.
-	Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

The initial pattern constitutes the first population of the system and is determined by the player. The rules continue to be applied repeatedly to create further generations without any action of the player. It is the reason why this game is considered as a zero-player game. 

Many people have dedicated their entire lives to this game trying to understand the complexity that certain cell structures can have which may seem simple at first glance. Even today, interesting cell structures are found and continue to question scientists.

The goal of the project is to adapt the original Game of Life to a one-player version. To do this, I have implemented on the game some functionality as the possibility for the player to save and load his game in order to have not to set again the cell structure (for example if the structure is complex and long to set). 

I have also added a little Periodicity game with 4 levels of difficulty which allows the player to search and find special structures with special periods given.

When the structure reached is found and when it is the good structure, my program detects it and a congratulation message appears. In addition, the user can have some help to understand how this game works and what are the rules.

The user can save the game when he wants in the game scene and a new save will be created. He also can go back to the menu when he wants, and the game scene are going to be reset in order to be able to create a new one with eventually a different size, a different username or time per generation.

Before this window appears, the player can select the grid size he wants and the time between generations via sliders. He can also insert his username.

When you save a game pattern, it saves in first time a “.png” image of the grid in order to have a preview of the pattern when you are going to load it and it sets a name (with the username of the game) to the new save. All the information that the game needs to load the grid are contained in a text file with these information:
-	Name to give to the window
-	Grid size
-	Time per generation
-	Series of 1 (living cells) and 0 (dead cells)
 
You can then load the save via the load functionality and the following view is proposed when you select one.

Several interesting cell structures are already available in the resources and accessible when the game is launched like the one above.
