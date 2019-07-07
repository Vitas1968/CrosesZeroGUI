package GamePackaje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener
{
    private int row; //номер стоки
    private int cell; //номер столбца
    private GameButton button; // ссылка на кнопку
    private static Random random = new Random(); // для случайного хода

    public GameActionListener(int row, int cell, GameButton button)
    {
        this.row = row;
        this.cell = cell;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        GameBoard board = button.getBoard();
        if (board.isTurnable(row,cell))
        {
            updateByPlayersDate(board);
            if (board.isFull())
            {
                board.getGame().showMessage("Ничья");
                board.emptyField();
            } else {
                updateByAIDate(board);
            }

        } else {board.getGame().showMessage("Некорректный ход");}

    }

    // ход человека
    private void updateByPlayersDate(GameBoard board)
    {  // обновляем матрицу игры
        board.updateGameField(row, cell);
        // обновляем содержимое кнопки
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        //проверка победы
        if (board.checkWin())
        {
            board.getGame().showMessage("You Win!");
        //чистим поле
            board.emptyField();
        } else{
            board.getGame().passTurn();
        }
    }
// ход компьютера
    private void updateByAIDate(GameBoard board)
    {
        int x = -1;
        int y = -1;
            do {
                x = random.nextInt(GameBoard.dimension);
                y = random.nextInt(GameBoard.dimension);
            } while(!board.isTurnable(x, y));

            //  обновляем матрицу игры
        board.updateGameField(x, y);

        // обновить собержимое кнопки

        int cellIndex = GameBoard.dimension * x+y;
        board.gameBotton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        //проверка победы
        if (board.checkWin())
        {
            board.getGame().showMessage("AI Win!");
            //чистим поле
            board.emptyField();
        } else{
            board.getGame().passTurn();
        }
    }
}
