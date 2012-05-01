package game;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class TileBag {
	private int tileIndex = 0;
	private char[] pocketTiles = new char[8];
	private char[] flopTiles = new char[3];
	
	@SuppressWarnings("boxing")
	Character[] tileArray = new Character[] {
		// TODO Read tiles from config file;
		'e','e','e','e','e','e','e','e','e','e',
		'e','e','a','a','a','a','a','a','a','a',
		'a','i','i','i','i','i','i','i','i','i',
		'o','o','o','o','o','o','o','o','n','n',
		'n','n','n','n','r','r','r','r','r','r',
		't','t','t','t','t','t','l','l','l','l',
		's','s','s','s','u','u','u','u','d','d',
		'd','d','g','g','g','b','b','c','c','m',
		'm','p','p','f','f','h','h','v','v','w',
		'w','y','y','k','j','x','q','z'
	};
	
	List<Character> tileList = Arrays.asList(tileArray);
	
	public void shuffleTiles(){
		Collections.shuffle(tileList);
	}
	
	@SuppressWarnings("boxing")
	public char[] dealPlayerTiles(int players){ // 2 to each player
		for(int i = 0;i < players*2;i++){
			// cycle through players handing out tiles
	
			pocketTiles[tileIndex] = tileList.get(tileIndex);
			tileIndex++;
		}
		return pocketTiles;
	}

	@SuppressWarnings("boxing")
	public char[] flop() {  // 3 community tiles
		for(int i = 0;i < 3;i++){
			flopTiles[i] = tileList.get(tileIndex);
			tileIndex++;
		}
		return flopTiles;
	}
	
	@SuppressWarnings("boxing")
	public char turn() {  // next community tile
		char t = tileList.get(tileIndex);
		tileIndex++;
		return t;
	}
	
	@SuppressWarnings("boxing")
	public char river() { // final community tile
		char r = tileList.get(tileIndex);
		tileIndex = 0;
		return r;
	}
}