public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\033[0;33m";  // YELLOW
    public static final String ANSI_BLUE = "\033[0;34m";    // BLUE
    public static final String ANSI_PURPLE = "\033[0;35m";  // PURPLE
    public static final String ANSI_CYAN = "\033[0;36m";    // CYAN
    public static final String ANSI_WHITE = "\033[0;37m";   // WHITE


    /*
    NB: add to bottome of Graph function
    public String[] getVertices(){
        return this.vertices;
    }

    /*
     */
    public static void main(String[] args) {
        String fileName = "graph.txt";              //change this to appropriate graph file
        SimpleTests();              //tests without file
//        Tests(1, fileName);       //uncomment test 1 file at a time, comment out vvvvv
        String[] bulkgraphs = {"graph.txt", "graph2.txt","Lgraph1.txt", "graph3.txt", "graph4.txt","graph5.txt", "graph6.txt", "graph7Numbered.txt", "graph8.txt", "graph9.txt", "graph10.txt", "graph11.txt", "graph12.txt", "graph13.txt", "graph14.txt", "graph15.txt", "graph16.txt", "graph17.txt", "graph18.txt", "graph19.txt", "graph20.txt"};
        for(int i = 0; i < bulkgraphs.length; i++){         //change i < , to number of tests to run
            Tests(i, bulkgraphs[i]);
        }
    }
    public static void Tests(int Testnum, String filename) {
        System.out.println(ANSI_WHITE + "-------------Test: " + Testnum + " ---Testing file: " + filename + "----------" + ANSI_RESET);
        Graph g = new Graph(filename);
        fullSuite(g);
        System.out.println(ANSI_WHITE + "-------------End Test: " + Testnum + " -------------" + ANSI_RESET);

    }

    public static void SimpleTests() {
        System.out.println(ANSI_WHITE + "----------------Simple Tests---------------" + ANSI_RESET);

        Graph g = new Graph("");
        System.out.println(g);
        fullSuite(g);
        InsertVertexIntoGraph(g, "J");
        InsertVertexIntoGraph(g, "A");
//
        fullSuite(g);
        InsertEdgeIntoGraph(g, "J", "J", 4);
        fullSuite(g);
        InsertEdgeIntoGraph(g, "J", "J", 0);
        fullSuite(g);
        InsertEdgeIntoGraph(g, "J", "A", 0);
        fullSuite(g);
        InsertEdgeIntoGraph(g, "B", "A", 0);
        fullSuite(g);
        InsertEdgeIntoGraph(g, "J", "J", 4);
        fullSuite(g);
        InsertVertexIntoGraph(g, "A");
        fullSuite(g);
        RemoveVertexFromGraph(g, "J");
        fullSuite(g);
        InsertEdgeIntoGraph(g, "", "A", 5);
        InsertVertexIntoGraph(g, "L");
        fullSuite(g);
        InsertEdgeIntoGraph(g, "O", "A", 5);
        InsertVertexIntoGraph(g, "M");
        fullSuite(g);

        InsertEdgeIntoGraph(g, "O", "A", 5);
        InsertVertexIntoGraph(g, "N");
        fullSuite(g);

        InsertEdgeIntoGraph(g, "O", "A", 5);
        InsertVertexIntoGraph(g, "P");
        fullSuite(g);

        InsertEdgeIntoGraph(g, "O", "A", 5);
        InsertVertexIntoGraph(g, "R");
        fullSuite(g);

        InsertEdgeIntoGraph(g, "O", "A", 5);
        fullSuite(g);
        System.out.println(ANSI_WHITE + "----------------End Simple Tests---------------" + ANSI_RESET);

    }
    public static void fullSuite(Graph curr){

        System.out.println("-------FULL SUITE----------");
        System.out.println(curr);
        System.out.println("DFS:" + curr.depthFirstTraversal());
        System.out.println("BFS:" + curr.breadthFirstTraversal());
        System.out.println("Strongly Connected:\n" + curr.stronglyConnectedComponents());
        System.out.println(cycleDetect(curr));
        System.out.println(shortestDistance(curr, "2", "6"));               //change to suite need
        PrintDistanceMatrix(curr);
        System.out.println("-------END FULL SUITE----------");

    }

    public static String cycleDetect (Graph curr) {
        return (curr.cycleDetection() ? ANSI_PURPLE + "Cycle found\n" + ANSI_RESET : ANSI_BLUE + "Cycle not found\n" + ANSI_RESET);
    }
    private static String shortestDistance(Graph curr, String start, String end) {
        System.out.println("------PRINTING SHORTEST DISTANCE FROM: [" + start + "] TO: [" + end + "]--------");

        Double find = curr.shortestPath(start, end);
        if(find != null) return "Distance from: [" + start + "] TO: [" + end + "]--> " + find;
        else return ANSI_RED + "start not found in graph/end not found in graph\n" + ANSI_RESET;
    }
    public static void PrintDistanceMatrix(Graph curr) {
        System.out.println("------PRINTING SHORTEST DISTANCE MATRIX--------");

        Double[][] find = curr.shortestPaths();
        String[] verts = curr.getVertices();
        int vertCounter = 0;
        System.out.print("\t");
        System.out.print("\t");
        for(String Vertex: verts){
            System.out.printf("%-6s\t", Vertex);
        }
        System.out.println();
        for (Double[] doubles : find) {
            System.out.printf("%-6s\t", verts[vertCounter++]);
            for (int j = 0; j < find[0].length; j++) {
                if(doubles[j] == Double.POSITIVE_INFINITY) System.out.printf("%-4.3s\t", "Inf ");
                else System.out.printf("%-6.1f\t", doubles[j]);
            }
            System.out.println();
        }
    }
    private static void InsertVertexIntoGraph(Graph curr, String name) {
        System.out.println("------INSERT " + name + "---------");

        curr.insertVertex(name);
        System.out.println("Graph after inserting Vertex: " + name);
        System.out.println(curr);
    }

    private static void InsertEdgeIntoGraph(Graph curr, String start, String end, int weight) {
        System.out.println("------INSERT [" + start + "]---" + weight + "-->[" + end+ "]");

        curr.insertEdge(start, end, weight);
        System.out.println("Graph after inserting:  [" + start + "]---" + weight + "-->[" + end+ "]");
        System.out.println(curr);
    }

    private static void RemoveVertexFromGraph(Graph curr, String name) {
        System.out.println("------REMOVE [" + name + "]---------");

        curr.removeVertex(name);
        System.out.println("Graph after removing Vertex: " + name);
        System.out.println(curr);
    }

    private static void RemoveEdgeFromGraph(Graph curr, String start, String end, int weight) {
        System.out.println("------REMOVE [" + start + "]---(" + weight + ")-->[" + end+ "]");

        curr.removeEdge(start, end);
        System.out.println("Graph after removing:  [" + start + "]---(" + weight + ")-->[" + end+ "]");
        System.out.println(curr);
    }
    //>simple
    /*
    ----------------Simple Tests---------------
(0,0)
-------FULL SUITE----------
(0,0)
DFS:
BFS:
Strongly Connected:

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------

-------END FULL SUITE----------
------INSERT J---------
Graph after inserting Vertex: J
(1,0)	J
J	0
------INSERT A---------
Graph after inserting Vertex: A
(2,0)	J	A
J	0	0
A	0	0
-------FULL SUITE----------
(2,0)	J	A
J	0	0
A	0	0
DFS:[J][A]
BFS:[J][A]
Strongly Connected:
[J]
[A]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		J     	A
J     	0.0   	Inf
A     	Inf 	0.0
-------END FULL SUITE----------
------INSERT [J]---4-->[J]
Graph after inserting:  [J]---4-->[J]
(2,1)	J	A
J	4	0
A	0	0
-------FULL SUITE----------
(2,1)	J	A
J	4	0
A	0	0
DFS:[J][A]
BFS:[J][A]
Strongly Connected:
[J]
[A]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		J     	A
J     	0.0   	Inf
A     	Inf 	0.0
-------END FULL SUITE----------
------INSERT [J]---0-->[J]
Graph after inserting:  [J]---0-->[J]
(2,1)	J	A
J	4	0
A	0	0
-------FULL SUITE----------
(2,1)	J	A
J	4	0
A	0	0
DFS:[J][A]
BFS:[J][A]
Strongly Connected:
[J]
[A]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		J     	A
J     	0.0   	Inf
A     	Inf 	0.0
-------END FULL SUITE----------
------INSERT [J]---0-->[A]
Graph after inserting:  [J]---0-->[A]
(2,1)	J	A
J	4	0
A	0	0
-------FULL SUITE----------
(2,1)	J	A
J	4	0
A	0	0
DFS:[J][A]
BFS:[J][A]
Strongly Connected:
[J]
[A]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		J     	A
J     	0.0   	Inf
A     	Inf 	0.0
-------END FULL SUITE----------
------INSERT [B]---0-->[A]
Graph after inserting:  [B]---0-->[A]
(2,1)	J	A
J	4	0
A	0	0
-------FULL SUITE----------
(2,1)	J	A
J	4	0
A	0	0
DFS:[J][A]
BFS:[J][A]
Strongly Connected:
[J]
[A]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		J     	A
J     	0.0   	Inf
A     	Inf 	0.0
-------END FULL SUITE----------
------INSERT [J]---4-->[J]
Graph after inserting:  [J]---4-->[J]
(2,1)	J	A
J	4	0
A	0	0
-------FULL SUITE----------
(2,1)	J	A
J	4	0
A	0	0
DFS:[J][A]
BFS:[J][A]
Strongly Connected:
[J]
[A]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		J     	A
J     	0.0   	Inf
A     	Inf 	0.0
-------END FULL SUITE----------
------INSERT A---------
Graph after inserting Vertex: A
(3,1)	J	A	A
J	4	0	0
A	0	0	0
A	0	0	0
-------FULL SUITE----------
(3,1)	J	A	A
J	4	0	0
A	0	0	0
A	0	0	0
DFS:[J][A][A]
BFS:[J][A][A]
Strongly Connected:
[J]
[A]
[A]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		J     	A     	A
J     	0.0   	Inf 	Inf
A     	Inf 	0.0   	Inf
A     	Inf 	Inf 	0.0
-------END FULL SUITE----------
------REMOVE [J]---------
Graph after removing Vertex: J
(2,0)	A	A
A	0	0
A	0	0
-------FULL SUITE----------
(2,0)	A	A
A	0	0
A	0	0
DFS:[A][A]
BFS:[A][A]
Strongly Connected:
[A]
[A]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		A     	A
A     	0.0   	Inf
A     	Inf 	0.0
-------END FULL SUITE----------
------INSERT []---5-->[A]
Graph after inserting:  []---5-->[A]
(2,0)	A	A
A	0	0
A	0	0
------INSERT L---------
Graph after inserting Vertex: L
(3,0)	A	A	L
A	0	0	0
A	0	0	0
L	0	0	0
-------FULL SUITE----------
(3,0)	A	A	L
A	0	0	0
A	0	0	0
L	0	0	0
DFS:[A][A][L]
BFS:[A][A][L]
Strongly Connected:
[A]
[A]
[L]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		A     	A     	L
A     	0.0   	Inf 	Inf
A     	Inf 	0.0   	Inf
L     	Inf 	Inf 	0.0
-------END FULL SUITE----------
------INSERT [O]---5-->[A]
Graph after inserting:  [O]---5-->[A]
(3,0)	A	A	L
A	0	0	0
A	0	0	0
L	0	0	0
------INSERT M---------
Graph after inserting Vertex: M
(4,0)	A	A	L	M
A	0	0	0	0
A	0	0	0	0
L	0	0	0	0
M	0	0	0	0
-------FULL SUITE----------
(4,0)	A	A	L	M
A	0	0	0	0
A	0	0	0	0
L	0	0	0	0
M	0	0	0	0
DFS:[A][A][L][M]
BFS:[A][A][L][M]
Strongly Connected:
[A]
[A]
[L]
[M]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		A     	A     	L     	M
A     	0.0   	Inf 	Inf 	Inf
A     	Inf 	0.0   	Inf 	Inf
L     	Inf 	Inf 	0.0   	Inf
M     	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
------INSERT [O]---5-->[A]
Graph after inserting:  [O]---5-->[A]
(4,0)	A	A	L	M
A	0	0	0	0
A	0	0	0	0
L	0	0	0	0
M	0	0	0	0
------INSERT N---------
Graph after inserting Vertex: N
(5,0)	A	A	L	M	N
A	0	0	0	0	0
A	0	0	0	0	0
L	0	0	0	0	0
M	0	0	0	0	0
N	0	0	0	0	0
-------FULL SUITE----------
(5,0)	A	A	L	M	N
A	0	0	0	0	0
A	0	0	0	0	0
L	0	0	0	0	0
M	0	0	0	0	0
N	0	0	0	0	0
DFS:[A][A][L][M][N]
BFS:[A][A][L][M][N]
Strongly Connected:
[A]
[A]
[L]
[M]
[N]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		A     	A     	L     	M     	N
A     	0.0   	Inf 	Inf 	Inf 	Inf
A     	Inf 	0.0   	Inf 	Inf 	Inf
L     	Inf 	Inf 	0.0   	Inf 	Inf
M     	Inf 	Inf 	Inf 	0.0   	Inf
N     	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
------INSERT [O]---5-->[A]
Graph after inserting:  [O]---5-->[A]
(5,0)	A	A	L	M	N
A	0	0	0	0	0
A	0	0	0	0	0
L	0	0	0	0	0
M	0	0	0	0	0
N	0	0	0	0	0
------INSERT P---------
Graph after inserting Vertex: P
(6,0)	A	A	L	M	N	P
A	0	0	0	0	0	0
A	0	0	0	0	0	0
L	0	0	0	0	0	0
M	0	0	0	0	0	0
N	0	0	0	0	0	0
P	0	0	0	0	0	0
-------FULL SUITE----------
(6,0)	A	A	L	M	N	P
A	0	0	0	0	0	0
A	0	0	0	0	0	0
L	0	0	0	0	0	0
M	0	0	0	0	0	0
N	0	0	0	0	0	0
P	0	0	0	0	0	0
DFS:[A][A][L][M][N][P]
BFS:[A][A][L][M][N][P]
Strongly Connected:
[A]
[A]
[L]
[M]
[N]
[P]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		A     	A     	L     	M     	N     	P
A     	0.0   	Inf 	Inf 	Inf 	Inf 	Inf
A     	Inf 	0.0   	Inf 	Inf 	Inf 	Inf
L     	Inf 	Inf 	0.0   	Inf 	Inf 	Inf
M     	Inf 	Inf 	Inf 	0.0   	Inf 	Inf
N     	Inf 	Inf 	Inf 	Inf 	0.0   	Inf
P     	Inf 	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
------INSERT [O]---5-->[A]
Graph after inserting:  [O]---5-->[A]
(6,0)	A	A	L	M	N	P
A	0	0	0	0	0	0
A	0	0	0	0	0	0
L	0	0	0	0	0	0
M	0	0	0	0	0	0
N	0	0	0	0	0	0
P	0	0	0	0	0	0
------INSERT R---------
Graph after inserting Vertex: R
(7,0)	A	A	L	M	N	P	R
A	0	0	0	0	0	0	0
A	0	0	0	0	0	0	0
L	0	0	0	0	0	0	0
M	0	0	0	0	0	0	0
N	0	0	0	0	0	0	0
P	0	0	0	0	0	0	0
R	0	0	0	0	0	0	0
-------FULL SUITE----------
(7,0)	A	A	L	M	N	P	R
A	0	0	0	0	0	0	0
A	0	0	0	0	0	0	0
L	0	0	0	0	0	0	0
M	0	0	0	0	0	0	0
N	0	0	0	0	0	0	0
P	0	0	0	0	0	0	0
R	0	0	0	0	0	0	0
DFS:[A][A][L][M][N][P][R]
BFS:[A][A][L][M][N][P][R]
Strongly Connected:
[A]
[A]
[L]
[M]
[N]
[P]
[R]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		A     	A     	L     	M     	N     	P     	R
A     	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
A     	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf
L     	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf
M     	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf
N     	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf
P     	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf
R     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
------INSERT [O]---5-->[A]
Graph after inserting:  [O]---5-->[A]
(7,0)	A	A	L	M	N	P	R
A	0	0	0	0	0	0	0
A	0	0	0	0	0	0	0
L	0	0	0	0	0	0	0
M	0	0	0	0	0	0	0
N	0	0	0	0	0	0	0
P	0	0	0	0	0	0	0
R	0	0	0	0	0	0	0
-------FULL SUITE----------
(7,0)	A	A	L	M	N	P	R
A	0	0	0	0	0	0	0
A	0	0	0	0	0	0	0
L	0	0	0	0	0	0	0
M	0	0	0	0	0	0	0
N	0	0	0	0	0	0	0
P	0	0	0	0	0	0	0
R	0	0	0	0	0	0	0
DFS:[A][A][L][M][N][P][R]
BFS:[A][A][L][M][N][P][R]
Strongly Connected:
[A]
[A]
[L]
[M]
[N]
[P]
[R]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		A     	A     	L     	M     	N     	P     	R
A     	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
A     	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf
L     	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf
M     	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf
N     	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf
P     	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf
R     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
----------------End Simple Tests---------------
*/
    //>graph
    /*
-------------Test: 0 ---Testing file: graph.txt----------
-------FULL SUITE----------
(7,8)	A	J	L	M	N	P	R
A	0	3	0	2	0	0	0
J	0	0	0	0	0	0	4
L	0	0	0	0	0	0	0
M	0	0	0	0	0	0	0
N	0	0	0	0	0	0	-5
P	0	0	3	0	4	0	0
R	0	0	0	2	0	1	0
DFS:[A][J][R][M][P][L][N]
BFS:[A][J][M][R][P][L][N]
Strongly Connected:
[M]
[L]
[N][P][R]
[J]
[A]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		A     	J     	L     	M     	N     	P     	R
A     	0.0   	3.0   	11.0  	2.0   	12.0  	8.0   	7.0
J     	Inf 	0.0   	8.0   	6.0   	9.0   	5.0   	4.0
L     	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf
M     	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf
N     	Inf 	Inf 	-1.0  	-3.0  	0.0   	-4.0  	-5.0
P     	Inf 	Inf 	3.0   	1.0   	4.0   	0.0   	-1.0
R     	Inf 	Inf 	4.0   	2.0   	5.0   	1.0   	0.0
-------END FULL SUITE----------
-------------End Test: 0 -------------
-------------Test: 1 ---Testing file: graph2.txt----------
-------FULL SUITE----------
(3,2)	A	B	C
A	0	0	0
B	0	0	3
C	0	3	0
DFS:[A][B][C]
BFS:[A][B][C]
Strongly Connected:
[A]
[C][B]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		A     	B     	C
A     	0.0   	Inf 	Inf
B     	Inf 	0.0   	3.0
C     	Inf 	3.0   	0.0
-------END FULL SUITE----------
-------------End Test: 1 -------------
*/
    //>graph2
    /*
-------------Test: 2 ---Testing file: Lgraph1.txt----------
-------FULL SUITE----------
(102,202)	0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20	21	22	23	24	25	26	27	28	29	30	31	32	33	34	35	36	37	38	39	40	41	42	43	44	45	46	47	48	49	50	51	52	53	54	55	56	57	58	59	60	61	62	63	64	65	66	67	68	69	70	71	72	73	74	75	76	77	78	79	80	81	82	83	84	85	86	87	88	89	90	91	92	93	94	95	96	97	98	99	100	101
0	0	70	15	60	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
1	70	0	0	0	3	90	35	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
2	15	0	0	0	0	0	0	0	3	10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
3	60	0	0	0	0	0	0	0	0	0	18	30	21	15	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
4	0	3	0	0	0	0	0	0	0	0	0	0	0	0	10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
5	0	90	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	37	16	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
6	0	35	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	37	21	14	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
7	0	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	16	30	80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
8	0	0	3	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	90	21	35	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
9	0	0	10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	16	50	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
10	0	0	0	18	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	90	37	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
11	0	0	0	30	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	21	16	80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
12	0	0	0	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	35	37	14	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
13	0	0	0	15	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	14	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
14	0	0	0	0	10	0	0	0	0	0	0	0	0	0	0	16	50	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
15	0	0	0	0	0	0	0	0	0	0	0	0	0	0	16	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
16	0	0	0	0	0	0	0	0	0	0	0	0	0	0	50	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
17	0	0	0	0	0	37	0	0	0	0	0	0	0	0	0	0	0	0	0	21	14	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
18	0	0	0	0	0	16	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	50	30	15	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
19	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
20	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	14	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
22	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	50	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
23	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	30	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
24	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	15	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
25	0	0	0	0	0	0	37	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	18	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
26	0	0	0	0	0	0	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	30	15	18	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
27	0	0	0	0	0	0	14	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	15	10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
28	0	0	0	0	0	0	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
29	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	18	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
30	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	30	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
31	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	15	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
32	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	18	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
33	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	15	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
34	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
35	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
36	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
37	0	0	0	0	0	0	0	16	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	50	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
38	0	0	0	0	0	0	0	30	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	18	21	15	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
39	0	0	0	0	0	0	0	80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
40	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
41	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	50	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
42	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	18	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
43	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
44	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	15	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
45	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
46	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
47	0	0	0	0	0	0	0	0	90	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	18	37	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
48	0	0	0	0	0	0	0	0	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	16	30	80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
49	0	0	0	0	0	0	0	0	35	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	21	12	14	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
50	0	0	0	0	0	0	0	0	0	16	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	21	30	80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
51	0	0	0	0	0	0	0	0	0	50	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	21	30	21	15	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
52	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	18	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
53	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	37	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
54	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	16	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
55	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	30	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
56	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
57	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
58	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
59	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	14	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
60	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
61	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	30	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
62	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
63	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
64	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	30	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
65	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
66	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	15	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
67	0	0	0	0	0	0	0	0	0	0	90	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	21	35	3	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
68	0	0	0	0	0	0	0	0	0	0	37	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	35	14	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
69	0	0	0	0	0	0	0	0	0	0	0	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	90	35	3	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
70	0	0	0	0	0	0	0	0	0	0	0	16	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	0	0	0	0	0	0	0	0	0	0	0	0	0
71	0	0	0	0	0	0	0	0	0	0	0	80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	0	12	0	0	0	0	0	0	0	0	0	0	0	0
72	0	0	0	0	0	0	0	0	0	0	0	0	35	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	3	90	21	0	0	0	0	0	0	0	0	0
73	0	0	0	0	0	0	0	0	0	0	0	0	37	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	90	0	0	0	0	0	0	0	0
74	0	0	0	0	0	0	0	0	0	0	0	0	14	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	0	0	0	0	0
75	0	0	0	0	0	0	0	0	0	0	0	0	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	80	10	0	0	0	0	0	0
76	0	0	0	0	0	0	0	0	0	0	0	0	0	14	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	35	37	12	0	0
77	0	0	0	0	0	0	0	0	0	0	0	0	0	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	80	12
78	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
79	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	35	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	3	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
81	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	35	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
82	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	14	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
83	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
84	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	90	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
85	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	35	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
86	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	3	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
87	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
88	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
89	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
90	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	3	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
91	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	90	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
92	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
93	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	90	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
94	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
95	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
96	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
97	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	35	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
98	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	37	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
99	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
100	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
101	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
DFS:[0][1][4][14][15][16][5][17][19][20][21][18][22][23][24][6][25][29][26][30][31][32][27][33][34][28][35][36][7][37][40][41][38][42][43][44][39][45][46][2][8][47][52][53][48][54][55][56][49][57][58][59][9][50][60][61][62][51][63][64][65][66][3][10][67][78][79][80][68][81][82][83][11][69][84][85][86][70][88][71][87][89][12][72][90][91][92][73][93][74][96][75][94][95][13][76][97][98][99][77][100][101]
BFS:[0][1][2][3][4][5][6][7][8][9][10][11][12][13][14][17][18][25][26][27][28][37][38][39][47][48][49][50][51][67][68][69][70][71][72][73][74][75][76][77][15][16][19][20][21][22][23][24][29][30][31][32][33][34][35][36][40][41][42][43][44][45][46][52][53][54][55][56][57][58][59][60][61][62][63][64][65][66][78][79][80][81][82][83][84][85][86][88][87][89][90][91][92][93][96][94][95][97][98][99][100][101]
Strongly Connected:
[101][100][77][99][98][97][76][13][95][94][75][96][74][93][73][92][91][90][72][12][89][87][71][88][70][86][85][84][69][11][83][82][81][68][80][79][78][67][10][3][66][65][64][63][51][62][61][60][50][9][59][58][57][49][56][55][54][48][53][52][47][8][2][46][45][39][44][43][42][38][41][40][37][7][36][35][28][34][33][27][32][31][30][26][29][25][6][24][23][22][18][21][20][19][17][5][16][15][14][4][1][0]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
Distance from: [2] TO: [6]--> 120.0
------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5     	6     	7     	8     	9     	10    	11    	12    	13    	14    	15    	16    	17    	18    	19    	20    	21    	22    	23    	24    	25    	26    	27    	28    	29    	30    	31    	32    	33    	34    	35    	36    	37    	38    	39    	40    	41    	42    	43    	44    	45    	46    	47    	48    	49    	50    	51    	52    	53    	54    	55    	56    	57    	58    	59    	60    	61    	62    	63    	64    	65    	66    	67    	68    	69    	70    	71    	72    	73    	74    	75    	76    	77    	78    	79    	80    	81    	82    	83    	84    	85    	86    	87    	88    	89    	90    	91    	92    	93    	94    	95    	96    	97    	98    	99    	100   	101
0     	0.0   	70.0  	15.0  	60.0  	73.0  	160.0 	105.0 	91.0  	18.0  	25.0  	78.0  	90.0  	81.0  	75.0  	83.0  	99.0  	133.0 	197.0 	176.0 	218.0 	211.0 	209.0 	226.0 	206.0 	191.0 	142.0 	126.0 	119.0 	117.0 	160.0 	156.0 	141.0 	144.0 	134.0 	129.0 	127.0 	197.0 	107.0 	121.0 	171.0 	117.0 	157.0 	139.0 	142.0 	136.0 	181.0 	183.0 	108.0 	39.0  	53.0  	41.0  	75.0  	126.0 	145.0 	55.0  	69.0  	119.0 	74.0  	65.0  	67.0  	62.0  	71.0  	121.0 	96.0  	105.0 	96.0  	90.0  	168.0 	115.0 	111.0 	106.0 	170.0 	116.0 	118.0 	95.0  	93.0  	89.0  	87.0  	189.0 	203.0 	171.0 	150.0 	129.0 	127.0 	201.0 	146.0 	114.0 	180.0 	116.0 	182.0 	119.0 	206.0 	137.0 	208.0 	173.0 	103.0 	105.0 	124.0 	126.0 	101.0 	167.0 	99.0
1     	70.0  	0.0   	85.0  	130.0 	3.0   	90.0  	35.0  	21.0  	88.0  	95.0  	148.0 	160.0 	151.0 	145.0 	13.0  	29.0  	63.0  	127.0 	106.0 	148.0 	141.0 	139.0 	156.0 	136.0 	121.0 	72.0  	56.0  	49.0  	47.0  	90.0  	86.0  	71.0  	74.0  	64.0  	59.0  	57.0  	127.0 	37.0  	51.0  	101.0 	47.0  	87.0  	69.0  	72.0  	66.0  	111.0 	113.0 	178.0 	109.0 	123.0 	111.0 	145.0 	196.0 	215.0 	125.0 	139.0 	189.0 	144.0 	135.0 	137.0 	132.0 	141.0 	191.0 	166.0 	175.0 	166.0 	160.0 	238.0 	185.0 	181.0 	176.0 	240.0 	186.0 	188.0 	165.0 	163.0 	159.0 	157.0 	259.0 	273.0 	241.0 	220.0 	199.0 	197.0 	271.0 	216.0 	184.0 	250.0 	186.0 	252.0 	189.0 	276.0 	207.0 	278.0 	243.0 	173.0 	175.0 	194.0 	196.0 	171.0 	237.0 	169.0
2     	15.0  	85.0  	0.0   	75.0  	88.0  	175.0 	120.0 	106.0 	3.0   	10.0  	93.0  	105.0 	96.0  	90.0  	98.0  	114.0 	148.0 	212.0 	191.0 	233.0 	226.0 	224.0 	241.0 	221.0 	206.0 	157.0 	141.0 	134.0 	132.0 	175.0 	171.0 	156.0 	159.0 	149.0 	144.0 	142.0 	212.0 	122.0 	136.0 	186.0 	132.0 	172.0 	154.0 	157.0 	151.0 	196.0 	198.0 	93.0  	24.0  	38.0  	26.0  	60.0  	111.0 	130.0 	40.0  	54.0  	104.0 	59.0  	50.0  	52.0  	47.0  	56.0  	106.0 	81.0  	90.0  	81.0  	75.0  	183.0 	130.0 	126.0 	121.0 	185.0 	131.0 	133.0 	110.0 	108.0 	104.0 	102.0 	204.0 	218.0 	186.0 	165.0 	144.0 	142.0 	216.0 	161.0 	129.0 	195.0 	131.0 	197.0 	134.0 	221.0 	152.0 	223.0 	188.0 	118.0 	120.0 	139.0 	141.0 	116.0 	182.0 	114.0
3     	60.0  	130.0 	75.0  	0.0   	133.0 	220.0 	165.0 	151.0 	78.0  	85.0  	18.0  	30.0  	21.0  	15.0  	143.0 	159.0 	193.0 	257.0 	236.0 	278.0 	271.0 	269.0 	286.0 	266.0 	251.0 	202.0 	186.0 	179.0 	177.0 	220.0 	216.0 	201.0 	204.0 	194.0 	189.0 	187.0 	257.0 	167.0 	181.0 	231.0 	177.0 	217.0 	199.0 	202.0 	196.0 	241.0 	243.0 	168.0 	99.0  	113.0 	101.0 	135.0 	186.0 	205.0 	115.0 	129.0 	179.0 	134.0 	125.0 	127.0 	122.0 	131.0 	181.0 	156.0 	165.0 	156.0 	150.0 	108.0 	55.0  	51.0  	46.0  	110.0 	56.0  	58.0  	35.0  	33.0  	29.0  	27.0  	129.0 	143.0 	111.0 	90.0  	69.0  	67.0  	141.0 	86.0  	54.0  	120.0 	56.0  	122.0 	59.0  	146.0 	77.0  	148.0 	113.0 	43.0  	45.0  	64.0  	66.0  	41.0  	107.0 	39.0
4     	73.0  	3.0   	88.0  	133.0 	0.0   	93.0  	38.0  	24.0  	91.0  	98.0  	151.0 	163.0 	154.0 	148.0 	10.0  	26.0  	60.0  	130.0 	109.0 	151.0 	144.0 	142.0 	159.0 	139.0 	124.0 	75.0  	59.0  	52.0  	50.0  	93.0  	89.0  	74.0  	77.0  	67.0  	62.0  	60.0  	130.0 	40.0  	54.0  	104.0 	50.0  	90.0  	72.0  	75.0  	69.0  	114.0 	116.0 	181.0 	112.0 	126.0 	114.0 	148.0 	199.0 	218.0 	128.0 	142.0 	192.0 	147.0 	138.0 	140.0 	135.0 	144.0 	194.0 	169.0 	178.0 	169.0 	163.0 	241.0 	188.0 	184.0 	179.0 	243.0 	189.0 	191.0 	168.0 	166.0 	162.0 	160.0 	262.0 	276.0 	244.0 	223.0 	202.0 	200.0 	274.0 	219.0 	187.0 	253.0 	189.0 	255.0 	192.0 	279.0 	210.0 	281.0 	246.0 	176.0 	178.0 	197.0 	199.0 	174.0 	240.0 	172.0
5     	160.0 	90.0  	175.0 	220.0 	93.0  	0.0   	125.0 	111.0 	178.0 	185.0 	238.0 	250.0 	241.0 	235.0 	103.0 	119.0 	153.0 	37.0  	16.0  	58.0  	51.0  	49.0  	66.0  	46.0  	31.0  	162.0 	146.0 	139.0 	137.0 	180.0 	176.0 	161.0 	164.0 	154.0 	149.0 	147.0 	217.0 	127.0 	141.0 	191.0 	137.0 	177.0 	159.0 	162.0 	156.0 	201.0 	203.0 	268.0 	199.0 	213.0 	201.0 	235.0 	286.0 	305.0 	215.0 	229.0 	279.0 	234.0 	225.0 	227.0 	222.0 	231.0 	281.0 	256.0 	265.0 	256.0 	250.0 	328.0 	275.0 	271.0 	266.0 	330.0 	276.0 	278.0 	255.0 	253.0 	249.0 	247.0 	349.0 	363.0 	331.0 	310.0 	289.0 	287.0 	361.0 	306.0 	274.0 	340.0 	276.0 	342.0 	279.0 	366.0 	297.0 	368.0 	333.0 	263.0 	265.0 	284.0 	286.0 	261.0 	327.0 	259.0
6     	105.0 	35.0  	120.0 	165.0 	38.0  	125.0 	0.0   	56.0  	123.0 	130.0 	183.0 	195.0 	186.0 	180.0 	48.0  	64.0  	98.0  	162.0 	141.0 	183.0 	176.0 	174.0 	191.0 	171.0 	156.0 	37.0  	21.0  	14.0  	12.0  	55.0  	51.0  	36.0  	39.0  	29.0  	24.0  	22.0  	92.0  	72.0  	86.0  	136.0 	82.0  	122.0 	104.0 	107.0 	101.0 	146.0 	148.0 	213.0 	144.0 	158.0 	146.0 	180.0 	231.0 	250.0 	160.0 	174.0 	224.0 	179.0 	170.0 	172.0 	167.0 	176.0 	226.0 	201.0 	210.0 	201.0 	195.0 	273.0 	220.0 	216.0 	211.0 	275.0 	221.0 	223.0 	200.0 	198.0 	194.0 	192.0 	294.0 	308.0 	276.0 	255.0 	234.0 	232.0 	306.0 	251.0 	219.0 	285.0 	221.0 	287.0 	224.0 	311.0 	242.0 	313.0 	278.0 	208.0 	210.0 	229.0 	231.0 	206.0 	272.0 	204.0
7     	91.0  	21.0  	106.0 	151.0 	24.0  	111.0 	56.0  	0.0   	109.0 	116.0 	169.0 	181.0 	172.0 	166.0 	34.0  	50.0  	84.0  	148.0 	127.0 	169.0 	162.0 	160.0 	177.0 	157.0 	142.0 	93.0  	77.0  	70.0  	68.0  	111.0 	107.0 	92.0  	95.0  	85.0  	80.0  	78.0  	148.0 	16.0  	30.0  	80.0  	26.0  	66.0  	48.0  	51.0  	45.0  	90.0  	92.0  	199.0 	130.0 	144.0 	132.0 	166.0 	217.0 	236.0 	146.0 	160.0 	210.0 	165.0 	156.0 	158.0 	153.0 	162.0 	212.0 	187.0 	196.0 	187.0 	181.0 	259.0 	206.0 	202.0 	197.0 	261.0 	207.0 	209.0 	186.0 	184.0 	180.0 	178.0 	280.0 	294.0 	262.0 	241.0 	220.0 	218.0 	292.0 	237.0 	205.0 	271.0 	207.0 	273.0 	210.0 	297.0 	228.0 	299.0 	264.0 	194.0 	196.0 	215.0 	217.0 	192.0 	258.0 	190.0
8     	18.0  	88.0  	3.0   	78.0  	91.0  	178.0 	123.0 	109.0 	0.0   	13.0  	96.0  	108.0 	99.0  	93.0  	101.0 	117.0 	151.0 	215.0 	194.0 	236.0 	229.0 	227.0 	244.0 	224.0 	209.0 	160.0 	144.0 	137.0 	135.0 	178.0 	174.0 	159.0 	162.0 	152.0 	147.0 	145.0 	215.0 	125.0 	139.0 	189.0 	135.0 	175.0 	157.0 	160.0 	154.0 	199.0 	201.0 	90.0  	21.0  	35.0  	29.0  	63.0  	108.0 	127.0 	37.0  	51.0  	101.0 	56.0  	47.0  	49.0  	50.0  	59.0  	109.0 	84.0  	93.0  	84.0  	78.0  	186.0 	133.0 	129.0 	124.0 	188.0 	134.0 	136.0 	113.0 	111.0 	107.0 	105.0 	207.0 	221.0 	189.0 	168.0 	147.0 	145.0 	219.0 	164.0 	132.0 	198.0 	134.0 	200.0 	137.0 	224.0 	155.0 	226.0 	191.0 	121.0 	123.0 	142.0 	144.0 	119.0 	185.0 	117.0
9     	25.0  	95.0  	10.0  	85.0  	98.0  	185.0 	130.0 	116.0 	13.0  	0.0   	103.0 	115.0 	106.0 	100.0 	108.0 	124.0 	158.0 	222.0 	201.0 	243.0 	236.0 	234.0 	251.0 	231.0 	216.0 	167.0 	151.0 	144.0 	142.0 	185.0 	181.0 	166.0 	169.0 	159.0 	154.0 	152.0 	222.0 	132.0 	146.0 	196.0 	142.0 	182.0 	164.0 	167.0 	161.0 	206.0 	208.0 	103.0 	34.0  	48.0  	16.0  	50.0  	121.0 	140.0 	50.0  	64.0  	114.0 	69.0  	60.0  	62.0  	37.0  	46.0  	96.0  	71.0  	80.0  	71.0  	65.0  	193.0 	140.0 	136.0 	131.0 	195.0 	141.0 	143.0 	120.0 	118.0 	114.0 	112.0 	214.0 	228.0 	196.0 	175.0 	154.0 	152.0 	226.0 	171.0 	139.0 	205.0 	141.0 	207.0 	144.0 	231.0 	162.0 	233.0 	198.0 	128.0 	130.0 	149.0 	151.0 	126.0 	192.0 	124.0
10    	78.0  	148.0 	93.0  	18.0  	151.0 	238.0 	183.0 	169.0 	96.0  	103.0 	0.0   	48.0  	39.0  	33.0  	161.0 	177.0 	211.0 	275.0 	254.0 	296.0 	289.0 	287.0 	304.0 	284.0 	269.0 	220.0 	204.0 	197.0 	195.0 	238.0 	234.0 	219.0 	222.0 	212.0 	207.0 	205.0 	275.0 	185.0 	199.0 	249.0 	195.0 	235.0 	217.0 	220.0 	214.0 	259.0 	261.0 	186.0 	117.0 	131.0 	119.0 	153.0 	204.0 	223.0 	133.0 	147.0 	197.0 	152.0 	143.0 	145.0 	140.0 	149.0 	199.0 	174.0 	183.0 	174.0 	168.0 	90.0  	37.0  	69.0  	64.0  	128.0 	74.0  	76.0  	53.0  	51.0  	47.0  	45.0  	111.0 	125.0 	93.0  	72.0  	51.0  	49.0  	159.0 	104.0 	72.0  	138.0 	74.0  	140.0 	77.0  	164.0 	95.0  	166.0 	131.0 	61.0  	63.0  	82.0  	84.0  	59.0  	125.0 	57.0
11    	90.0  	160.0 	105.0 	30.0  	163.0 	250.0 	195.0 	181.0 	108.0 	115.0 	48.0  	0.0   	51.0  	45.0  	173.0 	189.0 	223.0 	287.0 	266.0 	308.0 	301.0 	299.0 	316.0 	296.0 	281.0 	232.0 	216.0 	209.0 	207.0 	250.0 	246.0 	231.0 	234.0 	224.0 	219.0 	217.0 	287.0 	197.0 	211.0 	261.0 	207.0 	247.0 	229.0 	232.0 	226.0 	271.0 	273.0 	198.0 	129.0 	143.0 	131.0 	165.0 	216.0 	235.0 	145.0 	159.0 	209.0 	164.0 	155.0 	157.0 	152.0 	161.0 	211.0 	186.0 	195.0 	186.0 	180.0 	138.0 	85.0  	21.0  	16.0  	80.0  	86.0  	88.0  	65.0  	63.0  	59.0  	57.0  	159.0 	173.0 	141.0 	120.0 	99.0  	97.0  	111.0 	56.0  	24.0  	90.0  	26.0  	92.0  	89.0  	176.0 	107.0 	178.0 	143.0 	73.0  	75.0  	94.0  	96.0  	71.0  	137.0 	69.0
12    	81.0  	151.0 	96.0  	21.0  	154.0 	241.0 	186.0 	172.0 	99.0  	106.0 	39.0  	51.0  	0.0   	36.0  	164.0 	180.0 	214.0 	278.0 	257.0 	299.0 	292.0 	290.0 	307.0 	287.0 	272.0 	223.0 	207.0 	200.0 	198.0 	241.0 	237.0 	222.0 	225.0 	215.0 	210.0 	208.0 	278.0 	188.0 	202.0 	252.0 	198.0 	238.0 	220.0 	223.0 	217.0 	262.0 	264.0 	189.0 	120.0 	134.0 	122.0 	156.0 	207.0 	226.0 	136.0 	150.0 	200.0 	155.0 	146.0 	148.0 	143.0 	152.0 	202.0 	177.0 	186.0 	177.0 	171.0 	129.0 	76.0  	72.0  	67.0  	131.0 	35.0  	37.0  	14.0  	12.0  	50.0  	48.0  	150.0 	164.0 	132.0 	111.0 	90.0  	88.0  	162.0 	107.0 	75.0  	141.0 	77.0  	143.0 	38.0  	125.0 	56.0  	127.0 	92.0  	22.0  	24.0  	85.0  	87.0  	62.0  	128.0 	60.0
13    	75.0  	145.0 	90.0  	15.0  	148.0 	235.0 	180.0 	166.0 	93.0  	100.0 	33.0  	45.0  	36.0  	0.0   	158.0 	174.0 	208.0 	272.0 	251.0 	293.0 	286.0 	284.0 	301.0 	281.0 	266.0 	217.0 	201.0 	194.0 	192.0 	235.0 	231.0 	216.0 	219.0 	209.0 	204.0 	202.0 	272.0 	182.0 	196.0 	246.0 	192.0 	232.0 	214.0 	217.0 	211.0 	256.0 	258.0 	183.0 	114.0 	128.0 	116.0 	150.0 	201.0 	220.0 	130.0 	144.0 	194.0 	149.0 	140.0 	142.0 	137.0 	146.0 	196.0 	171.0 	180.0 	171.0 	165.0 	123.0 	70.0  	66.0  	61.0  	125.0 	71.0  	73.0  	50.0  	48.0  	14.0  	12.0  	144.0 	158.0 	126.0 	105.0 	84.0  	82.0  	156.0 	101.0 	69.0  	135.0 	71.0  	137.0 	74.0  	161.0 	92.0  	163.0 	128.0 	58.0  	60.0  	49.0  	51.0  	26.0  	92.0  	24.0
14    	83.0  	13.0  	98.0  	143.0 	10.0  	103.0 	48.0  	34.0  	101.0 	108.0 	161.0 	173.0 	164.0 	158.0 	0.0   	16.0  	50.0  	140.0 	119.0 	161.0 	154.0 	152.0 	169.0 	149.0 	134.0 	85.0  	69.0  	62.0  	60.0  	103.0 	99.0  	84.0  	87.0  	77.0  	72.0  	70.0  	140.0 	50.0  	64.0  	114.0 	60.0  	100.0 	82.0  	85.0  	79.0  	124.0 	126.0 	191.0 	122.0 	136.0 	124.0 	158.0 	209.0 	228.0 	138.0 	152.0 	202.0 	157.0 	148.0 	150.0 	145.0 	154.0 	204.0 	179.0 	188.0 	179.0 	173.0 	251.0 	198.0 	194.0 	189.0 	253.0 	199.0 	201.0 	178.0 	176.0 	172.0 	170.0 	272.0 	286.0 	254.0 	233.0 	212.0 	210.0 	284.0 	229.0 	197.0 	263.0 	199.0 	265.0 	202.0 	289.0 	220.0 	291.0 	256.0 	186.0 	188.0 	207.0 	209.0 	184.0 	250.0 	182.0
15    	99.0  	29.0  	114.0 	159.0 	26.0  	119.0 	64.0  	50.0  	117.0 	124.0 	177.0 	189.0 	180.0 	174.0 	16.0  	0.0   	66.0  	156.0 	135.0 	177.0 	170.0 	168.0 	185.0 	165.0 	150.0 	101.0 	85.0  	78.0  	76.0  	119.0 	115.0 	100.0 	103.0 	93.0  	88.0  	86.0  	156.0 	66.0  	80.0  	130.0 	76.0  	116.0 	98.0  	101.0 	95.0  	140.0 	142.0 	207.0 	138.0 	152.0 	140.0 	174.0 	225.0 	244.0 	154.0 	168.0 	218.0 	173.0 	164.0 	166.0 	161.0 	170.0 	220.0 	195.0 	204.0 	195.0 	189.0 	267.0 	214.0 	210.0 	205.0 	269.0 	215.0 	217.0 	194.0 	192.0 	188.0 	186.0 	288.0 	302.0 	270.0 	249.0 	228.0 	226.0 	300.0 	245.0 	213.0 	279.0 	215.0 	281.0 	218.0 	305.0 	236.0 	307.0 	272.0 	202.0 	204.0 	223.0 	225.0 	200.0 	266.0 	198.0
16    	133.0 	63.0  	148.0 	193.0 	60.0  	153.0 	98.0  	84.0  	151.0 	158.0 	211.0 	223.0 	214.0 	208.0 	50.0  	66.0  	0.0   	190.0 	169.0 	211.0 	204.0 	202.0 	219.0 	199.0 	184.0 	135.0 	119.0 	112.0 	110.0 	153.0 	149.0 	134.0 	137.0 	127.0 	122.0 	120.0 	190.0 	100.0 	114.0 	164.0 	110.0 	150.0 	132.0 	135.0 	129.0 	174.0 	176.0 	241.0 	172.0 	186.0 	174.0 	208.0 	259.0 	278.0 	188.0 	202.0 	252.0 	207.0 	198.0 	200.0 	195.0 	204.0 	254.0 	229.0 	238.0 	229.0 	223.0 	301.0 	248.0 	244.0 	239.0 	303.0 	249.0 	251.0 	228.0 	226.0 	222.0 	220.0 	322.0 	336.0 	304.0 	283.0 	262.0 	260.0 	334.0 	279.0 	247.0 	313.0 	249.0 	315.0 	252.0 	339.0 	270.0 	341.0 	306.0 	236.0 	238.0 	257.0 	259.0 	234.0 	300.0 	232.0
17    	197.0 	127.0 	212.0 	257.0 	130.0 	37.0  	162.0 	148.0 	215.0 	222.0 	275.0 	287.0 	278.0 	272.0 	140.0 	156.0 	190.0 	0.0   	53.0  	21.0  	14.0  	12.0  	103.0 	83.0  	68.0  	199.0 	183.0 	176.0 	174.0 	217.0 	213.0 	198.0 	201.0 	191.0 	186.0 	184.0 	254.0 	164.0 	178.0 	228.0 	174.0 	214.0 	196.0 	199.0 	193.0 	238.0 	240.0 	305.0 	236.0 	250.0 	238.0 	272.0 	323.0 	342.0 	252.0 	266.0 	316.0 	271.0 	262.0 	264.0 	259.0 	268.0 	318.0 	293.0 	302.0 	293.0 	287.0 	365.0 	312.0 	308.0 	303.0 	367.0 	313.0 	315.0 	292.0 	290.0 	286.0 	284.0 	386.0 	400.0 	368.0 	347.0 	326.0 	324.0 	398.0 	343.0 	311.0 	377.0 	313.0 	379.0 	316.0 	403.0 	334.0 	405.0 	370.0 	300.0 	302.0 	321.0 	323.0 	298.0 	364.0 	296.0
18    	176.0 	106.0 	191.0 	236.0 	109.0 	16.0  	141.0 	127.0 	194.0 	201.0 	254.0 	266.0 	257.0 	251.0 	119.0 	135.0 	169.0 	53.0  	0.0   	74.0  	67.0  	65.0  	50.0  	30.0  	15.0  	178.0 	162.0 	155.0 	153.0 	196.0 	192.0 	177.0 	180.0 	170.0 	165.0 	163.0 	233.0 	143.0 	157.0 	207.0 	153.0 	193.0 	175.0 	178.0 	172.0 	217.0 	219.0 	284.0 	215.0 	229.0 	217.0 	251.0 	302.0 	321.0 	231.0 	245.0 	295.0 	250.0 	241.0 	243.0 	238.0 	247.0 	297.0 	272.0 	281.0 	272.0 	266.0 	344.0 	291.0 	287.0 	282.0 	346.0 	292.0 	294.0 	271.0 	269.0 	265.0 	263.0 	365.0 	379.0 	347.0 	326.0 	305.0 	303.0 	377.0 	322.0 	290.0 	356.0 	292.0 	358.0 	295.0 	382.0 	313.0 	384.0 	349.0 	279.0 	281.0 	300.0 	302.0 	277.0 	343.0 	275.0
19    	218.0 	148.0 	233.0 	278.0 	151.0 	58.0  	183.0 	169.0 	236.0 	243.0 	296.0 	308.0 	299.0 	293.0 	161.0 	177.0 	211.0 	21.0  	74.0  	0.0   	35.0  	33.0  	124.0 	104.0 	89.0  	220.0 	204.0 	197.0 	195.0 	238.0 	234.0 	219.0 	222.0 	212.0 	207.0 	205.0 	275.0 	185.0 	199.0 	249.0 	195.0 	235.0 	217.0 	220.0 	214.0 	259.0 	261.0 	326.0 	257.0 	271.0 	259.0 	293.0 	344.0 	363.0 	273.0 	287.0 	337.0 	292.0 	283.0 	285.0 	280.0 	289.0 	339.0 	314.0 	323.0 	314.0 	308.0 	386.0 	333.0 	329.0 	324.0 	388.0 	334.0 	336.0 	313.0 	311.0 	307.0 	305.0 	407.0 	421.0 	389.0 	368.0 	347.0 	345.0 	419.0 	364.0 	332.0 	398.0 	334.0 	400.0 	337.0 	424.0 	355.0 	426.0 	391.0 	321.0 	323.0 	342.0 	344.0 	319.0 	385.0 	317.0
20    	211.0 	141.0 	226.0 	271.0 	144.0 	51.0  	176.0 	162.0 	229.0 	236.0 	289.0 	301.0 	292.0 	286.0 	154.0 	170.0 	204.0 	14.0  	67.0  	35.0  	0.0   	26.0  	117.0 	97.0  	82.0  	213.0 	197.0 	190.0 	188.0 	231.0 	227.0 	212.0 	215.0 	205.0 	200.0 	198.0 	268.0 	178.0 	192.0 	242.0 	188.0 	228.0 	210.0 	213.0 	207.0 	252.0 	254.0 	319.0 	250.0 	264.0 	252.0 	286.0 	337.0 	356.0 	266.0 	280.0 	330.0 	285.0 	276.0 	278.0 	273.0 	282.0 	332.0 	307.0 	316.0 	307.0 	301.0 	379.0 	326.0 	322.0 	317.0 	381.0 	327.0 	329.0 	306.0 	304.0 	300.0 	298.0 	400.0 	414.0 	382.0 	361.0 	340.0 	338.0 	412.0 	357.0 	325.0 	391.0 	327.0 	393.0 	330.0 	417.0 	348.0 	419.0 	384.0 	314.0 	316.0 	335.0 	337.0 	312.0 	378.0 	310.0
21    	209.0 	139.0 	224.0 	269.0 	142.0 	49.0  	174.0 	160.0 	227.0 	234.0 	287.0 	299.0 	290.0 	284.0 	152.0 	168.0 	202.0 	12.0  	65.0  	33.0  	26.0  	0.0   	115.0 	95.0  	80.0  	211.0 	195.0 	188.0 	186.0 	229.0 	225.0 	210.0 	213.0 	203.0 	198.0 	196.0 	266.0 	176.0 	190.0 	240.0 	186.0 	226.0 	208.0 	211.0 	205.0 	250.0 	252.0 	317.0 	248.0 	262.0 	250.0 	284.0 	335.0 	354.0 	264.0 	278.0 	328.0 	283.0 	274.0 	276.0 	271.0 	280.0 	330.0 	305.0 	314.0 	305.0 	299.0 	377.0 	324.0 	320.0 	315.0 	379.0 	325.0 	327.0 	304.0 	302.0 	298.0 	296.0 	398.0 	412.0 	380.0 	359.0 	338.0 	336.0 	410.0 	355.0 	323.0 	389.0 	325.0 	391.0 	328.0 	415.0 	346.0 	417.0 	382.0 	312.0 	314.0 	333.0 	335.0 	310.0 	376.0 	308.0
22    	226.0 	156.0 	241.0 	286.0 	159.0 	66.0  	191.0 	177.0 	244.0 	251.0 	304.0 	316.0 	307.0 	301.0 	169.0 	185.0 	219.0 	103.0 	50.0  	124.0 	117.0 	115.0 	0.0   	80.0  	65.0  	228.0 	212.0 	205.0 	203.0 	246.0 	242.0 	227.0 	230.0 	220.0 	215.0 	213.0 	283.0 	193.0 	207.0 	257.0 	203.0 	243.0 	225.0 	228.0 	222.0 	267.0 	269.0 	334.0 	265.0 	279.0 	267.0 	301.0 	352.0 	371.0 	281.0 	295.0 	345.0 	300.0 	291.0 	293.0 	288.0 	297.0 	347.0 	322.0 	331.0 	322.0 	316.0 	394.0 	341.0 	337.0 	332.0 	396.0 	342.0 	344.0 	321.0 	319.0 	315.0 	313.0 	415.0 	429.0 	397.0 	376.0 	355.0 	353.0 	427.0 	372.0 	340.0 	406.0 	342.0 	408.0 	345.0 	432.0 	363.0 	434.0 	399.0 	329.0 	331.0 	350.0 	352.0 	327.0 	393.0 	325.0
23    	206.0 	136.0 	221.0 	266.0 	139.0 	46.0  	171.0 	157.0 	224.0 	231.0 	284.0 	296.0 	287.0 	281.0 	149.0 	165.0 	199.0 	83.0  	30.0  	104.0 	97.0  	95.0  	80.0  	0.0   	45.0  	208.0 	192.0 	185.0 	183.0 	226.0 	222.0 	207.0 	210.0 	200.0 	195.0 	193.0 	263.0 	173.0 	187.0 	237.0 	183.0 	223.0 	205.0 	208.0 	202.0 	247.0 	249.0 	314.0 	245.0 	259.0 	247.0 	281.0 	332.0 	351.0 	261.0 	275.0 	325.0 	280.0 	271.0 	273.0 	268.0 	277.0 	327.0 	302.0 	311.0 	302.0 	296.0 	374.0 	321.0 	317.0 	312.0 	376.0 	322.0 	324.0 	301.0 	299.0 	295.0 	293.0 	395.0 	409.0 	377.0 	356.0 	335.0 	333.0 	407.0 	352.0 	320.0 	386.0 	322.0 	388.0 	325.0 	412.0 	343.0 	414.0 	379.0 	309.0 	311.0 	330.0 	332.0 	307.0 	373.0 	305.0
24    	191.0 	121.0 	206.0 	251.0 	124.0 	31.0  	156.0 	142.0 	209.0 	216.0 	269.0 	281.0 	272.0 	266.0 	134.0 	150.0 	184.0 	68.0  	15.0  	89.0  	82.0  	80.0  	65.0  	45.0  	0.0   	193.0 	177.0 	170.0 	168.0 	211.0 	207.0 	192.0 	195.0 	185.0 	180.0 	178.0 	248.0 	158.0 	172.0 	222.0 	168.0 	208.0 	190.0 	193.0 	187.0 	232.0 	234.0 	299.0 	230.0 	244.0 	232.0 	266.0 	317.0 	336.0 	246.0 	260.0 	310.0 	265.0 	256.0 	258.0 	253.0 	262.0 	312.0 	287.0 	296.0 	287.0 	281.0 	359.0 	306.0 	302.0 	297.0 	361.0 	307.0 	309.0 	286.0 	284.0 	280.0 	278.0 	380.0 	394.0 	362.0 	341.0 	320.0 	318.0 	392.0 	337.0 	305.0 	371.0 	307.0 	373.0 	310.0 	397.0 	328.0 	399.0 	364.0 	294.0 	296.0 	315.0 	317.0 	292.0 	358.0 	290.0
25    	142.0 	72.0  	157.0 	202.0 	75.0  	162.0 	37.0  	93.0  	160.0 	167.0 	220.0 	232.0 	223.0 	217.0 	85.0  	101.0 	135.0 	199.0 	178.0 	220.0 	213.0 	211.0 	228.0 	208.0 	193.0 	0.0   	58.0  	51.0  	49.0  	18.0  	88.0  	73.0  	76.0  	66.0  	61.0  	59.0  	129.0 	109.0 	123.0 	173.0 	119.0 	159.0 	141.0 	144.0 	138.0 	183.0 	185.0 	250.0 	181.0 	195.0 	183.0 	217.0 	268.0 	287.0 	197.0 	211.0 	261.0 	216.0 	207.0 	209.0 	204.0 	213.0 	263.0 	238.0 	247.0 	238.0 	232.0 	310.0 	257.0 	253.0 	248.0 	312.0 	258.0 	260.0 	237.0 	235.0 	231.0 	229.0 	331.0 	345.0 	313.0 	292.0 	271.0 	269.0 	343.0 	288.0 	256.0 	322.0 	258.0 	324.0 	261.0 	348.0 	279.0 	350.0 	315.0 	245.0 	247.0 	266.0 	268.0 	243.0 	309.0 	241.0
26    	126.0 	56.0  	141.0 	186.0 	59.0  	146.0 	21.0  	77.0  	144.0 	151.0 	204.0 	216.0 	207.0 	201.0 	69.0  	85.0  	119.0 	183.0 	162.0 	204.0 	197.0 	195.0 	212.0 	192.0 	177.0 	58.0  	0.0   	35.0  	33.0  	76.0  	30.0  	15.0  	18.0  	50.0  	45.0  	43.0  	113.0 	93.0  	107.0 	157.0 	103.0 	143.0 	125.0 	128.0 	122.0 	167.0 	169.0 	234.0 	165.0 	179.0 	167.0 	201.0 	252.0 	271.0 	181.0 	195.0 	245.0 	200.0 	191.0 	193.0 	188.0 	197.0 	247.0 	222.0 	231.0 	222.0 	216.0 	294.0 	241.0 	237.0 	232.0 	296.0 	242.0 	244.0 	221.0 	219.0 	215.0 	213.0 	315.0 	329.0 	297.0 	276.0 	255.0 	253.0 	327.0 	272.0 	240.0 	306.0 	242.0 	308.0 	245.0 	332.0 	263.0 	334.0 	299.0 	229.0 	231.0 	250.0 	252.0 	227.0 	293.0 	225.0
27    	119.0 	49.0  	134.0 	179.0 	52.0  	139.0 	14.0  	70.0  	137.0 	144.0 	197.0 	209.0 	200.0 	194.0 	62.0  	78.0  	112.0 	176.0 	155.0 	197.0 	190.0 	188.0 	205.0 	185.0 	170.0 	51.0  	35.0  	0.0   	26.0  	69.0  	65.0  	50.0  	53.0  	15.0  	10.0  	36.0  	106.0 	86.0  	100.0 	150.0 	96.0  	136.0 	118.0 	121.0 	115.0 	160.0 	162.0 	227.0 	158.0 	172.0 	160.0 	194.0 	245.0 	264.0 	174.0 	188.0 	238.0 	193.0 	184.0 	186.0 	181.0 	190.0 	240.0 	215.0 	224.0 	215.0 	209.0 	287.0 	234.0 	230.0 	225.0 	289.0 	235.0 	237.0 	214.0 	212.0 	208.0 	206.0 	308.0 	322.0 	290.0 	269.0 	248.0 	246.0 	320.0 	265.0 	233.0 	299.0 	235.0 	301.0 	238.0 	325.0 	256.0 	327.0 	292.0 	222.0 	224.0 	243.0 	245.0 	220.0 	286.0 	218.0
28    	117.0 	47.0  	132.0 	177.0 	50.0  	137.0 	12.0  	68.0  	135.0 	142.0 	195.0 	207.0 	198.0 	192.0 	60.0  	76.0  	110.0 	174.0 	153.0 	195.0 	188.0 	186.0 	203.0 	183.0 	168.0 	49.0  	33.0  	26.0  	0.0   	67.0  	63.0  	48.0  	51.0  	41.0  	36.0  	10.0  	80.0  	84.0  	98.0  	148.0 	94.0  	134.0 	116.0 	119.0 	113.0 	158.0 	160.0 	225.0 	156.0 	170.0 	158.0 	192.0 	243.0 	262.0 	172.0 	186.0 	236.0 	191.0 	182.0 	184.0 	179.0 	188.0 	238.0 	213.0 	222.0 	213.0 	207.0 	285.0 	232.0 	228.0 	223.0 	287.0 	233.0 	235.0 	212.0 	210.0 	206.0 	204.0 	306.0 	320.0 	288.0 	267.0 	246.0 	244.0 	318.0 	263.0 	231.0 	297.0 	233.0 	299.0 	236.0 	323.0 	254.0 	325.0 	290.0 	220.0 	222.0 	241.0 	243.0 	218.0 	284.0 	216.0
29    	160.0 	90.0  	175.0 	220.0 	93.0  	180.0 	55.0  	111.0 	178.0 	185.0 	238.0 	250.0 	241.0 	235.0 	103.0 	119.0 	153.0 	217.0 	196.0 	238.0 	231.0 	229.0 	246.0 	226.0 	211.0 	18.0  	76.0  	69.0  	67.0  	0.0   	106.0 	91.0  	94.0  	84.0  	79.0  	77.0  	147.0 	127.0 	141.0 	191.0 	137.0 	177.0 	159.0 	162.0 	156.0 	201.0 	203.0 	268.0 	199.0 	213.0 	201.0 	235.0 	286.0 	305.0 	215.0 	229.0 	279.0 	234.0 	225.0 	227.0 	222.0 	231.0 	281.0 	256.0 	265.0 	256.0 	250.0 	328.0 	275.0 	271.0 	266.0 	330.0 	276.0 	278.0 	255.0 	253.0 	249.0 	247.0 	349.0 	363.0 	331.0 	310.0 	289.0 	287.0 	361.0 	306.0 	274.0 	340.0 	276.0 	342.0 	279.0 	366.0 	297.0 	368.0 	333.0 	263.0 	265.0 	284.0 	286.0 	261.0 	327.0 	259.0
30    	156.0 	86.0  	171.0 	216.0 	89.0  	176.0 	51.0  	107.0 	174.0 	181.0 	234.0 	246.0 	237.0 	231.0 	99.0  	115.0 	149.0 	213.0 	192.0 	234.0 	227.0 	225.0 	242.0 	222.0 	207.0 	88.0  	30.0  	65.0  	63.0  	106.0 	0.0   	45.0  	48.0  	80.0  	75.0  	73.0  	143.0 	123.0 	137.0 	187.0 	133.0 	173.0 	155.0 	158.0 	152.0 	197.0 	199.0 	264.0 	195.0 	209.0 	197.0 	231.0 	282.0 	301.0 	211.0 	225.0 	275.0 	230.0 	221.0 	223.0 	218.0 	227.0 	277.0 	252.0 	261.0 	252.0 	246.0 	324.0 	271.0 	267.0 	262.0 	326.0 	272.0 	274.0 	251.0 	249.0 	245.0 	243.0 	345.0 	359.0 	327.0 	306.0 	285.0 	283.0 	357.0 	302.0 	270.0 	336.0 	272.0 	338.0 	275.0 	362.0 	293.0 	364.0 	329.0 	259.0 	261.0 	280.0 	282.0 	257.0 	323.0 	255.0
31    	141.0 	71.0  	156.0 	201.0 	74.0  	161.0 	36.0  	92.0  	159.0 	166.0 	219.0 	231.0 	222.0 	216.0 	84.0  	100.0 	134.0 	198.0 	177.0 	219.0 	212.0 	210.0 	227.0 	207.0 	192.0 	73.0  	15.0  	50.0  	48.0  	91.0  	45.0  	0.0   	33.0  	65.0  	60.0  	58.0  	128.0 	108.0 	122.0 	172.0 	118.0 	158.0 	140.0 	143.0 	137.0 	182.0 	184.0 	249.0 	180.0 	194.0 	182.0 	216.0 	267.0 	286.0 	196.0 	210.0 	260.0 	215.0 	206.0 	208.0 	203.0 	212.0 	262.0 	237.0 	246.0 	237.0 	231.0 	309.0 	256.0 	252.0 	247.0 	311.0 	257.0 	259.0 	236.0 	234.0 	230.0 	228.0 	330.0 	344.0 	312.0 	291.0 	270.0 	268.0 	342.0 	287.0 	255.0 	321.0 	257.0 	323.0 	260.0 	347.0 	278.0 	349.0 	314.0 	244.0 	246.0 	265.0 	267.0 	242.0 	308.0 	240.0
32    	144.0 	74.0  	159.0 	204.0 	77.0  	164.0 	39.0  	95.0  	162.0 	169.0 	222.0 	234.0 	225.0 	219.0 	87.0  	103.0 	137.0 	201.0 	180.0 	222.0 	215.0 	213.0 	230.0 	210.0 	195.0 	76.0  	18.0  	53.0  	51.0  	94.0  	48.0  	33.0  	0.0   	68.0  	63.0  	61.0  	131.0 	111.0 	125.0 	175.0 	121.0 	161.0 	143.0 	146.0 	140.0 	185.0 	187.0 	252.0 	183.0 	197.0 	185.0 	219.0 	270.0 	289.0 	199.0 	213.0 	263.0 	218.0 	209.0 	211.0 	206.0 	215.0 	265.0 	240.0 	249.0 	240.0 	234.0 	312.0 	259.0 	255.0 	250.0 	314.0 	260.0 	262.0 	239.0 	237.0 	233.0 	231.0 	333.0 	347.0 	315.0 	294.0 	273.0 	271.0 	345.0 	290.0 	258.0 	324.0 	260.0 	326.0 	263.0 	350.0 	281.0 	352.0 	317.0 	247.0 	249.0 	268.0 	270.0 	245.0 	311.0 	243.0
33    	134.0 	64.0  	149.0 	194.0 	67.0  	154.0 	29.0  	85.0  	152.0 	159.0 	212.0 	224.0 	215.0 	209.0 	77.0  	93.0  	127.0 	191.0 	170.0 	212.0 	205.0 	203.0 	220.0 	200.0 	185.0 	66.0  	50.0  	15.0  	41.0  	84.0  	80.0  	65.0  	68.0  	0.0   	25.0  	51.0  	121.0 	101.0 	115.0 	165.0 	111.0 	151.0 	133.0 	136.0 	130.0 	175.0 	177.0 	242.0 	173.0 	187.0 	175.0 	209.0 	260.0 	279.0 	189.0 	203.0 	253.0 	208.0 	199.0 	201.0 	196.0 	205.0 	255.0 	230.0 	239.0 	230.0 	224.0 	302.0 	249.0 	245.0 	240.0 	304.0 	250.0 	252.0 	229.0 	227.0 	223.0 	221.0 	323.0 	337.0 	305.0 	284.0 	263.0 	261.0 	335.0 	280.0 	248.0 	314.0 	250.0 	316.0 	253.0 	340.0 	271.0 	342.0 	307.0 	237.0 	239.0 	258.0 	260.0 	235.0 	301.0 	233.0
34    	129.0 	59.0  	144.0 	189.0 	62.0  	149.0 	24.0  	80.0  	147.0 	154.0 	207.0 	219.0 	210.0 	204.0 	72.0  	88.0  	122.0 	186.0 	165.0 	207.0 	200.0 	198.0 	215.0 	195.0 	180.0 	61.0  	45.0  	10.0  	36.0  	79.0  	75.0  	60.0  	63.0  	25.0  	0.0   	46.0  	116.0 	96.0  	110.0 	160.0 	106.0 	146.0 	128.0 	131.0 	125.0 	170.0 	172.0 	237.0 	168.0 	182.0 	170.0 	204.0 	255.0 	274.0 	184.0 	198.0 	248.0 	203.0 	194.0 	196.0 	191.0 	200.0 	250.0 	225.0 	234.0 	225.0 	219.0 	297.0 	244.0 	240.0 	235.0 	299.0 	245.0 	247.0 	224.0 	222.0 	218.0 	216.0 	318.0 	332.0 	300.0 	279.0 	258.0 	256.0 	330.0 	275.0 	243.0 	309.0 	245.0 	311.0 	248.0 	335.0 	266.0 	337.0 	302.0 	232.0 	234.0 	253.0 	255.0 	230.0 	296.0 	228.0
35    	127.0 	57.0  	142.0 	187.0 	60.0  	147.0 	22.0  	78.0  	145.0 	152.0 	205.0 	217.0 	208.0 	202.0 	70.0  	86.0  	120.0 	184.0 	163.0 	205.0 	198.0 	196.0 	213.0 	193.0 	178.0 	59.0  	43.0  	36.0  	10.0  	77.0  	73.0  	58.0  	61.0  	51.0  	46.0  	0.0   	90.0  	94.0  	108.0 	158.0 	104.0 	144.0 	126.0 	129.0 	123.0 	168.0 	170.0 	235.0 	166.0 	180.0 	168.0 	202.0 	253.0 	272.0 	182.0 	196.0 	246.0 	201.0 	192.0 	194.0 	189.0 	198.0 	248.0 	223.0 	232.0 	223.0 	217.0 	295.0 	242.0 	238.0 	233.0 	297.0 	243.0 	245.0 	222.0 	220.0 	216.0 	214.0 	316.0 	330.0 	298.0 	277.0 	256.0 	254.0 	328.0 	273.0 	241.0 	307.0 	243.0 	309.0 	246.0 	333.0 	264.0 	335.0 	300.0 	230.0 	232.0 	251.0 	253.0 	228.0 	294.0 	226.0
36    	197.0 	127.0 	212.0 	257.0 	130.0 	217.0 	92.0  	148.0 	215.0 	222.0 	275.0 	287.0 	278.0 	272.0 	140.0 	156.0 	190.0 	254.0 	233.0 	275.0 	268.0 	266.0 	283.0 	263.0 	248.0 	129.0 	113.0 	106.0 	80.0  	147.0 	143.0 	128.0 	131.0 	121.0 	116.0 	90.0  	0.0   	164.0 	178.0 	228.0 	174.0 	214.0 	196.0 	199.0 	193.0 	238.0 	240.0 	305.0 	236.0 	250.0 	238.0 	272.0 	323.0 	342.0 	252.0 	266.0 	316.0 	271.0 	262.0 	264.0 	259.0 	268.0 	318.0 	293.0 	302.0 	293.0 	287.0 	365.0 	312.0 	308.0 	303.0 	367.0 	313.0 	315.0 	292.0 	290.0 	286.0 	284.0 	386.0 	400.0 	368.0 	347.0 	326.0 	324.0 	398.0 	343.0 	311.0 	377.0 	313.0 	379.0 	316.0 	403.0 	334.0 	405.0 	370.0 	300.0 	302.0 	321.0 	323.0 	298.0 	364.0 	296.0
37    	107.0 	37.0  	122.0 	167.0 	40.0  	127.0 	72.0  	16.0  	125.0 	132.0 	185.0 	197.0 	188.0 	182.0 	50.0  	66.0  	100.0 	164.0 	143.0 	185.0 	178.0 	176.0 	193.0 	173.0 	158.0 	109.0 	93.0  	86.0  	84.0  	127.0 	123.0 	108.0 	111.0 	101.0 	96.0  	94.0  	164.0 	0.0   	46.0  	96.0  	10.0  	50.0  	64.0  	67.0  	61.0  	106.0 	108.0 	215.0 	146.0 	160.0 	148.0 	182.0 	233.0 	252.0 	162.0 	176.0 	226.0 	181.0 	172.0 	174.0 	169.0 	178.0 	228.0 	203.0 	212.0 	203.0 	197.0 	275.0 	222.0 	218.0 	213.0 	277.0 	223.0 	225.0 	202.0 	200.0 	196.0 	194.0 	296.0 	310.0 	278.0 	257.0 	236.0 	234.0 	308.0 	253.0 	221.0 	287.0 	223.0 	289.0 	226.0 	313.0 	244.0 	315.0 	280.0 	210.0 	212.0 	231.0 	233.0 	208.0 	274.0 	206.0
38    	121.0 	51.0  	136.0 	181.0 	54.0  	141.0 	86.0  	30.0  	139.0 	146.0 	199.0 	211.0 	202.0 	196.0 	64.0  	80.0  	114.0 	178.0 	157.0 	199.0 	192.0 	190.0 	207.0 	187.0 	172.0 	123.0 	107.0 	100.0 	98.0  	141.0 	137.0 	122.0 	125.0 	115.0 	110.0 	108.0 	178.0 	46.0  	0.0   	110.0 	56.0  	96.0  	18.0  	21.0  	15.0  	120.0 	122.0 	229.0 	160.0 	174.0 	162.0 	196.0 	247.0 	266.0 	176.0 	190.0 	240.0 	195.0 	186.0 	188.0 	183.0 	192.0 	242.0 	217.0 	226.0 	217.0 	211.0 	289.0 	236.0 	232.0 	227.0 	291.0 	237.0 	239.0 	216.0 	214.0 	210.0 	208.0 	310.0 	324.0 	292.0 	271.0 	250.0 	248.0 	322.0 	267.0 	235.0 	301.0 	237.0 	303.0 	240.0 	327.0 	258.0 	329.0 	294.0 	224.0 	226.0 	245.0 	247.0 	222.0 	288.0 	220.0
39    	171.0 	101.0 	186.0 	231.0 	104.0 	191.0 	136.0 	80.0  	189.0 	196.0 	249.0 	261.0 	252.0 	246.0 	114.0 	130.0 	164.0 	228.0 	207.0 	249.0 	242.0 	240.0 	257.0 	237.0 	222.0 	173.0 	157.0 	150.0 	148.0 	191.0 	187.0 	172.0 	175.0 	165.0 	160.0 	158.0 	228.0 	96.0  	110.0 	0.0   	106.0 	146.0 	128.0 	131.0 	125.0 	10.0  	12.0  	279.0 	210.0 	224.0 	212.0 	246.0 	297.0 	316.0 	226.0 	240.0 	290.0 	245.0 	236.0 	238.0 	233.0 	242.0 	292.0 	267.0 	276.0 	267.0 	261.0 	339.0 	286.0 	282.0 	277.0 	341.0 	287.0 	289.0 	266.0 	264.0 	260.0 	258.0 	360.0 	374.0 	342.0 	321.0 	300.0 	298.0 	372.0 	317.0 	285.0 	351.0 	287.0 	353.0 	290.0 	377.0 	308.0 	379.0 	344.0 	274.0 	276.0 	295.0 	297.0 	272.0 	338.0 	270.0
40    	117.0 	47.0  	132.0 	177.0 	50.0  	137.0 	82.0  	26.0  	135.0 	142.0 	195.0 	207.0 	198.0 	192.0 	60.0  	76.0  	110.0 	174.0 	153.0 	195.0 	188.0 	186.0 	203.0 	183.0 	168.0 	119.0 	103.0 	96.0  	94.0  	137.0 	133.0 	118.0 	121.0 	111.0 	106.0 	104.0 	174.0 	10.0  	56.0  	106.0 	0.0   	60.0  	74.0  	77.0  	71.0  	116.0 	118.0 	225.0 	156.0 	170.0 	158.0 	192.0 	243.0 	262.0 	172.0 	186.0 	236.0 	191.0 	182.0 	184.0 	179.0 	188.0 	238.0 	213.0 	222.0 	213.0 	207.0 	285.0 	232.0 	228.0 	223.0 	287.0 	233.0 	235.0 	212.0 	210.0 	206.0 	204.0 	306.0 	320.0 	288.0 	267.0 	246.0 	244.0 	318.0 	263.0 	231.0 	297.0 	233.0 	299.0 	236.0 	323.0 	254.0 	325.0 	290.0 	220.0 	222.0 	241.0 	243.0 	218.0 	284.0 	216.0
41    	157.0 	87.0  	172.0 	217.0 	90.0  	177.0 	122.0 	66.0  	175.0 	182.0 	235.0 	247.0 	238.0 	232.0 	100.0 	116.0 	150.0 	214.0 	193.0 	235.0 	228.0 	226.0 	243.0 	223.0 	208.0 	159.0 	143.0 	136.0 	134.0 	177.0 	173.0 	158.0 	161.0 	151.0 	146.0 	144.0 	214.0 	50.0  	96.0  	146.0 	60.0  	0.0   	114.0 	117.0 	111.0 	156.0 	158.0 	265.0 	196.0 	210.0 	198.0 	232.0 	283.0 	302.0 	212.0 	226.0 	276.0 	231.0 	222.0 	224.0 	219.0 	228.0 	278.0 	253.0 	262.0 	253.0 	247.0 	325.0 	272.0 	268.0 	263.0 	327.0 	273.0 	275.0 	252.0 	250.0 	246.0 	244.0 	346.0 	360.0 	328.0 	307.0 	286.0 	284.0 	358.0 	303.0 	271.0 	337.0 	273.0 	339.0 	276.0 	363.0 	294.0 	365.0 	330.0 	260.0 	262.0 	281.0 	283.0 	258.0 	324.0 	256.0
42    	139.0 	69.0  	154.0 	199.0 	72.0  	159.0 	104.0 	48.0  	157.0 	164.0 	217.0 	229.0 	220.0 	214.0 	82.0  	98.0  	132.0 	196.0 	175.0 	217.0 	210.0 	208.0 	225.0 	205.0 	190.0 	141.0 	125.0 	118.0 	116.0 	159.0 	155.0 	140.0 	143.0 	133.0 	128.0 	126.0 	196.0 	64.0  	18.0  	128.0 	74.0  	114.0 	0.0   	39.0  	33.0  	138.0 	140.0 	247.0 	178.0 	192.0 	180.0 	214.0 	265.0 	284.0 	194.0 	208.0 	258.0 	213.0 	204.0 	206.0 	201.0 	210.0 	260.0 	235.0 	244.0 	235.0 	229.0 	307.0 	254.0 	250.0 	245.0 	309.0 	255.0 	257.0 	234.0 	232.0 	228.0 	226.0 	328.0 	342.0 	310.0 	289.0 	268.0 	266.0 	340.0 	285.0 	253.0 	319.0 	255.0 	321.0 	258.0 	345.0 	276.0 	347.0 	312.0 	242.0 	244.0 	263.0 	265.0 	240.0 	306.0 	238.0
43    	142.0 	72.0  	157.0 	202.0 	75.0  	162.0 	107.0 	51.0  	160.0 	167.0 	220.0 	232.0 	223.0 	217.0 	85.0  	101.0 	135.0 	199.0 	178.0 	220.0 	213.0 	211.0 	228.0 	208.0 	193.0 	144.0 	128.0 	121.0 	119.0 	162.0 	158.0 	143.0 	146.0 	136.0 	131.0 	129.0 	199.0 	67.0  	21.0  	131.0 	77.0  	117.0 	39.0  	0.0   	36.0  	141.0 	143.0 	250.0 	181.0 	195.0 	183.0 	217.0 	268.0 	287.0 	197.0 	211.0 	261.0 	216.0 	207.0 	209.0 	204.0 	213.0 	263.0 	238.0 	247.0 	238.0 	232.0 	310.0 	257.0 	253.0 	248.0 	312.0 	258.0 	260.0 	237.0 	235.0 	231.0 	229.0 	331.0 	345.0 	313.0 	292.0 	271.0 	269.0 	343.0 	288.0 	256.0 	322.0 	258.0 	324.0 	261.0 	348.0 	279.0 	350.0 	315.0 	245.0 	247.0 	266.0 	268.0 	243.0 	309.0 	241.0
44    	136.0 	66.0  	151.0 	196.0 	69.0  	156.0 	101.0 	45.0  	154.0 	161.0 	214.0 	226.0 	217.0 	211.0 	79.0  	95.0  	129.0 	193.0 	172.0 	214.0 	207.0 	205.0 	222.0 	202.0 	187.0 	138.0 	122.0 	115.0 	113.0 	156.0 	152.0 	137.0 	140.0 	130.0 	125.0 	123.0 	193.0 	61.0  	15.0  	125.0 	71.0  	111.0 	33.0  	36.0  	0.0   	135.0 	137.0 	244.0 	175.0 	189.0 	177.0 	211.0 	262.0 	281.0 	191.0 	205.0 	255.0 	210.0 	201.0 	203.0 	198.0 	207.0 	257.0 	232.0 	241.0 	232.0 	226.0 	304.0 	251.0 	247.0 	242.0 	306.0 	252.0 	254.0 	231.0 	229.0 	225.0 	223.0 	325.0 	339.0 	307.0 	286.0 	265.0 	263.0 	337.0 	282.0 	250.0 	316.0 	252.0 	318.0 	255.0 	342.0 	273.0 	344.0 	309.0 	239.0 	241.0 	260.0 	262.0 	237.0 	303.0 	235.0
45    	181.0 	111.0 	196.0 	241.0 	114.0 	201.0 	146.0 	90.0  	199.0 	206.0 	259.0 	271.0 	262.0 	256.0 	124.0 	140.0 	174.0 	238.0 	217.0 	259.0 	252.0 	250.0 	267.0 	247.0 	232.0 	183.0 	167.0 	160.0 	158.0 	201.0 	197.0 	182.0 	185.0 	175.0 	170.0 	168.0 	238.0 	106.0 	120.0 	10.0  	116.0 	156.0 	138.0 	141.0 	135.0 	0.0   	22.0  	289.0 	220.0 	234.0 	222.0 	256.0 	307.0 	326.0 	236.0 	250.0 	300.0 	255.0 	246.0 	248.0 	243.0 	252.0 	302.0 	277.0 	286.0 	277.0 	271.0 	349.0 	296.0 	292.0 	287.0 	351.0 	297.0 	299.0 	276.0 	274.0 	270.0 	268.0 	370.0 	384.0 	352.0 	331.0 	310.0 	308.0 	382.0 	327.0 	295.0 	361.0 	297.0 	363.0 	300.0 	387.0 	318.0 	389.0 	354.0 	284.0 	286.0 	305.0 	307.0 	282.0 	348.0 	280.0
46    	183.0 	113.0 	198.0 	243.0 	116.0 	203.0 	148.0 	92.0  	201.0 	208.0 	261.0 	273.0 	264.0 	258.0 	126.0 	142.0 	176.0 	240.0 	219.0 	261.0 	254.0 	252.0 	269.0 	249.0 	234.0 	185.0 	169.0 	162.0 	160.0 	203.0 	199.0 	184.0 	187.0 	177.0 	172.0 	170.0 	240.0 	108.0 	122.0 	12.0  	118.0 	158.0 	140.0 	143.0 	137.0 	22.0  	0.0   	291.0 	222.0 	236.0 	224.0 	258.0 	309.0 	328.0 	238.0 	252.0 	302.0 	257.0 	248.0 	250.0 	245.0 	254.0 	304.0 	279.0 	288.0 	279.0 	273.0 	351.0 	298.0 	294.0 	289.0 	353.0 	299.0 	301.0 	278.0 	276.0 	272.0 	270.0 	372.0 	386.0 	354.0 	333.0 	312.0 	310.0 	384.0 	329.0 	297.0 	363.0 	299.0 	365.0 	302.0 	389.0 	320.0 	391.0 	356.0 	286.0 	288.0 	307.0 	309.0 	284.0 	350.0 	282.0
47    	108.0 	178.0 	93.0  	168.0 	181.0 	268.0 	213.0 	199.0 	90.0  	103.0 	186.0 	198.0 	189.0 	183.0 	191.0 	207.0 	241.0 	305.0 	284.0 	326.0 	319.0 	317.0 	334.0 	314.0 	299.0 	250.0 	234.0 	227.0 	225.0 	268.0 	264.0 	249.0 	252.0 	242.0 	237.0 	235.0 	305.0 	215.0 	229.0 	279.0 	225.0 	265.0 	247.0 	250.0 	244.0 	289.0 	291.0 	0.0   	111.0 	125.0 	119.0 	153.0 	18.0  	37.0  	127.0 	141.0 	191.0 	146.0 	137.0 	139.0 	140.0 	149.0 	199.0 	174.0 	183.0 	174.0 	168.0 	276.0 	223.0 	219.0 	214.0 	278.0 	224.0 	226.0 	203.0 	201.0 	197.0 	195.0 	297.0 	311.0 	279.0 	258.0 	237.0 	235.0 	309.0 	254.0 	222.0 	288.0 	224.0 	290.0 	227.0 	314.0 	245.0 	316.0 	281.0 	211.0 	213.0 	232.0 	234.0 	209.0 	275.0 	207.0
48    	39.0  	109.0 	24.0  	99.0  	112.0 	199.0 	144.0 	130.0 	21.0  	34.0  	117.0 	129.0 	120.0 	114.0 	122.0 	138.0 	172.0 	236.0 	215.0 	257.0 	250.0 	248.0 	265.0 	245.0 	230.0 	181.0 	165.0 	158.0 	156.0 	199.0 	195.0 	180.0 	183.0 	173.0 	168.0 	166.0 	236.0 	146.0 	160.0 	210.0 	156.0 	196.0 	178.0 	181.0 	175.0 	220.0 	222.0 	111.0 	0.0   	56.0  	50.0  	84.0  	129.0 	148.0 	16.0  	30.0  	80.0  	77.0  	68.0  	70.0  	71.0  	80.0  	130.0 	105.0 	114.0 	105.0 	99.0  	207.0 	154.0 	150.0 	145.0 	209.0 	155.0 	157.0 	134.0 	132.0 	128.0 	126.0 	228.0 	242.0 	210.0 	189.0 	168.0 	166.0 	240.0 	185.0 	153.0 	219.0 	155.0 	221.0 	158.0 	245.0 	176.0 	247.0 	212.0 	142.0 	144.0 	163.0 	165.0 	140.0 	206.0 	138.0
49    	53.0  	123.0 	38.0  	113.0 	126.0 	213.0 	158.0 	144.0 	35.0  	48.0  	131.0 	143.0 	134.0 	128.0 	136.0 	152.0 	186.0 	250.0 	229.0 	271.0 	264.0 	262.0 	279.0 	259.0 	244.0 	195.0 	179.0 	172.0 	170.0 	213.0 	209.0 	194.0 	197.0 	187.0 	182.0 	180.0 	250.0 	160.0 	174.0 	224.0 	170.0 	210.0 	192.0 	195.0 	189.0 	234.0 	236.0 	125.0 	56.0  	0.0   	64.0  	98.0  	143.0 	162.0 	72.0  	86.0  	136.0 	21.0  	12.0  	14.0  	85.0  	94.0  	144.0 	119.0 	128.0 	119.0 	113.0 	221.0 	168.0 	164.0 	159.0 	223.0 	169.0 	171.0 	148.0 	146.0 	142.0 	140.0 	242.0 	256.0 	224.0 	203.0 	182.0 	180.0 	254.0 	199.0 	167.0 	233.0 	169.0 	235.0 	172.0 	259.0 	190.0 	261.0 	226.0 	156.0 	158.0 	177.0 	179.0 	154.0 	220.0 	152.0
50    	41.0  	111.0 	26.0  	101.0 	114.0 	201.0 	146.0 	132.0 	29.0  	16.0  	119.0 	131.0 	122.0 	116.0 	124.0 	140.0 	174.0 	238.0 	217.0 	259.0 	252.0 	250.0 	267.0 	247.0 	232.0 	183.0 	167.0 	160.0 	158.0 	201.0 	197.0 	182.0 	185.0 	175.0 	170.0 	168.0 	238.0 	148.0 	162.0 	212.0 	158.0 	198.0 	180.0 	183.0 	177.0 	222.0 	224.0 	119.0 	50.0  	64.0  	0.0   	66.0  	137.0 	156.0 	66.0  	80.0  	130.0 	85.0  	76.0  	78.0  	21.0  	30.0  	80.0  	87.0  	96.0  	87.0  	81.0  	209.0 	156.0 	152.0 	147.0 	211.0 	157.0 	159.0 	136.0 	134.0 	130.0 	128.0 	230.0 	244.0 	212.0 	191.0 	170.0 	168.0 	242.0 	187.0 	155.0 	221.0 	157.0 	223.0 	160.0 	247.0 	178.0 	249.0 	214.0 	144.0 	146.0 	165.0 	167.0 	142.0 	208.0 	140.0
51    	75.0  	145.0 	60.0  	135.0 	148.0 	235.0 	180.0 	166.0 	63.0  	50.0  	153.0 	165.0 	156.0 	150.0 	158.0 	174.0 	208.0 	272.0 	251.0 	293.0 	286.0 	284.0 	301.0 	281.0 	266.0 	217.0 	201.0 	194.0 	192.0 	235.0 	231.0 	216.0 	219.0 	209.0 	204.0 	202.0 	272.0 	182.0 	196.0 	246.0 	192.0 	232.0 	214.0 	217.0 	211.0 	256.0 	258.0 	153.0 	84.0  	98.0  	66.0  	0.0   	171.0 	190.0 	100.0 	114.0 	164.0 	119.0 	110.0 	112.0 	87.0  	96.0  	146.0 	21.0  	30.0  	21.0  	15.0  	243.0 	190.0 	186.0 	181.0 	245.0 	191.0 	193.0 	170.0 	168.0 	164.0 	162.0 	264.0 	278.0 	246.0 	225.0 	204.0 	202.0 	276.0 	221.0 	189.0 	255.0 	191.0 	257.0 	194.0 	281.0 	212.0 	283.0 	248.0 	178.0 	180.0 	199.0 	201.0 	176.0 	242.0 	174.0
52    	126.0 	196.0 	111.0 	186.0 	199.0 	286.0 	231.0 	217.0 	108.0 	121.0 	204.0 	216.0 	207.0 	201.0 	209.0 	225.0 	259.0 	323.0 	302.0 	344.0 	337.0 	335.0 	352.0 	332.0 	317.0 	268.0 	252.0 	245.0 	243.0 	286.0 	282.0 	267.0 	270.0 	260.0 	255.0 	253.0 	323.0 	233.0 	247.0 	297.0 	243.0 	283.0 	265.0 	268.0 	262.0 	307.0 	309.0 	18.0  	129.0 	143.0 	137.0 	171.0 	0.0   	55.0  	145.0 	159.0 	209.0 	164.0 	155.0 	157.0 	158.0 	167.0 	217.0 	192.0 	201.0 	192.0 	186.0 	294.0 	241.0 	237.0 	232.0 	296.0 	242.0 	244.0 	221.0 	219.0 	215.0 	213.0 	315.0 	329.0 	297.0 	276.0 	255.0 	253.0 	327.0 	272.0 	240.0 	306.0 	242.0 	308.0 	245.0 	332.0 	263.0 	334.0 	299.0 	229.0 	231.0 	250.0 	252.0 	227.0 	293.0 	225.0
53    	145.0 	215.0 	130.0 	205.0 	218.0 	305.0 	250.0 	236.0 	127.0 	140.0 	223.0 	235.0 	226.0 	220.0 	228.0 	244.0 	278.0 	342.0 	321.0 	363.0 	356.0 	354.0 	371.0 	351.0 	336.0 	287.0 	271.0 	264.0 	262.0 	305.0 	301.0 	286.0 	289.0 	279.0 	274.0 	272.0 	342.0 	252.0 	266.0 	316.0 	262.0 	302.0 	284.0 	287.0 	281.0 	326.0 	328.0 	37.0  	148.0 	162.0 	156.0 	190.0 	55.0  	0.0   	164.0 	178.0 	228.0 	183.0 	174.0 	176.0 	177.0 	186.0 	236.0 	211.0 	220.0 	211.0 	205.0 	313.0 	260.0 	256.0 	251.0 	315.0 	261.0 	263.0 	240.0 	238.0 	234.0 	232.0 	334.0 	348.0 	316.0 	295.0 	274.0 	272.0 	346.0 	291.0 	259.0 	325.0 	261.0 	327.0 	264.0 	351.0 	282.0 	353.0 	318.0 	248.0 	250.0 	269.0 	271.0 	246.0 	312.0 	244.0
54    	55.0  	125.0 	40.0  	115.0 	128.0 	215.0 	160.0 	146.0 	37.0  	50.0  	133.0 	145.0 	136.0 	130.0 	138.0 	154.0 	188.0 	252.0 	231.0 	273.0 	266.0 	264.0 	281.0 	261.0 	246.0 	197.0 	181.0 	174.0 	172.0 	215.0 	211.0 	196.0 	199.0 	189.0 	184.0 	182.0 	252.0 	162.0 	176.0 	226.0 	172.0 	212.0 	194.0 	197.0 	191.0 	236.0 	238.0 	127.0 	16.0  	72.0  	66.0  	100.0 	145.0 	164.0 	0.0   	46.0  	96.0  	93.0  	84.0  	86.0  	87.0  	96.0  	146.0 	121.0 	130.0 	121.0 	115.0 	223.0 	170.0 	166.0 	161.0 	225.0 	171.0 	173.0 	150.0 	148.0 	144.0 	142.0 	244.0 	258.0 	226.0 	205.0 	184.0 	182.0 	256.0 	201.0 	169.0 	235.0 	171.0 	237.0 	174.0 	261.0 	192.0 	263.0 	228.0 	158.0 	160.0 	179.0 	181.0 	156.0 	222.0 	154.0
55    	69.0  	139.0 	54.0  	129.0 	142.0 	229.0 	174.0 	160.0 	51.0  	64.0  	147.0 	159.0 	150.0 	144.0 	152.0 	168.0 	202.0 	266.0 	245.0 	287.0 	280.0 	278.0 	295.0 	275.0 	260.0 	211.0 	195.0 	188.0 	186.0 	229.0 	225.0 	210.0 	213.0 	203.0 	198.0 	196.0 	266.0 	176.0 	190.0 	240.0 	186.0 	226.0 	208.0 	211.0 	205.0 	250.0 	252.0 	141.0 	30.0  	86.0  	80.0  	114.0 	159.0 	178.0 	46.0  	0.0   	110.0 	107.0 	98.0  	100.0 	101.0 	110.0 	160.0 	135.0 	144.0 	135.0 	129.0 	237.0 	184.0 	180.0 	175.0 	239.0 	185.0 	187.0 	164.0 	162.0 	158.0 	156.0 	258.0 	272.0 	240.0 	219.0 	198.0 	196.0 	270.0 	215.0 	183.0 	249.0 	185.0 	251.0 	188.0 	275.0 	206.0 	277.0 	242.0 	172.0 	174.0 	193.0 	195.0 	170.0 	236.0 	168.0
56    	119.0 	189.0 	104.0 	179.0 	192.0 	279.0 	224.0 	210.0 	101.0 	114.0 	197.0 	209.0 	200.0 	194.0 	202.0 	218.0 	252.0 	316.0 	295.0 	337.0 	330.0 	328.0 	345.0 	325.0 	310.0 	261.0 	245.0 	238.0 	236.0 	279.0 	275.0 	260.0 	263.0 	253.0 	248.0 	246.0 	316.0 	226.0 	240.0 	290.0 	236.0 	276.0 	258.0 	261.0 	255.0 	300.0 	302.0 	191.0 	80.0  	136.0 	130.0 	164.0 	209.0 	228.0 	96.0  	110.0 	0.0   	157.0 	148.0 	150.0 	151.0 	160.0 	210.0 	185.0 	194.0 	185.0 	179.0 	287.0 	234.0 	230.0 	225.0 	289.0 	235.0 	237.0 	214.0 	212.0 	208.0 	206.0 	308.0 	322.0 	290.0 	269.0 	248.0 	246.0 	320.0 	265.0 	233.0 	299.0 	235.0 	301.0 	238.0 	325.0 	256.0 	327.0 	292.0 	222.0 	224.0 	243.0 	245.0 	220.0 	286.0 	218.0
57    	74.0  	144.0 	59.0  	134.0 	147.0 	234.0 	179.0 	165.0 	56.0  	69.0  	152.0 	164.0 	155.0 	149.0 	157.0 	173.0 	207.0 	271.0 	250.0 	292.0 	285.0 	283.0 	300.0 	280.0 	265.0 	216.0 	200.0 	193.0 	191.0 	234.0 	230.0 	215.0 	218.0 	208.0 	203.0 	201.0 	271.0 	181.0 	195.0 	245.0 	191.0 	231.0 	213.0 	216.0 	210.0 	255.0 	257.0 	146.0 	77.0  	21.0  	85.0  	119.0 	164.0 	183.0 	93.0  	107.0 	157.0 	0.0   	33.0  	35.0  	106.0 	115.0 	165.0 	140.0 	149.0 	140.0 	134.0 	242.0 	189.0 	185.0 	180.0 	244.0 	190.0 	192.0 	169.0 	167.0 	163.0 	161.0 	263.0 	277.0 	245.0 	224.0 	203.0 	201.0 	275.0 	220.0 	188.0 	254.0 	190.0 	256.0 	193.0 	280.0 	211.0 	282.0 	247.0 	177.0 	179.0 	198.0 	200.0 	175.0 	241.0 	173.0
58    	65.0  	135.0 	50.0  	125.0 	138.0 	225.0 	170.0 	156.0 	47.0  	60.0  	143.0 	155.0 	146.0 	140.0 	148.0 	164.0 	198.0 	262.0 	241.0 	283.0 	276.0 	274.0 	291.0 	271.0 	256.0 	207.0 	191.0 	184.0 	182.0 	225.0 	221.0 	206.0 	209.0 	199.0 	194.0 	192.0 	262.0 	172.0 	186.0 	236.0 	182.0 	222.0 	204.0 	207.0 	201.0 	246.0 	248.0 	137.0 	68.0  	12.0  	76.0  	110.0 	155.0 	174.0 	84.0  	98.0  	148.0 	33.0  	0.0   	26.0  	97.0  	106.0 	156.0 	131.0 	140.0 	131.0 	125.0 	233.0 	180.0 	176.0 	171.0 	235.0 	181.0 	183.0 	160.0 	158.0 	154.0 	152.0 	254.0 	268.0 	236.0 	215.0 	194.0 	192.0 	266.0 	211.0 	179.0 	245.0 	181.0 	247.0 	184.0 	271.0 	202.0 	273.0 	238.0 	168.0 	170.0 	189.0 	191.0 	166.0 	232.0 	164.0
59    	67.0  	137.0 	52.0  	127.0 	140.0 	227.0 	172.0 	158.0 	49.0  	62.0  	145.0 	157.0 	148.0 	142.0 	150.0 	166.0 	200.0 	264.0 	243.0 	285.0 	278.0 	276.0 	293.0 	273.0 	258.0 	209.0 	193.0 	186.0 	184.0 	227.0 	223.0 	208.0 	211.0 	201.0 	196.0 	194.0 	264.0 	174.0 	188.0 	238.0 	184.0 	224.0 	206.0 	209.0 	203.0 	248.0 	250.0 	139.0 	70.0  	14.0  	78.0  	112.0 	157.0 	176.0 	86.0  	100.0 	150.0 	35.0  	26.0  	0.0   	99.0  	108.0 	158.0 	133.0 	142.0 	133.0 	127.0 	235.0 	182.0 	178.0 	173.0 	237.0 	183.0 	185.0 	162.0 	160.0 	156.0 	154.0 	256.0 	270.0 	238.0 	217.0 	196.0 	194.0 	268.0 	213.0 	181.0 	247.0 	183.0 	249.0 	186.0 	273.0 	204.0 	275.0 	240.0 	170.0 	172.0 	191.0 	193.0 	168.0 	234.0 	166.0
60    	62.0  	132.0 	47.0  	122.0 	135.0 	222.0 	167.0 	153.0 	50.0  	37.0  	140.0 	152.0 	143.0 	137.0 	145.0 	161.0 	195.0 	259.0 	238.0 	280.0 	273.0 	271.0 	288.0 	268.0 	253.0 	204.0 	188.0 	181.0 	179.0 	222.0 	218.0 	203.0 	206.0 	196.0 	191.0 	189.0 	259.0 	169.0 	183.0 	233.0 	179.0 	219.0 	201.0 	204.0 	198.0 	243.0 	245.0 	140.0 	71.0  	85.0  	21.0  	87.0  	158.0 	177.0 	87.0  	101.0 	151.0 	106.0 	97.0  	99.0  	0.0   	51.0  	101.0 	108.0 	117.0 	108.0 	102.0 	230.0 	177.0 	173.0 	168.0 	232.0 	178.0 	180.0 	157.0 	155.0 	151.0 	149.0 	251.0 	265.0 	233.0 	212.0 	191.0 	189.0 	263.0 	208.0 	176.0 	242.0 	178.0 	244.0 	181.0 	268.0 	199.0 	270.0 	235.0 	165.0 	167.0 	186.0 	188.0 	163.0 	229.0 	161.0
61    	71.0  	141.0 	56.0  	131.0 	144.0 	231.0 	176.0 	162.0 	59.0  	46.0  	149.0 	161.0 	152.0 	146.0 	154.0 	170.0 	204.0 	268.0 	247.0 	289.0 	282.0 	280.0 	297.0 	277.0 	262.0 	213.0 	197.0 	190.0 	188.0 	231.0 	227.0 	212.0 	215.0 	205.0 	200.0 	198.0 	268.0 	178.0 	192.0 	242.0 	188.0 	228.0 	210.0 	213.0 	207.0 	252.0 	254.0 	149.0 	80.0  	94.0  	30.0  	96.0  	167.0 	186.0 	96.0  	110.0 	160.0 	115.0 	106.0 	108.0 	51.0  	0.0   	110.0 	117.0 	126.0 	117.0 	111.0 	239.0 	186.0 	182.0 	177.0 	241.0 	187.0 	189.0 	166.0 	164.0 	160.0 	158.0 	260.0 	274.0 	242.0 	221.0 	200.0 	198.0 	272.0 	217.0 	185.0 	251.0 	187.0 	253.0 	190.0 	277.0 	208.0 	279.0 	244.0 	174.0 	176.0 	195.0 	197.0 	172.0 	238.0 	170.0
62    	121.0 	191.0 	106.0 	181.0 	194.0 	281.0 	226.0 	212.0 	109.0 	96.0  	199.0 	211.0 	202.0 	196.0 	204.0 	220.0 	254.0 	318.0 	297.0 	339.0 	332.0 	330.0 	347.0 	327.0 	312.0 	263.0 	247.0 	240.0 	238.0 	281.0 	277.0 	262.0 	265.0 	255.0 	250.0 	248.0 	318.0 	228.0 	242.0 	292.0 	238.0 	278.0 	260.0 	263.0 	257.0 	302.0 	304.0 	199.0 	130.0 	144.0 	80.0  	146.0 	217.0 	236.0 	146.0 	160.0 	210.0 	165.0 	156.0 	158.0 	101.0 	110.0 	0.0   	167.0 	176.0 	167.0 	161.0 	289.0 	236.0 	232.0 	227.0 	291.0 	237.0 	239.0 	216.0 	214.0 	210.0 	208.0 	310.0 	324.0 	292.0 	271.0 	250.0 	248.0 	322.0 	267.0 	235.0 	301.0 	237.0 	303.0 	240.0 	327.0 	258.0 	329.0 	294.0 	224.0 	226.0 	245.0 	247.0 	222.0 	288.0 	220.0
63    	96.0  	166.0 	81.0  	156.0 	169.0 	256.0 	201.0 	187.0 	84.0  	71.0  	174.0 	186.0 	177.0 	171.0 	179.0 	195.0 	229.0 	293.0 	272.0 	314.0 	307.0 	305.0 	322.0 	302.0 	287.0 	238.0 	222.0 	215.0 	213.0 	256.0 	252.0 	237.0 	240.0 	230.0 	225.0 	223.0 	293.0 	203.0 	217.0 	267.0 	213.0 	253.0 	235.0 	238.0 	232.0 	277.0 	279.0 	174.0 	105.0 	119.0 	87.0  	21.0  	192.0 	211.0 	121.0 	135.0 	185.0 	140.0 	131.0 	133.0 	108.0 	117.0 	167.0 	0.0   	51.0  	42.0  	36.0  	264.0 	211.0 	207.0 	202.0 	266.0 	212.0 	214.0 	191.0 	189.0 	185.0 	183.0 	285.0 	299.0 	267.0 	246.0 	225.0 	223.0 	297.0 	242.0 	210.0 	276.0 	212.0 	278.0 	215.0 	302.0 	233.0 	304.0 	269.0 	199.0 	201.0 	220.0 	222.0 	197.0 	263.0 	195.0
64    	105.0 	175.0 	90.0  	165.0 	178.0 	265.0 	210.0 	196.0 	93.0  	80.0  	183.0 	195.0 	186.0 	180.0 	188.0 	204.0 	238.0 	302.0 	281.0 	323.0 	316.0 	314.0 	331.0 	311.0 	296.0 	247.0 	231.0 	224.0 	222.0 	265.0 	261.0 	246.0 	249.0 	239.0 	234.0 	232.0 	302.0 	212.0 	226.0 	276.0 	222.0 	262.0 	244.0 	247.0 	241.0 	286.0 	288.0 	183.0 	114.0 	128.0 	96.0  	30.0  	201.0 	220.0 	130.0 	144.0 	194.0 	149.0 	140.0 	142.0 	117.0 	126.0 	176.0 	51.0  	0.0   	51.0  	45.0  	273.0 	220.0 	216.0 	211.0 	275.0 	221.0 	223.0 	200.0 	198.0 	194.0 	192.0 	294.0 	308.0 	276.0 	255.0 	234.0 	232.0 	306.0 	251.0 	219.0 	285.0 	221.0 	287.0 	224.0 	311.0 	242.0 	313.0 	278.0 	208.0 	210.0 	229.0 	231.0 	206.0 	272.0 	204.0
65    	96.0  	166.0 	81.0  	156.0 	169.0 	256.0 	201.0 	187.0 	84.0  	71.0  	174.0 	186.0 	177.0 	171.0 	179.0 	195.0 	229.0 	293.0 	272.0 	314.0 	307.0 	305.0 	322.0 	302.0 	287.0 	238.0 	222.0 	215.0 	213.0 	256.0 	252.0 	237.0 	240.0 	230.0 	225.0 	223.0 	293.0 	203.0 	217.0 	267.0 	213.0 	253.0 	235.0 	238.0 	232.0 	277.0 	279.0 	174.0 	105.0 	119.0 	87.0  	21.0  	192.0 	211.0 	121.0 	135.0 	185.0 	140.0 	131.0 	133.0 	108.0 	117.0 	167.0 	42.0  	51.0  	0.0   	36.0  	264.0 	211.0 	207.0 	202.0 	266.0 	212.0 	214.0 	191.0 	189.0 	185.0 	183.0 	285.0 	299.0 	267.0 	246.0 	225.0 	223.0 	297.0 	242.0 	210.0 	276.0 	212.0 	278.0 	215.0 	302.0 	233.0 	304.0 	269.0 	199.0 	201.0 	220.0 	222.0 	197.0 	263.0 	195.0
66    	90.0  	160.0 	75.0  	150.0 	163.0 	250.0 	195.0 	181.0 	78.0  	65.0  	168.0 	180.0 	171.0 	165.0 	173.0 	189.0 	223.0 	287.0 	266.0 	308.0 	301.0 	299.0 	316.0 	296.0 	281.0 	232.0 	216.0 	209.0 	207.0 	250.0 	246.0 	231.0 	234.0 	224.0 	219.0 	217.0 	287.0 	197.0 	211.0 	261.0 	207.0 	247.0 	229.0 	232.0 	226.0 	271.0 	273.0 	168.0 	99.0  	113.0 	81.0  	15.0  	186.0 	205.0 	115.0 	129.0 	179.0 	134.0 	125.0 	127.0 	102.0 	111.0 	161.0 	36.0  	45.0  	36.0  	0.0   	258.0 	205.0 	201.0 	196.0 	260.0 	206.0 	208.0 	185.0 	183.0 	179.0 	177.0 	279.0 	293.0 	261.0 	240.0 	219.0 	217.0 	291.0 	236.0 	204.0 	270.0 	206.0 	272.0 	209.0 	296.0 	227.0 	298.0 	263.0 	193.0 	195.0 	214.0 	216.0 	191.0 	257.0 	189.0
67    	168.0 	238.0 	183.0 	108.0 	241.0 	328.0 	273.0 	259.0 	186.0 	193.0 	90.0  	138.0 	129.0 	123.0 	251.0 	267.0 	301.0 	365.0 	344.0 	386.0 	379.0 	377.0 	394.0 	374.0 	359.0 	310.0 	294.0 	287.0 	285.0 	328.0 	324.0 	309.0 	312.0 	302.0 	297.0 	295.0 	365.0 	275.0 	289.0 	339.0 	285.0 	325.0 	307.0 	310.0 	304.0 	349.0 	351.0 	276.0 	207.0 	221.0 	209.0 	243.0 	294.0 	313.0 	223.0 	237.0 	287.0 	242.0 	233.0 	235.0 	230.0 	239.0 	289.0 	264.0 	273.0 	264.0 	258.0 	0.0   	127.0 	159.0 	154.0 	218.0 	164.0 	166.0 	143.0 	141.0 	137.0 	135.0 	21.0  	35.0  	3.0   	162.0 	141.0 	139.0 	249.0 	194.0 	162.0 	228.0 	164.0 	230.0 	167.0 	254.0 	185.0 	256.0 	221.0 	151.0 	153.0 	172.0 	174.0 	149.0 	215.0 	147.0
68    	115.0 	185.0 	130.0 	55.0  	188.0 	275.0 	220.0 	206.0 	133.0 	140.0 	37.0  	85.0  	76.0  	70.0  	198.0 	214.0 	248.0 	312.0 	291.0 	333.0 	326.0 	324.0 	341.0 	321.0 	306.0 	257.0 	241.0 	234.0 	232.0 	275.0 	271.0 	256.0 	259.0 	249.0 	244.0 	242.0 	312.0 	222.0 	236.0 	286.0 	232.0 	272.0 	254.0 	257.0 	251.0 	296.0 	298.0 	223.0 	154.0 	168.0 	156.0 	190.0 	241.0 	260.0 	170.0 	184.0 	234.0 	189.0 	180.0 	182.0 	177.0 	186.0 	236.0 	211.0 	220.0 	211.0 	205.0 	127.0 	0.0   	106.0 	101.0 	165.0 	111.0 	113.0 	90.0  	88.0  	84.0  	82.0  	148.0 	162.0 	130.0 	35.0  	14.0  	12.0  	196.0 	141.0 	109.0 	175.0 	111.0 	177.0 	114.0 	201.0 	132.0 	203.0 	168.0 	98.0  	100.0 	119.0 	121.0 	96.0  	162.0 	94.0
69    	111.0 	181.0 	126.0 	51.0  	184.0 	271.0 	216.0 	202.0 	129.0 	136.0 	69.0  	21.0  	72.0  	66.0  	194.0 	210.0 	244.0 	308.0 	287.0 	329.0 	322.0 	320.0 	337.0 	317.0 	302.0 	253.0 	237.0 	230.0 	228.0 	271.0 	267.0 	252.0 	255.0 	245.0 	240.0 	238.0 	308.0 	218.0 	232.0 	282.0 	228.0 	268.0 	250.0 	253.0 	247.0 	292.0 	294.0 	219.0 	150.0 	164.0 	152.0 	186.0 	237.0 	256.0 	166.0 	180.0 	230.0 	185.0 	176.0 	178.0 	173.0 	182.0 	232.0 	207.0 	216.0 	207.0 	201.0 	159.0 	106.0 	0.0   	37.0  	101.0 	107.0 	109.0 	86.0  	84.0  	80.0  	78.0  	180.0 	194.0 	162.0 	141.0 	120.0 	118.0 	90.0  	35.0  	3.0   	111.0 	47.0  	113.0 	110.0 	197.0 	128.0 	199.0 	164.0 	94.0  	96.0  	115.0 	117.0 	92.0  	158.0 	90.0
70    	106.0 	176.0 	121.0 	46.0  	179.0 	266.0 	211.0 	197.0 	124.0 	131.0 	64.0  	16.0  	67.0  	61.0  	189.0 	205.0 	239.0 	303.0 	282.0 	324.0 	317.0 	315.0 	332.0 	312.0 	297.0 	248.0 	232.0 	225.0 	223.0 	266.0 	262.0 	247.0 	250.0 	240.0 	235.0 	233.0 	303.0 	213.0 	227.0 	277.0 	223.0 	263.0 	245.0 	248.0 	242.0 	287.0 	289.0 	214.0 	145.0 	159.0 	147.0 	181.0 	232.0 	251.0 	161.0 	175.0 	225.0 	180.0 	171.0 	173.0 	168.0 	177.0 	227.0 	202.0 	211.0 	202.0 	196.0 	154.0 	101.0 	37.0  	0.0   	96.0  	102.0 	104.0 	81.0  	79.0  	75.0  	73.0  	175.0 	189.0 	157.0 	136.0 	115.0 	113.0 	127.0 	72.0  	40.0  	106.0 	10.0  	108.0 	105.0 	192.0 	123.0 	194.0 	159.0 	89.0  	91.0  	110.0 	112.0 	87.0  	153.0 	85.0
71    	170.0 	240.0 	185.0 	110.0 	243.0 	330.0 	275.0 	261.0 	188.0 	195.0 	128.0 	80.0  	131.0 	125.0 	253.0 	269.0 	303.0 	367.0 	346.0 	388.0 	381.0 	379.0 	396.0 	376.0 	361.0 	312.0 	296.0 	289.0 	287.0 	330.0 	326.0 	311.0 	314.0 	304.0 	299.0 	297.0 	367.0 	277.0 	291.0 	341.0 	287.0 	327.0 	309.0 	312.0 	306.0 	351.0 	353.0 	278.0 	209.0 	223.0 	211.0 	245.0 	296.0 	315.0 	225.0 	239.0 	289.0 	244.0 	235.0 	237.0 	232.0 	241.0 	291.0 	266.0 	275.0 	266.0 	260.0 	218.0 	165.0 	101.0 	96.0  	0.0   	166.0 	168.0 	145.0 	143.0 	139.0 	137.0 	239.0 	253.0 	221.0 	200.0 	179.0 	177.0 	191.0 	136.0 	104.0 	10.0  	106.0 	12.0  	169.0 	256.0 	187.0 	258.0 	223.0 	153.0 	155.0 	174.0 	176.0 	151.0 	217.0 	149.0
72    	116.0 	186.0 	131.0 	56.0  	189.0 	276.0 	221.0 	207.0 	134.0 	141.0 	74.0  	86.0  	35.0  	71.0  	199.0 	215.0 	249.0 	313.0 	292.0 	334.0 	327.0 	325.0 	342.0 	322.0 	307.0 	258.0 	242.0 	235.0 	233.0 	276.0 	272.0 	257.0 	260.0 	250.0 	245.0 	243.0 	313.0 	223.0 	237.0 	287.0 	233.0 	273.0 	255.0 	258.0 	252.0 	297.0 	299.0 	224.0 	155.0 	169.0 	157.0 	191.0 	242.0 	261.0 	171.0 	185.0 	235.0 	190.0 	181.0 	183.0 	178.0 	187.0 	237.0 	212.0 	221.0 	212.0 	206.0 	164.0 	111.0 	107.0 	102.0 	166.0 	0.0   	72.0  	49.0  	47.0  	85.0  	83.0  	185.0 	199.0 	167.0 	146.0 	125.0 	123.0 	197.0 	142.0 	110.0 	176.0 	112.0 	178.0 	3.0   	90.0  	21.0  	162.0 	127.0 	57.0  	59.0  	120.0 	122.0 	97.0  	163.0 	95.0
73    	118.0 	188.0 	133.0 	58.0  	191.0 	278.0 	223.0 	209.0 	136.0 	143.0 	76.0  	88.0  	37.0  	73.0  	201.0 	217.0 	251.0 	315.0 	294.0 	336.0 	329.0 	327.0 	344.0 	324.0 	309.0 	260.0 	244.0 	237.0 	235.0 	278.0 	274.0 	259.0 	262.0 	252.0 	247.0 	245.0 	315.0 	225.0 	239.0 	289.0 	235.0 	275.0 	257.0 	260.0 	254.0 	299.0 	301.0 	226.0 	157.0 	171.0 	159.0 	193.0 	244.0 	263.0 	173.0 	187.0 	237.0 	192.0 	183.0 	185.0 	180.0 	189.0 	239.0 	214.0 	223.0 	214.0 	208.0 	166.0 	113.0 	109.0 	104.0 	168.0 	72.0  	0.0   	51.0  	49.0  	87.0  	85.0  	187.0 	201.0 	169.0 	148.0 	127.0 	125.0 	199.0 	144.0 	112.0 	178.0 	114.0 	180.0 	75.0  	162.0 	93.0  	90.0  	129.0 	59.0  	61.0  	122.0 	124.0 	99.0  	165.0 	97.0
74    	95.0  	165.0 	110.0 	35.0  	168.0 	255.0 	200.0 	186.0 	113.0 	120.0 	53.0  	65.0  	14.0  	50.0  	178.0 	194.0 	228.0 	292.0 	271.0 	313.0 	306.0 	304.0 	321.0 	301.0 	286.0 	237.0 	221.0 	214.0 	212.0 	255.0 	251.0 	236.0 	239.0 	229.0 	224.0 	222.0 	292.0 	202.0 	216.0 	266.0 	212.0 	252.0 	234.0 	237.0 	231.0 	276.0 	278.0 	203.0 	134.0 	148.0 	136.0 	170.0 	221.0 	240.0 	150.0 	164.0 	214.0 	169.0 	160.0 	162.0 	157.0 	166.0 	216.0 	191.0 	200.0 	191.0 	185.0 	143.0 	90.0  	86.0  	81.0  	145.0 	49.0  	51.0  	0.0   	26.0  	64.0  	62.0  	164.0 	178.0 	146.0 	125.0 	104.0 	102.0 	176.0 	121.0 	89.0  	155.0 	91.0  	157.0 	52.0  	139.0 	70.0  	141.0 	106.0 	36.0  	10.0  	99.0  	101.0 	76.0  	142.0 	74.0
75    	93.0  	163.0 	108.0 	33.0  	166.0 	253.0 	198.0 	184.0 	111.0 	118.0 	51.0  	63.0  	12.0  	48.0  	176.0 	192.0 	226.0 	290.0 	269.0 	311.0 	304.0 	302.0 	319.0 	299.0 	284.0 	235.0 	219.0 	212.0 	210.0 	253.0 	249.0 	234.0 	237.0 	227.0 	222.0 	220.0 	290.0 	200.0 	214.0 	264.0 	210.0 	250.0 	232.0 	235.0 	229.0 	274.0 	276.0 	201.0 	132.0 	146.0 	134.0 	168.0 	219.0 	238.0 	148.0 	162.0 	212.0 	167.0 	158.0 	160.0 	155.0 	164.0 	214.0 	189.0 	198.0 	189.0 	183.0 	141.0 	88.0  	84.0  	79.0  	143.0 	47.0  	49.0  	26.0  	0.0   	62.0  	60.0  	162.0 	176.0 	144.0 	123.0 	102.0 	100.0 	174.0 	119.0 	87.0  	153.0 	89.0  	155.0 	50.0  	137.0 	68.0  	139.0 	80.0  	10.0  	36.0  	97.0  	99.0  	74.0  	140.0 	72.0
76    	89.0  	159.0 	104.0 	29.0  	162.0 	249.0 	194.0 	180.0 	107.0 	114.0 	47.0  	59.0  	50.0  	14.0  	172.0 	188.0 	222.0 	286.0 	265.0 	307.0 	300.0 	298.0 	315.0 	295.0 	280.0 	231.0 	215.0 	208.0 	206.0 	249.0 	245.0 	230.0 	233.0 	223.0 	218.0 	216.0 	286.0 	196.0 	210.0 	260.0 	206.0 	246.0 	228.0 	231.0 	225.0 	270.0 	272.0 	197.0 	128.0 	142.0 	130.0 	164.0 	215.0 	234.0 	144.0 	158.0 	208.0 	163.0 	154.0 	156.0 	151.0 	160.0 	210.0 	185.0 	194.0 	185.0 	179.0 	137.0 	84.0  	80.0  	75.0  	139.0 	85.0  	87.0  	64.0  	62.0  	0.0   	26.0  	158.0 	172.0 	140.0 	119.0 	98.0  	96.0  	170.0 	115.0 	83.0  	149.0 	85.0  	151.0 	88.0  	175.0 	106.0 	177.0 	142.0 	72.0  	74.0  	35.0  	37.0  	12.0  	106.0 	38.0
77    	87.0  	157.0 	102.0 	27.0  	160.0 	247.0 	192.0 	178.0 	105.0 	112.0 	45.0  	57.0  	48.0  	12.0  	170.0 	186.0 	220.0 	284.0 	263.0 	305.0 	298.0 	296.0 	313.0 	293.0 	278.0 	229.0 	213.0 	206.0 	204.0 	247.0 	243.0 	228.0 	231.0 	221.0 	216.0 	214.0 	284.0 	194.0 	208.0 	258.0 	204.0 	244.0 	226.0 	229.0 	223.0 	268.0 	270.0 	195.0 	126.0 	140.0 	128.0 	162.0 	213.0 	232.0 	142.0 	156.0 	206.0 	161.0 	152.0 	154.0 	149.0 	158.0 	208.0 	183.0 	192.0 	183.0 	177.0 	135.0 	82.0  	78.0  	73.0  	137.0 	83.0  	85.0  	62.0  	60.0  	26.0  	0.0   	156.0 	170.0 	138.0 	117.0 	96.0  	94.0  	168.0 	113.0 	81.0  	147.0 	83.0  	149.0 	86.0  	173.0 	104.0 	175.0 	140.0 	70.0  	72.0  	61.0  	63.0  	38.0  	80.0  	12.0
78    	189.0 	259.0 	204.0 	129.0 	262.0 	349.0 	294.0 	280.0 	207.0 	214.0 	111.0 	159.0 	150.0 	144.0 	272.0 	288.0 	322.0 	386.0 	365.0 	407.0 	400.0 	398.0 	415.0 	395.0 	380.0 	331.0 	315.0 	308.0 	306.0 	349.0 	345.0 	330.0 	333.0 	323.0 	318.0 	316.0 	386.0 	296.0 	310.0 	360.0 	306.0 	346.0 	328.0 	331.0 	325.0 	370.0 	372.0 	297.0 	228.0 	242.0 	230.0 	264.0 	315.0 	334.0 	244.0 	258.0 	308.0 	263.0 	254.0 	256.0 	251.0 	260.0 	310.0 	285.0 	294.0 	285.0 	279.0 	21.0  	148.0 	180.0 	175.0 	239.0 	185.0 	187.0 	164.0 	162.0 	158.0 	156.0 	0.0   	56.0  	24.0  	183.0 	162.0 	160.0 	270.0 	215.0 	183.0 	249.0 	185.0 	251.0 	188.0 	275.0 	206.0 	277.0 	242.0 	172.0 	174.0 	193.0 	195.0 	170.0 	236.0 	168.0
79    	203.0 	273.0 	218.0 	143.0 	276.0 	363.0 	308.0 	294.0 	221.0 	228.0 	125.0 	173.0 	164.0 	158.0 	286.0 	302.0 	336.0 	400.0 	379.0 	421.0 	414.0 	412.0 	429.0 	409.0 	394.0 	345.0 	329.0 	322.0 	320.0 	363.0 	359.0 	344.0 	347.0 	337.0 	332.0 	330.0 	400.0 	310.0 	324.0 	374.0 	320.0 	360.0 	342.0 	345.0 	339.0 	384.0 	386.0 	311.0 	242.0 	256.0 	244.0 	278.0 	329.0 	348.0 	258.0 	272.0 	322.0 	277.0 	268.0 	270.0 	265.0 	274.0 	324.0 	299.0 	308.0 	299.0 	293.0 	35.0  	162.0 	194.0 	189.0 	253.0 	199.0 	201.0 	178.0 	176.0 	172.0 	170.0 	56.0  	0.0   	38.0  	197.0 	176.0 	174.0 	284.0 	229.0 	197.0 	263.0 	199.0 	265.0 	202.0 	289.0 	220.0 	291.0 	256.0 	186.0 	188.0 	207.0 	209.0 	184.0 	250.0 	182.0
80    	171.0 	241.0 	186.0 	111.0 	244.0 	331.0 	276.0 	262.0 	189.0 	196.0 	93.0  	141.0 	132.0 	126.0 	254.0 	270.0 	304.0 	368.0 	347.0 	389.0 	382.0 	380.0 	397.0 	377.0 	362.0 	313.0 	297.0 	290.0 	288.0 	331.0 	327.0 	312.0 	315.0 	305.0 	300.0 	298.0 	368.0 	278.0 	292.0 	342.0 	288.0 	328.0 	310.0 	313.0 	307.0 	352.0 	354.0 	279.0 	210.0 	224.0 	212.0 	246.0 	297.0 	316.0 	226.0 	240.0 	290.0 	245.0 	236.0 	238.0 	233.0 	242.0 	292.0 	267.0 	276.0 	267.0 	261.0 	3.0   	130.0 	162.0 	157.0 	221.0 	167.0 	169.0 	146.0 	144.0 	140.0 	138.0 	24.0  	38.0  	0.0   	165.0 	144.0 	142.0 	252.0 	197.0 	165.0 	231.0 	167.0 	233.0 	170.0 	257.0 	188.0 	259.0 	224.0 	154.0 	156.0 	175.0 	177.0 	152.0 	218.0 	150.0
81    	150.0 	220.0 	165.0 	90.0  	223.0 	310.0 	255.0 	241.0 	168.0 	175.0 	72.0  	120.0 	111.0 	105.0 	233.0 	249.0 	283.0 	347.0 	326.0 	368.0 	361.0 	359.0 	376.0 	356.0 	341.0 	292.0 	276.0 	269.0 	267.0 	310.0 	306.0 	291.0 	294.0 	284.0 	279.0 	277.0 	347.0 	257.0 	271.0 	321.0 	267.0 	307.0 	289.0 	292.0 	286.0 	331.0 	333.0 	258.0 	189.0 	203.0 	191.0 	225.0 	276.0 	295.0 	205.0 	219.0 	269.0 	224.0 	215.0 	217.0 	212.0 	221.0 	271.0 	246.0 	255.0 	246.0 	240.0 	162.0 	35.0  	141.0 	136.0 	200.0 	146.0 	148.0 	125.0 	123.0 	119.0 	117.0 	183.0 	197.0 	165.0 	0.0   	49.0  	47.0  	231.0 	176.0 	144.0 	210.0 	146.0 	212.0 	149.0 	236.0 	167.0 	238.0 	203.0 	133.0 	135.0 	154.0 	156.0 	131.0 	197.0 	129.0
82    	129.0 	199.0 	144.0 	69.0  	202.0 	289.0 	234.0 	220.0 	147.0 	154.0 	51.0  	99.0  	90.0  	84.0  	212.0 	228.0 	262.0 	326.0 	305.0 	347.0 	340.0 	338.0 	355.0 	335.0 	320.0 	271.0 	255.0 	248.0 	246.0 	289.0 	285.0 	270.0 	273.0 	263.0 	258.0 	256.0 	326.0 	236.0 	250.0 	300.0 	246.0 	286.0 	268.0 	271.0 	265.0 	310.0 	312.0 	237.0 	168.0 	182.0 	170.0 	204.0 	255.0 	274.0 	184.0 	198.0 	248.0 	203.0 	194.0 	196.0 	191.0 	200.0 	250.0 	225.0 	234.0 	225.0 	219.0 	141.0 	14.0  	120.0 	115.0 	179.0 	125.0 	127.0 	104.0 	102.0 	98.0  	96.0  	162.0 	176.0 	144.0 	49.0  	0.0   	26.0  	210.0 	155.0 	123.0 	189.0 	125.0 	191.0 	128.0 	215.0 	146.0 	217.0 	182.0 	112.0 	114.0 	133.0 	135.0 	110.0 	176.0 	108.0
83    	127.0 	197.0 	142.0 	67.0  	200.0 	287.0 	232.0 	218.0 	145.0 	152.0 	49.0  	97.0  	88.0  	82.0  	210.0 	226.0 	260.0 	324.0 	303.0 	345.0 	338.0 	336.0 	353.0 	333.0 	318.0 	269.0 	253.0 	246.0 	244.0 	287.0 	283.0 	268.0 	271.0 	261.0 	256.0 	254.0 	324.0 	234.0 	248.0 	298.0 	244.0 	284.0 	266.0 	269.0 	263.0 	308.0 	310.0 	235.0 	166.0 	180.0 	168.0 	202.0 	253.0 	272.0 	182.0 	196.0 	246.0 	201.0 	192.0 	194.0 	189.0 	198.0 	248.0 	223.0 	232.0 	223.0 	217.0 	139.0 	12.0  	118.0 	113.0 	177.0 	123.0 	125.0 	102.0 	100.0 	96.0  	94.0  	160.0 	174.0 	142.0 	47.0  	26.0  	0.0   	208.0 	153.0 	121.0 	187.0 	123.0 	189.0 	126.0 	213.0 	144.0 	215.0 	180.0 	110.0 	112.0 	131.0 	133.0 	108.0 	174.0 	106.0
84    	201.0 	271.0 	216.0 	141.0 	274.0 	361.0 	306.0 	292.0 	219.0 	226.0 	159.0 	111.0 	162.0 	156.0 	284.0 	300.0 	334.0 	398.0 	377.0 	419.0 	412.0 	410.0 	427.0 	407.0 	392.0 	343.0 	327.0 	320.0 	318.0 	361.0 	357.0 	342.0 	345.0 	335.0 	330.0 	328.0 	398.0 	308.0 	322.0 	372.0 	318.0 	358.0 	340.0 	343.0 	337.0 	382.0 	384.0 	309.0 	240.0 	254.0 	242.0 	276.0 	327.0 	346.0 	256.0 	270.0 	320.0 	275.0 	266.0 	268.0 	263.0 	272.0 	322.0 	297.0 	306.0 	297.0 	291.0 	249.0 	196.0 	90.0  	127.0 	191.0 	197.0 	199.0 	176.0 	174.0 	170.0 	168.0 	270.0 	284.0 	252.0 	231.0 	210.0 	208.0 	0.0   	125.0 	93.0  	201.0 	137.0 	203.0 	200.0 	287.0 	218.0 	289.0 	254.0 	184.0 	186.0 	205.0 	207.0 	182.0 	248.0 	180.0
85    	146.0 	216.0 	161.0 	86.0  	219.0 	306.0 	251.0 	237.0 	164.0 	171.0 	104.0 	56.0  	107.0 	101.0 	229.0 	245.0 	279.0 	343.0 	322.0 	364.0 	357.0 	355.0 	372.0 	352.0 	337.0 	288.0 	272.0 	265.0 	263.0 	306.0 	302.0 	287.0 	290.0 	280.0 	275.0 	273.0 	343.0 	253.0 	267.0 	317.0 	263.0 	303.0 	285.0 	288.0 	282.0 	327.0 	329.0 	254.0 	185.0 	199.0 	187.0 	221.0 	272.0 	291.0 	201.0 	215.0 	265.0 	220.0 	211.0 	213.0 	208.0 	217.0 	267.0 	242.0 	251.0 	242.0 	236.0 	194.0 	141.0 	35.0  	72.0  	136.0 	142.0 	144.0 	121.0 	119.0 	115.0 	113.0 	215.0 	229.0 	197.0 	176.0 	155.0 	153.0 	125.0 	0.0   	38.0  	146.0 	82.0  	148.0 	145.0 	232.0 	163.0 	234.0 	199.0 	129.0 	131.0 	150.0 	152.0 	127.0 	193.0 	125.0
86    	114.0 	184.0 	129.0 	54.0  	187.0 	274.0 	219.0 	205.0 	132.0 	139.0 	72.0  	24.0  	75.0  	69.0  	197.0 	213.0 	247.0 	311.0 	290.0 	332.0 	325.0 	323.0 	340.0 	320.0 	305.0 	256.0 	240.0 	233.0 	231.0 	274.0 	270.0 	255.0 	258.0 	248.0 	243.0 	241.0 	311.0 	221.0 	235.0 	285.0 	231.0 	271.0 	253.0 	256.0 	250.0 	295.0 	297.0 	222.0 	153.0 	167.0 	155.0 	189.0 	240.0 	259.0 	169.0 	183.0 	233.0 	188.0 	179.0 	181.0 	176.0 	185.0 	235.0 	210.0 	219.0 	210.0 	204.0 	162.0 	109.0 	3.0   	40.0  	104.0 	110.0 	112.0 	89.0  	87.0  	83.0  	81.0  	183.0 	197.0 	165.0 	144.0 	123.0 	121.0 	93.0  	38.0  	0.0   	114.0 	50.0  	116.0 	113.0 	200.0 	131.0 	202.0 	167.0 	97.0  	99.0  	118.0 	120.0 	95.0  	161.0 	93.0
87    	180.0 	250.0 	195.0 	120.0 	253.0 	340.0 	285.0 	271.0 	198.0 	205.0 	138.0 	90.0  	141.0 	135.0 	263.0 	279.0 	313.0 	377.0 	356.0 	398.0 	391.0 	389.0 	406.0 	386.0 	371.0 	322.0 	306.0 	299.0 	297.0 	340.0 	336.0 	321.0 	324.0 	314.0 	309.0 	307.0 	377.0 	287.0 	301.0 	351.0 	297.0 	337.0 	319.0 	322.0 	316.0 	361.0 	363.0 	288.0 	219.0 	233.0 	221.0 	255.0 	306.0 	325.0 	235.0 	249.0 	299.0 	254.0 	245.0 	247.0 	242.0 	251.0 	301.0 	276.0 	285.0 	276.0 	270.0 	228.0 	175.0 	111.0 	106.0 	10.0  	176.0 	178.0 	155.0 	153.0 	149.0 	147.0 	249.0 	263.0 	231.0 	210.0 	189.0 	187.0 	201.0 	146.0 	114.0 	0.0   	116.0 	22.0  	179.0 	266.0 	197.0 	268.0 	233.0 	163.0 	165.0 	184.0 	186.0 	161.0 	227.0 	159.0
88    	116.0 	186.0 	131.0 	56.0  	189.0 	276.0 	221.0 	207.0 	134.0 	141.0 	74.0  	26.0  	77.0  	71.0  	199.0 	215.0 	249.0 	313.0 	292.0 	334.0 	327.0 	325.0 	342.0 	322.0 	307.0 	258.0 	242.0 	235.0 	233.0 	276.0 	272.0 	257.0 	260.0 	250.0 	245.0 	243.0 	313.0 	223.0 	237.0 	287.0 	233.0 	273.0 	255.0 	258.0 	252.0 	297.0 	299.0 	224.0 	155.0 	169.0 	157.0 	191.0 	242.0 	261.0 	171.0 	185.0 	235.0 	190.0 	181.0 	183.0 	178.0 	187.0 	237.0 	212.0 	221.0 	212.0 	206.0 	164.0 	111.0 	47.0  	10.0  	106.0 	112.0 	114.0 	91.0  	89.0  	85.0  	83.0  	185.0 	199.0 	167.0 	146.0 	125.0 	123.0 	137.0 	82.0  	50.0  	116.0 	0.0   	118.0 	115.0 	202.0 	133.0 	204.0 	169.0 	99.0  	101.0 	120.0 	122.0 	97.0  	163.0 	95.0
89    	182.0 	252.0 	197.0 	122.0 	255.0 	342.0 	287.0 	273.0 	200.0 	207.0 	140.0 	92.0  	143.0 	137.0 	265.0 	281.0 	315.0 	379.0 	358.0 	400.0 	393.0 	391.0 	408.0 	388.0 	373.0 	324.0 	308.0 	301.0 	299.0 	342.0 	338.0 	323.0 	326.0 	316.0 	311.0 	309.0 	379.0 	289.0 	303.0 	353.0 	299.0 	339.0 	321.0 	324.0 	318.0 	363.0 	365.0 	290.0 	221.0 	235.0 	223.0 	257.0 	308.0 	327.0 	237.0 	251.0 	301.0 	256.0 	247.0 	249.0 	244.0 	253.0 	303.0 	278.0 	287.0 	278.0 	272.0 	230.0 	177.0 	113.0 	108.0 	12.0  	178.0 	180.0 	157.0 	155.0 	151.0 	149.0 	251.0 	265.0 	233.0 	212.0 	191.0 	189.0 	203.0 	148.0 	116.0 	22.0  	118.0 	0.0   	181.0 	268.0 	199.0 	270.0 	235.0 	165.0 	167.0 	186.0 	188.0 	163.0 	229.0 	161.0
90    	119.0 	189.0 	134.0 	59.0  	192.0 	279.0 	224.0 	210.0 	137.0 	144.0 	77.0  	89.0  	38.0  	74.0  	202.0 	218.0 	252.0 	316.0 	295.0 	337.0 	330.0 	328.0 	345.0 	325.0 	310.0 	261.0 	245.0 	238.0 	236.0 	279.0 	275.0 	260.0 	263.0 	253.0 	248.0 	246.0 	316.0 	226.0 	240.0 	290.0 	236.0 	276.0 	258.0 	261.0 	255.0 	300.0 	302.0 	227.0 	158.0 	172.0 	160.0 	194.0 	245.0 	264.0 	174.0 	188.0 	238.0 	193.0 	184.0 	186.0 	181.0 	190.0 	240.0 	215.0 	224.0 	215.0 	209.0 	167.0 	114.0 	110.0 	105.0 	169.0 	3.0   	75.0  	52.0  	50.0  	88.0  	86.0  	188.0 	202.0 	170.0 	149.0 	128.0 	126.0 	200.0 	145.0 	113.0 	179.0 	115.0 	181.0 	0.0   	93.0  	24.0  	165.0 	130.0 	60.0  	62.0  	123.0 	125.0 	100.0 	166.0 	98.0
91    	206.0 	276.0 	221.0 	146.0 	279.0 	366.0 	311.0 	297.0 	224.0 	231.0 	164.0 	176.0 	125.0 	161.0 	289.0 	305.0 	339.0 	403.0 	382.0 	424.0 	417.0 	415.0 	432.0 	412.0 	397.0 	348.0 	332.0 	325.0 	323.0 	366.0 	362.0 	347.0 	350.0 	340.0 	335.0 	333.0 	403.0 	313.0 	327.0 	377.0 	323.0 	363.0 	345.0 	348.0 	342.0 	387.0 	389.0 	314.0 	245.0 	259.0 	247.0 	281.0 	332.0 	351.0 	261.0 	275.0 	325.0 	280.0 	271.0 	273.0 	268.0 	277.0 	327.0 	302.0 	311.0 	302.0 	296.0 	254.0 	201.0 	197.0 	192.0 	256.0 	90.0  	162.0 	139.0 	137.0 	175.0 	173.0 	275.0 	289.0 	257.0 	236.0 	215.0 	213.0 	287.0 	232.0 	200.0 	266.0 	202.0 	268.0 	93.0  	0.0   	111.0 	252.0 	217.0 	147.0 	149.0 	210.0 	212.0 	187.0 	253.0 	185.0
92    	137.0 	207.0 	152.0 	77.0  	210.0 	297.0 	242.0 	228.0 	155.0 	162.0 	95.0  	107.0 	56.0  	92.0  	220.0 	236.0 	270.0 	334.0 	313.0 	355.0 	348.0 	346.0 	363.0 	343.0 	328.0 	279.0 	263.0 	256.0 	254.0 	297.0 	293.0 	278.0 	281.0 	271.0 	266.0 	264.0 	334.0 	244.0 	258.0 	308.0 	254.0 	294.0 	276.0 	279.0 	273.0 	318.0 	320.0 	245.0 	176.0 	190.0 	178.0 	212.0 	263.0 	282.0 	192.0 	206.0 	256.0 	211.0 	202.0 	204.0 	199.0 	208.0 	258.0 	233.0 	242.0 	233.0 	227.0 	185.0 	132.0 	128.0 	123.0 	187.0 	21.0  	93.0  	70.0  	68.0  	106.0 	104.0 	206.0 	220.0 	188.0 	167.0 	146.0 	144.0 	218.0 	163.0 	131.0 	197.0 	133.0 	199.0 	24.0  	111.0 	0.0   	183.0 	148.0 	78.0  	80.0  	141.0 	143.0 	118.0 	184.0 	116.0
93    	208.0 	278.0 	223.0 	148.0 	281.0 	368.0 	313.0 	299.0 	226.0 	233.0 	166.0 	178.0 	127.0 	163.0 	291.0 	307.0 	341.0 	405.0 	384.0 	426.0 	419.0 	417.0 	434.0 	414.0 	399.0 	350.0 	334.0 	327.0 	325.0 	368.0 	364.0 	349.0 	352.0 	342.0 	337.0 	335.0 	405.0 	315.0 	329.0 	379.0 	325.0 	365.0 	347.0 	350.0 	344.0 	389.0 	391.0 	316.0 	247.0 	261.0 	249.0 	283.0 	334.0 	353.0 	263.0 	277.0 	327.0 	282.0 	273.0 	275.0 	270.0 	279.0 	329.0 	304.0 	313.0 	304.0 	298.0 	256.0 	203.0 	199.0 	194.0 	258.0 	162.0 	90.0  	141.0 	139.0 	177.0 	175.0 	277.0 	291.0 	259.0 	238.0 	217.0 	215.0 	289.0 	234.0 	202.0 	268.0 	204.0 	270.0 	165.0 	252.0 	183.0 	0.0   	219.0 	149.0 	151.0 	212.0 	214.0 	189.0 	255.0 	187.0
94    	173.0 	243.0 	188.0 	113.0 	246.0 	333.0 	278.0 	264.0 	191.0 	198.0 	131.0 	143.0 	92.0  	128.0 	256.0 	272.0 	306.0 	370.0 	349.0 	391.0 	384.0 	382.0 	399.0 	379.0 	364.0 	315.0 	299.0 	292.0 	290.0 	333.0 	329.0 	314.0 	317.0 	307.0 	302.0 	300.0 	370.0 	280.0 	294.0 	344.0 	290.0 	330.0 	312.0 	315.0 	309.0 	354.0 	356.0 	281.0 	212.0 	226.0 	214.0 	248.0 	299.0 	318.0 	228.0 	242.0 	292.0 	247.0 	238.0 	240.0 	235.0 	244.0 	294.0 	269.0 	278.0 	269.0 	263.0 	221.0 	168.0 	164.0 	159.0 	223.0 	127.0 	129.0 	106.0 	80.0  	142.0 	140.0 	242.0 	256.0 	224.0 	203.0 	182.0 	180.0 	254.0 	199.0 	167.0 	233.0 	169.0 	235.0 	130.0 	217.0 	148.0 	219.0 	0.0   	90.0  	116.0 	177.0 	179.0 	154.0 	220.0 	152.0
95    	103.0 	173.0 	118.0 	43.0  	176.0 	263.0 	208.0 	194.0 	121.0 	128.0 	61.0  	73.0  	22.0  	58.0  	186.0 	202.0 	236.0 	300.0 	279.0 	321.0 	314.0 	312.0 	329.0 	309.0 	294.0 	245.0 	229.0 	222.0 	220.0 	263.0 	259.0 	244.0 	247.0 	237.0 	232.0 	230.0 	300.0 	210.0 	224.0 	274.0 	220.0 	260.0 	242.0 	245.0 	239.0 	284.0 	286.0 	211.0 	142.0 	156.0 	144.0 	178.0 	229.0 	248.0 	158.0 	172.0 	222.0 	177.0 	168.0 	170.0 	165.0 	174.0 	224.0 	199.0 	208.0 	199.0 	193.0 	151.0 	98.0  	94.0  	89.0  	153.0 	57.0  	59.0  	36.0  	10.0  	72.0  	70.0  	172.0 	186.0 	154.0 	133.0 	112.0 	110.0 	184.0 	129.0 	97.0  	163.0 	99.0  	165.0 	60.0  	147.0 	78.0  	149.0 	90.0  	0.0   	46.0  	107.0 	109.0 	84.0  	150.0 	82.0
96    	105.0 	175.0 	120.0 	45.0  	178.0 	265.0 	210.0 	196.0 	123.0 	130.0 	63.0  	75.0  	24.0  	60.0  	188.0 	204.0 	238.0 	302.0 	281.0 	323.0 	316.0 	314.0 	331.0 	311.0 	296.0 	247.0 	231.0 	224.0 	222.0 	265.0 	261.0 	246.0 	249.0 	239.0 	234.0 	232.0 	302.0 	212.0 	226.0 	276.0 	222.0 	262.0 	244.0 	247.0 	241.0 	286.0 	288.0 	213.0 	144.0 	158.0 	146.0 	180.0 	231.0 	250.0 	160.0 	174.0 	224.0 	179.0 	170.0 	172.0 	167.0 	176.0 	226.0 	201.0 	210.0 	201.0 	195.0 	153.0 	100.0 	96.0  	91.0  	155.0 	59.0  	61.0  	10.0  	36.0  	74.0  	72.0  	174.0 	188.0 	156.0 	135.0 	114.0 	112.0 	186.0 	131.0 	99.0  	165.0 	101.0 	167.0 	62.0  	149.0 	80.0  	151.0 	116.0 	46.0  	0.0   	109.0 	111.0 	86.0  	152.0 	84.0
97    	124.0 	194.0 	139.0 	64.0  	197.0 	284.0 	229.0 	215.0 	142.0 	149.0 	82.0  	94.0  	85.0  	49.0  	207.0 	223.0 	257.0 	321.0 	300.0 	342.0 	335.0 	333.0 	350.0 	330.0 	315.0 	266.0 	250.0 	243.0 	241.0 	284.0 	280.0 	265.0 	268.0 	258.0 	253.0 	251.0 	321.0 	231.0 	245.0 	295.0 	241.0 	281.0 	263.0 	266.0 	260.0 	305.0 	307.0 	232.0 	163.0 	177.0 	165.0 	199.0 	250.0 	269.0 	179.0 	193.0 	243.0 	198.0 	189.0 	191.0 	186.0 	195.0 	245.0 	220.0 	229.0 	220.0 	214.0 	172.0 	119.0 	115.0 	110.0 	174.0 	120.0 	122.0 	99.0  	97.0  	35.0  	61.0  	193.0 	207.0 	175.0 	154.0 	133.0 	131.0 	205.0 	150.0 	118.0 	184.0 	120.0 	186.0 	123.0 	210.0 	141.0 	212.0 	177.0 	107.0 	109.0 	0.0   	72.0  	47.0  	141.0 	73.0
98    	126.0 	196.0 	141.0 	66.0  	199.0 	286.0 	231.0 	217.0 	144.0 	151.0 	84.0  	96.0  	87.0  	51.0  	209.0 	225.0 	259.0 	323.0 	302.0 	344.0 	337.0 	335.0 	352.0 	332.0 	317.0 	268.0 	252.0 	245.0 	243.0 	286.0 	282.0 	267.0 	270.0 	260.0 	255.0 	253.0 	323.0 	233.0 	247.0 	297.0 	243.0 	283.0 	265.0 	268.0 	262.0 	307.0 	309.0 	234.0 	165.0 	179.0 	167.0 	201.0 	252.0 	271.0 	181.0 	195.0 	245.0 	200.0 	191.0 	193.0 	188.0 	197.0 	247.0 	222.0 	231.0 	222.0 	216.0 	174.0 	121.0 	117.0 	112.0 	176.0 	122.0 	124.0 	101.0 	99.0  	37.0  	63.0  	195.0 	209.0 	177.0 	156.0 	135.0 	133.0 	207.0 	152.0 	120.0 	186.0 	122.0 	188.0 	125.0 	212.0 	143.0 	214.0 	179.0 	109.0 	111.0 	72.0  	0.0   	49.0  	143.0 	75.0
99    	101.0 	171.0 	116.0 	41.0  	174.0 	261.0 	206.0 	192.0 	119.0 	126.0 	59.0  	71.0  	62.0  	26.0  	184.0 	200.0 	234.0 	298.0 	277.0 	319.0 	312.0 	310.0 	327.0 	307.0 	292.0 	243.0 	227.0 	220.0 	218.0 	261.0 	257.0 	242.0 	245.0 	235.0 	230.0 	228.0 	298.0 	208.0 	222.0 	272.0 	218.0 	258.0 	240.0 	243.0 	237.0 	282.0 	284.0 	209.0 	140.0 	154.0 	142.0 	176.0 	227.0 	246.0 	156.0 	170.0 	220.0 	175.0 	166.0 	168.0 	163.0 	172.0 	222.0 	197.0 	206.0 	197.0 	191.0 	149.0 	96.0  	92.0  	87.0  	151.0 	97.0  	99.0  	76.0  	74.0  	12.0  	38.0  	170.0 	184.0 	152.0 	131.0 	110.0 	108.0 	182.0 	127.0 	95.0  	161.0 	97.0  	163.0 	100.0 	187.0 	118.0 	189.0 	154.0 	84.0  	86.0  	47.0  	49.0  	0.0   	118.0 	50.0
100   	167.0 	237.0 	182.0 	107.0 	240.0 	327.0 	272.0 	258.0 	185.0 	192.0 	125.0 	137.0 	128.0 	92.0  	250.0 	266.0 	300.0 	364.0 	343.0 	385.0 	378.0 	376.0 	393.0 	373.0 	358.0 	309.0 	293.0 	286.0 	284.0 	327.0 	323.0 	308.0 	311.0 	301.0 	296.0 	294.0 	364.0 	274.0 	288.0 	338.0 	284.0 	324.0 	306.0 	309.0 	303.0 	348.0 	350.0 	275.0 	206.0 	220.0 	208.0 	242.0 	293.0 	312.0 	222.0 	236.0 	286.0 	241.0 	232.0 	234.0 	229.0 	238.0 	288.0 	263.0 	272.0 	263.0 	257.0 	215.0 	162.0 	158.0 	153.0 	217.0 	163.0 	165.0 	142.0 	140.0 	106.0 	80.0  	236.0 	250.0 	218.0 	197.0 	176.0 	174.0 	248.0 	193.0 	161.0 	227.0 	163.0 	229.0 	166.0 	253.0 	184.0 	255.0 	220.0 	150.0 	152.0 	141.0 	143.0 	118.0 	0.0   	92.0
101   	99.0  	169.0 	114.0 	39.0  	172.0 	259.0 	204.0 	190.0 	117.0 	124.0 	57.0  	69.0  	60.0  	24.0  	182.0 	198.0 	232.0 	296.0 	275.0 	317.0 	310.0 	308.0 	325.0 	305.0 	290.0 	241.0 	225.0 	218.0 	216.0 	259.0 	255.0 	240.0 	243.0 	233.0 	228.0 	226.0 	296.0 	206.0 	220.0 	270.0 	216.0 	256.0 	238.0 	241.0 	235.0 	280.0 	282.0 	207.0 	138.0 	152.0 	140.0 	174.0 	225.0 	244.0 	154.0 	168.0 	218.0 	173.0 	164.0 	166.0 	161.0 	170.0 	220.0 	195.0 	204.0 	195.0 	189.0 	147.0 	94.0  	90.0  	85.0  	149.0 	95.0  	97.0  	74.0  	72.0  	38.0  	12.0  	168.0 	182.0 	150.0 	129.0 	108.0 	106.0 	180.0 	125.0 	93.0  	159.0 	95.0  	161.0 	98.0  	185.0 	116.0 	187.0 	152.0 	82.0  	84.0  	73.0  	75.0  	50.0  	92.0  	0.0
-------END FULL SUITE----------
-------------End Test: 2 -------------
*/
    //>graph3
    /*
-------------Test: 3 ---Testing file: graph3.txt----------
-------FULL SUITE----------
(10,13)	0	1	2	3	4	5	6	7	8	9
0	0	10	0	0	0	0	0	0	0	0
1	0	0	10	0	0	0	8	13	0	0
2	0	0	0	10	0	0	0	8	13	0
3	0	0	0	0	10	0	0	0	8	0
4	0	0	0	0	0	0	0	0	0	0
5	0	0	0	0	0	0	10	0	0	0
6	0	0	0	0	0	0	0	10	0	0
7	0	0	0	0	0	0	0	0	10	0
8	0	0	0	0	0	0	0	0	0	10
9	0	0	0	0	0	0	0	0	0	0
DFS:[0][1][2][3][4][8][9][7][6][5]
BFS:[0][1][2][6][7][3][8][4][9][5]
Strongly Connected:
[4]
[9]
[8]
[3]
[7]
[2]
[6]
[1]
[0]
[5]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
Distance from: [2] TO: [6]--> Infinity
------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5     	6     	7     	8     	9
0     	0.0   	10.0  	20.0  	30.0  	40.0  	Inf 	18.0  	23.0  	33.0  	43.0
1     	Inf 	0.0   	10.0  	20.0  	30.0  	Inf 	8.0   	13.0  	23.0  	33.0
2     	Inf 	Inf 	0.0   	10.0  	20.0  	Inf 	Inf 	8.0   	13.0  	23.0
3     	Inf 	Inf 	Inf 	0.0   	10.0  	Inf 	Inf 	Inf 	8.0   	18.0
4     	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf
5     	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	10.0  	20.0  	30.0  	40.0
6     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	10.0  	20.0  	30.0
7     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	10.0  	20.0
8     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	10.0
9     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
-------------End Test: 3 -------------
*/
    //>graph4
    /*
-------------Test: 4 ---Testing file: graph4.txt----------
-------FULL SUITE----------
(9,16)	0	1	2	3	4	5	6	7	8
0	0	8	12	0	0	0	0	0	0
1	0	0	13	25	0	0	0	0	0
2	0	0	0	14	0	0	21	0	0
3	0	0	0	0	20	8	12	12	16
4	0	9	0	0	0	19	0	0	0
5	0	0	0	0	0	0	0	11	0
6	0	0	0	0	0	0	0	0	11
7	0	0	0	0	0	0	0	0	9
8	0	0	0	0	0	0	0	0	0
DFS:[0][1][2][3][4][5][7][8][6]
BFS:[0][1][2][3][6][4][5][7][8]
Strongly Connected:
[8]
[7]
[5]
[6]
[4][3][2][1]
[0]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
Distance from: [2] TO: [6]--> 21.0
------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5     	6     	7     	8
0     	0.0   	8.0   	12.0  	26.0  	46.0  	34.0  	33.0  	38.0  	42.0
1     	Inf 	0.0   	13.0  	25.0  	45.0  	33.0  	34.0  	37.0  	41.0
2     	Inf 	43.0  	0.0   	14.0  	34.0  	22.0  	21.0  	26.0  	30.0
3     	Inf 	29.0  	42.0  	0.0   	20.0  	8.0   	12.0  	12.0  	16.0
4     	Inf 	9.0   	22.0  	34.0  	0.0   	19.0  	43.0  	30.0  	39.0
5     	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	11.0  	20.0
6     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	11.0
7     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	9.0
8     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
-------------End Test: 4 -------------
*/
    //>graph5
    /*
-------------Test: 5 ---Testing file: graph5.txt----------
-------FULL SUITE----------
(6,8)	0	1	2	3	4	5
0	0	1	7	0	0	0
1	0	0	0	9	0	15
2	0	0	0	0	4	0
3	0	0	0	0	10	5
4	0	0	0	0	0	3
5	0	0	0	0	0	0
DFS:[0][1][3][4][5][2]
BFS:[0][1][2][3][5][4]
Strongly Connected:
[5]
[4]
[3]
[1]
[2]
[0]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5
0     	0.0   	1.0   	7.0   	10.0  	11.0  	14.0
1     	Inf 	0.0   	Inf 	9.0   	19.0  	14.0
2     	Inf 	Inf 	0.0   	Inf 	4.0   	7.0
3     	Inf 	Inf 	Inf 	0.0   	10.0  	5.0
4     	Inf 	Inf 	Inf 	Inf 	0.0   	3.0
5     	Inf 	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
-------------End Test: 5 -------------
*/
    //>graph6
    /*
-------------Test: 6 ---Testing file: graph6.txt----------
-------FULL SUITE----------
(5,5)	0	1	2	3	4
0	0	1	10	0	0
1	0	0	0	2	0
2	0	0	0	-10	0
3	0	0	0	0	3
4	0	0	0	0	0
DFS:[0][1][3][4][2]
BFS:[0][1][2][3][4]
Strongly Connected:
[4]
[3]
[1]
[2]
[0]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4
0     	0.0   	1.0   	10.0  	0.0   	3.0
1     	Inf 	0.0   	Inf 	2.0   	5.0
2     	Inf 	Inf 	0.0   	-10.0 	-7.0
3     	Inf 	Inf 	Inf 	0.0   	3.0
4     	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
-------------End Test: 6 -------------
*/
    //>graph7
    /*
-------------Test: 7 ---Testing file: graph7Numbered.txt----------
-------FULL SUITE----------
(63,94)	0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20	21	22	23	24	25	26	27	28	29	30	31	32	33	34	35	36	37	38	39	40	41	42	43	44	45	46	47	48	49	50	51	52	53	54	55	56	57	58	59	60	61	62
0	0	46	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	34	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
1	0	0	93	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	16	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
2	0	0	0	17	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
3	0	0	0	0	95	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
4	0	0	0	0	0	71	0	0	0	0	0	0	0	0	0	0	0	0	0	51	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
5	0	0	0	0	0	0	3	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
6	0	0	0	0	0	0	0	61	0	0	0	0	0	0	0	0	0	0	71	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
7	0	0	0	0	0	0	0	0	86	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
8	0	0	0	0	0	0	0	0	0	36	0	0	0	0	0	0	0	55	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
9	0	0	0	0	0	0	0	0	0	0	97	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
10	0	0	0	0	0	0	0	0	0	0	0	29	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
11	0	0	0	0	0	0	0	0	62	0	0	0	80	0	0	0	93	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
13	0	0	0	0	0	0	0	0	0	0	0	0	73	0	0	36	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
14	0	0	0	0	0	0	0	0	0	0	0	0	0	30	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
15	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	54	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
16	0	0	0	0	0	0	0	0	0	0	0	0	0	19	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
17	0	0	0	0	0	0	76	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	43	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
18	0	0	0	0	18	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	70	0	24	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
19	0	0	55	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
20	0	10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	38	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
21	0	0	0	0	82	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	93	0	10	0	0	0	0	0	0	0	0	0	0	0	0	0
22	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	42	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
23	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	35	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
24	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
25	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	27	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
26	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	33	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
27	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	40	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
28	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	20	0	16	0	0	0	91	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
29	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	59	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
30	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	47	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
31	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
32	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
33	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	97	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
34	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	26	36	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
35	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	88	0	18	0	0	0	0	0	39	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
36	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	76	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
37	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
38	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	83	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
39	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	75	22	0	87	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
40	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	57	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
41	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	94	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	72	0	0	0	0	7	52	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
42	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
43	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
44	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	82	0	0	0	0	0	0	25	0	0	0	0	0	0	0	0	0	0	0	0
45	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	62	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
46	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
47	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	38	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
48	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	55	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	80	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
49	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	6	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	62	0	0	0	0	0	0	0	0	0	0	0	0	0	0
50	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	13	78	0	0	0	0	0	0	0	0	0	0
51	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
52	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	19	0	0	0	0	0	0	0	0	0
53	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	5	0	0	0	0	0	0	47	26
54	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	33	0	0	0	0	0	0	0
55	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	88	0	0	0	0	23	0
56	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	17	0	82	0	0	0
57	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	39	52	0	0	0
58	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	16	0	0	0
59	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	91	0	0
60	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	10	9	60	0	0	0	0	0
61	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	64	0	0	0	0	0	0	0	3
62	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	34	0	92	0	0	0	0	0	0	0	0
DFS:[0][1][2][3][4][5][6][7][8][9][10][11][12][16][13][15][26][28][27][29][30][31][32][33][34][35][25][17][24][36][37][18][22][41][23][38][39][40][42][43][44][50][51][52][53][54][55][56][57][58][59][60][61][62][45][46][19][20][21][47][49][48][14]
BFS:[0][1][20][2][19][21][3][4][47][49][5][22][48][6][41][7][18][23][39][44][45][8][24][38][37][40][43][50][46][9][17][35][42][51][52][10][25][27][33][53][11][26][34][54][61][62][12][16][28][36][55][13][29][56][15][30][57][59][31][58][60][32][14]
Strongly Connected:
[12]
[32]
[31]
[30]
[29]
[24]
[37]
[36]
[42]
[43]
[40]
[51]
[62][61][60][59][58][57][56][55][54][53][52]
[50]
[44]
[46]
[45]
[19][39][38][23][41][22][18][17][25][35][34][33][27][28][26][15][13][16][11][10][9][8][7][6][5][4][3][2]
[1]
[47]
[48][49][21][20]
[0]
[14]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
Distance from: [2] TO: [6]--> 186.0
------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5     	6     	7     	8     	9     	10    	11    	12    	13    	14    	15    	16    	17    	18    	19    	20    	21    	22    	23    	24    	25    	26    	27    	28    	29    	30    	31    	32    	33    	34    	35    	36    	37    	38    	39    	40    	41    	42    	43    	44    	45    	46    	47    	48    	49    	50    	51    	52    	53    	54    	55    	56    	57    	58    	59    	60    	61    	62
0     	0.0   	44.0  	115.0 	132.0 	154.0 	225.0 	228.0 	289.0 	375.0 	411.0 	508.0 	537.0 	617.0 	649.0 	Inf 	685.0 	630.0 	430.0 	299.0 	60.0  	34.0  	72.0  	203.0 	339.0 	323.0 	510.0 	480.0 	440.0 	513.0 	529.0 	588.0 	635.0 	647.0 	461.0 	558.0 	422.0 	594.0 	392.0 	339.0 	317.0 	404.0 	245.0 	405.0 	334.0 	252.0 	297.0 	359.0 	165.0 	144.0 	82.0  	277.0 	290.0 	355.0 	374.0 	379.0 	412.0 	500.0 	517.0 	556.0 	569.0 	660.0 	421.0 	400.0
1     	Inf 	0.0   	71.0  	88.0  	183.0 	254.0 	257.0 	318.0 	404.0 	440.0 	537.0 	566.0 	646.0 	678.0 	Inf 	714.0 	659.0 	459.0 	328.0 	16.0  	Inf 	Inf 	398.0 	534.0 	352.0 	705.0 	675.0 	635.0 	708.0 	724.0 	783.0 	830.0 	842.0 	656.0 	753.0 	617.0 	789.0 	587.0 	534.0 	512.0 	599.0 	440.0 	600.0 	529.0 	447.0 	492.0 	554.0 	Inf 	Inf 	Inf 	472.0 	485.0 	550.0 	569.0 	574.0 	607.0 	695.0 	712.0 	751.0 	764.0 	855.0 	616.0 	595.0
2     	Inf 	Inf 	0.0   	17.0  	112.0 	183.0 	186.0 	247.0 	333.0 	369.0 	466.0 	495.0 	575.0 	607.0 	Inf 	643.0 	588.0 	388.0 	257.0 	163.0 	Inf 	Inf 	327.0 	463.0 	281.0 	634.0 	604.0 	564.0 	637.0 	653.0 	712.0 	759.0 	771.0 	585.0 	682.0 	546.0 	718.0 	516.0 	463.0 	441.0 	528.0 	369.0 	529.0 	458.0 	376.0 	421.0 	483.0 	Inf 	Inf 	Inf 	401.0 	414.0 	479.0 	498.0 	503.0 	536.0 	624.0 	641.0 	680.0 	693.0 	784.0 	545.0 	524.0
3     	Inf 	Inf 	201.0 	0.0   	95.0  	166.0 	169.0 	230.0 	316.0 	352.0 	449.0 	478.0 	558.0 	590.0 	Inf 	626.0 	571.0 	371.0 	240.0 	146.0 	Inf 	Inf 	310.0 	446.0 	264.0 	617.0 	587.0 	547.0 	620.0 	636.0 	695.0 	742.0 	754.0 	568.0 	665.0 	529.0 	701.0 	499.0 	446.0 	424.0 	511.0 	352.0 	512.0 	441.0 	359.0 	404.0 	466.0 	Inf 	Inf 	Inf 	384.0 	397.0 	462.0 	481.0 	486.0 	519.0 	607.0 	624.0 	663.0 	676.0 	767.0 	528.0 	507.0
4     	Inf 	Inf 	106.0 	123.0 	0.0   	71.0  	74.0  	135.0 	221.0 	257.0 	354.0 	383.0 	463.0 	495.0 	Inf 	531.0 	476.0 	276.0 	145.0 	51.0  	Inf 	Inf 	215.0 	351.0 	169.0 	522.0 	492.0 	452.0 	525.0 	541.0 	600.0 	647.0 	659.0 	473.0 	570.0 	434.0 	606.0 	404.0 	351.0 	329.0 	416.0 	257.0 	417.0 	346.0 	264.0 	309.0 	371.0 	Inf 	Inf 	Inf 	289.0 	302.0 	367.0 	386.0 	391.0 	424.0 	512.0 	529.0 	568.0 	581.0 	672.0 	433.0 	412.0
5     	Inf 	Inf 	198.0 	215.0 	92.0  	0.0   	3.0   	64.0  	150.0 	186.0 	283.0 	312.0 	392.0 	424.0 	Inf 	460.0 	405.0 	205.0 	74.0  	143.0 	Inf 	Inf 	144.0 	280.0 	98.0  	451.0 	421.0 	381.0 	454.0 	470.0 	529.0 	576.0 	588.0 	402.0 	499.0 	363.0 	535.0 	333.0 	280.0 	258.0 	345.0 	186.0 	346.0 	275.0 	193.0 	238.0 	300.0 	Inf 	Inf 	Inf 	218.0 	231.0 	296.0 	315.0 	320.0 	353.0 	441.0 	458.0 	497.0 	510.0 	601.0 	362.0 	341.0
6     	Inf 	Inf 	195.0 	212.0 	89.0  	160.0 	0.0   	61.0  	147.0 	183.0 	280.0 	309.0 	389.0 	421.0 	Inf 	457.0 	402.0 	202.0 	71.0  	140.0 	Inf 	Inf 	141.0 	277.0 	95.0  	448.0 	418.0 	378.0 	451.0 	467.0 	526.0 	573.0 	585.0 	399.0 	496.0 	360.0 	532.0 	330.0 	277.0 	255.0 	342.0 	183.0 	343.0 	272.0 	190.0 	235.0 	297.0 	Inf 	Inf 	Inf 	215.0 	228.0 	293.0 	312.0 	317.0 	350.0 	438.0 	455.0 	494.0 	507.0 	598.0 	359.0 	338.0
7     	Inf 	Inf 	412.0 	429.0 	306.0 	377.0 	217.0 	0.0   	86.0  	122.0 	219.0 	248.0 	328.0 	360.0 	Inf 	396.0 	341.0 	141.0 	288.0 	357.0 	Inf 	Inf 	358.0 	494.0 	184.0 	665.0 	450.0 	503.0 	483.0 	499.0 	558.0 	605.0 	617.0 	574.0 	671.0 	577.0 	707.0 	547.0 	494.0 	472.0 	559.0 	400.0 	560.0 	489.0 	407.0 	452.0 	514.0 	Inf 	Inf 	Inf 	432.0 	445.0 	510.0 	529.0 	534.0 	567.0 	655.0 	672.0 	711.0 	724.0 	815.0 	576.0 	555.0
8     	Inf 	Inf 	326.0 	343.0 	220.0 	291.0 	131.0 	192.0 	0.0   	36.0  	133.0 	162.0 	242.0 	274.0 	Inf 	310.0 	255.0 	55.0  	202.0 	271.0 	Inf 	Inf 	272.0 	408.0 	98.0  	579.0 	364.0 	417.0 	397.0 	413.0 	472.0 	519.0 	531.0 	488.0 	585.0 	491.0 	621.0 	461.0 	408.0 	386.0 	473.0 	314.0 	474.0 	403.0 	321.0 	366.0 	428.0 	Inf 	Inf 	Inf 	346.0 	359.0 	424.0 	443.0 	448.0 	481.0 	569.0 	586.0 	625.0 	638.0 	729.0 	490.0 	469.0
9     	Inf 	Inf 	514.0 	531.0 	408.0 	479.0 	319.0 	380.0 	188.0 	0.0   	97.0  	126.0 	206.0 	238.0 	Inf 	274.0 	219.0 	243.0 	390.0 	459.0 	Inf 	Inf 	460.0 	596.0 	286.0 	663.0 	328.0 	381.0 	361.0 	377.0 	436.0 	483.0 	495.0 	452.0 	549.0 	575.0 	585.0 	649.0 	596.0 	574.0 	661.0 	502.0 	662.0 	591.0 	509.0 	554.0 	616.0 	Inf 	Inf 	Inf 	534.0 	547.0 	612.0 	631.0 	636.0 	669.0 	757.0 	774.0 	813.0 	826.0 	917.0 	678.0 	657.0
10    	Inf 	Inf 	417.0 	434.0 	311.0 	382.0 	222.0 	283.0 	91.0  	127.0 	0.0   	29.0  	109.0 	141.0 	Inf 	177.0 	122.0 	146.0 	293.0 	362.0 	Inf 	Inf 	363.0 	499.0 	189.0 	566.0 	231.0 	284.0 	264.0 	280.0 	339.0 	386.0 	398.0 	355.0 	452.0 	478.0 	488.0 	552.0 	499.0 	477.0 	564.0 	405.0 	565.0 	494.0 	412.0 	457.0 	519.0 	Inf 	Inf 	Inf 	437.0 	450.0 	515.0 	534.0 	539.0 	572.0 	660.0 	677.0 	716.0 	729.0 	820.0 	581.0 	560.0
11    	Inf 	Inf 	388.0 	405.0 	282.0 	353.0 	193.0 	254.0 	62.0  	98.0  	195.0 	0.0   	80.0  	112.0 	Inf 	148.0 	93.0  	117.0 	264.0 	333.0 	Inf 	Inf 	334.0 	470.0 	160.0 	537.0 	202.0 	255.0 	235.0 	251.0 	310.0 	357.0 	369.0 	326.0 	423.0 	449.0 	459.0 	523.0 	470.0 	448.0 	535.0 	376.0 	536.0 	465.0 	383.0 	428.0 	490.0 	Inf 	Inf 	Inf 	408.0 	421.0 	486.0 	505.0 	510.0 	543.0 	631.0 	648.0 	687.0 	700.0 	791.0 	552.0 	531.0
12    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
13    	Inf 	Inf 	723.0 	740.0 	617.0 	688.0 	528.0 	589.0 	675.0 	711.0 	808.0 	837.0 	73.0  	0.0   	Inf 	36.0  	930.0 	452.0 	599.0 	668.0 	Inf 	Inf 	669.0 	805.0 	495.0 	425.0 	90.0  	143.0 	123.0 	139.0 	198.0 	245.0 	257.0 	214.0 	311.0 	337.0 	347.0 	423.0 	805.0 	783.0 	870.0 	711.0 	871.0 	800.0 	718.0 	763.0 	825.0 	Inf 	Inf 	Inf 	743.0 	756.0 	821.0 	840.0 	845.0 	878.0 	966.0 	983.0 	1022.0	1035.0	1126.0	887.0 	866.0
14    	Inf 	Inf 	753.0 	770.0 	647.0 	718.0 	558.0 	619.0 	705.0 	741.0 	838.0 	867.0 	103.0 	30.0  	0.0   	66.0  	960.0 	482.0 	629.0 	698.0 	Inf 	Inf 	699.0 	835.0 	525.0 	455.0 	120.0 	173.0 	153.0 	169.0 	228.0 	275.0 	287.0 	244.0 	341.0 	367.0 	377.0 	453.0 	835.0 	813.0 	900.0 	741.0 	901.0 	830.0 	748.0 	793.0 	855.0 	Inf 	Inf 	Inf 	773.0 	786.0 	851.0 	870.0 	875.0 	908.0 	996.0 	1013.0	1052.0	1065.0	1156.0	917.0 	896.0
15    	Inf 	Inf 	687.0 	704.0 	581.0 	652.0 	492.0 	553.0 	639.0 	675.0 	772.0 	801.0 	881.0 	913.0 	Inf 	0.0   	894.0 	416.0 	563.0 	632.0 	Inf 	Inf 	633.0 	769.0 	459.0 	389.0 	54.0  	107.0 	87.0  	103.0 	162.0 	209.0 	221.0 	178.0 	275.0 	301.0 	311.0 	387.0 	769.0 	747.0 	834.0 	675.0 	835.0 	764.0 	682.0 	727.0 	789.0 	Inf 	Inf 	Inf 	707.0 	720.0 	785.0 	804.0 	809.0 	842.0 	930.0 	947.0 	986.0 	999.0 	1090.0	851.0 	830.0
16    	Inf 	Inf 	742.0 	759.0 	636.0 	707.0 	547.0 	608.0 	694.0 	730.0 	827.0 	856.0 	92.0  	19.0  	Inf 	55.0  	0.0   	471.0 	618.0 	687.0 	Inf 	Inf 	688.0 	824.0 	514.0 	444.0 	109.0 	162.0 	142.0 	158.0 	217.0 	264.0 	276.0 	233.0 	330.0 	356.0 	366.0 	442.0 	824.0 	802.0 	889.0 	730.0 	890.0 	819.0 	737.0 	782.0 	844.0 	Inf 	Inf 	Inf 	762.0 	775.0 	840.0 	859.0 	864.0 	897.0 	985.0 	1002.0	1041.0	1054.0	1145.0	906.0 	885.0
17    	Inf 	Inf 	271.0 	288.0 	165.0 	236.0 	76.0  	137.0 	223.0 	259.0 	356.0 	385.0 	465.0 	497.0 	Inf 	533.0 	478.0 	0.0   	147.0 	216.0 	Inf 	Inf 	217.0 	353.0 	43.0  	524.0 	494.0 	454.0 	527.0 	543.0 	602.0 	649.0 	661.0 	475.0 	572.0 	436.0 	608.0 	406.0 	353.0 	331.0 	418.0 	259.0 	419.0 	348.0 	266.0 	311.0 	373.0 	Inf 	Inf 	Inf 	291.0 	304.0 	369.0 	388.0 	393.0 	426.0 	514.0 	531.0 	570.0 	583.0 	674.0 	435.0 	414.0
18    	Inf 	Inf 	124.0 	141.0 	18.0  	89.0  	92.0  	153.0 	239.0 	275.0 	372.0 	401.0 	481.0 	513.0 	Inf 	549.0 	494.0 	294.0 	0.0   	69.0  	Inf 	Inf 	70.0  	206.0 	24.0  	377.0 	347.0 	307.0 	380.0 	396.0 	455.0 	502.0 	514.0 	328.0 	425.0 	289.0 	461.0 	259.0 	206.0 	184.0 	271.0 	112.0 	272.0 	201.0 	119.0 	164.0 	226.0 	Inf 	Inf 	Inf 	144.0 	157.0 	222.0 	241.0 	246.0 	279.0 	367.0 	384.0 	423.0 	436.0 	527.0 	288.0 	267.0
19    	Inf 	Inf 	55.0  	72.0  	167.0 	238.0 	241.0 	302.0 	388.0 	424.0 	521.0 	550.0 	630.0 	662.0 	Inf 	698.0 	643.0 	443.0 	312.0 	0.0   	Inf 	Inf 	382.0 	518.0 	336.0 	689.0 	659.0 	619.0 	692.0 	708.0 	767.0 	814.0 	826.0 	640.0 	737.0 	601.0 	773.0 	571.0 	518.0 	496.0 	583.0 	424.0 	584.0 	513.0 	431.0 	476.0 	538.0 	Inf 	Inf 	Inf 	456.0 	469.0 	534.0 	553.0 	558.0 	591.0 	679.0 	696.0 	735.0 	748.0 	839.0 	600.0 	579.0
20    	Inf 	10.0  	81.0  	98.0  	120.0 	191.0 	194.0 	255.0 	341.0 	377.0 	474.0 	503.0 	583.0 	615.0 	Inf 	651.0 	596.0 	396.0 	265.0 	26.0  	0.0   	38.0  	169.0 	305.0 	289.0 	476.0 	446.0 	406.0 	479.0 	495.0 	554.0 	601.0 	613.0 	427.0 	524.0 	388.0 	560.0 	358.0 	305.0 	283.0 	370.0 	211.0 	371.0 	300.0 	218.0 	263.0 	325.0 	131.0 	110.0 	48.0  	243.0 	256.0 	321.0 	340.0 	345.0 	378.0 	466.0 	483.0 	522.0 	535.0 	626.0 	387.0 	366.0
21    	Inf 	26.0  	97.0  	114.0 	82.0  	153.0 	156.0 	217.0 	303.0 	339.0 	436.0 	465.0 	545.0 	577.0 	Inf 	613.0 	558.0 	358.0 	227.0 	42.0  	16.0  	0.0   	131.0 	267.0 	251.0 	438.0 	408.0 	368.0 	441.0 	457.0 	516.0 	563.0 	575.0 	389.0 	486.0 	350.0 	522.0 	320.0 	267.0 	245.0 	332.0 	173.0 	333.0 	262.0 	180.0 	225.0 	287.0 	93.0  	72.0  	10.0  	205.0 	218.0 	283.0 	302.0 	307.0 	340.0 	428.0 	445.0 	484.0 	497.0 	588.0 	349.0 	328.0
22    	Inf 	Inf 	340.0 	357.0 	234.0 	305.0 	308.0 	369.0 	455.0 	491.0 	588.0 	617.0 	697.0 	729.0 	Inf 	765.0 	710.0 	334.0 	216.0 	285.0 	Inf 	Inf 	0.0   	136.0 	240.0 	307.0 	277.0 	237.0 	310.0 	326.0 	385.0 	432.0 	444.0 	258.0 	355.0 	219.0 	391.0 	189.0 	136.0 	114.0 	201.0 	42.0  	202.0 	131.0 	49.0  	94.0  	156.0 	Inf 	Inf 	Inf 	74.0  	87.0  	152.0 	171.0 	176.0 	209.0 	297.0 	314.0 	353.0 	366.0 	457.0 	218.0 	197.0
23    	Inf 	Inf 	204.0 	221.0 	98.0  	169.0 	172.0 	233.0 	319.0 	355.0 	452.0 	481.0 	561.0 	593.0 	Inf 	629.0 	574.0 	233.0 	80.0  	149.0 	Inf 	Inf 	150.0 	0.0   	104.0 	206.0 	176.0 	136.0 	209.0 	225.0 	284.0 	331.0 	343.0 	157.0 	254.0 	118.0 	290.0 	339.0 	35.0  	264.0 	351.0 	192.0 	352.0 	281.0 	199.0 	244.0 	306.0 	Inf 	Inf 	Inf 	224.0 	237.0 	302.0 	321.0 	326.0 	359.0 	447.0 	464.0 	503.0 	516.0 	607.0 	368.0 	347.0
24    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
25    	Inf 	Inf 	298.0 	315.0 	192.0 	263.0 	103.0 	164.0 	250.0 	286.0 	383.0 	412.0 	492.0 	524.0 	Inf 	560.0 	505.0 	27.0  	174.0 	243.0 	Inf 	Inf 	244.0 	380.0 	70.0  	0.0   	521.0 	481.0 	554.0 	570.0 	629.0 	676.0 	688.0 	502.0 	599.0 	463.0 	635.0 	433.0 	380.0 	358.0 	445.0 	286.0 	446.0 	375.0 	293.0 	338.0 	400.0 	Inf 	Inf 	Inf 	318.0 	331.0 	396.0 	415.0 	420.0 	453.0 	541.0 	558.0 	597.0 	610.0 	701.0 	462.0 	441.0
26    	Inf 	Inf 	633.0 	650.0 	527.0 	598.0 	438.0 	499.0 	585.0 	621.0 	718.0 	747.0 	827.0 	859.0 	Inf 	895.0 	840.0 	362.0 	509.0 	578.0 	Inf 	Inf 	579.0 	715.0 	405.0 	335.0 	0.0   	53.0  	33.0  	49.0  	108.0 	155.0 	167.0 	124.0 	221.0 	247.0 	257.0 	333.0 	715.0 	693.0 	780.0 	621.0 	781.0 	710.0 	628.0 	673.0 	735.0 	Inf 	Inf 	Inf 	653.0 	666.0 	731.0 	750.0 	755.0 	788.0 	876.0 	893.0 	932.0 	945.0 	1036.0	797.0 	776.0
27    	Inf 	Inf 	673.0 	690.0 	567.0 	638.0 	478.0 	539.0 	625.0 	661.0 	758.0 	787.0 	867.0 	899.0 	Inf 	935.0 	880.0 	402.0 	549.0 	618.0 	Inf 	Inf 	619.0 	755.0 	445.0 	375.0 	40.0  	0.0   	73.0  	89.0  	148.0 	195.0 	207.0 	164.0 	261.0 	287.0 	297.0 	373.0 	755.0 	733.0 	820.0 	661.0 	821.0 	750.0 	668.0 	713.0 	775.0 	Inf 	Inf 	Inf 	693.0 	706.0 	771.0 	790.0 	795.0 	828.0 	916.0 	933.0 	972.0 	985.0 	1076.0	837.0 	816.0
28    	Inf 	Inf 	600.0 	617.0 	494.0 	565.0 	405.0 	466.0 	552.0 	588.0 	685.0 	714.0 	794.0 	826.0 	Inf 	862.0 	807.0 	329.0 	476.0 	545.0 	Inf 	Inf 	546.0 	682.0 	372.0 	302.0 	60.0  	20.0  	0.0   	16.0  	75.0  	122.0 	134.0 	91.0  	188.0 	214.0 	224.0 	300.0 	682.0 	660.0 	747.0 	588.0 	748.0 	677.0 	595.0 	640.0 	702.0 	Inf 	Inf 	Inf 	620.0 	633.0 	698.0 	717.0 	722.0 	755.0 	843.0 	860.0 	899.0 	912.0 	1003.0	764.0 	743.0
29    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	59.0  	106.0 	118.0 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
30    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	47.0  	59.0  	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
31    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	12.0  	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
32    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
33    	Inf 	Inf 	509.0 	526.0 	403.0 	474.0 	314.0 	375.0 	461.0 	497.0 	594.0 	623.0 	703.0 	735.0 	Inf 	771.0 	716.0 	238.0 	385.0 	454.0 	Inf 	Inf 	455.0 	591.0 	281.0 	211.0 	181.0 	141.0 	214.0 	230.0 	289.0 	336.0 	348.0 	0.0   	97.0  	123.0 	133.0 	209.0 	591.0 	569.0 	656.0 	497.0 	657.0 	586.0 	504.0 	549.0 	611.0 	Inf 	Inf 	Inf 	529.0 	542.0 	607.0 	626.0 	631.0 	664.0 	752.0 	769.0 	808.0 	821.0 	912.0 	673.0 	652.0
34    	Inf 	Inf 	412.0 	429.0 	306.0 	377.0 	217.0 	278.0 	364.0 	400.0 	497.0 	526.0 	606.0 	638.0 	Inf 	674.0 	619.0 	141.0 	288.0 	357.0 	Inf 	Inf 	358.0 	494.0 	184.0 	114.0 	84.0  	44.0  	117.0 	133.0 	192.0 	239.0 	251.0 	65.0  	0.0   	26.0  	36.0  	112.0 	494.0 	472.0 	559.0 	400.0 	560.0 	489.0 	407.0 	452.0 	514.0 	Inf 	Inf 	Inf 	432.0 	445.0 	510.0 	529.0 	534.0 	567.0 	655.0 	672.0 	711.0 	724.0 	815.0 	576.0 	555.0
35    	Inf 	Inf 	386.0 	403.0 	280.0 	351.0 	191.0 	252.0 	338.0 	374.0 	471.0 	500.0 	580.0 	612.0 	Inf 	648.0 	593.0 	115.0 	262.0 	331.0 	Inf 	Inf 	332.0 	468.0 	158.0 	88.0  	58.0  	18.0  	91.0  	107.0 	166.0 	213.0 	225.0 	39.0  	136.0 	0.0   	172.0 	248.0 	468.0 	446.0 	533.0 	374.0 	534.0 	463.0 	381.0 	426.0 	488.0 	Inf 	Inf 	Inf 	406.0 	419.0 	484.0 	503.0 	508.0 	541.0 	629.0 	646.0 	685.0 	698.0 	789.0 	550.0 	529.0
36    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	76.0  	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
37    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
38    	Inf 	Inf 	469.0 	486.0 	363.0 	434.0 	274.0 	335.0 	421.0 	457.0 	554.0 	583.0 	663.0 	695.0 	Inf 	731.0 	676.0 	198.0 	345.0 	414.0 	Inf 	Inf 	415.0 	551.0 	241.0 	171.0 	141.0 	101.0 	174.0 	190.0 	249.0 	296.0 	308.0 	122.0 	219.0 	83.0  	255.0 	331.0 	0.0   	529.0 	616.0 	457.0 	617.0 	546.0 	464.0 	509.0 	571.0 	Inf 	Inf 	Inf 	489.0 	502.0 	567.0 	586.0 	591.0 	624.0 	712.0 	729.0 	768.0 	781.0 	872.0 	633.0 	612.0
39    	Inf 	Inf 	491.0 	508.0 	385.0 	456.0 	296.0 	357.0 	443.0 	479.0 	576.0 	605.0 	685.0 	717.0 	Inf 	753.0 	698.0 	220.0 	367.0 	436.0 	Inf 	Inf 	437.0 	573.0 	263.0 	193.0 	163.0 	123.0 	196.0 	212.0 	271.0 	318.0 	330.0 	144.0 	241.0 	105.0 	277.0 	75.0  	22.0  	0.0   	87.0  	479.0 	88.0  	144.0 	486.0 	531.0 	593.0 	Inf 	Inf 	Inf 	511.0 	524.0 	589.0 	608.0 	613.0 	646.0 	734.0 	751.0 	790.0 	803.0 	894.0 	655.0 	634.0
40    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	1.0   	57.0  	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
41    	Inf 	Inf 	298.0 	315.0 	192.0 	263.0 	266.0 	327.0 	413.0 	449.0 	546.0 	575.0 	655.0 	687.0 	Inf 	723.0 	668.0 	292.0 	174.0 	243.0 	Inf 	Inf 	244.0 	94.0  	198.0 	265.0 	235.0 	195.0 	268.0 	284.0 	343.0 	390.0 	402.0 	216.0 	313.0 	177.0 	349.0 	147.0 	94.0  	72.0  	159.0 	0.0   	160.0 	89.0  	7.0   	52.0  	114.0 	Inf 	Inf 	Inf 	32.0  	45.0  	110.0 	129.0 	134.0 	167.0 	255.0 	272.0 	311.0 	324.0 	415.0 	176.0 	155.0
42    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
43    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
44    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	82.0  	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	25.0  	38.0  	103.0 	122.0 	127.0 	160.0 	248.0 	265.0 	304.0 	317.0 	408.0 	169.0 	148.0
45    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	62.0  	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
46    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
47    	Inf 	Inf 	378.0 	395.0 	272.0 	343.0 	346.0 	407.0 	493.0 	529.0 	626.0 	655.0 	735.0 	767.0 	Inf 	803.0 	748.0 	372.0 	254.0 	323.0 	Inf 	Inf 	38.0  	174.0 	278.0 	345.0 	315.0 	275.0 	348.0 	364.0 	423.0 	470.0 	482.0 	296.0 	393.0 	257.0 	429.0 	227.0 	174.0 	152.0 	239.0 	80.0  	240.0 	169.0 	87.0  	132.0 	194.0 	0.0   	Inf 	Inf 	112.0 	125.0 	190.0 	209.0 	214.0 	247.0 	335.0 	352.0 	391.0 	404.0 	495.0 	256.0 	235.0
48    	Inf 	65.0  	136.0 	153.0 	175.0 	246.0 	249.0 	310.0 	396.0 	432.0 	529.0 	558.0 	638.0 	670.0 	Inf 	706.0 	651.0 	451.0 	320.0 	81.0  	55.0  	93.0  	118.0 	254.0 	344.0 	425.0 	395.0 	355.0 	428.0 	444.0 	503.0 	550.0 	562.0 	376.0 	473.0 	337.0 	509.0 	307.0 	254.0 	232.0 	319.0 	160.0 	320.0 	249.0 	167.0 	212.0 	274.0 	80.0  	0.0   	103.0 	192.0 	205.0 	270.0 	289.0 	294.0 	327.0 	415.0 	432.0 	471.0 	484.0 	575.0 	336.0 	315.0
49    	Inf 	16.0  	87.0  	104.0 	126.0 	197.0 	200.0 	261.0 	347.0 	383.0 	480.0 	509.0 	589.0 	621.0 	Inf 	657.0 	602.0 	402.0 	271.0 	32.0  	6.0   	44.0  	175.0 	311.0 	295.0 	482.0 	452.0 	412.0 	485.0 	501.0 	560.0 	607.0 	619.0 	433.0 	530.0 	394.0 	566.0 	364.0 	311.0 	289.0 	376.0 	217.0 	377.0 	306.0 	224.0 	269.0 	331.0 	137.0 	62.0  	0.0   	249.0 	262.0 	327.0 	346.0 	351.0 	384.0 	472.0 	489.0 	528.0 	541.0 	632.0 	393.0 	372.0
50    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	13.0  	78.0  	97.0  	102.0 	135.0 	223.0 	240.0 	279.0 	292.0 	383.0 	144.0 	123.0
51    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
52    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	19.0  	24.0  	57.0  	145.0 	162.0 	201.0 	214.0 	305.0 	66.0  	45.0
53    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	60.0  	0.0   	5.0   	38.0  	126.0 	143.0 	182.0 	195.0 	286.0 	47.0  	26.0
54    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	93.0  	112.0 	0.0   	33.0  	121.0 	138.0 	177.0 	190.0 	281.0 	56.0  	59.0
55    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	60.0  	79.0  	84.0  	0.0   	88.0  	105.0 	144.0 	157.0 	248.0 	23.0  	26.0
56    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	230.0 	249.0 	254.0 	170.0 	0.0   	17.0  	56.0  	69.0  	160.0 	193.0 	196.0
57    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	213.0 	232.0 	237.0 	153.0 	152.0 	0.0   	39.0  	52.0  	143.0 	176.0 	179.0
58    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	177.0 	196.0 	201.0 	117.0 	116.0 	133.0 	0.0   	16.0  	107.0 	140.0 	143.0
59    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	161.0 	180.0 	185.0 	101.0 	100.0 	117.0 	156.0 	0.0   	91.0  	124.0 	127.0
60    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	70.0  	89.0  	94.0  	10.0  	9.0   	26.0  	65.0  	78.0  	0.0   	33.0  	36.0
61    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	37.0  	56.0  	61.0  	94.0  	182.0 	199.0 	238.0 	251.0 	342.0 	0.0   	3.0
62    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	34.0  	53.0  	58.0  	91.0  	179.0 	196.0 	235.0 	248.0 	339.0 	100.0 	0.0
-------END FULL SUITE----------
-------------End Test: 7 -------------
*/
    //>graph8
    /*
-------------Test: 8 ---Testing file: graph8.txt----------
-------FULL SUITE----------
(5,7)	0	1	2	3	4
0	0	9	75	0	0
1	0	0	95	0	42
2	0	0	0	51	0
3	0	19	0	0	0
4	0	0	0	31	0
DFS:[0][1][2][3][4]
BFS:[0][1][2][4][3]
Strongly Connected:
[4][3][2][1]
[0]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4
0     	0.0   	9.0   	75.0  	82.0  	51.0
1     	Inf 	0.0   	95.0  	73.0  	42.0
2     	Inf 	70.0  	0.0   	51.0  	112.0
3     	Inf 	19.0  	114.0 	0.0   	61.0
4     	Inf 	50.0  	145.0 	31.0  	0.0
-------END FULL SUITE----------
-------------End Test: 8 -------------
*/
    //>graph9
    /*
-------------Test: 9 ---Testing file: graph9.txt----------
-------FULL SUITE----------
(48,49)	0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20	21	22	23	24	25	26	27	28	29	30	31	32	33	34	35	36	37	38	39	40	41	42	43	44	45	46	47
0	0	1	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
1	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
2	0	0	0	1	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
3	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
4	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
5	0	0	0	0	0	0	1	0	0	0	0	0	1	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
6	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
7	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
8	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
9	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
10	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
11	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
12	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
13	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
14	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
15	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
16	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
17	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
18	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
19	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
20	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
21	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
22	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
23	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
24	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0
25	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
26	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0
27	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
28	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0
29	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
30	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0
31	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
32	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
33	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	1	1	0	0	0	0	0
34	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0
35	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	1	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0
36	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0
37	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
38	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0
39	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1
40	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
41	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0
42	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
43	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0
44	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0
45	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
46	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
47	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0
DFS:[0][1][2][3][11][4][5][6][7][15][14][22][23][31][30][38][39][47][46][12][13][10][18][19][25][26][35][27][20][21][28][36][29][37][34][43][44][45][32][24][33][40][41][42][9][8][16][17]
BFS:[0][1][9][2][8][3][10][16][17][11][18][25][4][19][26][32][5][35][24][6][12][13][27][28][34][33][7][20][36][43][40][41][42][15][21][29][37][44][14][45][22][23][31][30][38][39][47][46]
Strongly Connected:
[46]
[47]
[39]
[38]
[30]
[31]
[23]
[22]
[14]
[15]
[7]
[6]
[12]
[13]
[5]
[4]
[11]
[3]
[19]
[21]
[20]
[27]
[29]
[37]
[36]
[28]
[45]
[44]
[43]
[34]
[35]
[26]
[40]
[41]
[42]
[33]
[24]
[32]
[25]
[18]
[10]
[2]
[1]
[16]
[17]
[8]
[9]
[0]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
Distance from: [2] TO: [6]--> 5.0
------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5     	6     	7     	8     	9     	10    	11    	12    	13    	14    	15    	16    	17    	18    	19    	20    	21    	22    	23    	24    	25    	26    	27    	28    	29    	30    	31    	32    	33    	34    	35    	36    	37    	38    	39    	40    	41    	42    	43    	44    	45    	46    	47
0     	0.0   	1.0   	2.0   	3.0   	5.0   	6.0   	7.0   	8.0   	2.0   	1.0   	3.0   	4.0   	7.0   	7.0   	10.0  	9.0   	3.0   	3.0   	4.0   	5.0   	8.0   	9.0   	11.0  	12.0  	6.0   	4.0   	5.0   	7.0   	7.0   	9.0   	14.0  	13.0  	5.0   	7.0   	7.0   	6.0   	8.0   	9.0   	15.0  	16.0  	8.0   	8.0   	8.0   	8.0   	9.0   	10.0  	18.0  	17.0
1     	Inf 	0.0   	1.0   	2.0   	4.0   	5.0   	6.0   	7.0   	Inf 	Inf 	2.0   	3.0   	6.0   	6.0   	9.0   	8.0   	Inf 	Inf 	3.0   	4.0   	8.0   	9.0   	10.0  	11.0  	6.0   	4.0   	5.0   	7.0   	7.0   	9.0   	13.0  	12.0  	5.0   	7.0   	7.0   	6.0   	8.0   	9.0   	14.0  	15.0  	8.0   	8.0   	8.0   	8.0   	9.0   	10.0  	17.0  	16.0
2     	Inf 	Inf 	0.0   	1.0   	3.0   	4.0   	5.0   	6.0   	Inf 	Inf 	1.0   	2.0   	5.0   	5.0   	8.0   	7.0   	Inf 	Inf 	2.0   	3.0   	7.0   	8.0   	9.0   	10.0  	5.0   	3.0   	4.0   	6.0   	6.0   	8.0   	12.0  	11.0  	4.0   	6.0   	6.0   	5.0   	7.0   	8.0   	13.0  	14.0  	7.0   	7.0   	7.0   	7.0   	8.0   	9.0   	16.0  	15.0
3     	Inf 	Inf 	Inf 	0.0   	2.0   	3.0   	4.0   	5.0   	Inf 	Inf 	Inf 	1.0   	4.0   	4.0   	7.0   	6.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	8.0   	9.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	11.0  	10.0  	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	12.0  	13.0  	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	15.0  	14.0
4     	Inf 	Inf 	Inf 	Inf 	0.0   	1.0   	2.0   	3.0   	Inf 	Inf 	Inf 	Inf 	2.0   	2.0   	5.0   	4.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	6.0   	7.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	9.0   	8.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	10.0  	11.0  	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	13.0  	12.0
5     	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	1.0   	2.0   	Inf 	Inf 	Inf 	Inf 	1.0   	1.0   	4.0   	3.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	5.0   	6.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	8.0   	7.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	9.0   	10.0  	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	12.0  	11.0
6     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	1.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	3.0   	2.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	4.0   	5.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	7.0   	6.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	8.0   	9.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	11.0  	10.0
7     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	2.0   	1.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	3.0   	4.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	6.0   	5.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	7.0   	8.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	10.0  	9.0
8     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	1.0   	Inf 	Inf 	6.0   	7.0   	Inf 	Inf 	4.0   	2.0   	3.0   	5.0   	5.0   	7.0   	Inf 	Inf 	3.0   	5.0   	5.0   	4.0   	6.0   	7.0   	Inf 	Inf 	6.0   	6.0   	6.0   	6.0   	7.0   	8.0   	Inf 	Inf
9     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	2.0   	2.0   	Inf 	Inf 	7.0   	8.0   	Inf 	Inf 	5.0   	3.0   	4.0   	6.0   	6.0   	8.0   	Inf 	Inf 	4.0   	6.0   	6.0   	5.0   	7.0   	8.0   	Inf 	Inf 	7.0   	7.0   	7.0   	7.0   	8.0   	9.0   	Inf 	Inf
10    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	2.0   	6.0   	7.0   	Inf 	Inf 	4.0   	2.0   	3.0   	5.0   	5.0   	7.0   	Inf 	Inf 	3.0   	5.0   	5.0   	4.0   	6.0   	7.0   	Inf 	Inf 	6.0   	6.0   	6.0   	6.0   	7.0   	8.0   	Inf 	Inf
11    	Inf 	Inf 	Inf 	Inf 	1.0   	2.0   	3.0   	4.0   	Inf 	Inf 	Inf 	0.0   	3.0   	3.0   	6.0   	5.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	7.0   	8.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	10.0  	9.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	11.0  	12.0  	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	14.0  	13.0
12    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
13    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
14    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	2.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	4.0   	3.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	5.0   	6.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	8.0   	7.0
15    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	2.0   	3.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	5.0   	4.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	6.0   	7.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	9.0   	8.0
16    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	5.0   	6.0   	Inf 	Inf 	3.0   	1.0   	2.0   	4.0   	4.0   	6.0   	Inf 	Inf 	2.0   	4.0   	4.0   	3.0   	5.0   	6.0   	Inf 	Inf 	5.0   	5.0   	5.0   	5.0   	6.0   	7.0   	Inf 	Inf
17    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
18    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	1.0   	5.0   	6.0   	Inf 	Inf 	3.0   	1.0   	2.0   	4.0   	4.0   	6.0   	Inf 	Inf 	2.0   	4.0   	4.0   	3.0   	5.0   	6.0   	Inf 	Inf 	5.0   	5.0   	5.0   	5.0   	6.0   	7.0   	Inf 	Inf
19    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
20    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	1.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
21    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
22    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	1.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	3.0   	2.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	4.0   	5.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	7.0   	6.0
23    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	2.0   	1.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	3.0   	4.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	6.0   	5.0
24    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	3.0   	Inf 	Inf 	Inf 	Inf 	Inf 	2.0   	2.0   	2.0   	4.0   	5.0   	6.0   	Inf 	Inf
25    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	4.0   	5.0   	Inf 	Inf 	2.0   	0.0   	1.0   	3.0   	3.0   	5.0   	Inf 	Inf 	1.0   	3.0   	3.0   	2.0   	4.0   	5.0   	Inf 	Inf 	4.0   	4.0   	4.0   	4.0   	5.0   	6.0   	Inf 	Inf
26    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	3.0   	4.0   	Inf 	Inf 	Inf 	Inf 	0.0   	2.0   	2.0   	4.0   	Inf 	Inf 	Inf 	Inf 	2.0   	1.0   	3.0   	4.0   	Inf 	Inf 	Inf 	Inf 	Inf 	3.0   	4.0   	5.0   	Inf 	Inf
27    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	2.0   	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
28    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	2.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	2.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
29    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
30    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	2.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	4.0   	3.0
31    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	2.0   	3.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	5.0   	4.0
32    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	2.0   	4.0   	Inf 	Inf 	Inf 	Inf 	Inf 	3.0   	3.0   	3.0   	5.0   	6.0   	7.0   	Inf 	Inf
33    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	2.0   	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	1.0   	1.0   	3.0   	4.0   	5.0   	Inf 	Inf
34    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	2.0   	3.0   	Inf 	Inf
35    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	2.0   	3.0   	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	1.0   	3.0   	Inf 	Inf 	Inf 	Inf 	1.0   	0.0   	2.0   	3.0   	Inf 	Inf 	Inf 	Inf 	Inf 	2.0   	3.0   	4.0   	Inf 	Inf
36    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	1.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
37    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
38    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	1.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	3.0   	2.0
39    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	2.0   	1.0
40    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
41    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	2.0   	3.0   	4.0   	Inf 	Inf
42    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf
43    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	1.0   	2.0   	Inf 	Inf
44    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	1.0   	Inf 	Inf
45    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf
46    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf
47    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	1.0   	0.0
-------END FULL SUITE----------
-------------End Test: 9 -------------
*/
    //>graph10
    /*
-------------Test: 10 ---Testing file: graph10.txt----------
-------FULL SUITE----------
(18,32)	0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17
0	0	1	1	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0
1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
2	0	1	0	1	0	0	1	0	0	0	0	0	0	0	0	0	0	0
3	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
4	0	0	0	0	0	0	0	1	1	0	0	0	0	0	0	0	0	0
5	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0
6	0	0	1	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0
7	1	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0
8	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0
9	0	0	0	0	0	1	0	0	0	0	0	0	0	1	0	0	0	0
10	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	0	0	0
11	0	0	0	0	0	0	0	1	0	0	0	0	1	0	0	0	0	0
12	0	0	0	0	0	0	0	0	0	1	0	0	0	1	0	1	1	0
13	0	0	0	0	0	0	0	0	0	1	0	0	1	0	0	0	1	0
14	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0	0	0	0
15	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	0	0
16	0	0	0	0	0	0	0	0	0	0	0	0	1	1	0	0	0	1
17	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
DFS:[0][1][2][3][6][9][5][4][7][8][12][13][16][17][15][14][11][10]
BFS:[0][1][2][4][3][6][7][8][9][12][5][13][15][16][14][17][11][10]
Strongly Connected:
[1]
[17]
[11][14][15][16][13][12][8][7][4][5][9][6][3][2][0]
[10]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
Distance from: [2] TO: [6]--> 1.0
------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5     	6     	7     	8     	9     	10    	11    	12    	13    	14    	15    	16    	17
0     	0.0   	1.0   	1.0   	2.0   	1.0   	4.0   	2.0   	2.0   	2.0   	3.0   	Inf 	6.0   	3.0   	4.0   	5.0   	4.0   	4.0   	5.0
1     	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
2     	6.0   	1.0   	0.0   	1.0   	4.0   	3.0   	1.0   	5.0   	5.0   	2.0   	Inf 	7.0   	4.0   	3.0   	6.0   	5.0   	4.0   	5.0
3     	7.0   	2.0   	1.0   	0.0   	5.0   	4.0   	2.0   	6.0   	6.0   	3.0   	Inf 	8.0   	5.0   	4.0   	7.0   	6.0   	5.0   	6.0
4     	2.0   	3.0   	3.0   	4.0   	0.0   	4.0   	4.0   	1.0   	1.0   	3.0   	Inf 	5.0   	2.0   	3.0   	4.0   	3.0   	3.0   	4.0
5     	3.0   	4.0   	4.0   	5.0   	1.0   	0.0   	5.0   	2.0   	2.0   	4.0   	Inf 	6.0   	3.0   	4.0   	5.0   	4.0   	4.0   	5.0
6     	5.0   	2.0   	1.0   	2.0   	3.0   	2.0   	0.0   	4.0   	4.0   	1.0   	Inf 	6.0   	3.0   	2.0   	5.0   	4.0   	3.0   	4.0
7     	1.0   	2.0   	2.0   	3.0   	1.0   	5.0   	3.0   	0.0   	2.0   	4.0   	Inf 	6.0   	3.0   	4.0   	5.0   	4.0   	4.0   	5.0
8     	6.0   	7.0   	7.0   	8.0   	4.0   	3.0   	8.0   	5.0   	0.0   	2.0   	Inf 	4.0   	1.0   	2.0   	3.0   	2.0   	2.0   	3.0
9     	4.0   	5.0   	5.0   	6.0   	2.0   	1.0   	6.0   	3.0   	3.0   	0.0   	Inf 	5.0   	2.0   	1.0   	4.0   	3.0   	2.0   	3.0
10    	8.0   	3.0   	2.0   	1.0   	6.0   	5.0   	3.0   	7.0   	7.0   	4.0   	0.0   	9.0   	6.0   	5.0   	8.0   	7.0   	6.0   	7.0
11    	2.0   	3.0   	3.0   	4.0   	2.0   	3.0   	4.0   	1.0   	3.0   	2.0   	Inf 	0.0   	1.0   	2.0   	3.0   	2.0   	2.0   	3.0
12    	5.0   	6.0   	6.0   	7.0   	3.0   	2.0   	7.0   	4.0   	4.0   	1.0   	Inf 	3.0   	0.0   	1.0   	2.0   	1.0   	1.0   	2.0
13    	5.0   	6.0   	6.0   	7.0   	3.0   	2.0   	7.0   	4.0   	4.0   	1.0   	Inf 	4.0   	1.0   	0.0   	3.0   	2.0   	1.0   	2.0
14    	3.0   	4.0   	4.0   	5.0   	3.0   	4.0   	5.0   	2.0   	4.0   	3.0   	Inf 	1.0   	2.0   	3.0   	0.0   	3.0   	3.0   	4.0
15    	4.0   	5.0   	5.0   	6.0   	4.0   	5.0   	6.0   	3.0   	5.0   	4.0   	Inf 	2.0   	3.0   	4.0   	1.0   	0.0   	4.0   	5.0
16    	6.0   	7.0   	7.0   	8.0   	4.0   	3.0   	8.0   	5.0   	5.0   	2.0   	Inf 	4.0   	1.0   	1.0   	3.0   	2.0   	0.0   	1.0
17    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
-------------End Test: 10 -------------
*/
    //>graph11
    /*
-------------Test: 11 ---Testing file: graph11.txt----------
-------FULL SUITE----------
(6,10)	0	1	2	3	4	5
0	0	2	0	0	0	4
1	2	0	0	9	0	0
2	0	0	0	5	0	0
3	0	9	5	0	1	0
4	0	0	0	1	0	0
5	4	0	0	0	0	0
DFS:[0][1][3][2][4][5]
BFS:[0][1][5][3][2][4]
Strongly Connected:
[5][4][2][3][1][0]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5
0     	0.0   	2.0   	16.0  	11.0  	12.0  	4.0
1     	2.0   	0.0   	14.0  	9.0   	10.0  	6.0
2     	16.0  	14.0  	0.0   	5.0   	6.0   	20.0
3     	11.0  	9.0   	5.0   	0.0   	1.0   	15.0
4     	12.0  	10.0  	6.0   	1.0   	0.0   	16.0
5     	4.0   	6.0   	20.0  	15.0  	16.0  	0.0
-------END FULL SUITE----------
-------------End Test: 11 -------------
*/
    //>graph12
    /*
-------------Test: 12 ---Testing file: graph12.txt----------
-------FULL SUITE----------
(6,8)	0	1	2	3	4	5
0	0	1	7	0	0	0
1	0	0	0	9	0	15
2	0	0	0	0	4	0
3	0	0	0	0	10	5
4	0	0	0	0	0	3
5	0	0	0	0	0	0
DFS:[0][1][3][4][5][2]
BFS:[0][1][2][3][5][4]
Strongly Connected:
[5]
[4]
[3]
[1]
[2]
[0]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5
0     	0.0   	1.0   	7.0   	10.0  	11.0  	14.0
1     	Inf 	0.0   	Inf 	9.0   	19.0  	14.0
2     	Inf 	Inf 	0.0   	Inf 	4.0   	7.0
3     	Inf 	Inf 	Inf 	0.0   	10.0  	5.0
4     	Inf 	Inf 	Inf 	Inf 	0.0   	3.0
5     	Inf 	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
-------------End Test: 12 -------------
*/
    //>graph13
    /*
-------------Test: 13 ---Testing file: graph13.txt----------
-------FULL SUITE----------
(7,6)	0	1	2	3	4	5	6
0	0	6	0	0	0	0	0
1	0	0	5	0	0	0	0
2	0	0	0	4	0	0	0
3	0	0	0	0	3	0	0
4	0	0	0	0	0	2	0
5	0	0	0	0	0	0	1
6	0	0	0	0	0	0	0
DFS:[0][1][2][3][4][5][6]
BFS:[0][1][2][3][4][5][6]
Strongly Connected:
[6]
[5]
[4]
[3]
[2]
[1]
[0]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
Distance from: [2] TO: [6]--> 10.0
------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5     	6
0     	0.0   	6.0   	11.0  	15.0  	18.0  	20.0  	21.0
1     	Inf 	0.0   	5.0   	9.0   	12.0  	14.0  	15.0
2     	Inf 	Inf 	0.0   	4.0   	7.0   	9.0   	10.0
3     	Inf 	Inf 	Inf 	0.0   	3.0   	5.0   	6.0
4     	Inf 	Inf 	Inf 	Inf 	0.0   	2.0   	3.0
5     	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	1.0
6     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
-------------End Test: 13 -------------
*/
    //>graph14
    /*
-------------Test: 14 ---Testing file: graph14.txt----------
-------FULL SUITE----------
(11,15)	0	1	2	3	4	5	6	7	8	9	10
0	0	16	3	0	0	0	0	0	0	0	0
1	0	0	-32	0	0	0	0	0	0	0	0
2	0	0	0	8	8	0	0	0	0	0	0
3	0	0	0	0	-16	0	0	0	0	0	0
4	0	0	0	0	0	4	9	0	0	0	0
5	0	0	0	0	0	0	-8	0	0	0	0
6	0	0	0	0	0	0	0	2	7	0	0
7	0	0	0	0	0	0	0	0	-4	0	0
8	0	0	0	0	0	0	0	0	0	1	2
9	0	0	0	0	0	0	0	0	0	0	-2
10	0	0	0	0	0	0	0	0	0	0	0
DFS:[0][1][2][3][4][5][6][7][8][9][10]
BFS:[0][1][2][3][4][5][6][7][8][9][10]
Strongly Connected:
[10]
[9]
[8]
[7]
[6]
[5]
[4]
[3]
[2]
[1]
[0]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
Distance from: [2] TO: [6]--> -12.0
------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5     	6     	7     	8     	9     	10
0     	0.0   	16.0  	-16.0 	-8.0  	-24.0 	-20.0 	-28.0 	-26.0 	-30.0 	-29.0 	-31.0
1     	Inf 	0.0   	-32.0 	-24.0 	-40.0 	-36.0 	-44.0 	-42.0 	-46.0 	-45.0 	-47.0
2     	Inf 	Inf 	0.0   	8.0   	-8.0  	-4.0  	-12.0 	-10.0 	-14.0 	-13.0 	-15.0
3     	Inf 	Inf 	Inf 	0.0   	-16.0 	-12.0 	-20.0 	-18.0 	-22.0 	-21.0 	-23.0
4     	Inf 	Inf 	Inf 	Inf 	0.0   	4.0   	-4.0  	-2.0  	-6.0  	-5.0  	-7.0
5     	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	-8.0  	-6.0  	-10.0 	-9.0  	-11.0
6     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	2.0   	-2.0  	-1.0  	-3.0
7     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	-4.0  	-3.0  	-5.0
8     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	1.0   	-1.0
9     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	-2.0
10    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
-------------End Test: 14 -------------
*/
    //>graph15
    /*
-------------Test: 15 ---Testing file: graph15.txt----------
-------FULL SUITE----------
(5,5)	0	1	2	3	4
0	0	1	10	0	0
1	0	0	0	2	0
2	0	0	0	0	0
3	0	0	-10	0	3
4	0	0	0	0	0
DFS:[0][1][3][2][4]
BFS:[0][1][2][3][4]
Strongly Connected:
[2]
[4]
[3]
[1]
[0]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4
0     	0.0   	1.0   	-7.0  	3.0   	6.0
1     	Inf 	0.0   	-8.0  	2.0   	5.0
2     	Inf 	Inf 	0.0   	Inf 	Inf
3     	Inf 	Inf 	-10.0 	0.0   	3.0
4     	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
-------------End Test: 15 -------------
*/
    //>graph16
    /*
-------------Test: 16 ---Testing file: graph16.txt----------
-------FULL SUITE----------
(11,12)	0	1	2	3	4	5	6	7	8	9	10
0	0	16	3	0	0	0	0	0	0	0	0
1	0	0	-32	0	0	0	0	0	0	0	0
2	0	0	0	8	8	0	0	0	0	0	0
3	0	0	0	0	-16	0	0	0	0	0	0
4	0	0	0	0	0	4	0	0	0	0	0
5	0	0	0	0	0	0	0	0	0	0	0
6	0	0	0	0	0	0	0	2	0	0	0
7	0	0	0	0	0	0	0	0	-4	0	0
8	0	0	0	0	0	0	0	0	0	1	2
9	0	0	0	0	0	0	0	0	0	0	-2
10	0	0	0	0	0	0	0	0	0	0	0
DFS:[0][1][2][3][4][5][6][7][8][9][10]
BFS:[0][1][2][3][4][5][6][7][8][9][10]
Strongly Connected:
[5]
[4]
[3]
[2]
[1]
[0]
[10]
[9]
[8]
[7]
[6]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
Distance from: [2] TO: [6]--> Infinity
------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5     	6     	7     	8     	9     	10
0     	0.0   	16.0  	-16.0 	-8.0  	-24.0 	-20.0 	Inf 	Inf 	Inf 	Inf 	Inf
1     	Inf 	0.0   	-32.0 	-24.0 	-40.0 	-36.0 	Inf 	Inf 	Inf 	Inf 	Inf
2     	Inf 	Inf 	0.0   	8.0   	-8.0  	-4.0  	Inf 	Inf 	Inf 	Inf 	Inf
3     	Inf 	Inf 	Inf 	0.0   	-16.0 	-12.0 	Inf 	Inf 	Inf 	Inf 	Inf
4     	Inf 	Inf 	Inf 	Inf 	0.0   	4.0   	Inf 	Inf 	Inf 	Inf 	Inf
5     	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf
6     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	2.0   	-2.0  	-1.0  	-3.0
7     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	-4.0  	-3.0  	-5.0
8     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	1.0   	-1.0
9     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	-2.0
10    	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
-------------End Test: 16 -------------
*/
    //>graph17
    /*
-------------Test: 17 ---Testing file: graph17.txt----------
-------FULL SUITE----------
(12,14)	0	1	2	3	4	5	6	7	8	9	10	11
0	0	16	0	0	0	0	0	0	0	0	3	0
1	0	0	0	0	0	0	0	0	0	0	0	0
2	0	0	0	0	0	0	0	-3	0	0	0	0
3	0	0	0	0	4	0	0	0	0	0	0	0
4	0	0	0	0	0	0	0	0	0	0	0	0
5	0	0	1	0	0	0	2	0	0	0	0	0
6	0	0	0	0	0	0	0	-4	0	0	0	0
7	0	0	0	0	0	0	0	0	1	2	0	0
8	0	0	0	0	0	0	0	0	0	-2	0	0
9	0	0	0	0	0	0	0	0	0	0	0	0
10	0	-32	0	8	0	0	0	0	0	0	0	8
11	0	0	0	-16	0	0	0	0	0	0	0	0
DFS:[0][1][10][3][4][11][2][7][8][9][5][6]
BFS:[0][1][10][3][11][4][2][7][8][9][5][6]
Strongly Connected:
[1]
[4]
[3]
[11]
[10]
[0]
[9]
[8]
[7]
[2]
[6]
[5]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
Distance from: [2] TO: [6]--> Infinity
------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5     	6     	7     	8     	9     	10    	11
0     	0.0   	-29.0 	Inf 	-5.0  	-1.0  	Inf 	Inf 	Inf 	Inf 	Inf 	3.0   	11.0
1     	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
2     	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	-3.0  	-2.0  	-4.0  	Inf 	Inf
3     	Inf 	Inf 	Inf 	0.0   	4.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
4     	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
5     	Inf 	Inf 	1.0   	Inf 	Inf 	0.0   	2.0   	-2.0  	-1.0  	-3.0  	Inf 	Inf
6     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	-4.0  	-3.0  	-5.0  	Inf 	Inf
7     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	1.0   	-1.0  	Inf 	Inf
8     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	-2.0  	Inf 	Inf
9     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf
10    	Inf 	-32.0 	Inf 	-8.0  	-4.0  	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	8.0
11    	Inf 	Inf 	Inf 	-16.0 	-12.0 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
-------------End Test: 17 -------------
*/
    //>graph18
    /*
-------------Test: 18 ---Testing file: graph18.txt----------
-------FULL SUITE----------
(5,5)	0	1	2	3	4
0	0	-23	0	0	-99
1	0	0	10	0	0
2	0	12	0	-2	0
3	0	0	0	0	0
4	0	0	0	0	0
DFS:[0][1][2][3][4]
BFS:[0][1][4][2][3]
Strongly Connected:
[3]
[2][1]
[4]
[0]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
start not found in graph/end not found in graph

------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4
0     	0.0   	-23.0 	-13.0 	-15.0 	-99.0
1     	Inf 	0.0   	10.0  	8.0   	Inf
2     	Inf 	12.0  	0.0   	-2.0  	Inf
3     	Inf 	Inf 	Inf 	0.0   	Inf
4     	Inf 	Inf 	Inf 	Inf 	0.0
-------END FULL SUITE----------
-------------End Test: 18 -------------
*/
    //>graph19
    /*
-------------Test: 19 ---Testing file: graph19.txt----------
-------FULL SUITE----------
(7,2)	0	1	2	3	4	5	6
0	0	0	0	0	0	0	0
1	0	0	0	0	0	0	0
2	0	0	0	0	0	0	0
3	0	0	0	0	0	0	0
4	0	0	0	0	0	0	0
5	0	0	0	0	4	0	0
6	0	0	0	0	0	1	0
DFS:[0][1][2][3][4][5][6]
BFS:[0][1][2][3][4][5][6]
Strongly Connected:
[0]
[1]
[2]
[3]
[4]
[5]
[6]

Cycle not found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
Distance from: [2] TO: [6]--> Infinity
------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5     	6
0     	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
1     	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf
2     	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf
3     	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf
4     	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf
5     	Inf 	Inf 	Inf 	Inf 	4.0   	0.0   	Inf
6     	Inf 	Inf 	Inf 	Inf 	5.0   	1.0   	0.0
-------END FULL SUITE----------
-------------End Test: 19 -------------
*/
    //>graph20
    /*
-------------Test: 20 ---Testing file: graph20.txt----------
-------FULL SUITE----------
(12,14)	0	1	2	3	4	5	6	7	8	9	10	11
0	0	16	0	0	0	0	0	0	0	0	3	0
1	0	0	0	0	0	0	0	0	0	0	0	0
2	0	0	0	0	0	0	0	-3	0	0	0	0
3	0	0	0	0	4	0	0	0	0	0	8	0
4	0	0	0	0	0	0	0	0	0	0	0	0
5	0	0	1	0	0	0	2	0	0	0	0	0
6	0	0	0	0	0	0	0	-4	0	0	0	0
7	0	0	0	0	0	0	0	0	1	2	0	0
8	0	0	0	0	0	0	0	0	0	-2	0	0
9	0	0	0	0	0	0	0	0	0	0	0	0
10	0	-32	0	0	0	0	0	0	0	0	0	8
11	0	0	0	-16	0	0	0	0	0	0	0	0
DFS:[0][1][10][11][3][4][2][7][8][9][5][6]
BFS:[0][1][10][11][3][4][2][7][8][9][5][6]
Strongly Connected:
[1]
[4]
[3][11][10]
[0]
[9]
[8]
[7]
[2]
[6]
[5]

Cycle found

------PRINTING SHORTEST DISTANCE FROM: [2] TO: [6]--------
Distance from: [2] TO: [6]--> Infinity
------PRINTING SHORTEST DISTANCE MATRIX--------
		0     	1     	2     	3     	4     	5     	6     	7     	8     	9     	10    	11
0     	0.0   	-29.0 	Inf 	-5.0  	-1.0  	Inf 	Inf 	Inf 	Inf 	Inf 	3.0   	11.0
1     	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
2     	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	-3.0  	-2.0  	-4.0  	Inf 	Inf
3     	Inf 	-24.0 	Inf 	0.0   	4.0   	Inf 	Inf 	Inf 	Inf 	Inf 	8.0   	16.0
4     	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf
5     	Inf 	Inf 	1.0   	Inf 	Inf 	0.0   	2.0   	-2.0  	-1.0  	-3.0  	Inf 	Inf
6     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	-4.0  	-3.0  	-5.0  	Inf 	Inf
7     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	1.0   	-1.0  	Inf 	Inf
8     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	-2.0  	Inf 	Inf
9     	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	Inf 	Inf
10    	Inf 	-32.0 	Inf 	-8.0  	-4.0  	Inf 	Inf 	Inf 	Inf 	Inf 	0.0   	8.0
11    	Inf 	-40.0 	Inf 	-16.0 	-12.0 	Inf 	Inf 	Inf 	Inf 	Inf 	-8.0  	0.0
-------END FULL SUITE----------
-------------End Test: 20 -------------

     */
}