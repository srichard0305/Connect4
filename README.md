# Connect4

## Game Design and Logic 

This game is the classic implementation of the connect 4 board game. The user plays against the 
computer using the minimax algorithm. Each game starts with the player starting first and choosing 
which column they want to place their piece. The game ends when either the player or computer 
make a connection of 4 of their pieces either vertically, horizontally, or diagonally.  
During the users turn an arrow hovering above the board will follow the users mouse cursor. The 
user can simply click which column they want to drop their piece, and the piece will fall into place. 
The computer will make its only decision based on the current state of the board.  
The game implements the libgdx game engine to draw all the sprites and handle the clicking events. 
All other code is implemented by me including the minimax algorithm.

## How to Run the Game

Included in the repo is an executable jar file that contains the game. Just download the jar file to run it. 
Double click to run if a JRE is installed and if the java scripts are set up in the system environment 
variables directory. 

If double clicking is not working, typing java -jar -Connect4-1.0.0.jar into the terminal should get it 
to run as well.

## Minimax Algorithm  

The minimax algorithm makes use of some helper functions: 
• An evaluateBoard function to determine the value of each player depending on the state of 
the board. This only happens if the board is in a terminal state. The function will sum all 
values of each player’s piece that occupies a space on the board. The values are 
determined by the evaluation board that is created at instantiation.  
• getValidLocations function returns a list of all valid locations the player can drop a piece 
based on the current board state. 
• checkWinningMove function determines if the board is in a terminal state. 

The main minimax algorithm works by maximizing the computer agents score while minimizing the 
opponent’s score. The algorithm works to a maximum depth of 4 or to a terminal state to first start 
evaluating the scores of each player’s pieces.  If neither are true, the function then determines if its 
state is maximizing or not. If maximizing, the function gets all valid positions for the AI to make a 
move and finds the spot with the largest value. It does this by recursively tracing the game state tree 
and returning the values. A move is chosen at random and if a move with a larger value is found, 
assuming the largest out of all tree states, that move is returned, or the random choice is returned if 
no value is larger. The algorithm works similarly in a minimizing state but returns the smallest value. 
The algorithm also implements alpha-beta pruning to reduce computation time. With alpha-beta 
pruning, alpha and beta are two values that are updated at each iteration of the game state. In 
maximizing, the alpha value is update based on the largest value found in that iteration. If a branch 
is found with an alpha value larger than the current beta value that branch is pruned as no value in 
that branch will be larger than the current value. The logic works the same way in a minimizing state 
but with the beta value being the smallest value found.  

In theory the algorithm should find the best move for the AI based on the current game state by 
evaluating all possible future game states. The values in the evaluation board are predetermined 
and are chosen based on the more strategic position of the board.  

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.
