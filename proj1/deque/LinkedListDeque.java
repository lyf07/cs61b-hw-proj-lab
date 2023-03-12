package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T>{


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
    private int num;

    public class Node{
        public T item;

        public Node next;

        public Node prev;

        public Node(T x, Node after, Node before)
        {
            item = x;
            next = after;
            prev = before;
        }
    }

    private Node sentinel;
    public LinkedListDeque()
    {
        num = 0;
        sentinel = new Node(null ,null,null);

    }

    @Override
    public void addFirst(T item) {
        num++;
        Node p = new Node(item,null,null);
        if (sentinel.next == null) {
            sentinel.next = p;
            p.prev = sentinel;
            p.next = sentinel;
            sentinel.prev = p;
        }
        else
        {
            Node q = sentinel.next;
            sentinel.next = p;
            p.prev = sentinel;
            p.next = q;
            q.prev = p;
        }
    }

    @Override
    public void addLast(T item) {
        num++;
        Node p = new Node(item,null,null);
        if (sentinel.prev == null) {
            sentinel.next = p;
            sentinel.prev = p;
            p.next = sentinel;
            p.prev = sentinel;
        }
        else
        {
            Node q = sentinel.prev;
            sentinel.prev = p;
            p.next = sentinel;
            p.prev = q;
            q.next = p;
        }
    }


    public int size(){return num;}

    @Override
    public void printDeque() {
        Node p = sentinel.next;
        while (p != null && p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst()
    {
        T ans;
        if (sentinel.next == null) {
            return null;
        }
        else
        {
            num--;
            Node p = sentinel.next;
            ans = p.item;
            if (p.next == sentinel) {
                p = sentinel;
                sentinel.next = null;
                sentinel.prev = null;
            }
            else
            {
                sentinel.next = p.next;
                p.next.prev = sentinel;
                p = sentinel;
            }
        }
        return ans;
    }

    @Override
    public T removeLast() {
        T ans;
        if (sentinel.prev == null) {
            return null;
        }
        else
        {
            num--;
            Node p = sentinel.prev;
            ans = p.item;
            if (p.prev == sentinel) {
               sentinel.next = null;
               sentinel.prev = null;
               p = sentinel;
            }
            else
            {
                sentinel.prev = p.prev;
                p.prev.next = sentinel;
                p = sentinel;
            }
        }
        return ans;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= num)
        {
            return null;
        }
        else
        {
            int cnt = 0;
            Node p = sentinel.next;
            while(cnt < index)
            {
                p = p.next;
                cnt++;
            }
            return p.item;
        }
    }

    private T helper(Node p,int index){
        if (index == 0) {
            return p.item;
        }
        else{
            return helper(p.next,index - 1);
        }
    }
    public T getRecursive(int index)
    {
        if(index < 0 || index >= num)
        {
            return null;
        }
        else
        {
            return helper(sentinel.next,index);
        }
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
