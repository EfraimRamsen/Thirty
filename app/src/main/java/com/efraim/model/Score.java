
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


	public void addToScoreForEachRound(int scoreToAdd){
		mScoreForEachRound.add(scoreToAdd);
		mTotalScore = mTotalScore + scoreToAdd;
	}

	public int getTotalScore() {
		return mTotalScore;
	}

	public void setTotalScore(int mTotalScore) {
		this.mTotalScore = mTotalScore;
	}

	public ArrayList<Integer> getScoreForEachRound() {
		return mScoreForEachRound;
	}

	public void setScoreForEachRound(ArrayList<Integer> mScoreForEachRound) {
		this.mScoreForEachRound = mScoreForEachRound;
	}
}
