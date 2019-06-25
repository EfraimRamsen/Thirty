package com.efraim.model;

import java.util.Random;

public class Game {
	private Dice[] mDiceArray;

	public Game(){
		mDiceArray = new Dice[] {
		new Dice(1,1),
		new Dice(2,1),
		new Dice(3,1),
		new Dice(4,1),
		new Dice(5,1),
		new Dice(6,1)};

//		Score score = new Score(); //todo använd den här, börjar på 0
	}

	public void rollAllDice(){
		for (int i = 0; i < mDiceArray.length; i++){
				mDiceArray[i] = rollADice(mDiceArray[i]);
		}
	}

	public void rollSelectedOffDice(){
		for (int i = 0; i < mDiceArray.length; i++){
			if(mDiceArray[i].getDiceState() < 1){
				mDiceArray[i] = rollADice(mDiceArray[i]);
			}
		}
	}

	public Dice rollADice(Dice dice){
		dice.setDiceScore(randomD6());
		dice.setDiceState(1);
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


