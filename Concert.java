import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 The Concert program implements an application that
 * simply displays a registration to a concert
 */
public class Concert {
    /**
     * creating and returning LinkedList with Crowd or people in reception from a txt file
     */

    public static LinkedList getFromFile(String FileName) throws FileNotFoundException {
        LinkedList peoples = new LinkedList(); // create empty linked list
        // using java.io and java.util.Scanner packages to extract the txt from the file
        File file = new File(FileName);
        Scanner scan = new Scanner(file);
        //loop throw every line until no lines
        while (scan.hasNextLine()) {
            //creating a String from each line
            String line = scan.nextLine();
            //using split method on string creating an array: each index get the string before the ","
            String[] s = line.split(",", 4);
            // adding to linked list the array that represent a person
            peoples.add(s);
        }
        // returning linked list
        return peoples;
    }

    /**
     *
     * @param file_path of  the registered crows as txt file
     * @return HashClosed with all registered people
     * @throws FileNotFoundException if File as not found
     */
    public static HashClosed registerCrowd(String file_path) throws FileNotFoundException {
        // creating linked list using getFromFile method
        LinkedList peoples = getFromFile(file_path);
        //creating empty Hash table, size: linked list length
        HashClosed total_reg = new HashClosed(peoples.getSize());
        //pointer to head of list
        // the pointer will help us travel the list without changing the Head/Tail
        Node pointer = peoples.getHead();
        //loop through the linked list
        while (pointer != null) {
            //inserting to the hash table each person as an array
            total_reg.insert(pointer.getData());
            //advancing the pointer
            pointer = pointer.getNext();
        }
        //return the hash table
        return total_reg;
    }

