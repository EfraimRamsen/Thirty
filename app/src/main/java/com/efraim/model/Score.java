
package com.efraim.model;

import java.util.ArrayList;

/**
 * Holds the total score of a game and lists of used dice for each round in
 * the game to display at the score screen after the game is over.
 * @author Efraim Ramsén
 */
public class Score {
	private int mTotalScore = 0;
	private ArrayList<ArrayList<Dice>> mDiceForEachRound = new ArrayList<>();

	/**
	 * Adds a list with dice to the list mDiceForEachRound and updates the
	 * total score. It's supposed to add one list per finished round.
	 * @param diceList the list of dice being added.
	 */
	public void addDiceList(ArrayList<Dice> diceList){
		mDiceForEachRound.add(diceList);
		mTotalScore = 0;
		for (ArrayList<Dice> list : mDiceForEachRound){
			if(list == null)
				continue;
			for(Dice d : list){
				mTotalScore += d.getDiceScore();
			}
		}
	}
}
