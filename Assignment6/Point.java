
public class Point implements PointInterface,Comparable<Point> {
	float x,y,z;
	public Point(float x, float y,float z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
	}
	RBTree<Point,Point>AdjPoints=new RBTree<Point,Point>(); 
	DynamicArray<Triangle>Triangle=new DynamicArray<>();
	public float getX() {
		return this.x;
	}
	public float getY() {
		return this.y;
	}
	public float getZ() {
		return this.z;
	}
	public float[] getXYZcoordinate() {
		float[]ans=new float[3];
		ans[0]=x;
		ans[1]=y;
		ans[2]=z;
		return ans;
	}
	public String toString() {
		String s="[";
		s+=Float.toString(this.x);
		s+=",";
		s+=Float.toString(this.y)+",";
		s+=Float.toString(this.z)+"]";
		return s;
	}
	@Override
	public int compareTo(Point o) {
		if(this.x-o.getX()>=0.0001||this.x-o.getX()<=-0.0001)
		{
			if (this.x>o.x)return 1;
			else return -1;
		}
		else if(this.y-o.getY()>=0.0001||this.y-o.getY()<=-0.0001)
		{
			if (this.y>o.y)return 1;
			else return -1;
		}
		else if(this.z-o.getZ()>=0.0001||this.z-o.getZ()<=-0.0001)
		{
			if (this.z>o.z)return 1;
			else return -1;
		}
		return 0;
	}
}
