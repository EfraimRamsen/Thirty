package com.efraim.model;

import java.util.Random;

public class GameLogic {



	private Dice[] mDiceArray;

	public void newGame(){
		for(int i = 0; i < 6; i++){
			mDiceArray[i] = new Dice(i+1,1); //TODO fortsätt med det här, gör så tärningarna ändras i den här arrayen
		}
		 //todo set score to 0

		//todo metod som rullar ny tärning
		//todo metod som fyller listan med nya tärningar
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

	public Dice[] getDiceArray() {
		return mDiceArray;
	}

	public void setDiceArray(Dice[] diceArray) {
		mDiceArray = diceArray;
	}

}


