package com.efraim.model;

import java.util.Random;

public class Game {

	private Dice[] DICETESTARRAY = new Dice[]{
			new Dice(2,1),
			new Dice(2,1),
			new Dice(2,1),
			new Dice(2,1),
			new Dice(2,1),
			new Dice(2,1),
	};
	private Dice[] mDiceArray;
	private static final int DICE_OFF = 0;
	private static final int DICE_STANDARD = 1;
	private static final int DICE_LOCKED = 2;

	public Game(){
		mDiceArray = new Dice[] {
		new Dice(1,DICE_STANDARD),
		new Dice(2,DICE_STANDARD),
		new Dice(3,DICE_STANDARD),
		new Dice(4,DICE_STANDARD),
		new Dice(5,DICE_STANDARD),
		new Dice(6,DICE_STANDARD)};

//		Score score = new Score(); //todo använd den här, börjar på 0
	}

	public Dice[] getDiceArray() {
		return mDiceArray;
	}
	public void rollAllDice(){
		for (int i = 0; i < mDiceArray.length; i++){
			if(mDiceArray[i].getDiceState() != DICE_LOCKED) {
//				mDiceArray[i] = rollADice(mDiceArray[i]);
				mDiceArray[i] = DICETESTARRAY[i]; //TODO TESTKOD för att bestämma värden
			}
		}
	}

//	public void rollSelectedOffDice(){
//		for (int i = 0; i < mDiceArray.length; i++){
//				mDiceArray[i] = rollADice(mDiceArray[i]);
//			}
//		}
//	}

	public Dice rollADice(Dice dice){
		dice.setDiceScore(randomD6());
		dice.setDiceState(DICE_STANDARD);
		return dice;
	}

	/**
	 * Create a random number for a D6 dice.
	 * @return int 1-6
	 */
	public int randomD6(){
		Random r = new Random();
		int low = 1;
		int high = 7;
		int result = r.nextInt(high-low) + low;

		return result;
	}

	/**
	 * @param diceButtonNumber number of the diceButton (0-5)
	 * @return Dice object from the mDiceArray index
	 */
	public Dice getDiceForButton(int diceButtonNumber){
		return mDiceArray[diceButtonNumber];
	}

	//todo testkod
	public String mDiceArrayToString(){
		String message = "mDiceArrayToString: \n";
		for(int i = 0; i < mDiceArray.length; i++ ){
			message += "Dice"+(i+1) + ": Score = " + mDiceArray[i].getDiceScore() + " State = " + mDiceArray[i].getDiceState() +"\n";
		}
		return message;
	}

}


