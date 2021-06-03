package Trie;
import java.util.ArrayList;
import PriorityQueue.*;
@SuppressWarnings("all")
public class Trie<T> implements TrieInterface {

	public TrieNode root;
	public int size=0;
    
    public Trie()
	{
		root=new TrieNode(128);
	}
    public void HeapSort(String[]arr)
    {
    	MaxHeap<String> h=new MaxHeap();
    	h.heapify(arr);
    	String []k=new String[arr.length];
    	int i=0;
    	while(h.size>0)
    	{
    		k[i]=h.extractMax();
    		i++;
    	}
    	for(i=0;i<arr.length;i++)
    	{
    		arr[i]=k[arr.length-i-1];
    	}
    }
    public void HeapSort(Character[]arr)
    {
    	MaxHeap<Character> h=new MaxHeap<Character>();
    	h.heapify(arr);
    	Character []k=new Character[arr.length];
    	int i=0;
    	while(h.size>0)
    	{
    		k[i]=h.extractMax();
    		i++;
    	}
    	for(i=0;i<arr.length;i++)
    	{
    		arr[i]=k[arr.length-i-1];
    	}
    }
    public void printTrie(TrieNode trieNode) {
    	if(trieNode==null) {
    		return;
    	}
    	String tem=trieNode.armVar;
    	ArrayList<String>List=new ArrayList<String>();
    	printTrieRec(tem,trieNode,List);
    	String []arr=new String[List.size()];
    	for(int i=0;i<List.size();i++)
    	{
    		arr[i]=List.get(i);
    	}
    	//Arrays.sort(arr);
    	HeapSort(arr);
    	for(int i=0;i<List.size();i++)
    	{
    		System.out.println(arr[i]);
    	}
    }
    public void printTrieRec(String tem,TrieNode node,ArrayList<String>List){
    	if(node.getEnd()) {
    		List.add(node.getValue().toString());
    	}
    	if(node.toString().length()==0) {
    		return ;
    	}
    	String j=node.toString();
    	for(int i=0;i<j.length();i++)
    	{
    		printTrieRec(tem+j.charAt(i),node.arr[(int)j.charAt(i)],List);
    	}
    }
    @Override
    public  boolean delete(String word)
	{
		if(root==null)
		{
			return false;
		}
		boolean[] b=new boolean[1];
		DeleteRec(root,word,0,b);
		/*if(b[0]) {
			System.out.println("DELETED");
		}
		else {
			System.out.println("ERROR DELETING");
		}*/
		return b[0];
	}
    TrieNode DeleteRec(TrieNode node,String word,int i,boolean [] b){
		if (node==null) {
			b[0]=false;
			return null;
		}
		if(i==word.length()&& !node.getEnd()) {
			b[0]=false;
			return node;
		}
		 if (i==word.length()) {
			if (node.toString().length()==0) {
				if(node.getEnd()) {
					b[0]=true;
					return null;
				}
				b[0]=false;
				return null;
			}
			else {
				b[0]=true;
				node.SetEnd(false);
				return node;
			}
		}
		if(node.arr[(int)word.charAt(i)]==null) {
			b[0]=false;
			return null;
		}
		node.arr[(int)word.charAt(i)]=DeleteRec(node.arr[(int)word.charAt(i)],word,i+1,b);
		if(b[0]) {
			if(node.arr[(int)word.charAt(i)]==null)
			{
				String tem="";
				String k=node.toString();
				for(int m=0;m<k.length();m++) {
					if((int)k.charAt(m)!=(int)word.charAt(i)) {
						tem+=k.charAt(m);
					}
				}
				if(tem.length()==0) {
					return null;
				}
				node.setS(tem);
				return node;
			}
			return node;
		}
		return node;
	}

    @Override
    public TrieNode search(String word)
	{
		TrieNode start=root;
		if(start==null)
		{
			return null;
		}
		for(int i=0;i<word.length();i++)
		{
			int idx=(int)word.charAt(i);
			if(start.arr[idx]==null)
			{
				return null;
			}
			start=start.arr[idx];
		}
		if(!start.getEnd())
		{
			return null;
		}
		return start;
	}
    
    @Override
    public TrieNode startsWith(String Prefix)
	{
		if(root==null)
		{
			return null;
		}
		TrieNode prev=root;
		TrieNode curr=root;
		for (int i=0;i<Prefix.length();i++)
		{
			prev=curr;
			curr=curr.arr[(int)Prefix.charAt(i)];
			if (curr==null)
			{
				return null;
			}
		}
		return curr;
	}

