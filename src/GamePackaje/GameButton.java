package GamePackaje;

import javax.swing.*;

public class GameButton extends JButton
{
    private int buttonIndex; //номер(индекс) кнопки
    private GameBoard board; // ссылка на игровое поле

    public GameButton(int buttonIndex, GameBoard board)
    {
        this.buttonIndex = buttonIndex;
        this.board = board;
        int rowNum = buttonIndex / GameBoard.dimension;
        int cellNum = buttonIndex % GameBoard.dimension;

        setSize(GameBoard.cellSize-5, GameBoard.cellSize-5);
        addActionListener(new GameActionListener(rowNum, cellNum,this));
    }

    public GameBoard getBoard()
    {
        return board;
    }
}
