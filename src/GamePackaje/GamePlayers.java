package GamePackaje;

public class GamePlayers
{
    private char playerSign;; // симол игрова крестик или нолик
    private boolean realPlayer=true; // призак игрок это или компьютер

    public GamePlayers(char playerSign, boolean realPlayer)
    {
        this.playerSign = playerSign;
        this.realPlayer = realPlayer;
    }

    public char getPlayerSign()
    {
        return playerSign;
    }

    public boolean isRealPlayer()
    {
        return realPlayer;
    }
}
