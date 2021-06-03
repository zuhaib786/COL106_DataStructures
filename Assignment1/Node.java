import java.util.Iterator;
import java.io.*;
public class Node <T> implements Position_<T>
{
  public T val=null;
  public Node<T> next=null;
  Node()
  {}
  Node(T val)
  {
    this.val=val;
  }
  public T value()
  {
    return val;
  }
  public Node<T> after()
  {
    return this.next;
  }
}
