import java.util.Iterator;
import java.io.*;
public interface CourseGrade_ {		// Tuple of course and grade
   public String coursetitle();   	 // Returns course title
   public String coursenum();   	 // Returns course code, e.g., COL106
   public GradeInfo grade();   	 // Returns student's letter grade
};
