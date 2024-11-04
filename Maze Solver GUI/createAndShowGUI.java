  private static final int cellSize = 30;

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Mines of Moria");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mazePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int x = 0; x < moria.maze.length; x++) {
                    for (int y = 0; y < moria.maze[x].length; y++) {
                        if (moria.maze[x][y] == Cell.WALL) {
                            g.setColor(Color.BLACK);
                        } else if (moria.maze[x][y] == Cell.PATH) {
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
        };
        mazePanel.setPreferredSize(new Dimension(moria.maze[0].length * cellSize, moria.maze.length * cellSize));

        frame.add(mazePanel);
        frame.pack();
        frame.setVisible(true);
    }
}