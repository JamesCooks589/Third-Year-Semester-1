import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {
    private String[] vertices;
    private Integer[][] adjacencyMatrix;
    private int numVertices;
    private int numEdges;

    public Graph(String fileName) {
        //If passed in file name is an empty string, create an empty graph
        if (fileName == ""){
            vertices = new String[0];
            adjacencyMatrix = new Integer[0][0];
            numVertices = 0;
            numEdges = 0;
        }
        //Else read the file and initialize the graph
        else{
            try {
                //Read the file
                File file = new File(fileName);
                Scanner reader = new Scanner(file);
                //Read the first line as that is the number of vertices
                numVertices = Integer.parseInt(reader.nextLine());
                //Initialize the vertices array
                vertices = new String[numVertices];
                adjacencyMatrix = new Integer[numVertices][numVertices];
                //The next line contains the names of the vertices space seperated list
                String[] vertexNames = reader.nextLine().split(" ");
                //Add the names to the vertices array
                for (int i = 0; i < numVertices; i++){
                    vertices[i] = vertexNames[i];
                }
                //The rest of the lines contain the adjacency matrix space seperated
                for (int i = 0; i < numVertices; i++){
                    String[] line = reader.nextLine().split(" ");
                    for (int j = 0; j < numVertices; j++){
                        adjacencyMatrix[i][j] = Integer.parseInt(line[j]);
                        //if [i][j] is not 0 then there is an edge
                        if (adjacencyMatrix[i][j] != 0){
                            numEdges++;
                        }
                    }
                }
            reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        
    }

    public void insertVertex(String name) {
        //Add the name to the vertices array
        String[] newVertices = new String[numVertices + 1];
        for (int i = 0; i < numVertices; i++){
            newVertices[i] = vertices[i];
        }
        newVertices[numVertices] = name;
        //Update adjacency matrix assuming no edges
        Integer[][] newAdjacencyMatrix = new Integer[numVertices + 1][numVertices + 1];
        for (int i = 0; i < numVertices + 1; i++){
            for (int j = 0; j < numVertices + 1; j++){
                newAdjacencyMatrix[i][j] = 0;
            }
        }
        //Copy over the old adjacency matrix
        for (int i = 0; i < numVertices; i++){
            for (int j = 0; j < numVertices; j++){
                newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
            }
        }
        //Update the member variables
        vertices = newVertices;
        adjacencyMatrix = newAdjacencyMatrix;
        numVertices++;
        
    }

    public void insertEdge(String start, String end, int weight) {
        if (weight == 0){
            return;
        }
        //If start or end are not in the graph, return
        if (getVertexIndex(start) == -1 || getVertexIndex(end) == -1){
            return;
        }
        //Add edge to adjacency matrix if there is already an edge just update the weight
        if (adjacencyMatrix[getVertexIndex(start)][getVertexIndex(end)] == 0){
            numEdges++;
        }
        adjacencyMatrix[getVertexIndex(start)][getVertexIndex(end)] = weight;
    }

    public void removeVertex(String name) {
        //If the vertex is not in the graph, return
        if (getVertexIndex(name) == -1){
            return;
        }
        //Remove the vertex from the vertices array
        String[] newVertices = new String[numVertices - 1];
        //Get the index of the vertex being removed
        int indexOfVertex = getVertexIndex(name);

        //Count number of edges for the vertex being removed
        //And number of edges to the vertex being removed
        int edgesToVertex = CountEdgesToVertex(indexOfVertex);
        int edgesFromVertex = CountEdgesFromVertex(indexOfVertex);

        int index = 0;
        //Update the vertices array
        for (int i = 0; i < numVertices; i++){
            if (!vertices[i].equals(name)){
                newVertices[index] = vertices[i];
                index++;
            }
        }
        //Update adjacency matrix
        Integer[][] newAdjacencyMatrix = new Integer[numVertices - 1][numVertices - 1];
        for (int i = 0; i < numVertices - 1; i++){
            for (int j = 0; j < numVertices - 1; j++){
                if (i < indexOfVertex && j < indexOfVertex){
                    newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
                }
                else if (i < indexOfVertex && j >= indexOfVertex){
                    newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j + 1];
                }
                else if (i >= indexOfVertex && j < indexOfVertex){
                    newAdjacencyMatrix[i][j] = adjacencyMatrix[i + 1][j];
                }
                else if (i >= indexOfVertex && j >= indexOfVertex){
                    newAdjacencyMatrix[i][j] = adjacencyMatrix[i + 1][j + 1];
                }
            }
        }
        //Update member variables
        vertices = newVertices;
        adjacencyMatrix = newAdjacencyMatrix;
        numVertices--;
        numEdges -= edgesFromVertex;

    }

    public void removeEdge(String start, String end) {
        //If start or end are not in the graph, return
        if (getVertexIndex(start) == -1 || getVertexIndex(end) == -1){
            return;
        }
        //Set the edge to 0
        adjacencyMatrix[getVertexIndex(start)][getVertexIndex(end)] = 0;

        //Update member variables
        numEdges--;
    }

    @Override
    public String toString() {
        //If the graph is empty, return "(0,0)"
        if (numVertices == 0){
            return "(0,0)";
        }
        //Format specified in spec
        //(numVertices, numEdges) \t vertex1 \t vertex2 \t ... \t vertexN
        //vertex1 \t weight 1-1 \t weight 1-2 \t ... \t weight 1-N
        //...
        //vertexN \t weight N-1 \t weight N-2 \t ... \t weight N-N
        String output = "(" + numVertices + "," + numEdges + ")\t";
        for (int i = 0; i < numVertices; i++){
            if(i == numVertices - 1){
                output += vertices[i] + "\n";
            }
            else{
                output += vertices[i] + "\t";
            }
        }
        for (int i = 0; i < numVertices; i++){
            output += vertices[i] + "\t";
            for (int j = 0; j < numVertices; j++){
                if (j == numVertices - 1){
                    output += adjacencyMatrix[i][j] + "\n";
                }
                else{
                    output += adjacencyMatrix[i][j] + "\t";
                }
            }
        }
        return output;
    }

    public String depthFirstTraversal() {
        //If the graph is empty, return ""
        if (numVertices == 0){
            return "";
        }
        Boolean[] visited = new Boolean[numVertices];
        for(int i = 0; i < numVertices; i++){
            //Set all vertices to unvisited
            visited[i] = false;
        }
        String output = "";
        
        //While there is a vertex v that is unvisited
        while (unvisitedVertex(visited) != -1){
            //Call depthFirstTraversalHelper on v
            output += depthFirstTraversalHelper(unvisitedVertex(visited), visited);
        }
        return output;
    }

    public String breadthFirstTraversal() {
       //If graph is empty, return ""
         if (numVertices == 0){
              return "";
         }
         String out = "";
         boolean [] visited = new boolean[numVertices];
         //Set all to unvisited
            for (int i = 0; i < numVertices; i++){
                visited[i] = false;
            }
        for (int i = 0; i < numVertices; i++){
            if (!visited[i]){
                out += breadthFirstTraversalHelper(i, visited);
            }
        }
        return out;


    }

    public Double[][] shortestPaths() {
        //Using the All to All Shortest Paths algorithm
        //If graph is empty, return 0x0 array
        if (numVertices == 0){
            return new Double[0][0];
        }
        //Initialize the array with Double.POSITIVE_INFINITY
        Double[][] output = new Double[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++){
            for (int j = 0; j < numVertices; j++){
                output[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        //For all vertices v
        for (int v = 0; v < numVertices; v++){
            output[v][v] = 0.0;
        }
        //For each edge (u,v) with weight w
        for (int u = 0; u < numVertices; u++){
            for (int v = 0; v < numVertices; v++){
                if (adjacencyMatrix[u][v] != 0){
                    output[u][v] = adjacencyMatrix[u][v].doubleValue();
                }
            }
        }

        //For i = 1 to n
        for (int i = 0; i < numVertices; i++){
            //For j = 1 to n
            for (int j = 0; j < numVertices; j++){
                //For k = 1 to n
                for (int k = 0; k < numVertices; k++){
                    if (output[j][k] > output[j][i] + output[i][k]){
                        output[j][k] = output[j][i] + output[i][k];
                    }
                }
            }
        }
        return output;
    }

    public Double shortestPath(String start, String end) {
        //If start or end are not in the graph, return null
        if (getVertexIndex(start) == -1 || getVertexIndex(end) == -1){
            return null;
        }
        //Call shortestPaths
        Double[][] output = shortestPaths();
        //Return the value at the index of start and end
        return output[getVertexIndex(start)][getVertexIndex(end)];

    }

    public boolean cycleDetection() {
        //if the graph is empty, return false
        if (numVertices == 0){
            return false;
        }
        //Set all vertices to unvisited
        Boolean[] visited = new Boolean[numVertices];
        for(int i = 0; i < numVertices; i++){
            visited[i] = false;
        }
        //While there is a vertex v that is unvisited
        while (unvisitedVertex(visited) != -1){
            //Call cycleDetectionHelper on v
            if (cycleDetectionHelper(unvisitedVertex(visited), visited)){
                return true;
            }
        }
        return false;
    }

    public String stronglyConnectedComponents() {
        //If the graph is empty, return ""
        String output = "";
        if (numVertices == 0){
            return "";
        }
        //Set all vertices num = 0
        int[] num = new int[numVertices];
        for (int i = 0; i < numVertices; i++){
            num[i] = 0;
        }
        int i = 1;
        //While there is a vertex v that has num = 0
        while(unvisitedVertex(num) != -1){
            //Call stronglyConnectedComponentsHelper on v
            output += stronglyConnectedComponentsHelper(unvisitedVertex(num), num, i);
        }

        return output;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Helper Methods

    //Helper method for breadthFirstTraversal
    private String breadthFirstTraversalHelper(int index, boolean[] visited){
        String out = "";
        enqueue(index);
        visited[index] = true;
        while (queue != null){
            int v = dequeue();
            out += "[" + vertices[v] + "]";
            for (int i = 0; i < numVertices; i++){
                if (adjacencyMatrix[v][i] != 0 && !visited[i]){
                    enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return out;
    }
        
    //getVertices helper method
    public String[] getVertices(){
        return vertices;
    }

    public int getVertexIndex(String name){
        for (int i = 0; i < numVertices; i++){
            if (vertices[i].equals(name)){
                return i;
            }
        }
        return -1;
    }

    //Count number of edges to a vertex
    private int CountEdgesToVertex(int index){
        int edges = 0;
        for (int i = 0; i < numVertices; i++){
            if (adjacencyMatrix[i][index] != 0){
                edges++;
            }
        }
        return edges;
    }

    //Count number of edges from a vertex
    private int CountEdgesFromVertex(int index){
        int edges = 0;
        for (int i = 0; i < numVertices; i++){
            if (adjacencyMatrix[index][i] != 0){
                edges++;
            }
        }
        return edges;
    }

    //Depth first traversal helper method
    private String depthFirstTraversalHelper(int index, Boolean[] visited){
        String output = "";
        visited[index] = true;
        output += "[" + vertices[index] + "]";
        for (int i = 0; i < numVertices; i++){
            if (adjacencyMatrix[index][i] != 0 && !visited[i]){
                output += depthFirstTraversalHelper(i, visited);
            }
        }
        return output;
    }

    //Unvisited vertex helper method
    private int unvisitedVertex(Boolean[] visited){
        for (int i = 0; i < numVertices; i++){
            if (!visited[i]){
                return i;
            }
        }
        return -1;
    }


    //Queue functionality for BFT
    private int[] queue;

    private void enqueue(int index){
        if (queue == null){
            queue = new int[1];
            queue[0] = index;
        }
        else{
            int[] tempQueue = new int[queue.length + 1];
            for (int i = 0; i < queue.length; i++){
                tempQueue[i] = queue[i];
            }
            tempQueue[queue.length] = index;
            queue = tempQueue;
        }
    }

    private int dequeue(){
        if (queue.length == 1){
            int out = queue[0];
            queue = null;
            return out;
        }
        else
        {
            int[] tempQueue = new int[queue.length - 1];
            int out = queue[0];
            for (int i = 1; i < queue.length; i++){
                tempQueue[i - 1] = queue[i];
            }
            queue = tempQueue;
            return out;
        }
    }

    //Unvisited vertex helper method
    private int unvisitedVertex(boolean[] visited){
        for (int i = 0; i < numVertices; i++){
            if (!visited[i]){
                return i;
            }
        }
        return -1;
    }

    //Cycle detection helper method
    private boolean cycleDetectionHelper(int index, Boolean[] visited){
        visited[index] = true;
        for (int i = 0; i < numVertices; i++){
            if (adjacencyMatrix[index][i] != 0){
                if (visited[i]){
                    return true;
                }
                else{
                    return cycleDetectionHelper(i, visited);
                }
            }
        }
        return false;
    }

    //Unvisited vertex helper method
    private int unvisitedVertex(int[] num){
        for (int i = 0; i < numVertices; i++){
            if (num[i] == 0){
                return i;
            }
        }
        return -1;
    }

    //Strongly connected components helper method
    private String stronglyConnectedComponentsHelper(int index, int[] num, int i){
        String output = "";
        int[] pred = new int[numVertices];
        pred[index] = num[index] = i++;
        push(index);
        //for all vertices u adjacent to v
        for (int u = 0; u < numVertices; u++){
            if (adjacencyMatrix[index][u] != 0){
                if (num[u] == 0){
                    stronglyConnectedComponentsHelper(u, num, i);
                    pred[index] = Math.min(pred[index], pred[u]);
                }
                else if(num[u] < num[index] && stackContains(u)){
                    pred[index] = Math.min(pred[index], num[u]);
                }
            }
        }
        if (pred[index] == num[index]){
            int u = pop();
            output += "[" + vertices[u] + "]";
            while (u != index){
                u = pop();
                output += "[" + vertices[u] + "]";
            }
            output += "\n";
        }
        return output;
    }

    //Stack functionality for strongly connected components
    private int[] stack;

    private void push(int index){
        if (stack == null){
            stack = new int[1];
            stack[0] = index;
        }
        else{
            int[] tempStack = new int[stack.length + 1];
            for (int i = 0; i < stack.length; i++){
                tempStack[i] = stack[i];
            }
            tempStack[stack.length] = index;
            stack = tempStack;
        }
    }

    private int pop(){
        if (stack.length == 1){
            int out = stack[0];
            stack = null;
            return out;
        }
        else
        {
            int[] tempStack = new int[stack.length - 1];
            int out = stack[stack.length - 1];
            for (int i = 0; i < stack.length - 1; i++){
                tempStack[i] = stack[i];
            }
            stack = tempStack;
            return out;
        }
    }

    private boolean stackContains(int index){
        for (int i = 0; i < stack.length; i++){
            if (stack[i] == index){
                return true;
            }
        }
        return false;
    }


}