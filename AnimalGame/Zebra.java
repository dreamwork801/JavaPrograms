import java.util.Random;


public class Zebra implements Movements {

	public String getName(){
		return "Zebra";
	}
	@Override
	public void speak() {
		System.out.println("Hello I am the Zebra!");

	}

	@Override
	public void eat() {
		Random generator = new Random();
		int rand = generator.nextInt(3);
		switch (rand){
			case 0: System.out.println("The Zebra isn't hungry");
					break;
			case 1: System.out.println("You fed the Zebra some hay");
					break;
			case 2: System.out.println("You fed the Zebra some grass");
					break;
		}
	}

	@Override
	public void drink() {
		Random generator = new Random();
		int rand = generator.nextInt(2);
		switch (rand){
		case 0: System.out.println("The Zebra isn't thirsty");
				break;
		case 1: System.out.println("You gave the Zebra water");
				break;
		}
	}

	@Override
	public void play() {
		Random generator = new Random();
		int rand = generator.nextInt(2);
		switch (rand){
		case 0: System.out.println("The Zebra is going to go lay down.");
				break;
		case 1: System.out.println("The Zebra is now running around!");
				break;
		}

	}

}
