public class Board {
    private int numRows, numCols;
    private Cell cells[], rows[], cols[], blocks[];

    public String toString() {
        String res = "";
        for (int r = 0; r < numRows * numCols; r++) {
            if (r % numRows == 0) {
                res += horizLine() + "\n";
            }
            for (int c = 0; c < numRows * numCols; c++) {
                if (c % numCols == 0) {
                    res += "|";
                }
                res += cells[r * numRows * numCols + c];
            }
            res += "|\n";
        }

        res += horizLine();
        return res;
    }

    public String horizLine() {
        String res = "";
        for (int i = 0; i < numRows + 1 + (numRows * numCols * (String.valueOf(numRows * numCols).length() + 2)); i++) {
            res += "-";
        }
        return res;
    }

    public void testLinks() {
        System.out.println("Rows forward");

        for (int r = 0; r < numRows * numCols; r++) {
            System.out.print("Row " + r + "\t");
            Cell ptr = rows[r];
            while (ptr != null) {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.right;
            }
            System.out.println();
        }

        System.out.println("Cols forward");

        for (int c = 0; c < numRows * numCols; c++) {
            System.out.print("Col " + c + "\t");
            Cell ptr = cols[c];
            while (ptr != null) {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.below;
            }
            System.out.println();
        }

        System.out.println("Blocks");
        for (int b = 0; b < numRows * numCols; b++) {
            System.out.print("Block " + b + "\t");
            Cell ptr = blocks[b];
            while (ptr != null) {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.block;
            }
            System.out.println();
        }
    }

    public void testCells() {
        System.out.println("Cell\tCoord\tBlock\ttoString");
        for (Cell c : cells) {
            System.out.println(indexOf(c) + "\t(" + c.r + "," + c.c + ")\t" + c.b + "\t" + c);
        }
    }

    public int indexOf(Cell c) {
        for (int i = 0; i < numRows * numRows * numCols * numCols; i++) {
            if (cells[i].equals(c)) {
                return i;
            }
        }
        return -1;
    }

    public Cell cellAt(int r, int c) {
        if (r < 0 || r >= numRows * numCols || c < 0 || c >= numRows * numCols) {
            return null;
        }
        return cells[r * numRows * numCols + c];
    }

    /*
     * Don't change anything above this line
     */

    public Board(int r, int c, String boardString) {
        numRows = r;
        numCols = c;
        int blokkie = numRows * numCols;
        cells = new Cell[blokkie * blokkie];
        // Create cells from space separated string
        //Make an array of all the values
        String[] input = boardString.split(" "); 
        //Populate the cells array
        for (int i = 0; i < input.length; i++) {
            cells[i] = new Cell(numRows, numCols , input[i]);
        }
        
        this.setLinks();
    }

    public void setLinks() {
        int blokkie = numRows * numCols;
        rows = new Cell[blokkie];
        cols = new Cell[blokkie];
        blocks = new Cell[blokkie];
        //Set r and c for each cell
        for (int i = 0; i < cells.length; i++) {
            cells[i].r = i / (blokkie);
            cells[i].c = i % (blokkie);
        }
        //Set blocks array
        int counter = 0;
        for (int outer = 0; outer < blokkie; outer += numRows) {
            for (int inner = 0; inner < blokkie; inner += numCols) {
                blocks[counter] = cellAt(outer, inner);
                blocks[counter].b = counter++;
            }
        }
        
        //Set the links for rows using cellAt
        for (int i = 0; i < rows.length; i++) {
            rows[i] = cellAt(i, 0);
            Cell ptr = rows[i];
            for (int j = 1; j < blokkie; j++) {
                ptr.right = cellAt(i, j);
                ptr = ptr.right;
            }
        }
        //Set the links for cols using cellAt
        for (int i = 0; i < cols.length; i++) {
            cols[i] = cellAt(0, i);
            Cell ptr = cols[i];
            for (int j = 1; j < blokkie; j++) {
                ptr.below = cellAt(j, i);
                ptr = ptr.below;
            }
        }

        //Set the links for blocks using cellAt
        for (int i = 0; i < blocks.length; i++) {
            Cell ptr = blocks[i];
            int row = ptr.r;
            int col = ptr.c;
            for (int j = 0; j < numRows; j++) {
                for (int k = 0; k < numCols; k++) {
                    if (j == ptr.r && k == ptr.c) {
                        k++;
                    }
                    ptr.block = cellAt(row + j, col + k);
                    ptr = ptr.block;
                }
            }
        }
        

        

        //set b for each cell
        Cell setB = null;
        for (int i = 0; i < blocks.length; i++){
            setB = blocks[i];
            while (setB != null) {
                setB.b = i;
                setB = setB.block;
            }
        }


    }

