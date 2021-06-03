import java.util.Iterator;
import java.io.*;
enum LetterGrade {
 A, Aminus, B, Bminus, C, Cminus, D, E, F, I;
  }
public class GradeInfo implements GradeInfo_
{
   LetterGrade grade;
   GradeInfo(LetterGrade grade)
   {
       this.grade=grade;
   }
   public int gradepoint()
   {
      int s=0;
       switch(this.grade)
       {
           case A:
               s= 10;
               break;
           case Aminus:
               s= 9;
               break;
           case B:
               s= 8;
               break;
           case Bminus:
               s= 7;
               break;
           case C:
               s= 6;
               break;
           case Cminus:
               s= 5;
               break;
           case D:
               s= 4;
               break;
           case E:
               s= 0;
               break;
           case F:
               s= 0;
               break;
           case I:
               s= 0;
               break;
       }
       return s;
     }
   }
