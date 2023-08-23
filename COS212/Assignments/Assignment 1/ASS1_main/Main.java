public class Main {
    /*
     * Tips for using this Main
     * 
     * I included a sudoku gererator in the zip that can be used to make new boards
     * just make sure to run it in its own seperate folder or
     * with the command "javac sudoku.java" and "java sudoku"
     * 
     * IM not 100% sure on all the output yet but this should cause your code to
     * atleast find a couple erros
     * 
     * I did seperate the different tests into their own colours
     * 
     * the only board that is supposed to have dashes for the solution is the
     * unsolvable board which is the last one
     * 
     * the board that is 4x4 does have a solution and it took a while to get, so
     * there is supposed to be leading "0s" before the single digit numbers
     * 
     * This main took a really lonng time so any donations would be greately
     * appreciated, my pay pal is: https://www.paypal.me/ChuufMaster
     */
    public static void main(String[] args) {

        final String ANSI_RESET = "\u001B[0m";
        // final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        task1();

        System.out.println(ANSI_RED +
                "1x1=====================================================================");

        task2("1x1.txt");
        task3("1x1.txt");

        System.out.println(ANSI_GREEN +
                "2x2=====================================================================");

        task2("2x2.txt");
        task3("2x2.txt");

        System.out.println(ANSI_YELLOW +
                "2x3=====================================================================");

        task2("2x3.txt");
        task3("2x3.txt");

        System.out.println(ANSI_BLUE +
                "3x2=====================================================================");

        task2("3x2.txt");
        task3("3x2.txt");
        
        System.out.println(ANSI_PURPLE +
                "3x3=====================================================================");

        task2("3x3.txt");
        task3("3x3.txt");
        
        System.out.println(ANSI_CYAN +
                "4x4=====================================================================");

        task2("4x4.txt");
        task3("4x4.txt");

        System.out.println(
                ANSI_RED + "5x5================================================================");

        task2("5x5.txt");
        task3("5x5.txt");

        System.out.println(
                ANSI_YELLOW + "4x5================================================================");

        task2("4x5.txt");
        task3("4x5.txt");


        System.out.println(
                ANSI_WHITE + "3x3 hard================================================================");

        task2("3x3 hard.txt");
        task3("3x3.txt");

        System.out.println(
                ANSI_RED + "3x3 generated================================================================");

        task2("3x3 generated.txt");
        task3("3x3 generated.txt");

        System.out.println(
                ANSI_GREEN + "3x3 unsolvable================================================================");

        task2("3x3 unsolvable.txt");
        task3("3x3 unsolvable.txt");

        System.out.println(
                ANSI_CYAN + "3x3 empty================================================================");

        task2("3x3 empty.txt");
        task3("3x3 empty.txt");
         
        System.out.print(ANSI_RESET);

    }

    public static void task1() {
        System.out.println("==========\nTask1\n==========");
        List<Integer> l = new List<>();

        for (int i = 1; i <= 20; i++) {
            l.append(i);
        }

        for (int i = 20; i >= 1; i--) {
            Boolean testBoolean = l.remove(i);
            if (testBoolean)
                System.out.println("Removed :" + i);
        }
        System.out.println(l.length + "\t" + l);
        l.remove(2);
        System.out.println(l.length + "\t" + l);
        l.remove(1);
        System.out.println(l.length + "\t" + l);
        l.remove(9);
        System.out.println(l.length + "\t" + l);
        l.remove(4);
        System.out.println(l.length + "\t" + l);

        System.out.println("==========\nTask1 remake file and delete from bottom up\n==========");
        for (int i = 1; i <= 20; i++) {
            l.append(i);
        }

        for (int i = 1; i <= 20; i++) {
            Boolean testBoolean = l.remove(i);
            if (testBoolean)
                System.out.println("Removed :" + i);
        }
        System.out.println(l.length + "\t" + l);
        l.remove(2);
        System.out.println(l.length + "\t" + l);
        l.remove(1);
        System.out.println(l.length + "\t" + l);
        l.remove(9);
        System.out.println(l.length + "\t" + l);
        l.remove(4);
        System.out.println(l.length + "\t" + l);

        System.out.println("==========\nTask1 testing equals\n==========");

        for (int i = 1; i <= 20; i++) {
            l.append(i);
        }

        List<Integer> newList = new List<>();

        System.out.println(
                "if your out put has 2 \u001B[32mgreen\u001B[0m  outputs for the following one after the other it is correct: \n");
        for (int i = 1; i <= 20; i++) {
            newList.append(i);
            boolean test = newList.equals(l);
            final String ANSI_RESET = "\u001B[0m";
            String ANSI_GREEN = "";
            if (i == 20 && test) {
                ANSI_GREEN = "\u001B[32m";
            } else {
                ANSI_GREEN = "";
            }
            System.out.println(ANSI_GREEN + "newList equals: " + test + ANSI_RESET);
        }

        for (int i = 1; i <= 20; i++) {
            boolean test = newList.equals(l);
            String ANSI_GREEN = "";
            final String ANSI_RESET = "\u001B[0m";
            if (i == 1 && test) {
                ANSI_GREEN = "\u001B[32m";
            } else {
                ANSI_GREEN = "";
            }
            System.out.println(ANSI_GREEN + "newList equals: " + test + ANSI_RESET);
            newList.remove(i);
        }

        System.out.println("==========\nTask1 test if a list is equal to an empyt list\n==========");

        List<Integer> emptyList = null;

        System.out.println("Testing for empty list against full list: \u001B[31m" + newList.equals(emptyList)
                + "\u001B[0m; false is expected \n");

        List<Integer> deleteFromSelf = new List<>();
        for (int i = 1; i <= 20; i++) {
            deleteFromSelf.append(i);
        }
        System.out.println("==========\nTask1 test if you can delete a list form itself\n==========");

        System.out.println("Testing deleteing a list from itself: \u001B[32m" + deleteFromSelf.remove(deleteFromSelf)
                + "\u001B[0m; true is expected \n");
        System.out.println(deleteFromSelf.length);
        System.out.println(deleteFromSelf);

    }

    public static void task2(String input) {
        System.out.println("==========\nTask2\n==========");
        SudokuSolver s1 = new SudokuSolver(input);
        System.out.println("### Print Board ###");
        System.out.println(s1.board());
        System.out.println("### testLinks ###");
        s1.board().testLinks();
        System.out.println("### testCells ###");
        s1.board().testCells();
    }

    public static void task3(String input) {
        System.out.println("==========\nTask3\n==========");
        SudokuSolver s1 = new SudokuSolver(input);
        s1.solveBoard();
    }
}
