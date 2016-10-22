package org.ag.simulator;

import org.ag.base.Cell;
import org.ag.config.Config;

public class Simulator {
    protected static int DIM;
    protected static int ITER;
    static int[] currentPosition = {0, 0};
    static int dir = 0;
    private static Cell[][] grid;

    public static void run() throws InterruptedException {
        init();
        Thread.sleep(3000);
        simulate();
    }

    public static void init() {
        DIM = Config.DIM;
        ITER = Config.ITERATIONS;

        grid = new Cell[DIM][DIM];
        grid = SimulatorUtils.fillGrid(grid);


        SimulatorUtils.initDisplay();
    }

    public static void simulate() throws InterruptedException {

        int q = ITER;
        label:
        while (q-- > 0) { // As i goes to 0

            int i = currentPosition[0];
            int j = currentPosition[1];

            Cell current = grid[i][j];
//            current.increment();

            Cell neighbor = new Cell();
            boolean neighborExists = false;
            while (!neighborExists) {
                if (reachedBorder(i, j, dir)) { // If the simulation can't go on because the board size is too small
                    break label;
                }
                int[] neighborCoords = current.getNeighbor(dir);
                if (neighborCoords != null) {
                    neighbor = get(grid, neighborCoords);
                    neighborExists = true;
                } else {
                    updateDirection();
                }
            }

            currentPosition = toPos(neighbor);

            current.increment();
            updateDirection();

            if (currentPosition[0] % 2 == 0) {
                currentPosition[0] += (int) (Math.floor(current.getValue())) - (int) (Math.floor(Math.sqrt(currentPosition[1])));
                updateDirection();
                if (currentPosition[0] < 0) {
                    currentPosition[0] = 0;
                }
            }

            if (currentPosition[1] % 2 == 0) {
                currentPosition[1] += (int) (Math.floor(current.getValue())) - (int) (Math.floor(Math.sqrt(currentPosition[1])));
                updateDirection();
                updateDirection();
                if (currentPosition[1] < 0) {
                    currentPosition[1] = 0;
                }
            }


            Thread.sleep(100);
            SimulatorUtils.display(grid, q, currentPosition);
        }
    }

    private static boolean reachedBorder(int i, int j, int dir) {
        int value = grid[i][j].getValue();

        if (value % 2 == 0) {
            if (dir == 3 && i == DIM - 1) {
                return true;
            }
        } else {
            if ((dir == 0 && i == DIM - 1) || (dir == 2 && j == DIM - 1) || (dir == 3 && (i == DIM - 1 || j == DIM - 1))) {
                return true;
            }
        }

        return false;
    }

    private static int[] toPos(Cell neighbor) {
        return SimulatorUtils.toPos(neighbor);
    }

    private static void updateDirection() {
//        dir = ++dir % 4;
        dir++;
        dir %= 4;
        dir += 10;
        dir %= 4;
    }

    private static Cell get(Cell[][] grid, int[] coords) {
        return SimulatorUtils.get(grid, coords);
    }

    private static String s(Cell k) {
        return "(" + k.getRow() + ", " + k.getCol() + ")";
    }
}
