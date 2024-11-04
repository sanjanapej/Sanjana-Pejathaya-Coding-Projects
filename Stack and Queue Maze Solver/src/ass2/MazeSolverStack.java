package ass2;

public class MazeSolverStack extends MazeSolver {
    private MyStack<Square> worklist  = new MyStack<Square>();
    
    public MazeSolverStack(Maze maze) {
        super(maze);
        this.makeEmpty();
        Square start = this.maze.getStart();
        start.mark();
        this.add(start);
    }
    
    @Override
    void makeEmpty() {
        worklist  = new MyStack<Square>();        
    }

    @Override
    boolean isEmpty() {        
        return worklist.isEmpty();
    }

    @Override
    void add(Square sq) {        
        worklist.push(sq);
    }

    @Override
    Square getNext() {        
        return worklist.pop();
    }

}
