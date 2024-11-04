public class MazeSolver {
    public static void main(String[] args) {
        MinesOfMoria moria = new MinesOfMoria();

        System.out.println("Maze:");
        moria.printMaze();

        if (moria.findPath(0, 0)) {
            System.out.println("\nPath found:");
            moria.printMaze();
        } else {
            System.out.println("\nNo path found.");
        }
    }
}
