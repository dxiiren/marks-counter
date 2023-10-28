import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

class MarkingMarkApp
{
 /* Programmer        : Muhd Akmal Ahmad Suhaimi, Adam Taufiq Shahrul Affendi, Tuan Nur Afrina Zahira Tuan Zainuddin, Amir Hariz Azimin
	Date              : 10/05/2021
	Description       : This program takes in how many students the user wants to input, Determine how many part of the question have,
	Totalling the whole mark of each part and sum up the total mark for each student, along with the percentage of the carry mark.
	The system also will determine which student who get the highest and the lowest mark for the test and also the average mark for all student.
	Finally, User will able to search a particular student based on their unique ID number. */

    public static void main(String []args) throws IOException,Exception
    {
		  // Open file for writing
		  File fo = new File("Student_Mark.doc");
		  FileWriter fw = new FileWriter(fo);
		  BufferedWriter bw = new BufferedWriter(fw);
		  PrintWriter pw = new PrintWriter (bw);

          // instantiate the object of the class Scanner
	      Scanner in = new Scanner(System.in);

	      // declare main variable
	      int [] mark4part;
	      int numstu, numpart, fullmark=0;
	      double [] partArray;
	      double allmark=0, percentage;

	      // display
          System.out.println("	=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=	");
          System.out.println("	|*** Welcome to Counting Marks For Examiners App ***|	");
          System.out.println("	=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=	\n\n");

	      // first part input - detail about program
	      System.out.print("Subject Name: ");
	      String sn = in.nextLine();
	      System.out.print("Subject Code: ");
	      String sc = in.nextLine();
	      System.out.print("Credit Hour: ");
	      int ch = Integer.parseInt(in.nextLine());
	      System.out.println("");
	      System.out.print("How many student?: ");
	      numstu = Integer.parseInt(in.nextLine());
	      System.out.print("Enter total part of the question: ");
	      numpart = Integer.parseInt(in.nextLine());

	      // get mark for each part and sum up all
	      mark4part = new int[numpart];
	      for(int a=0 ; a<numpart ; a++)      {
                  System.out.print("Enter full mark for part "+(a+1)+ ": ");
	              mark4part[a] = Integer.parseInt(in.nextLine());
	              fullmark+=mark4part[a];     }

	      System.out.print("The Percentage of Carry Mark that the question hold: ");
	      percentage = Integer.parseInt(in.nextLine());

	      //Object Instantiation
	      Exam info = new Exam(sn, sc, ch);

	      Exam marklist[] = new Exam[numstu];           // Array to store Object of concrete class Exam
          Student studentlist[] = new Student[numstu];  // Array to store Object of concrete class Students

		  //  display
		  System.out.println("\n\t\t~~~~~~~~"+ sn + "~~~~~~~~");
		  System.out.println("\t\t============================================");
		  System.out.println("\t\t||        CODE          || "+ sc + "	||");
		  System.out.println("\t\t||     CREDIT HOUR      || "+ ch + "		||");
		  System.out.println("\t\t||    TOTAL STUDENT     || "+ numstu + "		||");
		  System.out.println("\t\t|| PART OF THE QUESTION || "+ numpart + "		||");
		  System.out.println("\t\t||     CARRY MARK %     || "+ percentage+ "		||");
	   	  System.out.println("\t\t============================================\n");

          //  OUTPUT on file
          pw.println("\n\t\t~~~~~~~~"+ sn + "~~~~~~~~");
		  pw.println("\t\t============================================");
		  pw.println("\t\t||        CODE          || "+ sc + "	||");
		  pw.println("\t\t||     CREDIT HOUR      || "+ ch + "		||");
		  pw.println("\t\t||    TOTAL STUDENT     || "+ numstu + "		||");
		  pw.println("\t\t|| PART OF THE QUESTION || "+ numpart + "		||");
		  pw.println("\t\t||     CARRY MARK %     || "+ percentage+ "		||");
	   	  pw.println("\t\t============================================\n");

		  // input for every student
		  partArray = new double [numpart];
		  System.out.println("\t\t\t........System Start........\t\t\t");
          for(int box=0 ; box<numstu ; box++)
          {
				    System.out.println("\nStudent("+(box+1)+")");
					System.out.print("Student "+(box+1)+" Name = ");
					String nm = in.nextLine();
					System.out.print("Student "+(box+1)+" Id = ");
					String id = in.nextLine();
					System.out.print("Class = ");
					String cl = in.nextLine();
					System.out.print("Gender(M/F) = ");
					char gn = in.nextLine().charAt(0);
                    System.out.print("\n");

					for(int c=0 ;c<numpart ;c++)                               {
						    System.out.print("Mark Part "+(c+1)+ ": ");
					        partArray[c]=Double.parseDouble(in.nextLine());     }

					// Object instantiation of class Exam
					marklist[box]= new Exam(partArray);

					double mark = marklist[box].countMark();        //method to invoke CountMark method from class Exam
					double carrymark=(mark/fullmark * percentage);  //count the carrymark based on the counted mark

                    // Object instantiation of class Student
					studentlist[box]=new Student(nm, id, cl, gn, mark,carrymark);

					//to store all of the student marj
                    allmark += studentlist[box].getMark();         //method to invoke getMark method from class Student
                    System.out.print("\n");

			}// end of loop
			System.out.println("===============================================================\n");


            // PROCESS
            // #1 Determine which student who get the highest mark
            double highest = studentlist[0].getMark();            //We assign the index zero of studentlist as the highest
            for(int d=1; d<numstu ; d++)
            {
				    if(studentlist[d].getMark()>highest)          //Compare which one is higher
				            highest = studentlist[d].getMark();   //Capture the highest mark
			}

            System.out.print("++++++++++++++++++[ Highest Mark = "+ highest +" ]++++++++++++++++++");

            // Find anyone who have mark that same with the highest mark and display it out
            System.out.println("\n\t\tStudent who get the highest mark " );
            for(int e=0 ; e<numstu ; e++)
            {
				   if(studentlist[e].getMark()==highest)                                                                           {
				      	   studentlist[e].print();
				           for(int f=0; f<numpart; f++)
				                   System.out.println( f+6 + " - Part "+(f+1) +" = "+ marklist[e].getPart(f)+ "/" + mark4part[f]); }
		    System.out.print("\n\n");
			}
 			System.out.println("==============================================================\n");


            // #2 Determine student who get the lowest mark among all
			double lowest = highest;                             //We assign the highest mark as a lower mark at first
			for(int g=0; g<numstu ; g++)
		    {
					 if(studentlist[g].getMark()<lowest)         //Compare which one is lower
		            	     lowest=studentlist[g].getMark();    //capture the lowest mark
		    }

		    System.out.print("------------------[ Lowest Mark = "+ lowest + " ]------------------");

		    // Find anyone who have mark that same with the lowest mark and display it out
			System.out.println("\n\t\tStudent who get the lowest mark \n" );
			for(int h=0 ; h<numstu ; h++)
			{
					 if(studentlist[h].getMark()==lowest)                                                                            {
				            studentlist[h].print();
						    for(int i=0; i<numpart; i++)
				                   System.out.println( i+6 + " - Part "+(i+1) +" = "+ marklist[h].getPart(i)+ "/" + mark4part[i]);   }
		    System.out.print("\n");
		    }
            System.out.println("==============================================================\n");


            // #3 Determine the average mark of all student
			double avg=allmark/numstu;
			System.out.print("___________________________________________\n");
			System.out.println("|The average mark of all student is " +String.format("%.2f",avg )+ "/"+ fullmark +"|");
			System.out.println("___________________________________________\n\n");


            // #4 Searching Algorithm to find the student based on their Student Id
            System.out.println("Do you want to search for specific student (Yes/No)>> ");
            String answer = in.nextLine();

            // We use sentinel loop so the User can seacrh as many as they want
            while(answer.equalsIgnoreCase("yes"))
            {
                  Student index1 = new Student();
                  String index2 = null;
                  System.out.print("\nEnter Student ID >> ");
		          String input = in.nextLine();

				  for(int z=0 ; z<numstu ; z++)
				  {
				       if(studentlist[z].isInputID(input))    //method to invoke isInputID method from class Student
				            {  index1=studentlist[z];
				               index2="Found";
				               break;         }
			      }

                  if(index2!=null)
                      index1.print();
                  else
                      System.out.println("~ Invalid ID !! ~");

                  System.out.println("\nDo you wish to continue searching ? (Yes/No)>> ");
                  answer = in.nextLine();

			}

            //User can choose wether want to display all the data or not
            System.out.println("\nDo you want to display all the students mark data ? (Yes/No)>> ");
            answer = in.nextLine();

            if(answer.equalsIgnoreCase("yes"))
            {
			// display all student mark
			info.print();
			System.out.println("\n|=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-|");
			System.out.println("|Bil " + "   | Student Name\t\t" + "| Mark\t  " + "| Carry Mark   |");
			System.out.println("|_______|_______________________|_________|______________|");
			for(int i=0 ; i<numstu ; i++)
				   System.out.println( "|"+(i+1)+"      |       "+ studentlist[i].getName()+ "\t\t|" + studentlist[i].getMark() +"\t  |" + String.format("%.2f",studentlist[i].getcarryMark())+"%\t|" );
			System.out.println("|=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-|");
            System.out.println("\n");
		    }

		    else{
			System.out.println("\n");
			System.out.println("\n-----------------------------------------------");
		    System.out.println("\n-- *** Thank you and have a good day !!! :D *** --");
			System.out.println("\n-----------------------------------------------");  }


            // OUTPUT on file
            pw.println("|=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-|");
			pw.println("|Bil " + "   | Student Name\t\t" + "| Mark\t  " + "| Carry Mark   |");
			pw.println("|_______|_______________________|_________|______________|");
			for(int i=0 ; i<numstu ; i++)
							pw.println( "|"+(i+1)+"      |       "+ studentlist[i].getName()+ "\t\t|" + studentlist[i].getMark() +"\t  |" + String.format("%.2f",studentlist[i].getcarryMark())+"%\t|" );
			pw.println("|=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-|");
            pw.println("\n");

            //close the output/file stream. Otherwise it wont save the data into file
            pw.close();


	}
}

