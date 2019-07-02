package com.efraim.model;

import java.util.Random;

public class Game {
	//TEST
//	private Dice[] DICETESTARRAY = new Dice[]{
//			new Dice(1,1),
//			new Dice(1,1),
//			new Dice(5,1),
//			new Dice(6,1),
//			new Dice(1,1),
//			new Dice(1,1),
//	};

	private Dice[] mDiceArray;
	private int mRound; //1-10
	private int mDiceThrow; //1-3
	private Score score;

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
		mRound = 1;
		mDiceThrow = 1;

		score = new Score(); //todo använd den här, börjar på 0
	}

	Dice[] getDiceArray() {
		return mDiceArray;
	}

	public void rollAllDice(){
		for (int i = 0; i < mDiceArray.length; i++){
			if(mDiceArray[i].getDiceState() != DICE_LOCKED) {
				mDiceArray[i] = rollADice(mDiceArray[i]);
//				mDiceArray[i] = DICETESTARRAY[i]; //TODO TESTKOD för att bestämma värden
			}
		}
	}

	private Dice rollADice(Dice dice){
		dice.setDiceScore(randomD6());
		dice.setDiceState(DICE_STANDARD);
		return dice;
	}

	/**
	 * Create a random number for a D6 dice.
	 * @return int 1-6
	 */
	private int randomD6(){
		Random r = new Random();
		int low = 1;
		int high = 7;

		return r.nextInt(high-low) + low;
	}

	/**
	 * @param diceButtonNumber number of the diceButton (0-5)
	 * @return Dice object from the mDiceArray index
	 */
	public Dice getDiceForButton(int diceButtonNumber){
		return mDiceArray[diceButtonNumber];
	}

	public int getRound() {
		return mRound;
	}

	public void setRound(int mRound) {
		this.mRound = mRound;
	}

	public int getDiceThrow() {
		return mDiceThrow;
	}

	public void setDiceThrow(int mDiceThrow) {
		this.mDiceThrow = mDiceThrow;
	}

	public void incrementDiceThrow(){
		this.mDiceThrow = mDiceThrow++;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
}


