
public class BinaryMaxHeap<T extends Comparable<T>>{
	public DynamicArray<T> heap;
	public int size;
	public BinaryMaxHeap(){
		heap=new DynamicArray<T>();
		size=0;
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
		while(i>=0 && heap.get(Parent(i)).compareTo(heap.get(i))>0) {
			T temp=heap.get(i);
			heap.set(i, heap.get(Parent(i)));
			heap.set(Parent(i), temp);
			i=Parent(i);
		}
	}
	public void ShiftDown(int i) {
		int l=LeftChild(i);
		int r=RightChild(i);
		int maxIndex=i;
		if(l<size && heap.get(l).compareTo(heap.get(maxIndex))<0)
		{
			maxIndex=l;
		}
		if(r<size &&heap.get(r).compareTo(heap.get(maxIndex))<0) {
			maxIndex=r;
		}
		if(i!=maxIndex) {
			T temp=heap.get(maxIndex);
			heap.set(maxIndex, heap.get(i));
			heap.set(i,temp);
			ShiftDown(maxIndex);
		}
	}
    public void insert(T elemnt) {
    	heap.insert(elemnt);
    	ShiftUp(size);
    	size+=1;
    }
    public T extractMax() {
    	if(size==0) return null;
        T res=heap.get(0);
        heap.set(0, heap.get(size-1));
        heap.Delete(size-1);
        size-=1;
        ShiftDown(0);
        return res;
    }
    public void Heapify()
    {
    	for(int i=heap.size()/2;i>=0;i--)
    	{
    		ShiftDown(i);
    	}
    }
    public DynamicArray<T> HeapSort(DynamicArray<T>DA)
    {
    	for(int j=0;j<DA.size();j++)
    	{
    		this.heap.insert(DA.get(j));
    	}
    	DynamicArray<T>res=new DynamicArray<>(DA.size());
    	this.size=DA.size();
    	this.Heapify();
    	while(this.size>0)
    	{
    		res.insert(this.extractMax());
    	}
    	return res;
    }
    public static void main(String []args)
    {
    	BinaryMaxHeap<Integer>BMH=new BinaryMaxHeap<Integer>();
    	DynamicArray<Integer>DA=new DynamicArray<Integer>();
    	DA.insert(2);
    	DA.insert(100);
    	DA.insert(3);
    	DA=BMH.HeapSort(DA);
    	System.out.println(DA);
    }
}