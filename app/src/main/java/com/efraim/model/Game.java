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
 * 'usedChoiceButtons' contains the choices that are used to check that each
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
	private ArrayList<Button> usedChoiceButtons = new ArrayList<>();
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
//		mRound = 1;
		mRound = 9; //TODO för testande, återställ sen
		score = new Score();
	}

	/**
	 * @return gameOver, boolean that should be false before the end of round 10
	 */
	public boolean getGameOver(){
		return gameOver;
	}

	/**
	 * @return mDiceArray, array containing the currently displayed dice
	 */
	public Dice[] getDiceArray() {
		return mDiceArray;
	}

	/**
	 * Used to see if a score choice button (LOW-12) already has been used to save
	 * a score
	 * @param choiceButton the choice button being checked
	 * @return boolean true if button is used, false if it's not used
	 */
	public boolean choiceButtonIsUsed(Button choiceButton){
		if(usedChoiceButtons == null)
			return false;
		for(Button b : usedChoiceButtons){
			if(b == choiceButton)
				return true;
		}
		return false;
	}

	/**
	 * @return ArrayList with all the choice buttons that have been used already
	 */
	public ArrayList<Button> getUsedChoiceButtons() {
		return usedChoiceButtons;
	}

	/**
	 * TEST
	 * Used in rollAllDice() instead of rollADice() to set each dice
	 * manually instead of randomly.
	 *
	 */
//	private Dice[] DICETESTARRAY = new Dice[]{
//			new Dice(1,1),
//			new Dice(1,1),
//			new Dice(5,1),
//			new Dice(6,1),
//			new Dice(1,1),
//			new Dice(1,1),
//	};

	/**
	 * Set all the dice in mDiceArray to individual random scores (1-6)
	 * unless the dice state is locked.
	 */
	public void rollAllDice(){
		for (int i = 0; i < mDiceArray.length; i++){
			if(mDiceArray[i].getDiceState() != DICE_LOCKED) {
				mDiceArray[i] = rollADice(mDiceArray[i]);
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
	 * //TODO
	 * @param diceList
	 * @return
	 */
	public int getDiceListScore(ArrayList<Dice> diceList){
		int score = 0;
		for(Dice d : diceList){
			score += d.getDiceScore();
		}
		return score;
	}

	public int getDiceThrow() {
		return mDiceThrow;
	}

	public void incrementDiceThrow(){
		mDiceThrow++;
		System.out.println(mDiceThrow);
	}

	public void finishRound(){
		ArrayList<Dice> roundScore = getLatestScoreDiceList();
		score.addDiceList(roundScore);
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

	public int getRound() {
		return mRound;
	}
}


