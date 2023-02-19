/**
 * LinkedList is class where we implement a data structure we will use in the hase table
 */
public class LinkedList {
    Node Head;
    Node Tail;
    int size;

    public LinkedList() // Constructor
    {
        /**
         * in this function we set build an empty linked list
         */
        this.Head = null;
        this.Tail = null;
        size = 0;
    }

    /**
     * in the add function we are adding a person with his in information to the linked_list
     * and update the size and pointers.
     * @param person the person we are adding to the linked_list.
     *        person is an array with his first name, family name, id , ticket kind
     */
    public void add(String[] person)
    {
        Node p = new Node(person);
        if (Tail != null) // if the linklist is not empty we add to the tail
            this.Tail.next = p; // updating the  pointer
        else
            this.Head = p; // adding to the head
        this.Tail = p;
        size++; //updating the size after the add
    }

    /**
     *
     * @return the function getSize return the size of the linked list, how many pepole singed to the concert
     */
    public int getSize(){
        return size;
    }

    /**
     *
     * @return the function getHead return the pointer of the head of  linked list
     */
    public Node getHead(){
        return this.Head;
    }
}
