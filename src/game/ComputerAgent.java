package game;

import java.util.Random;

import dict.DictionaryTable;
import dict.EnglishScrabbleScorer;

public class ComputerAgent extends Player {
	private int purse, riskTolerance, moodiness, handValue, mood, difficulty, wordLen; // initialize variables
	private String word;
	private Random generator = new Random(); // intiialize random number
	private EnglishScrabbleScorer wordScorer = new EnglishScrabbleScorer(); 
	private DictionaryTable dict = new DictionaryTable();

	
	
	public ComputerAgent(int initialPurse, int riskTol, int m) {
		super(initialPurse);
		// initialise variables
		riskTolerance = riskTol;
		purse = initialPurse;
		moodiness = m;
		difficulty = (int) (Math.random()*10);
		if(difficulty < 3){
			wordLen = 3;
		} else if(difficulty < 6){
			wordLen = 4;
		} else if(difficulty < 8){
			wordLen = 5;
		} else if(difficulty <= 10){
			wordLen = 6;
		}
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

	public String takeTurn(String letters, int currentStake) {
		word = dict.getBestWordIn(wordLen, letters);
		String action = "";
		if(shouldFold(word)){
			fold();
			action = "Computer agent folded";
		} else if(shouldCheck(word)){
			check();
			action = "Computer agent checked";
		} else if(shouldRaise(word)){
			raise(currentStake, currentStake);
			action = "Computer agent raised by " + currentStake;
		}
		return action;
	}

	public static void main(String[] args) {
		String bestWord = "squirdelljuppe";
		ComputerAgent alice = new ComputerAgent(100, 50, 40);
		ComputerAgent bob = new ComputerAgent(100, 20, 10);
		ComputerAgent chuck = new ComputerAgent(100, 80, 20);
		ComputerAgent dave = new ComputerAgent(100, 90, 25);
		ComputerAgent eve = new ComputerAgent(100, 100, 100);

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