package ass2;

import java.util.*;

abstract class MazeSolver {
    public static final int STACK = 1, QUEUE = 2;
    protected Maze maze;
    protected boolean finished = false;
    protected boolean pathFound = false;
    private  List<Square> path= new LinkedList<Square>();

    MazeSolver(Maze maze) {
        this.maze = maze;          
    }

    abstract void makeEmpty();

    abstract boolean isEmpty();

    abstract void add(Square sq);

    abstract Square getNext();

    public boolean isFinished() {
        return finished;
    }

    public boolean pathFound() {
        return pathFound;
    }

    /*
     * makes a list of the squares on a path from the start square to the exit
     * square
     */
    public List<Square> getPath() {        
        return this.path; 
    }

    private void buildPath(Square current) {
        this.path = new LinkedList<>(); // Reset path list

        // Traverse from the exit to the start square
        while (current != null && current.getType() != Square.START) {
            this.path.add(current);
            current.setOnPath();         // Mark square as part of the solution path
            current = current.getPrevious();
        }
        
        // Add the start square
        if (current != null && current.getType() == Square.START) {
            this.path.add(current);
        }
        
        // Reverse the list once to get the correct path order
        Collections.reverse(this.path);
    }

    
    /* performs one step of the maze solver algorithm */
    public void step() {
        // Check if the worklist is empty and we haven't found the exit
        if (isEmpty() && !pathFound) {
            finished = true; // Maze is unsolvable, terminate the algorithm
            return;
        }

        // Get the next square to explore from the worklist
        Square current = getNext();

        // Check if this square is the exit
        if (current.getType() == Square.EXIT) {
            pathFound = true; // Exit is reachable, path found
            finished = true;  // Terminate the algorithm
            buildPath(current); // Build the path from start to exit
            return;
        }

        // Explore the neighboring squares of the current square
        List<Square> neighbors = maze.getNeighbors(current);
        for (Square neighbor : neighbors) {
            // Only add squares that are not walls and haven't been marked yet
            if (neighbor.getType() != Square.WALL && !neighbor.isMarked()) {
                neighbor.mark();           // Mark this square as explored
                neighbor.setPrevious(current); // Set the previous square for path tracing
                add(neighbor);             // Add it to the worklist for exploration
            }
        }
    }

    }