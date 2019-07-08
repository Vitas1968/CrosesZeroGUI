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
           /* do {
                x = random.nextInt(GameBoard.dimension);
                y = random.nextInt(GameBoard.dimension);
            } while(!board.isTurnable(x, y));*/
        // ход с учетом рейтинга клетки
        int maxScoreFielX = -1;
        int maxScoreFielY = -1;
        int maxScore = 0;
        char map [][] = board.getGameField();
        char DOT_O = 'O';

        for (int i = 0; i < GameBoard.dimension; i++)
        {
            for (int j = 0; j < GameBoard.dimension; j++)
            { int fieldScore = 0;
                if (map[i][j]==GameBoard.nullSymbol)
                {
                    // слева сверху
                    if (i-1>=0 && j-1>=0 && map[i-1][j-1]==DOT_O)
                    {

                        fieldScore++;
                        // верх
                    }
                    if (i-1>=0  && map[i-1][j]==DOT_O)
                    {

                        fieldScore++;
                        //справа вверху
                    }
                    if (i-1>=0 && j+1<GameBoard.dimension && map[i-1][j+1]==DOT_O)
                    {

                        fieldScore++;
                        // слева
                    }
                    if (j-1>=0  && map[i][j-1]==DOT_O)
                    {

                        fieldScore++;
                        // справа
                    }
                    if (j+1<GameBoard.dimension  && map[i][j+1]==DOT_O)
                    {

                        fieldScore++;
                        // слева снизу
                    }
                    if (i+1<GameBoard.dimension && j-1>=0  && map[i+1][j-1]==DOT_O)
                    {
                        fieldScore++;
                        // снизу
                    }
                    if (i+1<GameBoard.dimension && map[i+1][j]==DOT_O)
                    {
                        fieldScore++;
                        // снизу cправа
                    }
                    if (i+1<GameBoard.dimension && j+1<GameBoard.dimension  && map[i+1][j+1]==DOT_O)
                    {
                        fieldScore++;
                    }
                }
                if (fieldScore>maxScore)
                {
                    maxScore=fieldScore;
                    maxScoreFielX = j;
                    maxScoreFielY = i;
                }
            }
        }
        if (maxScoreFielX != -1)
        {
            x=maxScoreFielX;
            y=maxScoreFielY;
        }
        if (x == -1)
        {
            do {
                x = random.nextInt(GameBoard.dimension);
                y = random.nextInt(GameBoard.dimension);
            } while(!board.isTurnable(x, y));
        }

            //  обновляем матрицу игры
        board.updateGameField(x, y);

        // обновить содержимое кнопки

        int cellIndex = GameBoard.dimension * x + y;
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
