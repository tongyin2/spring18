public class ArrayDeque<T> implements Deque<T> {

    private T[] content;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque(){
        content = (T[]) new Object[8];
        size= 0;
        nextFirst = content.length - 1;
        nextLast = 0;
    }

    @Override
    public void addFirst(T item) {
        if(size() == content.length){
            resize(2 * content.length);
        }
        content[nextFirst] = item;
        if(nextFirst == 0){
            nextFirst = content.length - 1;
        }else{
            nextFirst--;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        if(size() == content.length) {
            resize(2 * content.length);
        }
        content[nextLast] = item;
        if(nextLast == content.length - 1){
            nextLast = 0;
        }else{
            nextLast++;
        }
        size++;
    }

    private void resize(int newlength){
        T[] temp = (T[]) new Object[newlength];
        /*int srcstart = nextFirst == content.length - 1 ? 0 : nextFirst + 1;
        int l = content.length - srcstart;
        System.arraycopy(content, srcstart, temp, 0, l);
        System.arraycopy(content, 0, temp, l, nextLast);
        nextFirst = temp.length - 1;
        nextLast = size();
        content = temp;*/

        int srcstart = nextFirst == content.length - 1 ? 0 : nextFirst + 1;
        int srcend = nextLast == 0 ? content.length - 1 : nextLast - 1;

        if(srcstart <= srcend){
            System.arraycopy(content, srcstart, temp, 0, srcend - srcstart + 1);
        }else{
            System.arraycopy(content, srcstart, temp, 0, content.length - srcstart);
            System.arraycopy(content, 0, temp, content.length - srcstart, srcend + 1);
        }
        nextFirst = temp.length - 1;
        nextLast = size();
        content = temp;
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
        System.out.println(toString());
    }

    @Override
    public T removeFirst() {
        if(isEmpty()) return null;
        int srcstart = nextFirst == content.length - 1 ? 0 : nextFirst + 1;
        T re = content[srcstart];
        nextFirst = srcstart;
        size--;
        if(content.length >= 16 && size() <= content.length / 4){
            resize(content.length / 2);
        }
        return re;
    }

    @Override
    public T removeLast() {
        if(isEmpty()) return null;
        int srcend = nextLast == 0 ? content.length - 1 : nextLast - 1;
        T re = content[srcend];
        nextLast = srcend;
        size--;
        if(content.length >= 16 && size() <= content.length / 4){
            resize(content.length / 2);
        }
        return re;
    }

    @Override
    public T get(int index) {
        if(isEmpty() || index < 0 || index >= size()) return null;
        int srcstart = nextFirst == content.length - 1 ? 0 : nextFirst + 1;
        int target = index + srcstart <= content.length - 1 ? index + srcstart : index + srcstart - (content.length - 1) - 1;
        return content[target];
    }

    public int capacity(){
        return content.length;
    }

    public static void main(String[] args){
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
        intArr.printDeque();
        System.out.println(intArr.get(1));
    }








    /**
     *
     * @return
     */
    @Override
    public String toString(){
        int srcstart = nextFirst == content.length - 1 ? 0 : nextFirst + 1;
        int count = size();
        String s = "";
        while(count != 0){
            s += content[srcstart].toString() + " ";
            count--;
            srcstart = srcstart == content.length - 1 ? 0 : srcstart + 1;
        }
        return s;
    }

    @Override
    public boolean equals(Object x){
        if(!(x instanceof ArrayDeque<?>)){
            return false;
        }

        if(((ArrayDeque) x).size() != size()) return false;

        int srcstart = nextFirst == content.length - 1 ? 0 : nextFirst + 1;
        int count = size();
        int xsrcstart = ((ArrayDeque) x).nextFirst == ((ArrayDeque) x).content.length - 1 ? 0 : ((ArrayDeque) x).nextFirst + 1;
        while(count != 0){

            if(!((ArrayDeque<?>) x).content[xsrcstart].equals(content[srcstart])) {
                return false;
            }
            count--;
            srcstart = srcstart == content.length - 1 ? 0 : srcstart + 1;
            xsrcstart = xsrcstart == ((ArrayDeque) x).content.length - 1 ? 0 : xsrcstart + 1;
        }
        return true;
    }
}
