import java.util.Iterator;
import java.io.*;
public class CourseGrade
{
    private String coursetitle;
    private String coursenum;
    private GradeInfo grade;
    private int gradepoint;
    private String g;
    CourseGrade(String coursetitle,String coursenum,String g)
    {
        this.grade=new GradeInfo(LetterGrade.valueOf(g));
        this.coursetitle=coursetitle;
        this.coursenum=coursenum;
        this.g=g;
    }
    public int gradepoint()
    {
      return grade.gradepoint();
    }
    public String coursetitle()
    {
        return this.coursetitle;
    }
    public String coursenum()
    {
        return this.coursenum;
    }
    public GradeInfo grade()
    {
        return this.grade;
    }
    public String LetG()
    {
      return this.g;
    }
}
