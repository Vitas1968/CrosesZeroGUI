package GamePackaje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame
{
    static int dimension = 3; // размерность игрового поля
    static int cellSize = 150; // размер клетки в пикселях
    private char gameField [][]; // матрица игрового поля
    private GameButton gameBottons []; // массив кнопок
    private  Game game; // ссылка на игру

    public GameBoard(Game currentGame) throws HeadlessException
    {
        this.game = currentGame;
        initField();
    }

    // инциализация(отрисовка) игрового поля
    private void  initField()
    {//основные настройки окна игры
      setBounds(cellSize*dimension, cellSize*dimension, 400,300);
      setTitle("Крестики-нолики");
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setLayout(new BorderLayout());
      // панель управления игрой
        JOptionPane controPanel = new JOptionPane();
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
        controPanel.setSize(cellSize*dimension,150);

        // панель игры
        JPanel gameFieldPanel = new JPanel();
        gameFieldPanel.setLayout(new GridLayout(dimension,dimension));
        gameFieldPanel.setSize(cellSize*dimension,cellSize*dimension);

        //инициализация матрицы/массива игры
        gameField=new char[dimension][dimension];
        gameBottons = new GameButton[dimension*dimension];

        for (int i = 0; i < (dimension*dimension) ; i++)
        {
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            gameBottons [i]=fieldButton;
        }

        //добавляем панели на главную панель
        getContentPane().add(controPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);
        setVisible(true);

        //метод очистки игрового поля

    }


}
