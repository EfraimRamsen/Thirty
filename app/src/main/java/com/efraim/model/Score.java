
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
	private ArrayList<Integer> mScoreForEachRound = new ArrayList<>();

	/**
	 * Adds a list with dice to the list mDiceForEachRound and updates the
	 * total score. It's supposed to add one list per finished round.
	 * @param diceList the list of dice being added.
	 */
	public void addDiceList(ArrayList<Dice> diceList){
		mDiceForEachRound.add(diceList);
		mTotalScore = 0;
		mScoreForEachRound.clear();

		for (ArrayList<Dice> list : mDiceForEachRound){
			if(list == null){
				mScoreForEachRound.add(0);
				continue;
			}
			int i = 0;
			for(Dice d : list){
				i += d.getDiceScore();
				mTotalScore += d.getDiceScore();
			}
			mScoreForEachRound.add(i);
		}
	}

	/**
	 * @return mDiceForEachRound, an ArrayList of ArrayLists for each round with all the Dice that was used
	 */
	public ArrayList<ArrayList<Dice>> getDiceForEachRound() {
		return mDiceForEachRound;
	}

	/**
	 * @return mScoreForEachRound, an ArrayList of Integers with the scored points for each round
	 */
	public ArrayList<Integer> getScoreForEachRound(){//TODO
		return mScoreForEachRound;
	}
}
