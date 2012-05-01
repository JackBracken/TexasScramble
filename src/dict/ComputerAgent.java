package dict;
import java.util.Random;

public class ComputerAgent {
	private int purse, riskTolerance, moodiness, handValue, mood; //initialize variables
	private Random generator = new Random(); //intiialize random number generator
	private EnglishScrabbleScorer wordScorer = new EnglishScrabbleScorer(); // intiialize scrabble word scorer

	ComputerAgent(int initialPurse, int rT, int m) {
		// initialise variables
		riskTolerance = rT;
		purse = initialPurse;
		moodiness = m;
	}

	public int getCurrentBankroll() {
		return purse;
	}

	public void addToCurrentBankroll(int delta) {// when it wins
		purse += delta;
	}

	public void takeFromCurrentBankroll(int delta) { // when it loses
		purse -= delta;
	}

	public boolean isBankrupt() { // return true if has no money left
		return purse == 0;
	}

	public boolean shouldFold(String bestWord) { // return true to fold
		handValue = wordScorer.getScore(bestWord);
		mood = generator.nextInt(moodiness);
		return generator.nextInt(100) > (riskTolerance + handValue + mood);
	}

	public boolean shouldRaise(String bestWord) { // return true to raise
		handValue = wordScorer.getScore(bestWord);
		mood = generator.nextInt(moodiness);
		return generator.nextInt(100) < (riskTolerance + handValue + mood);
	}

	public boolean shouldCheck(String bestWord) { // return true to check
		return (!shouldFold(bestWord) && !shouldRaise(bestWord));
	}
	
	public static void main(String[] args){
		String bestWord = "squirdelljuppe";
		ComputerAgent alice = new ComputerAgent(100,50,40);
		ComputerAgent bob = new ComputerAgent(100,20,10);
		ComputerAgent chuck = new ComputerAgent(100,80,20);
		ComputerAgent dave = new ComputerAgent(100,90,25);
		ComputerAgent eve = new ComputerAgent(100,100,100);
	
		System.out.println("\nAlice\n");
		System.out.println(alice.shouldFold(bestWord));
		System.out.println(alice.shouldCheck(bestWord));
		System.out.println(alice.shouldRaise(bestWord));

		System.out.println("\nBob\n");
		System.out.println(bob.shouldFold(bestWord));
		System.out.println(bob.shouldCheck(bestWord));
		System.out.println(bob.shouldRaise(bestWord));
		
		System.out.println("\nChuck\n");
		System.out.println(chuck.shouldFold(bestWord));
		System.out.println(chuck.shouldCheck(bestWord));
		System.out.println(chuck.shouldRaise(bestWord));
		
		System.out.println("\nDave\n");
		System.out.println(dave.shouldFold(bestWord));
		System.out.println(dave.shouldCheck(bestWord));
		System.out.println(dave.shouldRaise(bestWord));
		
		System.out.println("\nEve\n");
		System.out.println(eve.shouldFold(bestWord));
		System.out.println(eve.shouldCheck(bestWord));
		System.out.println(eve.shouldRaise(bestWord));
		
	}
}