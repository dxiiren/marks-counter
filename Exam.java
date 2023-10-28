import java.util.ArrayList;
public class Exam
{
//declare attribute
      private String SubName,SubCode;
      private int CHour;
      private ArrayList<Double> part = new ArrayList<>(); //To Store mark of each part of each student - Array Declare

//method definition
//storer method
      public Exam(String sn, String sc, int ch)
      {
			SubName = sn;
			SubCode = sc;
			CHour = ch;
      }

      //Here we use variable argument to receive stream of input of same type from main
      //And also we use enhanced for loop to store data in arrylist
      public Exam( double...a)
      {
		     for(double b : a)
		        part.add(b);
	  }

//getter method
      public double getPart(int a)
      {
		    return part.get(a);

	  }

//process method
      public double countMark()           //a method to count mark from the attribute part
      {
		    double mark=0;
		    for(double a : part)
		    {  		mark+=a;   }

		    return mark;                  //return the total to the caller in main
	  }

//printer method
	  public void print()
	  {
		  System.out.println("\n************************************************");
		  System.out.println("\n\tSubject Name = " +SubName   );
		  System.out.println("\tSubject Code = "   +SubCode   );
		  System.out.println("\tCredit Hour = "    +CHour  +"\n");
		  System.out.println("************************************************");
	  }


}