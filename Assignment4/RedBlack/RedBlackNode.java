package RedBlack;
import Util.RBNodeInterface;

import java.util.List;
import java.util.LinkedList;
@SuppressWarnings("all")
public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {
	public boolean isLeft=true;
	public boolean isRed=true;
	public T key;
	public List<E> values;
	public RedBlackNode<T,E>left,right,parent;
	RedBlackNode(T key)
	{
		this.key=key;
		values=null;
	}
    @Override
    public E getValue() {
    	if(values==null)
    		return null;
        return values.get(0);
    }
    public void insert(E val)
    {
    	if (values==null) {
    		values=new LinkedList<E>();
    	}
    	values.add(val);
    	return;
    }
    @Override
    public List<E> getValues() {
        return values;
    }
    @Override
    public String toString()
    {
    	return this.key.toString()+" Values = "+this.values.toString();
    }
}
