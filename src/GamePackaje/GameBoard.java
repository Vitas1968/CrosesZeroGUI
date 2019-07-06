package GamePackaje;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame
{
    static int dimension = 3; // размерность игрового поля
    static int cellSize = 150; // размер клетки в пикселях
    private char gameField [][]; // матрица игрового поля
    private GameBotton gameBottons []; // массив кнопок
    private  Game game; // ссылка на игру

    public GameBoard(Game currentGame) throws HeadlessException
    {
        this.game = currentGame;
    }


}
