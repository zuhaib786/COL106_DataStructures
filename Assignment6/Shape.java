public class Shape implements ShapeInterface
{
	public Tuple OneSourceMaxima(Triangle T)
	{
		Queue<Triangle>Q=new Queue<>();
		Q.insert(T);
		int max=-1;
		while(Q.size>0)
		{
			T=Q.delete();
			T.isVisited=true;
			for(int i=0;i<T.AdjTriangles.size();i++)
			{
				if(T.AdjTriangles.get(i).isVisited)
				{
					continue;
				}
				else
				if(T.AdjTriangles.get(i).dis==-1)
				{
					T.AdjTriangles.get(i).dis=T.dis+1;
					Q.insert(T.AdjTriangles.get(i));
				}
				else if(T.AdjTriangles.get(i).dis>T.dis+1)
				{
					T.AdjTriangles.get(i).dis=T.dis+1;
					Q.insert(T.AdjTriangles.get(i));
				}
			}
		}
		int num=0;
		for(int i=0;i<D.size();i++)
		{
			if(D.get(i).dis!=-1)
			{
				num+=1;
			}
			if(D.get(i).dis>max)
			{
				max=D.get(i).dis;
			}
			D.get(i).dis=-1;
			D.get(i).isVisited=false;
		}
		Tuple m=new Tuple(2);
		m.insert(num);
		m.insert(max);
		return m;
	}
	public DynamicArray<Triangle>D=new DynamicArray<>();;
	public RBTree<Point,Point>Points=new RBTree<>();
	public RBTree<Edge,Edge>Edges=new RBTree<>();
	public RBTree<Triangle,Triangle>Triangles=new RBTree<>();
	public DynamicArray<Edge> BoundaryEdges=new DynamicArray<>();
	public int Num1Edges=0;
	public int NumG2Edges=0;
	public DisjointSet<Triangle>DS=new DisjointSet<>();
	public static void Add(float[]V1,float[]V2)
	{
		for(int i=0;i<3;i++)
		{
			V1[i]+=V2[i];
		}
	}
	@Override
	public boolean ADD_TRIANGLE(float[]TC )
	{
		float x_1=TC[0],y_1=TC[1],z_1=TC[2],x_2=TC[3],y_2=TC[4],z_2=TC[5],x_3=TC[6],y_3=TC[7],z_3=TC[8];
		Point P1=new Point(x_1,y_1,z_1);
		Point P2=new Point(x_2,y_2,z_2);
		Point P3=new Point(x_3,y_3,z_3);
		RedBlackNode<Point,Point>P=Points.search(P1);
		if(P.getValues()==null)
		{
			Points.insert(P1, P1);
		}
		else
		{
			P1=P.getValue();
		}
		P=Points.search(P2);
		if(P.getValues()==null)
		{
			Points.insert(P2, P2);
		}
		else
		{
			P2=P.getValue();
		}
		P=Points.search(P3);
		if(P.getValues()==null)
		{
			Points.insert(P3, P3);
		}
		else
		{
			P3=P.getValue();
		}
		Edge E1=new Edge(P1,P2);
		Edge E2=new Edge(P2,P3);
		Edge E3=new Edge(P1,P3);
		Triangle Z=new Triangle(E1,E2,E3);
		if(!Z.isValid())
		{
			return false;
		}
		RedBlackNode<Edge,Edge>E=Edges.search(E1);
		if(E.getValues()==null)
		{
			Edges.insert(E1, E1);
			BoundaryEdges.insert(E1);
			Num1Edges+=1;
			P1.AdjPoints.insert(P2, P2);
			P2.AdjPoints.insert(P1,P1);
		}
		else
		{
			E1=E.getValue();
			if(E1.AjdTr.size()==1)
			{
				Num1Edges-=1;
				for(int i=0;i<BoundaryEdges.size();i++)
				{
					if(BoundaryEdges.get(i).compareTo(E1)==0)
					{
						BoundaryEdges.Delete(i);
						break;
					}
				}
			}
		}
		E=Edges.search(E2);
		if(E.getValues()==null)
		{
			Edges.insert(E2, E2);
			Num1Edges+=1;
			BoundaryEdges.insert(E2);
			P3.AdjPoints.insert(P2, P2);
			P2.AdjPoints.insert(P3,P3);
		}
		else
		{
			E2=E.getValue();
			if(E2.AjdTr.size()==1)
			{
				Num1Edges-=1;
				for(int i=0;i<BoundaryEdges.size();i++)
				{
					if(BoundaryEdges.get(i).compareTo(E2)==0)
					{
						BoundaryEdges.Delete(i);
						break;
					}
				}
			}
		}
		E=Edges.search(E3);
		if (E.getValues()==null)
		{
			Edges.insert(E3, E3);
			BoundaryEdges.insert(E3);
			P1.AdjPoints.insert(P3, P3);
			P3.AdjPoints.insert(P1,P1);
			Num1Edges+=1;
		}
		else
		{
			E3=E.getValue();
			if(E3.AjdTr.size()==1)
			{
				for(int i=0;i<BoundaryEdges.size();i++)
				{
					if(BoundaryEdges.get(i).compareTo(E3)==0)
					{
						BoundaryEdges.Delete(i);
						break;
					}
				}
				Num1Edges-=1;
			}
		}
		Triangle T=new Triangle(E1,E2,E3);
		if(T.isValid())
		{
			DS.insert(T);
			D.insert(T);
			P1.Triangle.insert(T);
			P2.Triangle.insert(T);
			P3.Triangle.insert(T);
			T.NumTr=Triangles.size;
			Triangles.insert(T, T);
			E1.AjdTr.insert(T);
			E2.AjdTr.insert(T);
			E3.AjdTr.insert(T);
			if(E1.AjdTr.size()>=3)
			{
				NumG2Edges+=1;
			}
			if(E2.AjdTr.size()>=3)
			{
				NumG2Edges+=1;
			}
			if(E3.AjdTr.size()>=3)
			{
				NumG2Edges+=1;
			}
			for(int i=0;i<E1.AjdTr.size()-1;i++)
			{
				T.AdjTriangles.insert(E1.AjdTr.get(i));
				E1.AjdTr.get(i).AdjTriangles.insert(T);
				DS.Union(T.NumTr, E1.AjdTr.get(i).NumTr);
			}
			for(int i=0;i<E2.AjdTr.size()-1;i++)
			{
				T.AdjTriangles.insert(E2.AjdTr.get(i));
				E2.AjdTr.get(i).AdjTriangles.insert(T);
				DS.Union(T.NumTr, E2.AjdTr.get(i).NumTr);
			}
			for(int i=0;i<E3.AjdTr.size()-1;i++)
			{
				T.AdjTriangles.insert(E3.AjdTr.get(i));
				E3.AjdTr.get(i).AdjTriangles.insert(T);
				DS.Union(T.NumTr, E3.AjdTr.get(i).NumTr);
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	@Override
	public int TYPE_MESH()
	{
		if(NumG2Edges!=0)
		{
			return 3;
		}
		else if(Num1Edges!=0)
		{
			return 2;
		}
		return 1;
	}
	@Override
	public EdgeInterface [] BOUNDARY_EDGES()
	{
		if(BoundaryEdges.size()==0)return null;
		DynamicArray<Tuple>DA=new DynamicArray<Tuple>();
		EdgeInterface[]result=new EdgeInterface[BoundaryEdges.size()];
		for(int i=0;i<BoundaryEdges.size();i++)
		{
			Tuple Z=new Tuple(2);
			Z.insert(BoundaryEdges.get(i).length_sq);
			Z.insert(BoundaryEdges.get(i));
			DA.insert(Z);
		}
		BinaryMaxHeap<Tuple>MH=new BinaryMaxHeap<>();
		DA=MH.HeapSort(DA);
		for(int i=0;i<DA.size();i++)
		{
			result[i]=(EdgeInterface)DA.get(i).get(1);
		}
		
		return result;
	}
	@Override
	public int COUNT_CONNECTED_COMPONENTS()
	{
//		for(int i=0;i<D.size();i++)
//		{
//			System.out.println(D.get(i)+" "+D.get(i).AdjTriangles);
//		}
		return DS.getComp();
	}
	@Override
	public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2)
	{
		float[]TC=triangle_coord_1;
		float x_1=TC[0],y_1=TC[1],z_1=TC[2],x_2=TC[3],y_2=TC[4],z_2=TC[5],x_3=TC[6],y_3=TC[7],z_3=TC[8];
		Point P1=new Point(x_1,y_1,z_1);
		Point P2=new Point(x_2,y_2,z_2);
		Point P3=new Point(x_3,y_3,z_3);
		Edge E1=new Edge(P1,P2);
		Edge E2=new Edge(P2,P3);
		Edge E3=new Edge(P1,P3);
		Triangle T1=new Triangle(E1,E2,E3);
		TC=triangle_coord_2;
		 x_1=TC[0];y_1=TC[1];z_1=TC[2];x_2=TC[3];y_2=TC[4];z_2=TC[5];x_3=TC[6];y_3=TC[7];z_3=TC[8];
		 P1=new Point(x_1,y_1,z_1);
		 P2=new Point(x_2,y_2,z_2);
		 P3=new Point(x_3,y_3,z_3);
		 E1=new Edge(P1,P2);
		 E2=new Edge(P2,P3);
		 E3=new Edge(P1,P3);
		 Triangle T2=new Triangle(E1,E2,E3);
		 RedBlackNode<Triangle,Triangle>R1=Triangles.search(T1);
		 RedBlackNode<Triangle,Triangle>R2=Triangles.search(T2);
		 if(R1.getValues()==null ||R2.getValues()==null)
		 {
			 return false;
		 }
		 else
		 {
			 T1=R1.getValue();
			 T2=R2.getValue();
			 if(DS.Find(T1.NumTr)==DS.Find(T2.NumTr))
			 {
				 return true;
			 }
			 return false;
		 }
	}
	@Override
	public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] TC)
	{//Sorted on the basis of time of Arrival
		float x_1=TC[0],y_1=TC[1],z_1=TC[2],x_2=TC[3],y_2=TC[4],z_2=TC[5],x_3=TC[6],y_3=TC[7],z_3=TC[8];
		Point P1=new Point(x_1,y_1,z_1);
		Point P2=new Point(x_2,y_2,z_2);
		Point P3=new Point(x_3,y_3,z_3);
		Edge E1=new Edge(P1,P2);
		Edge E2=new Edge(P2,P3);
		Edge E3=new Edge(P1,P3);
		Triangle T=new Triangle(E1,E2,E3);
		RedBlackNode<Triangle,Triangle>TT=Triangles.search(T);
		if(TT.getValues()==null)
		{
			return null;
		}
		T=TT.getValue();
		TriangleInterface[]result=new TriangleInterface[T.AdjTriangles.size()];
		for(int i=0;i<T.AdjTriangles.size();i++)
		{
			result[i]=T.AdjTriangles.get(i);
		}
		return result;
	}
	@Override 
	public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] TC)
	{//No sorting
		float x_1=TC[0],y_1=TC[1],z_1=TC[2],x_2=TC[3],y_2=TC[4],z_2=TC[5],x_3=TC[6],y_3=TC[7],z_3=TC[8];
		Point P1=new Point(x_1,y_1,z_1);
		Point P2=new Point(x_2,y_2,z_2);
		Point P3=new Point(x_3,y_3,z_3);
		Edge E1=new Edge(P1,P2);
		Edge E2=new Edge(P2,P3);
		Edge E3=new Edge(P1,P3);
		Triangle T=new Triangle(E1,E2,E3);
		RedBlackNode<Triangle,Triangle>TT=Triangles.search(T);
		if(TT.getValues()==null)
		{
			return null;
		}
		else
		{
			return TT.getValue().getEdges();
		}
	}
	@Override
	public PointInterface[] VERTEX_NEIGHBOR_TRIANGLE(float [] TC)
	{
		float x_1=TC[0],y_1=TC[1],z_1=TC[2],x_2=TC[3],y_2=TC[4],z_2=TC[5],x_3=TC[6],y_3=TC[7],z_3=TC[8];
		Point P1=new Point(x_1,y_1,z_1);
		Point P2=new Point(x_2,y_2,z_2);
		Point P3=new Point(x_3,y_3,z_3);
		Edge E1=new Edge(P1,P2);
		Edge E2=new Edge(P2,P3);
		Edge E3=new Edge(P1,P3);
		Triangle T=new Triangle(E1,E2,E3);
		RedBlackNode<Triangle,Triangle>TT=Triangles.search(T);
		if(TT.getValues()==null)
		{
			return null;
		}
		else
		{
			return TT.getValue().triangle_coord();
		}
	}
	@Override
	public TriangleInterface[] EXTENDED_NEIGHBOR_TRIANGLE(float [] TC)
	{
		float x_1=TC[0],y_1=TC[1],z_1=TC[2],x_2=TC[3],y_2=TC[4],z_2=TC[5],x_3=TC[6],y_3=TC[7],z_3=TC[8];
		Point P1=new Point(x_1,y_1,z_1);
		Point P2=new Point(x_2,y_2,z_2);
		Point P3=new Point(x_3,y_3,z_3);
		Edge E1=new Edge(P1,P2);
		Edge E2=new Edge(P2,P3);
		Edge E3=new Edge(P1,P3);
		Triangle T=new Triangle(E1,E2,E3);
		RedBlackNode<Triangle,Triangle>TT=Triangles.search(T);
		if(TT.getValues()==null)
		{
			return null;
		}
		P1=Points.search(P1).getValue();
		P2=Points.search(P2).getValue();
		P3=Points.search(P3).getValue();
		DynamicArray<Tuple>D=new DynamicArray<>(P1.Triangle.size()+P2.Triangle.size()+P3.Triangle.size());
		for(int i=0;i<P1.Triangle.size();i++)
		{
			if(P1.Triangle.get(i).compareTo(TT.getValue())!=0)
			{
				Tuple Z=new Tuple(2);
				Z.insert(P1.Triangle.get(i).NumTr);
				Z.insert(P1.Triangle.get(i));
				D.insert(Z);
			}
		}
		for(int i=0;i<P2.Triangle.size();i++)
		{
			if(P2.Triangle.get(i).compareTo(TT.getValue())!=0)
			{
				Tuple Z=new Tuple(2);
				Z.insert(P2.Triangle.get(i).NumTr);
				Z.insert(P2.Triangle.get(i));
				D.insert(Z);
			}
		}
		for(int i=0;i<P3.Triangle.size();i++)
		{
			if(P3.Triangle.get(i).compareTo(TT.getValue())!=0)
			{
				Tuple Z=new Tuple(2);
				Z.insert(P3.Triangle.get(i).NumTr);
				Z.insert(P3.Triangle.get(i));
				D.insert(Z);
			}
		}
		BinaryMaxHeap<Tuple>MH=new BinaryMaxHeap<Tuple>();
		D=MH.HeapSort(D);
		DynamicArray<Triangle>K=new DynamicArray<Triangle>();
		int prev=-1;
		for(int i=0;i<D.size();i++)
		{
			if((Integer)D.get(i).get(0)!=prev)
			{
				prev=(Integer)D.get(i).get(0);
				K.insert((Triangle)D.get(i).get(1));
			}
		}
		if(K.size()==0)
		{
			return null;
		}
		TriangleInterface[]res=new TriangleInterface[K.size()];
		for(int i=0;i<K.size();i++)
		{
			res[i]=K.get(i);
		}
		return res;
	}
	@Override
	public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates)
	{
		Point P=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		RedBlackNode<Point,Point>PP=Points.search(P);
		if(PP.getValues()==null||PP.getValue().Triangle.size()==0)return null;
		else
		{
			P=PP.getValue();
			TriangleInterface[]res=new TriangleInterface[P.Triangle.size()];
			for(int i=0;i<P.Triangle.size();i++)
			{
				res[i]=P.Triangle.get(i);
			}
			return res;
		}
	}
	@Override
	public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates)
	{
		Point P=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		RedBlackNode<Point,Point>PP=Points.search(P);
		if(PP.getValues()==null||PP.getValue().Triangle.size()==0)return null;
		else
		{
			P=PP.getValue();
			TriangleInterface[]res=new TriangleInterface[P.Triangle.size()];
			for(int i=0;i<P.Triangle.size();i++)
			{
				res[i]=P.Triangle.get(i);
			}
			return res;
		}
	}
	@Override
	public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates)
	 {
		Point P1=new Point(edge_coordinates[0],edge_coordinates[1],edge_coordinates[2]);
		Point P2=new Point(edge_coordinates[3],edge_coordinates[4],edge_coordinates[5]);
		Edge E=new Edge(P1,P2);
		RedBlackNode<Edge,Edge>EE=Edges.search(E);
		if(EE.getValues()==null||EE.getValue().AjdTr.size()==0)
		{
			return null;
		}
		E=EE.getValue();
		TriangleInterface[]res=new TriangleInterface[E.AjdTr.size()];
		for(int i=0;i<E.AjdTr.size();i++)
		{
			res[i]=E.AjdTr.get(i);
		}
		return res;
	 }
	public int Calc(DynamicArray<Integer>D)
	{
		MHash m=new MHash();
		for(int i=0;i<D.size();i++)
		{
			m.insert(D.get(i), 1);
		}
		return m.getCount();
	}
	public PointInterface [] CENTROID (){
//		DynamicArray<float[]>Centroids=new DynamicArray<>(DS.getComp());
//		DynamicArray<DynamicArray<Integer>>Point_Cnt=new DynamicArray<>(DS.getComp());
//		RBTree<Integer,Integer>Map=new RBTree<>();
//		for(int i=0;i<DS.Parent.size();i++)
//		{
//			Integer I=DS.Find(i);
//			RedBlackNode<Integer,Integer>RB=Map.search(I);
//			if(RB.getValues()==null)
//			{
//				Map.insert(I, Map.size);
//				float[]res=new float[3];
//				for(int k=0;k<3;k++)
//				{
//					res[k]=0.0f;
//				}
//				Triangle T=DS.value.get(i);
//				DynamicArray<Integer>D=new DynamicArray<>();
//				D.insert(T.E1.AjdTr.size());
//				D.insert(T.E2.AjdTr.size());
//				D.insert(T.E3.AjdTr.size());
//				Point_Cnt.insert(D);
//				Shape.Add(res,DS.value.get(i).Centroid_Calc());
//				Centroids.insert(res);
//			}
//			else
//			{
//				Triangle T=DS.value.get(i);
//				Point_Cnt.get(RB.getValue()).insert(T.E1.AjdTr.size());
//				Point_Cnt.get(RB.getValue()).insert(T.E1.AjdTr.size());
//				Point_Cnt.get(RB.getValue()).insert(T.E1.AjdTr.size());
//				Shape.Add(Centroids.get(RB.getValue()), DS.value.get(i).Centroid_Calc());
//			}
//		}
//		DynamicArray<Integer>P=new DynamicArray<Integer>();
//		for(int i=0;i<Point_Cnt.size();i++)
//		{
//			P.insert(Calc(Point_Cnt.get(i)));
//		}
//		for(int i=0;i<Centroids.size();i++)
//		{
//		}
//		PointInterface[]res=new PointInterface[DS.getComp()];
//		for(int i=0;i<DS.getComp();i++)
//		{
//			float X1=Centroids.get(i)[0]/(2*P.get(i));
//			float Y1=Centroids.get(i)[1]/(2*P.get(i));
//			float Z1=Centroids.get(i)[2]/(2*P.get(i));
//			Point Z=new Point(X1,Y1,Z1);
//			res[i]=Z;
//		}//Will debug later on
//		return res;
		//This thing was my first implementation. It was incorrect;;;;;
		DynamicArray<RBTree<Point,Point>>DA=new DynamicArray<>();
		RBTree<Integer,Integer>RB=new RBTree<>();
		for(int i=0;i<DS.Parent.size();i++)
		{
			int I=DS.Find(i);
			RedBlackNode<Integer,Integer>X=RB.search(I);
			if(X.getValues()==null)
			{
				DA.insert(new RBTree<>());
				RB.insert(I, DA.size()-1);
				I=DA.size()-1;
			}
			else
			{
				I=X.getValue();
			}
			Triangle T=DS.value.get(i);
			PointInterface[] P=T.triangle_coord();
			Point P1=(Point)P[0];
			DA.get(I).insert(P1,P1);
			P1=(Point)P[1];
			DA.get(I).insert(P1,P1);
			P1=(Point)P[2];
			DA.get(I).insert(P1,P1);
		}
		DynamicArray<DynamicArray<Point>>DS=new DynamicArray<>();
		for(int i=0;i<DA.size();i++)
		{
			DynamicArray<Point>KD=new DynamicArray<>();
			DA.get(i).RBTree_to_Array(KD);
			DS.insert(KD);
		}
		DynamicArray<Point>AD=new DynamicArray<>();
		for(int i=0;i<DS.size();i++)
		{
			AD.insert(Zuhaib(DS.get(i)));
		}
		BinaryMaxHeap<Point>BM=new BinaryMaxHeap<Point>();
		AD=BM.HeapSort(AD);
		PointInterface[]KP=new PointInterface[AD.size()];
		for(int i=0;i<KP.length;i++)
		{
			KP[i]=AD.get(i);
		}
		return KP;
	}
	@Override
	 public PointInterface CENTROID_OF_COMPONENT(float[]TC)
	 {
		Point P=new Point(TC[0],TC[1],TC[2]);
		RedBlackNode<Point,Point>PP=Points.search(P);
		if(PP.getValues()==null)
		{
			return null;
		}
		P=PP.getValue();
		Triangle T=P.Triangle.get(0);
		RBTree<Point,Point>DA=new RBTree<>();
//		RBTree<Integer,Integer>RB=new RBTree<>();
		int J=DS.Find(T.NumTr);
		for(int i=0;i<DS.Parent.size();i++)
		{
			int I=DS.Find(i);
			if(J==I)
			{
				PointInterface[]M=DS.value.get(i).triangle_coord();
				Point P1=(Point)M[0];
				DA.insert(P1, P1);
				P1=(Point)M[1];
				DA.insert(P1, P1);
				P1=(Point)M[2];
				DA.insert(P1, P1);
			}
		}
		DynamicArray<Point>DJ=new DynamicArray<>();
		DA.RBTree_to_Array(DJ);
		float[]res=new float[3];
		for(int i=0;i<3;i++)
		{
			res[0]=0.0f;
		}
		for(int i=0;i<DJ.size();i++)
		{
			Shape.Add(res,DJ.get(i).getXYZcoordinate());
		}
		res[0]/=DJ.size();
		res[1]/=DJ.size();
		res[2]/=DJ.size();
		return new Point(res[0],res[1],res[2]);
	 }
	public Point Zuhaib(DynamicArray<Point>DS)
	{
		float[] res=new float[3];
		for(int i=0;i<3;i++)
		{
			res[i]=0.0f;
		}
		for(int i=0;i<DS.size();i++)
		{
			Shape.Add(res,DS.get(i).getXYZcoordinate());
		}
		res[0]=res[0]/DS.size();
		res[1]=res[1]/DS.size();
		res[2]=res[2]/DS.size();
		return new Point(res[0],res[1],res[2]);
	}
	@Override
	public int MAXIMUM_DIAMETER()
	{
		Tuple max=new Tuple(2);
		max.insert(-1);
		max.insert(0);
		for(int i=0;i<D.size();i++)
		{
			D.get(i).dis=0;
			Tuple j=OneSourceMaxima(D.get(i));
			if(j.compareTo(max)>0)
			{
				max=j;
			}
		}
		return (Integer)max.get(1);
	}
	@Override
	public PointInterface[]CLOSEST_COMPONENTS()
	{
		DynamicArray<RBTree<Point,Point>>DA=new DynamicArray<>();
		RBTree<Integer,Integer>RB=new RBTree<>();
		for(int i=0;i<DS.Parent.size();i++)
		{
			int I=DS.Find(i);
			RedBlackNode<Integer,Integer>X=RB.search(I);
			if(X.getValues()==null)
			{
				DA.insert(new RBTree<>());
				RB.insert(I, DA.size()-1);
				I=DA.size()-1;
			}
			else
			{
				I=X.getValue();
			}
			Triangle T=DS.value.get(i);
			PointInterface[] P=T.triangle_coord();
			Point P1=(Point)P[0];
			DA.get(I).insert(P1,P1);
			P1=(Point)P[1];
			DA.get(I).insert(P1,P1);
			P1=(Point)P[2];
			DA.get(I).insert(P1,P1);
		}
		DynamicArray<DynamicArray<Point>>DSK=new DynamicArray<>();
		for(int i=0;i<DA.size();i++)
		{
			DynamicArray<Point>KD=new DynamicArray<>();
			DA.get(i).RBTree_to_Array(KD);
			DSK.insert(KD);
		}
		float min=-1;
		Tuple store=null;
		for(int i=0;i<DSK.size();i++)
		{
			for(int j=i+1;j<DSK.size();j++)
			{
				Tuple z=ClosestDist(DSK.get(i),DSK.get(j));
				if(min==-1)
				{
					min=(float)z.get(0);
					store=z;
				}
				else if(min-(float)z.get(0)>=0.001)
				{
					min=(float)z.get(0);
					store=z;
				}
			}
		}
		if(store==null)
		{
			return null;
		}
		PointInterface[]P=new PointInterface[2];
		P[0]=(PointInterface)store.get(1);
		P[1]=(PointInterface)store.get(2);
		return P;
	}
	Tuple ClosestDist(DynamicArray<Point>D1,DynamicArray<Point>D2)
	{
		float min=-1;
		Tuple store=null;
		for(int i=0;i<D1.size();i++)
		{
			for(int j=0;j<D2.size();j++)
			{
				Point P1=D1.get(i),P2=D2.get(j);
				float dis=(P1.getX()-P2.getX())*(P1.getX()-P2.getX())+(P1.getY()-P2.getY())*(P1.getY()-P2.getY())+(P1.getZ()-P2.getZ())*(P1.getZ()-P2.getZ());
				if(min==-1)
				{
					min=dis;
					store=new Tuple(3);
					store.insert(min);
					store.insert(P1);
					store.insert(P2);
				}
				else if(min-dis>0.001)
				{
					min=dis;
					store=new Tuple(3);
					store.insert(min);
					store.insert(P1);
					store.insert(P2);
				}
			}
		}return store;
	}
	@Override 
	public PointInterface [] NEIGHBORS_OF_POINT(float [] PC)
	{
		Point P=new Point(PC[0],PC[1],PC[2]);
		RedBlackNode<Point,Point> RB=Points.search(P);
		if(RB.getValues()==null)
		{
			return null;
		}
		else
		{
			P=RB.getValue();
			PointInterface[]res=new PointInterface[P.AdjPoints.size];
			DynamicArray<Point>PD=new DynamicArray<>();
			P.AdjPoints.RBTree_to_Array(PD);
			for(int i=0;i<PD.size();i++)
			{
				res[i]=PD.get(i);
			}
			return res;
		}
	}
	@Override
	public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] PC)
	{
		Point P=new Point(PC[0],PC[1],PC[2]);
		RedBlackNode<Point,Point>PP=Points.search(P);
		if(PP.getValues()==null)
		{
			return null;
		}
		P=PP.getValue();
		EdgeInterface[]res=new EdgeInterface[P.AdjPoints.size];
		DynamicArray<Point>PD=new DynamicArray<Point>();
		P.AdjPoints.RBTree_to_Array(PD);
		for(int i=0;i<PD.size();i++)
		{
			res[i]=new Edge(P,PD.get(i));
		}
		return res;
	}
}