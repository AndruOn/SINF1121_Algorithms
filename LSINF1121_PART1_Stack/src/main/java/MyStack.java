import java.util.EmptyStackException;

public class MyStack<E> implements Stack<E> {

    private Node<E> top;        // the node on the top of the stack
    private int size;        // size of the stack

    // helper linked list class
    private class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }

    }

    /**
     * Tests if this stack is empty
     */
    @Override
    public boolean empty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Looks at the object at the top of this stack
     * without removing it from the stack
     */
    @Override
    public E peek() throws EmptyStackException {
        if (!empty()) {
            return top.item;
        }
        throw new EmptyStackException();
    }

    /**
     * Removes the object at the top of this stack
     * and returns that object as the value of this function
     */
    @Override
    public E pop() throws EmptyStackException {
        if (empty()) {
            throw new EmptyStackException();
        } else {
            Node<E> r = top;
            top = r.next;
            size--;
            r.next = null;
            return r.item;
        }

    }

    /**
     * Pushes an item onto the top of this stack
     * @param item the item to append
     */
    @Override
    public void push(E item) {
        Node<E> newNode = new Node<E>(item,top);
        top= newNode;
        size++;
    }


}
