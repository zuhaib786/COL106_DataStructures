package PriorityQueue;
import java.util.ArrayList;
@SuppressWarnings("all")
public class MaxHeap<T extends Comparable> implements PriorityQueueInterface<T> {
	 class Pair<T extends Comparable>{
			T val;
			int idx;
			Pair(T val,int index){
				this.val=val;
				this.idx=index;
			}
			public int compareTo(Pair<T> P)
			{
				int x=this.val.compareTo(P.val);
				if(x!=0) {
					return x;
				}
				return P.idx-this.idx;
			}
		}
	private int idx=0;
	public ArrayList<Pair<T>> heap;
	public int size;
	public MaxHeap(){
		heap=new ArrayList<Pair<T>>();
		size=0;
	}
	public void heapify(T arr[])
	{//Used for sorting in this assignment
		for(int i=0;i<arr.length;i++)
		{
			heap.add(new Pair<T>(arr[i],1));
		}
		size=arr.length;
		for(int i=arr.length/2;i>=0;i--)
		{
			ShiftDown(i);
		}
	}
	public static int Parent(int i) {
		if(i==0) {
			return 0;
		}
		return (i-1)/2;
	}
	public static  int LeftChild(int i) {
		return 2*i+1;
	}
	public static int RightChild(int i) {
		return 2*i+2;
	}
	public void ShiftUp(int i) {
		while(i>=0 && heap.get(Parent(i)).compareTo(heap.get(i))<0) {
			Pair<T> temp=heap.get(i);
			heap.set(i, heap.get(Parent(i)));
			heap.set(Parent(i), temp);
			i=Parent(i);
		}
	}
	public void ShiftDown(int i) {
		int l=LeftChild(i);
		int r=RightChild(i);
		int maxIndex=i;
		if(l<size && heap.get(l).compareTo(heap.get(maxIndex))>0)
		{
			maxIndex=l;
		}
		if(r<size &&heap.get(r).compareTo(heap.get(maxIndex))>0) {
			maxIndex=r;
		}
		if(i!=maxIndex) {
			Pair<T>temp=heap.get(maxIndex);
			heap.set(maxIndex, heap.get(i));
			heap.set(i,temp);
			ShiftDown(maxIndex);
		}
	}
    @Override
    public void insert(T element) {
    	Pair<T>elemnt=new Pair<T>(element,idx);
    	idx+=1;
    	heap.add(elemnt);
    	ShiftUp(size);
    	size+=1;
    }
    public void NewInsert(T element,int index)
    {
    	Pair<T>elemnt=new Pair<T>(element,index);
    	heap.add(elemnt);
    	ShiftUp(size);
    	size+=1;
    }
    public ArrayList NewExtractMax()
    {
    	if(size==0) return null;
    	Pair<T>res=heap.get(0);
    	heap.set(0, heap.get(size-1));
    	heap.remove(size-1);
        size-=1;
        ShiftDown(0);
        ArrayList arr=new ArrayList();
        arr.add(res.val);
        arr.add(res.idx);
        return arr;
    }
    @Override
    public T extractMax() {
    	if(size==0) return null;
        Pair<T>res=heap.get(0);
        heap.set(0, heap.get(size-1));
        heap.remove(size-1);
        size-=1;
        ShiftDown(0);
        return res.val;
    }
}