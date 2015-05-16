import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class hillcipher {
	   public static void main(String[] args) throws IOException { 
		   
		   //Get File scanners   
		   Scanner keyfile = new Scanner(new File(args[0]));
		   Scanner textfile = new Scanner(new File(args[1]));
		  
		   //Initialize Key Array
		   int size = keyfile.nextInt();
		   int[][] array = new int[size][size];
		   
		   //Scan in Key Array from file
		   for(int i = 0; i<size; i++){
			   for(int j = 0; j<size; j++){
				   array[i][j] = keyfile.nextInt();
			   }
		   }
		   
		   //Initialize/Scan plaintext String
		   String plaintext = textfile.nextLine();
		   
		   while (textfile.hasNextLine()){
			   plaintext += textfile.nextLine();
		   }
		   
		   //Remove all spaces, punctuation and make all lowercase
		   plaintext = plaintext.replaceAll("[^a-zA-Z ]", "").replaceAll("\\s+","").toLowerCase();
		   
		   System.out.println();
		   System.out.println();
		   System.out.println("Key Matrix:");
		   System.out.println();
		   
		   
		   //Print Key File
		   for(int i = 0; i<size; i++){
			   for(int j = 0; j<size; j++){
				   System.out.print(array[i][j] + " ");
			   }
			   System.out.println();
		   }
		   
		   //Get length of plaintext and add x if it doesn't mod size
		   int length = plaintext.length();
		   while (length%size !=0){
			   plaintext += 'x';
			   length = plaintext.length();
		   }
		   
		   //Print Plain Text
		   System.out.println();
		   System.out.println();
		   System.out.println("Plaintext:");
		   System.out.println();
		   
		   int count = 0;
		   
		   for (int i = 0; i<plaintext.length(); i++){
			   System.out.print(plaintext.charAt(i));
			   count++;
			  if (count%80 == 0){
				   System.out.println();
			  }
		   }
		   
		   System.out.println();
		   System.out.println();
		   
		   int[] pl = new int[size]; //plain letter integers
		   int[] cl = new int[size]; //cipher letter integers
		   char[] cipherletters =  new char[size]; //cipher letter characters 
		   String ciphertext = new String(); //cipher string
		   
		   for (int i = 0; i<length; i=i+size){
			   
			   //Get numerical values for plaintext
			   for (int j = 0; j<size; j++){
				   pl[j] = plaintext.charAt(j+i)-97;
			   }
			   
			   //Initialize cl
			   for (int j=0; j<size; j++){
				   cl[j]=0;
			   }
			   
			   //Get cipher numbers by matrix multiplication
			   for (int j = 0; j<size; j++){
				   for (int k =0; k<size; k++){
					   cl[j] += array[j][k] * pl[k];
				   }
			       cl[j] = cl[j]% 26 + 97;
			   }
		
			   //Get letters for ciphernumbers
			   for (int j = 0; j<size; j++){
				   cipherletters[j] = (char)cl[j];
			   }
			   
			   //Add cipher letters to cipher string
			   for (int j = 0; j<size; j++){
				   ciphertext += cipherletters[j];
			   }
		   }
		   
		   //Print out ciphertext to screen
		   System.out.println("Ciphertext:");
		   System.out.println();
		   
		   count = 0;
		   
		   for (int i = 0; i<ciphertext.length(); i++){
			   System.out.print(ciphertext.charAt(i));
			   count++;
			  if (count%80 == 0){
				   System.out.println();
			  }
		   }
		   
		   System.out.println();
		   System.out.println();
		   
		   //Close input and file scanners
		   keyfile.close();
		   textfile.close();
	   }
}