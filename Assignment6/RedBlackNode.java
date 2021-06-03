public class RedBlackNode<T extends Comparable<T>, E> {
	public boolean isLeft=true;
	public boolean isRed=true;
	public T key;
	public DynamicArray<E> values;
	public RedBlackNode<T,E>left,right,parent;
	RedBlackNode(T key)
	{
		this.key=key;
		values=null;
	}
    public E getValue() {
        return values.get(0);
    }
    public void insert(E val)
    {
    	if (values==null) {
    		values=new DynamicArray<E>();
    	}
    	values.insert(val);
    	return;
    }
    public DynamicArray<E> getValues() {
        return values;
    }
    @Override
    public String toString()
    {
    	return this.key.toString()+" Values = "+this.values.toString();
    }
}
