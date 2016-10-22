package org.ag.simulator;

import org.ag.base.Cell;
import org.ag.config.Config;
import org.jetbrains.annotations.Contract;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    private static int DIM;
    private static Cell[][] grid;
    private static int[] currentPosition;
    private int darkness = 30;

    public Display() {
        DIM = Config.DIM;

        grid = new Cell[DIM][DIM];
        grid = SimulatorUtils.fillGrid(grid);

        currentPosition = new int[]{0, 0};


        this.setBackground(Color.BLACK);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        g.drawRect(230,80,10,10);
//        g.setColor(Color.RED);
//        g.fillRect(230,80,10,10);

        g.setColor(getColor(grid[currentPosition[0]][currentPosition[1]].getValue()));
        g.fillRect(currentPosition[0] * 10, currentPosition[1] * 10, 10, 10);
    }

    @Contract("_ -> !null")
    private Color getColor(int value) {
        int red = value * 255 / darkness;
        if (red > 255) red = 255;
        return new Color(red, 0, red);
    }

    public void update(Cell[][] newGrid, int[] newCurrentPosition) {
        grid = newGrid.clone();
        currentPosition = newCurrentPosition;
        paintImmediately(currentPosition[0] * 10, currentPosition[1] * 10, 10, 10);
    }

    public Dimension getPreferredSize() {
        return new Dimension(10 * DIM, 10 * DIM); // appropriate constants
    }
}
