In this Project , I created an engine for generating explorable worlds.
This is a  2D tile-based game. By “tile-based”, we mean the world for your game will consist of a 2D grid of tiles. By “game” we mean that the player will be able to walk around and interact with the world. Your game will have an overhead perspective. 


Skeleton Code Structure
The skeleton code contains two keypackages: byog.TileEngine and byog.Core. 
byog.TileEngine provides some basic methods for rendering, as well as basic code structure for tiles, and contains:
	•TERenderer.java - contains rendering-related methods.
	•TETile.java - the type used for representing tiles in the world.
	•Tileset.java - a library of provided tiles.

The byog.Core package comes with the following classes:
	•RandomUtils.java - Handy utility methods for doing randomness related things.
	•Main.java - How the player starts the game. Reads command line arguments and calls the appropriate function in Game.java.
	•Game.java - Contains the two methods that allow playing of your game.


byog.Core.Game provides two methods for playing your game. The first is public TETile[][] playWithInputString(String input). This method takes as input a series of keyboard inputs, and returns a 2D TETile array representing the state of the universe after processing all the key presses provided in input .
The second is public void playWithKeyboard(). This method takes input from the keyboard, and draws the result of each keypress to the screen.


When Core.Game.playWithKeyboard() method is run, game will  display a Main Menu that provides at the option to start a new game, load a previously saved game, and quit the game. The Main Menu should be navigable only using the keyboard, using N for “new game”, L for “load game”, and Q for quit. 

After pressing N for “new game”, the user should be prompted to enter a “random seed”, which is an integer of their choosing. This integer will be used to generate the world randomly . After the user has pressed the final number in their seed, they should press S to tell the game that they’ve entered the entire seed that they want.

The player must be able to control some sort of entity that can moved around using the W, A, S, and D keys. 
