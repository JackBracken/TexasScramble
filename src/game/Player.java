package game;

public abstract class Player {
	protected int purse;
	protected boolean inRound;
	protected boolean inGame = true;
	protected boolean isPlayersTurn = false;
	protected boolean hasMetBet;
	protected int smallBlind = 5, bigBlind = 10, currentBet;	
	protected char[] pocket = new char[2];
	
	Player(int purseSize) {
		purse = purseSize;
	}

	public void setPlayersTurn() {
		isPlayersTurn = true;
	}
	
	public void endPlayersTurn() {
		isPlayersTurn = false;
	}
	
	public void smallBlind(){
		purse -= smallBlind;
		currentBet = smallBlind;
	}
	
	public int sBAmount(){
		return smallBlind;
	}
	
	public int bBAmount(){
		return bigBlind;
	}
	
	public void bigBlind() {
		purse -= bigBlind;
		hasMetBet = true;
	}
	
	public void fold() {
		inRound = false;
		endPlayersTurn();
	}

	public int call(int currentStake) {
		if (purse >= currentStake) {
			purse -= currentStake;
			endPlayersTurn();
		} else {
			inRound = false;
		}
		return currentStake;
	}

	public int raise(int currentStake, int raiseAmount) {
		if(purse >= currentStake + raiseAmount){
			purse -= currentStake + raiseAmount;
			endPlayersTurn();
		} else {
			System.out.println("You do not have enough money to raise");
		}
		
		return currentStake + raiseAmount;
	}

	public void check() {
		if(hasMetBet){
			endPlayersTurn();
		}
	}

	public void pocketTiles(char a, char b){
		pocket[0] = a;
		pocket[1] = b;
	}
	
	public char getFirstTile(){
		return pocket[0];
	}
	
	public char getSecondTile(){
		return pocket[1];
	}
	
	public String takeTurn(){
		return null;
	}
}
