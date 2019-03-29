/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Gary Cullen
 * Description: Stack integer data structure class
 * Date: 01/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */

/** Stack integer data structure class */
public class Stack {

    /* ===== properties ===== */
    private final int SIZE = 10;  // set constant size of stack
    private int[] stack;  // stack base structure is array
    private int top;

    /* ===== constructor ===== */
    public Stack(){
        stack = new int[SIZE];  // create array of set size
        top = -1;  // top initialised to -1 => indicates empty stack
    }

    /* ===== methods ===== */
    /** push element onto stack */
    public void push(int element) throws IndexOutOfBoundsException{

        if (isFull()) {

            throw new IndexOutOfBoundsException("Stack OverFlow:");
        }
        else{
            top++;
            stack[top] = element;
        }
    }

    /** pop element off top of stack */
    public int pop() throws IndexOutOfBoundsException{

        int itemPopped = -1;

        if (isEmpty()) {

            throw new IndexOutOfBoundsException("Stack UnderFlow:");
        }
        else{
            itemPopped = stack[top];
            top--;
        }
        return itemPopped;
//
//        int poppedVal;
//        poppedVal=stackObj.pop();
    }

    /** determine if stack is empty -> achieved by assessing the value of property `top` */
    public boolean isEmpty(){

        if(top == -1)
            return true;
        else
            return false;
    }

    /** determine if stack is full -> achieved by assessing the value of constant property `SIZE` */
    public boolean isFull(){

        if(top == SIZE-1)
            return true;
        else
            return false;
    }

    /** IF stack not empty -> return contents of top index in stack */
    public int peek(){

        if(!isEmpty())
            return stack[top];
        else
            return -1;
    }

    /** return size/amount of elements in stack */
    public int size(){

        return top+1;
    }

    /** print elements in stack */
    public void print(){

        System.out.print("\nSTACK CONTAINS ==> ");

        for(int i=0; i<=top; i++)

            System.out.print(stack[i] + ",");
    }

    /** get stack method for GUI demonstration */
    public int[] getStack() throws NegativeArraySizeException{

        int[] returnStack;
        if(isEmpty())
            throw new NegativeArraySizeException();
        else {
            returnStack = new int[size()];
            for (int i = 0; i < returnStack.length; i++)
                returnStack[i] = stack[i];
        }
        return returnStack;
    }
}
