package GamePackaje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionListener implements ActionListener
{
    private int row; //номер стоки
    private int cell; //номер столбца
    private GameButton button; // ссылка на кнопку

    public GameActionListener(int row, int cell, GameButton button)
    {
        this.row = row;
        this.cell = cell;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
