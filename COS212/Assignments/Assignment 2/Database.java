@SuppressWarnings("unchecked")
public class Database {
    String[][] database;
    String[] columnNames;
    Treap<Cell>[] indexes;

    public Database(String[] cols, int maxSize) {
        database = new String[maxSize][cols.length];
        columnNames = cols;
        indexes = new Treap[cols.length];
    }

    public void insert(String[] newRowDetails) throws DatabaseException {
        //First check if the row has correct number of columns
        if (newRowDetails.length != columnNames.length) {
            throw DatabaseException.invalidNumberOfColums();
        }
        boolean flag = false;
        for(int count = 0; count < database.length; count++){
            if(database[count][0] == null){
                for(int count2 = 0; count2 < newRowDetails.length; count2++){
                    try{
                        if(indexes[count2] == null){
                            database[count][count2] = newRowDetails[count2];
                            flag = true;
                        }
                        else{
                            if(indexes[count2].access(new Cell(newRowDetails[count2])) == null){
                                indexes[count2].insert(new Cell(count, newRowDetails[count2]));
                            }
                            else{
                                throw DatabaseException.duplicateInsert(newRowDetails[count2]);
                            }
                        }
                    }
                    catch (DatabaseException error){
                        //Retrow the error
                        throw error;
                    }
            }
            if(flag){
                return;
            }
        }

            if(count == database.length - 1){
                throw DatabaseException.databaseFull();
            }
        }
    }

    public String[] removeFirstWhere(String col, String data) throws DatabaseException {
        return null;
    }

    public String[][] removeAllWhere(String col, String data) throws DatabaseException {
        return null;
    }

    public String[] findFirstWhere(String col, String data) throws DatabaseException {
        return null;
    }

    public String[][] findAllWhere(String col, String data) throws DatabaseException {
        return null;
    }

    public String[] updateFirstWhere(String col, String updateCondition, String data) throws DatabaseException {
        return null;
    }

    public String[][] updateAllWhere(String col, String updateCondition, String data) throws DatabaseException {
        return null;
    }

    public Treap<Cell> generateIndexOn(String col) throws DatabaseException {
        return null;
    }

    public Treap<Cell>[] generateIndexAll() throws DatabaseException {
        return null;
    }

    public int countOccurences(String col, String data) throws DatabaseException {
        return 0;
    }
}
