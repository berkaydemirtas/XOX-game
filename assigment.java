import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class assigment {
	

	public static void main (String[] args) throws FileNotFoundException {
		
	    
	    Scanner console= new Scanner (System.in);
	    File f;
	    String file;
	    Scanner input;
	    System.out.println("Welcome to the XOX Game.");
	    System.out.println("Would you like to load the board from file or create a new one? (L or C)");
	    String type=console.nextLine();
	    while(!type.equalsIgnoreCase("l")&&!type.equalsIgnoreCase("c")) {
	    	System.out.println("please enter c or l");
	    	 type=console.nextLine();
	    }
	    if(type.equalsIgnoreCase("l")){
	    	System.out.println("Please enter the file name:");
	    file=console.nextLine();
	    f=new File(file);
	    while(!f.exists()) {
	    	System.out.println("This file does not exist.Please enter a valid file name.");
	        file=console.nextLine();
	        f= new File(file);
	    }
	    System.out.println("Load successful.");
	    input=new Scanner(new File(file));
	    
	    }
	    
	    
	   // Scanner input=new Scanner(new File(file));
	    
	    
	    System.out.println("Enter your symbol: (X or O)");
	    String figure=console.nextLine();
	    while(!figure.equalsIgnoreCase("x")&&!figure.equalsIgnoreCase("o")) {
	    	System.out.println("Please enter X ore O");
	    	figure=console.nextLine();
	    }
	    String a=" ";
	    figure=figure.toUpperCase();
	    if(figure.equals("X"))
	    	 a="O";
	    else 
	    	a="X";
	   
	    System.out.println("You are player "+figure+"and the computer is player "+a+".");
		
		
		String table="";
		
		//if(type.equalsIgnoreCase("l")){
			//while(input.hasNextLine()) {
		    	//String sum =input.nextLine();
		    //	table=table+sum+"\n";
		    //}
			  //}
			
		//else {
			 table="| E | E | E | E |\n| E | E | E | E |\n| E | E | E | E |\n| E | E | E | E |";
		//}
		
		Random rand = new Random();
		boolean starter=rand.nextBoolean();
		
		int first=0;
		int second=0;
		while(!finish(table)) {
			
			if(starter) {
			
			 first=console.nextInt();
			 second=console.nextInt();
			
			while(!(first<=4&&first>=1&&second<=4&&second>=1)|| !(table.charAt(16*first+4*second-2)=='E')){
				System.out.println("Wrong input! Try again:");
				 first=console.nextInt();
				 second=console.nextInt(); 
			}
			
			String line1=table.substring(0,17);
	        String line2=table.substring(17,34);
	 		String line3=table.substring(34,51);
	 		String line4=table.substring(51,68);
			game(first,second,figure,line1,line2,line3,line4);
			
		}
			
			else {
				first=rand.nextInt(4)+1;
				second=rand.nextInt(4)+1;
				while(!(first<=4&&first>=1&&second<=4&&second>=1)|| !(table.charAt(16*first+4*second-2)=='E')) {
					first=rand.nextInt(4)+1;
					second=rand.nextInt(4)+1;
					
				}
				String line1=table.substring(0,17);
		        String line2=table.substring(17,34);
		 		String line3=table.substring(34,51);
		 		String line4=table.substring(51,68);
				game(first,second,figure,line1,line2,line3,line4);
				
				
			}
			
				
			
			if(starter==true)
		        starter=false;
			else
				starter=true;
		}
		
	}
	
	
	
	
	public static String game(int first,int second,String figure,String line1,String line2,String line3,String line4){
		
		Scanner console= new Scanner (System.in);
		
		
		
		if(first==1 ) {
		   if(figure.equalsIgnoreCase("x"))	
	          line1=line1.substring(0,4*second-2)+"X"+line1.substring(4*second-1);
		   else 
			   line1=line1.substring(0,4*second-2)+"O"+line1.substring(4*second-1);
		}
		
		if(first==2 ) {
			   if(figure.equalsIgnoreCase("x"))	
		          line2=line2.substring(0,4*second-2)+"X"+line2.substring(4*second-1);
			   else 
				   line2=line2.substring(0,4*second-2)+"O"+line2.substring(4*second-1);
			}
		
		if(first==3 ) {
			   if(figure.equalsIgnoreCase("x"))	
		          line3=line3.substring(0,4*second-2)+"X"+line3.substring(4*second-1);
			   else 
				   line3=line3.substring(0,4*second-2)+"O"+line3.substring(4*second-1);
			}
			
		if(first==4 ) {
			   if(figure.equalsIgnoreCase("x"))	
		          line4=line4.substring(0,4*second-2)+"X"+line4.substring(4*second-1);
			   else 
				   line4=line4.substring(0,4*second-2)+"O"+line4.substring(4*second-1);
			}
		
		System.out.println(line1);
		System.out.println(line2);
		System.out.println(line3);
		System.out.println(line4);
		
		
		  String table=line1+line2+line3+line4;
		return table;
			
			
			
		}
	
	    public static boolean finish(String table) {
	    	int count=0;
	    	if(table.charAt(2)==table.charAt(23)&&table.charAt(23)==table.charAt(44))//yanlarýna eþit deðil e koymayý unutma
	    	    count++;
	    	
	    	if(table.charAt(6)==table.charAt(27)&&table.charAt(27)==table.charAt(48))
	    	    count++;
	    	
	    	if(table.charAt(19)==table.charAt(40)&&table.charAt(40)==table.charAt(61))
	    	    count++;
	    	
	    	if(table.charAt(23)==table.charAt(44)&&table.charAt(44)==table.charAt(65))
	    	    count++;
	    	
	    	if(table.charAt(2)==table.charAt(23)&&table.charAt(23)==table.charAt(44))
	    	    count++;
	    	
	    	if(table.charAt(10)==table.charAt(23)&&table.charAt(23)==table.charAt(36))
	    	    count++;
	    	
	    	if(table.charAt(14)==table.charAt(27)&&table.charAt(27)==table.charAt(40))
	    	    count++;
	    	
	    	if(table.charAt(27)==table.charAt(40)&&table.charAt(40)==table.charAt(53))
	    	    count++;
	    	
	    	if(table.charAt(31)==table.charAt(44)&&table.charAt(44)==table.charAt(57))
	    	    count++;
	    	
	        String line1=table.substring(0,17);
	        String line2=table.substring(17,34);
	 		String line3=table.substring(34,51);
	 		String line4=table.substring(51,68);
	 		
	 		count+=finish2(line1);
	 		count+=finish2(line2);
	 		count+=finish2(line3);
	 		count+=finish2(line4);
	 		
	 	    for (int i=0;i<17;i++) {
	 	    
	 	      if(line1.charAt(2)=='X'||line1.charAt(2)=='O') {
	 	      
	 	    	if(line1.charAt(2)==line2.charAt(6)&&line2.charAt(2)==line3.charAt(2))
	 	    	count++;
	 	      }
	 	      
	 	     if(line2.charAt(2)=='X'||line2.charAt(2)=='O') {
		 	      
		 	    	if(line2.charAt(2)==line3.charAt(6)&&line3.charAt(2)==line4.charAt(2))
		 	    	count++;
		 	      }
	 	    }
	 			if(count>0)
	 				return true;
	 			
	 			return false;
	 			
	 		}
	    
	    
	    public static int finish2(String line) {
	    	
           for(int i=0;i<13;i++) {
        	   
        	 if(line.charAt(i)=='X'||line.charAt(i)=='O') {
	 			
	 			if(line.charAt(i)==line.charAt(i+4)) 
	 				return 1;
	 			
	}   
        	 
           }
           return 0;
	    
	    			
	    			
	    			
	    			
	    			
	    			
	    			
	    			
	    }}

	


