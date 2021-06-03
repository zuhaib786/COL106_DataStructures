package Trie;


import Util.NodeInterface;

public class TrieNode<T> implements NodeInterface<T> {
	private T val;
	public TrieNode<T>[]arr;
	private String s="";
	public String armVar="";
	private boolean endofword=false;
	private int n;
	@SuppressWarnings("all")
	TrieNode(int n){
		arr=new TrieNode[n];
		val=null;
		this.n=n;
	}
    @Override
    public T getValue() {
    	return val;
    }
    public void setVal(T val) {
    	this.val=val;
    }
    public void SetEnd(boolean b) {
    	this.endofword=b;
    }
    public boolean getEnd() {
    	return this.endofword;
    }
    @SuppressWarnings("all")
    public TrieNode[] getArr() {
    	return this.arr;
    }
    @SuppressWarnings("all")
    public TrieNode insert(char a) {
    	if(this.arr[(int)a]==null)
    	{
    		arr[(int)a]=new TrieNode(this.n);
    		s+=a;
    	}
    	return arr[(int)a];
    }
    public String toString() {
    	return this.s;
    }
    public void setS(String s) {
    	this.s=s;
    }
    /*public static void main(String []args) {
    	TrieNode<String> trie=new TrieNode<String>(128);
    	for(int i=48;i<127;i++) {
    		trie.insert((char)i);
    	}
    	System.out.println(trie);
    }
    
    Temporary debugging is done will do further debugging later on.
    */

}