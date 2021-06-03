public class DynamicArray<T> {
	private int ArraySize=0;
	public int currentSize=0;
	public Object[]arr;
	public DynamicArray()
	{
		ArraySize=10;
		arr=new Object[10];
	}
	public DynamicArray(int size)
	{
		ArraySize=size;
		arr=new Object[size];
	}
	public int size()
	{
		return this.currentSize;
	}
	public void insert(T val)
	{
		if(currentSize==this.ArraySize)
		{
			Object[]temp=new Object[2*ArraySize];
			this.ArraySize=2*ArraySize;
			for(int i=0;i<this.currentSize;i++)
			{
				temp[i]=arr[i];
			}
			arr=temp;
		}
		this.arr[this.currentSize]=val;
		this.currentSize+=1;
	}
	@SuppressWarnings("unchecked")
	public T get(int x) throws ArrayIndexOutOfBoundsException
	{
		if (x>=this.currentSize)
		{
			throw new  ArrayIndexOutOfBoundsException();
		}
		return (T)this.arr[x];
	}
	public void set(int x,T val)
	{
		if (x>=this.currentSize)
		{
			System.out.println("Here");
			throw new  ArrayIndexOutOfBoundsException();
		}
		this.arr[x]=val;
	}
	public void Delete(int x)
	{
		if (x>=this.currentSize)
		{
			System.out.println(x+" "+this.currentSize);
			throw new  ArrayIndexOutOfBoundsException();
		}
		this.arr[x]=null;
		for (int i=x+1;i<this.currentSize;i++)
		{
			this.arr[i-1]=this.arr[i];
		}
		this.currentSize-=1;
	}
	public String toString()
	{
		String s="[";
		for(int i=0;i<currentSize;i++)
		{
			@SuppressWarnings("unchecked")
			T J=(T)arr[i];
			s+=J.toString();
			if(i!=currentSize-1)
			s+=",";
		}
		s+="]";
		return s;
	}
	/*
	public static void main(String[]args)
	{
		DynamicArray<DynamicArray<String>>DY=new DynamicArray<>();
		for(int i=0;i<100;i++)
		{
			DynamicArray<String>temp=new DynamicArray<>();
			for(int j=11;j<11*i+11;j++)
			{
				temp.insert(Integer.toString(j));
			}
			DY.insert(temp);
		}
		System.out.println(DY);
	}*/
}
