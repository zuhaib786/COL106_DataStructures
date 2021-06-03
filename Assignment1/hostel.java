import java.util.Iterator;
import java.io.*;

public class hostel implements Entity_
{
  private String name;
  private LinkedList<Student>  studentList=new LinkedList<Student> ();
  hostel(String name)
  {
    this.name=name;
  }
  public void addStudent(Student s)
  {
    studentList.add(s);
  }
  public String name()
  {
    return this.name;
  }
  public Iterator<Node<Student> > studentList()
  {
    return this.studentList.iterator();
  }
}
