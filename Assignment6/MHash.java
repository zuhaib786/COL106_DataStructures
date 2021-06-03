public class MHash {
	public DynamicArray<Integer>indices=new DynamicArray<>();
	public static int hash32shift(int key)
	{
	  key = ~key + (key << 15); // key = (key << 15) - key - 1;
	  key = key ^ (key >>> 12);
	  key = key + (key << 2);
	  key = key ^ (key >>> 4);
	  key = key * 2057; // key = (key + (key << 3)) + (key << 11);
	  key = key ^ (key >>> 16);
	  if (key<0)return -1*key;
	  return key;
	}
	public DynamicArray<DynamicArray<Integer>>[]HashTable=new DynamicArray[4969];//Key value pair
	public MHash()
	{
		
	}
	public void insert(Integer key,Integer value )
	{
		int idx=MHash.hash32shift(key)%4969;
		if(HashTable[idx]==null)
		{
			indices.insert(idx);
			HashTable[idx]=new DynamicArray<>();
			DynamicArray<Integer>ins=new DynamicArray<>();
			ins.insert(key);
			ins.insert(value);
			HashTable[idx].insert(ins);
			return ;
		}
		for(int i=0;i<HashTable[idx].size();i++)
		{
			if(HashTable[idx].get(i).get(0).compareTo(key)==0)
			{
				Integer x=HashTable[idx].get(i).get(1);
				x+=value;
				HashTable[idx].get(i).set(1, x);
				return ;
			}
		}
		DynamicArray<Integer>ins=new DynamicArray<>();
		ins.insert(key);
		ins.insert(value);
		HashTable[idx].insert(ins);
		return;
	}
	public int getCount()
	{
		int res=0;
		for(int i=0;i<indices.size();i++)
		{
			int k=indices.get(i);
			if(HashTable[k]!=null)
			{
				for(int j=0;j<HashTable[k].size();j++)
				{
					res+=HashTable[k].get(j).get(1)/HashTable[k].get(j).get(0);
				}
			}
		}
		return res;
	}
}