    public void fullProp() {
        Cell cell = null;
        for (int i = 0; i < cells.length; i++) {
            cell = cells[i];
            propCell(cell);
        }
    }

    public void propCell(Cell cell) {
        if (cell.value == null)
        return;

        Cell ptrRows = rows[cell.r];
        Cell ptrCols = cols[cell.c];
        Cell ptrBlocks = blocks[cell.b];

        //Go through all the cells in the same row
        while (ptrRows != null) {
            ptrRows.removeVal(cell.value);
            ptrRows = ptrRows.right;
        }
        //Go through all the cells in the same column
        while (ptrCols != null) {
            ptrCols.removeVal(cell.value);
            ptrCols = ptrCols.below;
        }
        //Go through all the cells in the same block
        while (ptrBlocks != null) {
            ptrBlocks.removeVal(cell.value);
            ptrBlocks = ptrBlocks.block;
        }

    }

    public void solve() {
        int Counter = 0;
        while (soleCandidate () || uniqueCandidate () || duplicateCells ()){
            Counter++;
        }
        System.out.println("Number of moves: " + Counter);
    }

    public boolean soleCandidate() {
        for (int c = 0; c < cells.length; c++) {
            if (cells[c].value == null && cells[c].possibleValues.length == 1) {
                int answer = cells[c].possibleValues.head.data;
                cells[c].setVal(answer);
                propCell(cells[c]);
                return true;
            }
        }
        return false;
    }

