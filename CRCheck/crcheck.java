import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.math.*;
public class crcheck {
	
	public static void main(String[] args) throws UnsupportedEncodingException { 

		//Get mode operation
		String command = args[0];
		
		//Get textfile
		Scanner textfile = null;
		try {
			textfile = new Scanner(new File(args[1]));
		} 
		catch (FileNotFoundException e) {
			System.out.println("The system cannot find the file specified");
		}
		
		//Make sure the command is either c or v
	    if ((!command.equals("c")) && (!command.equals("v"))){
		   textfile.close();
		   System.err.println("Invalid Operation Exception: Only c or v are allowed");
		   System.exit(1);
	   }
	    
	    //Determine if Verification
	    Variables.isVerify = (command.equals("v"));
	    
	    //Get array of plaintext with added '.'
	    String[] textarray = processinput(textfile);
	    
	    System.out.println("\n\nCRC 16 calculation progress:\n");
	    
	    //Generate the CRC
	    generateCRC(textarray);
	    
	    textfile.close();
	}
	
	//Global variables to use
	public static class Variables {
		public static String CRC;
		public static boolean isVerify;
		public static final int[] poly = {1,0,1,0,0,0,0,0,0,1,0,1,0,0,1,1};
	}

	//A method that takes the polynomials and the plaintext in array format and prints out the CRC and calculation process
	private static void generateCRC(String[] textarray) {
		String CRCheck = "";
		for (int x = 1; x<=8; x++){
	    	
	    	//Get constants
	    	int size = (x==8) ? 512*x-64 : 512*x;
	    	int padsize = size + 15;
	    	
		    //Initialize Array
		    int[] binaryarray = new int[padsize]; 
		    for (int i = 0; i<padsize; i++){
		    	binaryarray[i] = 0;
		    }
		    
		    String binary = "";
		    
		    for (int y = 0; y<x; y++){
		    	//Get the binary in a string.
			    String bin = new BigInteger(textarray[y].getBytes()).toString(2);
			    
		    	int sized = (y==7) ? 448 : 512;
			    
			    //Make sure we didn't lose any leading zeros that we will need
			    while (bin.length() < sized){
			    	bin = "0" + bin;
			    }
			    binary = binary + bin;
		    }
		    
		    //Convert string to an array
		    for (int i = 0; i<binary.length(); i++){
		    	binaryarray[i] = binary.charAt(i) - 48;
		    }
		    
		    int k = 0;
		    while (k<size){
		    	
		    	k = 0;
			    // Get an non-zero value for index k
			    while (binaryarray[k] == 0){
			    	k++;
			    }
			    
			    if (k>= size){
			    	break;
			    }
			    
			    //Xor magic
			    for (int i = 0; i<16; i++){
			    	binaryarray[i+k] = xor(Variables.poly[i],binaryarray[i+k]);
			    }
		    }
		    
		    String CRC = Integer.toString(binaryarray[size-11], 16);
		    for(int i = size; i<padsize; i++)
		    	CRC += Integer.toString(binaryarray[i], 16);
		    
		    CRC = binaryToHex(CRC);
		    CRC = CRC.replaceAll(" ", "0");
		    CRC = "0000" + CRC;
		    
		    if(x==8){
		    	System.out.println(textarray[x-1] + CRC.toLowerCase() + " - " + CRC.toLowerCase());
		    }
		    else
		    {
		    	System.out.println(textarray[x-1] + " - " + CRC.toLowerCase());
		    }
		    
		    CRCheck = CRC;
	    }
		System.out.println("\nCRC16 result : " + CRCheck.toLowerCase() + "\n");
		
		if(Variables.isVerify && CRCheck.toLowerCase().equals(Variables.CRC))
			System.out.println("CRC 16 verification passsed");
	}
	
	//A method that processes the user input and returns a String array of 8 rows of 64 characters per row.
	private static String[] processinput(Scanner textfile){
		
		//Get the entire input into one string
		String plaintext = textfile.nextLine();
		while (textfile.hasNextLine()){
		    plaintext += textfile.nextLine();
		    }
		
		//Print the plaintext
		printinput(plaintext);
		
		//If it has a CRC attached, we just want the plaintext.
		if (plaintext.length() > 504){
		Variables.CRC = plaintext.substring(504);
		plaintext = plaintext.substring(0,504);
		}
	    
		//Pad with extra "." if necessary
		 while (plaintext.length() != 504){
		    	plaintext += ".";
		    }
		
		//Separate into 8 rows of characters of 64 characters each.
	    int count = 0;
	    int line = 0;
	    String[] textarray = new String[8];
	    for (int i=0; i<8; i++)
	    	textarray[i] = "";
	    
	    for (int i = 0; i<plaintext.length(); i++){
			   count++;
			   textarray[line] += plaintext.charAt(i);
			  if (count%64 == 0){
				   line++;
			  }
		   }
	    
	    return textarray;
	}
	
	//A method that prints the plain input in rows of 64 characters long.
	private static void printinput(String plaintext){
		System.out.println("CRC16 Input text from file\n");
		
		int count = 0;
		   
		   for (int i = 0; i<plaintext.length(); i++){
			   System.out.print(plaintext.charAt(i));
			   count++;
			  if (count%64 == 0){
				   System.out.println();
			  }
		   }
	}
	
	//Method that turns binary into hex
	private static String binaryToHex(String bin) {
		   return String.format("%4X", Long.parseLong(bin,2)) ;
	}
	
	//Takes in two integers that are in the format of bits and returns the XOR of them
	private static int xor(int poly, int bytes){
		return (poly ^ bytes);
	}
}