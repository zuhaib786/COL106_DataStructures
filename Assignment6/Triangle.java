public class Triangle implements TriangleInterface,Comparable<Triangle> {
	public Edge E1,E2,E3;
	public DynamicArray<Triangle>AdjTriangles=new DynamicArray<>();
	public int NumTr;
	public int dis=-1;
	public boolean isVisited=false;
	public Triangle(Edge E1,Edge E2, Edge E3)
	{
		BinaryMaxHeap<Edge>Heap=new BinaryMaxHeap<>();
		Heap.insert(E1);
		Heap.insert(E2);
		Heap.insert(E3);
		this.E1=Heap.extractMax();
		this.E2=Heap.extractMax();
		this.E3=Heap.extractMax();
	}
	@Override
	public PointInterface[] triangle_coord() {
		PointInterface[]res=new PointInterface [3];
		PointInterface []temp=E3.edgeEndPoints();
		res[0]=temp[0];
		res[1]=temp[1];
		temp=E2.edgeEndPoints();
		res[2]=temp[1];
		return res;
	}
	@Override
	public int compareTo(Triangle o) {
		if(this.E1.compareTo(o.E1)!=0)return this.E1.compareTo(o.E1);
		else if(this.E2.compareTo(o.E2)!=0)return this.E2.compareTo(o.E2);
		return this.E3.compareTo(o.E3);
	}
	public boolean isEqual(float a1,float a2)
	{
		if(a1-a2<0.0001 && a1-a2>-0.0001)return true;
		return false;
	}
	public  boolean isZer(float a)
	{
		if (a<0.0001 && a>-0.0001)
		{
			return true;
		}
		return false;
	}
	public boolean isValid()
	{
		PointInterface[]ans=this.triangle_coord();
		float X1=ans[1].getX()-ans[2].getX();
		float X2=ans[0].getX()-ans[1].getX();
		float Y1=ans[1].getY()-ans[2].getY();
		float Y2=ans[0].getY()-ans[1].getY();
		float Z1=ans[1].getZ()-ans[2].getZ();
		float Z2=ans[0].getZ()-ans[1].getZ();
		
		float Xcomp=Y1*Z2-Y2*Z1;
		float Ycomp=X1*Z2-X2*Z1;
		float Zcomp=X1*Y2-X2*Y1;
		if(isZer(Xcomp)&&isZer(Ycomp)&&isZer(Zcomp))
		{
			return false;
		}
		return true;
	}
	public Edge[]getEdges()
	{
		Edge []e=new Edge[3];
		e[0]=E1;
		e[1]=E2;
		e[2]=E3;
		return e;
	}
	public float[] Centroid_Calc()
	{
		float[]res=new float[3];
		for(int i=0;i<3;i++)
		{
			res[i]=0.0f;
		}
		Shape.Add(res, E1.Centroid_Calc());
		Shape.Add(res, E2.Centroid_Calc());
		Shape.Add(res, E3.Centroid_Calc());
		return res;
	}
	public String toString()
	{
		PointInterface[] P=this.triangle_coord();
		return Integer.toString(NumTr);//((Point)P[0]).toString()+","+((Point)P[1]).toString()+","+((Point)P[2]).toString();
	}
}
