/**
 * Zach's Zoo Program
 * 
 * @author Zachary
 *
 */
import java.util.Scanner;
public class AnimalTester {

	public static void main(String[] args) {
		
		//Creates an Array of animals
		Movements[] aArray = new Movements[] {new Dog(), new Zebra(), new Lion()};
		
		//Ask user for animal
		System.out.println("Welcome to Zach's Zoo!");
		System.out.println("Who would you like to see today? Press 0 for Dog, 1 for Zebra, 2 for Lion, or 3 to leave the Zoo");
		Scanner sc = new Scanner(System.in); //create scanner
		int selection = sc.nextInt(); //scan and store user input
	
		do {
			if (selection == 3)
				break;
			//Ask for movement of said animal
			System.out.println("What would you like the " + aArray[selection].getName() + " to do?");
			System.out.println("Press 0 to Eat, 1 to Drink, 2 to Speak or 3 to Play or 4 to Switch Animals");
			int selection2 = sc.nextInt(); //scan and store user input
			do {
				if (selection2 == 4)
					break;
				switch (selection2){
					
					case 0: aArray[selection].eat();
							break;
					case 1: aArray[selection].drink();
							break;
					case 2: aArray[selection].speak();
							break;
					case 3: aArray[selection].play();
							break;
				}//end switch
				System.out.println();
				System.out.println("What else would you like the " + aArray[selection].getName() + " to do?");
				System.out.println("Press 0 to Eat, 1 to Drink, 2 to Speak or 3 to Play or 4 to Switch Animals");
				selection2 = sc.nextInt(); //scan and store user input
			} while (selection2 !=4); //end inner do loop
			System.out.println();
			System.out.println("Who would you like to see now? Press 0 for Dog, 1 for Zebra, 2 for Lion, or 3 to leave the Zoo");
			selection = sc.nextInt(); //scan and store user input
		} while (selection !=3); //end outer do loop
		System.out.println("Thank you, come again.");
	} //end main
} //end class
