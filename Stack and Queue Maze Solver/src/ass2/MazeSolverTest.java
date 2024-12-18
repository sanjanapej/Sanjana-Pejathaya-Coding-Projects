package ass2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class MazeSolverTest {
	// maze 3 is not solvable
	// maze 4 has the same solution for stack and queue
	static final String maze1 = "3 3" + System.lineSeparator() +
			"2 0 0" + System.lineSeparator() +
			"0 0 0" + System.lineSeparator() +
			"0 0 3";
	
	static final String maze2 = "7 13" + System.lineSeparator() +
			"0 0 0 0 0 0 1 0 0 0 0 0 0" + System.lineSeparator() +
			"1 1 0 1 1 1 1 1 1 1 0 1 0" + System.lineSeparator() +
			"0 1 0 0 0 0 0 0 0 0 0 1 0" + System.lineSeparator() +
			"0 1 0 1 1 0 0 1 1 1 0 1 0" + System.lineSeparator() +
			"0 0 0 1 0 0 0 0 0 1 0 1 0" + System.lineSeparator() +
			"0 1 1 1 1 1 0 1 0 1 0 1 0" + System.lineSeparator() +
			"0 0 0 0 2 0 0 1 0 0 1 3 0";
	
	static final String maze3 = "3 5" + System.lineSeparator() +
			"0 0 1 0 0" + System.lineSeparator() +
			"2 0 1 0 3" + System.lineSeparator() +
			"0 0 1 0 0";
	
	static final String maze4 = "10 10" + System.lineSeparator() +
			"0 0 0 0 0 0 0 0 0 3" + System.lineSeparator() +
			"0 1 1 1 1 1 1 1 1 1" + System.lineSeparator() +
			"0 1 0 0 0 0 0 0 0 1" + System.lineSeparator() +
			"0 1 0 1 1 1 1 1 0 1" + System.lineSeparator() +
			"0 1 0 1 0 0 0 1 0 1" + System.lineSeparator() +
			"0 1 0 1 2 1 0 1 0 1" + System.lineSeparator() +
			"0 1 0 1 1 1 0 1 0 1" + System.lineSeparator() +
			"0 1 0 0 0 0 0 1 0 1" + System.lineSeparator() +
			"0 1 1 1 1 1 1 1 0 1" + System.lineSeparator() +
			"0 0 0 0 0 0 0 0 0 1";
	
	@Test
	void testPathFoundStack() {
		Maze m = Maze.loadMazeFromString(maze3);
		MazeSolver solver = new MazeSolverStack(m);
		
        while (!solver.isFinished()) {
        	solver.step();
        }
        assertEquals(solver.pathFound(), false); 
        
        m = Maze.loadMazeFromString(maze2);
		solver = new MazeSolverStack(m);
		
        while (!solver.isFinished()) {
        	solver.step();
        }
        assertEquals(solver.pathFound(), true); 
	}
	
	@Test
	void testPathFoundQueue() {
	    Maze m = Maze.loadMazeFromString(maze3); // unsolvable maze
	    MazeSolver solver = new MazeSolverQueue(m);
	    
	    while (!solver.isFinished()) {
	        solver.step();
	    }
	    assertEquals(false, solver.pathFound());
	    
	    m = Maze.loadMazeFromString(maze2); // solvable maze
	    solver = new MazeSolverQueue(m);
	    
	    while (!solver.isFinished()) {
	        solver.step();
	    }
	    assertEquals(true, solver.pathFound());
	}

	@Test
	void testGetPathStack() {
		Maze m = Maze.loadMazeFromString(maze1);
		MazeSolver solver = new MazeSolverStack(m);
		
        while (!solver.isFinished()) {
        	solver.step();
        }
        
        assertEquals(MazeApp.pathToString(solver.getPath()), 
        		"[0,0]-->[0,1]-->[0,2]-->[1,2]-->[2,2]"); 
        
        m = Maze.loadMazeFromString(maze2);
		solver = new MazeSolverStack(m);
		
        while (!solver.isFinished()) {
        	solver.step();
        }
        
        assertEquals(MazeApp.pathToString(solver.getPath()), 
        		"[6,4]-->[6,5]-->[6,6]-->[5,6]-->[4,6]-->[4,5]-->[3,5]-->[2,5]-->[2,6]-->[2,7]-->[2,8]-->[2,9]-->[2,10]-->[1,10]-->[0,10]-->[0,11]-->[0,12]-->[1,12]-->[2,12]-->[3,12]-->[4,12]-->[5,12]-->[6,12]-->[6,11]"); 

        m = Maze.loadMazeFromString(maze4);
		solver = new MazeSolverStack(m);
		
        while (!solver.isFinished()) {
        	solver.step();
        }
        
        assertEquals(MazeApp.pathToString(solver.getPath()), 
        		"[5,4]-->[4,4]-->[4,5]-->[4,6]-->[5,6]-->[6,6]-->[7,6]-->[7,5]-->[7,4]-->[7,3]-->[7,2]-->[6,2]-->[5,2]-->[4,2]-->[3,2]-->[2,2]-->[2,3]-->[2,4]-->[2,5]-->[2,6]-->[2,7]-->[2,8]-->[3,8]-->[4,8]-->[5,8]-->[6,8]-->[7,8]-->[8,8]-->[9,8]-->[9,7]-->[9,6]-->[9,5]-->[9,4]-->[9,3]-->[9,2]-->[9,1]-->[9,0]-->[8,0]-->[7,0]-->[6,0]-->[5,0]-->[4,0]-->[3,0]-->[2,0]-->[1,0]-->[0,0]-->[0,1]-->[0,2]-->[0,3]-->[0,4]-->[0,5]-->[0,6]-->[0,7]-->[0,8]-->[0,9]"); 
	}
	
	@Test
	void testGetPathQueue() {
		Maze m = Maze.loadMazeFromString(maze1);
		MazeSolver solver = new MazeSolverQueue(m);
		
        while (!solver.isFinished()) {
        	solver.step();
        }
        
        assertEquals(MazeApp.pathToString(solver.getPath()), 
        		"[0,0]-->[1,0]-->[2,0]-->[2,1]-->[2,2]"); 
        
        m = Maze.loadMazeFromString(maze2);
		solver = new MazeSolverQueue(m);
		
        while (!solver.isFinished()) {
        	solver.step();
        }
        
        assertEquals(MazeApp.pathToString(solver.getPath()), 
        		"[6,4]-->[6,5]-->[6,6]-->[5,6]-->[4,6]-->[3,6]-->[2,6]-->[2,7]-->[2,8]-->[2,9]-->[2,10]-->[1,10]-->[0,10]-->[0,11]-->[0,12]-->[1,12]-->[2,12]-->[3,12]-->[4,12]-->[5,12]-->[6,12]-->[6,11]"); 

        m = Maze.loadMazeFromString(maze4);
		solver = new MazeSolverQueue(m);
		
        while (!solver.isFinished()) {
        	solver.step();
        }
        
        assertEquals(MazeApp.pathToString(solver.getPath()), 
        		"[5,4]-->[4,4]-->[4,5]-->[4,6]-->[5,6]-->[6,6]-->[7,6]-->[7,5]-->[7,4]-->[7,3]-->[7,2]-->[6,2]-->[5,2]-->[4,2]-->[3,2]-->[2,2]-->[2,3]-->[2,4]-->[2,5]-->[2,6]-->[2,7]-->[2,8]-->[3,8]-->[4,8]-->[5,8]-->[6,8]-->[7,8]-->[8,8]-->[9,8]-->[9,7]-->[9,6]-->[9,5]-->[9,4]-->[9,3]-->[9,2]-->[9,1]-->[9,0]-->[8,0]-->[7,0]-->[6,0]-->[5,0]-->[4,0]-->[3,0]-->[2,0]-->[1,0]-->[0,0]-->[0,1]-->[0,2]-->[0,3]-->[0,4]-->[0,5]-->[0,6]-->[0,7]-->[0,8]-->[0,9]"); 
 	}
}
