import java.util.Iterator;
import java.io.*;
public interface LinkedList_<T>
{
    public int count();//return the size
    public Iterator<Node <T> > iterator();
    public Node<T> add(T e);
};
