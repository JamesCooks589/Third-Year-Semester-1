public class Main {
    public static void main(String[] args) {
        SkipList<Integer> myList = new SkipList<>(3);
        System.out.println(myList);
        for (int i = 0; i < 10; i++) {
            myList.insert(i);
        }
       System.out.println(myList);
        System.out.print("Searching for 8\t");
        myList.printSearchPath(8);
        System.out.print("Searching for 2\t");
        myList.printSearchPath(2);
        myList.insert(8);
        myList.insert(8);
        myList.insert(8);
        myList.insert(8);
        myList.insert(8);
        System.out.println(myList);

        if (myList.delete(8)) {
            System.out.println("Deleted 8");
        }
        else {
            System.out.println("8 not found");
        }

        System.out.println(myList);
    }
}
