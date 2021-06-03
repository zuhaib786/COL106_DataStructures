import java.util.Iterator;
import java.io.*;
public class Student
{
    private String name;
    private String  entryNo;
    private String hostel;
    private String department;
    private double performance=0.0;
    private int  completedCredits=0;
    public int dummycc=0;
    private double cgpa=0.0;
    private LinkedList<CourseGrade> courselist;
    Student(String name,String entryNo,String hostel,String department)
    {
        this.name=name;
        this.entryNo=entryNo;
        this.hostel=hostel;
        this.department=department;
        this.courselist=new LinkedList<CourseGrade> ();
    }
    public String name()
    {
        return this.name;
    }
    public String entryNo()
    {
        return this.entryNo;
    }
    public String department()
    {
        return this.department;
    }
    public String completedCredits()
    {
        return Integer.toString(this.completedCredits);
    }
    public String cgpa()
    {
      String k=Double.toString(this.cgpa);
      String em="";
      int i;
      for( i=0;i<k.length();i++)
      {
        em+=k.charAt(i);
        if(k.charAt(i)=='.')
        {
          break;
        }
      }
      em+=k.charAt(i+1);
      if(k.length()-i>3)
      {
        if((int)k.charAt(i+3)-(int)'0'>=5)
        {
          int j=(int)k.charAt(i+2)+1;
          em+=(char)j;
        }
        else
        {
          em+=k.charAt(i+2);
        }
      }
      else
      {
        if(i+2<k.length())
        {
          em+=k.charAt(i);
        }
      }
        return em;
    }
    public String hostel()
    {
      return this.hostel;
    }
    public void GotCredits(String CourseName,String Coursenum,String Grade)
    {
        CourseGrade temp=new CourseGrade(CourseName,Coursenum,Grade);
        courselist.add(temp);
        int k=temp.gradepoint();
        dummycc+=3;
        if(!(Grade=="E"||Grade=="F"||Grade=="I"))
        {
          completedCredits+=3;
        }
        if(Grade!="I")
        {
          performance+=3*k;
        }
        cgpa=performance/dummycc;
    }
    public Iterator<Node<CourseGrade> > courselist()
    {
      return courselist.iterator();
    }
}
