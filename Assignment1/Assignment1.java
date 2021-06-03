import java.util.Iterator;
import java.io.*; 	
public class Assignment1
{
  public static void SortedAdd(LinkedList<String> ll,String e)
  {
    Node<String> trav=ll.head;
    if(ll.count()==0)
    {
      ll.add(e);
      return;
    }
    if(isGreater(trav.val,e))
    {
      ll.add(e);
      return;
    }
    else
    {
      while(trav.next!=null)
      {
        if(isGreater(trav.next.val,e))
        {
          Node<String > temp=trav.next;
          Node<String> new_Node=new Node<String > (e);
          new_Node.next=temp;
          trav.next=new_Node;
          ll.changeCount();
          return ;
        }
        trav=trav.next;
      }
      if(trav.next==null)
      {
        Node<String > temp=new Node<String > (e);
        trav.next=temp;
        ll.changeCount();
        return ;
      }
    }
  }
  public static boolean isGreater(String s1,String s2)
  {
    int a=s1.length();
    if(a>s2.length())
    {
      a=s2.length();
    }
    for(int i=0;i<a;i++)
    {
      int a1=(int)s1.charAt(i);
      int a2=(int)s2.charAt(i);
      if(a1<a2)
      {
        return false;
      }
      else
      if(a1>a2)
      {
        return true;
      }
    }
    return s1.length()>=s2.length();
  }
  public static boolean isequal(String s1,String s2)
  {
    if(s1.length()!=s2.length())
    {
      return false;
    }
    else
    {
      for(int i=0;i<s2.length();i++)
      {
        int a1,a2;
        a1=(int)s1.charAt(i);
        a2=(int)s2.charAt(i);
        if(a1!=a2)
        {
          return false;
        }
      }
      return true;
    }
  }
  private static void getData(String filename1,String filename2,LinkedList<hostel>allHostel,LinkedList<department>allDepartments,LinkedList<course>allCourses,LinkedList<Student>Memory) throws java.io.IOException
  {
    File file=new File(filename1);
    try
    {
      String s;
      BufferedReader br=new BufferedReader(new FileReader(file));
      while((s=br.readLine())!=null)
      {
        Node<department>depNode=allDepartments.head;
        Node<hostel>hosNode=allHostel.head;
        String name="";
        String ent="";
        String depar="";
        String hos="";
        String temp="";
        for(int j=0;j<s.length();j++)
        {
          if(s.charAt(j)!=' ')
          {
            temp+=s.charAt(j);
          }
          else
          {
            if(ent=="")
            {
              ent=temp;
              temp="";
            }
            else
            if(name=="")
            {
              name=temp;
              temp="";
            }
            else
            if(depar=="")
            {
              depar=temp;
              temp="";
            }
          }
          hos=temp;
        }
        Student student=new Student(name,ent,hos,depar);
        boolean b=false;
        while(allDepartments.count()!=0&&depNode!=null)
        {
          if(isequal(depNode.val.name(),depar))
          {
            depNode.val.addStudent(student);
            b=true;
            break;
          }
          depNode=depNode.next;
        }
        if(!b)
        {
          department Ntemp=new department(depar);
          Ntemp.addStudent(student);
          allDepartments.add(Ntemp);
        }
        b=false;
        while(allHostel.count()!=0&&hosNode!=null)
        {
          if(isequal(hosNode.val.name(),hos))
          {
            hosNode.val.addStudent(student);
            b=true;
            break;
          }
          hosNode=hosNode.next;
        }
        if(!b)
        {
          hostel Ntemp=new hostel(hos);
          Ntemp.addStudent(student);
          allHostel.add(Ntemp);
        }
        Memory.add(student);
      }
    }//Student Record file
    catch(FileNotFoundException ex)
    {
      System.out.println("File Not found");
    }
    try
    {
      file=new File(filename2);
      String s;
      BufferedReader br=new BufferedReader(new FileReader(file));
      while( (s=br.readLine())!=null)
      {
        String cour="";
        String ent="";
        String gra="";
        String courID="";
        String temp="";
        for(int j=0;j<s.length();j++)
        {
          if(s.charAt(j)!=' ')
          {
            temp+=s.charAt(j);
          }
          else
          if(ent==""||courID==""||gra=="")
          {
            if(ent=="")
            {
              ent=temp;
              temp="";
            }
            else
            if(courID=="")
            {
              courID=temp;
              temp="";
            }
            else
            if(gra=="")
            {
              gra=temp;
              temp="";
            }
          }
          else
          {
            cour+=temp;
            temp="";
            cour+=" ";
          }
        }
        cour+=temp;
        Node<Student>memNode=Memory.head;
        String na="";String hos="";String depar="";
        while(allCourses.count()!=0 && memNode!=null)
        {
          if(isequal(memNode.val.entryNo(),ent))
          {
            memNode.val.GotCredits(cour,courID,gra);
            na=memNode.val.name();
            hos=memNode.val.hostel();
            depar=memNode.val.department();
            break;
          }
          memNode=memNode.next;
        }
        Node<course>cNode=allCourses.head;
        boolean b=false;
        Student student=new Student(na,ent,hos,depar);
        while(allCourses.count()!=0 && cNode!=null)
        {
          if(isequal(cNode.val.ID(),courID))
          {
            cNode.val.addStudent(student);
            b=true;
            break;
          }
          cNode=cNode.next;
        }
        if(!b)
        {
          course Ntemp=new course(courID,cour);
          Ntemp.addStudent(student);
          allCourses.add(Ntemp);
        }
      }
    }//course file;
    catch(FileNotFoundException  ex)
    {
      System.out.println("File Not Found");
    }
  }
  private static void answerQueries(String filename,LinkedList<department>allDepartments,LinkedList<course>allCourses,LinkedList<hostel>allHostel,LinkedList<Student>Memory)throws java.io.IOException
  {
    File file=new File(filename);
    try{//Query File
      BufferedReader br=new BufferedReader(new FileReader(file));
      LinkedList<String> QueryList=new LinkedList<String> ();
      String Q;//Strore Strings To be printed In reverrse order as answer for queries;
      while((Q=br.readLine())!=null)
      {
        if(Q.charAt(0)=='S')//QuerytypeShare
        {
            String en="";String qu="";String temp="";
            for(int i=6;i<Q.length();i++)
            {
              if(Q.charAt(i)!=' ')
              {
                temp+=Q.charAt(i);
              }
              else
              {
                if(en=="")
                {
                  en=temp;
                  temp="";
                }
              }
              qu=temp;
            }
            Node<Student>sNode=Memory.head;
            boolean found=false;
            while(sNode!=null)
            {
              if(isequal(sNode.val.entryNo(),en))
              {//Find the Node of the student
                if(isequal(sNode.val.hostel(),qu))
                {//Found that the query is about hostel common
                  found=true;
                  Node<hostel> hosNode=allHostel.head;
                  while(hosNode!=null)
                  {//Found which hostel does the student is Present and check in allHostel Linked List
                    if(isequal(hosNode.val.name(),qu))
                    {
                      break;
                    }
                    else
                    {
                      hosNode=hosNode.next;
                    }
                  }
                  Iterator<Node<Student> > stu=hosNode.val.studentList();
                  LinkedList<String> Store=new LinkedList<String> ();
                  while(stu.hasNext())
                  {
                    String j=stu.next().val.entryNo();
                    if(!isequal(j,en))
                    {
                      SortedAdd(Store,j);//Later implemet in Lexicographic order and Will print in reverse order of queries;
                    }
                  }
                  Iterator<Node<String> > i= Store.iterator();
                  int k=0;
                  String sa="";
                  while(i.hasNext())
                  {
                    if(k==Store.count()-1)
                    {
                      sa+=i.next().val;
                    }
                    else
                    {
                      sa+=i.next().val+" ";
                    }
                    k++;
                  }
                  QueryList.add(sa);//Saved IN Lexicographic oder in Query List
                }
                else
                if(isequal(sNode.val.department(),qu))
                {//Found the query is about department;
                  Node<department> depNode=allDepartments.head;
                  found=true;
                  while(depNode!=null)
                  {
                    if(isequal(depNode.val.name(),qu))
                    {//Found which department is it in allDepartmentsLinked List;
                      break;
                    }
                    else
                    {
                      depNode=depNode.next;
                    }
                  }
                  Iterator<Node<Student> > stu=depNode.val.studentList();
                  LinkedList<String>Store=new LinkedList<String> ();
                  while(stu.hasNext())
                  {
                    String j=stu.next().val.entryNo();
                    if(!isequal(j,en))
                    {
                      SortedAdd(Store,j);
                    }
                  }
                  Iterator<Node<String> >i=Store.iterator();
                  int j=0;
                  String sa="";
                  while(i.hasNext())
                  {
                    if(j==Store.count()-1)
                    {
                      sa+=i.next().val;
                    }
                    else
                    {
                      sa+=i.next().val+" ";
                    }
                    j++;
                  }
                  QueryList.add(sa);
                }
              }
              sNode=sNode.next;
            }
            if(!found)
            {
              //The query is about Students Course;
              Node<course> cNode=allCourses.head;
              while(cNode!=null)
              {
                if(isequal(cNode.val.ID(),qu))
                {
                  break;
                }
                else
                {
                  cNode=cNode.next;
                }
              }
              Iterator<Node<Student> > stu=cNode.val.studentList();
              LinkedList<String> Store=new LinkedList<String> ();
              while(stu.hasNext())
              {
                String j=stu.next().val.entryNo();
                if(!isequal(j,en))
                {
                  SortedAdd(Store,j);
                }
              }
              Iterator<Node<String> >i=Store.iterator();
              int j=0;
              String sa="";
              while(i.hasNext())
              {
                if(j==Store.count()-1)
                {
                  sa+=i.next().val;
                }
                else
                {
                  sa+=i.next().val+" ";
                }
                j++;
              }
              QueryList.add(sa);
            }
        }
        else
        if(Q.charAt(0)=='I')//QueryType INfo
        {
          String en="";
          for(int i=5;i<Q.length();i++)
          {
            en+=Q.charAt(i);
          }
          Node<Student>sNode=Memory.head;
          while(sNode!=null)
          {
            if(isequal(sNode.val.entryNo(),en))
            {
              break;
            }
            else
            {
              sNode=sNode.next;
            }
          }
          String sa="";
          sa+=en+" "+sNode.val.name()+" "+sNode.val.department()+" "+sNode.val.hostel()+" "+sNode.val.cgpa()+" ";

          Iterator<Node<CourseGrade>> cgtuple=sNode.val.courselist();
          LinkedList<String>Store=new LinkedList<String>();
          while(cgtuple.hasNext())
          {
            Node<CourseGrade> cg=cgtuple.next();
            //System.out.print(cg.entryNo()+" "+)
            SortedAdd(Store,cg.val.coursenum()+" "+cg.val.LetG());
          }
          int j=0;
          Iterator<Node<String>> i=Store.iterator();
          while(i.hasNext())
          {
            if(j==Store.count()-1)
            {
              sa+=i.next().val;
            }
            else
            {
              sa+=i.next().val+" ";
            }
            j++;
          }
          QueryList.add(sa);
        }
        else
        if(Q.charAt(0)=='C') //QueryType CourseTitle
        {
          String CID="";
          for(int i=12;i<Q.length();i++)
          {
            CID+=Q.charAt(i);
          }
          Node<course> cNode=allCourses.head;
          while(cNode!=null)
          {
            if(isequal(cNode.val.ID(),CID))
            {
              break;
            }
            else
            {
              cNode=cNode.next;
            }
          }
          if(cNode!=null)
          {
            QueryList.add(cNode.val.name());
          }
        }
      }
      Iterator<Node<String> > i=QueryList.iterator();
      while(i.hasNext())
      {
        System.out.println(i.next().val);
      }
    }
    catch(FileNotFoundException e)
    {
      System.out.println("File Not found");
    }
  }
  public static void main(String [] args)
  {
    LinkedList <hostel>allHostel=new LinkedList<hostel> ();
    LinkedList<department>allDepartments=new LinkedList<department> ();
    LinkedList<course>allCourses=new LinkedList<course> ();
    LinkedList<Student>Memory=new LinkedList<Student>();
    try{
      getData(args[0],args[1],allHostel,allDepartments,allCourses,Memory);
    }
    catch(IOException e)
    {
      System.out.println("File not found");
    }
    try
    {
      answerQueries(args[2],allDepartments,allCourses,allHostel,Memory);
    }
    catch(IOException e)
    {
      System.out.println("File not found");
    }
  }
}
