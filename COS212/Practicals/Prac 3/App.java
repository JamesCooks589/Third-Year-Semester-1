public class App {
    public static void main(String[] args) {
        //test();
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        method(arr);
    }

    public void method(int arr[]){
        int el = 0;
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            el = arr[i];
            for (j = i; j > 0 && el < arr[j-1]; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = el;
        }
        
    }

    public static void test() {

    //     BinaryTree<Integer> t = new StandardBinaryTree<>();
    
    //     t.insert(2);
    //     t.insert(1);
    //     t.insert(3);
    
    //     System.out.println("Tree Height");
    //     System.out.println(t.height() + "\n");
    //     System.out.println("Tree Number of Leaves");
    //     System.out.println(t.numLeavesInTree() + "\n");
    //     System.out.println("Depth First Traversal");
    //     t.depthFirstTraversal();
    //     System.out.println("\nFind parent of [3]");
    //     t.findParent(3);
    //     System.out.println("\nFind [1]");
    //     t.find(1);
    //     System.out.println("\nContains [5]");
    //     t.contains(5);

    //     BinaryTree<Integer> test = new StandardBinaryTree<>();
    //     System.out.println("\nthe following list of numbers is the order in which the nodes are inserted to get the binary tree for if you want to visualize it: \n");
    //     for(int i = 10; i < 20; i++){
    //         test.insert((i%2+1)*i+i);
    //         System.out.print(((i%2+1)*i+i)+",");
    //     }

    //     for(int i = 9 ; i >= 1; i--){
    //         test.insert((i%2+1)*i+i);
    //         System.out.print(((i%2+1)*i+i)+",");
    //     }

    //     System.out.println("\n");
    //     System.out.println("Tree Height");
    //     System.out.println(test.height() + "\n");
    //     System.out.println("Tree Number of Leaves");
    //     System.out.println(test.numLeavesInTree() + "\n");
    //     System.out.println("Depth First Traversal");
    //     test.depthFirstTraversal();
    //     System.out.println("\nFind parent of [3]");
    //     test.findParent(3);
    //     System.out.println("\nFind [1]");
    //     test.find(1);
    //     System.out.println("\nContains [5]");
    //     test.contains(5);

    //     BinaryTree<Integer> test2 = test.convertTree();

    //     System.out.println("Tree Height");
    //     System.out.println(test2.height() + "\n");
    //     System.out.println("Tree Number of Leaves");
    //     System.out.println(test2.numLeavesInTree() + "\n");
    //     System.out.println("Depth First Traversal");
    //     test2.depthFirstTraversal();
    //     System.out.println("\nFind parent of [3]");
    //     test2.findParent(3);
    //     System.out.println("\nFind [1]");
    //     test2.find(1);
    //     System.out.println("\nContains [5]");
    //     test2.contains(5);
    // }
}
}

/*
Tree Height
1

Tree Number of Leaves
3

Depth First Traversal
L[null]<-[1]->R[null]
L[1]<-[2]->R[3]
L[null]<-[3]->R[null]

Find parent of [3]
L[1]<-[2]->R[3]

Find [1]
L[1]<-[2]->R[3]
L[null]<-[1]->R[null]

Contains [5]
L[1]<-[2]->R[3]
L[null]<-[3]->R[null]

the following list of numbers is the order in which the nodes are inserted to get the binary tree for if you want to visualize it:

20,33,24,39,28,45,32,51,36,57,27,16,21,12,15,8,9,4,3,

Tree Height
5

Tree Number of Leaves
19

Depth First Traversal
L[null]<-[3]->R[null]
L[3]<-[4]->R[null]
L[4]<-[8]->R[9]
L[null]<-[9]->R[null]
L[8]<-[12]->R[15]
L[null]<-[15]->R[null]
L[12]<-[16]->R[null]
L[16]<-[20]->R[33]
L[null]<-[21]->R[null]
L[21]<-[24]->R[28]
L[null]<-[27]->R[null]
L[27]<-[28]->R[32]
L[null]<-[32]->R[null]
L[24]<-[33]->R[39]
L[null]<-[36]->R[null]
L[36]<-[39]->R[45]
L[null]<-[45]->R[51]
L[null]<-[51]->R[57]
L[null]<-[57]->R[null]

Find parent of [3]
L[16]<-[20]->R[33]
L[12]<-[16]->R[null]
L[8]<-[12]->R[15]
L[4]<-[8]->R[9]
L[3]<-[4]->R[null]

Find [1]
L[16]<-[20]->R[33]
L[12]<-[16]->R[null]
L[8]<-[12]->R[15]
L[4]<-[8]->R[9]
L[3]<-[4]->R[null]
L[null]<-[3]->R[null]

Contains [5]
L[16]<-[20]->R[33]
L[12]<-[16]->R[null]
L[8]<-[12]->R[15]
L[4]<-[8]->R[9]
L[3]<-[4]->R[null]
Tree Height
5

Tree Number of Leaves
19

Depth First Traversal
L[null]<-[3]->R[null]
L[null]<-[4]->R[3]
L[9]<-[8]->R[4]
L[null]<-[9]->R[null]
L[15]<-[12]->R[8]
L[null]<-[15]->R[null]
L[null]<-[16]->R[12]
L[33]<-[20]->R[16]
L[null]<-[21]->R[null]
L[28]<-[24]->R[21]
L[null]<-[27]->R[null]
L[32]<-[28]->R[27]
L[null]<-[32]->R[null]
L[39]<-[33]->R[24]
L[null]<-[36]->R[null]
L[45]<-[39]->R[36]
L[51]<-[45]->R[null]
L[57]<-[51]->R[null]
L[null]<-[57]->R[null]

Find parent of [3]
L[33]<-[20]->R[16]
L[null]<-[16]->R[12]
L[15]<-[12]->R[8]
L[9]<-[8]->R[4]
L[null]<-[4]->R[3]

Find [1]
L[33]<-[20]->R[16]
L[null]<-[16]->R[12]
L[15]<-[12]->R[8]
L[9]<-[8]->R[4]
L[null]<-[4]->R[3]
L[null]<-[3]->R[null]

Contains [5]
L[33]<-[20]->R[16]
L[null]<-[16]->R[12]
L[15]<-[12]->R[8]
L[9]<-[8]->R[4]
L[null]<-[4]->R[3]
 */