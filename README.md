# WumpusGame
This project implemented the minimax algorithm on a simple game called WumpusGame.

The game rule is as the following:
The chess board size can be 3*3, 6*6, and 9*9.
The Wumpus can be slain by the hero, the hero can be killed by the mage, and the mage can be eaten up by the wumpus.
All characters can step on a pit, and once they did that, the pit will swallow the character and disappear with it.

We can choose to play with AI or play with our friends. The game is always started with the blue side at the bottom, and if we play with AI, human always steps first.

The AI side is implemented through a minimax algorithm:
It will predict 3 steps further of the game and will choose the one with the most beneficial step for AI, and the worst occasion for the player. Trees are implemented for the algorithm.
