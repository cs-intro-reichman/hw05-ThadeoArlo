public class TesterGameOfLife {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java TesterGameOfLife <functionName> <filename>");
            return;
        }

        String functionName = args[0];
        String fileName = args[1];

        switch (functionName) {
            case "print":
                testPrint(fileName);
                break;
            case "cellValue":
                testCellValue(fileName);
                break;
            case "count":
                testCount(fileName);
                break;
            case "evolve":
                testEvolve(fileName, 3);
                break;
            default:
                System.out.println("Invalid function name.");
                break;
        }
    }

    private static void testPrint(String fileName) {
        int[][] board = GameOfLife.read(fileName);
        GameOfLife.print(board);
    }

    private static void testCellValue(String fileName) {
        int[][] board = GameOfLife.read(fileName);
        int[][] testCasesCellValue = fileName.equals("line.dat") ?
            new int[][] {
                {2, 2, 0},
                {2, 3, 1},
                {3, 2, 0},
                {3, 3, 1},
                {4, 3, 1}
            } :
            new int[][] {
                {2, 2, 1},
                {2, 3, 1},
                {3, 2, 1},
                {3, 3, 1},
                {4, 3, 0}
            };
    
        for (int[] test : testCasesCellValue) {
            int actualValue = GameOfLife.cellValue(board, test[0], test[1]);
            System.out.printf("Cell (%d, %d) - Expected Next Value: %d, Actual Next Value: %d\n", 
                              test[0], test[1], test[2], actualValue);
        }
    }
    
    private static void testCount(String fileName) {
        int[][] board = GameOfLife.read(fileName);
        int[][] testCasesCount = fileName.equals("line.dat") ?
            new int[][] {
                {2, 2, 2},
                {2, 3, 3},
                {3, 2, 1},
                {3, 3, 2},
                {4, 3, 3}
            } :
            new int[][] {
                {2, 2, 3},
                {2, 3, 3},
                {3, 2, 3},
                {3, 3, 3},
                {4, 3, 2}
            };
    
        for (int[] test : testCasesCount) {
            int actualNeighbors = GameOfLife.count(board, test[0], test[1]);
            System.out.printf("Cell (%d, %d) - Expected Neighbors: %d, Actual Neighbors: %d\n", 
                              test[0], test[1], test[2], actualNeighbors);
        }
    }

	private static void testEvolve(String fileName, int Ngen) {
		int[][] board = GameOfLife.read(fileName);
		for (int gen = 0; gen < Ngen; gen++) {
			System.out.println("Generation " + gen + ":");
			GameOfLife.print(board);
			board = GameOfLife.evolve(board);
		}
	}
    
    
}    

