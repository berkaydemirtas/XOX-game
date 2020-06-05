package assigmentCorecct;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class BD2017400234 {

	
	public static void main(String[] args) throws FileNotFoundException {
		String table="";                                                                            //I use this variable to represent table on which game is playing
		Scanner console=new Scanner(System.in);                                                     
		Scanner x;                                                                                  // I use this variable to take file
		System.out.println("Welcome to the XOX Game.");
		System.out.print("Would you like to load the board from file or create a new one? (L or C)");
		String a=console.nextLine().toUpperCase().charAt(0)+"";                                     // I take user's answer from console and use variable "a" to hold whether user wants to create a new game or load a game from a file  
		while(!a.equalsIgnoreCase("l")&&!a.equalsIgnoreCase("c")) {                                 //
			System.out.println("wrong input");                                                      //this while loop check whether user give a valid answer
			 a=console.nextLine().toUpperCase().charAt(0)+"";                                       //
		}
		String tableLoad="";        //represent to table in file
		if(a.equalsIgnoreCase("l")) {                                         //if user want to load a game from file,this "if" want the name of file from user                                                                                           
			System.out.print("Please enter the file name: ");                 
			String c=console.nextLine();                                      //variable "c" represent to name of file        
			File f=new File(c);                                               //variable "f" represent to file that user give name
			
			while(!f.canRead()||!fileTest(c)) {                               //this while loop check whether file "f" can read and ask method "fileTest" whether this file has a valid "table" to play game on 
				System.out.println("invalid file . Try again");               //and until f provide this conditions ,it wants new file name
				 c=console.nextLine();
				 f=new File(c);
			}
			System.out.println("Load successful.");                   
			 x=new Scanner (new File(c));
			tableLoad=x.next();                                              //"tableLoad" is equalized to table  in file which is consist of 16 E or O or X
		}
		
		System.out.print("Enter your symbol: (X or O)");
		String icon=console.next().toUpperCase();                            //this part asks user to select whether use X or O ,and equalize it to variable "icon"
		while(!icon.equalsIgnoreCase("x")&&!icon.equalsIgnoreCase("o")) {    //and if user don't write X or O , asks again
			System.out.println("wrong input");
			icon=console.next().toUpperCase();
		}
		if(icon.equalsIgnoreCase("x"))                                           //
		System.out.println("You are player X and the computer is player O.");     //this part inform about icon of user and computer
		else                                                                     //
			System.out.println("You are player O and the computer is player X.");//	
		String b="y";  //represent whether user want to play again (Y or N)
		int you=0;     //represent how many times user win
		int computer=0;//represent how many times computer win
		String who;    //represent who win the game
		if(a.equalsIgnoreCase("l")) {      //
			table=tableLoad;               //
			who=LoadPlay(tableLoad,icon);  //LoadPlay is a method return string by equalizing this string to "who",I understand who win
			if(who.equalsIgnoreCase("you"))      //if user select to load a game from file ,this part is played game by calling LoadPlay method
			you ++;                              //and count how many times user and computer win
			if(who.equalsIgnoreCase("computer"))//
			computer++;                         //
			 b=console.nextLine();                  //this part take the answer of user the question "do you want to play again?"
				while(!b.equalsIgnoreCase("y")&&!b.equalsIgnoreCase("n")) {//and thanks to the this while, user is asked for a new answer  
					System.out.println("wrong input. Try again");          //until user's answer valid (Y or N)
					b=console.nextLine();
		}}
		
		if(!a.equalsIgnoreCase("l")||b.equalsIgnoreCase("y")) {  //if user want to create a game or after play loaded game ,want to play new game 
		while(b.equalsIgnoreCase("y")) {                         //this if statement is being used
	    table="EEEEEEEEEEEEEEEE";//"table" is equalized to starting table
		who=gamePlayCreate( icon, table);//LoadPlay is a method return string by equalizing this string to "who",I understand who win
		if(who.equalsIgnoreCase("you"))  //
		you ++;                          //
		if(who.equalsIgnoreCase("computer"))//this part again counts how many times user and computer win
		computer++;                      //
		 b=console.next();                //and take the user's answer whether want to play again or not 
		while(!b.equalsIgnoreCase("y")&&!b.equalsIgnoreCase("n")) {//and until user's answer is valid ask for new answer
			System.out.println("wrong input. Try again");
			b=console.nextLine();
		}}
		}
		System.out.println("You: "+you +" computer: "+computer);// after user don't want to play a new game ,this part is printed how many times computer and user win
		System.out.println("thanks for playing!");}

	//this method take the coordinates , icon , who start,and game table as parameters , edit game table according to this parameters
	//print edited game table and return edited game table
	
	public static String table (String table,int first,int second ,String icon,int starter) {
				String newTable=table.substring(0,4*(first-1)+second-1)+icon+table.substring(4*(first-1)+second);//this line edit game table  and equalize this table to variable "newTable"        
		if(starter==1||control(newTable)) {//if "starter"(a variable that I used to determine who will start) is 1 and control which is a method controls whether anyone win this if is used
			for(int j=0;j<=15;j++) {       //this for loop is printed the edited game table
				System.out.print("| ");
				System.out.print(newTable.charAt(j)+" ");
				if(j==3||j==7||j==11) {
					System.out.println("|");
				}		}
			System.out.print("|");
		}
			return newTable;   //returns edited table
	}
	public static boolean control(String table) {	// this method (take a parameter table) check whether anyone win and return true if anyone win
		int count=0;
		String line1=table.substring(0,4);      //this part separate table into the lines
		String line2=table.substring(4,8);
		String line3=table.substring(8,12);
		String line4=table.substring(12);
		
		count+=control2(line1);      //this part control lines one by one by using method control2  
		count+=control2(line2);      //which control whether there is a table which provide sufficient condition for horizontal win and if anyone win return 1
		count+=control2(line3);      //and sum the return of the control2 with count
		count+=control2(line4);
		
	
		
		count+=control3(table);//sum return of control3 with count
		count+=control4(table);//sum return of control4 with count
		if(count<1)// and if count bigger than or equal 1 this method return true
			return false;
			else 
				return true;	}
	//this method take parameter line and control whether it provide any win condition horizantally .if it does ,return 1
	  public static int control2(String line) {
		   for(int i=0;i<=1;i++) {
				if(line.charAt(i)==line.charAt(i+1)&&line.charAt(i)==line.charAt(i+2)&&!(line.charAt(i)=='E')){
					return 1;}
				}     return 0;  }
	  
	   //this method take a table as a parameter and control whether it provides any win condition veertically . Ýf it does , return 1
	  public static int  control3(String table) { 
		  for(int i=0;i<=7;i++) {
			   if(table.charAt(i)==table.charAt(i+4)&&table.charAt(i)==table.charAt(i+8)&&!(table.charAt(i)=='E'))
				  return 1; }
		  return 0;}
	      
	  //this method take a table as a parameter and control whether it provides any win condition diagonally . Ýf it does , return 1
	  public static int control4(String table) {
		   for(int i=0;i<=1;i++) {
			  if(table.charAt(i)==table.charAt(i+5)&&table.charAt(i)==table.charAt(i+10)&&!(table.charAt(i)=='E')) {
				  return 1; }
	 }
           for(int i=4;i<=5;i++) {                                                                                    //there are 4 for loop  
 			   if(table.charAt(i)==table.charAt(i+5)&&table.charAt(i)==table.charAt(i+10)&&!(table.charAt(i)=='E')) { //and control different
 				  return 1;}                                                                                          //diagonally win conditions
	 }
           for(int i=2;i<=3;i++) {
        	   if(table.charAt(i)==table.charAt(i+3)&&table.charAt(i)==table.charAt(i+6)&&!(table.charAt(i)=='E')) {
  				  return 1; }
        	   }
           for(int i=6;i<=7;i++) {
           if(table.charAt(i)==table.charAt(i+3)&&table.charAt(i)==table.charAt(i+6)&&!(table.charAt(i)=='E')) {
				  return 1;
				  }}
           return 0;
           }
	                                                                   //this method take icon and table parameter.
	  public static String gamePlayCreate(String icon,String table) {  // if user select create a new game of continue after loaded game
		  String compIcon =" ";  //represent the icon of computer      //this method is used and return who win as a string.
		  if(icon.equalsIgnoreCase("x"))
		            compIcon="O";        //this part find the computer icon equalize it to compIcon
		            else
		            	compIcon="X"; 
		  int first;//represent the first coordinate
		  int second;//represent the second coordinate
		  Random rand=new Random ();
		  int starter=rand.nextInt(2);//I use this part to randomly select who will start
		  if(starter==1)
			  System.out.println("you will start");
		  else                                         //and I am printed who will start
			  System.out.println("computer will start");
		  Scanner console=new Scanner(System.in);
		  if(starter==1) {//if first user will play inside of this if will be executed
			  System.out.println("| E | E | E | E |");
			  System.out.println("| E | E | E | E |");    
			  System.out.println("| E | E | E | E |");
			  System.out.println("| E | E | E | E |");
			while(!control(table)&&table.contains("E")) {	//thanks to this while loop , game continue until there is E in table and anyone win	
				System.out.println("Enter coordinates:");
				
				
				
				while(true) {
					try {
						first = console.nextInt();
						second = console.nextInt();
						console.nextLine();
						if(!(first<=4&&first>=1&&second<=4&&second>=1))//this part takes coordinates from user and until user give a valid answer
							System.out.println("wrong input.Try again:");//ask again
						break;						
					}catch(Exception X) {                           //by the try catch ý want to inhibit mismatch exception
						console.nextLine();
						System.out.println("Wrong input! Try again:");
						continue;
					}
				}
				while(!(first<=4&&first>=1&&second<=4&&second>=1)||!(table.charAt(4*(first-1)+second-1)=='E')) {//by this condition user give input until
					while(true) {                                                                   //give coordinates for free areas and between 1-4
						try {
							first = console.nextInt();//this part takes coordinates from user and until user give a valid answer
							second = console.nextInt();//ask again
							console.nextLine();
							if(!(first<=4&&first>=1&&second<=4&&second>=1))
								System.out.println("wrong input! Try again:");//and I use try catch again
							break;						
						}catch(Exception X) {
							console.nextLine();
							System.out.println("Wrong input! Try again:");
							continue;
						}
					}
				}
				
				
				
				
				table=table(table,first,second,icon,starter-1);  //this part edit table according to coordinates
				if(control(table)) {                           //and if edited table provide any win condition this if is executed
					System.out.println();
					System.out.println("You win! Do you want to play again? (Y or N)");
				    return "you"; 
					}
				
				first=rand.nextInt(4)+1;
				second=rand.nextInt(4)+1;
				while(!(table.charAt(4*(first-1)+second-1)=='E')) {//this part randomly take coordinates from computer until 
					first=rand.nextInt(4)+1;                        //coordinates provide sufficient conditions
					second=rand.nextInt(4)+1;
				}
				table=table(table,first,second,compIcon,starter); //table is edited according to the computer's coordinate
				System.out.println();
				if(control(table)) { //and if edited table provide any win condition this if is executed
					System.out.println();
					System.out.println("computer win! Do you want to play again? (Y or N)");
					return "computer";
				}
				if(!table.contains("E")) {//if there are not a winner and there is no E in table there is tie
					
					System.out.println("tie! Do you want to play again");
					return "tie";
				}
				
			}
			}
		  else {
			 while(!control(table)&&table.contains("E")) { //if first computer will play inside of this if will be executed
			    first=rand.nextInt(4)+1;
				second=rand.nextInt(4)+1;
				while(!(table.charAt(4*(first-1)+second-1)=='E')) {// takes valid coordinates from computer
					first=rand.nextInt(4)+1;
					second=rand.nextInt(4)+1;
				}
				table=table(table,first,second,compIcon,1);//table is edited according to computer's input
				System.out.println();
				if(control(table)) {//check whether any win condition is provided and if it does this if is executed
					System.out.println();
					System.out.println("computer win! Do you want to play again? (Y or N)");
					return "computer";
				}
				System.out.println("enter coordinates: ");
				while(true) {
					try {
						first = console.nextInt();           //this part take input from user  
						second = console.nextInt();          //until user give a valid input 
						console.nextLine();                  //and by try catch I hinder misMatch exception
						if(!(first<=4&&first>=1&&second<=4&&second>=1))
							System.out.println("wrong input.Try again:");
						break;						
					}catch(Exception X) {
						console.nextLine();
						System.out.println("Wrong input! Try again:");
						continue;
					}
				}
				while(!(first<=4&&first>=1&&second<=4&&second>=1)||!(table.charAt(4*(first-1)+second-1)=='E')) {//until user give a valid input 
					while(true) {
						try {                                                                     //this part wants new input 
							first = console.nextInt();
							second = console.nextInt();
							console.nextLine();
							if(!(first<=4&&first>=1&&second<=4&&second>=1))
								System.out.println("wrong input.Try again:");
							break;						
						}catch(Exception X) {
							console.nextLine();
							System.out.println("Wrong input! Try again:");
							continue;
						}
					}
				}
				table=table(table,first,second,icon,starter-1);  //and according to coordinates table is edited
				if(control(table)) {//control whether there are any winner 
					System.out.println();
					System.out.println("You win! Do you want to play again? (Y or N)");
					return "you";  }
				if(!table.contains("E")) {//if there are not a winner return tie
					table(table,first,second,icon,1);
					System.out.println("tie! Do you want to play again(Y or N)");
					return "tie";
				}	
			 }
		  }
		  System.out.println();
		  System.out.println("tie! do you want to play again? (Y or N)");
		  return "tie";  }
	  
	 public static boolean fileTest(String fileName) throws FileNotFoundException {  //this file takes file name as a parameter and check whether 
		 Scanner a=new Scanner(new File(fileName));                           //table inside of this file suitable to play
		 int countX=0;//this variables count the number of X O E
		 int countO=0;
		 int countE=0;
		  String b;
		  if(a.hasNextLine())//control whether it is a empty 
			  b=a.nextLine();
		  else
			  return false;
		  for(int i=0;i<b.length();i++) {  
			  if(b.charAt(i)=='X')
				  countX++;           //this part counts the number of X O E
			  if(b.charAt(i)=='O')
				  countO++;
			  if(b.charAt(i)=='E')
				  countE++;
			  }
		  if(Math.abs(countX-countO)>1)//check the difference between the nuber of X and O
			  return false;
		  if(countX+countO+countE!=16) {//check whether there is something other than X O E
			  return false;
		  }
		  if(b.length()>16)//check the length of table inside the file
			  return false;
		  if(control(b))
			  return false;
		  
		  return true; }
	  
	 public static String LoadPlay(String table,String icon) {//this method is used when user want to play a loaded game
		   int first;
		   int second;
		   Scanner console=new Scanner (System.in);
		   Random rand=new Random();
		   int starter=rand.nextInt(2);  //this part select randomly who will start if there is a equality between X and O
		   String compIcon =" ";
			  if(icon.equalsIgnoreCase("x"))
			            compIcon="O";
			            else                //this part select computer's icon
			            	compIcon="X";
			  int countX=0;
				 int countO=0;
				 int countE=0;
			  for(int i=0;i<table.length();i++) { //this for count the number of X O E
				  if(table.charAt(i)=='X')
					  countX++;
				  if(table.charAt(i)=='O')
					  countO++;
				  if(table.charAt(i)=='E')
					  countE++;
				   }
			  if((countX>countO && icon.equalsIgnoreCase("x"))||((countX==countO)&&starter==0) ||(icon.equalsIgnoreCase("o"))&&countO>countX) {
				  System.out.println("computer will start");                           //if starter is computer this part is used
				  while(!control(table)&&table.contains("E")) { 
					    first=rand.nextInt(4)+1;
						second=rand.nextInt(4)+1;
						while(!(table.charAt(4*(first-1)+second-1)=='E')) {//this part take valid coordinates from computer
							first=rand.nextInt(4)+1;
							second=rand.nextInt(4)+1;
						}
						table=table(table,first,second,compIcon,1);//this part edit table
						System.out.println();
						if(control(table)) {//control whether there is a winner 
							System.out.println();
							System.out.println("computer win! Do you want to play again? (Y or N)");
							return "computer";
						}
						if(!table.contains("E")) {//look at for tie
							System.out.println("Tie! Do you want to play again");
							return "tie";
						}
					
						System.out.println("enter coordinates: ");
						while(true) {
							try {
								first = console.nextInt();
								second = console.nextInt();
								console.nextLine();
								if(!(first<=4&&first>=1&&second<=4&&second>=1))//this part want coordinates from user 
									System.out.println("wrong input.Try again:");
								break;						
							}catch(Exception X) { 
								console.nextLine();
								System.out.println("Wrong input! Try again:");
								continue;
							}
						}
						while(!(first<=4&&first>=1&&second<=4&&second>=1)||!(table.charAt(4*(first-1)+second-1)=='E')) {//and until user give valid coordinates ask again
							while(true) {
								try {
									first = console.nextInt();
									second = console.nextInt();//asks for new coordinate
									console.nextLine();
									if(!(first<=4&&first>=1&&second<=4&&second>=1))
										System.out.println("wrong input.Try again:");
									break;						
								}catch(Exception X) {
									console.nextLine();
									System.out.println("Wrong input! Try again:");
									continue;
								}
							}
						}
						table=table(table,first,second,icon,starter-1);//table is edited according to user's coordinates
						
						if(control(table)) {//control whether there is a winner
							System.out.println();
							System.out.println("You win! Do you want to play again? (Y or N)");
							return "you"; }
					if(!table.contains("E")) {              //check whether there is a tie
						table(table,first,second,icon,1);
						System.out.println();
						System.out.println("Tie! Do you want to play again? (y or n)");
						return "tie";
					}
				  }
		     }
			  if((countO>countX && icon.equalsIgnoreCase("x"))||((countX==countO)&&starter==1)||(countX>countO)&&icon.equalsIgnoreCase("o")) {
				  System.out.println("you will start");                                  //this if is used if starter is user
				  for(int j=0;j<=15;j++) {
						System.out.print("| ");
						System.out.print(table.charAt(j)+" ");//this part is printing the table
						if(j==3||j==7||j==11) {
							System.out.println("|");
						}		}
					System.out.print("|");
					System.out.println();
				  while(!control(table)&&table.contains("E")) {	
						System.out.println("Enter coordinates:");
						while(true) {
							try {
								first = console.nextInt();
								second = console.nextInt();
								console.nextLine();
								if(!(first<=4&&first>=1&&second<=4&&second>=1))//this part as user for coordinates
									System.out.println("wrong input.Try again:");
								break;						
							}catch(Exception X) {
								console.nextLine();
								System.out.println("Wrong input! Try again:");
								continue;
							}
						}
						while(!(first<=4&&first>=1&&second<=4&&second>=1)||!(table.charAt(4*(first-1)+second-1)=='E')) {
							while(true) {                        //if coordinates are invalid ,thanks to this while ,it asks again
								try {
									first = console.nextInt();
									second = console.nextInt();
									console.nextLine();
									if(!(first<=4&&first>=1&&second<=4&&second>=1))//by try catches ý want to hinder mismatch exception
										System.out.println("wrong input.Try again:");
									break;						
								}catch(Exception X) {
									console.nextLine();
									System.out.println("Wrong input! Try again:");
									continue;
								}
							}
						}
						table=table(table,first,second,icon,starter-1);//table again edit according to user's coordinates
						
						if(control(table)) {
							System.out.println();
							System.out.println("You win! Do you want to play again? (Y or N)");
						    return "you"; }
						if(!table.contains("E")) {
							table(table,first,second,icon,1);
							System.out.println();
							System.out.println("Tie! Do you want to play again? (Y or N)");
							return "tie";
						}
						
					
						first=rand.nextInt(4)+1;
						second=rand.nextInt(4)+1;
						while(!(table.charAt(4*(first-1)+second-1)=='E')) {//this part takes random valid coordinates from computer
							first=rand.nextInt(4)+1;
							second=rand.nextInt(4)+1;  }
						table=table(table,first,second,compIcon,1);//table again edit according to computers input
						System.out.println();
						if(control(table)) {//control whether there is a winner 
							System.out.println();
							System.out.println("computer win! Do you want to play again? (Y or N)");
							return "computer";	}
						if(!table.contains("E")) {//check whether there is tie situation
							
							System.out.println("Tie! Do you want to play again(Y or N)");
							return "tie";
						}
						}
						}
			  return "tie";
			  }  
	 
    
















}
