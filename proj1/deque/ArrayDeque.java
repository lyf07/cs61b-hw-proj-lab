package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
    private class MyIterator implements Iterator<T>{
        private int pos;
        public MyIterator(){
            pos = 0;
        }

        public boolean hasNext(){
            return pos < size();
        }

        public T next(){
            T ans = get(pos);
            pos += 1;
            return ans;
        }
    }

    private int length;
    private T []array;


    private int start;
    private int last;
    public ArrayDeque()
    {
        length = 8;
        //put start at the end of the array, when add elements, just move forward
        start = 7;
        //put last at the beginning of the array, when add elements, just move backward
        last = 0;
        array = (T[])new Object[length];
    }


    //Don't rush to do it, do it when you've done everything else
    private void resize()
    {
        //resize to more elements
        int ori = length;
        int tail = length - start - 1;
        int head = last;
        length *= 2;
        T []temp = (T[]) new Object[length];
        for (int i = 0; i  < tail; i++) {
            temp[length - 1 - i] = array[ori - 1 - i];
        }
        for (int i = 0; i < head; i++) {
            temp[i] = array[i];
        }
        start = length - (ori - start);
        array = temp;
    }

    @Override
    public void addFirst(T item)
    {
        if (size() + 1 > length) {
            resize();
        }
        array[start] = item;
        start--;
    }

    @Override
    public void addLast(T item){
        if (size() + 1 > length) {
            resize();
        }
        array[last] = item;
        last++;
    }


    @Override
    public void printDeque(){
        //print from start,to the end
        for (int i = start; i < length; i++) {
            System.out.print(array[i] + " ");
        }

        //print from beginning, to the last
        for (int i = 0; i <= last; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if (isEmpty()) {
            return null;
        }
        T item;
        if (start == length - 1) {
            last--;
            item = array[0];
            for (int i = 0; i < last - 1; i++) {
                array[i] = array[i + 1];
            }
        }
        else
        {
            item = array[start + 1];
            start++;
        }
        return item;
    }

    @Override
    public T removeLast(){
        if (isEmpty()) {
            return null;
        }
        T item;
        if (last == 0) {
            start++;
            item = array[length - 1];
            for (int i = length - 1; i > start; i--) {
                array[i] = array[i - 1];
            }
        }
        else
        {
            item = array[last - 1];
            last--;
        }
        return item;
    }

    @Override
    public T get(int index){
        if (index < 0 || index >= length - start + last - 1) {
            return null;
        }
        return array[(start + index + 1) % length];

    }

    @Override
    public int size()
    {
        int tail = length - start -1;
        int head = last;
        return tail + head;
    }

    @Override
    public Iterator<T> iterator(){
        return new MyIterator();
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Deque){
            if(((Deque<?>) o).size() == this.size()){
                for (int i = 0; this.get(i) != null; i += 1){
                    if(((Deque<?>) o).get(i) != this.get(i)){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
