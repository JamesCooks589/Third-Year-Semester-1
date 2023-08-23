import javax.management.RuntimeErrorException;

public class Main {

    public static void main(String[] args) {
        try{
        task1();
        }catch(DatabaseException e){
            System.out.println(e);
        }
     //   task2();
    }

    public static void task1() throws DatabaseException {
        /*
         * You are not given a Main for this task, because we want you to figure out how
         * to do it for yourself.
         *
         * You are provided with a validTreap() function which will print out valid or
         * invalid for a passed in Treap.
         *
         * Use this function to make sure that your heaps follow the rules set by the
         * Assignment.
         *
         * Tip : Create a Main that inserts / deletes a lot of elements and call
         * validTreap after every step
         */
    
        System.out.println("empty treap testing");
        Treap<Integer> empty = new Treap<>();
        //empty delete
        System.out.println("empty remove 10: ");
        if(empty.remove(10) == null) System.out.println("\u001B[32m" + "Empty remove success" + "\u001B[0m");
        else System.out.println("\u001B[31m" + "Empty remove unsuccessful" + "\u001B[0m");


        //empty access
        System.out.println("empty access 10: ");
        if(empty.access(10) == null) System.out.println("\u001B[32m" + "Empty access success" + "\u001B[0m");
        else System.out.println("\u001B[31m" + "Empty access unsuccessful" + "\u001B[0m");

        System.out.println("treap testing");
        Treap<Integer> t = new Treap<>();
        
        for(int i = 0; i < 100; i+=10){
            System.out.println("inserting: " + i);
            t.insert(i);
            System.out.print("Is treap valid after inserting " + i+ " : " + validTreap(t));
        }
        System.out.println("inserting: " + 5);
        t.insert(5);
        System.out.print("Is treap valid after inserting 5: " + validTreap(t));

        System.out.println("inserting: " + 12);
        t.insert(12);
        System.out.print("Is treap valid after inserting 12: " + validTreap(t));

        System.out.println("inserting: " + 99);
        t.insert(99);
        System.out.print("Is treap valid after inserting 99: " + validTreap(t));

        System.out.println("inserting: " + 102);
        t.insert(102);
        System.out.print("Is treap valid after inserting 102: " + validTreap(t));

        System.out.println("inserting: " + 1);
        t.insert(1);
        System.out.print("Is treap valid:  after inserting 1: " + validTreap(t));

        System.out.println("Final treap");
        System.out.println(t);



        try{
            System.out.println("insert dupicate 99: ");
            t.insert(99);
            System.out.print("Is treap valid after inserting duplicate 99: ");
            System.out.print(validTreap(t));
            System.out.println("\u001B[31m" + "insert duplicate not caught\"" + "\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[32m" + "insert duplicate caught" + "\u001B[0m");
        }

        System.out.println("Remove treap testing: ");
        System.out.println("Removing: " + 60);
        Node<Integer> tr = t.remove(60);
        if(tr != null) System.out.println("\u001B[32m" + "60/root remove success" + tr + "\u001B[0m");
        else System.out.println("\u001B[31m" + "60/root remove unsuccessful" + "\u001B[0m");
        System.out.print("Is treap valid after removing 60(root): " + validTreap(t));
        System.out.println(t);
        System.out.println();
        System.out.println("Removing: " + 12);
        tr = t.remove(12);
        if(tr != null) System.out.println("\u001B[32m" + "12/non-leaf remove success" + tr+ "\u001B[0m");
        else System.out.println("\u001B[31m" + "12/non-leaf remove unsuccessful" + "\u001B[0m");
        System.out.print("Is treap valid after removing 12(non-leaf): " + validTreap(t));
        System.out.println(t);

        System.out.println();
        System.out.println("Removing: " + 50);
        tr = t.remove(50);
        if(tr != null) System.out.println("\u001B[32m" + "50/leaf remove success" + tr + "\u001B[0m");
        else System.out.println("\u001B[31m" + "50/leaf remove unsuccessful" + "\u001B[0m");
        System.out.print("Is treap valid after removing 50(leaf): " + validTreap(t));
        System.out.println(t);
        System.out.println();

        System.out.println("Removing non-existent: " + 99999);
        tr = t.remove(99999);
        if(tr == null) System.out.println("\u001B[32m" + "99999 not found" + "\u001B[0m");
        else System.out.println("\u001B[31m" + "99999 found: " + tr + "\u001B[0m");
        System.out.print("Is treap valid after removing 99999(non-existent): " + validTreap(t));
        System.out.println(t);
        System.out.println();


        System.out.println("Access testing");

        System.out.println("Accessing 10(root) ");
        Node<Integer> accessor = t.access(10);
        if(accessor != null) System.out.println("\u001B[32m" + "10 found: " +  accessor + "\u001B[0m");
        else System.out.println("\u001B[31m" + "10 not found" + "\u001B[0m");
        System.out.print("Is treap valid after accessing 10: " + validTreap(t));
        System.out.println(t);
        System.out.println();

        System.out.println("Accessing 99(non-leaf) ");
        accessor = t.access(99);
        if(accessor != null) System.out.println("\u001B[32m" + "99 found: " +  accessor + "\u001B[0m");
        else System.out.println("\u001B[31m" + "99 not found" + "\u001B[0m");
        System.out.print("Is treap valid after accessing 99: " + validTreap(t));
        System.out.println(t);
        System.out.println();

        System.out.println("Accessing 102(leaf) ");
        accessor = t.access(102);
        if(accessor != null) System.out.println("\u001B[32m" + "102 found: " +  accessor + "\u001B[0m");
        else System.out.println("\u001B[31m" + "102 not found" + "\u001B[0m");
        System.out.print("Is treap valid after accessing 102: " + validTreap(t));
        System.out.println(t);
        System.out.println();

        System.out.println("Acceessing non-existent: " + 99999);
        Node<Integer> accessor1 = t.access(99999);
        if(accessor1 == null) System.out.println("\u001B[32m" + "99999 not found" + "\u001B[0m");
        else System.out.println("\u001B[31m" + "99999 found" + "\u001B[0m");
        System.out.print("Is treap valid after accessing non-existent 99999: " + validTreap(t));
        System.out.println(t);
        System.out.println();

    }

