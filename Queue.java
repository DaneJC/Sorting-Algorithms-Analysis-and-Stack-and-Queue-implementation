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
    /** Insert element onto queue */
    public void insert(int element) throws IndexOutOfBoundsException {

        if (isFull()) {

            throw new IndexOutOfBoundsException("Queue OverFlow:");
        }
        else {
            if (tail == SIZE - 1)
                tail = 0;
            else
                tail++;
            queue[tail] = element;
            noElements++;

        }
    }

    /** Remove element from queue */
    public void remove() throws IndexOutOfBoundsException{

        int itemToReturn = -1;
        if (noElements == 0) {

            throw new IndexOutOfBoundsException("Queue UnderFlow:");
        }
        else{
            itemToReturn = queue[head];
            if(head == SIZE-1)
                head = 0;
            else
                head++;
            noElements--;
        }
//        return itemToReturn;
    }

    /** determine if queue is empty -> achieved by assessing the value of property `noElements` */
    public boolean isEmpty(){

        if(noElements == 0)
            return true;
        else
            return false;
    }

    /** determine if queue is full -> achieved by assessing the value of constant property `noElements` */
    public boolean isFull(){

        if(noElements == SIZE)
            return true;
        else
            return false;
    }

    /** return contents of index at front of queue */
    public int queueTop(){

        if(!isEmpty())
            return queue[head];
        else return -1;
    }

    /** return size/amount of elements in queue */
    public int size(){

        return noElements;
    }

    /** get stack method for GUI demonstration */
    public int[] getQueue() throws NegativeArraySizeException{

//        int[] returnQueue;
//        if(isEmpty())
//            throw new NegativeArraySizeException();
//        else {
//            if(head == tail) {
//                returnQueue = new int[1];
//                returnQueue[0] = queue[0];
//            }
//            else {
//                int index = 0;
//                returnQueue = new int[size()];
//                for (int i = head; i < tail; i++)
//                    returnQueue[index] = queue[i];
//            }
//        }
//        return returnQueue;
        return queue;
    }

    /** Getters - GUI purposes */
    public int getHead(){
        return head;
    }

    public int getTail(){
        return tail;
    }

}
