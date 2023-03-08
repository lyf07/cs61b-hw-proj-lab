package deque;

public class ArrayDeque<T> implements Deque<T>{

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
//    private void resize()
//    {
//
//    }
    @Override
    public void addFirst(T item)
    {
        array[start] = item;
        start--;
    }

    @Override
    public void addLast(T item){
        array[last] = item;
        last++;
    }

    @Override
    public boolean isEmpty(){return size() == 0;}

    @Override
    public void printDeque(){
        //print from start,to the end
        for(int i = start; i < length; i++)
        {
            System.out.print(array[i] + " ");
        }

        //print from beginning, to the last
        for(int i = 0; i <= last; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        T item = array[start];
        start++;
        return item;
    }

    @Override
    public T removeLast(){
        T item = array[last];
        last--;
        return item;
    }

    @Override
    public T get(int index){
//        int rest = index;
//
//        if(rest < 0)
//            return null;
//        //we first look at the starting portion of this data structure
//        if(rest > length - start - 1)
//        {
//            rest -= length - start - 1;
//        }
//        else
//        {
//            return array[start + rest + 1];
//        }
//        if(rest < last)
//        {
//            return array[rest];
//        }
//        return null;
        if(index < 0 || index >= length - start + last - 1)
        {
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
}
