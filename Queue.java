/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Gary Cullen
 * Description: Queue integer data structure class
 * Date: 01/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */

/** Queue integer data structure class */
public class Queue {

    /* ===== properties ===== */
    final int SIZE=10;  // set constant size of queue
    int queue[]; // queue base structure is array
    int head; // head - 0 for start of queue
    int tail; // tail - -1 indicates empty queue
    int noElements; // noElements - 0 initially.

    /* ===== constructor ===== */
    public Queue(){

        queue = new int[SIZE];  // create array of set size
        head = 0;  // head - 0 for start of queue
        tail = -1;  // tail - -1 indicates empty queue
        noElements = 0;  // noElements - 0 initially.
    }

    /* ===== methods ===== */
    /** push element onto stack */


}
