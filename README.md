# Chinese Checkers AI Game

This is a Java-based implementation of the Chinese Checkers game with a graphical user interface (GUI). The game allows you to play against an AI opponent using the Alpha-Beta Pruning algorithm. The objective of the game is to move all your marbles to the opposite point of the star-shaped game board. The first player to achieve this wins the game.

## Game Rules

1. The game is designed for two players. However, in this project, we will implement a 2-player version.
2. The game board is a star with six edges, each containing a different colored set of 10 marbles.
3. Player 1 owns the Green, Blue, and Purple sets, while Player 2 owns the Red, Orange, and Yellow sets.
4. To win, a player must move all their marbles to the opposite side of the star, replacing the opponent's marbles.

## How to Play

1. Players take turns moving their pieces across the board.
2. During a player's turn, they can move their piece to any adjacent empty hole.
3. A player's piece can also hop over an adjacent piece into an empty hole. The piece can be their own colored piece or the opponent's piece. They can continue hopping over subsequent pieces during that turn in any direction, as long as there are empty holes on the other side of those pieces.

## Game Features

- Human vs. Computer Mode: The game is designed to be played in Human vs. Computer mode. You can play against the AI opponent.
- Alpha-Beta Pruning Algorithm: The AI opponent utilizes the Alpha-Beta Pruning algorithm to decide on its moves. This algorithm helps in efficiently searching the game tree and making optimal decisions.
- Game Controller: The game includes a game controller that organizes the game by switching roles between the two players. It receives the user's play, changes the game board, and declares the end of the game when a player wins.
- Knowledge Representation: The game state is represented using suitable data structures to store the game board, player positions, and other relevant information. This allows for easy manipulation and tracking of the game state.
- Utility Function: An adequate utility function is provided to evaluate the current game state with respect to a given player. The utility function assigns a positive value to good states and a negative value to bad states. The larger the utility, the better the state. The utility function for Player A is typically the negative utility function for Player B.
- Min-Max Implementation: The game includes a basic Min-Max implementation that can be used to determine the AI opponent's moves. The Min-Max algorithm explores the game tree by considering all possible moves and their outcomes to make optimal decisions.
- Difficulty Levels: The game supports different difficulty levels (Easy, Medium, Hard) characterized by the depth of the Min-Max algorithm. The depth determines the level of strategic thinking and difficulty of the AI opponent. For example, Easy may have a depth of 1, Medium a depth of 3, and Hard a depth of 5.

## Team

This Chinese Checkers AI Game project was developed by a team of passionate programmers and game enthusiasts:

- [Khaled Ashraf](https://github.com/KhaledAshrafH).
- [Noura Ashraf](https://github.com/NouraAshraff).
- [Samaa Khalifa](https://github.com/SamaaKhalifa).
- [Ahmed Sayed](https://github.com/AhmedSayed117).
- [Ahmed Ebarhim](https://github.com/Ahmed-Ibrahim-30).

We would like to express our gratitude to the team for their hard work and dedication in bringing this project to life.

## Launching the Game

1. Clone the repository or download the project files.
2. Open the project in your preferred Java development environment (IDE).
3. Compile and run the project.
4. Select the desired difficulty level (Easy, Medium, Hard) before making the first move. The higher the difficulty level, the more challenging the AI opponent.
5. Play the game by taking turns with the AI opponent, following the rules mentioned above.

## Game Components

The project includes the following components:

1. GameBoard: Represents the game board and provides methods to initialize, update, and display the board. The game board is a star-shaped grid with colored marbles.
2. Player: Represents a player and stores their colored sets of marbles. Each player has three sets of colored marbles.
3. Move: Represents a move made by a player and stores the source and destination positions. The move class helps in validating and applying moves.
4. GameController: Controls the game flow, manages player turns, receives user input, and handles the AI opponent's moves. The game controller orchestrates the game and updates the game state accordingly.
5. AIPlayer: Implements the AI opponent using the Alpha-Beta Pruning algorithm for decision making. The AI opponent evaluates the game state, explores the game tree, and selects the best move based on the utility function and the Min-Max algorithm.

## Contributions

We welcome contributions to enhance the game and its features. If you would like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make the necessary changes and additions.
4. Test your changes thoroughly.
5. Commit and push your changes to your forked repository.
6. Submit a pull request with a detailed description of your changes.

Please note that all contributions will be reviewed and evaluated before merging them into the main project.

## License

This project is licensed under the [MIT License](LICENSE.md). You are free to use, modify, and distribute the code as permitted by the license.

## Acknowledgments

We would like to express our gratitude to the following resources:

- [Wikipedia](https://en.wikipedia.org/wiki/Chinese_checkers) for providing information on Chinese Checkers game rules and strategies.
- [Stack Overflow](https://stackoverflow.com/) for the vast community knowledge and support.

These resources have been invaluable in our journey to create this Chinese Checkers AI Game.
