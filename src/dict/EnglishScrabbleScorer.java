package dict;
public class EnglishScrabbleScorer {

	public int getScore(String word) {
		// Initialize variables, populate score array with letter values etc
		final int LETTER_A = 1;
		final int LETTER_B = 3;
		final int LETTER_C = 3;
		final int LETTER_D = 2;
		final int LETTER_E = 1;
		final int LETTER_F = 4;
		final int LETTER_G = 2;
		final int LETTER_H = 4;
		final int LETTER_I = 1;
		final int LETTER_J = 8;
		final int LETTER_K = 5;
		final int LETTER_L = 1;
		final int LETTER_M = 3;
		final int LETTER_N = 1;
		final int LETTER_O = 1;
		final int LETTER_P = 3;
		final int LETTER_Q = 10;
		final int LETTER_R = 1;
		final int LETTER_S = 1;
		final int LETTER_T = 1;
		final int LETTER_U = 1;
		final int LETTER_V = 4;
		final int LETTER_W = 4;
		final int LETTER_X = 8;
		final int LETTER_Y = 4;
		final int LETTER_Z = 10;
		final int SKIP = 97;
		
		int total = 0, charScore;
		char eval;
		
		int[] scoreArray = new int[] { LETTER_A, LETTER_B, LETTER_C, LETTER_D,
				LETTER_E, LETTER_F, LETTER_G, LETTER_H, LETTER_I, LETTER_J,
				LETTER_K, LETTER_L, LETTER_M, LETTER_N, LETTER_O, LETTER_P,
				LETTER_Q, LETTER_R, LETTER_S, LETTER_T, LETTER_U, LETTER_V,
				LETTER_W, LETTER_X, LETTER_Y, LETTER_Z };
		String vord = word.toLowerCase();

		// Error handling to ensure only english language letters.
		try {
			// Iterate through word, converting each char to int (ascii value)
			// Subtract constant SKIP from ascii value to find index in score
			// array
			// Increment total score by character score
			for (int i = 0; i < vord.length(); i++) {
				eval = vord.charAt(i);
				charScore = (int) eval - SKIP;
				total += scoreArray[charScore];
			}

			// Return total word score

		} catch (ArrayIndexOutOfBoundsException e) {
			System.err
					.println("Invalid word '"+word+"'. Please use only letters - score was: "
							+ e.getMessage());
		}
		return total;
	}
}