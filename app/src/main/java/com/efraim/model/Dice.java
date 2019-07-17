package com.efraim.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.efraim.activity.R;

/**
 * The class for creating Dice objects
 */
public class Dice implements Parcelable {

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


	protected Dice(Parcel in) {
		mImageResId = in.readInt();
		mDiceScore = in.readInt();
		mDiceState = in.readInt();
	}

	public static final Creator<Dice> CREATOR = new Creator<Dice>() {
		@Override
		public Dice createFromParcel(Parcel in) {
			return new Dice(in);
		}

		@Override
		public Dice[] newArray(int size) {
			return new Dice[size];
		}
	};

	/**
	 * Sets the correct image for a dice depending on its' score (1-6)
	 * and state (DICE_STANDARD, DICE_LOCKED, DICE_OFF).
	 *
	 * @param diceScore, int value that is the score of the dice being set (1-6).
	 * @param diceState, int value that is the state of the dice being set.
	 *                   (0 = DICE_OFF, 1 = DICE_STANDARD, 2 = DICE_LOCKED)
	 * @return imageResId, int, to have a unique numeric reference for the dice image to use.
	 */
	public int setDiceImage(int diceScore, int diceState){
		int imageResId = 0;

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
	 * Get the score of a dice.
	 * @return int mDiceScore, (should be 1-6)
	 */
	public int getDiceScore() {
		return mDiceScore;
	}

	/**
	 * Sets the score for a dice.
	 * @param diceScore, int (should be 1-6)
	 */
	public void setDiceScore(int diceScore) {
		mDiceScore = diceScore;
	}

	/**
	 * @return int mDiceState, (0 = DICE_OFF, 1 = DICE_STANDARD, 2 = DICE_LOCKED)
	 */
	public int getDiceState() {
		return mDiceState;
	}

	/**
	 * Sets the state of a dice.
	 * @param diceState, int (0 = DICE_OFF, 1 = DICE_STANDARD, 2 = DICE_LOCKED)
	 */
	public void setDiceState(int diceState) {
		mDiceState = diceState;
	}

	/**
	 * TEST
	 * Used for printouts in the console when testing
	 * @return String message, with a dice score number
	 */
	@Override
	public String toString(){
		String message = ""+this.getDiceScore();
		return message;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mImageResId);
		dest.writeInt(mDiceScore);
		dest.writeInt(mDiceState);
	}
}
