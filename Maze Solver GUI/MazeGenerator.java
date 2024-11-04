import javax.swing.*;
import java.awt.*;
import java.util.*;

//Optional Challenges: Write a method that automatically generates a maze

//Maze Generator class extends JPanel to create a custom panel for drawing the maze
public class MazeGenerator extends JPanel {

//Constants for the size of the maze and each cells pixels
    private static final int SIZE = 20;
    private static final int CELL_SIZE = 30;

//Random object for generating number 
    private static final Random random = new Random();
// Enum to represent the possible cell types in the maze

    private enum Cell {
        WALL, PATH
    }

//2D array representing the maze, initialized with WALLs

    private Cell[][] maze = new Cell[SIZE][SIZE];

//Initializes the maze
    public MazeGenerator() {
        generateMaze();
    }

    private void generateMaze() {
        // Initialize maze with walls
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                maze[i][j] = Cell.WALL;
            }
        }

        // Generate maze using Recursive Backtracking algorithm
        generatePath(1, 1);

        // Set start and end points
        maze[1][0] = Cell.PATH;
        maze[SIZE - 2][SIZE - 1] = Cell.PATH;
    }

    private void generatePath(int x, int y) {
        maze[x][y] = Cell.PATH;
        //Array list of neigbouring cells 
        ArrayList<int[]> neighbors = new ArrayList<>();
        neighbors.add(new int[]{x - 2, y});
        neighbors.add(new int[]{x + 2, y});
        neighbors.add(new int[]{x, y - 2});
        neighbors.add(new int[]{x, y + 2});
        Collections.shuffle(neighbors);

    //Iterate over neigbours and create paths 

        for (int[] neighbor : neighbors) {
            int nx = neighbor[0];
            int ny = neighbor[1];
            if (nx > 0 && ny > 0 && nx < SIZE - 1 && ny < SIZE - 1 && maze[nx][ny] == Cell.WALL) {
                int mx = (nx + x) / 2;
                int my = (ny + y) / 2;
                maze[mx][my] = Cell.PATH;
                generatePath(nx, ny);
            }
        }
    }

//Override the paintComponent method to draw the maze
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (maze[i][j] == Cell.WALL) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
//Main Method to create a JFrame and display the maze
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Maze Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(SIZE * CELL_SIZE, SIZE * CELL_SIZE);
            MazeGenerator mazeGenerator = new MazeGenerator();
            frame.add(mazeGenerator);
            frame.setVisible(true);
        });
    }
}