    // A utility function to swap two elements
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /* This function takes last element as pivot, places
       the pivot element at its correct position in sorted
       array, and places all smaller (smaller than pivot)
       to left of pivot and all greater elements to right
       of pivot */
    static int partition(int[] arr, int low, int high) {
        // pivot
        int pivot = arr[high];
        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller
            // than the pivot
            if (arr[j] < pivot) {// Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }
    //quiksort function: will sort the array of id's
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     *
     * @param file_path of  the registered crows as txt file
     * @param registered HashClosed with all registered people
     * @return int[] with sortedIds after reception
     * @throws FileNotFoundException if File as not found
     */
    public static int[] reception(String file_path, HashClosed registered) throws FileNotFoundException {
        // creating linked list using getFromFile method
        LinkedList peoples = getFromFile(file_path);
        //creating 4 empty linked lists
        //each linked list for each type of ticket
        LinkedList vip = new LinkedList();
        LinkedList golden = new LinkedList();
        LinkedList inner = new LinkedList();
        LinkedList outer = new LinkedList();
        //creating an empty array, size: linked list of peoples length
        //at the end will hold all id's after reception
        int[] id = new int[peoples.getSize()];
        //pointer to head of linked list
        Node pointer = peoples.getHead();
        //index for adding to id array
        int temp = 0;
        //loop until end of linked list
        while (pointer != null) {
            //searching in the hash table to see if person who registered is actually arrived
            //adding the data the is_found array
            int[] is_found = registered.search(pointer.getData());
            //if found
            if (is_found[0] == 1) {
                //adding to the id's array of people who will get in to the concert
                id[temp] = pointer.getId();
                temp++;
            } else { // not found in the registered peoples
                //adding the person to a linked list depend on his ticket type
                if (pointer.getData()[3].equals("VIP"))
                    vip.add(pointer.getData());
                else if (pointer.getData()[3].equals("GOLDEN_RING"))
                    golden.add(pointer.getData());
                else if (pointer.getData()[3].equals("INNER_RING"))
                    inner.add(pointer.getData());
                else if (pointer.getData()[3].equals("OUTER_RING"))
                    outer.add(pointer.getData());
            }
            pointer = pointer.getNext();
        }
        //creating an array that hold the size of each linked list in the hash-closed table
        //using the getNodesSize function
        int[] nodes_size = registered.getNodesSize();
        int sum = 0;
        //summing with for loop all the peoples that souled arrive
        for (int i = 0; i < nodes_size.length; i++) {
            sum += nodes_size[i];
        }
        //count: the number of tickets that we can sell(spare tickets of the people that registers and not arrived)
        int count = sum - temp + 1;
        //filling the empty spots with loop
        while (count != 0) {
            //first giving the tickets to vip customers
            //check if linked lists of each ticket type has people, then adding them to the id's array
            //removing them from the "waiting" linked list
            if (vip.getHead() != null) {
                id[temp] = vip.getHead().getId();
                temp++;
                vip.Head = vip.getHead().getNext();
                //Then to golden ring
            } else if (golden.getHead() != null) {
                id[temp] = golden.getHead().getId();
                temp++;
                golden.Head = golden.getHead().getNext();
                //inner ring
            } else if (inner.getHead() != null) {
                id[temp] = inner.getHead().getId();
                temp++;
                inner.Head = inner.getHead().getNext();
                //last to outer ring
            } else if (outer.getHead() != null) {
                id[temp] = outer.getHead().getId();
                temp++;
                outer.Head = outer.getHead().getNext();
            }
            //after adding to the id's, decreasing the counter: now we have less one empty spot
            count--;
        }
        // counter that will hold the id that no zero
        //using loop to get the size
        int cnt_size = 0;
        for (int i = 0; i < id.length; i++) {
            if (id[i] != 0)
                cnt_size++;
        }
        // if there is 0 in the id array:
        // there is more seats to sell and no people to sell to
        if(cnt_size != id.length)
        {
            // array in the size of the real id's in id array
            int[] id_no_zeros = new int[cnt_size-1];
            //adding the real id's to the new array with loop
            for (int i = 0; i < id_no_zeros.length; i++) {
                id_no_zeros[i] = id[i];
            }
            // sorting the id_no_zeros array with quickSort
            quickSort(id_no_zeros, 0, id_no_zeros.length-1);
            return id_no_zeros;
        }
        // sorting the id's array using quickSort
        quickSort(id, 0, id.length-1);
        // returning the sorted id's array
        return id;
    }

    /**
     *
     * @param file_path of  the registered crows as txt file
     * @param registered HashClosed with all registered people
     * @return average steps of searching in HashClosed
     * @throws FileNotFoundException if File as not found
     */
    public static int reception_AverageSteps(String file_path, HashClosed registered) throws FileNotFoundException {
        // using getFromFile to extract peoples to linked list
        LinkedList peoples = getFromFile(file_path);
        // pointer to head of list
        Node pointer = peoples.getHead();
        int cnt = 0; // num of peoples (nodes in linked list)
        int sum = 0; // all the steps returning from search method
        // loop until pointer is null (end of linked list)
        while (pointer != null) {
            // array that represent if node has been found and how many steps took to find
            int[] is_found = registered.search(pointer.getData());
                sum += is_found[1];
                // counting + 1 num of nodes (peoples)
                cnt++;
            // advancing the pointer
            pointer = pointer.getNext();
        }
        // return avg of steps: total / num of peoples
        return sum / cnt;
    }

    /**
     *
     * @param sortedCrowed array of id's of the crowd (sorted)
     * @param registered HashClosed with all registered people
     * @param functionNum num of hash function (1 or 2)
     * @return array of stats as requested in the assigment
     */
    // return stats as mentioned in the assignment
    public static int[] seatingArrangement(int[] sortedCrowed, HashClosed registered, int functionNum) {
        // creating hash table as HashOpen: size of the registered;
        HashOpen hall = new HashOpen(registered.GetRealSize());
        // will sum num of steps
        int sum = 0;
        // array that will hold the requested stats
        int[] stats = new int[4];
        // loop through id's length and adding them to the HashOpen
        // summing the steps took to insert
        for (int i = 0; i < sortedCrowed.length; i++) {
            sum += hall.insert(sortedCrowed[i], functionNum);
            // num of steps of n/2 first peoples
            if(i+1 == sortedCrowed.length / 2)
                stats[0] = sum;
            // num of steps of 3/4 n first peoples
            if(i+1 == Math.floor(sortedCrowed.length * 0.75))
                stats[1] = sum;
            // num of steps of n - sqrt(n), sqrt is round up first peoples
            if(i+1 == (sortedCrowed.length - Math.floor(Math.sqrt(sortedCrowed.length))))
                stats[2] = sum;
        }
        // num of steps of last sqrt(n) peoples, sqrt is round up
        // this is complementary of the first  n - sqrt(n)
        stats[3] = sum - stats[2];
        return stats;
    }
}


