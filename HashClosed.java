/**
 The HashClosed program implements a Hash table that
 * simply displays all people that registered with an array of linked lists
 */
public class HashClosed {
    //variable of an array of linked list
    public LinkedList[] A;
    public int true_m = 0;
    private int size;
    // Constructor: creating a hash table with an array of linked list
    // m is the number of people that we plan to register(!)
    public HashClosed(int m){

        this.A = new LinkedList[m / 3];
        this.true_m = m;
        this.size = 0;
    }
    //hash func: with this func we will decide on the index of insertion
    public int Hashfunc(int id, int m){
        if (m != 0)
            return id % (m);
        return 0;
    }

    /**
     * inserting to the hash table(close) a person
     * @param person: as an array with all details
     */
    public void insert(String[] person){
        //getting the index from the hash func
        int i = Hashfunc(Integer.parseInt(person[0]),A.length);
        // check if there is already a person in linked list in the A[index]
        // if not: creating new list with the person as the head
        // else: adding the person to the linked list
        if(A[i] == null){
            LinkedList first = new LinkedList();
            first.add(person);
            A[i] = first;
        }
        else
            A[i].add(person);
        this.size++;
    }
    // Return the size of each node in the hash table [node1size, node2size, node3size, ...]
    public int[] getNodesSize() {
        // empty array of size of the hash table size
        // will hold in each index the size of each node
        int [] sizes = new int[A.length];
        // loop through hash length and using getSize() method to get each node size
        for (int i = 0; i < A.length; i++)
        {
            // check if linked lists in table are not empty
            if (A[i] != null)
                sizes[i] = A[i].getSize();
            else
                sizes[i] = 0;
        }
        return sizes;
    }
    // searching a person in the hash table
    // Return array: [found(1 if found, 0 if not), number of steps] , as mentioned in the assignment
    public int[] search(String[] person) {
        int [] found = new int[2];
        if(A.length == 0)
        {
            found[0] = 0;
            found[1] = 1;
            return found;
        }
        // casting the id to int from string
        int id = Integer.parseInt(person[0]);
        // getting the index that the person suppose to be there using Hashfunc
        int i = Hashfunc(id,A.length);
        //pointer to head of linked
        if (A[i] != null) {
            Node pointer = A[i].getHead();
            // loop through linked list size
            for (int j = 0; j < A[i].size; j++) {
                //check if we found the id of person
                if (pointer.getId() == id) {
                    // 1 is found
                    found[0] = 1;
                    // num of step until found
                    found[1] = j + 1;
                    return found;
                }
                // advancing the pointer
                pointer = pointer.getNext();
            }
            // 0 if not found
            found[0] = 0;
            //num of steps
            found[1] = A[i].size + 1;
        }
        else
        {
            found[0] = 0;
            found[1] = 1;
        }
        return found;
    }
    // returns table size
    public int getSize() {
        return A.length;
    }
    public int GetRealSize(){
        return this.size;
    }
}
