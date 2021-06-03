public class Queue<T> {
	class Node<T>{
		T data;
		Node<T>next;
		Node(T data)
		{
			this.data=data;
		}
	}
	Node<T>head;
	Node<T>tail;
	int size=0;
	public Queue()
	{
		head=tail=null;
		size=0;
	}
	public void insert(T val)
	{
		if(size==0)
		{
			head=new Node<>(val);
			tail=head;
			size++;
		}
		else
		{
			tail.next=new Node<>(val);
			tail=tail.next;
			size+=1;
		}
	}
	public T delete()
	{
		if(size==0)return null;
		else
		{
			T val=head.data;
			head=head.next;
			size-=1;
			return val;
		}
	}
}
