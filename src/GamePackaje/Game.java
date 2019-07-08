package GamePackaje;

import javax.swing.*;

public class Game
{
    private GameBoard board;  // ссылка на игровое поле
    private GamePlayers [] gamePlayers = new GamePlayers [2]; //массив мгроков
    private int playerTurn = 0; // индекс текущего игрока

    public Game()
    {
        this.board= new GameBoard(this);
    }

    public  void initGame()
    {
        gamePlayers [0] = new GamePlayers('X',true); // создаем игрока человека
        gamePlayers [1] = new GamePlayers('O',false); // создаем игрока компьютер
    }

    // метод передачи хода

    void passTurn()
    {
        if (playerTurn==0)
            playerTurn=1;
        else
            playerTurn=0;
    }

    //метод получения текущего игрова
    public GamePlayers getCurrentPlayer()
    {
        return gamePlayers[playerTurn];
    }

    // метод показа сообщения

    void showMessage(String messageText)
    {
        JOptionPane.showMessageDialog(board, messageText);
    }
}
