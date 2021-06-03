public class RBTree<T extends Comparable<T>, E>{
	public int size=0;
	public RedBlackNode<T,E> root;
	public RBTree()
	{
		root=null;
		size=0;
	}
    public void insert(T key, E value) {
    	if(root==null)
    	{
    		root=new RedBlackNode<T,E>(key);
    		root.insert(value);
    		root.isRed=false;//Set Root as Black
    		size+=1;
    		return;
    	}
    	size+=1;
    	Insert(root,key,value);
    }
    public void Insert(RedBlackNode<T,E> start,T key,E value)
    {
    	int x=start.key.compareTo(key);
    	if(x==0) {
    		start.insert(value);
    		return;
    	}
    	else if(x>0)
    	{
    		if(start.left==null)
    		{
    			start.left=new RedBlackNode<T,E>(key);
    			start.left.insert(value);
    			start.left.parent=start;
    			if (start.isRed)
    			{
    				//Double Red
    				ResolveDoubleRed(start.left);
    			}
    		}
    		else
    			Insert(start.left,key,value);
    	}
    	else
    	{
    		if(start.right==null)
    		{
    			start.right=new RedBlackNode<T,E>(key);
    			start.right.insert(value);
    			start.right.parent=start;
    			start.right.isLeft=false;
    			if(start.isRed)
    			{
    				//Double Red
    				ResolveDoubleRed(start.right);
    			}
    		}
    		else
    			Insert(start.right,key,value);
    	}
    }
    public void ResolveDoubleRed(RedBlackNode<T,E> trav)
    {
    	if(trav.parent==null)
    	{//Node is root
    		if(trav.isRed)
    		{
    			trav.isRed=false;
    		}
    		return;
    	}//Not a Double Red
    	if(!trav.isRed || !trav.parent.isRed)//One of the two is black
    	{
    		return;
    	}
    	//A double Red;
    	RedBlackNode<T,E> z=trav,y=trav.parent,x=y.parent,w=null;
    	if(y.isLeft)
    	{
    		w=x.right;
    	}
    	else
    	{
    		w=x.left;
    	}
    	if(w==null)//=>Black Sibling of y . So one Restructure and Then Do recolouring
    	{
    		RedBlackNode<T,E> ab=x.parent;
    		boolean b=x.isRed;
    		RedBlackNode<T,E> rbt=Restructure(z);
    		//System.out.println(ab.getValue());
    		rbt.parent=ab;
    		if(ab==null)
    		{
    			root=rbt;
    			rbt.isLeft=true;
    			rbt.isRed=false;
    			rbt.parent=null;
    			return;
    		}
    		if(rbt.isLeft)
    		{
    			ab.left=rbt;
    			rbt.isRed=b;
    		}
    		else
    		{
    			ab.right=rbt;
    			rbt.isRed=b;
    		}
    	}
    	else
    	if(w.isRed)//Red Uncle of z . Re-color and Recur
    	{
    		w.isRed=false;
    		y.isRed=false;
    		x.isRed=true;
    		ResolveDoubleRed(x);
    		return;
    	}
    	else//Black Sibling of y
    	{
    		RedBlackNode<T,E> ab=x.parent;
    		boolean b=x.isRed;
    		RedBlackNode<T,E> rbt=Restructure(z);
    		//System.out.println(ab.getValue());
    		rbt.parent=ab;
    		if(ab==null)
    		{
    			root=rbt;
    			rbt.isLeft=true;
    			rbt.isRed=false;
    			rbt.parent=null;
    			return;
    		}
    		if(rbt.isLeft)
    		{
    			ab.left=rbt;
    			rbt.isRed=b;
    		}
    		else
    		{
    			ab.right=rbt;
    			rbt.isRed=b;
    		}
    	}
    }
    public RedBlackNode<T,E> Restructure(RedBlackNode<T,E> trav)
    {
    	RedBlackNode<T,E> z=trav,y=trav.parent,x=y.parent;
    	boolean k=x.isRed;
    	RedBlackNode<T,E> a=null,b=null,c=null,T1=null,T2=null,T3=null,T4=null;
    	if(y.isLeft)
    	{
    		if(z.isLeft)
    		{
    			/*
    			   x
    			  / \
    			 y   T4
    			/ \
    		   z   T3
    		  / \
    		 T1  T2 
    			 
    			 */
    			c=x;
    			b=y;
    			a=z;
    			T1=a.left;
    			T2=a.right;
    			T3=b.right;
    			T4=c.right;
    			
    		}
    		else
    		{
    			/*
    			    x
    			   / \
    			  y   T4
    			 / \
    			T1   z
    			    / \
    			   T2  T3
    			  
    			 */
    			c=x;
    			b=z;
    			a=y;
    			T1=a.left;
    			T2=b.left;
    			T3=b.right;
    			T4=c.right;
    		}
    		b.isLeft=x.isLeft;
    		b.left=a;
    		a.parent=b;
    		a.isLeft=true;
    		b.right=c;
    		c.isLeft=false;
    		c.parent=b;
    		a.left=T1;
    		if(T1!=null) {
	    		T1.parent=a;
	    		T1.isLeft=true;
    		}
    		a.right=T2;
    		if(T2!=null) {
	    		T2.parent=a;
	    		T2.isLeft=false;
    		}
    		c.left=T3;
    		if(T3!=null) {
	    		T3.parent=c;
	    		T3.isLeft=true;
    		}
    		c.right=T4;
    		if(T4!=null) {
	    		T4.isLeft=false;
	    		T4.parent=c;
    		}
    		a.isRed=true;
    		c.isRed=true;
    		b.isRed=k;
    		return b;
    	}
    	else
    	{
    		if(!z.isLeft)
    		{
    			/*
    			 x
    			/ \
    		   T1  y
    			  / \
    			 T2  z
    			    / \ 
    			   T3  T4
    			 */
    			a=x;
    			b=y;
    			c=z;
    			T1=x.left;
    			T2=y.left;
    			T3=z.left;
    			T4=z.right;
        	}
    		else
    		{
    			/*
    			  x
    			 / \
    			T1   y
    			    / \
    			   z   T4 
    			  / \
    			 T2  T3
    			 */
    			a=x;
    			b=z;
    			c=y;
    			T1=x.left;
    			T2=z.left;
    			T3=z.right;
    			T4=y.right;
    		}
    		b.isLeft=x.isLeft;
    		b.left=a;
    		a.parent=b;
    		a.isLeft=true;
    		b.right=c;
    		c.isLeft=false;
    		c.parent=b;
    		a.left=T1;
    		if(T1!=null) {
	    		T1.parent=a;
	    		T1.isLeft=true;
    		}
    		a.right=T2;
    		if(T2!=null) {
	    		T2.parent=a;
	    		T2.isLeft=false;
    		}
    		c.left=T3;
    		if(T3!=null) {
	    		T3.parent=c;
	    		T3.isLeft=true;
    		}
    		c.right=T4;
    		if(T4!=null) {
	    		T4.isLeft=false;
	    		T4.parent=c;
    		}
    		a.isRed=true;
    		c.isRed=true;
    		b.isRed=k;
    		return b;
    	}
    }
    public RedBlackNode<T, E> search(T key) {
    	RedBlackNode<T,E> trav=root;
    	while(trav!=null)
    	{
    		int x=trav.key.compareTo(key);
    		if(x>0)
    		{
    			trav=trav.left;
    		}
    		else if(x<0)
    		{
    			trav=trav.right;
    		}
    		else
    			return trav;
    	}
    	RedBlackNode<T,E>zu=new RedBlackNode<>(null);
    	return zu ;
    }
    public void preorderTrav()
    {
    	preorder(root);
    }
    public void preorder(RedBlackNode<T,E> start)
    {
    	if(start==null)
    	{
    		return ;
    	}
    	System.out.println(start+" Start.isLeft = "+start.isLeft+" Start.colour is Red is "+start.isRed);
    	preorder(start.left);
    	preorder(start.right);
    }
    public void RBTree_to_Array(DynamicArray<E>D,RedBlackNode<T,E>node)
    {
    	if(node==null)return ;
    	else
    	{
    		RBTree_to_Array(D,node.left);
    		D.insert(node.getValue());
    		RBTree_to_Array(D,node.right);
    	}
    }
    public void RBTree_to_Array(DynamicArray<E>D)
    {
    	RBTree_to_Array(D,root);
    }
    
   /* public static void main(String[]args)
    {
    	RBTree<String,String> re=new RBTree<String,String>();
    	re.insert("hah", "KK");
    	re.insert("haha","BK");
    	re.insert("hahaa","CK");
    	re.insert("AB","XY");
    	re.insert("Alpha", "Beta");
    	re.insert("Index", "Value");
    	re.insert("Alpha", "NewBeta");
    	re.preorderTrav();
    }*/
}