/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Gary Cullen
 * Description: Stack data structure class
 * Date: 01/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */

/** Stack data structure class */
public class Stack {

    private final int SIZE = 10;  // set constant size of stack
    private int stack[];  // stack base structure is array
    private int top;

    /* ===== constructor ===== */
    public Stack(){
        stack = new int[SIZE];  // create array of set size
        top = -1;  // top initialised to -1 => indicates empty stack
    }

    /* ===== methods ===== */
    /** push element onto stack */
    private void push(int element) {

        if (!isFull()) {
            top++;
            stack[top] = element;
        }
    }

    /** pop element off top of stack */
    private int pop() {

        int itemPopped = -1;

        if (!isEmpty()) {

            itemPopped = stack[top];
            top--;
        }
        return itemPopped;
//
//        int poppedVal;
//        poppedVal=stackObj.pop();
    }

    /** determine if stack is empty -> achieved by referencing the value of property `top` */
    private boolean isEmpty(){

        if(top == -1)
            return true;
        else
            return false;
    }

    /** determine if stack is full -> achieved by referencing the value of constant property `SIZE` */
    private boolean isFull(){

        if(top == SIZE-1)
            return true;
        else
            return false;
    }

    /** IF stack not empty -> return contents of top element in stack */
    private int peek(){

        if(!isEmpty())
            return stack[top];
        else
            return -1;
    }

    /** return size/amount of elements in stack */
    private int size(){

        return top+1;
    }

    /** print elements in stack */
    void print(){

        System.out.print("\nSTACK CONTAINS ==> ");

        for(int i=0; i<=top; i++)

            System.out.print(stack[i] + ",");
    }
}
