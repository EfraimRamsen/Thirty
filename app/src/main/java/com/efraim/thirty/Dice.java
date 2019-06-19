package com.efraim.thirty;

import java.util.Random;

public class Dice {

	private int mImageResId;
	private int mDiceScore;
	private int mDiceState;

	private static final int DICE_OFF = 0;
	private static final int DICE_STANDARD = 1;
	private static final int DICE_LOCKED = 2;

	public Dice(int diceScore, int diceState){
		mImageResId = setDiceImage(diceScore, diceState);
		mDiceScore = diceScore;
		mDiceState = diceState;
	}

	/**
	 * Sets the right image for a dice depending on its' score (1-6)
	 * and state (DICE_STANDARD, DICE_LOCKED, DICE_OFF).
	 *
	 * @param diceScore
	 * @param diceState
	 * @return imageResId to have a reference for the dice image to use.
	 */
	public int setDiceImage(int diceScore, int diceState){
		int imageResId = R.mipmap.thirty_launcher; // if this is visible something is very wrong :)

		switch (diceScore){
			case 1:{
				if(diceState == DICE_STANDARD){
					imageResId = R.mipmap.dice1;

				}else if(diceState == DICE_LOCKED){
					imageResId = R.mipmap.blue_dice1;

				}else if(diceState == DICE_OFF){
					imageResId = R.mipmap.off_dice1;
				}
				break;
			}
			case 2:{
				if(diceState == DICE_STANDARD){
					imageResId = R.mipmap.dice2;

				}else if(diceState == DICE_LOCKED){
					imageResId = R.mipmap.blue_dice2;

				}else if(diceState == DICE_OFF){
					imageResId = R.mipmap.off_dice2;
				}
				break;
			}
			case 3:{
				if(diceState == DICE_STANDARD){
					imageResId = R.mipmap.dice3;

				}else if(diceState == DICE_LOCKED){
					imageResId = R.mipmap.blue_dice3;

				}else if(diceState == DICE_OFF){
					imageResId = R.mipmap.off_dice3;
				}
				break;
			}
			case 4:{
				if(diceState == DICE_STANDARD){
					imageResId = R.mipmap.dice4;

				}else if(diceState == DICE_LOCKED){
					imageResId = R.mipmap.blue_dice4;

				}else if(diceState == DICE_OFF){
					imageResId = R.mipmap.off_dice4;
				}
				break;
			}
			case 5:{
				if(diceState == DICE_STANDARD){
					imageResId = R.mipmap.dice5;

				}else if(diceState == DICE_LOCKED){
					imageResId = R.mipmap.blue_dice5;

				}else if(diceState == DICE_OFF){
					imageResId = R.mipmap.off_dice5;
				}
				break;
			}
			case 6:{
				if(diceState == DICE_STANDARD){
					imageResId = R.mipmap.dice6;

				}else if(diceState == DICE_LOCKED){
					imageResId = R.mipmap.blue_dice6;

				}else if(diceState == DICE_OFF){
					imageResId = R.mipmap.off_dice6;
				}
				break;
			}
		}
		return imageResId;
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

	public int getImageResId() {
		return mImageResId;
	}

	public void setImageResId(int imageResId) {
		mImageResId = imageResId;
	}

	public int getDiceScore() {
		return mDiceScore;
	}

	public void setDiceScore(int diceScore) {
		mDiceScore = diceScore;
	}

	public int getDiceState() {
		return mDiceState;
	}

	public void setDiceState(int diceState) {
		mDiceState = diceState;
	}
}
