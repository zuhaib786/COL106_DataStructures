//Hashing Using Separate Chaining
public class MyHashMap<K extends Comparable<K>,T> {
	public DynamicArray<RBTree<K,T>>HashTable;
	int x=2;
	public MyHashMap(int n)
	{
		HashTable=new DynamicArray<RBTree<K,T>>(n);
		for(int i=0;i<n;i++)
		{
			HashTable.insert(null);
		}
	}
	public  int djb2(String str) { 
	    int hash = 5381; 
	    for (int i = 0; i < str.length(); i++) { 
	        hash = ((hash << 5) + hash) + str.charAt(i); 
	    } 
	    return Math.abs(hash) % HashTable.size(); 
	}
	public void insert(K key,T val)
	{
		String J=key.toString();
		int idx=this.djb2(J);
		if(HashTable.get(idx)==null)
		{
			RBTree<K,T> z=new RBTree<K,T>();
			z.insert(key, val);
			HashTable.set(idx,z);
		}
		else
		{
			RBTree<K,T>z=HashTable.get(idx);
			z.insert(key, val);
		}
	}
	public T get(K key)
	{
		String J=key.toString();
		int idx=this.djb2(J);
		if(HashTable.get(idx)==null||HashTable.get(idx).search(key).getValues()==null)
		{
			return null;
		}
		else
		{
			return HashTable.get(idx).search(key).getValue();
		}
	}
}
