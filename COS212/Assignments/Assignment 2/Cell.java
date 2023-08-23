public class Cell implements Comparable<Cell> {
    int databaseRow;
    String value;

    Cell(int dbRow, String val) {
        databaseRow = dbRow;
        value = val;
    }

    Cell (String val) {
        value = val;
    }

    @Override
    public int compareTo(Cell o) {
        return value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return value + "{" + databaseRow + "}";
    }
}
