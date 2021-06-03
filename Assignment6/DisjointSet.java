public class DisjointSet<T> {
	private int Components=0;
	public DynamicArray<Integer> Parent;
	public DynamicArray<T>value;
	public DynamicArray<Integer>Rank;
	public DisjointSet()
	{
		Parent=new DynamicArray<>();
		value=new DynamicArray<>();
		Rank=new DynamicArray<>();
	}
	public void insert(T val)
	{
		Parent.insert(Parent.size());
		value.insert(val);
		Rank.insert(0);
		Components+=1;
	}
	public int Find(int i)
	{
		if(i!=Parent.get(i))
		{
			Parent.set(i, Find(Parent.get(i)));
		}
		return Parent.get(i);
	}
	public void Union(int index1,int index2)
	{
		index1=Find(index1);
		index2=Find(index2);
		if(index1==index2)return ;
		if(Rank.get(index1)>Rank.get(index2))
		{
			Parent.set(index2, index1);
		}
		else if(Rank.get(index2)>Rank.get(index1))
		{
			Parent.set(index1, index2);
		}
		else
		{
			Parent.set(index2, index1);
			Rank.set(index1, Rank.get(index1)+1);
		}
		Components-=1;
	}
	public int getComp()
	{
		return this.Components;
	}
}
