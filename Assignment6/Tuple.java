
public class Tuple implements Comparable<Tuple> {
	private Comparable[]array;
	private int j=0;
	public Tuple(int n)
	{
		array=new Comparable[n];
	}
	public Comparable get(int i)
	{
		return array[i];
	}
	public void insert(Comparable i)
	{
		array[j]=i;
		j+=1;
	}
	@Override
	public int compareTo(Tuple o) {
		for(int i=0;i<array.length;i++)
		{
			int x=this.array[i].compareTo(o.get(i));
			if (x!=0) {
				return x;
			}
		}
		return 0;
	}

}
