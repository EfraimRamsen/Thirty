
package com.efraim.model;

import java.util.ArrayList;

/**
 * Holds the total score of a game and lists of used dice for each round in
 * the game to display at the score screen after the game is over.
 * @author Efraim Ramsén
 */
public class Score{
	private int mTotalScore = 0;
	private ArrayList<Integer> mScoreForEachRound = new ArrayList<>();

	/**
	 * Adds the score from a round to the total score and to a list of scores for each round
	 * to be displayed after the game is over.
	 * @param scoreToAdd, int - the score from a finished round to add.
	 */
	public void addToScoreForEachRound(int scoreToAdd){
		mScoreForEachRound.add(scoreToAdd);
		mTotalScore = mTotalScore + scoreToAdd;
	}

	/**
	 * @return mTotalScore, int - the total score of the game (so far).
	 */
	public int getTotalScore() {
		return mTotalScore;
	}

	/**
	 * Set the total score of the game (so far).
	 * @param mTotalScore, int - the score to be set.
	 */
	public void setTotalScore(int mTotalScore) {
		this.mTotalScore = mTotalScore;
	}

	/**
	 * Get the score for each round played.
	 * @return mScoreForEachRound, ArrayList<Integer> - the list with the score for each round on each index (so far).
	 */
	public ArrayList<Integer> getScoreForEachRound() {
		return mScoreForEachRound;
	}

	/**
	 * Set the score for each round played.
	 * @param mScoreForEachRound, ArrayList<Integer> - the list with the score for each round on each index.
	 */
	public void setScoreForEachRound(ArrayList<Integer> mScoreForEachRound) {
		this.mScoreForEachRound = mScoreForEachRound;
	}
}