    public boolean uniqueCandidate() {
        //Check rows first
        for (int row = 0; row < rows.length; row++){
            int counts[] = new int[numRows * numCols];
            Cell rowPtr = rows[row];
            while (rowPtr != null) {
                if (rowPtr.possibleValues != null) {
                   Node<Integer> nodePtr = rowPtr.possibleValues.head; 
                    while (nodePtr != null) {
                        counts[nodePtr.data - 1]++;
                        nodePtr = nodePtr.next;
                    }
                }
                rowPtr = rowPtr.right;
            }
            for (int i=0; i < numRows * numCols; i++){
                if (counts[i] == 1) {
                    rowPtr = rows[row];
                    while (rowPtr != null) {
                        if (rowPtr.possibleValues != null && rowPtr.possibleValues.contains(i + 1)) {
                           rowPtr.setVal(i+1);
                           propCell(rowPtr); 
                        }
                        rowPtr = rowPtr.right;
                    }
                    return true;
                }
            }
        }
        //Check columns
        for (int col = 0; col < cols.length; col++){
            int counts[] = new int[numRows * numCols];
            Cell colPtr = cols[col];
            while (colPtr != null) {
                if (colPtr.possibleValues != null) {
                   Node<Integer> nodePtr = colPtr.possibleValues.head; 
                    while (nodePtr != null) {
                        counts[nodePtr.data - 1]++;
                        nodePtr = nodePtr.next;
                    }
                }
                colPtr = colPtr.below;
            }
            for (int i=0; i < numRows * numCols; i++){
                if (counts[i] == 1) {
                    colPtr = cols[col];
                    while (colPtr != null) {
                        if (colPtr.possibleValues != null && colPtr.possibleValues.contains(i + 1)) {
                           colPtr.setVal(i+1);
                           propCell(colPtr); 
                        }
                        colPtr = colPtr.below;
                    }
                    return true;
                }
            }
        }
        //Check blocks
        for (int block = 0; block < blocks.length; block++){
            int counts[] = new int[numRows * numCols];
            Cell blockPtr = blocks[block];
            while (blockPtr != null) {
                if (blockPtr.possibleValues != null) {
                   Node<Integer> nodePtr = blockPtr.possibleValues.head; 
                    while (nodePtr != null) {
                        counts[nodePtr.data - 1]++;
                        nodePtr = nodePtr.next;
                    }
                }
                blockPtr = blockPtr.block;
            }
            for (int i=0; i < numRows * numCols; i++){
                if (counts[i] == 1) {
                    blockPtr = blocks[block];
                    while (blockPtr != null) {
                        if (blockPtr.possibleValues != null && blockPtr.possibleValues.contains(i + 1)) {
                           blockPtr.setVal(i+1);
                           propCell(blockPtr); 
                        }
                        blockPtr = blockPtr.block;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean duplicateCells() {
        //Check rows first
        for (int row = 0; row < rows.length; row++) {
            Cell rowPtr = rows[row];
            while (rowPtr != null) {
                  if (rowPtr.possibleValues != null && rowPtr.possibleValues.length == 2) {
                    Cell secondPtr = rowPtr.right;
                    while (secondPtr != null) {
                        if (rowPtr.possibleValues.equals(secondPtr.possibleValues)) {
                            Cell thirdPtr = rowPtr.right;
                            boolean change = false;
                            while (thirdPtr != null) {
                                if (thirdPtr != secondPtr && thirdPtr != rowPtr && thirdPtr.possibleValues != null) {
                                    change = change || thirdPtr . possibleValues . remove ( rowPtr . possibleValues);
                                }
                                thirdPtr = thirdPtr.right;
                            }
                            if (change){
                                return true;
                            }
                        }
                        secondPtr = secondPtr.right;
                    }
                  }
                  rowPtr = rowPtr.right;  
            }
        }
        //Check columns
        for (int col = 0; col < cols.length; col++) {
            Cell colPtr = cols[col];
            while (colPtr != null) {
                  if (colPtr.possibleValues != null && colPtr.possibleValues.length == 2) {
                    Cell secondPtr = colPtr.below;
                    while (secondPtr != null) {
                        if (colPtr.possibleValues.equals(secondPtr.possibleValues)) {
                            Cell thirdPtr = colPtr.below;
                            boolean change = false;
                            while (thirdPtr != null) {
                                if (thirdPtr != secondPtr && thirdPtr != colPtr && thirdPtr.possibleValues != null) {
                                    change = change || thirdPtr . possibleValues . remove ( colPtr . possibleValues);
                                }
                                thirdPtr = thirdPtr.below;
                            }
                            if (change){
                                return true;
                            }
                        }
                        secondPtr = secondPtr.below;
                    }
                  }
                  colPtr = colPtr.below;  
            }
        }
        //Check blocks
        for (int block = 0; block < blocks.length; block++) {
            Cell blockPtr = blocks[block];
            while (blockPtr != null) {
                  if (blockPtr.possibleValues != null && blockPtr.possibleValues.length == 2) {
                    Cell secondPtr = blockPtr.block;
                    while (secondPtr != null) {
                        if (blockPtr.possibleValues.equals(secondPtr.possibleValues)) {
                            Cell thirdPtr = blockPtr.block;
                            boolean change = false;
                            while (thirdPtr != null) {
                                if (thirdPtr != secondPtr && thirdPtr != blockPtr && thirdPtr.possibleValues != null) {
                                    change = change || thirdPtr . possibleValues . remove ( blockPtr . possibleValues);
                                }
                                thirdPtr = thirdPtr.block;
                            }
                            if (change){
                                return true;
                            }
                        }
                        secondPtr = secondPtr.block;
                    }
                  }
                  blockPtr = blockPtr.block;  
            }
        }
        return false;

    }


}