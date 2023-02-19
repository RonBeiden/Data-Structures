/**
 *  The HashOpen program implements an application that
 *  simply displays a Hash table holding an arrays of size 2
 *  holds 2 hash functions to decide location of insertion
 */
public class HashOpen {
    // array of arrays as Hash table
    public int[][] A;
    // Constructor, m is table size as required
    public HashOpen(int m){
        this.A = new int[m][2];
    } // create array of arrays, each array contains person's id and number of steps until his seat
    public int hashFunc1(int id, int m){ return id % m;} // hash func as described in the assignment
    public int hashFunc2(int id, int m){ // hash func as described in the assignment
        int id_reverse = 0;
        while(id != 0) // in this loop we flip the order of digits in the id number
        {
            int remainder = id % 10;
            id_reverse = id_reverse * 10 + remainder;
            id = id / 10;
        }
        return id_reverse % m;
    }

    /**
     * the function check if the  content of two arrays are the same
     * @param arr1 first array
     * @param arr2 second array
     * @return boolean if the arrays are the same or not
     */
    public boolean isTheSame(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false; // checks if the sizes are different
        for (int i = 0; i < arr1.length; i++)
            if (arr1[i] != (arr2[i])) // check if all the values in the arrays at the same index is the same
                return false;
        return true;
    }

    /**
     *
     * @param id the id of the person we are adding
     * @param hashFunc the exact hash function we are using (1 or 2)
     * @return the number of steps it takes to the person's seat
     */
    public int insert(int id, int hashFunc) {
        int steps = 0;
        // empty int array: [0,0]
        int[] zero = new int[2];
        int index;
        if (hashFunc == 1) // decide what hase func to use
            index = hashFunc1(id, this.A.length); // the index in we are trying to seat the person
        else
            index = hashFunc2(id, this.A.length);  // the index in we are trying to seat the person
        int k = 0; // the distance we are trying from the original index
        while (!isTheSame(A[index+k],zero)) { // the loop will continue until we find an empty seat by using isTheSame function
            steps++; // number of tries
            if (k == 0){
                if (index + k + 1 > A.length-1) // in order to not step out of the array we are lowering k
                    k--;
                else
                    k++; // we are not stepping out so we increase k
        }
            else if(k < 0)
            {
                if(index - k + 1 >A.length-1) // in order to not step out of the array we are increasing k
                    k--;
                else
                    k = -k + 1; // changing k from negative to positive and the other way
            }
            else
            {
                if(index - k < 0) // in order to not step out of the array we are increasing k
                    k++;
                else
                    k = -k;
            }
        }
        A[index + k] = new int[] {id, steps}; // inserting the person to an empty space and updating the array
        return steps; // the steps to the person seat
    }
    // return number of cells that have elements in the HashOpen table
    public int getNumberElements() {
        int cnt = 0;
        int[] zero = new int[2];
        for (int i = 0; i < A.length; i++) {
            // check if array in the index is not empty: means there is an element there
            if (!isTheSame(A[i],zero))
                cnt++;
        }
        return cnt;
    }
    // returns Hash table size
    public int getSize() {
        return A.length;
    }

}
