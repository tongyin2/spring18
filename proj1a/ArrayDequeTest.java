import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testAdd(){
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < 10; i++){
            deque.addLast(i);
        }

        ArrayDeque<Integer> expected = new ArrayDeque<>();
        for(int i = 5; i < 10; i++){
            expected.addLast(i);
        }
        for(int i = 4; i >= 0; i--){
            expected.addFirst(i);
        }
        assertEquals(expected, deque);
    }

    @Test
    public void testAddRemoveResize() {
        /**
         * test removefirst remove last without resizing
         */
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < 10; i++){
            deque.addLast(i);
        }
        deque.removeFirst();
        deque.removeLast();
        ArrayDeque<Integer> expected = new ArrayDeque<>();
        for(int i = 1; i < 9; i++){
            expected.addLast(i);
        }
        assertEquals(expected, deque);

        /**
         *
         */
        deque = new ArrayDeque<>();
        for(int i = 0; i < 32; i++){
            deque.addLast(i);
        }
        for(int i = 0; i < 24; i++) {
            deque.removeLast();
        }
        expected = new ArrayDeque<>();
        for(int i = 7; i >= 0; i--){
            expected.addFirst(i);
        }
        assertEquals(expected, deque);
    }

    @Test
    public void testResize() {
        ArrayDeque<Integer> intArr = new ArrayDeque<>();
        for (int i = 0; i < 10000; i++) {
            intArr.addFirst(0);
        }
        for (int i = 0; i < 9999; i++) {
            intArr.removeLast();
        }
        assertEquals(1, intArr.size());
        assertEquals(8, intArr.capacity());
    }

    @Test
    public void testAddRemove() throws Exception {
        int[] expected = {1, 2, 3, 4, 5};

        ArrayDeque<Integer> intArr = new ArrayDeque<>();
        intArr.addLast(3);
        intArr.addFirst(2);
        intArr.addLast(4);
        intArr.addFirst(1);
        intArr.addLast(5);
        intArr.addFirst(0);
        intArr.addLast(6);

        intArr.removeFirst();
        intArr.removeLast();

        for(int i = 0; i < expected.length; i++){
            assertEquals(expected[i], intArr.get(i).intValue());
        }
    }
}
