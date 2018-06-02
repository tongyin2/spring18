public class LinkedListDeque<T> implements Deque<T> {

    public class Node {
        private T item;
        private Node pre;
        private Node next;

        public Node(T item) {
            this.item = item;
            pre = this;
            next = this;
        }
    }

    private Node sentinal;
    private int size;

    public LinkedListDeque() {
        sentinal = null;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        if (size() == 0) {
            sentinal = new Node(item);
        }
        Node node = new Node(item);
        Node temp = sentinal.next;
        sentinal.next = node;
        node.pre = sentinal;
        node.next = temp;
        temp.pre = node;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size() == 0) {
            sentinal = new Node(item);
        }
        Node node  = new Node(item);
        Node temp = sentinal.pre;
        temp.next = node;
        node.pre = temp;
        node.next = sentinal;
        sentinal.pre = node;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int count = size();
        Node n = sentinal.next;
        while (count != 0) {
            System.out.print(n.item + " ");
            n = n.next;
            count--;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size() == 0) return null;
        T re = sentinal.next.item;
        Node temp = sentinal.next.next;
        sentinal.next = temp;
        temp.pre = sentinal;
        size--;
        return re;
    }

    @Override
    public T removeLast() {
        if (size() == 0) return null;
        T re = sentinal.pre.item;
        Node temp = sentinal.pre.pre;
        sentinal.pre = temp;
        temp.next = sentinal;
        size--;
        return re;
    }

    @Override
    public T get(int index) {
        if (index >= size() || index < 0) return null;
        int count = index;
        Node n = sentinal;
        while (count >= 0) {
            n = n.next;
            count--;
        }
        return n.item;
    }

    public T getRecursive(int index) {
        if (index >= size() || index < 0) return null;
        return getRecursive(sentinal, index);
    }

    private T getRecursive(Node sentinal, int index) {
        if (index == 0) {
            return sentinal.next.item;
        }
        return getRecursive(sentinal.next, index - 1);
    }
}
