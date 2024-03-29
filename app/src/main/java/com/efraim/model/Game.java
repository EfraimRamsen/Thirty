package com.efraim.model;

import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

/**
 * An object of the Game class is created in onCreate() in MainActivity.
 * Game contains the array of currently rolled dice 'mDiceArray',
 * what round (1-10) and dice throw (0-3) it currently is and also creates
 * a new instance of the Score class when created.
 * 'latestScoreDiceList' contains the dice used in the latest calculated score.
 * 'usedChoiceButtonIDs' contains the choices that are used to check that each
 * button only can be used once in a game.
 * 'gameOver' will be set to true after round 10.
 *
 * @author Efraim Ramsén
 */
public class Game {

	private Dice[] mDiceArray;
	private int mRound;
	private int mDiceThrow;
	private Score score;
	private ArrayList<Dice> latestScoreDiceList;
	private ArrayList<Integer> usedChoiceButtonIDs = new ArrayList<>();
	private boolean gameOver = false;

	private static final int DICE_OFF = 0;
	private static final int DICE_STANDARD = 1;
	private static final int DICE_LOCKED = 2;

	/**
	 * The constructor will set the dice as they are displayed at the
	 * start of the game. The score count will start on the first time
	 * the player rolls the dice.
	 */
	public Game(){
		mDiceArray = new Dice[] {
		new Dice(1,DICE_STANDARD),
		new Dice(2,DICE_STANDARD),
		new Dice(3,DICE_STANDARD),
		new Dice(4,DICE_STANDARD),
		new Dice(5,DICE_STANDARD),
		new Dice(6,DICE_STANDARD)};
		mDiceThrow = 0;
		mRound = 1;
		score = new Score();
	}

	/**
	 * @return gameOver, boolean that should be false before the end of round 10
	 */
	public boolean getGameOver(){
		return gameOver;
	}

	/**
	 * Set if the game is over (after round 10 is finished).
	 * @param gameOver, boolean true or false if game is over.
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * Get the Dice array.
	 * @return mDiceArray, array containing the currently displayed dice
	 */
	public Dice[] getDiceArray() {
		return mDiceArray;
	}

	/**
	 * Set the Dice array.
	 * @param mDiceArray, an array containing 6 Dice objects.
	 */
	public void setDiceArray(Dice[] mDiceArray) {
		this.mDiceArray = mDiceArray;
	}

	/**
	 * Used to see if a score choice button (LOW-12) already has been used to save
	 * a score
	 * @param choiceButton the choice button being checked
	 * @return boolean true if button is used, false if it's not used
	 */
	public boolean choiceButtonIsUsed(Button choiceButton){
		if(usedChoiceButtonIDs == null)
			return false;
		for(Integer i : usedChoiceButtonIDs){
			if(i == choiceButton.getId())
				return true;
		}
		return false;
	}

	/**
	 * @return ArrayList with all the choice buttons that have been used already
	 */
	public ArrayList<Integer> getUsedChoiceButtonIDs() {
		return usedChoiceButtonIDs;
	}

//	/**
//	 * TEST
//	 * Used in rollAllDice() instead of rollADice() to set each dice
//	 * manually instead of randomly.
//	 *
//	 */
//	private Dice[] DICETESTARRAY = new Dice[]{
//			new Dice(6,1),
//			new Dice(5,1),
//			new Dice(3,1),
//			new Dice(4,1),
//			new Dice(3,1),
//			new Dice(3,1),
//	};

	/**
	 * Set all the dice in mDiceArray to individual random scores (1-6)
	 * unless the dice state is locked.
	 */
	public void rollAllDice(){
		for (int i = 0; i < mDiceArray.length; i++){
			if(mDiceArray[i].getDiceState() != DICE_LOCKED) {
				mDiceArray[i] = rollADice(mDiceArray[i]); //Comment this line when using line below
//				mDiceArray[i] = DICETESTARRAY[i]; //TEST CODE
			}
		}
	}

	/**
	 * Set a dice score to a random number (1-6) and it's state to standard.
	 * @param dice, the Dice object that will be set
	 * @return Dice object after score and state is set.
	 */
	private Dice rollADice(Dice dice){
		dice.setDiceScore(randomD6());
		dice.setDiceState(DICE_STANDARD);
		return dice;
	}

	/**
	 * Calculates the highest possible score by creating a new object of
	 * DiceScoreCalculation and saving the list of dice used for the highest
	 * score to latestScoreDiceList. This is to display the possible score
	 * every time a choice button is used but to save the score only when that
	 * button is confirmed and the round is over.
	 * @param buttonScoreChoice, the score button being pressed
	 * @param mDiceArray, the currently displayed set of dice
	 */
	public void diceScoreCalculation(int buttonScoreChoice, Dice[] mDiceArray){
		DiceScoreCalculation d = new DiceScoreCalculation(buttonScoreChoice, mDiceArray);
		latestScoreDiceList = d.getDiceForMaxScore();
	}

