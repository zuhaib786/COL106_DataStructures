import java.util.Iterator;
import java.io.*;
public class course implements Entity_
{
  private String name;
  private String ID;
  private LinkedList<Student>  studentList=new LinkedList<Student> ();
  course(String ID,String name )
  {
    this.name=name;
    this.ID=ID;
  }
  public void addStudent(Student s)
  {
    studentList.add(s);
  }
  public String name()
  {
    return this.name;
  }
  public String ID()
  {
    return this.ID;
  }
  public Iterator<Node<Student> > studentList()
  {
    return this.studentList.iterator();
  }
}
