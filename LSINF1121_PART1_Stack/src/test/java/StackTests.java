import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StackTests {

    @Test
    public void firstTest() {
        Stack<Integer> stack = new MyStack<Integer>();
        stack.push(1);
        assertEquals(new Integer(1), stack.pop());
    }

    @Test(expected = EmptyStackException.class)
    public void peekEmpty() {
        Stack<Integer> stack = new MyStack<Integer>();
        stack.peek();
    }
    @Test
    public void testEmpty() {
        Stack<Integer> stack = new MyStack<Integer>();
        assertTrue(stack.empty());
        stack.push(1);
        assertTrue(!stack.empty());
        stack.pop();
        assertTrue(stack.empty());
    }

    @Test(expected = EmptyStackException.class)
    public void popEmpty() {
        Stack<Integer> stack = new MyStack<Integer>();
        stack.pop();
    }

    @Test
    public void secondTest() {
        Stack<Integer> stack = new MyStack<Integer>();
        stack.push(1);
        stack.push(2);
        assertEquals(new Integer(2), stack.pop());
        assertEquals(new Integer(1), stack.peek());
        assertEquals(new Integer(1), stack.pop());
    }
    // Feel free to add tests in this class to debug your program

    @Test(expected = EmptyStackException.class)
    public void size() {
        Stack<Integer> stack = new MyStack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        stack.pop();
        stack.pop();
    }

    @Test
    public void stringTest() {
        Stack<String> stack = new MyStack<String>();
        stack.push("a");
        stack.push("b");
        assertEquals("b", stack.pop());
        assertEquals("a", stack.peek());
        assertEquals("a", stack.pop());
    }

    @Test
    public void forTest() {
        Stack<Integer> stack = new MyStack<Integer>();
        for (int i = 0; i <= 256; i++) {
            stack.push(new Integer(i));
            assertEquals(new Integer(i),stack.peek());

        }

        for (int i = 256; i >0; i--) {
            assertEquals(new Integer(i),stack.pop());
        }
    }


}