	/**
	 * Sets the state of all dice in mDiceArray
	 * @param state, the state to set to all dice (DICE_OFF, DICE_STANDARD, DICE_LOCKED)
	 */
	public void setAllDiceState(int state){
		for(Dice d : mDiceArray){
			d.setDiceState(state);
		}
	}

	/**
	 * Check if all the displayed dice are set to state DICE_STANDARD
	 * @return boolean, true/false
	 */
	public boolean allDiceStandardState(){
		for (Dice d : mDiceArray) {
			if (d.getDiceState() != DICE_STANDARD){
				return false;
			}
		}
		return true;
	}
	/**
	 * Check if all the displayed dice are set to state DICE_LOCKED
	 * @return boolean, true/false
	 */
	public boolean allDiceLockedState(){
		for (Dice d : mDiceArray) {
			if (d.getDiceState() != DICE_LOCKED){
				return false;
			}
		}
		return true;
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
	 * @param diceButtonNumber, number of the diceButton (0-5)
	 * @return Dice object from the mDiceArray index
	 */
	public Dice getDiceForButton(int diceButtonNumber){
		return mDiceArray[diceButtonNumber];
	}

	/**
	 * Get ArrayList with the dice used when calculating the latest
	 * maximum score for a score choice button.
	 * @return latestScoreDiceList, an ArrayList with 0-6 dice
	 */
	public ArrayList<Dice> getLatestScoreDiceList() {
		return latestScoreDiceList;
	}

	/**
	 * Set the latest score Dice list to keep the possible score from the latest pressed
	 * score choice button.
	 * @param latestScoreDiceList, ArrayList with the Dice used to calculate the possible score.
	 */
	public void setLatestScoreDiceList(ArrayList<Dice> latestScoreDiceList) {
		this.latestScoreDiceList = latestScoreDiceList;
	}

	/**
	 * Set the list of used score choice buttons to keep track of the buttons that have been used
	 * in the game.
	 * @param usedChoiceButtonIDs
	 */
	public void setUsedChoiceButtonIDs(ArrayList<Integer> usedChoiceButtonIDs) {
		this.usedChoiceButtonIDs = usedChoiceButtonIDs;
	}

	/**
	 * Calculates the total dice score for a list of dice
	 * @param diceList, the ArrayList of dice that should be calculated
	 * @return int score, the total score integer value
	 */
	public int getDiceListScore(ArrayList<Dice> diceList){
		int score = 0;
		for(Dice d : diceList){
			score += d.getDiceScore();
		}
		return score;
	}

	/**
	 * @return int mDiceThrow, the number of throws that has been made
	 * under the active round (0-3)
	 */
	public int getDiceThrow() {
		return mDiceThrow;
	}

	/**
	 * Set the round of the game.
	 * @param mRound, int - should be 1-10
	 */
	public void setRound(int mRound) {
		this.mRound = mRound;
	}

	/**
	 * Set how many dice throws (rolls) has been made in the active round.
	 * @param mDiceThrow, int - should be 0-3
	 */
	public void setDiceThrow(int mDiceThrow) {
		this.mDiceThrow = mDiceThrow;
	}

	/**
	 * Increase int mDiceThrow, this should happen after pushing the roll button
	 */
	public void incrementDiceThrow(){
		mDiceThrow++;
	}

	/**
	 * Used to finish a round (the game has a total of 10 rounds). If the last round is finishing,
	 * the gameOver boolean will be set to true and the button listener createConfirmButton() in
	 * MainActivity will open the score activity. Otherwise it will increment the round number,
	 * reset the dice throw number and set all dice to state DICE_OFF
	 */
	public void finishRound(){
		ArrayList<Dice> roundScore = getLatestScoreDiceList();
		System.out.println("finishRound() with roundScore ArrayList<Dice>:"+roundScore);
		score.addToScoreForEachRound(getDiceListScore(roundScore));

		if(mRound == 10){
			gameOver = true;
			System.out.println("Game over! :)");

		}
		else{
		mRound++;
		mDiceThrow = 0;
		setAllDiceState(DICE_OFF);
			}
	}

	/**
	 * @return int mRound, the round number that is active
	 */
	public int getRound() {
		return mRound;
	}

	/**
	 * Get the active Score object
	 * @return Score object
	 */
	public Score getScore() {
		return score;
	}

}


