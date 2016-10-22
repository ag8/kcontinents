package org.ag.simulator;

import org.ag.base.Cell;
import org.ag.config.Config;
import org.jetbrains.annotations.Contract;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    private static int DIM;
    private static Cell[][] grid;

    public Display() {
        DIM = Config.DIM;
        grid = new Cell[DIM][DIM];
        grid = SimulatorUtils.fillGrid(grid);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        g.drawRect(230,80,10,10);
//        g.setColor(Color.RED);
//        g.fillRect(230,80,10,10);

        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
//                g.setColor(getColor(grid[i][j].getValue()));
                Color c = getColor(grid[i][j].getValue());
                g.setColor(c);
                g.fillRect(i * 10, j * 10, 10, 10);
            }
        }
    }

    @Contract("_ -> !null")
    private Color getColor(int value) {
        int red = value * 255 / 99;
        if (red > 255) red = 255;
        return new Color(red, 0, red);
    }

    public void update(Cell[][] newGrid) {
        grid = newGrid.clone();
        repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(10 * DIM, 10 * DIM); // appropriate constants
    }
}
