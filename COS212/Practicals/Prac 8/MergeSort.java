public class MergeSort <T extends Comparable<T>> extends Sort<T> {
    @Override
    @SuppressWarnings("unchecked")
    public Comparable<T>[] sort(Comparable<T>[] arr) {
        //Hint This function can be implemented as a one liner consisting of a return and a function call
        return mergeSort(arr, 0, arr.length-1);
    }

    @SuppressWarnings("unchecked")
    private Comparable<T>[] mergeSort(Comparable<T>[] arr){
        //Do not change the position of this function.
        printArr(arr);
        //Add any code below
             
    }

    @SuppressWarnings("unchecked")
    private Comparable<T>[] merge(Comparable<T>[] lh, Comparable<T>[] rh){
        
    }

    private int getMidPoint(int first, int last){
        return (int)Math.floor((first+last)/2);
    }

    //Recursive Helper
    private Comparable<T>[] mergeSort(Comparable<T>[] arr, int first, int last){
        int mid = getMidPoint(first, last);
         
    }
    
}
