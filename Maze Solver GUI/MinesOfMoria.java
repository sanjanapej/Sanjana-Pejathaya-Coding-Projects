import javax.swing.*;
import java.awt.*;

// MinesOfMoria class extends JPanel to create a custom panel for the class 
public class MinesOfMoria extends JPanel {

// enum to represent the possible cell types in the maze
    private enum Cell {
        WALL, PATH, TRACK
    }
// This is the 2D array which represents the maze, created with WALLS and PATHS. 
    private Cell[][] maze = {
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL},
            {Cell.PATH, Cell.PATH, Cell.PATH, Cell.PATH, Cell.WALL},
            {Cell.WALL, Cell.PATH, Cell.WALL, Cell.PATH, Cell.WALL},
            {Cell.WALL, Cell.PATH, Cell.PATH, Cell.PATH, Cell.PATH},
            {Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL, Cell.WALL}
    };
// Array created to represent the directions to move around in the maze. 
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

// Recrusive methos to find the path through the maze
    public boolean findPath(int x, int y) {
        //This checks to see if the current position is out of bounds or not a PATH cell
        if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] != Cell.PATH) {
            return false;
        }
        //Checks if this is the exit of the maze
        if (x == maze.length - 1 && y == maze[0].length - 1) {
            maze[x][y] = Cell.TRACK;
            return true;
        }
        //marks the current position as part of the path
        maze[x][y] = Cell.TRACK;

        //Recursively tries a possible directions from the current positions
        for (int[] dir : directions) {
            if (findPath(x + dir[0], y + dir[1])) {
                return true;
            }
        }
        //With no path, it marks it as a path again
        maze[x][y] = Cell.PATH;
        return false;
    }

    //Override the paintComponent method to draw the maze
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = 100;

        //Loops through the maze and draw each cell based on its type 
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[x].length; y++) {
                if (maze[x][y] == Cell.WALL) {
                    g.setColor(Color.BLACK);
                } else if (maze[x][y] == Cell.PATH) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.RED);
                }
                g.fillRect(y * cellSize, x * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY);
                g.drawRect(y * cellSize, x * cellSize, cellSize, cellSize);
            }
        }
    }

    //Main Method to create a JFrame and display the maze

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mines of Moria");
        MinesOfMoria moria = new MinesOfMoria();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(moria);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
        moria.findPath(0, 0);
        moria.repaint();
    }
}
