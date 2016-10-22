package org.ag.simulator;

import org.ag.base.Cell;
import org.ag.config.Config;
import org.jetbrains.annotations.Contract;

import javax.swing.*;
import java.awt.*;

public class SimulatorUtils {
    static JFrame frame = new JFrame();
    static JPanel panel = new JPanel();
    static JTextArea output = new JTextArea();

    protected static Cell[][] fillGrid(Cell[][] toFill) {
        for (int i = 0; i < Simulator.DIM; i++) {
            for (int j = 0; j < Simulator.DIM; j++) {
                toFill[i][j] = new Cell(i, j);
            }
        }

        return toFill;
    }

    // Unwieldy to write out all the time
    @Contract(pure = true)
    protected static Cell get(Cell[][] grid, int[] coords) {
        return grid[coords[0]][coords[1]];
    }

    protected static int[] toPos(Cell neighbor) {
        return new int[]{neighbor.getRow(), neighbor.getCol()};
    }

    public static void display(Cell[][] grid, int q, int[] currentPosition) {
        output.setText(getDisplayText(grid, currentPosition));
        output.repaint();

        frame.setTitle("Kölner Continents: Iteration (" + (Config.ITERATIONS - q + 1) + "/" + Config.ITERATIONS + "), at cell (" + currentPosition[0] + "," + currentPosition[1] + ").");
    }

    private static String getDisplayText(Cell[][] grid, int[] current) {
        String s = "";

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                s += pad(grid[i][j].getValue() > 99 ? 99 : grid[i][j].getValue());
                if (current[0] == i && current[1] == j) {
                    s += "*";
                } else {
                    s += " ";
                }
            }
            s += "\n";
        }
        s += "___________________________";

        return s;
    }

    private static String pad(int value) {
        return String.format("%1$-" + 2 + "s", value + "");
    }

    public static void initDisplay() {
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Kölner Continents: Iteration (0/" + Config.ITERATIONS + "), at cell (0,0).");
        frame.setLocationRelativeTo(null);

        panel.setLayout(new FlowLayout());

        output = new JTextArea(12, 30);
        output.setFont(new Font("Courier New", Font.PLAIN, 10));
        panel.add(output);

        frame.pack();
        frame.setVisible(true);
    }
}
