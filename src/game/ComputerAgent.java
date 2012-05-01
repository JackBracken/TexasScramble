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
		} else {
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
}