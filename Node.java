/**
 The Node program implements a node that
 * simply displays all the details on a person who bought a ticket
 */
public class Node{
    //all details on person and his ticket
    public String private_name;
    public String last_name;
    public int id;
    public String type;
    public Node next;

    // Constructor, empty node
    public Node()
    {
        this.id = -1;
        this.private_name = null;
        this.last_name = null;
        this.type = null;
        next = null;
    }
    // Constructor, creating node from an array with all the details of a node
    public Node(String[] person){
        this.id = Integer.parseInt(person[0]);
        this.private_name = person[1];
        this.last_name = person[2];
        this.type = person[3];
        next = null; //setting his next to null, will be a part of the linked list
    }
    // returning the next node of this node
    public Node getNext(){
        return this.next;
    }
    // returning the id detail from the node
    public int getId(){return this.id;}
    // getting all the information about the node representing as an array
    // returning an array
    public String[] getData(){
        String[] A = new String[4];
        A[0] = Integer.toString(id);
        A[1] = private_name;
        A[2] = last_name;
        A[3] = type;
        return A;
    }
}
