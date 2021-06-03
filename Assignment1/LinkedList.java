import java.util.Iterator;
import java.io.*;
public class LinkedList <T> implements LinkedList_<T>
{
  public Node<T> head =new Node<T> ();
  public int size=0;
  public Node<T> add(T e)
  {
    if(head.val==null)
    {
      head.val=e;
      size+=1;
      return head;
    }
    Node<T> temp=new Node<T>(e);
    temp.next=head;
    head=temp;
    size+=1;
    return head;
  }
  public int count()
  {
    return size;
  }
  public Iterator<Node<T> > iterator()
  {
    return new ListIterator<T>(this);
  }
  public void changeCount()
  {
    this.size+=1;
  }
}
class ListIterator<T> implements Iterator<Node<T> > {
    Node<T> current;

    // initialize pointer to head of the list for iteration
    public ListIterator(LinkedList<T> list)
    {
        current = list.head;
    }

    // returns false if next element does not exist
    public boolean hasNext()
    {
        return current != null;
    }

    // return current data and update pointer
    public Node<T> next()
    {
        Node<T> data = current;
        current = current.next;
        return data;
    }
	public void remove()
	    {
		throw new UnsupportedOperationException();
	    }
}//LIFO type implementation to ease the reversing at time of query printing;
