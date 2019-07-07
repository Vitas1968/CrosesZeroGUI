package GamePackaje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame
{
    static int dimension = 3; // размерность игрового поля
    static int cellSize = 150; // размер клетки в пикселях
    private char gameField[][]; // матрица игрового поля
    private GameButton gameBottons[]; // массив кнопок
    private Game game; // ссылка на игру
    static char nullSymbol = '\u0000'; //пустой символ

    public GameBoard(Game currentGame) throws HeadlessException
    {
        this.game = currentGame;
        initField();
    }
    // геттер возвращающий кнопку по указанной позиции


    public GameButton gameBotton(int position)
    {
        return gameBottons[position];
    }

    // инциализация(отрисовка) игрового поля
    private void initField()
    {//основные настройки окна игры
        setBounds(cellSize * dimension, cellSize * dimension, 400, 300);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // панель управления игрой
        JPanel controPanel = new JPanel();
        //кнопка - Новая игра
        JButton newGameButton = new JButton("Новая игра");
        // подключаем слушателя к кнопке
        newGameButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // очистка игрового поля
                emptyField();
            }
        });

        controPanel.setLayout(new BoxLayout(controPanel, BoxLayout.Y_AXIS));
        controPanel.add(newGameButton);
        controPanel.setSize(cellSize * dimension, 150);

        // панель игры
        JPanel gameFieldPanel = new JPanel();
        gameFieldPanel.setLayout(new GridLayout(dimension, dimension));
        gameFieldPanel.setSize(cellSize * dimension, cellSize * dimension);

        //инициализация матрицы/массива игры
        gameField = new char[dimension][dimension];
        gameBottons = new GameButton[dimension * dimension];

        for (int i = 0; i < (dimension * dimension); i++)
        {
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            gameBottons[i] = fieldButton;
        }

        //добавляем панели на главную панель
        getContentPane().add(controPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);
        setVisible(true);

    }


    //метод очистки игрового поля
    void emptyField()
    {
        for (int i = 0; i < (dimension * dimension); i++)
        {
            gameBottons[i].setText("");
            //вычисляем номер строки
            int x = i / GameBoard.dimension;
            //вычисляем номер столбца
            int y = i % GameBoard.dimension;
            gameField[x][y] = nullSymbol;

        }
    }

    // возвращает экзепляр игры
    public Game getGame ()
    {
        return game;
    }

    // проверка что в это поле можно ходить
    boolean isTurnable(int x, int y)
    {
        boolean result  = false;
        if (gameField [x][y]==nullSymbol) result=true;
        return  result;
    }
    // обновление поля после хода игрока
    void updateGameField(int x, int y)
    { // получаем из игры текущего игровка, а у него символ которым он играет
        // и ставим по координатам x,y
        gameField [x][y]= game.getCurrentPlayer().getPlayerSign();
    }

    // проверка победы

    boolean checkWin()
    {
        boolean result = false;
        char playerSymbol = getGame().getCurrentPlayer().getPlayerSign();

        if (checkWinDiagonals(playerSymbol) || checkWinLines(playerSymbol)) result = true;


        return result;
    }

    // проверка выигрышной комбинации по диагоналям
    private boolean checkWinDiagonals(char playerSymbol)
    {

        boolean leftRight, rightLeft,result=false;
        leftRight = true;
        rightLeft=true;
        for (int i = 0; i < dimension; i++)
        {
            leftRight &= (gameField[i][i] == playerSymbol);
            rightLeft &= (gameField[dimension-i-1][i] == playerSymbol);

        }
        if (leftRight || rightLeft) result =true;


        return  result;
    }
    // проверка выигрышной комбинации по строкам и столбцам
    private boolean checkWinLines(char playerSymbol)
    {
        boolean cols, rows, result=false;

        for (int col = 0; col < dimension ; col++)
        {
            cols = true;
            rows = true;

            for (int row = 0; row < dimension; row++)
            {
                cols&=gameField[row][row] == playerSymbol;
                rows&=gameField[row][row] == playerSymbol;

            }
            if (cols || rows)
            {
                result=true;
                break;
            }
            if (result) break;

        }
        return  result;
    }

    // проверка есть ли ещё пустые поля/клетки на игровом поле
     boolean isFull()
    { boolean result=true;

        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                if (gameField[i][j]==nullSymbol)
                {
                    result = false;
                    break;
                }

            }
            if (!result) break;
        }

        return result;
    }


}