    // public static void task2() {
    //     /*
    //      * Note that we also want you to create your own main for this task.
    //      * 
    //      * It takes a while to set the DB up, so an example is given below, feel free to
    //      * change it to test the rest of the functions
    //      */
    //     String[] columns = { "Module Code", "Description", "Credits", "Year", "Session" };
    //     Database db = new Database(columns, 100);

    //     String[][] modules = {
    //             { "LST110", "Language and study skills 110", "6", "1", "Sem 1" },
    //             { "WTW124", "Mathematics 124", "16", "1", "Sem 2" },
    //             { "UP0102", "Academic orientation 102", "0", "1", "Year" },
    //             { "WTW114", "Calculus 114", "16", "1", "Sem 1" },
    //             { "WTW123", "Numerical analysis 123", "8", "1", "Sem 2" },
    //             { "PHY114", "First course in physics 114", "16", "1", "Sem 1" },
    //             { "PHY124", "First course in physics 124", "16", "1", "Sem 2" },
    //             { "AIM102", "Academic information management 102", "6", "1", "Sem 2" },
    //             { "COS122", "Operating systems 122", "16", "1", "Sem 2" },
    //             { "COS132", "Imperative programming 132", "16", "1", "Sem 1" },
    //             { "COS110", "Program design: Introduction 110", "16", "1", "Sem 2" },
    //             { "COS151", "Introduction to computer science 151", "8", "1", "Sem 1" },
    //             { "COS212", "Data structures and algorithms 212", "16", "2", "Sem 1" },
    //             { "COS226", "Concurrent systems 226", "16", "2", "Sem 2" },
    //             { "COS284", "Computer organisation and architecture 284", "16", "2", "Sem 2" },
    //             { "COS210", "Theoretical computer science 210", "8", "2", "Sem 1" },
    //             { "WTW248", "Vector analysis 248", "12", "2", "Sem 2" },
    //             { "PHY255", "Waves, thermodynamics and modem physics 255", "24", "2", "Sem 1" },
    //             { "PHY263", "General physics 263", "24", "2", "Sem 2" },
    //             { "WTW211", "Linear algebra 211", "12", "2", "Sem 1" },
    //             { "WTW218", "Calculus 218", "12", "2", "Sem 1" },
    //             { "WTW220", "Analysis 220", "12", "2", "Sem 2" },
    //             { "COS314", "Artificial intelligence 314", "18", "3", "Sem 1" },
    //             { "COS330", "Computer security and ethics 330", "18", "3", "Sem 2" },
    //             { "COS333", "Programming languages 333", "18", "3", "Sem 2" },
    //             { "COS344", "Computer graphics 344", "18", "3", "Sem 1" },
    //             { "PHY310", "Particle and astroparticle physics 310", "18", "3", "Sem 2" },
    //             { "PHY356", "Electronics, electromagnetism and quantum mechanics 356", "36", "3", "Sem 1" },
    //             { "PHY364", "Statistical mechanics, solid state physics and modelling 364", "36", "3", "Sem 2" },
    //             { "COS711", "Artificial Intelligence (II) 711", "15", "4", "Sem 2" },
    //             { "FSK700", "Physics 700", "135", "4", "Year" }
    //     };

    //     try {
    //         for (String[] mod : modules) {
    //             db.insert(mod);
    //         }

    //      //   db.generateIndexAll();
    //     } catch (DatabaseException e) {
    //         System.out.println("Error: " + e.getMessage());
    //     }
    //     for(int i=0; i < db.columnNames.length; i++) {
    //         System.out.print(db.columnNames[i] + "\t");
    //     }
    //     System.out.println();
    //     for (String[] row : db.database) {
    //         if (row[0] != null) {
    //             int c = 0;
    //             for (String s : row) {
    //                 if (c++ == 1) {
    //                     System.out.print(String.format("%1$-75s", s));
    //                 } else {
    //                     System.out.print(s + "\t");
    //                 }
    //             }
    //             System.out.println();
    //         }
    //     }

    //     // System.out.println(db.indexes[0]);
    //     // System.out.println(db.indexes[1]);
    // }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static <T extends Comparable<T>> String validTreap(Treap<T> t) {
        return (validTreap(t.root) ? ANSI_GREEN + "Valid\n" + ANSI_RESET : ANSI_RED + "Invalid\n" + ANSI_RESET);
    }

    public static <T extends Comparable<T>> boolean validTreap(Node<T> n) {
        if (n == null) {
            return true;
        }

        if (n.left != null && (n.left.priority > n.priority || n.getData().compareTo(n.left.getData()) < 0)) {
            return false;
        }

        if (n.right != null && (n.right.priority > n.priority || n.getData().compareTo(n.right.getData()) > 0)) {
            return false;
        }

        if (!validTreap(n.left)) {
            return false;
        }

        if (!validTreap(n.right)) {
            return false;
        }

        return true;
    }
}
