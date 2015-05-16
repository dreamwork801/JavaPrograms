import java.util.Random;


public class Dog implements Movements {
	
	public String getName(){
		return "Dog";
	}

	public void speak() {
		System.out.println("Hello I am the Dog!");
		
	}

	public void eat() {
		Random generator = new Random();
		int rand = generator.nextInt(3);
		switch (rand){
			case 0: System.out.println("The Dog isn't hungry");
					break;
			case 1: System.out.println("You fed the Dog a big bowl of dog food");
					break;
			case 2: System.out.println("You fed the Dog a bird found in the yard");
					break;
		}
		
	}

	public void drink() {
		Random generator = new Random();
		int rand = generator.nextInt(2);
		switch (rand){
		case 0: System.out.println("The Dog isn't thirsty");
				break;
		case 1: System.out.println("You gave the Dog water");
				break;
		}
	}

	public void play() {
		Random generator = new Random();
		int rand = generator.nextInt(2);
		switch (rand){
		case 0: System.out.println("The Dog wants to go to sleep");
				break;
		case 1: System.out.println("The Dog is playing with his bone");
				break;
		}
		
	}



}
