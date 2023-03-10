package deque;

public class LinkedListDeque<T> implements Deque<T> {
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
    public void addFirst(T item)
    {
        num++;
        Node p = new Node(item,null,null);
        if(sentinel.next == null)
        {
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
    public void addLast(T item)
    {
        num++;
        Node p = new Node(item,null,null);
        if(sentinel.prev == null)
        {
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
    public void printDeque()
    {
        Node p = sentinel.next;
        while(p != null && p != sentinel)
        {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst()
    {
        T ans;
        if(sentinel.next == null)
        {
            return null;
        }
        else
        {
            num--;
            Node p = sentinel.next;
            ans = p.item;
            if(p.next == sentinel)
            {
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
    public T removeLast()
    {
        T ans;
        if(sentinel.prev == null)
        {
            return null;
        }
        else
        {
            num--;
            Node p = sentinel.prev;
            ans = p.item;
            if(p.prev == sentinel)
            {
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
    public T get(int index)
    {
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

//    public T getRecursive(int index)
//    {
//        if(index < 0 || index >= num)
//        {
//            return null;
//        }
//        else
//        {
//            if(index == 0)
//        }
//
//    }
}
