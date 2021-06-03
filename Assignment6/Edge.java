
public class Edge implements EdgeInterface,Comparable<Edge> {
	public Point P1,P2;
	public DynamicArray<Triangle>AjdTr=new DynamicArray<>();
//	public int point_of_insertion;
	public float length_sq;
	public Edge(Point P1,Point P2)
	{
		if (P1.compareTo(P2)>0)
		{
			this.P1=P1;
			this.P2=P2;
		}
		else
		{
			this.P2=P1;
			this.P1=P2;
		}
		this.length_sq=(P1.getX()-P2.getX())*(P1.getX()-P2.getX())+(P1.getY()-P2.getY())*(P1.getY()-P2.getY())+(P1.getZ()-P2.getZ())*(P1.getZ()-P2.getZ());
	}
	public float[]Centroid_Calc()
	{
		float[]res=new float[3];
		for(int i=0;i<3;i++)
		{
			res[i]=0.0f;
		}
		res[0]=(P1.getX()+P2.getX())/this.AjdTr.size();
		res[1]=(P1.getY()+P2.getY())/this.AjdTr.size();
		res[2]=(P1.getZ()+P2.getZ())/this.AjdTr.size();
		return res;
	}
	@Override
	public PointInterface[] edgeEndPoints() {
		
		PointInterface[] P=new Point[2];
		P[0]=P1;
		P[1]=P2;
		return P;
	}
	@Override
	public int compareTo(Edge O) {
		if(this.P1.compareTo(O.P1)!=0)
		{
			return this.P1.compareTo(O.P1);
		}
		return this.P2.compareTo(O.P2);
	}
	@Override
	public String toString()
	{
		return P1.toString()+","+P2.toString();
	}
	public float length_sq()
	{
		return this.length_sq;
	}
}
