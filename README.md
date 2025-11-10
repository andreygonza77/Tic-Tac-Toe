# Tic-Tac-Toe (Client--Server) 🎮

A **Tic-Tac-Toe** implementation using a **client--server**
architecture.\
The server manages the entire game logic, while the clients send
commands and receive updates about the game state. ✨
Currently is in **italian**. 🇮🇹

## Project Structure 📁

    .
    ├── server-tris   # Server: game management, moves, state
    └── tris          # Client: command sending, board display

## How It Works ⚙️

-   The **server** accepts connections from two clients, initializes the
    game, and handles turn coordination.\
-   Each **client** sends moves to the server and receives text
    responses indicating:
    -   Current turn
    -   Updated board state
    -   Game result (win, loss, draw)

Communication occurs via TCP sockets.

## How to play 🎮

- Run the main thread in **server-tris** folder to start the Server
- Run the main thread in **tris** folder to connect a player
- With another runner repeat the last step
- When two clients are connected, the game starts

## Communication Protocol 🔌

The protocol is inspired by the model described here:\
https://gist.github.com/benve-meucci/c5c9d4fd0ec56c4355508d84e9cd171b


## Repository 🌐

https://github.com/andreygonza77/Tic-Tac-Toe