    @Override
	public boolean insert(String word,Object value) {
    	TrieNode current=root;
		for(int i=0;i<word.length();i++)
		{
			String c=current.armVar;
			current=current.insert(word.charAt(i));
			current.armVar=c+word.charAt(i);// Might change this implementation later on.Time complexity issues
		}
		if (current.getEnd())
		{
			return false;
		}
		current.setVal(value);
		current.SetEnd(true);
		size++;
		return true;
	}
    public int printLevel1(int level) {
    	System.out.print("Level "+level+": ");
    	ArrayList<Character>Q1=new ArrayList<Character>();
    	printAccording(root,Q1,0,level-1);
    	ArrayList<Character> Q=new ArrayList<Character>();
    	for(int i=0;i<Q1.size();i++)
    	{
    		Character x=Q1.get(i);
    		if((int)x!=32)
    			Q.add(x);
    	}
    	Character []arr=new Character[Q.size()];
    	for(int i=0;i<Q.size();i++)
    	{
    		arr[i]=Q.get(i);
    	}
    	//Arrays.sort(arr);
    	HeapSort(arr);
    	for(int i=0;i<Q.size()-1;i++) {
    		if((int)arr[i]!=32)
    		System.out.print(arr[i]+",");
    	}
    	if(Q1.size()==0) {
    		System.out.println();
    		return 0;
    	}
    	System.out.println(arr[Q.size()-1]);
    	return Q1.size();
    	
    }
    public void printLevel(int level) {
    	System.out.print("Level "+level+": ");
    	ArrayList<Character>Q1=new ArrayList<Character>();
    	printAccording(root,Q1,0,level-1);
    	ArrayList<Character> Q=new ArrayList<Character>();
    	for(int i=0;i<Q1.size();i++)
    	{
    		Character x=Q1.get(i);
    		if((int)x!=32)
    			Q.add(x);
    	}
    	Character []arr=new Character[Q.size()];
    	for(int i=0;i<Q.size();i++)
    	{
    		arr[i]=Q.get(i);
    	}
    	//System.out.println(Arrays.toString(arr));
    	//Arrays.sort(arr);
    	HeapSort(arr);
    	for(int i=0;i<Q.size()-1;i++) {
    		if((int)arr[i]!=32)
    		System.out.print(arr[i]+",");
    	}
    	if(Q1.size()==0) {
    		System.out.println();
    		return;
    	}
    	System.out.println(arr[Q.size()-1]);
    	System.out.println("-------------");
    }
    public void printAccording(TrieNode node,ArrayList<Character>Q,int curr_level,int level)
    {	if(node==null) {
    	return ;
    	}
    	if(curr_level==level) {
    		String temp=node.toString();
    		for (int i=0;i<temp.length();i++)
    		{
    			Q.add(temp.charAt(i));
    		}
    	}
    	String j=node.toString();
    	for(int i=0;i<j.length();i++)
    	{
    		printAccording(node.arr[(int)j.charAt(i)],Q,curr_level+1,level);
    	}
    	
    }
    @Override
    public void print() {
    	System.out.println("Printing Trie");
    	int x=printLevel1(1);
    	int j=2;
    	while(x!=0) {
    		x=printLevel1(j);
    		j++;
    	}
    	System.out.println("-------------");
    }
  /*  public static void main(String[]args) {
    	Trie<String> trie=new Trie<String>();
    	for(int i=32;i<58;i++)
    	{
    		for(int j=32;j<58;j++)
    		{
    			for(int k=32;k<58;k++)
    			{
    				String in="";
    				in+=(char)i;
    				in+=(char)j;
    				in+=(char)k;
    				System.out.println(trie.insert(in, in)+" "+in);
    			}
    		}
    	}
    	System.out.println(trie.root);
    	System.out.println(trie.delete("001"));
    	System.out.println(trie.delete("01"));
    	trie.printTrie(trie.startsWith("001"));
    	trie.printTrie(trie.startsWith("01"));//have to fix end comma and will check later on about the Lexicographic order
    	trie.print();
    	//Basic implementation has held all corner cases.
    	//Will check later on for some more subtle cases if I find some
    	//The Time complexity of string concatenation will be addressed later on
    	//Rest is basically correct
    }*/
}