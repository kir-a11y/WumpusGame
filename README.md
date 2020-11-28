# WumpusGame
This project implemented minimax algorithm on a simple game called WumpusGame.

The Game rule is as the following:
The chess board can be 3*3, 6*6 and 9*9.
The Wumpus can be slayed by the hero, the hero can be killed by the mage, and the mage can be eaten up by the wumpus.
All charactors can step on a pit, and once they did that, the pit will swallow the charactor and disapear with it.

We can choose to play with AI or play with our friend. The game is always started with the blue side at the bottom, and if we play with AI, human always step first.

The AI side is implemented through minimax algorithm:
It will predict 3 steps further of the game, and will choose the one with the most beneficial step for AI, and worst occation for player.Trees are implemented for the algorithm.

Java version development: IntelliJ Idea
