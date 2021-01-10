import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class RedBlackTests {

    @Test
    public void put_get_empty_size_delete_contains() {
        MyRedBlack tree = new MyRedBlack();
        assertTrue(tree.isEmpty());
        assertEquals(tree.size(), 0);
        tree.put(1, "a");
        assertEquals(tree.size(), 1);
        assertFalse(tree.isEmpty());
        tree.put(2, "b");
        assertEquals(tree.size(), 2);
        assertEquals(tree.get(1),"a");
        assertTrue(tree.contains(2));
        tree.deleteMin();
        assertFalse(tree.contains(1));

        tree.put(3,"c");
        tree.put(4,"d");
        tree.deleteMax();
        assertFalse(tree.contains(4));
        tree.deleteMax();
        assertFalse(tree.contains(3));
        tree.delete(2);
        assertTrue(tree.isEmpty());

    }

    @Test
    public void height_min_max_floor_ceiling_select_rank() {
        MyRedBlack tree = new MyRedBlack();
        tree.put(1, "a");
        tree.put(3,"c");
        tree.put(4,"d");
        tree.put(5, "a");
        tree.put(6, "b");
        tree.put(7,"c");
        tree.put(8,"d");
        assertEquals(tree.height(), 2);
        tree.put(10,"d");
        assertEquals(tree.min(),1);
        assertEquals(tree.max(),10);
        assertEquals(tree.height(), 3);

        assertEquals(tree.floor(2),1);
        assertEquals(tree.floor(3),3);
        assertEquals(tree.ceiling(9),10);
        assertEquals(tree.ceiling(8),8);

        assertEquals(tree.select(5),7);
        assertEquals(tree.rank(6),4);
        assertEquals(tree.select(2),4);
        assertEquals(tree.rank(2),1);
        assertEquals(tree.select(tree.rank(2)),3);
        assertEquals(tree.select(tree.rank(3)),3);
    }

    @Test
    public void testKeys() {
        MyRedBlack tree = new MyRedBlack();
        for (int i = 0; i < 8; i++) {
            tree.put(i, i);
        }

        for (Object key :
                tree.keys()) {
            assertEquals(tree.get((int) key),(int) key);
        }
    }

}