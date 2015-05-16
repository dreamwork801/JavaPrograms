import java.util.Random;


public class Lion implements Movements {
	
	public String getName(){
		return "Lion";
	}

	@Override
	public void speak() {
		System.out.println("Hello I am the Lion!");

	}

	@Override
	public void eat() {
		Random generator = new Random();
		int rand = generator.nextInt(3);
		switch (rand){
			case 0: System.out.println("The Lion isn't hungry");
					break;
			case 1: System.out.println("You fed the Lion a steak");
					break;
			case 2: System.out.println("You fed the Lion leftover stew");
					break;
		}
	}

	@Override
	public void drink() {
		Random generator = new Random();
		int rand = generator.nextInt(2);
		switch (rand){
		case 0: System.out.println("The Lion isn't thirsty");
				break;
		case 1: System.out.println("You gave the Lion water");
				break;
		}
	}

	@Override
	public void play() {
		Random generator = new Random();
		int rand = generator.nextInt(2);
		switch (rand){
		case 0: System.out.println("The Lion doesn't feel like playing");
				break;
		case 1: System.out.println("The Lion is now playing with his ball");
				break;
		}

	}

}
