public class Student
{
 //declare attribute
     private String name,id,className;
     private char gender;
     private double mark,carryMark;

 //method definition
 //storer method
     public Student(){}

	 public Student(String n, String i, String c, char g, double m ,double cm)
     {
			name = n;
			id = i;
			className = c;
			gender = g;
			mark = m;
			carryMark = cm;
     }

//getter method
     public double getMark(){return mark;}
     public String getName(){return name;}
     public double getcarryMark(){return carryMark;}

//processor method
     public boolean isInputID(String i)          //method to determine wether the id given
     {                                           //is exist on attribute id or not
		 if(id.equals(i))
		     return true;
		 else
		     return false;
     }

//printer method
     public void print()
     {
		 System.out.println("1 - Name = " + name);
		 System.out.println("2 - ID = "     + id);
		 System.out.println("3 - Class = "  + className);
		 System.out.println("4 - Gender = " + gender);
		 System.out.println("5 - Mark = "   + mark);
	 }

}