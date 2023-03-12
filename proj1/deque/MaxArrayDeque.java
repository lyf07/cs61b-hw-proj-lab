package deque;
import java.lang.reflect.Array;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{

    private Comparator com;
    private ArrayDeque<T> maxArray;
    public MaxArrayDeque(Comparator<T> c)
    {
        maxArray = new ArrayDeque<T>();
        com = c;
    }
    public T max()
    {
        if(maxArray.isEmpty())
        {
            return null;
        }
        T max = maxArray.get(0);
        for(int i = 1; ; i++)
        {
            T temp = maxArray.get(i);
            if(temp == null)
            {
                break;
            }
            if(com.compare(max,temp) < 0)
            {
                max = temp;
            }
        }
        return max;
    }

    public T max(Comparator<T> c)
    {
        if(maxArray.isEmpty())
        {
            return null;
        }
        T max = maxArray.get(0);
        for(int i = 1; ; i++)
        {
            T temp = maxArray.get(i);
            if(temp == null)
            {
                break;
            }
            if(c.compare(max,temp) < 0)
            {
                max = temp;
            }
        }
        return max;
    }
}
