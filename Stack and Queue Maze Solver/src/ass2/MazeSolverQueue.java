package ass2;

public class MazeSolverQueue extends MazeSolver {
    
    private MyQueue<Square> worklist  = new MyQueue<Square>();
    
    public MazeSolverQueue(Maze maze) {
        super(maze);
        this.makeEmpty();
        Square start = this.maze.getStart();
        start.mark();
        this.add(start);
    }
    
    @Override
    void makeEmpty() {
        worklist  = new MyQueue<Square>();        
    }

    @Override
    boolean isEmpty() {        
        return worklist.isEmpty();
    }

    @Override
    void add(Square sq) {
        worklist.enqueue(sq);        
    }

    @Override
    Square getNext() {        
        return worklist.dequeue();
    }

}
