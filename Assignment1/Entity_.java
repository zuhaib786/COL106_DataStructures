import java.util.Iterator;
import java.io.*;
public interface Entity_ { // Entities Classes Hostel, Dept, and Course all have this functionality.
   public String name();                 // Returns this  entitys name
   public Iterator< Node<Student> > studentList();        // Returns all students of this entity
};
