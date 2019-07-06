package GamePackaje;

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
        gamePlayers [0] = new GamePlayers('x',true); // создаем игрока человека
        gamePlayers [1] = new GamePlayers('o',false); // создаем игрока компьютер
    }
}
