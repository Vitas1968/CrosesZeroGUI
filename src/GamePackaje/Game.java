package GamePackaje;

public class Game
{
    private GameBoard board;  // ссылка на игровое поле
    private GamePlayers [] gamePlayers = GamePlayers [2]; //массив мгроков
    private int playerTurn = 0; // индекс текущего игрока

    public Game()
    {
        this.board= new GameBoard(this);
    }
}
